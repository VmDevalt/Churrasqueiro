package com.churrasqueiro.main;

import com.churrasqueiro.utils.DatabaseConnection;
import org.h2.tools.Console; 
import java.sql.SQLException;
import java.sql.Connection;

public class AppInitializer {
    
    public static void main(String[] args) {
        
        System.out.println("--- Iniciando Sistema O Churrasqueiro ---");
        
        try {
            try(Connection conn = DatabaseConnection.getConnection()){
                 System.out.println("Banco de dados e scripts (Schema/Data) inicializados com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("ERRO FATAL: A aplicação não pode ser iniciada devido a um erro no banco de dados.");
            e.printStackTrace();
            return; 
        }
        
        iniciarH2Console();
        System.out.println("Aplicação pronta para carregar a tela de login.");
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