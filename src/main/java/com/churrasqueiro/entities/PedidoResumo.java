package com.churrasqueiro.entities;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResumo {

    private int id;
    private int numeroMesa;
    private String garconLogin;
    private LocalDateTime dataHora;
    private double desconto;
    private double acrescimo;
    private double total;
    private String formaPagamento;
    private String status;
    private List<String> itensDescricao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }
    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getGarconLogin() {
        return garconLogin;
    }
    public void setGarconLogin(String garconLogin) {
        this.garconLogin = garconLogin;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
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

    public String getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getItensDescricao() {
        return itensDescricao;
    }
    public void setItensDescricao(List<String> itensDescricao) {
        this.itensDescricao = itensDescricao;
    }
}
