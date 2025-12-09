package com.churrasqueiro.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.entities.PedidoResumo;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.DatabaseConnection;

public class PedidoDAO {

    public void inserirPedidoComItens(PedidoEmMontagem pedido, int garconId) throws DatabaseException {
        String sqlPedido =
                "INSERT INTO Pedidos " +
                "(mesa_id, garcon_id, data_hora, status, desconto, acrescimo, total, forma_pagamento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlItem =
                "INSERT INTO Itens_Pedido " +
                "(pedido_id, item_cardapio_id, quantidade, preco_unitario, total_item) " +
                "VALUES (?, ?, ?, ?, ?)";

        String sqlCaixaSelect =
                "SELECT id, saldo_atual, vendas_dia " +
                "FROM Caixa " +
                "WHERE status_caixa = TRUE " +
                "ORDER BY data_abertura DESC " +
                "LIMIT 1";

        String sqlCaixaUpdate =
                "UPDATE Caixa SET saldo_atual = ?, vendas_dia = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psPedido =
                         conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psItem =
                         conn.prepareStatement(sqlItem)) {

                int mesaId = Integer.parseInt(pedido.getNumeroMesa());

                psPedido.setInt(1, mesaId);
                psPedido.setInt(2, garconId);
                psPedido.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                psPedido.setString(4, "Em Preparo");
                psPedido.setDouble(5, pedido.getDesconto());
                psPedido.setDouble(6, pedido.getAcrescimo());
                psPedido.setDouble(7, pedido.getTotalFinal());
                psPedido.setString(8, pedido.getFormaPagamento());

                int linhas = psPedido.executeUpdate();
                if (linhas == 0) {
                    throw new SQLException("Falha ao inserir pedido.");
                }

                int pedidoId;
                try (ResultSet rs = psPedido.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedidoId = rs.getInt(1);
                    } else {
                        throw new SQLException("Falha ao obter ID do pedido.");
                    }
                }

                Map<Integer, Integer> quantidades = new HashMap<>();
                Map<Integer, Double> precosUnitarios = new HashMap<>();

                for (ItemCardapio item : pedido.getItens()) {
                    if (item == null) continue;
                    int itemId = item.getId();

                    quantidades.merge(itemId, 1, Integer::sum);
                    precosUnitarios.putIfAbsent(itemId, item.getPreco());
                }

                for (Map.Entry<Integer, Integer> entry : quantidades.entrySet()) {
                    int itemId = entry.getKey();
                    int qtd = entry.getValue();
                    double preco = precosUnitarios.get(itemId);
                    double totalItem = preco * qtd;

                    psItem.setInt(1, pedidoId);
                    psItem.setInt(2, itemId);
                    psItem.setInt(3, qtd);
                    psItem.setDouble(4, preco);
                    psItem.setDouble(5, totalItem);
                    psItem.addBatch();
                }

                psItem.executeBatch();

                try (PreparedStatement psCxSel = conn.prepareStatement(sqlCaixaSelect);
                     ResultSet rsCx = psCxSel.executeQuery()) {

                    if (rsCx.next()) {
                        int caixaId = rsCx.getInt("id");
                        double saldoAtual = rsCx.getDouble("saldo_atual");
                        double vendasDia = rsCx.getDouble("vendas_dia");

                        double valorVenda = pedido.getTotalFinal();

                        try (PreparedStatement psCxUpd = conn.prepareStatement(sqlCaixaUpdate)) {
                            psCxUpd.setDouble(1, saldoAtual + valorVenda);
                            psCxUpd.setDouble(2, vendasDia + valorVenda);
                            psCxUpd.setInt(3, caixaId);
                            psCxUpd.executeUpdate();
                        }
                    }
                }

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                throw new DatabaseException("Erro ao salvar pedido e itens no banco: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Erro de conexão ao salvar pedido: " + e.getMessage());
        }
    }

public List<PedidoResumo> listarPorStatus(String status) throws DatabaseException {
    List<PedidoResumo> pedidos = new ArrayList<>();

    String sqlPedidos =
            "SELECT p.id, m.numeroMesa, u.login AS garcon_login, p.data_hora, " +
            "       p.desconto, p.acrescimo, p.total, p.forma_pagamento " +
            "FROM Pedidos p " +
            "JOIN Mesa m ON p.mesa_id = m.id " +
            "JOIN usuario u ON p.garcon_id = u.id " +
            "WHERE p.status = ? " +
            "ORDER BY p.data_hora DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sqlPedidos)) {

        ps.setString(1, status);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PedidoResumo resumo = new PedidoResumo();
                resumo.setId(rs.getInt("id"));
                resumo.setNumeroMesa(rs.getInt("numeroMesa"));
                resumo.setGarconLogin(rs.getString("garcon_login"));

                Timestamp ts = rs.getTimestamp("data_hora");
                resumo.setDataHora(ts != null ? ts.toLocalDateTime() : null);

                resumo.setDesconto(rs.getDouble("desconto"));
                resumo.setAcrescimo(rs.getDouble("acrescimo"));
                resumo.setTotal(rs.getDouble("total"));
                resumo.setFormaPagamento(rs.getString("forma_pagamento"));
                resumo.setItensDescricao(new ArrayList<>());

                pedidos.add(resumo);
            }
        }

        if (pedidos.isEmpty()) {
            return pedidos;
        }

        StringBuilder in = new StringBuilder("?");
        for (int i = 1; i < pedidos.size(); i++) {
            in.append(",?");
        }

        String sqlItens =
                "SELECT ip.pedido_id, ic.nome AS item_nome, ip.preco_unitario, " +
                "       SUM(ip.quantidade) AS quantidade " +
                "FROM Itens_Pedido ip " +
                "JOIN Itens_Cardapio ic ON ip.item_cardapio_id = ic.id " +
                "WHERE ip.pedido_id IN (" + in + ") " +
                "GROUP BY ip.pedido_id, ic.nome, ip.preco_unitario " +
                "ORDER BY ic.nome";

        Map<Integer, PedidoResumo> mapaPedidos = new HashMap<>();
        for (PedidoResumo p : pedidos) {
            mapaPedidos.put(p.getId(), p);
        }

        try (PreparedStatement psItens = conn.prepareStatement(sqlItens)) {
            int idx = 1;
            for (PedidoResumo p : pedidos) {
                psItens.setInt(idx++, p.getId());
            }

            try (ResultSet rsItens = psItens.executeQuery()) {
                while (rsItens.next()) {
                    int pedidoId = rsItens.getInt("pedido_id");
                    PedidoResumo resumo = mapaPedidos.get(pedidoId);
                    if (resumo == null) continue;

                    int quantidade = rsItens.getInt("quantidade");
                    String nomeItem = rsItens.getString("item_nome");
                    double preco = rsItens.getDouble("preco_unitario");

                    String precoFormatado = String.format(Locale.US, "%.2f", preco).replace('.', ',');
                    String linha = quantidade + "x " + nomeItem + " (R$" + precoFormatado + ")";
                    resumo.getItensDescricao().add(linha);
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw new DatabaseException("Erro ao listar pedidos: " + e.getMessage());
    }

    return pedidos;
}

    public void atualizarStatus(int pedidoId, String novoStatus) throws DatabaseException {
    String sql = "UPDATE Pedidos SET status = ? WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, novoStatus);
        ps.setInt(2, pedidoId);
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        throw new DatabaseException("Erro ao atualizar status do pedido: " + e.getMessage());
    }
}
}
