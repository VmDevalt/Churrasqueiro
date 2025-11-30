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

public class TelaPedidos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelVermelho;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;
	private JButton botaoNovoPedido;
	private JButton botaoEditPedidoAberto;
	private JButton botaoDeletPedidoAberto;
	private JButton botaoEditPedidoPreparo;
	private JButton botaoDeletPedidoPreparo;
	private JButton botaoEditPedidoPronto;
	private JButton botaoDeletPedidoPronto;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPedidos frame = new TelaPedidos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaPedidos() {
		
		 Color corPaletaVermelho = new Color(179,13,36);
	     Color corPaletaBege = new Color(227,202,187);
	     Color corPaletaVermelhoInteracao = new Color(200,50,50);
	     Color corPaletaVermelhoPressionado = new Color(150,0,0);
	     Color corPaletaPreto = new Color(0,0,0);
	     Color corPaletaPretoInteração = new Color(35,35,35);
	     Color corPaletaCinza = new Color(140,127,127);
	     Color corPaletaBegeInteracao = new Color(245,225,210);
	     Color corPaletaBegePressionado = new Color(200,175,160);
		
		setTitle("Pedidos - Churrasqueiro");
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
		
		JLabel gruposLabel = new JLabel("Grupos");
        gruposLabel.setBounds(539, 18, 208, 38);
        gruposLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        gruposLabel.setForeground(corPaletaBege);
        panelVermelho.add(gruposLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(29, -2, 92, 82);
        logoLabel.setIcon(new ImageIcon(TelaCriarGrupo.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        panelVermelho.add(logoLabel);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 102, 1266, 581);
        panel.setBackground(corPaletaBege);
        panelVermelho.add(panel);
        panel.setLayout(null);
		
		java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }

		
		this.botaoNovoPedido = new EstilizacaoRedonda.BotaoRedondo("Novo Pedido",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoNovoPedido.setBounds(519, 35, 268, 38);
		panelBranco.add(botaoNovoPedido);
		botaoNovoPedido.setForeground(new Color(227,202,187));
		botaoNovoPedido.setBackground(new Color(179, 13, 36));
		botaoNovoPedido.setFont(new Font("SansSerif", Font.PLAIN, 17));
		botaoNovoPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					TelaNovoPedido telaNovoPedido = new TelaNovoPedido();
					telaNovoPedido.setVisible(true);
				}
			});
		
		
		final EstilizacaoRedonda.PainelRedondo panelPedidoAberto = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaBege,null);
		panelPedidoAberto.setBorder(new LineBorder(new Color(179,13,36), 1, true));
		panelPedidoAberto.setFocusable(true);
		panelPedidoAberto.requestFocusInWindow();
		panelPedidoAberto.setBounds(137, 148, 279, 449);
		panelBranco.add(panelPedidoAberto);
		panelPedidoAberto.setLayout(null);
		
		JTextArea txtPedidoAberto = new JTextArea();
		txtPedidoAberto.setForeground(new Color(179, 13, 36));
		txtPedidoAberto.setBackground(new Color(227,202,187));
		txtPedidoAberto.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPedidoAberto.setColumns(2);
		txtPedidoAberto.setText("Mesa: 157\nGarçonete: Ingrid Vitória\nData e Hora: 17/11/2025 10:43:31\n\nTotal de itens: \n1x Gourmet (R$ 20,00)\nx Coca Cola 1L (R$ 11,00)\n\nDescontos: R$ 00,00\nAcréscimo: R$ 00,00\nTotal: R$ 31,00\nForma de pagamento: Cartão de Credito\nObservações: ......");
		txtPedidoAberto.setBounds(6, 12, 267, 259);
		panelPedidoAberto.add(txtPedidoAberto);
		
		this.botaoEditPedidoAberto = new EstilizacaoRedonda.BotaoRedondo("Editar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoEditPedidoAberto.setBounds(90, 270, 98, 24);
		panelPedidoAberto.add(botaoEditPedidoAberto);
		botaoEditPedidoAberto.setForeground(new Color(227,202,187));
		botaoEditPedidoAberto.setBackground(new Color(179, 13, 36));
		botaoEditPedidoAberto.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		this.botaoDeletPedidoAberto = new EstilizacaoRedonda.BotaoRedondo("Apagar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoDeletPedidoAberto.setBounds(90, 332, 98, 24);
		panelPedidoAberto.add(botaoDeletPedidoAberto);
		botaoDeletPedidoAberto.setForeground(new Color(227,202,187));
		botaoDeletPedidoAberto.setBackground(new Color(179, 13, 36));
		botaoDeletPedidoAberto.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		final EstilizacaoRedonda.PainelRedondo panelPedidoAndamento = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaBege,null);
		panelPedidoAndamento.setBorder(new LineBorder(new Color(179,13,36), 1, true));
		panelPedidoAndamento.setFocusable(true);
		panelPedidoAndamento.requestFocusInWindow();
		panelPedidoAndamento.setBounds(519, 148, 279, 449);
		panelBranco.add(panelPedidoAndamento);
		panelPedidoAndamento.setLayout(null);
		
		JTextArea txtPedidoPreparo = new JTextArea();
		txtPedidoPreparo.setText("Mesa: 157\nGarçonete: Ingrid Vitória\nData e Hora: 17/11/2025 10:43:31\n\nTotal de itens: \n1x Gourmet (R$ 20,00)\nx Coca Cola 1L (R$ 11,00)\n\nDescontos: R$ 00,00\nAcréscimo: R$ 00,00\nTotal: R$ 31,00\nForma de pagamento: Cartão de Credito\nObservações: ......");
		txtPedidoPreparo.setForeground(new Color(179, 13, 36));
		txtPedidoPreparo.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPedidoPreparo.setColumns(2);
		txtPedidoPreparo.setBackground(new Color(227, 202, 187));
		txtPedidoPreparo.setBounds(6, 12, 267, 230);
		panelPedidoAndamento.add(txtPedidoPreparo);
		
		this.botaoEditPedidoPreparo = new EstilizacaoRedonda.BotaoRedondo("Editar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoEditPedidoPreparo.setBounds(90, 270, 98, 24);
		panelPedidoAndamento.add(botaoEditPedidoPreparo);
		botaoEditPedidoPreparo.setForeground(new Color(227,202,187));
		botaoEditPedidoPreparo.setBackground(new Color(179, 13, 36));
		botaoEditPedidoPreparo.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		this.botaoDeletPedidoPreparo = new EstilizacaoRedonda.BotaoRedondo("Apagar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoDeletPedidoPreparo.setBounds(90, 332, 98, 24);
		panelPedidoAndamento.add(botaoDeletPedidoPreparo);
		botaoDeletPedidoPreparo.setForeground(new Color(227,202,187));
		botaoDeletPedidoPreparo.setBackground(new Color(179, 13, 36));
		botaoDeletPedidoPreparo.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		final EstilizacaoRedonda.PainelRedondo panelPedidoPronto = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaBege,null);
		panelPedidoPronto.setBorder(new LineBorder(new Color(179,13,36), 1, true));
		panelPedidoPronto.setFocusable(true);
		panelPedidoPronto.requestFocusInWindow();
		panelPedidoPronto.setBounds(913, 148, 279, 449);
		panelBranco.add(panelPedidoPronto);
		panelPedidoPronto.setLayout(null);
		
		JTextArea txtPedidoAberto_2 = new JTextArea();
		txtPedidoAberto_2.setText("Mesa: 157\nGarçonete: Ingrid Vitória\nData e Hora: 17/11/2025 10:43:31\n\nTotal de itens: \n1x Gourmet (R$ 20,00)\nx Coca Cola 1L (R$ 11,00)\n\nDescontos: R$ 00,00\nAcréscimo: R$ 00,00\nTotal: R$ 31,00\nForma de pagamento: Cartão de Credito\nObservações: ......");
		txtPedidoAberto_2.setForeground(new Color(179, 13, 36));
		txtPedidoAberto_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPedidoAberto_2.setColumns(2);
		txtPedidoAberto_2.setBackground(new Color(227, 202, 187));
		txtPedidoAberto_2.setBounds(6, 12, 267, 230);
		panelPedidoPronto.add(txtPedidoAberto_2);
		
		this.botaoEditPedidoPronto = new EstilizacaoRedonda.BotaoRedondo("Editar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoEditPedidoPronto.setBounds(90, 270, 98, 24);
		panelPedidoPronto.add(botaoEditPedidoPronto);
		botaoEditPedidoPronto.setForeground(new Color(227,202,187));
		botaoEditPedidoPronto.setBackground(new Color(179, 13, 36));
		botaoEditPedidoPronto.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		this.botaoDeletPedidoPronto = new EstilizacaoRedonda.BotaoRedondo("Apagar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoDeletPedidoPronto.setBounds(90, 332, 98, 24);
		panelPedidoPronto.add(botaoDeletPedidoPronto);
		botaoDeletPedidoPronto.setForeground(new Color(227,202,187));
		botaoDeletPedidoPronto.setBackground(new Color(179, 13, 36));
		botaoDeletPedidoPronto.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		JLabel lblPedidosAberto = new JLabel("Pedidos em aberto");
		lblPedidosAberto.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPedidosAberto.setForeground(corPaletaPreto);
		lblPedidosAberto.setBounds(204, 121, 144, 14);
		panelBranco.add(lblPedidosAberto);
		
		JLabel lblPedidosPreparo = new JLabel("Pedidos em preparo");
		lblPedidosPreparo.setForeground(Color.BLACK);
		lblPedidosPreparo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPedidosPreparo.setBounds(587, 122, 144, 14);
		panelBranco.add(lblPedidosPreparo);
		
		JLabel lblPedidosProntos = new JLabel("Pedidos prontos");
		lblPedidosProntos.setForeground(Color.BLACK);
		lblPedidosProntos.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblPedidosProntos.setBounds(994, 122, 126, 14);
		panelBranco.add(lblPedidosProntos);
        	
        final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoVoltar.setBounds(1127, 19, 104, 38);
		panelVermelho.add(botaoVoltar);
		botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoVoltar.setForeground(new Color(255, 255, 255));
		botaoVoltar.setBackground(new Color(0, 0, 0));
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPedidos.this.dispose();
				TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal();
				telaMenuPrincipal.setVisible(true);
			}
		});
        
        JLabel labelLogoBege = new JLabel("");
        labelLogoBege.setBounds(0, 0, 83, 82);
        panelVermelho.add(labelLogoBege);
        labelLogoBege.setIcon(new ImageIcon("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens/iconeJanelaPequena.png"));
        
        JLabel dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setForeground(new Color(227, 202, 187));
        dashboardLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        dashboardLabel.setBounds(807, 19, 208, 38);
        panelVermelho.add(dashboardLabel);
        
        JPanel panelSelecionado = new JPanel();
        panelSelecionado.setBounds(173, 0, 254, 77);
        panelVermelho.add(panelSelecionado);
        panelSelecionado.setBackground(corPaletaBege);
        panelSelecionado.setLayout(null);
        
        JLabel pedidosLabel = new JLabel("Pedidos");
        pedidosLabel.setForeground(corPaletaPreto);
        pedidosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        pedidosLabel.setBounds(53, 10, 191, 57);
        panelSelecionado.add(pedidosLabel);
        java.net.URL urlLogo = getClass().getResource("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens");
	}
}