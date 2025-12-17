package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.churrasqueiro.business.CaixaController;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.utils.FontsConstants;

public class TelaMenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaMenuPrincipal frame = new TelaMenuPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    Color corPaletaBege = new Color(227, 202, 187);
    Color corPaletaVermelho = new Color(179, 13, 36);
    Color corPaletaVermelhoInteracao = new Color(200, 50, 50);
    Color corPaletaVermelhoPressionado = new Color(150, 0, 0);
    Color corPaletaPreto = new Color(0, 0, 0);
    Color corPaletaCinza = new Color(150, 150, 150);

    public TelaMenuPrincipal() {
    	Usuario usuarioLogado = TelaLogin.getUsuarioLogado();
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        setResizable(false);
        setTitle("Menu Principal - Churrasqueiro");
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(corPaletaBege);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (Exception ignored) {}
        }

        final EstilizacaoRedonda.BotaoRedondo botaoPedidos =
            new EstilizacaoRedonda.BotaoRedondo(
                    "Pedidos",
                    corPaletaVermelho,
                    corPaletaVermelhoInteracao,
                    corPaletaVermelhoPressionado,
                    35
            );

        botaoPedidos.setForeground(corPaletaBege);
        botaoPedidos.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        botaoPedidos.setBounds(73, 269, 288, 335);
        contentPane.add(botaoPedidos);

        botaoPedidos.setEnabled(false);
        botaoPedidos.setBackground(corPaletaCinza);
        botaoPedidos.setToolTipText("Abra o caixa para habilitar os pedidos.");

        try {
            CaixaController caixaController = new CaixaController();
            boolean existeAberto = caixaController.buscarCaixaAberto().isPresent();

            if (existeAberto) {
                botaoPedidos.setEnabled(true);
                botaoPedidos.setBackground(corPaletaVermelho);
                botaoPedidos.setToolTipText(null);
            }

        } catch (DatabaseException ex) {
            botaoPedidos.setToolTipText("Erro ao verificar caixa.");
            ex.printStackTrace();
        }

        botaoPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!botaoPedidos.isEnabled()) {
                    return;
                }

                TelaPedidos telaPedidos = new TelaPedidos();
                telaPedidos.setVisible(true);
                dispose();
            }
        });
        
        if(usuarioLogado.getTipo().trim().equalsIgnoreCase("ADMIN")) {
	        final EstilizacaoRedonda.BotaoRedondo botaoDashboard =
	            new EstilizacaoRedonda.BotaoRedondo("Dashboard",
	                    corPaletaVermelho, corPaletaVermelhoInteracao,
	                    corPaletaVermelhoPressionado, 35);
	        botaoDashboard.setForeground(corPaletaBege);
	        botaoDashboard.setFont(FontsConstants.MONTSERRAT_BOLD_40);
	        botaoDashboard.setBounds(885, 269, 288, 335);
	        contentPane.add(botaoDashboard);
	        botaoDashboard.addActionListener(e -> {
	            dispose();
	            new TelaDashboard().setVisible(true);
	        });
	
	        final EstilizacaoRedonda.BotaoRedondo botaoGestao =
	            new EstilizacaoRedonda.BotaoRedondo("Gestão",
	                    corPaletaVermelho, corPaletaVermelhoInteracao,
	                    corPaletaVermelhoPressionado, 35);
	        botaoGestao.setForeground(corPaletaBege);
	        botaoGestao.setFont(FontsConstants.MONTSERRAT_BOLD_40);
	        botaoGestao.setBounds(482, 269, 288, 335);
	        contentPane.add(botaoGestao);
	        botaoGestao.addActionListener(e -> {
	            dispose();
	            new TelaGestao().setVisible(true);
	        });
        } else {
	        final EstilizacaoRedonda.BotaoRedondo botaoDashboard =
		            new EstilizacaoRedonda.BotaoRedondo("Dashboard",
		            		corPaletaCinza, new Color(172, 164, 164),
		            		corPaletaVermelhoPressionado, 35);
		        botaoDashboard.setForeground(corPaletaBege);
		        botaoDashboard.setFont(FontsConstants.MONTSERRAT_BOLD_40);
		        botaoDashboard.setBounds(885, 269, 288, 335);
		        contentPane.add(botaoDashboard);
		        botaoDashboard.addActionListener(e -> {
		        	JOptionPane.showMessageDialog(this, "Área restrita para Administradores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		        });
		
		        final EstilizacaoRedonda.BotaoRedondo botaoGestao =
		            new EstilizacaoRedonda.BotaoRedondo("Gestão",
		            		corPaletaCinza, new Color(172, 164, 164),
		            		corPaletaVermelhoPressionado, 35);
		        botaoGestao.setForeground(corPaletaBege);
		        botaoGestao.setFont(FontsConstants.MONTSERRAT_BOLD_40);
		        botaoGestao.setBounds(482, 269, 288, 335);
		        contentPane.add(botaoGestao);
		        botaoGestao.addActionListener(e -> {
		        	JOptionPane.showMessageDialog(this, "Área restrita para Administradores.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		        });
        }

        final EstilizacaoRedonda.BotaoRedondo botaoSair =
            new EstilizacaoRedonda.BotaoRedondo(
                "Sair", corPaletaPreto, new Color(50, 50, 50), corPaletaPreto, 35
            );
        botaoSair.setForeground(corPaletaVermelho);
        botaoSair.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        botaoSair.setBounds(1133, 39, 83, 38);
        contentPane.add(botaoSair);

        botaoSair.addActionListener(e -> {
            UIManager.put("OptionPane.background", corPaletaBege);
            UIManager.put("Panel.background", corPaletaBege);
            UIManager.put("OptionPane.messageForeground", corPaletaVermelho);

            int escolha = JOptionPane.showConfirmDialog(
                    TelaMenuPrincipal.this,
                    "Deseja realmente sair e voltar ao login?",
                    "Sair",
                    JOptionPane.YES_NO_OPTION
            );

            if (escolha == JOptionPane.YES_OPTION) {
                dispose();
                new TelaLogin().setVisible(true);
            }
        });

        JLabel labelEscolha = new JLabel("Escolha uma opção");
        labelEscolha.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        labelEscolha.setForeground(corPaletaPreto);
        labelEscolha.setBounds(437, 178, 523, 58);
        contentPane.add(labelEscolha);

        JLabel labelLogo = new JLabel("");
        labelLogo.setBounds(590, 22, 265, 165);
        contentPane.add(labelLogo);

        ImageIcon iconLogo = null;
        int larguraLogo = 80;
        int alturaLogo = 80;

        java.net.URL urlLogo = getClass().getResource("/assets/imagens/logo.png");
        if (urlLogo != null) {
            Image originalImage = Toolkit.getDefaultToolkit().getImage(urlLogo);
            Image resizedImage = originalImage.getScaledInstance(larguraLogo, alturaLogo, Image.SCALE_SMOOTH);
            iconLogo = new ImageIcon(resizedImage);
            labelLogo.setIcon(iconLogo);
        }
    }
}
