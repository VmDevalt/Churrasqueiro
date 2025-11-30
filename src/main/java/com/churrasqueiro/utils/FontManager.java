package com.churrasqueiro.utils;

import javax.swing.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FontManager {
    private static FontManager instance;
    private Map<String, Font> fontCache;

    private FontManager() {
        fontCache = new HashMap<>();
    }

    public static FontManager getInstance() {
        if (instance == null) {
            instance = new FontManager();
        }
        return instance;
    }

	public Font carregarFonte (String caminhoFonte, int tipoFonte, float tamanhoFonte) {
		String cacheKey = caminhoFonte + "|"  + tipoFonte + "|" + tamanhoFonte;

		if (fontCache.containsKey(cacheKey)) {
            return fontCache.get(cacheKey);
        }
		
		try {
			Font fonte = Font.createFont(
					Font.TRUETYPE_FONT,
					getClass().getResourceAsStream(caminhoFonte)).deriveFont(tipoFonte, tamanhoFonte);

            fontCache.put(cacheKey, fonte);
            return fonte;

		} catch (FontFormatException | IOException e) {
            System.err.println("Erro ao carregar fonte: " + caminhoFonte);
            e.printStackTrace();
            return new Font("Arial", tipoFonte, (int)tamanhoFonte);
        }
	}

    public Font getMontserratBold (float tamanho) {
        return carregarFonte("/assets/fontes/Montserrat-Bold.ttf", Font.PLAIN, tamanho);
    }

    public Font getMontserratExtraBold (float tamanho) {
        return carregarFonte("/assets/fontes/Montserrat-ExtraBold.ttf", Font.PLAIN, tamanho);
    }

    public Font getMontserratLight (float tamanho) {
        return carregarFonte("/assets/fontes/Montserrat-Light.ttf", Font.PLAIN, tamanho);
    }

    public Font getMontserratRegular (float tamanho) {
        return carregarFonte("/assets/fontes/Montserrat-Regular.ttf", Font.PLAIN, tamanho);
    }

    public void testarFontes() {
        String[] caminhos = {
                "/assets/fontes/Montserrat-Bold.ttf",
                "/assets/fontes/Montserrat-ExtraBold.ttf",
                "/assets/fontes/Montserrat-Light.ttf",
                "/assets/fontes/Montserrat-Regular.ttf"
        };

        for (String caminho : caminhos) {
            try {
                if (getClass().getResourceAsStream(caminho) != null) {
                    System.out.println("Fonte encontrada: " + caminho);
                } else {
                    System.out.println("Fonte NÃO encontrada: " + caminho);
                }
            } catch (Exception e) {
                System.out.println("Erro ao testar: " + caminho);
            }
        }
    }
}
