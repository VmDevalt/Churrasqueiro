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

public class TelaGestao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelVermelho;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;
	private JButton botaoCriarConta;
	private JButton botaoRelatorio;
	private JButton botaoConfiguracoes;
	private JButton botaoItens;
	private JButton botaoFecharCaixa;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGestao frame = new TelaGestao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaGestao() {
		
		 Color corPaletaVermelho = new Color(179,13,36);
	     Color corPaletaBege = new Color(227,202,187);
	     Color corPaletaVermelhoInteracao = new Color(200,50,50);
	     Color corPaletaVermelhoPressionado = new Color(150,0,0);
	     Color corPaletaPreto = new Color(0,0,0);
	     Color corPaletaPretoInteração = new Color(35,35,35);
	     Color corPaletaCinza = new Color(140,127,127);
	     Color corPaletaBegeInteracao = new Color(245,225,210);
	     Color corPaletaBegePressionado = new Color(200,175,160);
		
		setTitle("Gestão - Churrasqueiro");
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
		
		this.botaoCriarConta = new EstilizacaoRedonda.BotaoRedondo("Criar Conta",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoCriarConta.setText("Criar Conta");
		botaoCriarConta.setBounds(370, 361, 209, 38);
		panelBranco.add(botaoCriarConta);
		botaoCriarConta.setForeground(new Color(227,202,187));
		botaoCriarConta.setBackground(new Color(179, 13, 36));
		botaoCriarConta.setFont(new Font("SansSerif", Font.PLAIN, 17));
		botaoCriarConta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					TelaCadastro telaCadastro = new TelaCadastro();
					telaCadastro.setVisible(true);
				}
			});
		
		JLabel lblCaixa = new JLabel("Caixa:");
		lblCaixa.setForeground(Color.RED);
		lblCaixa.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblCaixa.setBounds(394, 43, 89, 46);
		panelBranco.add(lblCaixa);
		
		JLabel lblCaixaSatatus = new JLabel("Aberto");
		lblCaixaSatatus.setForeground(Color.BLACK);
		lblCaixaSatatus.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblCaixaSatatus.setBounds(472, 44, 89, 46);
		panelBranco.add(lblCaixaSatatus);
		
		JLabel lblData = new JLabel("28/11/2025");
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Dialog", Font.PLAIN, 28));
		lblData.setBounds(629, 41, 176, 46);
		panelBranco.add(lblData);
		
		this.botaoRelatorio = new EstilizacaoRedonda.BotaoRedondo("Relatórios",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoRelatorio.setText("Relatórios");
		botaoRelatorio.setBounds(707, 370, 209, 38);
		panelBranco.add(botaoRelatorio);
		botaoRelatorio.setForeground(new Color(227,202,187));
		botaoRelatorio.setBackground(new Color(179, 13, 36));
		botaoRelatorio.setFont(new Font("SansSerif", Font.PLAIN, 17));
		botaoRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaRelatorios telaRelatorio = new TelaRelatorios();
				telaRelatorio.setVisible(true);
			}
		});
	
		this.botaoConfiguracoes = new EstilizacaoRedonda.BotaoRedondo("Configurações",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoConfiguracoes.setText("Configurações");
		botaoConfiguracoes.setBounds(370, 262, 209, 38);
		panelBranco.add(botaoConfiguracoes);
		botaoConfiguracoes.setForeground(new Color(227,202,187));
		botaoConfiguracoes.setBackground(new Color(179, 13, 36));
		botaoConfiguracoes.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		this.botaoItens = new EstilizacaoRedonda.BotaoRedondo("Itens",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoItens.setText("Itens");
		botaoItens.setBounds(707, 262, 209, 38);
		panelBranco.add(botaoItens);
		botaoItens.setForeground(new Color(227,202,187));
		botaoItens.setBackground(new Color(179, 13, 36));
		botaoItens.setFont(new Font("SansSerif", Font.PLAIN, 17));
		botaoItens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaItens telaItens = new TelaItens();
				telaItens.setVisible(true);
			}
		});
		
		this.botaoFecharCaixa = new EstilizacaoRedonda.BotaoRedondo("Fechar Caixa",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoFecharCaixa.setText("Fechar Caixa");
		botaoFecharCaixa.setBounds(382, 91, 164, 24);
		panelBranco.add(botaoFecharCaixa);
		botaoFecharCaixa.setForeground(new Color(227,202,187));
		botaoFecharCaixa.setBackground(new Color(179, 13, 36));
		botaoFecharCaixa.setFont(new Font("SansSerif", Font.PLAIN, 17));
        
        JLabel labelLogoBege = new JLabel("");
        labelLogoBege.setBounds(0, 0, 83, 82);
        panelVermelho.add(labelLogoBege);
        labelLogoBege.setIcon(new ImageIcon("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens/iconeJanelaPequena.png"));
        java.net.URL urlLogo = getClass().getResource("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens");
        
        final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoVoltar.setForeground(new Color(255, 255, 255));
		botaoVoltar.setBackground(new Color(0, 0, 0));
		botaoVoltar.setBounds(1115, 19, 120, 38);
        panelVermelho.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal();
				telaMenuPrincipal.setVisible(true);
			}
		});
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/assets/imagens/iconeJanelaPequena.png")));     		
        logoLabel.setBounds(30, 0, 92, 82);
        panelVermelho.add(logoLabel);
        
        JPanel panelSelecionado = new JPanel();
        panelSelecionado.setBounds(495, 0, 254, 77);
        panelVermelho.add(panelSelecionado);
        panelSelecionado.setBackground(corPaletaBege);
        panelSelecionado.setLayout(null);
        
        JLabel gestaoLabel = new JLabel("Gestão");
        gestaoLabel.setForeground(corPaletaPreto);
        gestaoLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        gestaoLabel.setBounds(53, 10, 201, 57);
        panelSelecionado.add(gestaoLabel);
        
        JLabel pedidosLabel = new JLabel("Pedidos");
        pedidosLabel.setBounds(214, 10, 248, 54);
        panelVermelho.add(pedidosLabel);
        pedidosLabel.setForeground(new Color(227, 202, 187));
        pedidosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        
        JLabel dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setForeground(new Color(227, 202, 187));
        dashboardLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        dashboardLabel.setBounds(864, 10, 241, 54);
        panelVermelho.add(dashboardLabel);
        
        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
	}
}