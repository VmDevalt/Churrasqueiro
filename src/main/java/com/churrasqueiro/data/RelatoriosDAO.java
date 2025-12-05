package com.churrasqueiro.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.DatabaseConnection;

public class RelatoriosDAO {

    private String montarClausulaTempo(String periodo, String colunaData) {
        if ("HOJE".equalsIgnoreCase(periodo)) {
            return "CAST(" + colunaData + " AS DATE) = CURRENT_DATE";
        } else if ("ÚLTIMA SEMANA".equalsIgnoreCase(periodo)) {
            return colunaData + " >= DATEADD('DAY', -7, CURRENT_DATE)";
        } else if ("ÚLTIMO MÊS".equalsIgnoreCase(periodo)) {
            return colunaData + " >= DATEADD('DAY', -30, CURRENT_DATE)";
        } else if ("ÚLTIMO ANO".equalsIgnoreCase(periodo)) {
            return colunaData + " >= DATEADD('DAY', -365, CURRENT_DATE)";
        } else {
            return "";
        }
    }

    public double getTotalVendas(String periodo) throws DatabaseException {
        String whereTempo = montarClausulaTempo(periodo, "data_hora");

        String sql = "SELECT COALESCE(SUM(total), 0) AS total " +
                     "FROM pedidos";

        if (!whereTempo.isEmpty()) {
            sql += " WHERE " + whereTempo;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter total de vendas: " + e.getMessage());
            throw new DatabaseException("Erro ao obter total de vendas: " + e.getMessage());
        }

        return 0.0;
    }

    public String getItemMaisVendido(String periodo) throws DatabaseException {
        String whereTempo = montarClausulaTempo(periodo, "p.data_hora");

        String sql = "SELECT ic.nome AS nome_item, " +
                     "       COALESCE(SUM(ip.quantidade), 0) AS total_unidades " +
                     "FROM itens_pedido ip " +
                     "JOIN pedidos p ON p.id = ip.pedido_id " +
                     "JOIN itens_cardapio ic ON ic.id = ip.item_cardapio_id ";

        if (!whereTempo.isEmpty()) {
            sql += "WHERE " + whereTempo + " ";
        }

        sql += "GROUP BY ic.nome " +
               "ORDER BY total_unidades DESC " +
               "LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getString("nome_item");
            } else {
                return "N/A";
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter item mais vendido: " + e.getMessage());
            throw new DatabaseException("Erro ao obter item mais vendido: " + e.getMessage());
        }
    }

    public int getUnidadesMaisVendido(String periodo) throws DatabaseException {
        String whereTempo = montarClausulaTempo(periodo, "p.data_hora");

        String sql = "SELECT COALESCE(SUM(ip.quantidade), 0) AS total_unidades " +
                     "FROM itens_pedido ip " +
                     "JOIN pedidos p ON p.id = ip.pedido_id " +
                     "JOIN itens_cardapio ic ON ic.id = ip.item_cardapio_id ";

        if (!whereTempo.isEmpty()) {
            sql += "WHERE " + whereTempo + " ";
        }

        sql += "GROUP BY ic.nome " +
               "ORDER BY total_unidades DESC " +
               "LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total_unidades");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao obter unidades do item mais vendido: " + e.getMessage());
            throw new DatabaseException("Erro ao obter unidades do item mais vendido: " + e.getMessage());
        }
    }
}
