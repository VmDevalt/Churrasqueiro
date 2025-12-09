package com.churrasqueiro.entities;

import java.util.ArrayList;
import java.util.List;

public class PedidoEmMontagem {

    private List<ItemCardapio> itens = new ArrayList<>();

    private String numeroMesa;
    private String nomeCliente;
    private String formaPagamento;
    private double acrescimo;
    private double desconto;
    private String observacoes;

    public List<ItemCardapio> getItens() {
        return itens;
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public void removerItem(ItemCardapio item) {
        itens.remove(item);
    }

    public double getTotalItens() {
        double total = 0.0;
        for (ItemCardapio item : itens) {
            if (item != null) {
                total += item.getPreco();
            }
        }
        return total;
    }

    public double getTotalFinal() {
        return getTotalItens() + acrescimo - desconto;
    }
    
    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
