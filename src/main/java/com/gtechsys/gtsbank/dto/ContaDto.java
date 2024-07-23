package com.gtechsys.gtsbank.dto;

import com.gtechsys.gtsbank.entity.Conta;

public class ContaDto {
	private Long id;
	private String titular;
	private int numero;
	private int agencia;
	private double saldo;
	
	public Conta criarConta() {
		return new Conta(titular, numero, agencia, saldo);
	}
	
	public ContaDto() {
		
	}
	
	public ContaDto(Conta conta) {
		this.id = conta.getId();
		this.titular = conta.getTitular();
		this.numero = conta.getNumero();
		this.agencia = conta.getAgencia();
		this.saldo = conta.getSaldo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

}

