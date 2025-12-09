package com.churrasqueiro.business;

import com.churrasqueiro.data.RelatoriosDAO;
import com.churrasqueiro.exceptions.DatabaseException;

public class RelatoriosController {

    private final RelatoriosDAO relatoriosDAO;

    public RelatoriosController() {
        this.relatoriosDAO = new RelatoriosDAO();
    }

    public double getTotalVendas(String periodo) throws DatabaseException {
        return relatoriosDAO.getTotalVendas(periodo);
    }

    public String getItemMaisVendido(String periodo) throws DatabaseException {
        return relatoriosDAO.getItemMaisVendido(periodo);
    }

    public int getUnidadesMaisVendido(String periodo) throws DatabaseException {
        return relatoriosDAO.getUnidadesMaisVendido(periodo);
    }
}
