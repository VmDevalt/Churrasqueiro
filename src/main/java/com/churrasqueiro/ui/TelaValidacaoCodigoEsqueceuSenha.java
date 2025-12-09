package com.churrasqueiro.ui;

import com.churrasqueiro.utils.FontsConstants;
import com.churrasqueiro.business.EsqueceuSenhaController;
import com.churrasqueiro.data.UsuarioDAO;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import java.util.Optional;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaValidacaoCodigoEsqueceuSenha extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelVermelho;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;
	private EstilizacaoRedonda.CaixaTextoRedonda campoCodigo;
	private String emailUsuario;
	private String codigoGerado;
	private static final EsqueceuSenhaController esqueceuSenhaController = new EsqueceuSenhaController();

	public TelaValidacaoCodigoEsqueceuSenha(String email) {
		this.emailUsuario = email;
		try{
			Optional<Usuario> usuarioOpt = UsuarioDAO.buscarLoginViaEmail(email);
			this.codigoGerado = usuarioOpt.get().getTokenRecuperacao();
		}catch(DatabaseException e) {
	    	JOptionPane.showMessageDialog(this,
	    			"Falha de comunicação com o banco de dados.",
	    			"Erro",
	    			JOptionPane.ERROR_MESSAGE);
	    			e.printStackTrace();
	    	this.codigoGerado = TelaEsqueceuSenha.getToken();
		}
		initialize();
	}
	
	private String getCodigo() {
		return campoCodigo.getText().trim();
	}
	
	public void verificarCodigo() {
		String codigoDigitado = getCodigo();
		try {
			esqueceuSenhaController.validarCodigo(codigoDigitado, codigoGerado);
			JOptionPane.showMessageDialog(this,
					"Os códigos coincidem.",
					"Sucesso!",
					JOptionPane.INFORMATION_MESSAGE);
			TelaNovaSenha telaNovaSenha = new TelaNovaSenha(emailUsuario);
			telaNovaSenha.setVisible(true);
			dispose();
	    } catch (ControllerException e) {
			 JOptionPane.showMessageDialog(this, e.getMessage(), "Erro de Login",
			 JOptionPane.WARNING_MESSAGE);
			 e.printStackTrace();
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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaValidacaoCodigoEsqueceuSenha frame = new TelaValidacaoCodigoEsqueceuSenha("usuario@exemplo.com");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaValidacaoCodigoEsqueceuSenha() {
		initialize();
	}

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
        labelTitulo.setFont(FontsConstants.MONTSERRAT_EXTRABOLD_50);
        labelTitulo.setBounds(295, 115, 508, 55);
        panelBranco.add(labelTitulo);
        
        JLabel labelInstrucoes = new JLabel("<html><div style='text-align: center;'>Acabamos de enviar o código. Confira na sua caixa de entrada<br>e digite ele aqui embaixo.</div></html>");
        labelInstrucoes.setHorizontalAlignment(SwingConstants.CENTER);
        labelInstrucoes.setForeground(corPaletaPreto);
        labelInstrucoes.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelInstrucoes.setBounds(321, 170, 460, 42);
        panelBranco.add(labelInstrucoes);
        
        JLabel labelEmailInfo = new JLabel("Email: " + emailUsuario);
        labelEmailInfo.setHorizontalAlignment(SwingConstants.CENTER);
        labelEmailInfo.setForeground(corPaletaCinza);
        labelEmailInfo.setFont(FontsConstants.MONTSERRAT_REGULAR_13);
        labelEmailInfo.setBounds(349, 215, 400, 20);
        panelBranco.add(labelEmailInfo);
        
        this.campoCodigo = new EstilizacaoRedonda.CaixaTextoRedonda(null, corPaletaVermelho, corPaletaBege, corPaletaPreto, 2, 35);
        campoCodigo.setHorizontalAlignment(SwingConstants.CENTER);
        campoCodigo.setFont(FontsConstants.MONTSERRAT_EXTRABOLD_50);
        campoCodigo.setBounds(394, 240, 300, 70);
        campoCodigo.setColumns(10);
        panelBranco.add(campoCodigo);
        
        JButton botaoVerificar = new EstilizacaoRedonda.BotaoRedondo("Verificar Código", corPaletaVermelho, corPaletaVermelhoInteracao, corPaletaVermelhoPressionado, 35);
        botaoVerificar.setForeground(corPaletaBege);
        botaoVerificar.setBackground(corPaletaVermelho);
        botaoVerificar.setBounds(445, 320, 200, 38);
        botaoVerificar.setFont(FontsConstants.MONTSERRAT_BOLD_17);
        botaoVerificar.addActionListener(e -> verificarCodigo());
        panelBranco.add(botaoVerificar);
        
        JButton botaoReenviar = new EstilizacaoRedonda.BotaoRedondo("Reenviar Código", corPaletaPreto, corPaletaCinza, corPaletaVermelhoPressionado, 25);
        botaoReenviar.setForeground(corPaletaBege);
        botaoReenviar.setBackground(corPaletaPreto);
        botaoReenviar.setBorderPainted(false);
        botaoReenviar.setFocusPainted(false);
        botaoReenviar.setFont(FontsConstants.MONTSERRAT_BOLD_13);
        botaoReenviar.setBounds(470, 370, 150, 25);
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
        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        botaoVoltar.setBounds(35, 30, 120, 35);
        botaoVoltar.addActionListener(e -> {
            dispose();
            new TelaEsqueceuSenha().setVisible(true);
        });
        panelVermelho.add(botaoVoltar);
	}

	private void reenviarCodigo() {
		try {
    		esqueceuSenhaController.enviarToken(emailUsuario);
			Optional<Usuario> usuarioOpt = UsuarioDAO.buscarLoginViaEmail(emailUsuario);
			codigoGerado = usuarioOpt.get().getTokenRecuperacao();
        	JOptionPane.showMessageDialog(this,
        			"Novo código enviado para: " + emailUsuario,
        			"Código Reenviado!",
        			JOptionPane.INFORMATION_MESSAGE);
		} catch(ControllerException e) {
	    	JOptionPane.showMessageDialog(this,
	    			e.getMessage(),
	    			"Erro no envio",
	    			JOptionPane.ERROR_MESSAGE);
		} catch (DatabaseException e) {
	    	JOptionPane.showMessageDialog(this,
	    			e.getMessage(),
	    			"Erro no banco de dados",
	    			JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
	    	JOptionPane.showMessageDialog(this,
	    			"Erro inesperado.",
	    			"Erro fatal",
	    			JOptionPane.ERROR_MESSAGE);
		}
    }
}