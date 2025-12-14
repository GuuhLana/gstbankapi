package com.gtechsys.gtsbank.dto;

import com.gtechsys.gtsbank.entity.Conta;
import com.gtechsys.gtsbank.enums.TipoConta;

public class ContaDto {
	private Long id;
	private String titular;
	private int numero;
	private int agencia;
	private double saldo;
	private String cpf;
	private Integer idade;
	private TipoConta tipo;

	public Conta criarConta() {
		return new Conta(titular, agencia, numero, saldo, cpf, idade, tipo);
	}

	public ContaDto() {

	}

	public ContaDto(Conta conta) {
		this.id = conta.getId();
		this.titular = conta.getTitular();
		this.numero = conta.getNumero();
		this.agencia = conta.getAgencia();
		this.saldo = conta.getSaldo();
		this.cpf = conta.getCpf();
		this.idade = conta.getIdade();
		this.tipo = conta.getTipo();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}
}
