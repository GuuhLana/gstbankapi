package com.gtechsys.gtsbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtechsys.gtsbank.dto.EventoDto;
import com.gtechsys.gtsbank.service.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Eventos Controller")
@RestController
@RequestMapping("/api/eventos")
public class EventoController {
	@Autowired
	private EventoService eventoService;

	@Operation(summary = "Retorna todos os eventos barrados")
	@GetMapping("/listar")
	public ResponseEntity<?> consultaEventos() {
		try {
			List<EventoDto> eventos = eventoService.listaEventos();
			return new ResponseEntity<List<EventoDto>>(eventos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
