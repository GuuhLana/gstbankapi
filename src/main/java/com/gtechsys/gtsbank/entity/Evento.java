package com.gtechsys.gtsbank.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_EVENTOS")
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String message;
	private LocalDateTime horarioEvento;

	public Evento(String message, LocalDateTime horarioEvento) {
		super();
		this.message = message;
		this.horarioEvento = horarioEvento;
	}
	
	public Evento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getHorarioEvento() {
		return horarioEvento;
	}

	public void setHorarioEvento(LocalDateTime horarioEvento) {
		this.horarioEvento = horarioEvento;
	}
}
