package com.churrasqueiro.main;

import com.churrasqueiro.utils.DatabaseConnection;
import org.h2.tools.Console;
import org.h2.tools.RunScript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class AppInitializer {

    public static void main(String[] args) {

        System.out.println("--- Iniciando Sistema O Churrasqueiro ---");

        try (Connection conn = DatabaseConnection.getConnection()) {

            System.out.println("Banco de dados e schema inicializados com sucesso.");

            inicializarDados(conn);

        } catch (SQLException e) {
            System.err.println("ERRO FATAL: A aplicação não pode ser iniciada devido a um erro no banco de dados.");
            e.printStackTrace();
            return;
        }

        iniciarH2Console();
        System.out.println("Aplicação pronta para carregar a tela de login.");
    }

    private static void inicializarDados(Connection conn) {
        try {
            boolean tabelaVazia = isTabelaVazia(conn, "itens_cardapio");

            if (tabelaVazia) {
                System.out.println("Executando data.sql para popular dados iniciais...");

                InputStream in = AppInitializer.class.getResourceAsStream("/db/data.sql");
                if (in == null) {
                    System.err.println("data.sql não encontrado no classpath (/db/data.sql).");
                    return;
                }

                RunScript.execute(
                        conn,
                        new InputStreamReader(in, StandardCharsets.UTF_8)
                );

                System.out.println("data.sql executado com sucesso.");
            } else {
                System.out.println("Dados iniciais já existem. data.sql não será executado.");
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar data.sql: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean isTabelaVazia(Connection conn, String nomeTabela) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + nomeTabela;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                int qtd = rs.getInt(1);
                return qtd == 0;
            }
        }
        return true;
    }

    private static void iniciarH2Console() {
        Runnable consoleTask = () -> {
            System.out.println("\n[CONSOLE H2] Console Web sendo iniciado em uma Thread separada (http://localhost:8082)...");
            try {
                Console.main(new String[0]);
            } catch (Exception e) {
                System.err.println("[CONSOLE H2] Erro ao iniciar o Console Web.");
            }
        };

        Thread consoleThread = new Thread(consoleTask);
        consoleThread.setName("H2-Console-Thread");
        consoleThread.start();
    }
}
