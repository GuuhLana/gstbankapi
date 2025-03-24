package com.gtechsys.gtsbank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gtechsys.gtsbank.dto.ExtratoDto;
import com.gtechsys.gtsbank.entity.Evento;
import com.gtechsys.gtsbank.entity.Extrato;
import com.gtechsys.gtsbank.repository.ContaRepository;
import com.gtechsys.gtsbank.repository.EventoRepository;
import com.gtechsys.gtsbank.repository.ExtratoRepository;

@Service
public class ExtratoService {

    private static final Logger logger = LoggerFactory.getLogger(ExtratoService.class);

    @Autowired
    private ExtratoRepository extratoRepository;
    
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private EventoRepository eventoRepository;


    public List<ExtratoDto> consultaExtrato(Integer numeroConta, Integer numeroAgencia) {
        logger.info("Consultando extrato da conta: Agência {}, Número {}", numeroAgencia, numeroConta);

        if (!contaRepository.existsByNumeroAndAgencia(numeroConta, numeroAgencia)) {
            throw new RuntimeException("Conta não encontrada");
        }

        List<Extrato> listaTodosPeloDestino = extratoRepository
                .findAllByNumeroContaDestinoAndNumeroAgenciaDestino(numeroConta, numeroAgencia);
        List<Extrato> listaTodosPeloOrigem = extratoRepository
                .findAllByNumeroContaOrigemAndNumeroAgenciaOrigem(numeroConta, numeroAgencia);
        listaTodosPeloDestino.addAll(listaTodosPeloOrigem);

        String eventoMessage = String.format("Extrato solicitado: Conta %s, Agência %s", numeroConta, numeroAgencia);
        Evento evento = new Evento(eventoMessage, LocalDateTime.now());
        eventoRepository.save(evento);
        logger.info(eventoMessage);

        ExtratoDto extratoDto = new ExtratoDto();
        return extratoDto.converter(listaTodosPeloDestino);
    }
}