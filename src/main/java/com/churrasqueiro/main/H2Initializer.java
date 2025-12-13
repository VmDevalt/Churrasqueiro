package com.churrasqueiro.main;

import com.churrasqueiro.utils.DatabaseConnection;
import org.h2.tools.RunScript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class H2Initializer {

    public static void init() {

        try (Connection conn = DatabaseConnection.getConnection()) {

            inicializarDados(conn);

        } catch (SQLException e) {
            System.err.println("Erro ao inicializar o banco de dados.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void inicializarDados(Connection conn) {
        try {
            boolean tabelaVazia = isTabelaVazia(conn, "itens_cardapio");

            if (tabelaVazia) {
                InputStream in = H2Initializer.class.getResourceAsStream("/db/data.sql");

                if (in == null) {
                    throw new RuntimeException("data.sql não encontrado.");
                }

                RunScript.execute(
                        conn,
                        new InputStreamReader(in, StandardCharsets.UTF_8)
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar data.sql", e);
        }
    }

    private static boolean isTabelaVazia(Connection conn, String tabela) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tabela;

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            rs.next();
            return rs.getInt(1) == 0;
        }
    }
}
