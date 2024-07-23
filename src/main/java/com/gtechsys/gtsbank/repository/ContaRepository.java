package com.gtechsys.gtsbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtechsys.gtsbank.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
	
	boolean existsByNumeroAndAgencia(Integer numero, Integer agencia);
	
	Optional<Conta> findByNumeroAndAgencia(Integer numero, Integer agencia);
	
}
