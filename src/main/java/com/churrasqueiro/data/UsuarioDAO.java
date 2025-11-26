package com.churrasqueiro.data;

import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UsuarioDAO {

    public Optional<Usuario> buscarPorLogin(String login) throws DatabaseException {
        String sql = "SELECT id, login, senha, tipo, email FROM usuario WHERE login = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("tipo"),
                        rs.getString("email")
                    );
                    return Optional.of(usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fazer busca de usuário por login: " + e.getMessage());
            throw new DatabaseException("Falha ao consultar usuário no banco de dados.");
        }
        return Optional.empty();
    }
    
    public Usuario cadastrarUsuario(Usuario usuario) throws DatabaseException {
    	String sql = "INSERT INTO usuario (login, senha, tipo, email) VALUES (?, ?, ?, ?)";
		
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenhaHash());
			ps.setString(3, usuario.getTipo());
			ps.setString(4, usuario.getEmail());
			
			int linhasAfetadas = ps.executeUpdate();
			
			if (linhasAfetadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setId(rs.getInt(1));
                    }
                }
                return usuario;
            }
		} catch (SQLException e) {
			System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
			throw new DatabaseException("Falha ao cadastrar usuário no banco de dados.");
		}
		throw new DatabaseException("Falha desconhecida ao inserir usuário.");
    }
    
    //TODO: CRIAÇÃO DO MÉTODO QUE PROCURA UM EMAIL NO BD
}