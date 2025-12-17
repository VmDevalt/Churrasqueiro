package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontsConstants;
import javax.swing.JLabel;

public class TelaCartaoCredito extends JFrame {

	private static final long serialVersionUID = 1L;
	    private static final int LARGURA = 600;
	    private static final int ALTURA = 400;
	private JPanel contentPane;
	    private EstilizacaoRedonda.CaixaTextoRedonda campoNomeTitular;
	    private EstilizacaoRedonda.CaixaTextoRedonda campoCvv;
	    private EstilizacaoRedonda.CaixaTextoRedonda campoNumeroCartao;
	    private EstilizacaoRedonda.CaixaTextoRedonda campoDataVencimento;
	    private JButton botaoCadastrar;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
	public void run() {
	try {
		TelaCartaoCredito frame = new TelaCartaoCredito();
		frame.setVisible(true);
	} catch (Exception e) {
		e.printStackTrace();
				}
			}
		});
	}
	
	Color corPaletaVermelho = new Color(179,13,36);
	    Color corPaletaBege = new Color(227,202,187);
	    Color corPaletaVermelhoInteracao = new Color(200,50,50);
	    Color corPaletaVermelhoPressionado = new Color(150,0,0);
	    Color corPaletaPreto = new Color(0,0,0);
	    Color corPaletaPretoInteração = new Color(35,35,35);
	    Color corPaletaCinza = new Color(140,127,127);
	    private JLabel lblDataVencimento;
	    private JLabel lblNomeDoTitular_1;
	    private JLabel lblNumeroCartao;
	    private JLabel lblCvv;
	
	public TelaCartaoCredito() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Pagamento pelo cartão -  Churrasqueiro");
	setBounds(100, 100, 537, 372);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	setSize(LARGURA, ALTURA);
	        setResizable(false);
	        setLocationRelativeTo(null);
	contentPane.setLayout(null);
	contentPane.setBackground(corPaletaBege);
	
	campoNomeTitular = new EstilizacaoRedonda.CaixaTextoRedonda(
	                "",
	                corPaletaVermelho,
	                corPaletaBege,
	                corPaletaCinza,
	                2,
	                35
	        );
	        campoNomeTitular.setFont(new Font("SansSerif", Font.PLAIN, 14));
	        campoNomeTitular.setToolTipText("");
	        campoNomeTitular.setBounds(263, 78, 219, 29);
	        contentPane.add(campoNomeTitular);
	        campoNomeTitular.setColumns(10);
	       
	        campoCvv = new EstilizacaoRedonda.CaixaTextoRedonda(
	                "",
	                corPaletaVermelho,
	                corPaletaBege,
	                corPaletaCinza,
	                2,
	                35
	        );
	        campoCvv.setFont(new Font("SansSerif", Font.PLAIN, 14));
	        campoCvv.setToolTipText("");
	        campoCvv.setBounds(263, 231, 139, 29);
	        contentPane.add(campoCvv);
	        campoCvv.setColumns(10);
	       
	        campoNumeroCartao = new EstilizacaoRedonda.CaixaTextoRedonda(
	                "",
	                corPaletaVermelho,
	                corPaletaBege,
	                corPaletaCinza,
	                2,
	                35
	        );
	        campoNumeroCartao.setFont(new Font("SansSerif", Font.PLAIN, 14));
	        campoNumeroCartao.setToolTipText("");
	        campoNumeroCartao.setBounds(263, 181, 219, 29);
	        contentPane.add(campoNumeroCartao);
	        campoNumeroCartao.setColumns(10);
	       
	        campoDataVencimento = new EstilizacaoRedonda.CaixaTextoRedonda(
	                "",
	                corPaletaVermelho,
	                corPaletaBege,
	                corPaletaCinza,
	                2,
	                35
	        );
	        campoDataVencimento.setFont(new Font("SansSerif", Font.PLAIN, 14));
	        campoDataVencimento.setToolTipText("");
	        campoDataVencimento.setBounds(263, 130, 139, 29);
	        contentPane.add(campoDataVencimento);
	        campoDataVencimento.setColumns(10);
	       
	        this.botaoCadastrar = new EstilizacaoRedonda.BotaoRedondo("Entrar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
	        botaoCadastrar.setText("Cadastrar");
			botaoCadastrar.setForeground(new Color(227,202,187));
			botaoCadastrar.setBackground(new Color(179, 13, 36));
			botaoCadastrar.setBounds(185, 294, 226, 33);
	        botaoCadastrar.setFont(FontsConstants.MONTSERRAT_BOLD_20);
	        contentPane.add(botaoCadastrar);
	        
	        JLabel labelTituloDadosCartao = new JLabel("Dados do cartão");
	        labelTituloDadosCartao.setBounds(203, 26, 226, 26);
	        labelTituloDadosCartao.setForeground(corPaletaVermelho);
	        labelTituloDadosCartao.setFont(new Font("SansSerif", Font.BOLD, 25));
	        contentPane.add(labelTituloDadosCartao);
	        
	        lblDataVencimento = new JLabel("Data de vencimento");
	        lblDataVencimento.setForeground(new Color(179, 13, 36));
	        lblDataVencimento.setFont(new Font("SansSerif", Font.BOLD, 14));
	        lblDataVencimento.setBounds(60, 131, 149, 26);
	        contentPane.add(lblDataVencimento);
	        
	        lblNomeDoTitular_1 = new JLabel("Nome do titular");
	        lblNomeDoTitular_1.setForeground(new Color(179, 13, 36));
	        lblNomeDoTitular_1.setFont(new Font("SansSerif", Font.BOLD, 14));
	        lblNomeDoTitular_1.setBounds(60, 79, 139, 26);
	        contentPane.add(lblNomeDoTitular_1);
	        
	        lblNumeroCartao = new JLabel("Número do cartão");
	        lblNumeroCartao.setForeground(new Color(179, 13, 36));
	        lblNumeroCartao.setFont(new Font("SansSerif", Font.BOLD, 14));
	        lblNumeroCartao.setBounds(60, 182, 149, 26);
	        contentPane.add(lblNumeroCartao);
	        
	        lblCvv = new JLabel("Código de segurança");
	        lblCvv.setForeground(new Color(179, 13, 36));
	        lblCvv.setFont(new Font("SansSerif", Font.BOLD, 14));
	        lblCvv.setBounds(60, 232, 159, 26);
	        contentPane.add(lblCvv);

	       
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