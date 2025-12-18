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

import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.utils.FontsConstants;

public class TelaConfiguracoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

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

	Color corPaletaBege = new Color(227,202,187);
	Color corPaletaVermelho = new Color(179,13,36);
	Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaBranco = new Color(255,255,255);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteração = new Color(35,35,35);
    Color corPaletaCinza = new Color(140,127,127);
	
	public TelaConfiguracoes(){
		Usuario usuarioLogado = TelaLogin.getUsuarioLogado();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGURA, ALTURA);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
        setTitle("Configurações - Churrasqueiro");
		contentPane.setBackground(corPaletaVermelho);
		
		contentPane.setLayout(null);
        
        JLabel configuracoesLabel = new JLabel("Configurações");
        configuracoesLabel.setBounds(490, 25, 350, 58);
        configuracoesLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        configuracoesLabel.setForeground(corPaletaBege);
        contentPane.add(configuracoesLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(30, 12, 92, 82);
        logoLabel.setIcon(new ImageIcon(TelaCriarGrupo.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        contentPane.add(logoLabel);

		JPanel panel = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaBege,null);
        panel.setBounds(-25, 102, 1291, 581);
        panel.setBackground(corPaletaBege);
        contentPane.add(panel);
        panel.setLayout(null);
        
        final EstilizacaoRedonda.BotaoRedondo botaoSair = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoSair.setFont(FontsConstants.MONTSERRAT_BOLD_18);
		botaoSair.setForeground(corPaletaBege);
		botaoSair.setBackground(new Color(0, 0, 0));
		botaoSair.setBounds(1118, 32, 111, 38);
        contentPane.add(botaoSair);
        botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaGestao telaGestao = new TelaGestao();
				telaGestao.setVisible(true);
			}
		});
        
        JLabel contaLabel = new JLabel("Conta");
        contaLabel.setBounds(564, 26, 150, 76);
        contaLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        contaLabel.setForeground(corPaletaPreto);
        panel.add(contaLabel);
        
        JLabel loginTituloLabel = new JLabel("Login");
        loginTituloLabel.setForeground(corPaletaVermelho);
        loginTituloLabel.setFont(FontsConstants.MONTSERRAT_BOLD_34);
        loginTituloLabel.setBounds(66, 163, 109, 44);
        panel.add(loginTituloLabel);
        
        JLabel loginTextoLabel = new JLabel(usuarioLogado.getLogin());
        loginTextoLabel.setForeground(corPaletaPreto);
        loginTextoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        loginTextoLabel.setBounds(66, 199, 292, 35);
        panel.add(loginTextoLabel);
        
        JLabel tipoContaTextoLabel = new JLabel(usuarioLogado.getTipo());
        tipoContaTextoLabel.setForeground(Color.BLACK);
        tipoContaTextoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        tipoContaTextoLabel.setBounds(66, 302, 206, 35);
        panel.add(tipoContaTextoLabel);
        
        JLabel tipoContaTituloLabel = new JLabel("Tipo da conta");
        tipoContaTituloLabel.setForeground(new Color(179, 13, 36));
        tipoContaTituloLabel.setFont(FontsConstants.MONTSERRAT_BOLD_34);
        tipoContaTituloLabel.setBounds(66, 267, 250, 44);
        panel.add(tipoContaTituloLabel);
        
        JLabel emailTituloLabel = new JLabel("E-mail");
        emailTituloLabel.setForeground(new Color(179, 13, 36));
        emailTituloLabel.setFont(FontsConstants.MONTSERRAT_BOLD_34);
        emailTituloLabel.setBounds(826, 147, 130, 44);
        panel.add(emailTituloLabel);
        
        JLabel lblIngridvitriachurrasqueirocom = new JLabel(usuarioLogado.getEmail());
        lblIngridvitriachurrasqueirocom.setForeground(Color.BLACK);
        lblIngridvitriachurrasqueirocom.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        lblIngridvitriachurrasqueirocom.setBounds(826, 183, 378, 35);
        panel.add(lblIngridvitriachurrasqueirocom);
        
        JLabel cnpjTituloLabel = new JLabel("CNPJ");
        cnpjTituloLabel.setForeground(new Color(179, 13, 36));
        cnpjTituloLabel.setFont(FontsConstants.MONTSERRAT_BOLD_34);
        cnpjTituloLabel.setBounds(826, 251, 109, 44);
        panel.add(cnpjTituloLabel);
        
        JLabel cpfTextoLabel = new JLabel("91.398.041/0001-33"); //pj mockaado gerado no 4devs 
        cpfTextoLabel.setForeground(Color.BLACK);
        cpfTextoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        cpfTextoLabel.setBounds(826, 286, 284, 35);
        panel.add(cpfTextoLabel);
        
        JLabel formasPagamentoTituloLabel = new JLabel("Formas de Pagamento");
        formasPagamentoTituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formasPagamentoTituloLabel.setForeground(new Color(179, 13, 36));
        formasPagamentoTituloLabel.setFont(FontsConstants.MONTSERRAT_BOLD_28);
        formasPagamentoTituloLabel.setBounds(448, 329, 350, 44);
        panel.add(formasPagamentoTituloLabel);
        
        JLabel pixLabel = new JLabel("Pix");
        pixLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pixLabel.setForeground(Color.BLACK);
        pixLabel.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        pixLabel.setBounds(484, 370, 284, 35);
        panel.add(pixLabel);
        
        JLabel dinheiroLabel = new JLabel("Dinheiro");
        dinheiroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dinheiroLabel.setForeground(Color.BLACK);
        dinheiroLabel.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        dinheiroLabel.setBounds(484, 405, 284, 35);
        panel.add(dinheiroLabel);
        
        JLabel debitoLabel = new JLabel("Débito");
        debitoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        debitoLabel.setForeground(Color.BLACK);
        debitoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        debitoLabel.setBounds(484, 442, 284, 35);
        panel.add(debitoLabel);
        
        JLabel creditoLabel = new JLabel("Crédito");
        creditoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditoLabel.setForeground(Color.BLACK);
        creditoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        creditoLabel.setBounds(484, 478, 284, 35);
        panel.add(creditoLabel);

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
