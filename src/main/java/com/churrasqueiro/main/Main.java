package com.churrasqueiro.main;

import javax.swing.SwingUtilities;
import com.churrasqueiro.ui.TelaLogin;

public class Main {
    public static void main(String[] args) {
        H2Initializer.init();

        SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}
