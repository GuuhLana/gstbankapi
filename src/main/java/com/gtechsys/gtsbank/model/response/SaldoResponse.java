package com.gtechsys.gtsbank.model.response;

import com.gtechsys.gtsbank.enums.TipoConta;

public class SaldoResponse {
	private String nomeTitular;
	private String cpf;
	private Integer idade;
	private double saldo;
	private Integer numeroAgencia;
	private Integer numeroConta;
	private TipoConta tipoDaConta;

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public TipoConta getTipoDaConta() {
		return tipoDaConta;
	}

	public void setTipoDaConta(TipoConta tipoDaConta) {
		this.tipoDaConta = tipoDaConta;
	}

	public SaldoResponse(String nomeTitular, String cpf, Integer idade, double saldo, Integer numeroAgencia,
			Integer numeroConta, TipoConta tipoDaConta) {
		this.nomeTitular = nomeTitular;
		this.cpf = cpf;
		this.idade = idade;
		this.saldo = saldo;
		this.numeroAgencia = numeroAgencia;
		this.numeroConta = numeroConta;
		this.tipoDaConta = tipoDaConta;
	}

}
