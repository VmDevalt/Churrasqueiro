package com.churrasqueiro.data;

import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}