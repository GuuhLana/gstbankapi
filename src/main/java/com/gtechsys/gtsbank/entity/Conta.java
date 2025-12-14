package com.gtechsys.gtsbank.entity;

import java.util.Objects;

import com.gtechsys.gtsbank.enums.TipoConta;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_CONTA")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titular;
	private Integer agencia;
	private Integer numero;
	private double saldo;
	private String cpf;
	private Integer idade;

	@Enumerated(EnumType.STRING)
	private TipoConta tipo;

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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void transferir(double valor) {
		this.saldo -= valor;
	}

	public void receberTransferencia(double valor) {
		this.saldo += valor;
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

	public Conta() {
	}

	public Conta(String titular, Integer agencia, Integer numero, double saldo, String cpf, int idade, TipoConta tipo) {
		this.id = id;
		this.titular = titular;
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
		this.cpf = cpf;
		this.idade = idade;
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agencia, cpf, id, idade, numero, saldo, tipo, titular);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id)
				&& idade == other.idade && Objects.equals(numero, other.numero)
				&& Double.doubleToLongBits(saldo) == Double.doubleToLongBits(other.saldo) && tipo == other.tipo
				&& Objects.equals(titular, other.titular);
	}

}
