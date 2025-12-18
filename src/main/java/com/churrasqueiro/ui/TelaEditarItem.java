package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

import com.churrasqueiro.business.CategoriaController;
import com.churrasqueiro.business.ItemCardapioController;
import com.churrasqueiro.entities.Categoria;
import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontsConstants;

public class TelaEditarItem extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    private JPanel contentPaneVermelho;
    private EstilizacaoRedonda.CaixaTextoRedonda campoNome;
    private EstilizacaoRedonda.CaixaTextoRedonda campoDescricao;
    private EstilizacaoRedonda.CaixaTextoRedonda campoPreco;
    private EstilizacaoRedonda.CaixaTextoRedonda campoFoto;
    private EstilizacaoRedonda.CaixaTextoRedonda campoPrecoVariavel;
    private JComboBox<String> comboBoxGrupos;

    private final ItemCardapioController itemCardapioController = new ItemCardapioController();
    private final CategoriaController categoriaController = new CategoriaController();
    private final Map<String, Integer> listaCategorias = new HashMap<>();

    private final ItemCardapio itemEmEdicao;
    
    Color corPaletaVermelho = new Color(179,13,36);
    Color corPaletaBege = new Color(227,202,187);
    Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteracao = new Color(35,35,35);
    Color corPaletaCinza = new Color(140,127,127);
    Color corPaletaBegeInteracao = new Color(245,225,210);
    Color corPaletaBegePressionado = new Color(200,175,160);


    public TelaEditarItem(ItemCardapio item) {
        this.itemEmEdicao = item;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Editar Item - Churrasqueiro");

        contentPaneVermelho = new JPanel();
        contentPaneVermelho.setBackground(corPaletaVermelho);
        contentPaneVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPaneVermelho);
        contentPaneVermelho.setLayout(null);

        JPanel panelClaro = new JPanel();
        panelClaro.setBounds(0, 102, 1264, 663);
        contentPaneVermelho.add(panelClaro);
        panelClaro.setBackground(corPaletaBege);
        panelClaro.setLayout(null);

        JLabel labelEditarItem = new JLabel("Editar item");
        labelEditarItem.setBounds(505, 27, 340, 68);
        labelEditarItem.setForeground(corPaletaVermelho);
        labelEditarItem.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        panelClaro.add(labelEditarItem);

        EstilizacaoRedonda.BotaoRedondo botaoSalvar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Salvar alterações",
                        corPaletaPreto,
                        corPaletaPretoInteracao,
                        corPaletaPreto,
                        35
                );
        botaoSalvar.setBounds(542, 504, 220, 38);
        panelClaro.add(botaoSalvar);
        botaoSalvar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        botaoSalvar.setForeground(corPaletaBege);
        botaoSalvar.setBackground(corPaletaPreto);
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                salvarAlteracoes();
            }
        });

        comboBoxGrupos = new EstilizacaoRedonda.ComboBoxRedondo<>(new String[]{}, corPaletaBege, corPaletaVermelho, 2, 35);
        comboBoxGrupos.setFont(FontsConstants.MONTSERRAT_BOLD_13);
        comboBoxGrupos.setMaximumRowCount(5);
        comboBoxGrupos.setBounds(90, 309, 496, 38);
        panelClaro.add(comboBoxGrupos);

        campoNome = new EstilizacaoRedonda.CaixaTextoRedonda(
                "Digite o nome...",
                corPaletaVermelho,
                corPaletaBege,
                corPaletaCinza,
                2,
                35
        );
        campoNome.setFont(FontsConstants.MONTSERRAT_LIGHT_13);
        campoNome.setToolTipText("Nome do item");
        campoNome.setBounds(90, 128, 1088, 38);
        panelClaro.add(campoNome);
        campoNome.setColumns(10);

        campoDescricao = new EstilizacaoRedonda.CaixaTextoRedonda(
                "Digite a descrição...",
                corPaletaVermelho,
                corPaletaBege,
                corPaletaCinza,
                2,
                35
        );
        campoDescricao.setFont(FontsConstants.MONTSERRAT_LIGHT_13);
        campoDescricao.setToolTipText("Descrição do item");
        campoDescricao.setBounds(90, 222, 1088, 38);
        panelClaro.add(campoDescricao);
        campoDescricao.setColumns(10);

        campoPreco = new EstilizacaoRedonda.CaixaTextoRedonda(
                "R$.",
                corPaletaVermelho,
                corPaletaBege,
                corPaletaCinza,
                2,
                35
        );
        campoPreco.setFont(FontsConstants.MONTSERRAT_LIGHT_13);
        campoPreco.setToolTipText("Preço do item");
        campoPreco.setBounds(90, 406, 496, 38);
        panelClaro.add(campoPreco);
        campoPreco.setColumns(10);
        aplicarMascaraPreco(campoPreco);

        EstilizacaoRedonda.BotaoRedondo botaoSelecionarFoto =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Selecionar",
                        corPaletaPreto,
                        corPaletaPretoInteracao,
                        corPaletaPreto,
                        35
                );
        botaoSelecionarFoto.setFont(FontsConstants.MONTSERRAT_BOLD_14);
        botaoSelecionarFoto.setForeground(Color.WHITE);
        botaoSelecionarFoto.setBounds(1058, 309, 120, 38);
        panelClaro.add(botaoSelecionarFoto);

        campoFoto = new EstilizacaoRedonda.CaixaTextoRedonda(
                "Adicione uma foto...",
                corPaletaVermelho,
                corPaletaBege,
                corPaletaCinza,
                2,
                35
        );
        campoFoto.setFont(FontsConstants.MONTSERRAT_LIGHT_13);
        campoFoto.setToolTipText("Arquivo de imagem");
        campoFoto.setBounds(658, 309, 520, 38);
        panelClaro.add(campoFoto);
        campoFoto.setColumns(10);
        botaoSelecionarFoto.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Selecionar imagem");

                chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Imagens (*.png, *.jpg, *.jpeg)",
                        "png", "jpg", "jpeg"
                ));

                int resultado = chooser.showOpenDialog(null);

                if (resultado == JFileChooser.APPROVE_OPTION) {

                    try {
                        java.io.File arquivoOriginal = chooser.getSelectedFile();
                        String nomeArquivoOriginal = arquivoOriginal.getName();

                        String nomeBase = nomeArquivoOriginal;
                        if (nomeArquivoOriginal.lastIndexOf('.') != -1) {
                            nomeBase = nomeArquivoOriginal.substring(0, nomeArquivoOriginal.lastIndexOf('.'));
                        }
                        String nomeArquivoPng = nomeBase + ".png";

                        campoFoto.setText(nomeArquivoPng);

                        java.nio.file.Path destino = java.nio.file.Paths.get(
                                "src/main/resources/assets/imagens/itens/" + nomeArquivoPng
                        );

                        redimensionarEsalvar(arquivoOriginal, destino, 82, 82);

                        JOptionPane.showMessageDialog(
                                null,
                                "Imagem carregada, redimensionada para 82x82px e salva como PNG!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE
                        );
           			 UIManager.put("OptionPane.background", corPaletaBege);
        	         UIManager.put("Panel.background", corPaletaBege);
        	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Erro ao processar (redimensionar/copiar) a imagem:\n" + ex.getMessage(),
                                "Erro",
                                JOptionPane.ERROR_MESSAGE
                        );
           			 UIManager.put("OptionPane.background", corPaletaBege);
        	         UIManager.put("Panel.background", corPaletaBege);
        	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
                    }
                }
            }
        });

        campoPrecoVariavel = new EstilizacaoRedonda.CaixaTextoRedonda(
                "R$.",
                corPaletaVermelho,
                corPaletaBege,
                corPaletaCinza,
                2,
                35
        );
        campoPrecoVariavel.setFont(FontsConstants.MONTSERRAT_LIGHT_13);
        campoPrecoVariavel.setToolTipText("Preço a partir de (opcional)");
        campoPrecoVariavel.setBounds(658, 406, 520, 38);
        panelClaro.add(campoPrecoVariavel);
        campoPrecoVariavel.setColumns(10);
        aplicarMascaraPreco(campoPrecoVariavel);

        JLabel labelNome = new JLabel("NOME");
        labelNome.setFont(FontsConstants.MONTSERRAT_BOLD_15);
        labelNome.setForeground(corPaletaPreto);
        labelNome.setBounds(114, 103, 100, 14);
        panelClaro.add(labelNome);

        JLabel labelDescricao = new JLabel("DESCRIÇÃO");
        labelDescricao.setFont(FontsConstants.MONTSERRAT_BOLD_15);
        labelDescricao.setForeground(corPaletaPreto);
        labelDescricao.setBounds(114, 194, 120, 14);
        panelClaro.add(labelDescricao);

        JLabel labelGrupo = new JLabel("GRUPO");
        labelGrupo.setFont(FontsConstants.MONTSERRAT_BOLD_15);
        labelGrupo.setForeground(corPaletaPreto);
        labelGrupo.setBounds(114, 284, 80, 14);
        panelClaro.add(labelGrupo);

        JLabel labelFoto = new JLabel("FOTO");
        labelFoto.setFont(FontsConstants.MONTSERRAT_BOLD_15);
        labelFoto.setForeground(corPaletaPreto);
        labelFoto.setBounds(687, 284, 80, 14);
        panelClaro.add(labelFoto);

        JLabel labelPrecoVar = new JLabel("PREÇO \"A PARTIR DE:\"");
        labelPrecoVar.setFont(FontsConstants.MONTSERRAT_BOLD_15);
        labelPrecoVar.setForeground(corPaletaPreto);
        labelPrecoVar.setBounds(687, 381, 220, 14);
        panelClaro.add(labelPrecoVar);

        JLabel labelPreco = new JLabel("PREÇO");
        labelPreco.setFont(FontsConstants.MONTSERRAT_BOLD_15);
        labelPreco.setForeground(corPaletaPreto);
        labelPreco.setBounds(114, 381, 80, 14);
        panelClaro.add(labelPreco);

        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(30, 12, 92, 82);
        logoLabel.setIcon(new ImageIcon(TelaEditarItem.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        contentPaneVermelho.add(logoLabel);

        JLabel labelItemTopo = new JLabel("Itens");
        labelItemTopo.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        labelItemTopo.setForeground(corPaletaBege);
        labelItemTopo.setBounds(588, 34, 130, 38);
        contentPaneVermelho.add(labelItemTopo);

        EstilizacaoRedonda.BotaoRedondo botaoVoltar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Voltar",
                        corPaletaPreto,
                        corPaletaPretoInteracao,
                        corPaletaPreto,
                        35
                );
        botaoVoltar.setBounds(1128, 32, 104, 38);
        contentPaneVermelho.add(botaoVoltar);
        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        botaoVoltar.setForeground(corPaletaBege);
        botaoVoltar.setBackground(corPaletaPreto);
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
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

        carregarCategoriasNoComboBox();
        preencherCamposComItem();
        setLocationRelativeTo(null);
    }

    private void redimensionarEsalvar(java.io.File arquivoOriginal, java.nio.file.Path destino, int finalWidth, int finalHeight) throws IOException {

        BufferedImage originalImage = ImageIO.read(arquivoOriginal);

        if (originalImage == null) {
            throw new IOException("Não foi possível ler a imagem do caminho: " + arquivoOriginal.getAbsolutePath());
        }

        destino.getParent().toFile().mkdirs();

        Image scaledImage = originalImage.getScaledInstance(finalWidth, finalHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedBufferedImage = new BufferedImage(finalWidth, finalHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        ImageIO.write(resizedBufferedImage, "png", destino.toFile());
    }

    private void carregarCategoriasNoComboBox() {
        comboBoxGrupos.removeAllItems();
        listaCategorias.clear();
        try {
            List<Categoria> categoriasRetornadas = categoriaController.listarTodas();
            for (Categoria cat : categoriasRetornadas) {
                String nome = cat.getNome();
                int id = cat.getId();
                comboBoxGrupos.addItem(nome);
                listaCategorias.put(nome, id);
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar categorias do banco.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
        }
    }

    private void preencherCamposComItem() {
        if (itemEmEdicao == null) {
            return;
        }

        campoNome.setText(itemEmEdicao.getNome() != null ? itemEmEdicao.getNome() : "");
        campoDescricao.setText(itemEmEdicao.getDescricao() != null ? itemEmEdicao.getDescricao() : "");
        campoPreco.setText(String.format("R$ %.2f", itemEmEdicao.getPreco()).replace('.', ','));

        if (itemEmEdicao.getFotoUrl() != null) {
            campoFoto.setText(itemEmEdicao.getFotoUrl());
        } else {
            campoFoto.setText("");
        }

        if (itemEmEdicao.getPrecoComparacao() != null) {
            campoPrecoVariavel.setText(
                    String.format("R$ %.2f", itemEmEdicao.getPrecoComparacao()).replace('.', ',')
            );
        } else {
            campoPrecoVariavel.setText("");
        }

        int categoriaId = itemEmEdicao.getCategoriaId();
        for (int i = 0; i < comboBoxGrupos.getItemCount(); i++) {
            String nomeCat = comboBoxGrupos.getItemAt(i);
            Integer id = listaCategorias.get(nomeCat);
            if (id != null && id == categoriaId) {
                comboBoxGrupos.setSelectedIndex(i);
                break;
            }
        }
    }

    private void aplicarMascaraPreco(javax.swing.JTextField campo) {
        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = campo.getText();

                texto = texto.replaceAll("\\D", "");

                if (texto.isEmpty()) {
                    campo.setText("");
                    return;
                }

                try {
                    long valor = Long.parseLong(texto);
                    double valorFinal = valor / 100.0;
                    java.text.NumberFormat nf = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("pt", "BR"));
                    campo.setText(nf.format(valorFinal));
                } catch (Exception ex) {
                    campo.setText("");
                }
            }
        });
    }

    private double converterMoedaParaDouble(String texto) {
        try {
            return java.text.NumberFormat
                    .getCurrencyInstance(new java.util.Locale("pt", "BR"))
                    .parse(texto)
                    .doubleValue();
        } catch (Exception e) {
            return 0.0;
        }
    }

    private void salvarAlteracoes() {
        String nome = campoNome.getText().trim();
        String descricao = campoDescricao.getText().trim();
        String precoStr = campoPreco.getText().trim();
        String precoVarStr = campoPrecoVariavel.getText().trim();
        String foto = campoFoto.getText().trim();
        String categoriaSelecionada = (String) comboBoxGrupos.getSelectedItem();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do item.", "Aviso", JOptionPane.WARNING_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);
            return;
        }

        if (descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a descrição do item.", "Aviso", JOptionPane.WARNING_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);
            return;
        }

        if (categoriaSelecionada == null || categoriaSelecionada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um grupo/categoria.", "Aviso", JOptionPane.WARNING_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);
            return;
        }

        if (precoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o preço do item.", "Aviso", JOptionPane.WARNING_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);
            return;
        }

        double preco = converterMoedaParaDouble(precoStr);
        Double precoVariavel = null;

        if (!precoVarStr.isEmpty() && !precoVarStr.equalsIgnoreCase("R$.")) {
            precoVariavel = converterMoedaParaDouble(precoVarStr);
        }

        Integer categoriaId = listaCategorias.get(categoriaSelecionada);
        if (categoriaId == null) {
            JOptionPane.showMessageDialog(this, "Categoria selecionada inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
            return;
        }

        itemEmEdicao.setNome(nome);
        itemEmEdicao.setDescricao(descricao);
        itemEmEdicao.setPreco(preco);
        itemEmEdicao.setFotoUrl(foto);
        itemEmEdicao.setCategoriaId(categoriaId);
        itemEmEdicao.setPrecoComparacao(precoVariavel);

        try {
            itemCardapioController.atualizarItem(itemEmEdicao);

            JOptionPane.showMessageDialog(
                    this,
                    "Item atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);

            int opcao = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja voltar para a tela de Itens?",
                    "Item atualizado",
                    JOptionPane.YES_NO_OPTION
            );
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);

            if (opcao == JOptionPane.YES_OPTION) {
                dispose();
                TelaItens telaItens = new TelaItens();
                telaItens.setVisible(true);
            }

        } catch (ControllerException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao validar o item:\n" + ex.getMessage(),
                    "Erro de Validação",
                    JOptionPane.WARNING_MESSAGE
            );
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
	         
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar o item no banco:\n" + ex.getMessage(),
                    "Erro de Banco",
                    JOptionPane.ERROR_MESSAGE
            );
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ItemCardapio item = new ItemCardapio();
                    item.setId(1);
                    item.setNome("Item Exemplo");
                    item.setDescricao("Descrição exemplo");
                    item.setPreco(29.90);
                    item.setCategoriaId(1);
                    item.setFotoUrl("exemplo.png");
                    item.setPrecoComparacao(39.90);

                    TelaEditarItem frame = new TelaEditarItem(item);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
