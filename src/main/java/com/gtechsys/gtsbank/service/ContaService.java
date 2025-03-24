package com.gtechsys.gtsbank.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ExtratoRepository extratoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public SaldoResponse verificarSaldo(Integer numeroConta, Integer numeroAgencia) {
        logger.info("Verificando saldo da conta: Agência {}, Número {}", numeroAgencia, numeroConta);
        Conta conta = contaRepository.findByNumeroAndAgencia(numeroConta, numeroAgencia)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return new SaldoResponse(conta.getNumero(), conta.getAgencia(), conta.getTitular(), conta.getSaldo());
    }

    public List<Conta> verificarContas() {
        logger.info("Listando todas as contas");
        return contaRepository.findAll();
    }

    public void transferir(Integer numeroContaOrigem, Integer numeroAgenciaOrigem, Integer numeroContaDestino,
                           Integer numeroAgenciaDestino, double valor) {
        logger.info("Iniciando transferência: Origem Agência {}, Número {} -> Destino Agência {}, Número {}, Valor {}",
                numeroAgenciaOrigem, numeroContaOrigem, numeroAgenciaDestino, numeroContaDestino, valor);

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser positivo");
        }

        Conta contaOrigem = contaRepository.findByNumeroAndAgencia(numeroContaOrigem, numeroAgenciaOrigem)
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        Conta contaDestino = contaRepository.findByNumeroAndAgencia(numeroContaDestino, numeroAgenciaDestino)
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        if (contaOrigem.getSaldo() < valor) {
            logger.warn("Saldo insuficiente na conta de origem: Agência {}, Número {}", numeroAgenciaOrigem, numeroContaOrigem);
            throw new RuntimeException("Saldo insuficiente para transferência");
        }

        double taxa = valor * 0.01;
        double valorTaxado = valor - taxa;

        contaOrigem.transferir(valor);
        contaDestino.receberTransferencia(valorTaxado);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        Extrato log = new Extrato(contaOrigem.getNumero(), contaOrigem.getAgencia(),
                contaDestino.getNumero(), contaDestino.getAgencia(), Calendar.getInstance().getTime(), valor);
        extratoRepository.save(log);

        String eventoMessage = String.format("Transferência realizada: Titular %s -> Titular %s, Valor %s",
                contaOrigem.getTitular(), contaDestino.getTitular(), valor);
        Evento evento = new Evento(eventoMessage, LocalDateTime.now());
        eventoRepository.save(evento);

        logger.info("Transferência concluída com sucesso");
    }

    public ContaDto criarConta(ContaDto dto) {
        logger.info("Criando nova conta: {}", dto);
        Conta conta = dto.criarConta();

        if (contaRepository.existsByNumeroAndAgencia(conta.getNumero(), conta.getAgencia())) {
            String eventoMessage = "Tentativa de criação de conta com número e agência já existentes";
            Evento evento = new Evento(eventoMessage, LocalDateTime.now());
            eventoRepository.save(evento);
            logger.warn(eventoMessage);
            throw new RuntimeException("Conta já existe");
        }

        contaRepository.save(conta);
        String eventoMessage = String.format("Nova conta criada: Titular %s, ID %s", conta.getTitular(), conta.getId());
        Evento evento = new Evento(eventoMessage, LocalDateTime.now());
        eventoRepository.save(evento);
        logger.info(eventoMessage);

        return new ContaDto(conta);
    }

    public void depositarNaConta(Integer numeroConta, Integer numeroAgencia, double valor) {
        logger.info("Depositando na conta: Agência {}, Número {}, Valor {}", numeroAgencia, numeroConta, valor);

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo");
        }

        Conta conta = contaRepository.findByNumeroAndAgencia(numeroConta, numeroAgencia)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        String eventoMessage = String.format("Depósito realizado: Conta %s, Agência %s, Valor %s",
                numeroConta, numeroAgencia, valor);
        Evento evento = new Evento(eventoMessage, LocalDateTime.now());
        eventoRepository.save(evento);
        logger.info(eventoMessage);
    }

    public String deletarConta(Long id) {
        logger.info("Deletando conta: ID {}", id);

        if (!contaRepository.existsById(id)) {
            String eventoMessage = "Tentativa de deleção de conta inexistente";
            Evento evento = new Evento(eventoMessage, LocalDateTime.now());
            eventoRepository.save(evento);
            logger.warn(eventoMessage);
            throw new RuntimeException("Conta não existe");
        }

        contaRepository.deleteById(id);
        String eventoMessage = String.format("Conta deletada: ID %s", id);
        Evento evento = new Evento(eventoMessage, LocalDateTime.now());
        eventoRepository.save(evento);
        logger.info(eventoMessage);

        return "Conta deletada com sucesso";
    }
}