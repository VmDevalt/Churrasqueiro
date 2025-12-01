package com.churrasqueiro.entities;

import java.sql.Date;

public class Pedido {
	private int idPedido;
	private int mesaId;
	private int garconId;
	private Date dataHora;
	private double desconto;
	private double acrescimo;
	private double total;
	private String status;
	private String formaPagamento;
	
	public Pedido (int idPedido, int mesaId, int garconId, Date dataHora, double desconto, double acrescimo, double total, String status, String formaPagamento) {
		this.idPedido = idPedido;
		this.mesaId = mesaId;
		this.garconId = garconId;
		this.dataHora = dataHora;
		this.desconto = desconto;
		this.acrescimo = acrescimo;
		this.total = total;
		this.status = status;
		this.formaPagamento = formaPagamento;
	}
	
	public Pedido() {
		//CONSTRUTOR VAZIO (SÓ PRA NÃO FICAR SEM NADA DENTRO)
	}
	
	public Pedido(int mesaId, int garconId, Date dataHora, double desconto, double acrescimo, double total, String status, String formaPagamento) {
		this.mesaId = mesaId;
		this.garconId = garconId;
		this.dataHora = dataHora;
		this.desconto = desconto;
		this.acrescimo = acrescimo;
		this.total = total;
		this.status = status;
		this.formaPagamento = formaPagamento;
	}
	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getMesaId() {
		return mesaId;
	}

	public void setMesaId(int mesaId) {
		this.mesaId = mesaId;
	}

	public int getGarconId() {
		return garconId;
	}

	public void setGarconId(int garconId) {
		this.garconId = garconId;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
