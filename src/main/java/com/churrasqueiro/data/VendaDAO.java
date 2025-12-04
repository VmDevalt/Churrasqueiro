package com.churrasqueiro.data;

import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendaDAO {

    public double calcularTotalVendasDoDia(Date dia) throws DatabaseException {
        String sql =
            "SELECT COALESCE(SUM(total), 0) AS total " +
            "FROM Pedidos " +
            "WHERE CAST(data_hora AS DATE) = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, dia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao calcular total de vendas do dia: " + e.getMessage());
            throw new DatabaseException("Falha ao calcular total de vendas do dia.");
        }

        return 0.0;
    }
}
