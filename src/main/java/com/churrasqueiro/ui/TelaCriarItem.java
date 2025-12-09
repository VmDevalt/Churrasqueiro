package com.churrasqueiro.ui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.IOException;

import com.churrasqueiro.business.CategoriaController;
import com.churrasqueiro.business.ItemCardapioController;
import com.churrasqueiro.entities.Categoria;
import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.utils.FontsConstants;

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
    private final ItemCardapioController itemCardapioController = new ItemCardapioController();
    private final CategoriaController categoriaController = new CategoriaController();
    private Map<String, Integer> listaCategorias = new HashMap<>();

    public EstilizacaoRedonda.CaixaTextoRedonda getCampoNome() {
        return campoNome;
    }

    public EstilizacaoRedonda.CaixaTextoRedonda getCampoDescricao() {
        return campoDescricao;
    }

    public EstilizacaoRedonda.CaixaTextoRedonda getCampoPreco() {
        return campoPreco;
    }

    public EstilizacaoRedonda.CaixaTextoRedonda getCampoFoto() {
        return campoFoto;
    }

    public EstilizacaoRedonda.CaixaTextoRedonda getCampoPrecoVariavel() {
        return campoPrecoVariavel;
    }

    public JComboBox<String> getComboBoxGrupos() {
        return comboBoxGrupos;
    }

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

        Color corPaletaVermelho = new Color(179, 13, 36);
        Color corPaletaBege = new Color(227, 202, 187);
        Color corPaletaVermelhoInteracao = new Color(200, 50, 50);
        Color corPaletaVermelhoPressionado = new Color(150, 0, 0);
        Color corPaletaPreto = new Color(0, 0, 0);
        Color corPaletaPretoInteração = new Color(35, 35, 35);
        Color corPaletaCinza = new Color(140, 127, 127);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Criar Item - Churrasqueiro");
        contentPaneVermelho = new JPanel();
        contentPaneVermelho.setBackground(corPaletaVermelho);
        contentPaneVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPaneVermelho);
        contentPaneVermelho.setLayout(null);

        JPanel panelClaro = new JPanel();
        panelClaro.setBounds(0, 102, 1264, 663);
        contentPaneVermelho.add(panelClaro);
        panelClaro.setBackground(new Color(227, 202, 187));
        panelClaro.setLayout(null);

        JLabel labelCriarItem = new JLabel("Criar Item");
        labelCriarItem.setBounds(550, 24, 353, 68);
        labelCriarItem.setForeground(new Color(179, 13, 36));
        labelCriarItem.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        panelClaro.add(labelCriarItem);

        final EstilizacaoRedonda.BotaoRedondo botaoAdicionarItem =
                new EstilizacaoRedonda.BotaoRedondo("Confirmar", corPaletaPreto, corPaletaPretoInteração, corPaletaPreto, 35);
        botaoAdicionarItem.setText("Adicionar Item");
        botaoAdicionarItem.setBounds(542, 474, 178, 38);
        panelClaro.add(botaoAdicionarItem);
        botaoAdicionarItem.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoAdicionarItem.setForeground(new Color(255, 255, 255));
        botaoAdicionarItem.setBackground(new Color(0, 0, 0));
        botaoAdicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });

        this.comboBoxGrupos = new JComboBox<>();
        comboBoxGrupos.setBorder(new LineBorder(new Color(179, 13, 36), 1));
        comboBoxGrupos.setForeground(corPaletaPreto);
        comboBoxGrupos.setBackground(corPaletaBege);
        comboBoxGrupos.setFont(new Font("SansSerif", Font.PLAIN, 17));
        comboBoxGrupos.setMaximumRowCount(2);
        comboBoxGrupos.setBounds(100, 269, 486, 38);
        panelClaro.add(comboBoxGrupos);

        carregarCategoriasNoComboBox();

        this.campoNome = new EstilizacaoRedonda.CaixaTextoRedonda("Digite o nome...", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoNome.setToolTipText("Digite seu texto");
        campoNome.setBounds(90, 88, 1088, 38);
        panelClaro.add(campoNome);
        campoNome.setColumns(10);

        this.campoDescricao = new EstilizacaoRedonda.CaixaTextoRedonda("Digite a descrição...", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoDescricao.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoDescricao.setToolTipText("Digite seu texto");
        campoDescricao.setBounds(90, 182, 1088, 38);
        panelClaro.add(campoDescricao);
        campoDescricao.setColumns(10);

        this.campoPreco = new EstilizacaoRedonda.CaixaTextoRedonda("R$.", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoPreco.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoPreco.setToolTipText("Digite seu texto");
        campoPreco.setBounds(90, 366, 496, 38);
        panelClaro.add(campoPreco);
        campoPreco.setColumns(10);
        aplicarMascaraPreco(campoPreco);

        EstilizacaoRedonda.BotaoRedondo botaoSelecionarFoto =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Selecionar",
                        corPaletaPreto,
                        corPaletaPretoInteração,
                        corPaletaPreto,
                        35
                );

        botaoSelecionarFoto.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoSelecionarFoto.setForeground(Color.WHITE);
        botaoSelecionarFoto.setBounds(1050, 269, 120, 38);
        panelClaro.add(botaoSelecionarFoto);

        this.campoFoto = new EstilizacaoRedonda.CaixaTextoRedonda("Adicione uma foto...", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoFoto.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoFoto.setToolTipText("Digite seu texto");
        campoFoto.setBounds(658, 269, 520, 38);
        panelClaro.add(campoFoto);
        campoFoto.setColumns(10);
        botaoSelecionarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                        
                        ImageIcon novoIcone = new ImageIcon(destino.toFile().getAbsolutePath());

                        JOptionPane.showMessageDialog(null,"Imagem carregada, redimensionada para 82x82px e salva como PNG!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Erro ao processar (redimensionar/copiar) a imagem:\n" + ex.getMessage(),
                                "Erro",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });

        this.campoPrecoVariavel = new EstilizacaoRedonda.CaixaTextoRedonda("R$.", corPaletaVermelho, corPaletaBege, corPaletaCinza, 2, 35);
        campoPrecoVariavel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoPrecoVariavel.setToolTipText("Digite seu texto");
        campoPrecoVariavel.setBounds(658, 366, 520, 38);
        panelClaro.add(campoPrecoVariavel);
        campoPrecoVariavel.setColumns(10);
        aplicarMascaraPreco(campoPrecoVariavel);

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
        labelItem.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        labelItem.setForeground(corPaletaBege);
        labelItem.setBounds(570, 34, 208, 38);
        contentPaneVermelho.add(labelItem);

        final EstilizacaoRedonda.BotaoRedondo botaoSair =
                new EstilizacaoRedonda.BotaoRedondo("Voltar", corPaletaPreto, corPaletaPretoInteração, corPaletaPreto, 35);
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
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar categorias do banco.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoNome.setText("");
        campoDescricao.setText("");
        campoPreco.setText("");
        campoPrecoVariavel.setText("");
        campoFoto.setText("");
        comboBoxGrupos.setSelectedIndex(0);
    }

private void aplicarMascaraPreco(JTextField campo) {
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

    private void adicionarItem() {

        String nome = campoNome.getText().trim();
        String descricao = campoDescricao.getText().trim();
        String precoStr = campoPreco.getText().trim();
        String precoVarStr = campoPrecoVariavel.getText().trim();
        String foto = campoFoto.getText().trim();
        String categoriaSelecionada = (String) comboBoxGrupos.getSelectedItem();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do item.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe a descrição do item.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (categoriaSelecionada == null || categoriaSelecionada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um grupo/categoria.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (precoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o preço do item.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double preco = converterMoedaParaDouble(precoStr);
        Double precoVariavel = null;
        if (!precoVarStr.isEmpty()) {
            precoVariavel = converterMoedaParaDouble(precoVarStr);
        }

        Integer categoriaId = listaCategorias.get(categoriaSelecionada);
        if (categoriaId == null) {
            JOptionPane.showMessageDialog(this, "Categoria selecionada inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ItemCardapio item = new ItemCardapio();
        item.setNome(nome);
        item.setDescricao(descricao);
        item.setPreco(preco);
        item.setFotoUrl(foto);
        item.setCategoriaId(categoriaId);

        try {
            ItemCardapio itemCriado = itemCardapioController.cadastrarItem(item);

            JOptionPane.showMessageDialog(
                    this,
                    "Item criado com sucesso!\nID: " + itemCriado.getId(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            int opcao = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja voltar para a tela de Itens?",
                    "Item criado",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcao == JOptionPane.YES_OPTION) {
                dispose();
                TelaItens telaItens = new TelaItens();
                telaItens.setVisible(true);
            } else {
                limparCampos();
            }

        } catch (ControllerException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao validar o item:\n" + ex.getMessage(),
                    "Erro de Validação",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar o item no banco:\n" + ex.getMessage(),
                    "Erro de Banco",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
