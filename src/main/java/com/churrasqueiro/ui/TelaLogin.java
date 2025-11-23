package com.churrasqueiro.ui;

import java.awt.EventQueue;

import javax.swing.border.LineBorder;

import com.churrasqueiro.business.LoginController;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;

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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TelaLogin extends JFrame {
	
	private static final LoginController loginController = new LoginController();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoLogar;

    public String getLogin() {
		return campoLogin.getText().trim();
	}

	public String getSenha() {
		String senha = new String(campoSenha.getPassword());
		campoSenha.setText("");
		return senha;
	}
	

	public void autenticar() {
		String login = getLogin();
		String senha = getSenha();
		
		try {
			Usuario usuarioAutenticado = loginController.autenticar(login, senha);
			JOptionPane.showMessageDialog(this, 
	                "Login bem-sucedido! Tipo: " + usuarioAutenticado.getTipo(), 
	                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (ControllerException ex) {
			 JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Login",
			 JOptionPane.WARNING_MESSAGE);
            
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de comunicação com o banco de dados.", "Erro Fatal", 
            JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado", ex.getMessage(), 
            JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
      }

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
        contentPane.setBackground(new Color(179, 13, 36));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(227, 202, 187));
		panel.setBounds(91, 89, 1098, 505);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton botaoRevelarSenha = new JButton("");
		botaoRevelarSenha.setIcon(new ImageIcon(getClass().getResource("/assets/imagens/olho.png")));
		botaoRevelarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(botaoRevelarSenha, "Teste");
			}
		});
		botaoRevelarSenha.setBounds(728, 316, 47, 31);
		panel.add(botaoRevelarSenha);
		botaoRevelarSenha.setBorder(null);
		botaoRevelarSenha.setContentAreaFilled(false);
		botaoRevelarSenha.setFocusPainted(false);
		botaoRevelarSenha.setOpaque(false);
		
		campoLogin = new JTextField();
		campoLogin.setFont(new Font("Calibri", Font.PLAIN, 18));
		campoLogin.setText("Digite seu e-mail...");
		campoLogin.setToolTipText("Digite seu texto");
		campoLogin.setBounds(321, 221, 454, 31);
		panel.add(campoLogin);
		campoLogin.setColumns(10);
		campoLogin.setBackground(new Color(227, 202, 187));
		campoLogin.setBorder(new LineBorder(new Color(191, 63, 63), 2, true));

		
		campoSenha = new JPasswordField();
		campoSenha.setToolTipText("Digite seu texto");
		campoSenha.setText("Digite sua senha...");
		campoSenha.setFont(new Font("Calibri", Font.PLAIN, 18));
		campoSenha.setColumns(10);
		campoSenha.setBounds(321, 316, 454, 31);
		campoSenha.setBackground(new Color(227, 202, 187));
		campoSenha.setBorder(new LineBorder(new Color(191, 63, 63), 2));

		panel.add(campoSenha);

        int larguraLogo = 150;
        int alturaLogo = 132;
        JLabel labelLogo = new JLabel("");
        labelLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/assets/imagens/logo.png")));
        labelLogo.setBounds(463, 33, 150, 132);
        panel.add(labelLogo);
        java.net.URL urlLogo = getClass().getResource("/assets/imagens/logo.png");
        if (urlLogo != null) {
            java.awt.Image originalImage = java.awt.Toolkit.getDefaultToolkit().getImage(urlLogo);
            java.awt.Image resizedImage = originalImage.getScaledInstance(
                    larguraLogo,
                    alturaLogo,
                    java.awt.Image.SCALE_SMOOTH
            );
            labelLogo.setIcon(new ImageIcon(resizedImage));
        }



        JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelLogin.setBounds(510, 188, 71, 23);
		panel.add(labelLogin);
		
		JLabel labelSenha = new JLabel("SENHA");
		labelSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelSenha.setBounds(510, 286, 71, 20);
		panel.add(labelSenha);
		
		JButton botaoLogar = new JButton("ENTRAR");
		botaoLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticar();
			}
		});
		botaoLogar.setForeground(new Color(255, 255, 255));
		botaoLogar.setBackground(new Color(179, 13, 36));
		botaoLogar.setBounds(321, 400, 173, 31);
		panel.add(botaoLogar);
		
		JButton botaoEsqueciSenha = new JButton("ESQUECEU A SENHA?");
		botaoEsqueciSenha.setForeground(new Color(255, 255, 255));
		botaoEsqueciSenha.setBackground(new Color(0, 0, 0));
		botaoEsqueciSenha.setBounds(602, 400, 173, 31);
		panel.add(botaoEsqueciSenha);
	}
}
