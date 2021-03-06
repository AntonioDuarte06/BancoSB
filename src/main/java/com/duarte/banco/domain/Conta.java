package com.duarte.banco.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numConta;
	private double saldo;
	private String nome;
	private double limiteExtra;

	public Conta() {

	}

	public Conta(Integer numConta, double saldo, String nome, double limiteExtra) {
		super();
		this.numConta = numConta;
		this.saldo = saldo;
		this.nome = nome;
		this.limiteExtra = limiteExtra;
	}

	public Integer getNumConta() {
		return numConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getNome() {
		return nome;
	}

	public double getLimiteExtra() {
		return limiteExtra;
	}

	public void setNumConta(Integer numConta) {
		this.numConta = numConta;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLimiteExtra(double limiteExtra) {
		this.limiteExtra = limiteExtra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numConta == null) ? 0 : numConta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (numConta == null) {
			if (other.numConta != null)
				return false;
		} else if (!numConta.equals(other.numConta))
			return false;
		return true;
	}

}
