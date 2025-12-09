package com.churrasqueiro.ui;

import com.churrasqueiro.utils.FontsConstants;
import com.churrasqueiro.business.EsqueceuSenhaController;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaNovaSenha extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panelVermelho;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private EstilizacaoRedonda.CaixaSenhaRedonda campoSenha;
    private EstilizacaoRedonda.CaixaSenhaRedonda campoConfirmarSenha;
    private String emailUsuario;
    private static final EsqueceuSenhaController esqueceuSenhaController = new EsqueceuSenhaController();
    
    public TelaNovaSenha(String email) {
        emailUsuario = email;
        initialize();
    }
    
    public String getSenha() {
        return new String(campoSenha.getPassword());
    }
    
    public String getConfirmarSenha() {
        return new String(campoConfirmarSenha.getPassword());
    }
    
    public void salvarSenha() {
        String senha = getSenha();
        String confirmarSenha = getConfirmarSenha();
        String email = emailUsuario;
        
        try {
        	esqueceuSenhaController.validarCamposDeSenha(senha, confirmarSenha);
        	esqueceuSenhaController.redefinirSenha(senha, email);
			JOptionPane.showMessageDialog(this,
					"Senha atualizada.",
					"Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);
		
			JOptionPane.showMessageDialog(this,
					"Após essa mensagem, você será redirecionado(a) à tela de login.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			
			TelaLogin telaLogin = new TelaLogin();
			telaLogin.setVisible(true);
			dispose();
			
	    } catch (ControllerException e) {
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao redefinir senha",
			 JOptionPane.WARNING_MESSAGE);
			 
	    } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Erro de comunicação com o banco de dados.", "Erro Fatal", 
            JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro inesperado.", 
            JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
        }
    }
    
    private void initialize() {
        Color corPaletaVermelho = new Color(179,13,36);
        Color corPaletaBege = new Color(227,202,187);
        Color corPaletaVermelhoInteracao = new Color(200,50,50);
        Color corPaletaVermelhoPressionado = new Color(150,0,0);
        Color corPaletaPreto = new Color(0,0,0);
        Color corPaletaCinza = new Color(140,127,127);

        setTitle("Nova Senha - Churrasqueiro");
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
        
        JLabel labelTitulo = new JLabel("Nova Senha");
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setForeground(corPaletaPreto);
        labelTitulo.setFont(FontsConstants.MONTSERRAT_EXTRABOLD_50);
        labelTitulo.setBounds(298, 110, 508, 55);
        panelBranco.add(labelTitulo);
        
        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setForeground(corPaletaPreto);
        labelSenha.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        labelSenha.setBounds(515, 180, 100, 32);
        panelBranco.add(labelSenha);
        
        this.campoSenha = new EstilizacaoRedonda.CaixaSenhaRedonda("Digite sua nova senha...", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoSenha.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
        campoSenha.setBounds(379, 210, 340, 38);
        campoSenha.setColumns(10);
        panelBranco.add(campoSenha);
        
        JLabel labelConfirmarSenha = new JLabel("Confirmar Senha");
        labelConfirmarSenha.setForeground(corPaletaPreto);
        labelConfirmarSenha.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        labelConfirmarSenha.setBounds(462, 250, 200, 45);
        panelBranco.add(labelConfirmarSenha);
        
        this.campoConfirmarSenha = new EstilizacaoRedonda.CaixaSenhaRedonda("Digite a mesma senha...", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoConfirmarSenha.setFont(FontsConstants.MONTSERRAT_LIGHT_10);

        campoConfirmarSenha.setBounds(379, 290, 340, 38);
        campoConfirmarSenha.setColumns(10);
        panelBranco.add(campoConfirmarSenha);
        
        JButton botaoSalvar = new EstilizacaoRedonda.BotaoRedondo("Salvar Senha", corPaletaVermelho, corPaletaVermelhoInteracao, corPaletaVermelhoPressionado, 35);
        botaoSalvar.setForeground(corPaletaBege);
        botaoSalvar.setBackground(corPaletaVermelho);
        botaoSalvar.setBounds(429, 350, 240, 38);
        botaoSalvar.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        botaoSalvar.addActionListener(e -> salvarSenha());
        panelBranco.add(botaoSalvar);
        
        JLabel labelLogo = new JLabel("");
        labelLogo.setIcon(new ImageIcon("src/main/resources/assets/imagens/logoPequena.png"));
        labelLogo.setBounds(996, 394, 90, 99);
        panelBranco.add(labelLogo);
        
        JButton botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar", corPaletaPreto, corPaletaVermelhoInteracao, corPaletaVermelhoPressionado, 35);
        botaoVoltar.setForeground(corPaletaBege);
        botaoVoltar.setBackground(corPaletaVermelho);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        botaoVoltar.setBounds(35, 30, 120, 35);
        botaoVoltar.addActionListener(e -> {
            dispose();
            
            new TelaEsqueceuSenha().setVisible(true);
        });
        panelVermelho.add(botaoVoltar);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaNovaSenha frame = new TelaNovaSenha("usuario@exemplo.com");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}