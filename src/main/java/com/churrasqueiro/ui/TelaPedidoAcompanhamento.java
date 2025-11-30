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

public class TelaPedidoAcompanhamento extends JFrame {

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
					TelaPedidoAcompanhamento frame = new TelaPedidoAcompanhamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaPedidoAcompanhamento() {
		
		 Color corPaletaVermelho = new Color(179,13,36);
	     Color corPaletaBege = new Color(227,202,187);
	     Color corPaletaVermelhoInteracao = new Color(200,50,50);
	     Color corPaletaVermelhoPressionado = new Color(150,0,0);
	     Color corPaletaPreto = new Color(0,0,0);
	     Color corPaletaPretoInteração = new Color(35,35,35);
	     Color corPaletaCinza = new Color(140,127,127);
	     Color corPaletaBegeInteracao = new Color(245,225,210);
	     Color corPaletaBegePressionado = new Color(200,175,160);
		
		setTitle("Acompanhamentos - Churrasqueiro");
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
		
		java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
		
		JLabel lblAcompanhamentos = new JLabel("Acompanhamentos");
		lblAcompanhamentos.setForeground(Color.BLACK);
		lblAcompanhamentos.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblAcompanhamentos.setBounds(545, 12, 198, 26);
		panelBranco.add(lblAcompanhamentos);
		
		final EstilizacaoRedonda.PainelRedondo panelHamburguer = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaVermelho,null);
		panelHamburguer.setFocusable(true);
		panelHamburguer.requestFocusInWindow();
		panelHamburguer.setBounds(92, 59, 335, 196);
		panelBranco.add(panelHamburguer);
		panelHamburguer.setLayout(null);
		
		JLabel labelBatataFrango = new JLabel("");
		labelBatataFrango.setBounds(26, 12, 83, 82);
		panelHamburguer.add(labelBatataFrango);
		labelBatataFrango.setIcon(new ImageIcon(TelaItens.class.getResource("/assets/imagens/BATATA-FRANGO-BACON.jpg"))); 
        
        JLabel lblBatataFrango = new JLabel("Batata com Frango");
        lblBatataFrango.setForeground(Color.BLACK);
        lblBatataFrango.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblBatataFrango.setBounds(121, 12, 189, 26);
        panelHamburguer.add(lblBatataFrango);
        
        JLabel lblBatataFrangoValor = new JLabel("R$ 9,00");
        lblBatataFrangoValor.setForeground(Color.BLACK);
        lblBatataFrangoValor.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblBatataFrangoValor.setBounds(26, 158, 90, 26);
        panelHamburguer.add(lblBatataFrangoValor);
        java.net.URL urlBatataFrango = getClass().getResource("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens");
        
        this.botaoAdicionar = new EstilizacaoRedonda.BotaoRedondo("Adicionar",corPaletaBege,corPaletaBegeInteracao,corPaletaBegePressionado,35);
        botaoAdicionar.setFont(new Font("Calibri", Font.PLAIN, 22));
        botaoAdicionar.setForeground(new Color(0,0,0));
        botaoAdicionar.setBackground(new Color(227,202,187));
        botaoAdicionar.setBounds(165, 162, 125, 24);
        panelHamburguer.add(botaoAdicionar);
        
        JTextArea textBataFrango = new JTextArea();
        textBataFrango.setForeground(new Color(227,202,187));
        textBataFrango.setBackground(new Color(179,13,36));
        textBataFrango.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textBataFrango.setColumns(2);
        textBataFrango.setText("Uma porção de batata frita\ncom frengo desfiado que é\numa tentação!");
        textBataFrango.setBounds(119, 50, 176, 101);
        panelHamburguer.add(textBataFrango);
		
		final EstilizacaoRedonda.BotaoRedondo botaoAvancar = new EstilizacaoRedonda.BotaoRedondo("Avançar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoAvancar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoAvancar.setForeground(corPaletaBege);
		botaoAvancar.setBackground(new Color(0, 0, 0));
		botaoAvancar.setBounds(1052, 530, 182, 38);
        panelBranco.add(botaoAvancar);
        botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaPedidoConfirmar telaPedidoConfirmar = new TelaPedidoConfirmar();
				telaPedidoConfirmar.setVisible(true);
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
				TelaPedidoBebida telaPedidoBebida = new TelaPedidoBebida();
				telaPedidoBebida.setVisible(true);
			}
		});
        
        JLabel novoPedidoLabel = new JLabel("Novo Pedido");
        novoPedidoLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        novoPedidoLabel.setForeground(corPaletaBege);
        novoPedidoLabel.setBounds(485, 12, 261, 52);
        panelVermelho.add(novoPedidoLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/assets/imagens/iconeJanelaPequena.png")));     		
        logoLabel.setBounds(30, 0, 92, 82);
        panelVermelho.add(logoLabel);
	}
}