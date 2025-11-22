package com.churrasqueiro.ui;

import java.awt.EventQueue;

import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private JTextField campoLogin;
    private JTextField campoSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login - Churrasqueiro");
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 232, 170));
		panel.setBounds(39, 62, 1163, 563);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton botaoRevelarSenha = new JButton("");
		botaoRevelarSenha.setIcon(new ImageIcon(getClass().getResource("/assets/imagens/olho.png")));
		botaoRevelarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(botaoRevelarSenha, "Teste");
			}
		});
		botaoRevelarSenha.setBounds(735, 365, 29, 20);
		panel.add(botaoRevelarSenha);
		botaoRevelarSenha.setBorder(null);
		botaoRevelarSenha.setContentAreaFilled(false);
		botaoRevelarSenha.setFocusPainted(false);
		botaoRevelarSenha.setOpaque(false);
		
		campoLogin = new JTextField();
		campoLogin.setFont(new Font("Calibri", Font.PLAIN, 18));
		campoLogin.setText("Digite seu e-mail...");
		campoLogin.setToolTipText("Digite seu texto");
		campoLogin.setBounds(316, 266, 454, 31);
		panel.add(campoLogin);
		campoLogin.setColumns(10);
		campoLogin.setBackground(new Color(238, 232, 170));
		campoLogin.setBorder(new LineBorder(Color.RED, 2, true));

		
		campoSenha = new JTextField();
		campoSenha.setToolTipText("Digite seu texto");
		campoSenha.setText("Digite sua senha...");
		campoSenha.setFont(new Font("Calibri", Font.PLAIN, 18));
		campoSenha.setColumns(10);
		campoSenha.setBounds(316, 361, 454, 31);
		campoSenha.setBackground(new Color(238, 232, 170));
		campoSenha.setBorder(new LineBorder(Color.RED, 2));

		panel.add(campoSenha);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon(getClass().getResource("/assets/imagens/logo.png")));
		labelLogo.setBounds(442, 37, 202, 183);
		panel.add(labelLogo);
		
		JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelLogin.setBounds(505, 244, 71, 12);
		panel.add(labelLogin);
		
		JLabel labelSenha = new JLabel("SENHA");
		labelSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelSenha.setBounds(505, 339, 71, 12);
		panel.add(labelSenha);
		
		JButton botaoLogar = new JButton("ENTRAR");
		botaoLogar.setForeground(new Color(255, 255, 255));
		botaoLogar.setBackground(new Color(255, 0, 0));
		botaoLogar.setBounds(316, 445, 173, 31);
		panel.add(botaoLogar);
		
		JButton botaoEsqueciSenha = new JButton("ESQUECEU A SENHA?");
		botaoEsqueciSenha.setForeground(new Color(255, 255, 255));
		botaoEsqueciSenha.setBackground(new Color(0, 0, 0));
		botaoEsqueciSenha.setBounds(597, 445, 173, 31);
		panel.add(botaoEsqueciSenha);
	}
}
