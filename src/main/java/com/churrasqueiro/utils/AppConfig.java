package com.churrasqueiro.utils;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static Properties props = new Properties();

    static {
        try (InputStream is = AppConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (is == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado");
            }

            props.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar configurações", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
