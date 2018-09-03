package com.duarte.banco.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duarte.banco.domain.Conta;

@RestController
@RequestMapping(value = "/contas")
public class ContaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Conta> listar() {

		Conta c1 = new Conta(1, 1000, "Duarte", 2000);
		Conta c2 = new Conta(2, 2000, "Vilma", 2000);

		List<Conta> lista = new ArrayList<Conta>();

		lista.add(c1);
		lista.add(c2);

		return lista;
	}

}
