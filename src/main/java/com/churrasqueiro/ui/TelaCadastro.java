package com.churrasqueiro.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import com.churrasqueiro.business.CadastroUsuarioController;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontsConstants;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelVermelho;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;
	private static final CadastroUsuarioController CadastroController = new CadastroUsuarioController();
	private EstilizacaoRedonda.CaixaTextoRedonda campoLogin;
	private EstilizacaoRedonda.CaixaSenhaRedonda campoSenha;
	private EstilizacaoRedonda.CaixaSenhaRedonda CampoConfirmarSenha;
	private EstilizacaoRedonda.CaixaTextoRedonda campoEmail;
	private JComboBox<String> cBoxTipoUsuario;
	private JButton botaoCriarConta;
	
    Color corPaletaVermelho = new Color(179,13,36);
    Color corPaletaBege = new Color(227,202,187);
    Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteracao = new Color(35,35,35);
    Color corPaletaCinza = new Color(140,127,127);
    Color corPaletaBegeInteracao = new Color(245,225,210);
    Color corPaletaBegePressionado = new Color(200,175,160);
	
	public String getLogin() {
		return campoLogin.getText().trim();
	}
	
	public String getEmail() {
		return campoEmail.getText().trim();
	}
	
	public String getTipo() {
		Object tipoSelecionado = cBoxTipoUsuario.getSelectedItem();
		String tipo = String.valueOf(tipoSelecionado);
		return tipo;
	}
	
	public String getSenha() {
		String senha = new String(campoSenha.getPassword());
		return senha;
	}
	
	public String getSenhaConfirmada() {
		String senhaConfirmada = new String(CampoConfirmarSenha.getPassword());
		return senhaConfirmada;
	}
	
	public void cadastrar() {
		String login = getLogin();
		String tipo = getTipo();
		String email = getEmail();
		String senha = getSenha();
		String senhaConfirmada = getSenhaConfirmada();
		
		try {
			Usuario novoUsuario = CadastroController.cadastrar(login, tipo, email, senha, senhaConfirmada);
			JOptionPane.showMessageDialog(this,
					"Cadastro realizado com sucesso! Tipo: " + novoUsuario.getTipo(),
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);
			limparCampos();
		} catch(ControllerException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Login",
			JOptionPane.WARNING_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
		} catch(DatabaseException e) {
			JOptionPane.showMessageDialog(this, "Erro de comunicação com o banco de dados.", "Erro Fatal", 
            JOptionPane.ERROR_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
            e.printStackTrace();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Erro inesperado", e.getMessage(), 
            JOptionPane.ERROR_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
			e.printStackTrace();
		}
	}
	
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
		setSize(LARGURA, ALTURA);
	    setResizable(false);
	    setLocationRelativeTo(null);
	        
	    panelVermelho = new JPanel();
        panelVermelho.setBackground(new Color(179, 13, 36));
		panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelVermelho);
		panelVermelho.setLayout(null);

        final EstilizacaoRedonda.PainelRedondo panelBranco = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaBege,null);
        panelBranco.setFocusable(true);
        panelBranco.requestFocusInWindow();
		panelBranco.setBounds(90, 85, 1098, 505);
		panelVermelho.add(panelBranco);
		panelBranco.setLayout(null);
		
		final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteracao,corPaletaPreto,35);
		botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_20);
		botaoVoltar.setForeground(corPaletaVermelho);
		botaoVoltar.setBackground(new Color(0, 0, 0));
		botaoVoltar.setBounds(1090, 25, 133, 38);
        panelVermelho.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaGestao telaGestao = new TelaGestao();
				telaGestao.setVisible(true);
			}
		});
		
		this.campoEmail = new EstilizacaoRedonda.CaixaTextoRedonda("Digite seu email...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoEmail.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
		campoEmail.setToolTipText("Digite seu texto");
		campoEmail.setBounds(575, 219, 480, 38);
		campoEmail.setColumns(10);
		panelBranco.add(campoEmail);
		
		this.campoLogin = new EstilizacaoRedonda.CaixaTextoRedonda("Digite seu login...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoLogin.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
		campoLogin.setToolTipText("Digite seu texto");
		campoLogin.setBounds(45, 219, 480, 38);
		campoLogin.setColumns(10);
		panelBranco.add(campoLogin);
		
		this.campoSenha = new EstilizacaoRedonda.CaixaSenhaRedonda("Digite sua senha...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoSenha.setToolTipText("Digite seu texto");
		campoSenha.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
		campoSenha.setColumns(10);
		campoSenha.setBounds(45, 332, 480, 38);
		panelBranco.add(campoSenha);
		
		this.CampoConfirmarSenha = new EstilizacaoRedonda.CaixaSenhaRedonda("Confirme sua senha...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		CampoConfirmarSenha.setBounds(575, 332, 480, 38);
		CampoConfirmarSenha.setToolTipText("Digite seu texto");
		CampoConfirmarSenha.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
		CampoConfirmarSenha.setColumns(10);
		panelBranco.add(CampoConfirmarSenha);
		
		JLabel labelCadastro = new JLabel("Cadastro de Usuário");
		labelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
	    labelCadastro.setForeground(corPaletaPreto);
	    labelCadastro.setFont(FontsConstants.MONTSERRAT_BOLD_50);
	    labelCadastro.setBounds(247, 40, 600, 40);
	    panelBranco.add(labelCadastro);
		
		JLabel labelLogin = new JLabel("Login");
	    labelLogin.setForeground(corPaletaPreto);
	    labelLogin.setFont(FontsConstants.MONTSERRAT_BOLD_20);
	    labelLogin.setBounds(250, 180, 60, 40);
	    panelBranco.add(labelLogin);
	    
	    JLabel labelEmail = new JLabel("Email");
	    labelEmail.setForeground(corPaletaPreto);
	    labelEmail.setFont(FontsConstants.MONTSERRAT_BOLD_20);
	    labelEmail.setBounds(785, 180, 60, 40);
	    panelBranco.add(labelEmail);
			
	    JLabel labelSenha = new JLabel("Senha");
	    labelSenha.setForeground(corPaletaPreto);
	    labelSenha.setFont(FontsConstants.MONTSERRAT_BOLD_20);
	    labelSenha.setBounds(247, 300, 70, 32);
	    panelBranco.add(labelSenha);
	    
	    JLabel labelConfirmarSenha = new JLabel("Confirmar Senha");
	    labelConfirmarSenha.setForeground(Color.BLACK);
	    labelConfirmarSenha.setFont(FontsConstants.MONTSERRAT_BOLD_20);
	    labelConfirmarSenha.setBounds(720, 300, 180, 32);
	    panelBranco.add(labelConfirmarSenha);

        cBoxTipoUsuario = new EstilizacaoRedonda.ComboBoxRedondo<>(new String[]{"ADMIN", "ATENDENTE"}, corPaletaBege, corPaletaVermelho, 2, 20);
        cBoxTipoUsuario.setFont(FontsConstants.MONTSERRAT_LIGHT_16);
		cBoxTipoUsuario.setBounds(444, 140, 202, 23);
		panelBranco.add(cBoxTipoUsuario);
		
		JLabel labelTipoUsuario = new JLabel("Tipo de Usuário");
		labelTipoUsuario.setForeground(corPaletaPreto);
		labelTipoUsuario.setFont(FontsConstants.MONTSERRAT_BOLD_20);
		labelTipoUsuario.setBounds(463, 105, 170, 32);
		panelBranco.add(labelTipoUsuario);
		
		this.botaoCriarConta = new EstilizacaoRedonda.BotaoRedondo("Criar Conta",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
		botaoCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		botaoCriarConta.setForeground(corPaletaBege);
		botaoCriarConta.setBackground(new Color(179, 13, 36));
		botaoCriarConta.setBounds(399, 433, 268, 38);
        botaoCriarConta.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        panelBranco.add(botaoCriarConta);
        
        JLabel labelLogo = new JLabel("");
        labelLogo.setIcon(new ImageIcon("src/main/resources/assets/imagens/logoPequena.png"));
        labelLogo.setBounds(996, 394, 90, 99);
        panelBranco.add(labelLogo);
        java.net.URL urlLogo = getClass().getResource("src/main/resources/assets/imagens/logoPequena.png");
		
	}
	
	private void limparCampos() {
		campoLogin.setText("");
		campoSenha.setText("");
		CampoConfirmarSenha.setText("");
		campoEmail.setText("");
	}

}