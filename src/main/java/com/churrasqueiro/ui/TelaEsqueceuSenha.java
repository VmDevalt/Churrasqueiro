package com.churrasqueiro.ui;

import com.churrasqueiro.utils.FontManager;
import com.churrasqueiro.utils.FontsConstants;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaEsqueceuSenha extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panelVermelho;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private EstilizacaoRedonda.CaixaTextoRedonda campoEmail;
    private JButton botaoEnviarCodigo;

    public String getEmail() {
        return campoEmail.getText().trim();
    }

    public void enviarCodigo() {
        String email = getEmail();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite seu email.",
                    "Campo Vazio",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Código enviado para: " + email,
                "Código Enviado",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
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
        panelBranco.setBounds(40, 40, 1200, 640);
        panelVermelho.add(panelBranco);
        panelBranco.setLayout(null);
        
        JLabel labelTitulo = new JLabel("Esqueceu a senha?");
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setForeground(corPaletaPreto);
        labelTitulo.setFont(FontsConstants.MONTSERRAT_BOLD_50);
        labelTitulo.setBounds(386, 229, 508, 43);
        panelBranco.add(labelTitulo);
        
        JLabel labelInstrucoes = new JLabel("<html><div style='text-align: center;'>Digite seu email para troca a senha. Você irá receber um código<br>no seu email onde deverá colocar na página seguinte.</div><html>");
        labelInstrucoes.setHorizontalAlignment(SwingConstants.CENTER);
        labelInstrucoes.setForeground(corPaletaPreto);
        labelInstrucoes.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelInstrucoes.setBounds(410, 311, 460, 32);
        panelBranco.add(labelInstrucoes);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setForeground(corPaletaPreto);
        labelEmail.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        labelEmail.setBounds(610, 363, 59, 20);
        panelBranco.add(labelEmail);

        this.campoEmail = new EstilizacaoRedonda.CaixaTextoRedonda("Digite seu email...", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoEmail.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
        campoEmail.setToolTipText("Digite seu email");
        campoEmail.setBounds(344, 393, 592, 37);
        campoEmail.setColumns(10);
        panelBranco.add(campoEmail);

        this.botaoEnviarCodigo = new EstilizacaoRedonda.BotaoRedondo("Enviar código", corPaletaVermelho, corPaletaVermelhoInteracao, corPaletaVermelhoPressionado, 35);
        botaoEnviarCodigo.setForeground(Color.WHITE);
        botaoEnviarCodigo.setBackground(corPaletaVermelho);
        botaoEnviarCodigo.setBounds(509, 451, 261, 36);
        botaoEnviarCodigo.setFont(FontsConstants.MONTSERRAT_EXTRABOLD_50);
        panelBranco.add(botaoEnviarCodigo);
        botaoEnviarCodigo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarCodigo();
            }
        });

        JLabel labelLogo = new JLabel("");
        labelLogo.setIcon(new ImageIcon("src/main/resources/assets/imagens/logoPequena.png"));
        labelLogo.setBounds(1145, 587, 60, 58);
        panelBranco.add(labelLogo);
        
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setForeground(corPaletaBege);
        botaoVoltar.setBackground(corPaletaVermelho);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        botaoVoltar.setBounds(20, 20, 150, 25);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panelVermelho.add(botaoVoltar);
        
        
        
        
        
    }
}