package com.campay.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.tools.Server;

public class ConnectionFactory {

	private static final Properties PROPS = new Properties();
    private static final String DRIVER = "org.h2.Driver";
    private static Server webServer;

    static {
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
            
            if (input == null) {
                System.err.println("ERRO: Arquivo db.properties não encontrado em src/main/resources. Verifique o caminho.");
            } else {
                PROPS.load(input);
            }
        } catch (Exception e) {
            System.err.println("ERRO fatal ao carregar db.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(PROPS.getProperty("db.driver")); 
            
            return DriverManager.getConnection(
                PROPS.getProperty("db.url"), 
                PROPS.getProperty("db.user"), 
                PROPS.getProperty("db.password")
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado. Verifique a dependência do H2 no pom.xml.", e);
        }
    }
    
    public static void startH2Console() {
        if (webServer != null && webServer.isRunning(true)) {
            System.out.println("Console H2 já está rodando.");
            return;
        }
        
        try {
            webServer = Server.createWebServer("-webAllowOthers").start();
            
            String consoleUrl = webServer.getURL();
            System.out.println("Console H2 iniciado! URL: " + consoleUrl);
            
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(consoleUrl));
            }

            String jdbcUrl = PROPS.getProperty("db.url");
            System.out.println("Acesse o console e use esta URL JDBC: " + jdbcUrl);            
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o console H2: " + e.getMessage());
        }
    }
}