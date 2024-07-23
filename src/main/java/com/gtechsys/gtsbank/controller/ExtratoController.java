package com.gtechsys.gtsbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtechsys.gtsbank.dto.ExtratoDto;
import com.gtechsys.gtsbank.model.request.ExtratoRequest;
import com.gtechsys.gtsbank.service.ExtratoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Extrato Controller")
@RestController
@RequestMapping("/api/extrato")
public class ExtratoController {
	@Autowired
	private ExtratoService extratoService;

	@Operation(summary = "Retorna o extrato da conta informada")
	@GetMapping(path = "/consultar")
	public ResponseEntity<?> consultaExtrato(@RequestBody ExtratoRequest request) {
		try {
			Integer nmrConta = request.getContaNumero();
			Integer nmrAgencia = request.getContaAgencia();

			List<ExtratoDto> extratos = extratoService.consultaExtrato(nmrConta, nmrAgencia);
			return new ResponseEntity<List<ExtratoDto>>(extratos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
}
