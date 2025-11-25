package com.churrasqueiro.ui;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    
    public MenuPrincipal() {
        setSize(LARGURA, ALTURA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Calibri", Font.BOLD, 18));
        
        tabbedPane.addTab("Pedidos", new JPanel());
        tabbedPane.addTab("Gestão", new JPanel());
        tabbedPane.addTab("Dashboard", new JPanel());

        add(tabbedPane, BorderLayout.NORTH);
        
        add(new JLabel("Área de Conteúdo Principal", SwingConstants.CENTER), BorderLayout.CENTER);
    }
    
    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}