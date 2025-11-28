package com.churrasqueiro.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import com.churrasqueiro.business.CadastroUsuarioController;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import javax.swing.JTextArea;

public class TelaPedidoBebida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelVermelho;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;
	private JButton botaoNovoPedido;
	private JButton botaoAdicionar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPedidoBebida frame = new TelaPedidoBebida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaPedidoBebida() {
		
		 Color corPaletaVermelho = new Color(179,13,36);
	     Color corPaletaBege = new Color(227,202,187);
	     Color corPaletaVermelhoInteracao = new Color(200,50,50);
	     Color corPaletaVermelhoPressionado = new Color(150,0,0);
	     Color corPaletaPreto = new Color(0,0,0);
	     Color corPaletaPretoInteração = new Color(35,35,35);
	     Color corPaletaCinza = new Color(140,127,127);
	     Color corPaletaBegeInteracao = new Color(245,225,210);
	     Color corPaletaBegePressionado = new Color(200,175,160);
		
		setTitle("Bebidas - Churrasqueiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setSize(LARGURA, ALTURA);
	    setResizable(false);
	    setLocationRelativeTo(null);
	        
	    panelVermelho = new JPanel();
        panelVermelho.setBackground(new Color(179, 13, 36));
		panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelVermelho);
		panelVermelho.setLayout(null);
		
		JPanel panelBranco = new JPanel();
		panelBranco.setBackground(new Color(227,202,187));
		panelBranco.setBounds(0, 74, 1280, 609);
		panelVermelho.add(panelBranco);
		panelBranco.setLayout(null);
		
		JLabel lblBebidas = new JLabel("Bebidas");
		lblBebidas.setForeground(Color.BLACK);
		lblBebidas.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblBebidas.setBounds(593, 12, 96, 26);
		panelBranco.add(lblBebidas);
		
		final EstilizacaoRedonda.PainelRedondo panelHamburguer = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaVermelho,null);
		panelHamburguer.setFocusable(true);
		panelHamburguer.requestFocusInWindow();
		panelHamburguer.setBounds(92, 59, 335, 196);
		panelBranco.add(panelHamburguer);
		panelHamburguer.setLayout(null);
		
		JLabel labelCocaCola2L = new JLabel("");
		labelCocaCola2L.setBounds(26, 12, 83, 82);
		panelHamburguer.add(labelCocaCola2L);
        labelCocaCola2L.setIcon(new ImageIcon("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens/coca-cola-2-litros.jpg"));
        
        JLabel lblCocaCola2L = new JLabel("Coca Cola 2L");
        lblCocaCola2L.setForeground(Color.BLACK);
        lblCocaCola2L.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCocaCola2L.setBounds(134, 12, 168, 26);
        panelHamburguer.add(lblCocaCola2L);
        
        JLabel lblCocaColaValor = new JLabel("R$ 12,00");
        lblCocaColaValor.setForeground(Color.BLACK);
        lblCocaColaValor.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCocaColaValor.setBounds(26, 158, 90, 26);
        panelHamburguer.add(lblCocaColaValor);
        java.net.URL urlCocaCola2L = getClass().getResource("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens");
        
        this.botaoAdicionar = new EstilizacaoRedonda.BotaoRedondo("Adicionar",corPaletaBege,corPaletaBegeInteracao,corPaletaBegePressionado,35);
        botaoAdicionar.setFont(new Font("Calibri", Font.PLAIN, 22));
        botaoAdicionar.setForeground(new Color(0,0,0));
        botaoAdicionar.setBackground(new Color(227,202,187));
        botaoAdicionar.setBounds(165, 162, 125, 24);
        panelHamburguer.add(botaoAdicionar);
        
        JTextArea textCocaCola = new JTextArea();
        textCocaCola.setForeground(new Color(227,202,187));
        textCocaCola.setBackground(new Color(179,13,36));
        textCocaCola.setFont(new Font("Calibri", Font.PLAIN, 14));
        textCocaCola.setColumns(2);
        textCocaCola.setText("Uma bebida de 2 litros que \ngelada é muito boa!");
        textCocaCola.setBounds(119, 50, 176, 101);
        panelHamburguer.add(textCocaCola);
		
		final EstilizacaoRedonda.BotaoRedondo botaoAvancar = new EstilizacaoRedonda.BotaoRedondo("Avançar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoAvancar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoAvancar.setForeground(corPaletaBege);
		botaoAvancar.setBackground(new Color(0, 0, 0));
		botaoAvancar.setBounds(1052, 530, 182, 38);
        panelBranco.add(botaoAvancar);
        botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaPedidoAcompanhamento telaPedidoAcompanhamento = new TelaPedidoAcompanhamento();
				telaPedidoAcompanhamento.setVisible(true);
			}
		});
        
        this.botaoNovoPedido = new EstilizacaoRedonda.BotaoRedondo("Novo Pedido",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
        botaoNovoPedido.setText("Novo Pedido");
        botaoNovoPedido.setBounds(519, -13, 233, 97);
        botaoNovoPedido.setForeground(new Color(227,202,187));
    	botaoNovoPedido.setBackground(new Color(179, 13, 36));
    	botaoNovoPedido.setFont(new Font("Calibri", Font.PLAIN, 22));
        panelVermelho.add(botaoNovoPedido);
        	
        final EstilizacaoRedonda.BotaoRedondo botaoSair = new EstilizacaoRedonda.BotaoRedondo("Sair",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoSair.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoSair.setForeground(corPaletaVermelho);
		botaoSair.setBackground(new Color(0, 0, 0));
		botaoSair.setBounds(1149, 24, 83, 38);
        panelVermelho.add(botaoSair);
        botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaPedidoHamburguer telaPedidoHamburguer = new TelaPedidoHamburguer();
				telaPedidoHamburguer.setVisible(true);
			}
		});
        
        JLabel labelLogoBege = new JLabel("");
        labelLogoBege.setBounds(0, 0, 83, 82);
        panelVermelho.add(labelLogoBege);
        labelLogoBege.setIcon(new ImageIcon("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens/iconeJanelaPequena.png"));
        java.net.URL urlLogo = getClass().getResource("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens/iconeJanelaPequena.png");
        
	}
}