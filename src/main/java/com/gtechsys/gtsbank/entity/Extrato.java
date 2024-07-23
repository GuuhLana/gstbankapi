package com.gtechsys.gtsbank.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_EXTRATO")
public class Extrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	Integer numeroContaOrigem;
	Integer numeroAgenciaOrigem;
	Integer numeroContaDestino;
	Integer numeroAgenciaDestino;
	private Date dataTransferencia;

	private String dataFormatada;

	private double valor;

	public Extrato(Integer numeroContaOrigem, Integer numeroAgenciaOrigem, Integer numeroContaDestino,
			Integer numeroAgenciaDestino, Date dataTransferencia, double valor) {
		this.numeroContaOrigem = numeroContaOrigem;
		this.numeroAgenciaOrigem = numeroAgenciaOrigem;
		this.numeroContaDestino = numeroContaDestino;
		this.numeroAgenciaDestino = numeroAgenciaDestino;
		this.setDataTransferencia(dataTransferencia);
		this.valor = valor;
		System.out.println(this);
	}

	public Extrato() {

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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM dd HH:mm");
		this.dataFormatada = formatter.format(dataTransferencia);
	}

	public String getDataFormatada() {
		return dataFormatada;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
