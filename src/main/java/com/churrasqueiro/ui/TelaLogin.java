package com.churrasqueiro.ui;

import java.awt.EventQueue;

import javax.swing.border.LineBorder;

import com.churrasqueiro.ui.EstilizacaoRedonda;
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
    private EstilizacaoRedonda.CaixaTextoRedonda campoLogin;
    private EstilizacaoRedonda.CaixaSenhaRedonda campoSenha;
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

        Color corPaletaVermelho = new Color(179,13,36);
        Color corPaletaBege = new Color(227,202,187);
        Color corPaletaVermelhoInteracao = new Color(200,50,50);
        Color corPaletaVermelhoPressionado = new Color(150,0,0);
        Color corPaletaPreto = new Color(0,0,0);
        Color corPaletaPretoInteração = new Color(35,35,35);
        Color corPaletaCinza = new Color(140,127,127);

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

        final EstilizacaoRedonda.PainelRedondo panel = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaBege,null);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
		panel.setBounds(90, 85, 1098, 505);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton botaoRevelarSenha = new JButton("");
		botaoRevelarSenha.setIcon(new ImageIcon(getClass().getResource("/assets/imagens/olho.png")));
		botaoRevelarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(botaoRevelarSenha, "Teste");
			}
		});
		botaoRevelarSenha.setBounds(793, 308, 58, 38);
		panel.add(botaoRevelarSenha);
		botaoRevelarSenha.setBorder(null);
		botaoRevelarSenha.setContentAreaFilled(false);
		botaoRevelarSenha.setFocusPainted(false);
		botaoRevelarSenha.setOpaque(false);

        this.campoLogin = new EstilizacaoRedonda.CaixaTextoRedonda("Digite seu email...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoLogin.setFont(new Font("Calibri", Font.PLAIN, 14));
		campoLogin.setToolTipText("Digite seu texto");
		campoLogin.setBounds(250, 225, 597, 38);
		panel.add(campoLogin);
		campoLogin.setColumns(10);


		this.campoSenha = new EstilizacaoRedonda.CaixaSenhaRedonda("Digite sua senha...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoSenha.setToolTipText("Digite seu texto");
		campoSenha.setFont(new Font("Calibri", Font.PLAIN, 14));
		campoSenha.setColumns(10);
		campoSenha.setBounds(250, 308, 597, 38);
		panel.add(campoSenha);

        int larguraLogo = 116;
        int alturaLogo = 102;
        JLabel labelLogo = new JLabel("");
        labelLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/assets/imagens/logo.png")));
        labelLogo.setBounds(479, 77, 116, 102);
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
        labelLogin.setForeground(corPaletaPreto);
		labelLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelLogin.setBounds(510, 191, 58, 32);
		panel.add(labelLogin);
		
		JLabel labelSenha = new JLabel("SENHA");
        labelSenha.setForeground(corPaletaPreto);
		labelSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelSenha.setBounds(510, 276, 71, 32);
		panel.add(labelSenha);

        this.botaoLogar = new EstilizacaoRedonda.BotaoRedondo("Entrar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				autenticar();
			}
		});
		botaoLogar.setForeground(new Color(255, 255, 255));
		botaoLogar.setBackground(new Color(179, 13, 36));
		botaoLogar.setBounds(275, 393, 268, 38);
        botaoLogar.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(botaoLogar);

        final EstilizacaoRedonda.BotaoRedondo botaoEsqueciSenha = new EstilizacaoRedonda.BotaoRedondo("Esqueceu a Senha?",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoEsqueciSenha.setForeground(new Color(255, 255, 255));
		botaoEsqueciSenha.setBackground(new Color(0, 0, 0));
		botaoEsqueciSenha.setBounds(555, 393, 261, 38);
        botaoEsqueciSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
        panel.add(botaoEsqueciSenha);
    }
}
