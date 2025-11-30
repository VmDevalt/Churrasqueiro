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

public class TelaPedidoHamburguer extends JFrame {

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
					TelaPedidoHamburguer frame = new TelaPedidoHamburguer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaPedidoHamburguer() {
		
		 Color corPaletaVermelho = new Color(179,13,36);
	     Color corPaletaBege = new Color(227,202,187);
	     Color corPaletaVermelhoInteracao = new Color(200,50,50);
	     Color corPaletaVermelhoPressionado = new Color(150,0,0);
	     Color corPaletaPreto = new Color(0,0,0);
	     Color corPaletaPretoInteração = new Color(35,35,35);
	     Color corPaletaCinza = new Color(140,127,127);
	     Color corPaletaBegeInteracao = new Color(245,225,210);
	     Color corPaletaBegePressionado = new Color(200,175,160);
		
		setTitle("Hamburgueres - Churrasqueiro");
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
		
		java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
        
		JPanel panelBranco = new JPanel();
		panelBranco.setBackground(new Color(227,202,187));
		panelBranco.setBounds(0, 74, 1280, 609);
		panelVermelho.add(panelBranco);
		panelBranco.setLayout(null);
		
		JLabel lblHamburguer = new JLabel("Hamburgueres");
		lblHamburguer.setForeground(Color.BLACK);
		lblHamburguer.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblHamburguer.setBounds(558, 12, 168, 26);
		panelBranco.add(lblHamburguer);
		
		final EstilizacaoRedonda.PainelRedondo panelHamburguer = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaVermelho,null);
		panelHamburguer.setFocusable(true);
		panelHamburguer.requestFocusInWindow();
		panelHamburguer.setBounds(92, 59, 335, 196);
		panelBranco.add(panelHamburguer);
		panelHamburguer.setLayout(null);
		
		JLabel labelHamburguerCheedar = new JLabel("");
		labelHamburguerCheedar.setBounds(26, 12, 83, 82);
		panelHamburguer.add(labelHamburguerCheedar);
        java.net.URL urlHamburguerCheedar = getClass().getResource("/assets/imagens/DUPLO-CHEEDAR.jpg");
		labelHamburguerCheedar.setIcon(new ImageIcon(urlHamburguerCheedar));
        
        JLabel lblHamburguerCheedar = new JLabel("Duplo Cheedar");
        lblHamburguerCheedar.setForeground(Color.BLACK);
        lblHamburguerCheedar.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblHamburguerCheedar.setBounds(134, 12, 168, 26);
        panelHamburguer.add(lblHamburguerCheedar);
        
        JLabel lblCheedarValor = new JLabel("R$ 15,00");
        lblCheedarValor.setForeground(Color.BLACK);
        lblCheedarValor.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCheedarValor.setBounds(26, 158, 90, 26);
        panelHamburguer.add(lblCheedarValor);
        
        this.botaoAdicionar = new EstilizacaoRedonda.BotaoRedondo("Adicionar",corPaletaBege,corPaletaBegeInteracao,corPaletaBegePressionado,35);
        botaoAdicionar.setFont(new Font("Calibri", Font.PLAIN, 22));
        botaoAdicionar.setForeground(new Color(0,0,0));
        botaoAdicionar.setBackground(new Color(227,202,187));
        botaoAdicionar.setBounds(165, 162, 125, 24);
        panelHamburguer.add(botaoAdicionar);
        
        JTextArea textHamburguerCheedar = new JTextArea();
        textHamburguerCheedar.setForeground(new Color(227,202,187));
        textHamburguerCheedar.setBackground(new Color(179,13,36));
        textHamburguerCheedar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textHamburguerCheedar.setColumns(2);
        textHamburguerCheedar.setText("Vem com cheedar duplo e é \nmuito bom!\n");
        textHamburguerCheedar.setBounds(127, 50, 168, 101);
        panelHamburguer.add(textHamburguerCheedar);
		
		final EstilizacaoRedonda.BotaoRedondo botaoAvancar = new EstilizacaoRedonda.BotaoRedondo("Avançar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoAvancar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoAvancar.setForeground(corPaletaBege);
		botaoAvancar.setBackground(new Color(0, 0, 0));
		botaoAvancar.setBounds(1052, 530, 182, 38);
        panelBranco.add(botaoAvancar);
        botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaPedidoBebida telaPedidoBebida = new TelaPedidoBebida();
				telaPedidoBebida.setVisible(true);
			}
		});
        
        final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoVoltar.setForeground(new Color(255, 255, 255));
		botaoVoltar.setBackground(new Color(0, 0, 0));
		botaoVoltar.setBounds(1131, 19, 104, 38);
        panelVermelho.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaNovoPedido telaNovoPedido = new TelaNovoPedido();
				telaNovoPedido.setVisible(true);
			}
		});
        
        JLabel novoPedidoLabel = new JLabel("Novo Pedido");
        novoPedidoLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        novoPedidoLabel.setForeground(corPaletaBege);
        novoPedidoLabel.setBounds(485, 12, 261, 52);
        panelVermelho.add(novoPedidoLabel);
        
        JLabel logoLabel = new JLabel("");
		 logoLabel.setIcon(new
		 ImageIcon(getClass().getResource("/assets/imagens/iconeJanelaPequena.png")));
		        logoLabel.setBounds(30, 0, 92, 82);
        panelVermelho.add(logoLabel);
        
	}
}