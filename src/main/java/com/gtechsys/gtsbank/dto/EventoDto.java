package com.gtechsys.gtsbank.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.gtechsys.gtsbank.entity.Evento;

public class EventoDto {
	private Long id;
	private String message;
	private LocalDateTime horarioEvento;

	public EventoDto(String message, LocalDateTime horarioEvento) {
		this.message = message;
		this.horarioEvento = horarioEvento;
	}

	public EventoDto() {
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

	public EventoDto(Evento evento) {
		this.id = evento.getId();
		this.message = evento.getMessage();
		this.horarioEvento = evento.getHorarioEvento();

	}

	public List<EventoDto> converter(List<Evento> eventos) {
		return eventos.stream().map(EventoDto::new).collect(Collectors.toList());
	}

}
