package com.churrasqueiro.business;

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

}
