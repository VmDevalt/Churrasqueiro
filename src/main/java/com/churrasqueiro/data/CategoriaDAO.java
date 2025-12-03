package com.churrasqueiro.data;

import com.churrasqueiro.entities.Categoria;
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

public class CategoriaDAO {

    public Optional<Categoria> buscarPorId(int id) throws DatabaseException {
        String sql = "SELECT id, nome, descricao FROM categorias_cardapio WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                    );
                    return Optional.of(categoria);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria por ID: " + e.getMessage());
            throw new DatabaseException("Falha ao consultar categoria no banco de dados.");
        }
        return Optional.empty();
    }
    
    public List<Categoria> listarTodos() throws DatabaseException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nome, descricao FROM categorias_cardapio ORDER BY nome"; 

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao")
                );
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
            throw new DatabaseException("Falha ao listar categorias do cardápio.");
        }
        return categorias;
    }

    public Categoria inserir(Categoria categoria) throws DatabaseException {
        String sql = "INSERT INTO categorias_cardapio (nome, descricao) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescricao());

            int linhasAfetadas = ps.executeUpdate();
            
            if (linhasAfetadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        categoria.setId(rs.getInt(1));
                    }
                }
                return categoria;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir categoria: " + e.getMessage());
            throw new DatabaseException("Falha ao inserir categoria no cardápio.");
        }
        
        throw new DatabaseException("Falha desconhecida ao inserir categoria.");
    }
    
    public Optional<Categoria> buscarPorNome(String nome) throws DatabaseException {
        String sql = "SELECT id, nome, descricao FROM categorias_cardapio WHERE nome = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                    );
                    return Optional.of(categoria);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria por nome: " + e.getMessage());
            throw new DatabaseException("Falha ao consultar categoria por nome no banco de dados.");
        }
        return Optional.empty();
    }
}