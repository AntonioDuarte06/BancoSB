package com.duarte.banco;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.duarte.banco.domain.Conta;
import com.duarte.banco.repository.ContaRepository;

@SpringBootApplication
public class BancoSbApplication implements CommandLineRunner {

	@Autowired
	private ContaRepository contaRepo;

	public static void main(String[] args) {
		SpringApplication.run(BancoSbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Conta c1 = new Conta(null, 1000, "Antônio Duarte", 1500);
		Conta c2 = new Conta(null, 2000, "Vilma Rorigues", 2500);
		Conta c3 = new Conta(null, 3000, "Vilma Rorigues", 3500);

		contaRepo.saveAll(Arrays.asList(c1, c2, c3));

	}
}
