package com.campay.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DatabaseInitializer {
    
    public static void initialize() {
        executeScriptFile("schema.sql");
        executeScriptFile("data.sql");
    }

    private static void executeScriptFile(String filename) {
        String sqlScript = readSqlFile(filename);
        if (sqlScript != null && !sqlScript.trim().isEmpty()) {
            executeSql(sqlScript, filename);
        }
    }
    
    private static String readSqlFile(String filename) {
        try (InputStream is = DatabaseInitializer.class.getClassLoader().getResourceAsStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            
            if (is == null) {
                System.err.println("Database: Arquivo " + filename + " não encontrado em src/main/resources.");
                return null;
            }

            return reader.lines().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            System.err.println("Database: Erro ao ler arquivo " + filename + ": " + e.getMessage());
            return null;
        }
    }

    private static void executeSql(String sqlScript, String filename) {
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sqlScript);
            System.out.println("Database: Script " + filename + " executado com sucesso.");
            
        } catch (SQLException e) {
            System.err.println("Database: Erro ao executar SQL de " + filename + ": " + e.getMessage());
        }
    }
}