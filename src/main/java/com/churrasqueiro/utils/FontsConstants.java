package com.churrasqueiro.utils;

import java.awt.Font;

public class FontsConstants {
    public static Font MONTSERRAT_LIGHT_10;
    public static Font MONTSERRAT_REGULAR_13;
    public static Font MONTSERRAT_REGULAR_15;
    public static Font MONTSERRAT_BOLD_10;
    public static Font MONTSERRAT_BOLD_11;
    public static Font MONTSERRAT_BOLD_13;
    public static Font MONTSERRAT_BOLD_15;
    public static Font MONTSERRAT_BOLD_17;
    public static Font MONTSERRAT_BOLD_18;
    public static Font MONTSERRAT_BOLD_19;
    public static Font MONTSERRAT_BOLD_20;
    public static Font MONTSERRAT_BOLD_22;
    public static Font MONTSERRAT_BOLD_25;
    public static Font MONTSERRAT_BOLD_30;
    public static Font MONTSERRAT_BOLD_40;
    public static Font MONTSERRAT_BOLD_50;
    public static Font MONTSERRAT_BOLD_60;
    public static Font MONTSERRAT_EXTRABOLD_40;
    public static Font MONTSERRAT_EXTRABOLD_50;

    static {
        FontManager fontManager = FontManager.getInstance();

        MONTSERRAT_LIGHT_10 = fontManager.getMontserratLight(10);

        MONTSERRAT_REGULAR_13 = fontManager.getMontserratRegular(13);
        MONTSERRAT_REGULAR_15 = fontManager.getMontserratRegular(15);

        MONTSERRAT_BOLD_10 = fontManager.getMontserratBold(10);
        MONTSERRAT_BOLD_11 = fontManager.getMontserratBold(11);
        MONTSERRAT_BOLD_13 = fontManager.getMontserratBold(13);
        MONTSERRAT_BOLD_15 = fontManager.getMontserratBold(15);
        MONTSERRAT_BOLD_17 = fontManager.getMontserratBold(17);
        MONTSERRAT_BOLD_18 = fontManager.getMontserratBold(18);
        MONTSERRAT_BOLD_19 = fontManager.getMontserratBold(19);
        MONTSERRAT_BOLD_20 = fontManager.getMontserratBold(20);
        MONTSERRAT_BOLD_22 = fontManager.getMontserratBold(22);
        MONTSERRAT_BOLD_25 = fontManager.getMontserratBold(25);
        MONTSERRAT_BOLD_30 = fontManager.getMontserratBold(30);
        MONTSERRAT_BOLD_40 = fontManager.getMontserratBold(40);
        MONTSERRAT_BOLD_50 = fontManager.getMontserratBold(50);
        MONTSERRAT_BOLD_60 = fontManager.getMontserratBold(60);

        MONTSERRAT_EXTRABOLD_40 = fontManager.getMontserratExtraBold(40);
        MONTSERRAT_EXTRABOLD_50 = fontManager.getMontserratExtraBold(50);
    }

    public static void inicioalizarFontes() {

    }
}
