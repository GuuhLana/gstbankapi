package com.gtechsys.gtsbank.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtechsys.gtsbank.dto.ContaDto;
import com.gtechsys.gtsbank.entity.Conta;
import com.gtechsys.gtsbank.entity.Evento;
import com.gtechsys.gtsbank.entity.Extrato;
import com.gtechsys.gtsbank.model.response.SaldoResponse;
import com.gtechsys.gtsbank.repository.ContaRepository;
import com.gtechsys.gtsbank.repository.EventoRepository;
import com.gtechsys.gtsbank.repository.ExtratoRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ExtratoRepository extratoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	public SaldoResponse verificarSaldo(Integer numeroConta, Integer numeroAgencia) {
		Optional<Conta> conta = contaRepository.findByNumeroAndAgencia(numeroConta, numeroAgencia);
		SaldoResponse saldo = new SaldoResponse(conta.get().getNumero(), conta.get().getAgencia(),
				conta.get().getTitular(), conta.get().getSaldo());

		return saldo;
	}

	public List<Conta> verificarContas() {
		List<Conta> contas = contaRepository.findAll();
		return contas;
	}

	public void transferir(Integer numeroContaOrigem, Integer numeroAgenciaOrigem, Integer numeroContaDestino,
			Integer numeroAgenciaDestino, double valor) {
		Optional<Conta> contaOrigem = contaRepository.findByNumeroAndAgencia(numeroContaOrigem, numeroAgenciaOrigem);
		Optional<Conta> contaDestino = contaRepository.findByNumeroAndAgencia(numeroContaDestino, numeroAgenciaDestino);

		double taxa = valor * 0.01;

		if (verificaSaldoTransferencia(contaOrigem, valor)) {
			if (contaOrigem.get().getSaldo() >= valor) {
				double valorTaxado = valor - taxa;

				contaOrigem.get().transferir(valor);
				contaDestino.get().receberTransferencia(valorTaxado);

				contaRepository.save(contaOrigem.get());
				contaRepository.save(contaDestino.get());

				Extrato log = new Extrato(contaOrigem.get().getNumero(), contaOrigem.get().getAgencia(),
						contaDestino.get().getNumero(), contaDestino.get().getAgencia(),
						Calendar.getInstance().getTime(), valor);
				extratoRepository.save(log);

				String eventoMessage = "O titular: " + contaOrigem.get().getTitular()
						+ " realizou uma transferencia no valor de: " + valor + " para a conta do titular: "
						+ contaDestino.get().getTitular();
				Evento evento = new Evento(eventoMessage, LocalDateTime.now());
				eventoRepository.save(evento);

			} else {
				throw new RuntimeException("Não foi possivel realizar a transferencia, verifique os valores inseridos");
			}
		} else {
			throw new RuntimeException("Operação Negada! Saldo Insuficiênte");
		}
	}

	public ContaDto criarConta(ContaDto dto) {
		Conta conta = dto.criarConta();

		boolean existeConta = contaRepository.existsByNumeroAndAgencia(conta.getNumero(), conta.getAgencia());

		if (existeConta) {
			String eventoMessage = "Foi realizada a tentativa de criação de uma conta, porém, ja existem valores com os parâmetros fornecidos. ";
			Evento evento = new Evento(eventoMessage, LocalDateTime.now());
			eventoRepository.save(evento);

			throw new RuntimeException("Já existe uma conta cadastrada na agencia: " + conta.getAgencia()
					+ " com o número: " + conta.getNumero());
		} else {
			contaRepository.save(conta);
			ContaDto contaDTO = new ContaDto(conta);

			String eventoMessage = "Foi criada uma nova conta no nome de: " + conta.getTitular() + " ID: "
					+ conta.getId();
			Evento evento = new Evento(eventoMessage, LocalDateTime.now());
			eventoRepository.save(evento);

			return contaDTO;
		}
	}

	// Adicionar extrato ao realizar um deposito na conta
	public void depositarNaConta(Integer numeroConta, Integer numeroAgencia, double valor) {
		Optional<Conta> conta = contaRepository.findByNumeroAndAgencia(numeroConta, numeroAgencia);
		double saldoAtual = conta.get().getSaldo();
		double novoSaldo = saldoAtual + valor;

		conta.get().setSaldo(novoSaldo);
		contaRepository.save(conta.get());

		String eventoMessage = "Foi realizado um deposito no valor de: " + valor + " para a conta de numero: "
				+ numeroConta + " agencia: " + numeroAgencia;
		Evento evento = new Evento(eventoMessage, LocalDateTime.now());
		eventoRepository.save(evento);
	}

	public String deletarConta(Long id) {

		Long idConta = id;

		boolean existeConta = contaRepository.existsById(idConta);

		if (existeConta) {
			contaRepository.deleteById(idConta);

			String eventoMessage = "Uma conta com id: " + idConta + " foi deletada";
			Evento evento = new Evento(eventoMessage, LocalDateTime.now());
			eventoRepository.save(evento);
			return "Conta deleta com sucesso";
		} else {
			String eventoMessage = "Foi realizada uma tentativa de deleção de uma conta não existente";
			Evento evento = new Evento(eventoMessage, LocalDateTime.now());
			eventoRepository.save(evento);
			throw new RuntimeException("Conta não existe");
		}
	}

	public boolean verificaSaldoTransferencia(Optional<Conta> contaOrigem, double valor) {
		if (contaOrigem.get().getSaldo() >= valor) {
			return true;
		} else {
			return false;
		}
	}

}
