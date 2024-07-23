package com.gtechsys.gtsbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtechsys.gtsbank.entity.Evento;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

}
