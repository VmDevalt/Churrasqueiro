package com.churrasqueiro.business;

import com.churrasqueiro.data.CaixaDAO;
import com.churrasqueiro.data.VendaDAO;
import com.churrasqueiro.entities.Caixa;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;

import java.sql.Date;
import java.util.Optional;

public class CaixaController {

    private final CaixaDAO caixaDAO;
    private final VendaDAO vendaDAO;

    public CaixaController() {
        this.caixaDAO = new CaixaDAO();
        this.vendaDAO = new VendaDAO();
    }

    public Caixa abrirCaixa(double saldoInicial, double metaFaturamento)
            throws ControllerException, DatabaseException {

        if (saldoInicial < 0) {
            throw new ControllerException("O saldo inicial não pode ser negativo.");
        }
        if (metaFaturamento < 0) {
            throw new ControllerException("A meta de faturamento não pode ser negativa.");
        }

        Optional<Caixa> caixaAberto = caixaDAO.buscarCaixaAberto();
        if (caixaAberto.isPresent()) {
            throw new ControllerException("Já existe um caixa aberto. Feche o caixa atual antes de abrir outro.");
        }

        Caixa caixa = new Caixa();
        caixa.setSaldoInicial(saldoInicial);
        caixa.setSaldoAtual(saldoInicial);
        caixa.setMetaFaturamento(metaFaturamento);
        caixa.setStatusCaixa(true);
        caixa.setVendasDia(0.0);
        caixa.setVendasMeta(metaFaturamento);

        return caixaDAO.inserir(caixa);
    }

    public void fecharCaixaAutomatico() throws ControllerException, DatabaseException {

        Optional<Caixa> caixaAbertoOpt = caixaDAO.buscarCaixaAberto();
        if (caixaAbertoOpt.isEmpty()) {
            throw new ControllerException("Não há caixa aberto para fechar.");
        }

        Caixa caixa = caixaAbertoOpt.get();

        Date diaCaixa = new Date(caixa.getDataAbertura().getTime());

        double vendasDia = vendaDAO.calcularTotalVendasDoDia(diaCaixa);

        double saldoFinal = caixa.getSaldoInicial() + vendasDia;

        caixa.setVendasDia(vendasDia);
        caixa.setSaldoAtual(saldoFinal);

        caixaDAO.fecharCaixa(caixa);
    }

    public Optional<Caixa> buscarCaixaAberto() throws DatabaseException {
        return caixaDAO.buscarCaixaAberto();
    }

    public boolean existeCaixaAberto() throws DatabaseException {
        return caixaDAO.buscarCaixaAberto().isPresent();
    }

}