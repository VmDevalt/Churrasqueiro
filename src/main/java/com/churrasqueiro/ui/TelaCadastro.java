package com.churrasqueiro.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelVermelho;
	private JTextField textFieldNome;
	private JTextField txtDigiteSeuCpf;
	private JTextField txtDigiteSeuTelefone;
	private JTextField txtDigiteSeuEmail;
	private JPasswordField txtDigiteSenha;
	private JPasswordField txtConfirmeSenha;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TelaCadastro() {
		setTitle("Cadastro - Churrasqueiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		panelVermelho = new JPanel();
		panelVermelho.setForeground(new Color(230, 230, 250));
		panelVermelho.setBackground(new Color(179, 13, 36));
		panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelVermelho);
		panelVermelho.setLayout(null);
		
		JPanel panelBranco = new JPanel();
		panelBranco.setToolTipText("Digite seu nome...");
		panelBranco.setBackground(new Color(227, 202, 187));
		panelBranco.setBounds(97, 90, 1098, 505);
		panelVermelho.add(panelBranco);
		panelBranco.setLayout(null);
		
		JLabel textCadastrUsuario = new JLabel("Cadastro de Usúario");
		textCadastrUsuario.setBounds(384, 12, 378, 44);
		textCadastrUsuario.setFont(new Font("Calibri", Font.PLAIN, 40));
		panelBranco.add(textCadastrUsuario);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(993, 399, 82, 80);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBackground(new Color(0, 0, 0));
		lblLogo.setIcon(new ImageIcon("/home/victorscds/Documentos/GitHub/CampusPay/src/main/resources/assets/imagens/logoPequena.png"));
		panelBranco.add(lblLogo);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setBounds(459, 68, 197, 44);
		lblNomeCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCompleto.setFont(new Font("Calibri", Font.PLAIN, 18));
		panelBranco.add(lblNomeCompleto);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(333, 114, 454, 31);
		textFieldNome.setToolTipText("Digite seu texto");
		textFieldNome.setText("Digite seu nome completo...");
		textFieldNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldNome.setForeground(Color.BLACK);
		textFieldNome.setBackground(new Color(227, 202, 187));
		panelBranco.add(textFieldNome);
		textFieldNome.setColumns(10);
		textFieldNome.setBorder(new LineBorder(new Color(191, 63, 63), 2));
		
		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setBounds(284, 167, 197, 44);
		lblCPF.setHorizontalAlignment(SwingConstants.CENTER);
		lblCPF.setFont(new Font("Calibri", Font.PLAIN, 18));
		panelBranco.add(lblCPF);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(627, 167, 197, 44);
		lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefone.setFont(new Font("Calibri", Font.PLAIN, 18));
		panelBranco.add(lblTelefone);
		
		txtDigiteSeuCpf = new JTextField();
		txtDigiteSeuCpf.setBounds(223, 211, 312, 31);
		txtDigiteSeuCpf.setToolTipText("Digite seu texto");
		txtDigiteSeuCpf.setText("Digite seu CPF...");
		txtDigiteSeuCpf.setForeground(Color.BLACK);
		txtDigiteSeuCpf.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDigiteSeuCpf.setColumns(10);
		txtDigiteSeuCpf.setBorder(new LineBorder(new Color(191, 63, 63), 2));
		txtDigiteSeuCpf.setBackground(new Color(227, 202, 187));
		panelBranco.add(txtDigiteSeuCpf);
		
		txtDigiteSeuTelefone = new JTextField();
		txtDigiteSeuTelefone.setBounds(576, 211, 312, 31);
		txtDigiteSeuTelefone.setToolTipText("Digite seu texto");
		txtDigiteSeuTelefone.setText("Digite seu Telefone...");
		txtDigiteSeuTelefone.setForeground(Color.BLACK);
		txtDigiteSeuTelefone.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDigiteSeuTelefone.setColumns(10);
		txtDigiteSeuTelefone.setBorder(new LineBorder(new Color(191, 63, 63), 2));
		txtDigiteSeuTelefone.setBackground(new Color(227, 202, 187));
		panelBranco.add(txtDigiteSeuTelefone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(459, 255, 197, 44);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 18));
		panelBranco.add(lblEmail);
		
		txtDigiteSeuEmail = new JTextField();
		txtDigiteSeuEmail.setBounds(333, 293, 454, 31);
		txtDigiteSeuEmail.setToolTipText("Digite seu texto");
		txtDigiteSeuEmail.setText("Digite seu Email...");
		txtDigiteSeuEmail.setForeground(Color.BLACK);
		txtDigiteSeuEmail.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDigiteSeuEmail.setColumns(10);
		txtDigiteSeuEmail.setBorder(new LineBorder(new Color(191, 63, 63), 2));
		txtDigiteSeuEmail.setBackground(new Color(227, 202, 187));
		panelBranco.add(txtDigiteSeuEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(284, 355, 197, 44);
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setFont(new Font("Calibri", Font.PLAIN, 18));
		panelBranco.add(lblSenha);
		
		JLabel lblComfirmarSenha = new JLabel("Confirmar Senha");
		lblComfirmarSenha.setBounds(627, 355, 197, 44);
		lblComfirmarSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblComfirmarSenha.setFont(new Font("Calibri", Font.PLAIN, 18));
		panelBranco.add(lblComfirmarSenha);
		
		txtDigiteSenha = new JPasswordField();
		txtDigiteSenha.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDigiteSenha.setToolTipText("Digite sua senha...");
		txtDigiteSenha.setBounds(223, 399, 312, 31);
		txtDigiteSenha.setBorder(new LineBorder(new Color(191, 63, 63), 2));
		txtDigiteSenha.setBackground(new Color(227, 202, 187));
		panelBranco.add(txtDigiteSenha);
		
		JButton buttonCriarConta = new JButton("Criar Conta");
		buttonCriarConta.setBounds(500, 469, 121, 24);
		buttonCriarConta.setForeground(new Color(227, 202, 187));
		buttonCriarConta.setBackground(new Color(179, 13, 36));
		buttonCriarConta.setFont(new Font("Calibri", Font.PLAIN, 15));
		panelBranco.add(buttonCriarConta);
		
		txtConfirmeSenha = new JPasswordField();
		txtConfirmeSenha.setToolTipText("Digite sua senha...");
		txtConfirmeSenha.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtConfirmeSenha.setBorder(new LineBorder(new Color(191, 63, 63), 2));
		txtConfirmeSenha.setBackground(new Color(227, 202, 187));
		txtConfirmeSenha.setBounds(576, 399, 312, 31);
		panelBranco.add(txtConfirmeSenha);

	}
}