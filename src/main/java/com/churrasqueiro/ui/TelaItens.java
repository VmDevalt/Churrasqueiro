package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaItens extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;
    private EstilizacaoRedonda.CaixaTextoRedonda campoPesquisa;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaItens frame = new TelaItens();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaItens() {
		
		Color corPaletaVermelho = new Color(179,13,36);
	    Color corPaletaBege = new Color(227,202,187);
	    Color corPaletaVermelhoInteracao = new Color(200,50,50);
	    Color corPaletaVermelhoPressionado = new Color(150,0,0);
	    Color corPaletaPreto = new Color(0,0,0);
	    Color corPaletaPretoInteração = new Color(35,35,35);
	    Color corPaletaCinza = new Color(140,127,127);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(corPaletaVermelho);
		setContentPane(contentPane);
		setTitle("Itens - Churrasqueiro");
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

		JPanel panel = new JPanel();
        panel.setBounds(0, 102, 1266, 581);
        panel.setBackground(corPaletaBege);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lupaLabel = new JLabel("");
        lupaLabel.setBounds(1164, 120, 36, 38);
        panel.add(lupaLabel);
        lupaLabel.setIcon(new ImageIcon(TelaItens.class.getResource("/assets/imagens/lupa.png")));     		
        
        final EstilizacaoRedonda.BotaoRedondo botaoCriarItem = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelho,35);
        botaoCriarItem.setText("Criar Item");
        botaoCriarItem.setBounds(80, 60, 151, 38);
        panel.add(botaoCriarItem);
		botaoCriarItem.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoCriarItem.setForeground(new Color(255, 255, 255));
		botaoCriarItem.setBackground(new Color(0, 0, 0));
		botaoCriarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaCriarItem telaCriarItem = new TelaCriarItem();
				telaCriarItem.setVisible(true);
			}
		});
        
        final EstilizacaoRedonda.BotaoRedondo botaoCriarGrupo = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
        botaoCriarGrupo.setText("Criar Grupo");
        botaoCriarGrupo.setBounds(256, 60, 151, 38);
        panel.add(botaoCriarGrupo);
        botaoCriarGrupo.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoCriarGrupo.setForeground(new Color(255, 255, 255));
        botaoCriarGrupo.setBackground(new Color(0, 0, 0));
        botaoCriarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaCriarGrupo telaCriarGrupo = new TelaCriarGrupo();
				telaCriarGrupo.setVisible(true);
			}
		});
        
        final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setForeground(new Color(255, 255, 255));
        botaoVoltar.setBackground(new Color(0, 0, 0));
        botaoVoltar.setBounds(1132, 34, 104, 38);
        contentPane.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaGestao telaGestao = new TelaGestao();
				telaGestao.setVisible(true);
				dispose();
			}
		});
        
        JLabel relatoriosLabel = new JLabel("Itens");
        relatoriosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        relatoriosLabel.setForeground(corPaletaBege);
        relatoriosLabel.setBounds(536, 34, 208, 38);
        contentPane.add(relatoriosLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaItens.class.getResource("/assets/imagens/iconeJanelaPequena.png")));     		
        logoLabel.setBounds(30, 10, 92, 82);
        contentPane.add(logoLabel);
        
        this.campoPesquisa = new EstilizacaoRedonda.CaixaTextoRedonda("Digite...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
        campoPesquisa.setFont(new Font("Calibri", Font.PLAIN, 14));
        campoPesquisa.setToolTipText("Digite seu texto");
        campoPesquisa.setBounds(80, 120, 1135, 38);
        

		panel.add(campoPesquisa);
		campoPesquisa.setColumns(10);
		
		
		final EstilizacaoRedonda.PainelRedondo panelProduto2 = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaVermelho,null);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
		panelProduto2.setBounds(80, 201, 390, 195);
		panel.add(panelProduto2);
		panelProduto2.setLayout(null);
		
		JLabel burguerLabel = new JLabel("");
		burguerLabel.setBounds(37, 39, 109, 112);
		panelProduto2.add(burguerLabel);
		burguerLabel.setIcon(new ImageIcon(TelaItens.class.getResource("/assets/imagens/burguer.jpg")));     		
		
		JLabel nomeBurguerLabel = new JLabel("4 Queijos");
		nomeBurguerLabel.setBounds(172, 39, 208, 38);
		nomeBurguerLabel.setForeground(new Color(227, 202, 187));
		nomeBurguerLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		panelProduto2.add(nomeBurguerLabel);
		
		JLabel categoriaLabel = new JLabel("Categoria: Hamburguer");
		categoriaLabel.setBounds(172, 87, 208, 27);
		categoriaLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

		panelProduto2.add(categoriaLabel);
		
		JLabel precoLabel = new JLabel("R$18,50");
		precoLabel.setBounds(172, 113, 208, 38);
		precoLabel.setForeground(new Color(227, 202, 187));
		precoLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
		panelProduto2.add(precoLabel);
		
		JButton botaoEditar = new JButton("Editar");
		botaoEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botaoEditar.setBounds(27, 161, 119, 24);
		botaoEditar.setOpaque(false);
		botaoEditar.setContentAreaFilled(false);
		panelProduto2.add(botaoEditar);
		botaoEditar.setBorderPainted(false);
		botaoEditar.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		botaoEditar.setForeground(corPaletaBege);

	}
}
