package com.gtechsys.gtsbank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtechsys.gtsbank.dto.ContaDto;
import com.gtechsys.gtsbank.entity.Conta;
import com.gtechsys.gtsbank.model.request.DepositoRequest;
import com.gtechsys.gtsbank.model.request.TransferenciaRequest;
import com.gtechsys.gtsbank.model.response.SaldoResponse;
import com.gtechsys.gtsbank.service.ContaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Conta Controller")
@RestController
@RequestMapping("/api/conta")
public class ContaController {
	@Autowired
	private ContaService contaService;

	@Operation(summary = "Retorna as informações referentes a conta selecionada")
	@GetMapping(path = "/{numeroConta}/{numeroAgencia}")
	public ResponseEntity<?> consultarSaldo(@PathVariable Integer numeroConta, @PathVariable Integer numeroAgencia) {
		try {
			SaldoResponse saldo = contaService.verificarSaldo(numeroConta, numeroAgencia);
			return new ResponseEntity<SaldoResponse>(saldo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Retorna as informações referentes as contas cadastradas")
	@GetMapping(path = "/lista-contas")
	public ResponseEntity<?> consultarContas() {
		try {
			List<Conta> contas = contaService.verificarContas();
			return new ResponseEntity<>(contas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Cadastra uma conta")
	@PostMapping(path = "/cadastrar")
	public ResponseEntity<?> criarConta(@RequestBody ContaDto dto) {
		try {
			ContaDto contaDTO = contaService.criarConta(dto);
			return new ResponseEntity<ContaDto>(contaDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Transfere valores entre contas")
	@PostMapping(path = "/transferir")
	public ResponseEntity<Map<String, String>> transferir(@RequestBody TransferenciaRequest request) {
		try {
			contaService.transferir(request.getContaOrigemNumero(), request.getContaOrigemAgencia(),
					request.getContaDestinoNumero(), request.getContaDestinoAgencia(), request.getValor());
			Map<String, String> response = new HashMap<>();
			response.put("message", "Transferência realizada com sucesso");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> response = new HashMap<>();
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Deleta a conta")
	@DeleteMapping(path = "/deletar/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		try {
			contaService.deletarConta(id);
			return new ResponseEntity<String>("Conta deleta com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Deposita um valor em uma conta")
	@PostMapping(path = "/depositar")
	public ResponseEntity<String> depositar(@RequestBody DepositoRequest request) {
		try {
			contaService.depositarNaConta(request.getNumeroConta(), request.getNumeroAgencia(), request.getValor());
			return new ResponseEntity<String>("Deposito realizado com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
