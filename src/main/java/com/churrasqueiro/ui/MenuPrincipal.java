package com.churrasqueiro.ui;
import com.churrasqueiro.ui.EstilizacaoRedonda;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    Color corPaletaBege = new Color(227,202,187);
    Color corPaletaVermelho = new Color(179,13,36);
    Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaBranco = new Color(255,255,255);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteração = new Color(35,35,35);

	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setTitle("Menu Principal - Churrasqueiro");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(227,202,187));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
		
		final EstilizacaoRedonda.BotaoRedondo botaoPedidos = new EstilizacaoRedonda.BotaoRedondo("PEDIDOS",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoPedidos.setText("Pedidos");
		botaoPedidos.setForeground(corPaletaBege);
		botaoPedidos.setBounds(73, 269, 288, 335);
		botaoPedidos.setFont(new Font("SansSerif", Font.BOLD, 45));
        contentPane.add(botaoPedidos);
        
        final EstilizacaoRedonda.BotaoRedondo botaoGestao = new EstilizacaoRedonda.BotaoRedondo("GESTÃO",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
        botaoGestao.setFont(new Font("SansSerif", Font.BOLD, 45));
        botaoGestao.setText("Dashboard");
        botaoGestao.setForeground(corPaletaBege);
        botaoGestao.setBounds(885, 269, 288, 335);
		botaoPedidos.setFont(new Font("SansSerif", Font.BOLD, 45));
        contentPane.add(botaoGestao);
        
        final EstilizacaoRedonda.BotaoRedondo botaoDashboard = new EstilizacaoRedonda.BotaoRedondo("DASHBOARD",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
        botaoDashboard.setFont(new Font("SansSerif", Font.BOLD, 45));
        botaoDashboard.setText("Gestão");
        botaoDashboard.setForeground(corPaletaBege);
        botaoDashboard.setBounds(482, 269, 288, 335);
		botaoPedidos.setFont(new Font("SansSerif", Font.BOLD, 45));
        contentPane.add(botaoDashboard);
		
		final EstilizacaoRedonda.BotaoRedondo botaoSair = new EstilizacaoRedonda.BotaoRedondo("Sair",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoSair.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoSair.setForeground(corPaletaVermelho);
		botaoSair.setBackground(new Color(0, 0, 0));
		botaoSair.setBounds(1133, 39, 83, 38);
		botaoPedidos.setFont(new Font("SansSerif", Font.BOLD, 45));
        contentPane.add(botaoSair);
        
        JLabel labelEscolhaUmaOpcao = new JLabel("Escolha uma opção");
        labelEscolhaUmaOpcao.setFont(new Font("SansSerif", Font.BOLD, 40));
        labelEscolhaUmaOpcao.setBounds(437, 178, 523, 58);
        contentPane.add(labelEscolhaUmaOpcao);
        
        
        JLabel labelLogo = new JLabel("");
        labelLogo.setBounds(576, 22, 265, 165);
        contentPane.add(labelLogo);
        
        ImageIcon iconLogo = null;
        int larguraLogo = 80;
        int alturaLogo = 80;
        java.net.URL urlLogo = getClass().getResource("/assets/imagens/logo.png");
        if (urlLogo != null) {
            Image originalImage = Toolkit.getDefaultToolkit().getImage(urlLogo);
            Image resizedImage = originalImage.getScaledInstance(
                    larguraLogo,
                    alturaLogo,
                    Image.SCALE_SMOOTH
            );
            iconLogo = new ImageIcon(resizedImage);
            labelLogo.setIcon(iconLogo);
        }

	}
}
