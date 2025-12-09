package com.churrasqueiro.entities;

import java.sql.Timestamp;

public class Caixa {

    private Integer id;
    private double saldoInicial;
    private double saldoAtual;
    private double metaFaturamento;
    private Timestamp dataAbertura;
    private Timestamp dataFechamento;
    private boolean statusCaixa;
    private double vendasDia;
    private double vendasMeta;

    public Caixa() {}

    public Caixa(Integer id, double saldoInicial, double saldoAtual, double metaFaturamento,
                 Timestamp dataAbertura, Timestamp dataFechamento,
                 boolean statusCaixa, double vendasDia, double vendasMeta) {
        this.id = id;
        this.saldoInicial = saldoInicial;
        this.saldoAtual = saldoAtual;
        this.metaFaturamento = metaFaturamento;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.statusCaixa = statusCaixa;
        this.vendasDia = vendasDia;
        this.vendasMeta = vendasMeta;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(double saldoInicial) { this.saldoInicial = saldoInicial; }

    public double getSaldoAtual() { return saldoAtual; }
    public void setSaldoAtual(double saldoAtual) { this.saldoAtual = saldoAtual; }

    public double getMetaFaturamento() { return metaFaturamento; }
    public void setMetaFaturamento(double metaFaturamento) { this.metaFaturamento = metaFaturamento; }

    public Timestamp getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(Timestamp dataAbertura) { this.dataAbertura = dataAbertura; }

    public Timestamp getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(Timestamp dataFechamento) { this.dataFechamento = dataFechamento; }

    public boolean isStatusCaixa() { return statusCaixa; }
    public void setStatusCaixa(boolean statusCaixa) { this.statusCaixa = statusCaixa; }

    public double getVendasDia() { return vendasDia; }
    public void setVendasDia(double vendasDia) { this.vendasDia = vendasDia; }

    public double getVendasMeta() { return vendasMeta; }
    public void setVendasMeta(double vendasMeta) { this.vendasMeta = vendasMeta; }
}
