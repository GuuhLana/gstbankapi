package com.gtechsys.gtsbank.model.request;

public class DepositoRequest {
	private Integer numeroConta;
	private Integer numeroAgencia;
	private double valor;

	public DepositoRequest(Integer numeroConta, Integer numeroAgencia, double valor) {
		super();
		this.numeroConta = numeroConta;
		this.numeroAgencia = numeroAgencia;
		this.valor = valor;
	}

	public DepositoRequest() {
		super();
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Integer getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(Integer numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
