package com.duarte.banco.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.duarte.banco.domain.Conta;
import com.duarte.banco.services.ContaService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

	@Autowired
	private ContaService service;

	@GetMapping("/{id}")
	public Conta buscar(@PathVariable Integer id) {
		Conta obj = service.find(id);
		return obj;
	}
	
	@GetMapping("/all")
	public  List<Conta> buscarTodos() {
		List<Conta> lista = service.findAll();
		return  lista;
	}

	@PostMapping
	public ResponseEntity<Void> criar(@RequestBody Conta obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getNumConta()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/sacar/{id}")
	@JsonIgnore
	public ResponseEntity<Conta> sacar(@PathVariable Integer id, @RequestBody Double valor) {
		service.cashOut(id, valor);
		
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/update")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody Conta obj) {
		obj.setNumConta(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
}
