package com.duarte.banco.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contas")
public class ContasResource {

	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return  "Lista de contas";
	}
}
