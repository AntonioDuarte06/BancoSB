package com.duarte.banco.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.duarte.banco.domain.Conta;
import com.duarte.banco.repository.ContaRepository;
import com.duarte.banco.services.exeptions.DataIntegrityExeption;
import com.duarte.banco.services.exeptions.ObjectNotFoundException;

@Service
public class ContaService {

	@Autowired
	protected ContaRepository repo;

	public Conta find(Integer id) {
		Optional<Conta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName()));
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
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExeption("Não é possível excluir uma conta pois possui");
		}
	}
}
