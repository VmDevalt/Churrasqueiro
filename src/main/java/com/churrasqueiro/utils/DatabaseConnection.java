package com.churrasqueiro.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:h2:file:./database/churrasqueiro_db;INIT=RUNSCRIPT FROM 'classpath:db/schema.sql'";

    private static final String USER = ConfigUtil.getProperty("db.user");
    private static final String PASSWORD = ConfigUtil.getProperty("db.password");

    public static Connection getConnection() throws SQLException {
        if (USER == null || PASSWORD == null) {
            throw new SQLException("Configurações de usuário/senha do banco de dados não foram carregadas corretamente. Verifique o arquivo database.properties.");
        }
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}