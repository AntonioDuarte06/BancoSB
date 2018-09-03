package com.duarte.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duarte.banco.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
