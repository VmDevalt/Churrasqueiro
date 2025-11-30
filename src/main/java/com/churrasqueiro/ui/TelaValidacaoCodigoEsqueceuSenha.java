package com.churrasqueiro.ui;

import com.churrasqueiro.utils.FontsConstants;
import java.awt.EventQueue;
import java.awt.Color;
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
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
