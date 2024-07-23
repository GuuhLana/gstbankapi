package com.gtechsys.gtsbank.model.request;

public class ExtratoRequest {
	private Integer contaNumero;
	private Integer contaAgencia;

	public ExtratoRequest(Integer contaNumero, Integer contaAgencia) {
		super();
		this.contaNumero = contaNumero;
		this.contaAgencia = contaAgencia;
	}

	public ExtratoRequest() {
		super();
	}

	public Integer getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(Integer contaNumero) {
		this.contaNumero = contaNumero;
	}

	public Integer getContaAgencia() {
		return contaAgencia;
	}

	public void setContaAgencia(Integer contaAgencia) {
		this.contaAgencia = contaAgencia;
	}

}
