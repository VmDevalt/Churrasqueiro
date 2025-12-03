package com.churrasqueiro.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.DatabaseConnection;

public class DashboardDAO {

    public DefaultCategoryDataset buscarTopMaisVendidos() throws DatabaseException {
        String sql =
            "SELECT ic.nome AS item, SUM(ip.quantidade) AS total_quantidade " +
            "FROM Itens_Pedido ip " +
            "JOIN Itens_Cardapio ic ON ic.id = ip.item_cardapio_id " +
            "GROUP BY ic.nome ORDER BY total_quantidade DESC LIMIT 5";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            while (rs.next()) {
                dataset.addValue(
                    rs.getInt("total_quantidade"),
                    "Quantidade",
                    rs.getString("item")
                );
            }

            return dataset;

        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar Top Mais Vendidos: " + e.getMessage());
        }
    }

    public DefaultCategoryDataset buscarFaturamentoPorDia() throws DatabaseException {
        String sql =
            "SELECT CAST(p.data_hora AS DATE) AS dia, SUM(p.total) AS faturamento " +
            "FROM Pedidos p GROUP BY CAST(p.data_hora AS DATE) ORDER BY dia";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            while (rs.next()) {
                dataset.addValue(
                    rs.getDouble("faturamento"),
                    "Faturamento",
                    rs.getString("dia")
                );
            }

            return dataset;

        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar Faturamento por Dia: " + e.getMessage());
        }
    }

    public DefaultPieDataset buscarFormasPagamento() throws DatabaseException {
    String sql = 
        "SELECT forma_pagamento, COUNT(*) AS quantidade " +
        "FROM Pedidos GROUP BY forma_pagamento";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        DefaultPieDataset dataset = new DefaultPieDataset();

        while (rs.next()) {
            String forma = rs.getString("forma_pagamento");
            int qtd = rs.getInt("quantidade");

            dataset.setValue(forma, qtd);
        }

        return dataset;

    } catch (Exception e) {
        throw new DatabaseException("Erro ao buscar formas de pagamento: " + e.getMessage());
        }
    }
}
