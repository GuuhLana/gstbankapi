package com.gtechsys.gtsbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtechsys.gtsbank.dto.EventoDto;
import com.gtechsys.gtsbank.entity.Evento;
import com.gtechsys.gtsbank.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	public List<EventoDto> listaEventos() {
		List<Evento> listaEventos = eventoRepository.findAll();

		EventoDto dto = new EventoDto();
		List<EventoDto> list = dto.converter(listaEventos);

		return list;
	}
}
