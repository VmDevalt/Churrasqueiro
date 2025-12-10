package com.churrasqueiro.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;

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
            "GROUP BY ic.nome " +
            "ORDER BY total_quantidade DESC " +
            "LIMIT 5";

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

    public DefaultCategoryDataset buscarTopMaisVendidos(LocalDate dataInicio, LocalDate dataFim) throws DatabaseException {
        String sql =
            "SELECT ic.nome AS item, SUM(ip.quantidade) AS total_quantidade " +
            "FROM Itens_Pedido ip " +
            "JOIN Itens_Cardapio ic ON ic.id = ip.item_cardapio_id " +
            "JOIN Pedidos p ON p.id = ip.pedido_id " +
            "WHERE CAST(p.data_hora AS DATE) BETWEEN ? AND ? " +
            "GROUP BY ic.nome " +
            "ORDER BY total_quantidade DESC " +
            "LIMIT 5";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(dataInicio));
            ps.setDate(2, Date.valueOf(dataFim));

            try (ResultSet rs = ps.executeQuery()) {

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                while (rs.next()) {
                    dataset.addValue(
                        rs.getInt("total_quantidade"),
                        "Quantidade",
                        rs.getString("item")
                    );
                }

                return dataset;
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar Top Mais Vendidos (com filtro): " + e.getMessage());
        }
    }

    public DefaultCategoryDataset buscarFaturamentoPorDia() throws DatabaseException {
        String sql =
            "SELECT CAST(p.data_hora AS DATE) AS dia, SUM(p.total) AS faturamento " +
            "FROM Pedidos p " +
            "GROUP BY CAST(p.data_hora AS DATE) " +
            "ORDER BY dia";

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

    public DefaultCategoryDataset buscarFaturamentoPorDia(LocalDate dataInicio, LocalDate dataFim) throws DatabaseException {
        String sql =
            "SELECT CAST(p.data_hora AS DATE) AS dia, SUM(p.total) AS faturamento " +
            "FROM Pedidos p " +
            "WHERE CAST(p.data_hora AS DATE) BETWEEN ? AND ? " +
            "GROUP BY CAST(p.data_hora AS DATE) " +
            "ORDER BY dia";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(dataInicio));
            ps.setDate(2, Date.valueOf(dataFim));

            try (ResultSet rs = ps.executeQuery()) {

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                while (rs.next()) {
                    dataset.addValue(
                        rs.getDouble("faturamento"),
                        "Faturamento",
                        rs.getString("dia")
                    );
                }

                return dataset;
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar Faturamento por Dia (com filtro): " + e.getMessage());
        }
    }

    public DefaultPieDataset buscarFormasPagamento() throws DatabaseException {
        String sql =
            "SELECT forma_pagamento, COUNT(*) AS quantidade " +
            "FROM Pedidos " +
            "GROUP BY forma_pagamento";

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
    
    public DefaultPieDataset buscarFormasPagamento(LocalDate dataInicio, LocalDate dataFim) throws DatabaseException {
        String sql =
            "SELECT forma_pagamento, COUNT(*) AS quantidade " +
            "FROM Pedidos " +
            "WHERE CAST(data_hora AS DATE) BETWEEN ? AND ? " +
            "GROUP BY forma_pagamento";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(dataInicio));
            ps.setDate(2, Date.valueOf(dataFim));

            try (ResultSet rs = ps.executeQuery()) {

                DefaultPieDataset dataset = new DefaultPieDataset();

                while (rs.next()) {
                    String forma = rs.getString("forma_pagamento");
                    int qtd = rs.getInt("quantidade");

                    dataset.setValue(forma, qtd);
                }

                return dataset;
            }

        } catch (Exception e) {
            throw new DatabaseException("Erro ao buscar formas de pagamento (com filtro): " + e.getMessage());
        }
    }
}
