package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaCriarGrupo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private EstilizacaoRedonda.CaixaTextoRedonda campoNomeGrupo;
    private EstilizacaoRedonda.CaixaTextoRedonda campoDescricaoGrupo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarGrupo frame = new TelaCriarGrupo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
    Color corPaletaBege = new Color(227,202,187);
    Color corPaletaVermelho = new Color(179,13,36);
    Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaBranco = new Color(255,255,255);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteração = new Color(35,35,35);
    Color corPaletaCinza = new Color(140,127,127);

	public TelaCriarGrupo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(corPaletaVermelho);
		
		java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
        contentPane.setLayout(null);
        
        JLabel gruposLabel = new JLabel("Grupos");
        gruposLabel.setBounds(539, 34, 208, 38);
        gruposLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        gruposLabel.setForeground(corPaletaBege);
        contentPane.add(gruposLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(30, 10, 92, 82);
        logoLabel.setIcon(new ImageIcon(TelaCriarGrupo.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        contentPane.add(logoLabel);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 102, 1266, 581);
        panel.setBackground(corPaletaBege);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel criarGrupoLabel = new JLabel("Criar Grupo");
        criarGrupoLabel.setBounds(504, 27, 353, 68);
        panel.add(criarGrupoLabel);
        criarGrupoLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        criarGrupoLabel.setForeground(corPaletaVermelho);
        
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(65, 145, 187, 32);
        panel.add(nomeLabel);
        nomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        nomeLabel.setForeground(corPaletaPreto);
        
        this.campoNomeGrupo = new EstilizacaoRedonda.CaixaTextoRedonda("Digite seu email...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
        campoNomeGrupo.setFont(new Font("SansSerif", Font.BOLD, 14));
		campoNomeGrupo.setToolTipText("Digite o nome do Grupo");
		campoNomeGrupo.setBounds(65, 280, 1135, 38);
		panel.add(campoNomeGrupo);
		campoNomeGrupo.setColumns(10);
		
		this.campoDescricaoGrupo = new EstilizacaoRedonda.CaixaTextoRedonda("Digite seu email...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoDescricaoGrupo.setFont(new Font("SansSerif", Font.BOLD, 14));
		campoDescricaoGrupo.setToolTipText("Digite a descrição do Grupo");
		campoDescricaoGrupo.setBounds(65, 179, 1135, 38);
		panel.add(campoDescricaoGrupo);
		campoDescricaoGrupo.setColumns(10);
		
		JLabel descricaoLabel = new JLabel("Descrição");
		descricaoLabel.setForeground(Color.BLACK);
		descricaoLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		descricaoLabel.setBounds(65, 246, 187, 32);
		panel.add(descricaoLabel);
		
		final EstilizacaoRedonda.BotaoRedondo botaoAdicionarGrupo = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoAdicionarGrupo.setText("Adicionar Grupo");
		botaoAdicionarGrupo.setBounds(504, 445, 205, 38);
		panel.add(botaoAdicionarGrupo);
		botaoAdicionarGrupo.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoAdicionarGrupo.setForeground(corPaletaBege);
		botaoAdicionarGrupo.setBackground(new Color(0, 0, 0));
		
		final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoVoltar.setBounds(1128, 41, 104, 38);
		contentPane.add(botaoVoltar);
		botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoVoltar.setForeground(new Color(255, 255, 255));
		botaoVoltar.setBackground(new Color(0, 0, 0));
	}
}
