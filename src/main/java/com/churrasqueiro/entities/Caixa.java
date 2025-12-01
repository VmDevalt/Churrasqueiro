package com.churrasqueiro.entities;

import java.sql.Timestamp;

public class Caixa {
	private int id;
	private boolean status;
	private Timestamp dataAbertura;
	private Timestamp dataFechamento;
	private double valorAbertura;
	private double valorFechamento;
	private double valorEntrada;
	private double valorSaida;
	
	public Caixa(int id, boolean status, Timestamp dataAbertura, Timestamp dataFechamento, double valorAbertura, double valorFechamento, double valorEntrada, double valorSaida) {
		this.id = id;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.valorAbertura = valorAbertura;
		this.valorFechamento = valorFechamento;
		this.valorEntrada = valorEntrada;
		this.valorSaida = valorSaida;
	}
	
	public Caixa() {
		//CONSTRUTOR VAZIO (SÓ PRA NÃO FICAR SEM NADA DENTRO)
	}
	
	public Caixa(boolean status, Timestamp dataAbertura, Timestamp dataFechamento, double valorAbertura, double valorFechamento, double valorEntrada, double valorSaida) {
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.valorAbertura = valorAbertura;
		this.valorFechamento = valorFechamento;
		this.valorEntrada = valorEntrada;
		this.valorSaida = valorSaida;	
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Timestamp getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Timestamp dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Timestamp getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Timestamp dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public double getValorAbertura() {
		return valorAbertura;
	}

	public void setValorAbertura(double valorAbertura) {
		this.valorAbertura = valorAbertura;
	}

	public double getValorFechamento() {
		return valorFechamento;
	}

	public void setValorFechamento(double valorFechamento) {
		this.valorFechamento = valorFechamento;
	}

	public double getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public double getValorSaida() {
		return valorSaida;
	}

	public void setValorSaida(double valorSaida) {
		this.valorSaida = valorSaida;
	}
	
}
