package com.gtechsys.gtsbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtechsys.gtsbank.entity.Extrato;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

	List<Extrato> findAllByNumeroContaOrigemAndNumeroAgenciaOrigem(Integer numero, Integer agencia);

	List<Extrato> findAllByNumeroContaDestinoAndNumeroAgenciaDestino(Integer numero, Integer agencia);
}
