package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class TelaRelatorios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorios frame = new TelaRelatorios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaRelatorios() {
		
		Color corPaletaVermelho = new Color(179,13,36);
        Color corPaletaBege = new Color(227,202,187);
        Color corPaletaVermelhoInteracao = new Color(200,50,50);
        Color corPaletaVermelhoPressionado = new Color(150,0,0);
        Color corPaletaPreto = new Color(0,0,0);
        Color corPaletaPretoInteração = new Color(35,35,35);
        Color corPaletaCinza = new Color(140,127,127);
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Relatórios - Churrasqueiro");
        setResizable(false);
        setSize(LARGURA, ALTURA);
        
        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
        
        contentPane.setBackground(new Color(179, 13, 36));
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 102, 1266, 581);
        panel.setBackground(new Color(227,202,187));
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel vendasLabel = new JLabel("Vendas");
        vendasLabel.setForeground(corPaletaPreto);
        vendasLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        vendasLabel.setBounds(145, 101, 173, 45);
        panel.add(vendasLabel);
        
        JLabel labelValorVendas = new JLabel("R$12.911,34");
        labelValorVendas.setBounds(97, 145, 277, 51);
        labelValorVendas.setForeground(corPaletaVermelho);
        labelValorVendas.setFont(new Font("SansSerif", Font.BOLD, 45));
        panel.add(labelValorVendas);
        
        JComboBox vendasComboBox = new JComboBox();
        vendasComboBox.setBounds(427, 122, 124, 20);
        panel.add(vendasComboBox);
        
        JLabel maisVendidosLabel = new JLabel("Mais Vendido");
        maisVendidosLabel.setForeground(Color.BLACK);
        maisVendidosLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        maisVendidosLabel.setBounds(747, 101, 253, 45);
        panel.add(maisVendidosLabel);
        
        JLabel itemMaisVendidoLabel = new JLabel("Triple Smash");
        itemMaisVendidoLabel.setForeground(new Color(179, 13, 36));
        itemMaisVendidoLabel.setFont(new Font("SansSerif", Font.BOLD, 45));
        itemMaisVendidoLabel.setBounds(723, 145, 361, 51);
        panel.add(itemMaisVendidoLabel);
        
        JComboBox maisVendidoComboBox = new JComboBox();
        maisVendidoComboBox.setBounds(1123, 122, 119, 20);
        panel.add(maisVendidoComboBox);
        
        String[] opcoes = {"HOJE", "ÚLTIMA SEMANA", "ÚLTIMO MÊS", "ÚLTIMO ANO"};

        for (String item : opcoes) {
            vendasComboBox.addItem(item);
            maisVendidoComboBox.addItem(item);
        }
        
        JLabel unidadesLabel = new JLabel("17 Unidades");
        unidadesLabel.setBounds(916, 195, 112, 28);
        panel.add(unidadesLabel);
        unidadesLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JLabel caixaLabel = new JLabel("Caixa");
        caixaLabel.setForeground(Color.BLACK);
        caixaLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        caixaLabel.setBounds(201, 368, 173, 45);
        panel.add(caixaLabel);
        
        JLabel caixaHojeLabel = new JLabel("Caixa hoje:");
        caixaHojeLabel.setBounds(85, 439, 124, 28);
        panel.add(caixaHojeLabel);
        caixaHojeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        
        JLabel caixaComecouLabel = new JLabel("Caixa começou:");
        caixaComecouLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        caixaComecouLabel.setBounds(333, 439, 158, 28);
        panel.add(caixaComecouLabel);
        
        JLabel valorCaixaHojeLabel = new JLabel("R$5.555,55");
        valorCaixaHojeLabel.setForeground(new Color(179, 13, 36));
        valorCaixaHojeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        valorCaixaHojeLabel.setBounds(85, 450, 131, 51);
        panel.add(valorCaixaHojeLabel);
        
        JLabel valorCaixaHojeLabel_1 = new JLabel("5,55");
        valorCaixaHojeLabel_1.setForeground(new Color(179, 13, 36));
        valorCaixaHojeLabel_1.setFont(new Font("SansSerif", Font.BOLD, 20));
        valorCaixaHojeLabel_1.setBounds(343, 450, 131, 51);
        panel.add(valorCaixaHojeLabel_1);
        
        JLabel setaLabel = new JLabel("→");
        setaLabel.setFont(new Font("SansSerif", Font.BOLD, 45));
        setaLabel.setForeground(corPaletaVermelho);
        setaLabel.setBounds(235, 452, 44, 28);
        panel.add(setaLabel);
        
        JLabel metaFaturamentoLabel = new JLabel("Meta de Faturamento");
        metaFaturamentoLabel.setForeground(Color.BLACK);
        metaFaturamentoLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        metaFaturamentoLabel.setBounds(747, 368, 383, 45);
        panel.add(metaFaturamentoLabel);
        
        JLabel valorMetaFaturamentoLabel = new JLabel("R$25.000,00");
        valorMetaFaturamentoLabel.setForeground(new Color(179, 13, 36));
        valorMetaFaturamentoLabel.setFont(new Font("SansSerif", Font.BOLD, 45));
        valorMetaFaturamentoLabel.setBounds(782, 429, 277, 51);
        panel.add(valorMetaFaturamentoLabel);
        
        JLabel deHojeLabel = new JLabel("de hoje");
        deHojeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        deHojeLabel.setBounds(1044, 404, 75, 28);
        deHojeLabel.setForeground(corPaletaVermelho);

        panel.add(deHojeLabel);
        
        final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoVoltar.setForeground(new Color(255, 255, 255));
		botaoVoltar.setBackground(new Color(0, 0, 0));
		botaoVoltar.setBounds(1132, 34, 104, 38);
        contentPane.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaGestao telaGestao = new TelaGestao();
				telaGestao.setVisible(true);
				dispose();
			}
		});
        
        JLabel relatoriosLabel = new JLabel("Relatórios");
        relatoriosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        relatoriosLabel.setForeground(corPaletaBege);
        relatoriosLabel.setBounds(487, 34, 208, 38);
        contentPane.add(relatoriosLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/assets/imagens/iconeJanelaPequena.png")));     		
        logoLabel.setBounds(30, 10, 92, 82);
        contentPane.add(logoLabel);
	}
}
