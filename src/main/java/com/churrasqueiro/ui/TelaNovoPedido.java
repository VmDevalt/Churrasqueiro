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

public class TelaNovoPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelVermelho;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;
	private JComboBox<String> cBoxMesas;
	private JComboBox<String> cBoxFormaPagamento;
	private JLabel lblMesas;
	private JLabel lblNome;
	private EstilizacaoRedonda.CaixaTextoRedonda campoNome;
	private EstilizacaoRedonda.CaixaTextoRedonda campoAcrescimo;
	private EstilizacaoRedonda.CaixaTextoRedonda campoDesconto;
	private EstilizacaoRedonda.CaixaTextoRedonda campoObservacoes;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovoPedido frame = new TelaNovoPedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaNovoPedido() {
		
		 Color corPaletaVermelho = new Color(179,13,36);
	     Color corPaletaBege = new Color(227,202,187);
	     Color corPaletaVermelhoInteracao = new Color(200,50,50);
	     Color corPaletaVermelhoPressionado = new Color(150,0,0);
	     Color corPaletaPreto = new Color(0,0,0);
	     Color corPaletaPretoInteração = new Color(35,35,35);
	     Color corPaletaCinza = new Color(140,127,127);
	     Color corPaletaBegeInteracao = new Color(245,225,210);
	     Color corPaletaBegePressionado = new Color(200,175,160);
		
		setTitle("Novo Pedido - Churrasqueiro");
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
		
		JLabel lblinicioPedido = new JLabel("Início do Pedido");
		lblinicioPedido.setForeground(Color.BLACK);
		lblinicioPedido.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblinicioPedido.setBounds(558, 12, 168, 26);
		panelBranco.add(lblinicioPedido);
		
		java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
		
		String[] mesas = {"01", "02", "03"};
		this.cBoxMesas = new JComboBox<>(mesas);
		cBoxMesas.setBorder(new LineBorder(new Color(179, 13, 36), 1));
		cBoxMesas.setForeground(corPaletaPreto);
		cBoxMesas.setBackground(corPaletaBege);
		cBoxMesas.setFont(new Font("SansSerif", Font.PLAIN, 17));
		cBoxMesas.setMaximumRowCount(2);
		cBoxMesas.setBounds(500, 99, 56, 23);
		panelBranco.add(cBoxMesas);
		
		String[] FormaPagamento = {"Dinheiro", "Cartão de credito", "Cartão de débito", "Pix"};
		this.cBoxFormaPagamento = new JComboBox<>(FormaPagamento);
		cBoxFormaPagamento.setBorder(new LineBorder(new Color(179, 13, 36), 1));
		cBoxFormaPagamento.setForeground(corPaletaPreto);
		cBoxFormaPagamento.setBackground(corPaletaBege);
		cBoxFormaPagamento.setFont(new Font("SansSerif", Font.PLAIN, 17));
		cBoxFormaPagamento.setMaximumRowCount(2);
		cBoxFormaPagamento.setBounds(643, 340, 168, 23);
		panelBranco.add(cBoxFormaPagamento);
		
		lblMesas = new JLabel("Mesas:");
		lblMesas.setForeground(Color.BLACK);
		lblMesas.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblMesas.setBounds(402, 96, 80, 26);
		panelBranco.add(lblMesas);
		
		lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.BLACK);
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblNome.setBounds(626, 99, 80, 26);
		panelBranco.add(lblNome);
		
		this.campoNome = new EstilizacaoRedonda.CaixaTextoRedonda("Digite o nome",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoNome.setBounds(699, 93, 360, 38);
		campoNome.setColumns(10);
		panelBranco.add(campoNome);
		
		JLabel lblAcrescimo = new JLabel("Acréscimo:");
		lblAcrescimo.setForeground(Color.BLACK);
		lblAcrescimo.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblAcrescimo.setBounds(402, 200, 121, 26);
		panelBranco.add(lblAcrescimo);
		
		this.campoAcrescimo = new EstilizacaoRedonda.CaixaTextoRedonda("Digite o Acréscimo",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoAcrescimo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoAcrescimo.setBounds(520, 207, 360, 38);
		campoAcrescimo.setColumns(10);
		panelBranco.add(campoAcrescimo);
		
		JLabel lblDesconto = new JLabel("Desconto:");
		lblDesconto.setForeground(Color.BLACK);
		lblDesconto.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblDesconto.setBounds(402, 268, 121, 26);
		panelBranco.add(lblDesconto);
		
		this.campoDesconto = new EstilizacaoRedonda.CaixaTextoRedonda("Digite o Desconto",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoDesconto.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoDesconto.setBounds(520, 275, 360, 38);
		campoDesconto.setColumns(10);
		panelBranco.add(campoDesconto);
		
		JLabel lblFormaPagamento = new JLabel("Formas de Pagamento:");
		lblFormaPagamento.setForeground(Color.BLACK);
		lblFormaPagamento.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblFormaPagamento.setBounds(402, 337, 255, 26);
		panelBranco.add(lblFormaPagamento);
		
		JLabel lblObservacoes = new JLabel("Obserações:");
		lblObservacoes.setForeground(Color.BLACK);
		lblObservacoes.setFont(new Font("Dialog", Font.PLAIN, 22));
		lblObservacoes.setBounds(402, 411, 154, 26);
		panelBranco.add(lblObservacoes);
		
		this.campoObservacoes = new EstilizacaoRedonda.CaixaTextoRedonda("Digite as Observações",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoObservacoes.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoObservacoes.setBounds(535, 418, 360, 38);
		campoObservacoes.setColumns(10);
		panelBranco.add(campoObservacoes);
		
		final EstilizacaoRedonda.BotaoRedondo botaoAvancar = new EstilizacaoRedonda.BotaoRedondo("Avançar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoAvancar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoAvancar.setForeground(corPaletaBege);
		botaoAvancar.setBackground(new Color(0, 0, 0));
		botaoAvancar.setBounds(1052, 530, 182, 38);
        panelBranco.add(botaoAvancar);
        botaoAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaPedidoHamburguer telaPedidoHamburguer = new TelaPedidoHamburguer();
				telaPedidoHamburguer.setVisible(true);
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
				TelaPedidos telaPedidos = new TelaPedidos();
				telaPedidos.setVisible(true);
			}
		});
        
        JLabel relatoriosLabel = new JLabel("Novo Pedido");
        relatoriosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        relatoriosLabel.setForeground(corPaletaBege);
        relatoriosLabel.setBounds(485, 12, 261, 52);
        panelVermelho.add(relatoriosLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/assets/imagens/iconeJanelaPequena.png")));     		
        logoLabel.setBounds(30, 0, 92, 82);
        panelVermelho.add(logoLabel);
        
	}
}