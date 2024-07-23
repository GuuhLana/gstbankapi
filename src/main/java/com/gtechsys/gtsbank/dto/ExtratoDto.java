package com.gtechsys.gtsbank.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gtechsys.gtsbank.entity.Extrato;

public class ExtratoDto {
	private Long id;
	Integer numeroContaOrigem;
	Integer numeroAgenciaOrigem;
	Integer numeroContaDestino;
	Integer numeroAgenciaDestino;
	private Date dataTransferencia;
	private double valor;

	public ExtratoDto() {

	}

	public Extrato criarExtrato() {
		return new Extrato(numeroContaOrigem, numeroAgenciaOrigem, numeroContaDestino, numeroAgenciaDestino,
				dataTransferencia, valor);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroContaOrigem() {
		return numeroContaOrigem;
	}

	public void setNumeroContaOrigem(Integer numeroContaOrigem) {
		this.numeroContaOrigem = numeroContaOrigem;
	}

	public Integer getNumeroAgenciaOrigem() {
		return numeroAgenciaOrigem;
	}

	public void setNumeroAgenciaOrigem(Integer numeroAgenciaOrigem) {
		this.numeroAgenciaOrigem = numeroAgenciaOrigem;
	}

	public Integer getNumeroContaDestino() {
		return numeroContaDestino;
	}

	public void setNumeroContaDestino(Integer numeroContaDestino) {
		this.numeroContaDestino = numeroContaDestino;
	}

	public Integer getNumeroAgenciaDestino() {
		return numeroAgenciaDestino;
	}

	public void setNumeroAgenciaDestino(Integer numeroAgenciaDestino) {
		this.numeroAgenciaDestino = numeroAgenciaDestino;
	}

	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public double getValor() {
		return valor;
	}

	public String getDataFormatada() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm");
		return formatter.format(dataTransferencia);
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ExtratoDto(Extrato extrato) {
		this.id = extrato.getId();
		this.numeroContaOrigem = extrato.getNumeroContaOrigem();
		this.numeroAgenciaOrigem = extrato.getNumeroAgenciaOrigem();
		this.numeroContaDestino = extrato.getNumeroContaDestino();
		this.numeroAgenciaDestino = extrato.getNumeroAgenciaDestino();
		this.dataTransferencia = extrato.getDataTransferencia();
		extrato.getDataFormatada();
		this.valor = extrato.getValor();
	}

	public List<ExtratoDto> converter(List<Extrato> extratos) {
		return extratos.stream().map(ExtratoDto::new).collect(Collectors.toList());
	}

}
