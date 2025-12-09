package com.churrasqueiro.business;

import java.time.LocalDate;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import com.churrasqueiro.data.DashboardDAO;
import com.churrasqueiro.exceptions.DatabaseException;

public class DashboardController {

    private final DashboardDAO dashboardDAO;

    public DashboardController() {
        this.dashboardDAO = new DashboardDAO();
    }

    public DefaultCategoryDataset obterTopMaisVendidos() throws DatabaseException {
        return dashboardDAO.buscarTopMaisVendidos();
    }

    public DefaultCategoryDataset obterFaturamentoPorDia() throws DatabaseException {
        return dashboardDAO.buscarFaturamentoPorDia();
    }

    public DefaultPieDataset obterFormasPagamento() throws DatabaseException {
        return dashboardDAO.buscarFormasPagamento();
    }

    public DefaultCategoryDataset obterTopMaisVendidos(LocalDate dataInicio, LocalDate dataFim) throws DatabaseException {
        return dashboardDAO.buscarTopMaisVendidos(dataInicio, dataFim);
    }

    public DefaultCategoryDataset obterFaturamentoPorDia(LocalDate dataInicio, LocalDate dataFim) throws DatabaseException {
        return dashboardDAO.buscarFaturamentoPorDia(dataInicio, dataFim);
    }

    public DefaultPieDataset obterFormasPagamento(LocalDate dataInicio, LocalDate dataFim) throws DatabaseException {
        return dashboardDAO.buscarFormasPagamento(dataInicio, dataFim);
    }
}
