package com.duarte.banco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duarte.banco.domain.Conta;
import com.duarte.banco.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repo;

	public Conta find(Integer id) {
		Optional<Conta> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
