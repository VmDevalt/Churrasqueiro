package com.churrasqueiro.ui;

import com.churrasqueiro.utils.FontManager;
import com.churrasqueiro.utils.FontsConstants;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.JFrame;

public class TelaValidacaoCodigoEsqueceuSenha extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelVermelho;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;
	private EstilizacaoRedonda.CaixaTextoRedonda campoCodigo;
	private String emailUsuario;
	private String codigoGerado;

	public TelaValidacaoCodigoEsqueceuSenha(String email) {
		this.emailUsuario = email;
		this.codigoGerado = gerarCodigo();
		enviarCodigoPorEmail();
		initialize();
	}
	
	private String gerarCodigo() {
		int codigo = (int) (Math.random() * 900000) + 100000;
		return String.valueOf(codigo);
	}
	
	private void enviarCodigoPorEmail() {
		System.out.println("Código " + codigoGerado + " enviado para: " + emailUsuario);
	}
	
	private boolean validarCodigo(String codigoDigitado) {
		return codigoDigitado.equals(codigoGerado);
	}
	
	public void verificarCodigo() {
		String codigo = campoCodigo.getText().trim();
		
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Por favor, digite o código recebido.",
					"Campo Vazio",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (validarCodigo(codigo)) {
			JOptionPane.showMessageDialog(this,
					"Código válido!",
					"Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			
			dispose();
		} else {
			JOptionPane.showMessageDialog(this,
					"Código inválido. Tente novamente.",
					"Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaValidacaoCodigoEsqueceuSenha window = new TelaValidacaoCodigoEsqueceuSenha();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaValidacaoCodigoEsqueceuSenha() {
		initialize();
	}

/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Color corPaletaVermelho = new Color(179,13,36);
        Color corPaletaBege = new Color(227,202,187);
        Color corPaletaVermelhoInteracao = new Color(200,50,50);
        Color corPaletaVermelhoPressionado = new Color(150,0,0);
        Color corPaletaPreto = new Color(0,0,0);
        Color corPaletaCinza = new Color(140,127,127);
        
        setTitle("Verificação de Código - Churrasqueiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, LARGURA, ALTURA);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);
        
        panelVermelho = new JPanel();
        panelVermelho.setBackground(corPaletaVermelho);
        panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelVermelho);
        panelVermelho.setLayout(null);
        
        final EstilizacaoRedonda.PainelRedondo panelBranco = new EstilizacaoRedonda.PainelRedondo(null, 60, 4, corPaletaBege, null);
        panelBranco.setFocusable(true);
        panelBranco.requestFocusInWindow();
        panelBranco.setBounds(90, 85, 1098, 505);
        panelVermelho.add(panelBranco);
        panelBranco.setLayout(null);
        
        JLabel labelTitulo = new JLabel("Código Enviado");
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setForeground(corPaletaPreto);
        labelTitulo.setFont(FontsConstants.MONTSERRAT_BOLD_25);
        labelTitulo.setBounds(449, 50, 200, 32);
        panelBranco.add(labelTitulo);
        
        JLabel labelInstrucoes = new JLabel("<html><div style='text align: center;'>Acabamos de enviar o código. Confira na sua caixa de entrada<br>e digite ele aqui embaixo.</div></html>");
        labelInstrucoes.setHorizontalAlignment(SwingConstants.CENTER);
        labelInstrucoes.setForeground(corPaletaPreto);
        labelInstrucoes.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelInstrucoes.setBounds(349, 120, 400, 60);
        panelBranco.add(labelInstrucoes);
        
        JLabel labelEmailInfo = new JLabel("Email: " + emailUsuario);
        labelEmailInfo.setHorizontalAlignment(SwingConstants.CENTER);
        labelEmailInfo.setForeground(corPaletaCinza);
        labelEmailInfo.setFont(FontsConstants.MONTSERRAT_REGULAR_13);
        labelEmailInfo.setBounds(349, 150, 400, 20);
        panelBranco.add(labelEmailInfo);
        
        JSeparator separador = new JSeparator();
        separador.setForeground(corPaletaVermelho);
        separador.setBackground(corPaletaVermelho);
        separador.setBounds(349, 190, 400, 2);
        panelBranco.add(separador);
        
        JLabel labelCodigo = new JLabel("Código de Verificação");
        labelCodigo.setForeground(corPaletaPreto);
        labelCodigo.setFont(FontsConstants.MONTSERRAT_BOLD_15);
        labelCodigo.setBounds(469, 220, 180, 32);
        panelBranco.add(labelCodigo);
        
        this.campoCodigo = new EstilizacaoRedonda.CaixaTextoRedonda("Digite o código...", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoCodigo.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        campoCodigo.setToolTipText("Digite o código de 6 dígitos");
        campoCodigo.setBounds(449, 260, 180, 38);
        campoCodigo.setColumns(10);
        panelBranco.add(labelCodigo);
        
        JButton botaoVerificar = new EstilizacaoRedonda.BotaoRedondo("Verificar Código", corPaletaVermelho, corPaletaVermelhoInteracao, corPaletaVermelhoPressionado, 35);
        botaoVerificar.setForeground(corPaletaBege);
        botaoVerificar.setBackground(corPaletaVermelho);
        botaoVerificar.setBounds(449, 320, 180, 38);
        botaoVerificar.setFont(FontsConstants.MONTSERRAT_BOLD_15);
        botaoVerificar.addActionListener(e -> verificarCodigo());
        panelBranco.add(botaoVerificar);
        
        JButton botaoReenviar = new EstilizacaoRedonda.BotaoRedondo("Reenviar Código", corPaletaPreto, corPaletaCinza, corPaletaVermelhoPressionado, 25);
        botaoReenviar.setForeground(corPaletaBege);
        botaoReenviar.setBackground(corPaletaPreto);
        botaoReenviar.setBorderPainted(false);
        botaoReenviar.setFocusPainted(false);
        botaoReenviar.setFont(FontsConstants.MONTSERRAT_BOLD_13);
        botaoReenviar.setBounds(469, 370, 140, 25);
        botaoReenviar.addActionListener(e -> reenviarCodigo());
        panelBranco.add(botaoReenviar);
        
        JLabel labelLogo = new JLabel("");
        labelLogo.setIcon(new ImageIcon("src/main/resources/assets/imagens/logoPequena.png"));
        labelLogo.setBounds(996, 394, 90, 99);
        panelBranco.add(labelLogo);
        
        JButton botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar", corPaletaPreto, corPaletaVermelhoInteracao, corPaletaVermelhoPressionado, 35);
        botaoVoltar.setForeground(corPaletaVermelho);
        botaoVoltar.setBackground(corPaletaBege);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_13);
        botaoVoltar.setBounds(30, 20, 120, 35);
        botaoVoltar.addActionListener(e -> {
            dispose();
            
            new TelaEsqueceuSenha().setVisible(true);
        });
        panelVermelho.add(botaoVoltar);
	}
	
	private void reenviarCodigo() {
    	this.codigoGerado = gerarCodigo();
    	enviarCodigoPorEmail();
    	JOptionPane.showMessageDialog(this,
    			"Novo código enviado para: " + emailUsuario,
    			"Código Reenviado",
    			JOptionPane.INFORMATION_MESSAGE);
    }

}
