package com.churrasqueiro.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.churrasqueiro.ui.EstilizacaoRedonda;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class TelaCriarItem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPaneVermelho;
	private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private EstilizacaoRedonda.CaixaTextoRedonda campoNome;
    private EstilizacaoRedonda.CaixaTextoRedonda campoDescricao;
    private EstilizacaoRedonda.CaixaTextoRedonda campoPreco;
    private EstilizacaoRedonda.CaixaTextoRedonda campoFoto;
    private EstilizacaoRedonda.CaixaTextoRedonda campoPrecoVariavel;
    private JLabel NewLabelNome;
    private JLabel NewLabelDescricao;
    private JLabel NewLabelGrupo;
    private JLabel NewLabelFoto;
    private JLabel NewLabelPrecoVariavel;
    private JLabel NewLabelPreco;
    private JComboBox<String> comboBoxGrupos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCriarItem frame = new TelaCriarItem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCriarItem() {

        Color corPaletaVermelho = new Color(179,13,36);
        Color corPaletaBege = new Color(227,202,187);
        Color corPaletaVermelhoInteracao = new Color(200,50,50);
        Color corPaletaVermelhoPressionado = new Color(150,0,0);
        Color corPaletaPreto = new Color(0,0,0);
        Color corPaletaPretoInteração = new Color(35,35,35);
        Color corPaletaCinza = new Color(140,127,127);
        
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setTitle("Criar Item - Churrasqueiro");
		contentPaneVermelho = new JPanel();
		contentPaneVermelho.setBackground(new Color(179, 13, 36));
		contentPaneVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneVermelho);
		contentPaneVermelho.setLayout(null);
		
		JPanel panelClaro = new JPanel();
		panelClaro.setBounds(0, 71, 1264, 663);
		contentPaneVermelho.add(panelClaro);
		panelClaro.setBackground(new Color(227, 202, 187));
		panelClaro.setLayout(null);
		
		JLabel labelCriarItem = new JLabel("Criar item");
		labelCriarItem.setBounds(581, 24, 116, 26);
		labelCriarItem.setForeground(new Color(179, 13, 36));
		labelCriarItem.setFont(new Font("SansSerif", Font.BOLD, 25));
		panelClaro.add(labelCriarItem);
		
		final EstilizacaoRedonda.BotaoRedondo botaoAdicionarItem = new EstilizacaoRedonda.BotaoRedondo("Confirmar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoAdicionarItem.setText("Adicionar Item");
		botaoAdicionarItem.setBounds(542, 474, 178, 38);
		panelClaro.add(botaoAdicionarItem);
		botaoAdicionarItem.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoAdicionarItem.setForeground(new Color(255, 255, 255));
		botaoAdicionarItem.setBackground(new Color(0, 0, 0));
		
		
		
		String[] tiposGrupos = {"Bebida", "combos", "Porções", "Promoções", "Hambúrgueres Gourmet", "Hambúrgueres Especiais", "Hambúrgueres Supremos"};
		this.comboBoxGrupos = new JComboBox<>(tiposGrupos);
		comboBoxGrupos.setBorder(new LineBorder(new Color(179, 13, 36), 1));
		comboBoxGrupos.setForeground(corPaletaPreto);
		comboBoxGrupos.setBackground(corPaletaBege);
		comboBoxGrupos.setFont(new Font("SansSerif", Font.PLAIN, 17));
		comboBoxGrupos.setMaximumRowCount(2);
		comboBoxGrupos.setBounds(100, 269, 486, 38);
		panelClaro.add(comboBoxGrupos);
		
		
		this.campoNome = new EstilizacaoRedonda.CaixaTextoRedonda("Digite o nome...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoNome.setToolTipText("Digite seu texto");
		campoNome.setBounds(90, 88, 1088, 38);
		panelClaro.add(campoNome);
		campoNome.setColumns(10);
		
		this.campoDescricao = new EstilizacaoRedonda.CaixaTextoRedonda("Digite a descrição...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoDescricao.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoDescricao.setToolTipText("Digite seu texto");
		campoDescricao.setBounds(90, 182, 1088, 38);
		panelClaro.add(campoDescricao);
		campoDescricao.setColumns(10);
		
		this.campoPreco = new EstilizacaoRedonda.CaixaTextoRedonda("R$.",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoPreco.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoPreco.setToolTipText("Digite seu texto");
		campoPreco.setBounds(90, 366, 496, 38);
		panelClaro.add(campoPreco);
		campoPreco.setColumns(10);
		
		this.campoFoto = new EstilizacaoRedonda.CaixaTextoRedonda("Adicione uma foto...",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoFoto.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoFoto.setToolTipText("Digite seu texto");
		campoFoto.setBounds(658, 269, 520, 38);
		panelClaro.add(campoFoto);
		campoFoto.setColumns(10);
		
		this.campoPrecoVariavel = new EstilizacaoRedonda.CaixaTextoRedonda("R$.",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoPrecoVariavel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		campoPrecoVariavel.setToolTipText("Digite seu texto");
		campoPrecoVariavel.setBounds(658, 366, 520, 38);
		panelClaro.add(campoPrecoVariavel);
		campoPrecoVariavel.setColumns(10);
		
		NewLabelNome = new JLabel("NOME");
		NewLabelNome.setFont(new Font("SansSerif", Font.BOLD, 15));
		NewLabelNome.setBounds(114, 63, 46, 14);
		panelClaro.add(NewLabelNome);
		
		NewLabelDescricao = new JLabel("DESCRIÇÃO");
		NewLabelDescricao.setFont(new Font("SansSerif", Font.BOLD, 15));
		NewLabelDescricao.setBounds(114, 154, 108, 14);
		panelClaro.add(NewLabelDescricao);
		
		NewLabelGrupo = new JLabel("GRUPO");
		NewLabelGrupo.setFont(new Font("SansSerif", Font.BOLD, 15));
		NewLabelGrupo.setBounds(114, 242, 72, 14);
		panelClaro.add(NewLabelGrupo);
		
		NewLabelFoto = new JLabel("FOTO");
		NewLabelFoto.setFont(new Font("SansSerif", Font.BOLD, 15));
		NewLabelFoto.setBounds(687, 244, 65, 14);
		panelClaro.add(NewLabelFoto);
		
		NewLabelPrecoVariavel = new JLabel("PREÇO \"A PARTIR DE : \"");
		NewLabelPrecoVariavel.setFont(new Font("SansSerif", Font.BOLD, 15));
		NewLabelPrecoVariavel.setBounds(687, 341, 219, 14);
		panelClaro.add(NewLabelPrecoVariavel);
		
		NewLabelPreco = new JLabel("PREÇO");
		NewLabelPreco.setFont(new Font("SansSerif", Font.BOLD, 15));
		NewLabelPreco.setBounds(114, 341, 72, 14);
		panelClaro.add(NewLabelPreco);

		JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(61, 0, 92, 82);
        logoLabel.setIcon(new ImageIcon(TelaCriarGrupo.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        contentPaneVermelho.add(logoLabel);
		
		JLabel labelItem = new JLabel("Itens");
		labelItem.setFont(new Font("SansSerif", Font.BOLD, 25));
		labelItem.setForeground(new Color(255, 255, 255));
		labelItem.setBounds(607, 26, 65, 25);
		contentPaneVermelho.add(labelItem);
		
		final EstilizacaoRedonda.BotaoRedondo botaoSair = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoSair.setBounds(1112, 22, 110, 38);
		contentPaneVermelho.add(botaoSair);
		botaoSair.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoSair.setForeground(new Color(255, 255, 255));
		botaoSair.setBackground(new Color(0, 0, 0));
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaItens telaItens = new TelaItens();
				telaItens.setVisible(true);
			}
		});
		
		java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
		
	}
}
