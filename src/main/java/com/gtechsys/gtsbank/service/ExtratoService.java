package com.gtechsys.gtsbank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtechsys.gtsbank.dto.ExtratoDto;
import com.gtechsys.gtsbank.entity.Evento;
import com.gtechsys.gtsbank.entity.Extrato;
import com.gtechsys.gtsbank.repository.EventoRepository;
import com.gtechsys.gtsbank.repository.ExtratoRepository;

@Service
public class ExtratoService {

	@Autowired
	private ExtratoRepository extratoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	public List<ExtratoDto> consultaExtrato(Integer numeroConta, Integer numeroAgencia) {

		List<Extrato> listaTodosPeloDestino = extratoRepository
				.findAllByNumeroContaDestinoAndNumeroAgenciaDestino(numeroConta, numeroAgencia);
		List<Extrato> listaTodosPeloOrigem = extratoRepository
				.findAllByNumeroContaOrigemAndNumeroAgenciaOrigem(numeroConta, numeroAgencia);
		listaTodosPeloDestino.addAll(listaTodosPeloOrigem);

		ExtratoDto dto = new ExtratoDto();
		List<ExtratoDto> list = dto.converter(listaTodosPeloDestino);

		String eventoMessage = "Foi solicitado o extrato da conta de número: " + numeroConta + " e agencia: "
				+ numeroAgencia;
		Evento evento = new Evento(eventoMessage, LocalDateTime.now());
		eventoRepository.save(evento);
		return list;
	}
}
