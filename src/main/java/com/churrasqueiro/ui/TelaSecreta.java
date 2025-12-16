package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.churrasqueiro.utils.FontsConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaSecreta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    
    
    Color corPaletaVermelho = new Color(179,13,36);
    Color corPaletaBege = new Color(227,202,187);
    Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteracao = new Color(35,35,35);
    Color corPaletaCinza = new Color(140,127,127);
    Color corAzulPersonalizado = new Color(129, 161, 218);
    Color corAzulPersonalizadoPressionado = new Color(107, 167, 255);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSecreta frame = new TelaSecreta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaSecreta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Equipe Pokemon - Churrasqueiro");
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(17, 17, 20));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

        final EstilizacaoRedonda.PainelRedondo panel = new EstilizacaoRedonda.PainelRedondo(null,60,4,corAzulPersonalizado,null);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
		panel.setBounds(90, 85, 1098, 545);
		contentPane.add(panel);
		panel.setLayout(null);
		
        int larguraPikachu = 256;
        int alturaPikachu = 256;
        JLabel labelPikachu = new JLabel("");
        labelPikachu.setIcon(new ImageIcon(TelaLogin.class.getResource("/assets/imagens/pikachuSprResized.png")));
        labelPikachu.setBounds(-13, 154, 256, 256);
        panel.add(labelPikachu);
        
        JLabel labelPokebola = new JLabel("");
        labelPokebola.setToolTipText("Pikachu");
        labelPokebola.setIcon(new ImageIcon(TelaLogin.class.getResource("/assets/imagens/pokebolaSpr.png")));
        labelPokebola.setBounds(41, 67, 32, 32);
        labelPokebola.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        TelaSecreta.this,
                        "Pokemon já selecionado.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE
                        );
        	}
        });
        panel.add(labelPokebola);
        
        JLabel labelPokebolaCinza = new JLabel("");
        labelPokebolaCinza.setToolTipText("Sem pokemon");
        labelPokebolaCinza.setIcon(new ImageIcon(TelaLogin.class.getResource("/assets/imagens/pokebolaCinzaSpr.png")));
        labelPokebolaCinza.setBounds(98, 67, 32, 32);
        labelPokebolaCinza.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        TelaSecreta.this,
                        "Pokebola vazia.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE
                        );
        	}
        });
        panel.add(labelPokebolaCinza);
        
        JLabel labelPokebolaCinza2 = new JLabel("");
        labelPokebolaCinza2.setToolTipText("Sem pokemon");
        labelPokebolaCinza2.setIcon(new ImageIcon(TelaSecreta.class.getResource("/assets/imagens/pokebolaCinzaSpr.png")));
        labelPokebolaCinza2.setBounds(155, 67, 32, 32);
        labelPokebolaCinza2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        TelaSecreta.this,
                        "Pokebola vazia.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE
                        );
        	}
        });
        panel.add(labelPokebolaCinza2);
        
        JLabel labelPokebolaCinza3 = new JLabel("");
        labelPokebolaCinza3.setToolTipText("Sem pokemon");
        labelPokebolaCinza3.setIcon(new ImageIcon(TelaSecreta.class.getResource("/assets/imagens/pokebolaCinzaSpr.png")));
        labelPokebolaCinza3.setBounds(41, 111, 32, 32);
        labelPokebolaCinza3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		JOptionPane.showMessageDialog(
        				TelaSecreta.this,
        				"Pokebola vazia.",
        				"Aviso",
        				JOptionPane.INFORMATION_MESSAGE
        				);
        	}
        });
        panel.add(labelPokebolaCinza3);
        
        JLabel labelPokebolaCinza4 = new JLabel("");
        labelPokebolaCinza4.setToolTipText("Sem pokemon");
        labelPokebolaCinza4.setIcon(new ImageIcon(TelaSecreta.class.getResource("/assets/imagens/pokebolaCinzaSpr.png")));
        labelPokebolaCinza4.setBounds(98, 111, 32, 32);
        labelPokebolaCinza4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        TelaSecreta.this,
                        "Pokebola vazia.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE
                        );
        	}
        });
        panel.add(labelPokebolaCinza4);
        
        JLabel labelPokebolaCinza5 = new JLabel("");
        labelPokebolaCinza5.setToolTipText("Sem pokemon");
        labelPokebolaCinza5.setIcon(new ImageIcon(TelaSecreta.class.getResource("/assets/imagens/pokebolaCinzaSpr.png")));
        labelPokebolaCinza5.setBounds(155, 110, 32, 32);
        labelPokebolaCinza5.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(
                        TelaSecreta.this,
                        "Pokebola vazia.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE
                        );
        	}
        });
        panel.add(labelPokebolaCinza5);
        
        JLabel labelNomePikachu = new JLabel("Pikachu");
        labelNomePikachu.setForeground(corPaletaPreto);
        labelNomePikachu.setFont(FontsConstants.MONTSERRAT_BOLD_30);
        labelNomePikachu.setBounds(244, 162, 162, 56);
        panel.add(labelNomePikachu);
        
        JPanel panelLinhaVertical = new JPanel();
        panelLinhaVertical.setBackground(new Color(17, 17, 20));
        panelLinhaVertical.setBounds(229, 154, 2, 256);
        panel.add(panelLinhaVertical);
        
        JLabel labelTipoPikachu = new JLabel("Tipo: Elétrico");
        labelTipoPikachu.setForeground(Color.BLACK);
        labelTipoPikachu.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelTipoPikachu.setBounds(244, 200, 184, 32);
        panel.add(labelTipoPikachu);
        
        JLabel labelEspciePikachu = new JLabel("Espécie: Rato Pokemon");
        labelEspciePikachu.setForeground(Color.BLACK);
        labelEspciePikachu.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelEspciePikachu.setBounds(244, 222, 184, 32);
        panel.add(labelEspciePikachu);
        
        JLabel labelCartaxoSpr = new JLabel("");
        labelCartaxoSpr.setIcon(new ImageIcon(TelaLogin.class.getResource("/assets/imagens/cartaxoSprResized.png")));
        labelCartaxoSpr.setBounds(564, 154, 256, 256);
        panel.add(labelCartaxoSpr);
        
        JPanel panelLinhaVertical2 = new JPanel();
        panelLinhaVertical2.setBackground(new Color(17, 17, 20));
        panelLinhaVertical2.setBounds(229, 67, 2, 76);
        panel.add(panelLinhaVertical2);
        
        JLabel labelSlotsPokemon = new JLabel("1/6 Pokemon");
        labelSlotsPokemon.setForeground(Color.BLACK);
        labelSlotsPokemon.setFont(FontsConstants.MONTSERRAT_BOLD_30);
        labelSlotsPokemon.setBounds(251, 77, 212, 56);
        panel.add(labelSlotsPokemon);
        
        JLabel labelAlturaPikachu = new JLabel("Altura: 0.4m");
        labelAlturaPikachu.setForeground(Color.BLACK);
        labelAlturaPikachu.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelAlturaPikachu.setBounds(244, 243, 184, 32);
        panel.add(labelAlturaPikachu);
        
        JLabel labelPesoPikachu = new JLabel("Peso: 6.0kg");
        labelPesoPikachu.setForeground(Color.BLACK);
        labelPesoPikachu.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelPesoPikachu.setBounds(244, 263, 184, 32);
        panel.add(labelPesoPikachu);
        
        JLabel labelHabilidadePikachu = new JLabel("Habilidades: 'Static'");
        labelHabilidadePikachu.setForeground(Color.BLACK);
        labelHabilidadePikachu.setFont(new Font("Montserrat", Font.PLAIN, 15));
        labelHabilidadePikachu.setBounds(244, 282, 184, 32);
        panel.add(labelHabilidadePikachu);
        
        JLabel labelFontePikachu = new JLabel("Fonte: The Spriter Resource");
        labelFontePikachu.setForeground(Color.BLACK);
        labelFontePikachu.setFont(new Font("Montserrat", Font.PLAIN, 15));
        labelFontePikachu.setBounds(244, 370, 217, 32);
        panel.add(labelFontePikachu);
        
        JLabel labelInfoRedimensionado = new JLabel("OBS: Imagens redimensionadas em 400.00%.");
        labelInfoRedimensionado.setForeground(Color.BLACK);
        labelInfoRedimensionado.setFont(new Font("Montserrat", Font.PLAIN, 15));
        labelInfoRedimensionado.setBounds(10, 508, 343, 26);
        panel.add(labelInfoRedimensionado);
        
        JPanel panelLinhaVertica3 = new JPanel();
        panelLinhaVertica3.setBackground(new Color(17, 17, 20));
        panelLinhaVertica3.setBounds(832, 154, 2, 256);
        panel.add(panelLinhaVertica3);
        
        JLabel labelTreinador = new JLabel("Treinador");
        labelTreinador.setForeground(Color.BLACK);
        labelTreinador.setFont(FontsConstants.MONTSERRAT_BOLD_30);
        labelTreinador.setBounds(843, 162, 233, 56);
        panel.add(labelTreinador);
        
        JLabel labelBrunoCartaxo = new JLabel("Nome: Bruno Cartaxo");
        labelBrunoCartaxo.setForeground(Color.BLACK);
        labelBrunoCartaxo.setFont(new Font("Montserrat", Font.PLAIN, 15));
        labelBrunoCartaxo.setBounds(843, 202, 184, 32);
        panel.add(labelBrunoCartaxo);
        
        JLabel labelProfissao = new JLabel("Profissão: Professor");
        labelProfissao.setForeground(Color.BLACK);
        labelProfissao.setFont(FontsConstants.MONTSERRAT_REGULAR_15);
        labelProfissao.setBounds(843, 227, 184, 32);
        panel.add(labelProfissao);
        
        JLabel labelFonteCartaxo = new JLabel("Fonte: Autores (David)");
        labelFonteCartaxo.setForeground(Color.BLACK);
        labelFonteCartaxo.setFont(new Font("Montserrat", Font.PLAIN, 15));
        labelFonteCartaxo.setBounds(847, 370, 217, 32);
        panel.add(labelFonteCartaxo);
        
        final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corAzulPersonalizado,corAzulPersonalizadoPressionado,corAzulPersonalizado,35);
        botaoVoltar.setBounds(1038, 28, 133, 38);
        contentPane.add(botaoVoltar);
        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        botaoVoltar.setForeground(corPaletaPreto);
        botaoVoltar.setBackground(new Color(0, 0, 0));
        
        JLabel labelEquipe = new JLabel("Equipe Pokemon");
        labelEquipe.setBounds(90, 28, 412, 56);
        contentPane.add(labelEquipe);
        labelEquipe.setForeground(corAzulPersonalizado);
        labelEquipe.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        botaoVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		TelaGestao telaGestao = new TelaGestao();
        		telaGestao.setVisible(true);
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
