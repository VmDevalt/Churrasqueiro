package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
        
        final EstilizacaoRedonda.PainelRedondo panelBranco = new EstilizacaoRedonda.PainelRedondo(null, 60, 4, corPaletaBege, null);
        panelBranco.setFocusable(true);
        panelBranco.requestFocusInWindow();
        panelBranco.setBounds(90, 85, 1098, 505);
        panelVermelho.add(panelBranco);
        panelBranco.setLayout(null);
        
        JLabel labelTitulo = new JLabel("Esqueceu a senha?");
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setForeground(corPaletaPreto);
        labelTitulo.setFont(new Font("Calibre", Font.PLAIN, 17));
        labelTitulo.setBounds(349, 50, 400, 32);
        panelBranco.add(labelTitulo);
        
        JLabel labelInstrucoes = new JLabel("<html><div style='text-align: center;'>Digite seu email para troca a senha. Você irá receber um código<br>no seu email onde deverá colocar na página seguinte.</div><html>");
        labelInstrucoes.setHorizontalAlignment(SwingConstants.CENTER);
        labelInstrucoes.setForeground(corPaletaPreto);
        labelInstrucoes.setFont(new Font("Calibri", Font.PLAIN, 17));
        labelInstrucoes.setBounds(349, 100, 600, 60);
        panelBranco.add(labelInstrucoes);
        
        
        
        
        
        
        
        
        
        
    }
}