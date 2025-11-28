package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class TelaConfiguracoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConfiguracoes frame = new TelaConfiguracoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Color corPaletaBege = new Color(227,202,187);
	Color corPaletaVermelho = new Color(179,13,36);
	Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaBranco = new Color(255,255,255);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteração = new Color(35,35,35);
    Color corPaletaCinza = new Color(140,127,127);
	
	public TelaConfiguracoes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGURA, ALTURA);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setBackground(corPaletaVermelho);
		
		contentPane.setLayout(null);
        
        JLabel configuracoesLabel = new JLabel("Configurações");
        configuracoesLabel.setBounds(498, 21, 259, 58);
        configuracoesLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        configuracoesLabel.setForeground(corPaletaBege);
        contentPane.add(configuracoesLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(30, 10, 92, 82);
        logoLabel.setIcon(new ImageIcon(TelaCriarGrupo.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        contentPane.add(logoLabel);
		
		JPanel panel = new JPanel();
        panel.setBounds(0, 102, 1266, 581);
        panel.setBackground(corPaletaBege);
        contentPane.add(panel);
        panel.setLayout(null);
        
        final EstilizacaoRedonda.BotaoRedondo botaoSair = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoSair.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoSair.setForeground(corPaletaBege);
		botaoSair.setBackground(new Color(0, 0, 0));
		botaoSair.setBounds(1099, 38, 111, 38);
        contentPane.add(botaoSair);
        botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaGestao telaGestao = new TelaGestao();
				telaGestao.setVisible(true);
			}
		});
        
        JLabel contaLabel = new JLabel("Conta");
        contaLabel.setBounds(564, 26, 109, 76);
        contaLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        contaLabel.setForeground(corPaletaPreto);
        panel.add(contaLabel);
        
        JLabel nomeTituloLabel = new JLabel("Nome");
        nomeTituloLabel.setForeground(corPaletaVermelho);
        nomeTituloLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        nomeTituloLabel.setBounds(66, 163, 109, 44);
        panel.add(nomeTituloLabel);
        
        JLabel nomeTextoLabel = new JLabel("Ingrid Vitória");
        nomeTextoLabel.setForeground(corPaletaPreto);
        nomeTextoLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        nomeTextoLabel.setBounds(66, 199, 206, 35);
        panel.add(nomeTextoLabel);
        
        JLabel cpfTextoLabel = new JLabel("000.000.000-00");
        cpfTextoLabel.setForeground(Color.BLACK);
        cpfTextoLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        cpfTextoLabel.setBounds(66, 302, 206, 35);
        panel.add(cpfTextoLabel);
        
        JLabel cpfTituloLabel = new JLabel("CPF");
        cpfTituloLabel.setForeground(new Color(179, 13, 36));
        cpfTituloLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        cpfTituloLabel.setBounds(66, 267, 109, 44);
        panel.add(cpfTituloLabel);
        
        JLabel emailTituloLabel = new JLabel("E-mail");
        emailTituloLabel.setForeground(new Color(179, 13, 36));
        emailTituloLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        emailTituloLabel.setBounds(826, 147, 109, 44);
        panel.add(emailTituloLabel);
        
        JLabel lblIngridvitriachurrasqueirocom = new JLabel("ingridvitoria@churrasqueiro.com");
        lblIngridvitriachurrasqueirocom.setForeground(Color.BLACK);
        lblIngridvitriachurrasqueirocom.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblIngridvitriachurrasqueirocom.setBounds(826, 183, 378, 35);
        panel.add(lblIngridvitriachurrasqueirocom);
        
        JLabel cnpjTituloLabel = new JLabel("CNPJ");
        cnpjTituloLabel.setForeground(new Color(179, 13, 36));
        cnpjTituloLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        cnpjTituloLabel.setBounds(826, 251, 109, 44);
        panel.add(cnpjTituloLabel);
        
        JLabel cpfTextoLabel_1 = new JLabel("000.000.000/0001-00");
        cpfTextoLabel_1.setForeground(Color.BLACK);
        cpfTextoLabel_1.setFont(new Font("SansSerif", Font.BOLD, 22));
        cpfTextoLabel_1.setBounds(826, 286, 284, 35);
        panel.add(cpfTextoLabel_1);
        
        JLabel formasPagamentoLabel = new JLabel("Formas de Pagamento");
        formasPagamentoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formasPagamentoLabel.setForeground(new Color(179, 13, 36));
        formasPagamentoLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        formasPagamentoLabel.setBounds(448, 329, 350, 44);
        panel.add(formasPagamentoLabel);
        
        JLabel cpfTextoLabel_1_1 = new JLabel("Pix");
        cpfTextoLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        cpfTextoLabel_1_1.setForeground(Color.BLACK);
        cpfTextoLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 22));
        cpfTextoLabel_1_1.setBounds(484, 370, 284, 35);
        panel.add(cpfTextoLabel_1_1);
        
        JLabel cpfTextoLabel_1_1_1 = new JLabel("Dinheiro");
        cpfTextoLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        cpfTextoLabel_1_1_1.setForeground(Color.BLACK);
        cpfTextoLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 22));
        cpfTextoLabel_1_1_1.setBounds(484, 405, 284, 35);
        panel.add(cpfTextoLabel_1_1_1);
        
        JLabel cpfTextoLabel_1_1_2 = new JLabel("Débito");
        cpfTextoLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        cpfTextoLabel_1_1_2.setForeground(Color.BLACK);
        cpfTextoLabel_1_1_2.setFont(new Font("SansSerif", Font.BOLD, 22));
        cpfTextoLabel_1_1_2.setBounds(484, 442, 284, 35);
        panel.add(cpfTextoLabel_1_1_2);
        
        JLabel cpfTextoLabel_1_1_3 = new JLabel("Crédito");
        cpfTextoLabel_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        cpfTextoLabel_1_1_3.setForeground(Color.BLACK);
        cpfTextoLabel_1_1_3.setFont(new Font("SansSerif", Font.BOLD, 22));
        cpfTextoLabel_1_1_3.setBounds(484, 478, 284, 35);
        panel.add(cpfTextoLabel_1_1_3);
	}
}
