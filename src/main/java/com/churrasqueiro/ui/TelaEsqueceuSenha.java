package com.churrasqueiro.ui;

import com.churrasqueiro.ui.TelaValidacaoCodigoEsqueceuSenha;
import com.churrasqueiro.business.EsqueceuSenhaController;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontManager;
import com.churrasqueiro.utils.FontsConstants;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaEsqueceuSenha extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel panelVermelho;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private EstilizacaoRedonda.CaixaTextoRedonda campoEmail;
    private JButton botaoEnviarCodigo;
    private JButton botaoVoltar;
    private static final EsqueceuSenhaController EsqueceuSenhaController = new EsqueceuSenhaController();
    private static String token;
    
    public static String getToken() {
    	return token;
    }

    public String getEmail() {
        return campoEmail.getText().trim();
    }

    public void enviarCodigo() {
	    String email = getEmail();
	    
	    try {
	    	token = EsqueceuSenhaController.enviarToken(email);
	    	JOptionPane.showMessageDialog(this, "Código enviado para: " + email, "Código Enviado", JOptionPane.INFORMATION_MESSAGE);
			TelaValidacaoCodigoEsqueceuSenha telaValidacao = new TelaValidacaoCodigoEsqueceuSenha(email);
			telaValidacao.setVisible(true);
			dispose();
			
	    } catch (ControllerException e) {
				 JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Login",
				 JOptionPane.WARNING_MESSAGE);
	    } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Erro de comunicação com o banco de dados.", "Erro Fatal", 
            JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro inesperado", e.getMessage(), 
            JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaEsqueceuSenha frame = new TelaEsqueceuSenha();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaEsqueceuSenha() {
        Color corPaletaVermelho = new Color(179,13,36);
        Color corPaletaBege = new Color(227,202,187);
        Color corPaletaVermelhoInteracao = new Color(200,50,50);
        Color corPaletaVermelhoPressionado = new Color(150,0,0);
        Color corPaletaPreto = new Color(0,0,0);
        Color corPaletaCinza = new Color(140,127,127);

        setTitle("Esqueceu a Senha - Churrasqueiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, LARGURA, ALTURA);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        panelVermelho = new JPanel();
        panelVermelho.setBackground(new Color(179, 13, 36));
        panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelVermelho);
        panelVermelho.setLayout(null);

        final EstilizacaoRedonda.PainelRedondo panelBranco = new EstilizacaoRedonda.PainelRedondo(null, 60, 4, corPaletaBege, null);
        panelBranco.setFocusable(true);
        panelBranco.requestFocusInWindow();
        panelBranco.setBounds(90, 85, 1098, 505);
        panelVermelho.add(panelBranco);
        panelBranco.setLayout(null);

        JLabel labelTitulo = new JLabel("Esqueceu a senha?");
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setForeground(corPaletaPreto);
        labelTitulo.setFont(FontsConstants.MONTSERRAT_BOLD_50);
        labelTitulo.setBounds(290, 125, 508, 45);
        panelBranco.add(labelTitulo);

        JLabel labelInstrucoes = new JLabel("<html><div style='text-align: center;'>Digite seu email para troca a senha. Você irá receber um código<br>no seu email onde deverá colocar na página seguinte.</div><html>");
        labelInstrucoes.setHorizontalAlignment(SwingConstants.CENTER);
        labelInstrucoes.setForeground(corPaletaPreto);
        labelInstrucoes.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelInstrucoes.setBounds(320, 175, 460, 42);
        panelBranco.add(labelInstrucoes);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setForeground(corPaletaPreto);
        labelEmail.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        labelEmail.setBounds(518, 233, 59, 20);
        panelBranco.add(labelEmail);

        this.campoEmail = new EstilizacaoRedonda.CaixaTextoRedonda("Digite seu email...", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoEmail.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
        campoEmail.setBounds(255, 260, 592, 37);
        campoEmail.setColumns(10);
        panelBranco.add(campoEmail);

        this.botaoEnviarCodigo = new EstilizacaoRedonda.BotaoRedondo("Enviar código", corPaletaVermelho, corPaletaVermelhoInteracao, corPaletaVermelhoPressionado, 35);
        botaoEnviarCodigo.setForeground(corPaletaBege);
        botaoEnviarCodigo.setBackground(corPaletaVermelho);
        botaoEnviarCodigo.setBounds(415, 320, 261, 36);
        botaoEnviarCodigo.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        panelBranco.add(botaoEnviarCodigo);
        botaoEnviarCodigo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarCodigo();
            }
        });

        JLabel labelLogo = new JLabel("");
        labelLogo.setIcon(new ImageIcon("src/main/resources/assets/imagens/logoPequena.png"));
        labelLogo.setBounds(996, 394, 90, 99);
        panelBranco.add(labelLogo);


        this.botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar", corPaletaPreto, corPaletaVermelhoInteracao, corPaletaVermelhoPressionado, 35);
        botaoVoltar.setForeground(corPaletaVermelho);
        botaoVoltar.setBackground(corPaletaBege);
        botaoVoltar.setBorderPainted(false);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        botaoVoltar.setBounds(30, 20, 120, 35);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	TelaLogin telaLogin = new TelaLogin();
    			telaLogin.setVisible(true);
    			setVisible(false);
                dispose();
            }
        });
        panelVermelho.add(botaoVoltar);
    }
}