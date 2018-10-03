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

	public Conta find(Integer id) {
		Optional<Conta> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Conta.class.getName()));
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

	public double deposit(int contaId, Double valor) {
		Conta conta = repo.findBynumConta(contaId);

		if (valor > 0 && conta.getSaldo() < 0) {
			conta.setSaldo(conta.getSaldo() + valor);
			
			conta.setLimiteExtra(conta.getLimiteExtra() - conta.getSaldo());
			
		}else {
			conta.setSaldo(conta.getSaldo() + valor);

		}
		repo.save(conta);
		
		return valor;
	}

	public void cashOut(int contaId, Double valor) {
		Conta conta = repo.findBynumConta(contaId);

		if (valor <= conta.getSaldo() && valor > 0) {
			conta.setSaldo(conta.getSaldo() - valor);

		} else if (valor <= conta.getLimiteExtra() && valor > 0) {
			conta.setSaldo(conta.getSaldo() - valor);
			conta.setLimiteExtra(conta.getLimiteExtra() - valor);
			
		}else {
			System.out.println("Saldo insulficiente!");
		}
		repo.save(conta);
	}

	public void tranfer(int contaOrigem, int contaDestino, Double valor) {

		if (valor > 0) {
			cashOut(contaOrigem, valor);

			deposit(contaDestino, valor);
		}
	}

}
