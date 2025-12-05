package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import com.churrasqueiro.business.CategoriaController;
import com.churrasqueiro.business.ItemCardapioController;
import com.churrasqueiro.entities.Categoria;
import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.exceptions.DatabaseException;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.Image; 
import java.net.URL;

public class TelaItens extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    private JPanel contentPane;
    private JPanel panel;
    private JPanel panelCards;
    private JScrollPane scrollPane;

    private EstilizacaoRedonda.CaixaTextoRedonda campoPesquisa;

    private final Map<Integer, String> mapaCategorias = new HashMap<>();
    private final CategoriaController categoriaController = new CategoriaController();

    private List<ItemCardapio> listaCompletaItens;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaItens frame = new TelaItens();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void carregarMapaCategorias() {
        mapaCategorias.clear();
        try {
            List<Categoria> categorias = categoriaController.listarTodas();
            for (Categoria cat : categorias) {
                mapaCategorias.put(cat.getId(), cat.getNome());
            }
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar categorias essenciais.",
                    "Erro DB",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void carregarCardsDeItens() {
        ItemCardapioController itemController = new ItemCardapioController();
        panelCards.removeAll();
        try {
            listaCompletaItens = itemController.listarTodos();
            exibirItens(listaCompletaItens);
        } catch (DatabaseException ex) {
            System.err.println("Erro ao listar itens: " + ex.getMessage());
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar itens do cardápio: " + ex.getMessage(),
                    "Erro DB",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void exibirItens(List<ItemCardapio> itens) {
        panelCards.removeAll();

        int xPos = 80;
        int yPos = 20;
        final int CARD_WIDTH = 350;
        final int CARD_HEIGHT = 180;
        final int GAP = 30;
        final int COLUNAS = 3;

        int contador = 0;
        int yMax = 0;

        for (ItemCardapio item : itens) {
            JPanel card = criarCardProduto(item);
            card.setBounds(xPos, yPos, CARD_WIDTH, CARD_HEIGHT);
            panelCards.add(card);

            contador++;
            yMax = Math.max(yMax, yPos + CARD_HEIGHT);

            if (contador % COLUNAS == 0) {
                xPos = 80;
                yPos += CARD_HEIGHT + GAP;
            } else {
                xPos += CARD_WIDTH + GAP;
            }
        }

        int alturaTotal = yMax + GAP;
        if (alturaTotal < 400) {
            alturaTotal = 400;
        }
        panelCards.setPreferredSize(new Dimension(1266, alturaTotal));

        panelCards.revalidate();
        panelCards.repaint();
    }

    private void filtrarItens() {
        if (listaCompletaItens == null || listaCompletaItens.isEmpty()) {
            return;
        }

        String texto = campoPesquisa.getText();
        if (texto == null) texto = "";
        String termo = texto.trim().toLowerCase();

        if (termo.isEmpty()) {
            exibirItens(listaCompletaItens);
            return;
        }

        List<ItemCardapio> filtrados = listaCompletaItens.stream()
                .filter(item -> {
                    String nomeItem = item.getNome() != null ? item.getNome().toLowerCase() : "";
                    String nomeCategoria = "";
                    if (mapaCategorias.containsKey(item.getCategoriaId())) {
                        String cat = mapaCategorias.get(item.getCategoriaId());
                        nomeCategoria = cat != null ? cat.toLowerCase() : "";
                    }
                    return nomeItem.contains(termo) || nomeCategoria.contains(termo);
                })
                .collect(Collectors.toCollection(ArrayList::new));

        exibirItens(filtrados);
    }

    private JPanel criarCardProduto(ItemCardapio item) {
    Color corPaletaVermelho = new Color(179, 13, 36);
    Color corPaletaBege = new Color(227, 202, 187);
    Color corTextoCategoria = new Color(40, 40, 40);
    Color corPaletaPreto = new Color(0, 0, 0);
    Color corPaletaPretoInteracao = new Color(35, 35, 35);

    EstilizacaoRedonda.PainelRedondo panelCard =
            new EstilizacaoRedonda.PainelRedondo(null, 60, 4, corPaletaVermelho, null);
    panelCard.setLayout(null);

    String nomeCategoria = mapaCategorias.getOrDefault(item.getCategoriaId(), "Não definida");

    final int ICON_SIZE = 82;
    
    JLabel burguerLabel = new JLabel();
    burguerLabel.setBounds(40, 49, ICON_SIZE, ICON_SIZE); 

    if (item.getFotoUrl() != null && !item.getFotoUrl().isBlank()) {
        try {
            boolean carregado = false;
            Image imagemEscalada = null;
            
            URL imgUrl = getClass().getResource("/assets/imagens/itens/" + item.getFotoUrl());
            if (imgUrl != null) {
                imagemEscalada = new ImageIcon(imgUrl).getImage();
                carregado = true;
            } else {
                String pathRelativo = "src/main/resources/assets/imagens/itens/" + item.getFotoUrl();
                java.io.File arquivo = new java.io.File(pathRelativo);
                
                if (arquivo.exists()) {
                    imagemEscalada = new ImageIcon(arquivo.getAbsolutePath()).getImage();
                    carregado = true;
                }
            }
            if (carregado) {
                imagemEscalada = imagemEscalada.getScaledInstance(
                    ICON_SIZE, 
                    ICON_SIZE, 
                    Image.SCALE_SMOOTH
                ); 
                burguerLabel.setIcon(new ImageIcon(imagemEscalada));
            } else {System.err.println("Imagem não encontrada por Classpath ou caminho absoluto: " + item.getFotoUrl());
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ou escalar imagem: " + item.getFotoUrl() + " Erro: " + e.getMessage());
        }
    }
    panelCard.add(burguerLabel);

    JTextArea nomeLabel = new JTextArea(item.getNome());
    nomeLabel.setBounds(190, 25, 150, 60);
    nomeLabel.setLineWrap(true);
    nomeLabel.setWrapStyleWord(true);
    nomeLabel.setEditable(false);
    nomeLabel.setOpaque(false);
    nomeLabel.setForeground(corPaletaBege);
    nomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    panelCard.add(nomeLabel);

    JLabel precoLabel = new JLabel("R$" + String.format("%.2f", item.getPreco()).replace('.', ','));
    precoLabel.setBounds(190, 80, 150, 26);
    precoLabel.setForeground(corPaletaBege);
    precoLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
    panelCard.add(precoLabel);

    EstilizacaoRedonda.BotaoRedondo botaoEditar =
            new EstilizacaoRedonda.BotaoRedondo(
                    "Editar",
                    corPaletaPreto,
                    corPaletaPretoInteracao,
                    corPaletaPreto,
                    25
            );
    botaoEditar.setFont(new Font("SansSerif", Font.BOLD, 14));
    botaoEditar.setForeground(Color.WHITE);
    botaoEditar.setBounds(190, 145, 120, 26);
    panelCard.add(botaoEditar);
    botaoEditar.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {

            dispose();

            //pulo do gato; passando o item pelo contrutor "itemm"
            TelaEditarItem telaEditar = new TelaEditarItem(item);
            telaEditar.setVisible(true);
        }
    });

    return panelCard;
}

    public TelaItens() {

        Color corPaletaVermelho = new Color(179, 13, 36);
        Color corPaletaBege = new Color(227, 202, 187);
        Color corPaletaVermelhoInteracao = new Color(200, 50, 50);
        Color corPaletaPreto = new Color(0, 0, 0);
        Color corPaletaPretoInteração = new Color(35, 35, 35);
        Color corPaletaCinza = new Color(140, 127, 127);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(corPaletaVermelho);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setTitle("Itens - Churrasqueiro");

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }

        panel = new JPanel();
        panel.setBounds(0, 102, 1266, 581);
        panel.setBackground(corPaletaBege);
        panel.setLayout(null);
        contentPane.add(panel);

        final EstilizacaoRedonda.BotaoRedondo botaoVoltar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Voltar",
                        corPaletaPreto,
                        corPaletaPretoInteração,
                        corPaletaPreto,
                        35
                );
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setBackground(corPaletaPreto);
        botaoVoltar.setBounds(1132, 34, 104, 38);
        contentPane.add(botaoVoltar);
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                TelaGestao telaGestao = new TelaGestao();
                telaGestao.setVisible(true);
                dispose();
            }
        });

        JLabel relatoriosLabel = new JLabel("Itens");
        relatoriosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        relatoriosLabel.setForeground(corPaletaBege);
        relatoriosLabel.setBounds(570, 34, 200, 38);
        contentPane.add(relatoriosLabel);

        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaItens.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        logoLabel.setBounds(30, 10, 92, 82);
        contentPane.add(logoLabel);

        JLabel lupaLabel = new JLabel("");
        lupaLabel.setBounds(1164, 80, 36, 38);
        lupaLabel.setIcon(new ImageIcon(TelaItens.class.getResource("/assets/imagens/lupa.png")));
        panel.add(lupaLabel);

        final EstilizacaoRedonda.BotaoRedondo botaoCriarItem =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Criar Item",
                        corPaletaVermelho,
                        corPaletaVermelhoInteracao,
                        corPaletaVermelho,
                        35
                );
        botaoCriarItem.setBounds(80, 20, 151, 38);
        botaoCriarItem.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoCriarItem.setForeground(Color.WHITE);
        panel.add(botaoCriarItem);
        botaoCriarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
                TelaCriarItem telaCriarItem = new TelaCriarItem();
                telaCriarItem.setVisible(true);
            }
        });

        final EstilizacaoRedonda.BotaoRedondo botaoCriarGrupo =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Criar Grupo",
                        corPaletaPreto,
                        corPaletaPretoInteração,
                        corPaletaPreto,
                        35
                );
        botaoCriarGrupo.setBounds(256, 20, 151, 38);
        botaoCriarGrupo.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoCriarGrupo.setForeground(Color.WHITE);
        panel.add(botaoCriarGrupo);
        botaoCriarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
                TelaCriarGrupo telaCriarGrupo = new TelaCriarGrupo();
                telaCriarGrupo.setVisible(true);
            }
        });
        
        final EstilizacaoRedonda.BotaoRedondo botaoEditarGrupo = new EstilizacaoRedonda.BotaoRedondo("Editar Grupo",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
        botaoEditarGrupo.setBounds(435, 20, 151, 38);
        botaoEditarGrupo.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoEditarGrupo.setForeground(Color.WHITE);
        panel.add(botaoEditarGrupo);
        botaoEditarGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIManager.put("OptionPane.background", corPaletaBege);
		        UIManager.put("Panel.background", corPaletaBege);
		        UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
		        String[] itensGrupos = { "Hamburgueres", "Bebidas", "Acompanhamentos" };
		        JComboBox<String> cBoxItensGrupo = new JComboBox<>(itensGrupos);
		        Object[] opcoes = { "Avançar", "Cancelar" };
		        int escolha = JOptionPane.showOptionDialog(
		            TelaItens.this,"Selecione qual Grupo deseja editar", "Editar Grupo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
		        if (escolha == 0) { 
		            dispose();
		            TelaEditarGrupo telaEditarGrupo = new TelaEditarGrupo();
		            telaEditarGrupo.setVisible(true);
		        }
		    }
		});
       

        this.campoPesquisa = new EstilizacaoRedonda.CaixaTextoRedonda(
                "Digite...",
                corPaletaVermelho,
                corPaletaBege,
                corPaletaCinza,
                2,
                35
        );
        campoPesquisa.setFont(new Font("Calibri", Font.PLAIN, 14));
        campoPesquisa.setToolTipText("Digite seu texto");
        campoPesquisa.setBounds(80, 80, 1135, 38);
        panel.add(campoPesquisa);

        campoPesquisa.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarItens();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarItens();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarItens();
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 150, 1266, 431);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollPane);

        panelCards = new JPanel();
        panelCards.setBackground(corPaletaBege);
        panelCards.setLayout(null);
        scrollPane.setViewportView(panelCards);

        carregarMapaCategorias();
        carregarCardsDeItens();
    }
}
