package com.churrasqueiro.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE = "database.properties";

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.err.println("ERRO: Arquivo de propriedades '" + PROPERTIES_FILE + "' não encontrado no classpath.");
            } else {
                PROPERTIES.load(input);
            }
        } catch (IOException ex) {
            System.err.println("ERRO ao carregar o arquivo de propriedades: " + ex.getMessage());
        }
    }
    
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}