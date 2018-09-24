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

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

	@Autowired
	private ContaService service;

	@GetMapping("/{id}")
	public Conta find(@PathVariable Integer id) {
		Conta obj = service.find(id);
		return obj;
	}
	
	@GetMapping("/all")
	public  List<Conta> findAll() {
		List<Conta> lista = service.findAll();
		return  lista;
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Conta obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getNumConta()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/sacar/{id}")
	public ResponseEntity<Void> sacar(@PathVariable Integer id, @RequestBody Double valor) {
		Conta obj = service.find(id);
		obj.cashOut(valor);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> uptade(@PathVariable Integer id, @RequestBody Conta obj) {
		obj.setNumConta(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
}
