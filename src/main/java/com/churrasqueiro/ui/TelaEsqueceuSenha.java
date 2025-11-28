package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaEsqueceuSenha extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panelVermelho;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaEsqueceuSenha frame = new TelaEsqueceuSenha();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public TelaEsqueceuSenha() {
    	
        Color corPaletaVermelho = new Color(179,13,36);
        Color corPaletaBege = new Color(227,202,187);
        Color corPaletaVermelhoInteracao = new Color(200,50,50);
        Color corPaletaVermelhoPressionado = new Color(150,0,0);
        Color corPaletaPreto = new Color(0,0,0);
        Color corPaletaCinza = new Color(140,127,127);

        setTitle("Esqueceu a Senha - Churrasqueiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, LARGURA, ALTURA);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);
        
        panelVermelho = new JPanel();
        panelVermelho.setBackground(new Color(179, 13, 36));
        panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelVermelho);
        panelVermelho.setLayout(null);
    }
}