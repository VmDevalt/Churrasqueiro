package com.churrasqueiro.data;

import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ItemCardapioDAO {

    public Optional<ItemCardapio> buscarPorId(int id) throws DatabaseException {
        String sql = "SELECT id, nome, descricao, preco, categoria_id, foto_url, preco_comparacao FROM itens_cardapio WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ItemCardapio item = new ItemCardapio(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getInt("categoria_id"),
                        rs.getString("foto_url"),
                        rs.getDouble("preco_comparacao")
                    );
                    return Optional.of(item);
                }
            }
        } catch (SQLException e) {
             System.err.println("Erro ao buscar item por ID: " + e.getMessage());
             throw new DatabaseException("Falha ao consultar item do cardápio no banco de dados.");
        }
        return Optional.empty();
    }
    
    public Optional<ItemCardapio> buscarPorNome(String nome) throws DatabaseException {
        String sql = "SELECT id, nome, descricao, preco, categoria_id, foto_url, preco_comparacao FROM itens_cardapio WHERE nome = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nome);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ItemCardapio item = new ItemCardapio(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getInt("categoria_id"),
                        rs.getString("foto_url"),
                        rs.getDouble("preco_comparacao")
                    );
                    return Optional.of(item);
                }
            }
        } catch (SQLException e) {
             System.err.println("Erro ao buscar item por nome: " + e.getMessage());
             throw new DatabaseException("Falha ao consultar item por nome no banco de dados.");
        }
        return Optional.empty();
    }
    

    public List<ItemCardapio> listarTodos() throws DatabaseException {
        List<ItemCardapio> itens = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, preco, categoria_id, foto_url, preco_comparacao FROM itens_cardapio"; 
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ItemCardapio item = new ItemCardapio(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("preco"),
                    rs.getInt("categoria_id"),
                    rs.getString("foto_url"),
                    rs.getDouble("preco_comparacao")
                );
                itens.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar itens: " + e.getMessage());
            throw new DatabaseException("Falha ao listar itens do cardápio.");
        }
        return itens;
    }
    
    /**
     * Insere um novo item no cardápio (usado pelo ItemController).
     */
    public ItemCardapio inserir(ItemCardapio item) throws DatabaseException {
        String sql = "INSERT INTO itens_cardapio (nome, descricao, preco, categoria_id, foto_url, preco_comparacao) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, item.getNome());
            ps.setString(2, item.getDescricao());
            ps.setDouble(3, item.getPreco());
            ps.setInt(4, item.getCategoriaId());
            ps.setString(5, item.getFotoUrl());
            ps.setDouble(6, item.getPrecoComparacao());

            int linhasAfetadas = ps.executeUpdate();
            
            if (linhasAfetadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        item.setId(rs.getInt(1));
                    }
                }
                return item;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir item: " + e.getMessage());
            throw new DatabaseException("Falha ao inserir item no cardápio.");
        }
        
        throw new DatabaseException("Falha desconhecida ao inserir item.");
    }
}