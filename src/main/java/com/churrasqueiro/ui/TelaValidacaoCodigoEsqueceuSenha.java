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
        
        
	}

}
