package com.gtechsys.gtsbank.model.request;

public class TransferenciaRequest {
	private Integer contaOrigemNumero;
	private Integer contaOrigemAgencia;
	private Integer contaDestinoNumero;
	private Integer contaDestinoAgencia;
	private double valor;

	public TransferenciaRequest(Integer contaOrigemNumero, Integer contaOrigemAgencia, Integer contaDestinoNumero,
			Integer contaDestinoAgencia, double valor) {
		super();
		this.contaOrigemNumero = contaOrigemNumero;
		this.contaOrigemAgencia = contaOrigemAgencia;
		this.contaDestinoNumero = contaDestinoNumero;
		this.contaDestinoAgencia = contaDestinoAgencia;
		this.valor = valor;
	}

	public TransferenciaRequest() {
		super();
	}

	public Integer getContaOrigemNumero() {
		return contaOrigemNumero;
	}

	public void setContaOrigemNumero(Integer contaOrigemNumero) {
		this.contaOrigemNumero = contaOrigemNumero;
	}

	public Integer getContaOrigemAgencia() {
		return contaOrigemAgencia;
	}

	public void setContaOrigemAgencia(Integer contaOrigemAgencia) {
		this.contaOrigemAgencia = contaOrigemAgencia;
	}

	public Integer getContaDestinoNumero() {
		return contaDestinoNumero;
	}

	public void setContaDestinoNumero(Integer contaDestinoNumero) {
		this.contaDestinoNumero = contaDestinoNumero;
	}

	public Integer getContaDestinoAgencia() {
		return contaDestinoAgencia;
	}

	public void setContaDestinoAgencia(Integer contaDestinoAgencia) {
		this.contaDestinoAgencia = contaDestinoAgencia;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
