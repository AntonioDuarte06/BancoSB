package com.duarte.banco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duarte.banco.domain.Conta;
import com.duarte.banco.repository.ContaRepository;
import com.duarte.banco.services.exeptions.ObjectNotFoundException;

@Service
public class ContaService {

	@Autowired
	protected ContaRepository repo;

	@Autowired
	protected Conta conta;

	public Conta find(Integer id) {
		Optional<Conta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName()));
	}

	public List<Conta> findAll() {
		return repo.findAll();
	}

	public Conta create(Conta create) {
		create.setNumConta(null);
		return repo.save(create);
	}

	public Conta update(Conta obj) {
		find(obj.getNumConta());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}

	public double deposit(Double valor) {
		if (valor > 0) {
			conta.setSaldo(conta.getSaldo() + valor);
		}
		return valor;
	}

	public void cashOut(Double valor) {
		if (valor < conta.getSaldo() && valor > 0) {
			conta.setSaldo(conta.getSaldo() - valor);
		}
	}

	public void tranfer(ContaService destino, Double valor) {
		if (valor > 0) {
			cashOut(valor);
			destino.cashOut(valor);
		}
	}

}
