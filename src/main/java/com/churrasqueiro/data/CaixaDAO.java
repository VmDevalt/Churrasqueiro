package com.churrasqueiro.data;

import com.churrasqueiro.entities.Caixa;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.DatabaseConnection;

import java.sql.*;
import java.util.Optional;

public class CaixaDAO {

    public Optional<Caixa> buscarCaixaAberto() throws DatabaseException {
        String sql = "SELECT * FROM Caixa WHERE status_caixa = TRUE ORDER BY data_abertura DESC LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Caixa caixa = mapearCaixa(rs);
                return Optional.of(caixa);
            }
            return Optional.empty();

        } catch (SQLException e) {
            System.err.println("Erro ao buscar caixa aberto: " + e.getMessage());
            throw new DatabaseException("Falha ao consultar caixa aberto no banco de dados.");
        }
    }

    public Caixa inserir(Caixa caixa) throws DatabaseException {
        String sql = 
            "INSERT INTO Caixa ("
            + "saldo_inicial, saldo_atual, meta_faturamento, "
            + "data_abertura, data_fechamento, status_caixa, "
            + "vendas_dia, vendas_meta"
            + ") VALUES (?, ?, ?, CURRENT_TIMESTAMP, NULL, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDouble(1, caixa.getSaldoInicial());
            ps.setDouble(2, caixa.getSaldoAtual());
            ps.setDouble(3, caixa.getMetaFaturamento());
            ps.setBoolean(4, caixa.isStatusCaixa());
            ps.setDouble(5, caixa.getVendasDia());
            ps.setDouble(6, caixa.getVendasMeta());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        caixa.setId(rs.getInt(1));
                    }
                }
                return caixa;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao abrir caixa: " + e.getMessage());
            throw new DatabaseException("Falha ao abrir caixa no banco de dados.");
        }

        throw new DatabaseException("Falha desconhecida ao abrir caixa.");
    }

    public void fecharCaixa(Caixa caixa) throws DatabaseException {

        String sql =
            "UPDATE Caixa SET "
            + "saldo_atual = ?, "
            + "vendas_dia = ?, "
            + "vendas_meta = ?, "
            + "data_fechamento = CURRENT_TIMESTAMP, "
            + "status_caixa = FALSE "
            + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, caixa.getSaldoAtual());
            ps.setDouble(2, caixa.getVendasDia());
            ps.setDouble(3, caixa.getVendasMeta());
            ps.setInt(4, caixa.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Falha ao fechar caixa no banco.");
        }
    }

    private Caixa mapearCaixa(ResultSet rs) throws SQLException {
        Caixa caixa = new Caixa();
        caixa.setId(rs.getInt("id"));
        caixa.setSaldoInicial(rs.getDouble("saldo_inicial"));
        caixa.setSaldoAtual(rs.getDouble("saldo_atual"));
        caixa.setMetaFaturamento(rs.getDouble("meta_faturamento"));
        caixa.setDataAbertura(rs.getTimestamp("data_abertura"));
        caixa.setDataFechamento(rs.getTimestamp("data_fechamento"));
        caixa.setStatusCaixa(rs.getBoolean("status_caixa"));
        caixa.setVendasDia(rs.getDouble("vendas_dia"));
        caixa.setVendasMeta(rs.getDouble("vendas_meta"));
        return caixa;
    }
}
