package com.churrasqueiro.ui;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.churrasqueiro.business.ItemCardapioController;
import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.exceptions.DatabaseException;

public class TelaCardapio extends JFrame {

    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    private JPanel panelCards;
    private PedidoEmMontagem pedido;

    private ItemCardapioController itemController = new ItemCardapioController();
    private List<ItemCardapio> itens = new ArrayList<>();

    private final Color corPaletaVermelho = new Color(179, 13, 36);
    private final Color corPaletaBege = new Color(227, 202, 187);
    private final Color corPaletaVermelhoInteracao = new Color(200, 50, 50);
    private final Color corPaletaVermelhoPressionado = new Color(150, 0, 0);
    private final Color corPaletaPreto = new Color(0, 0, 0);
    private final Color corPaletaPretoInteracao = new Color(35, 35, 35);

    public TelaCardapio(PedidoEmMontagem pedido) {

        this.pedido = pedido;

        setTitle("Cardápio - Churrasqueiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panelVermelho = new JPanel();
        panelVermelho.setBackground(corPaletaVermelho);
        panelVermelho.setLayout(null);
        panelVermelho.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(panelVermelho);

        EstilizacaoRedonda.BotaoRedondo botaoVoltar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Voltar",
                        corPaletaPreto,
                        corPaletaPretoInteracao,
                        corPaletaPreto,
                        35
                );
        botaoVoltar.setBounds(1125, 19, 110, 38);
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setForeground(Color.WHITE);
        panelVermelho.add(botaoVoltar);
        botaoVoltar.addActionListener(e -> {
            TelaPedidos telaPedidos = new TelaPedidos();
            telaPedidos.setVisible(true);
            dispose();
        });

        JLabel logo = new JLabel(new ImageIcon(
                getClass().getResource("/assets/imagens/iconeJanelaPequena.png")));
        logo.setBounds(30, 0, 92, 82);
        panelVermelho.add(logo);

        JLabel titulo = new JLabel("Cardápio");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        titulo.setForeground(corPaletaBege);
        titulo.setBounds(530, 12, 400, 52);
        panelVermelho.add(titulo);

        JPanel panelBranco = new JPanel();
        panelBranco.setBackground(corPaletaBege);
        panelBranco.setBounds(0, 74, 1280, 609);
        panelBranco.setLayout(null);
        panelVermelho.add(panelBranco);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 60, 1280, 470);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panelBranco.add(scrollPane);

        panelCards = new JPanel();
        panelCards.setBackground(corPaletaBege);
        panelCards.setLayout(null);
        scrollPane.setViewportView(panelCards);

        EstilizacaoRedonda.BotaoRedondo botaoAvancar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Avançar",
                        corPaletaPreto,
                        corPaletaPretoInteracao,
                        corPaletaPreto,
                        35
                );
        botaoAvancar.setForeground(Color.WHITE);
        botaoAvancar.setFont(new Font("SansSerif", Font.BOLD, 20));
        botaoAvancar.setBounds(1050, 550, 180, 40);
        panelBranco.add(botaoAvancar);

        botaoAvancar.addActionListener(e -> {
            TelaNovoPedido t = new TelaNovoPedido(pedido);
            t.setVisible(true);
            dispose();
        });

        carregarItens();
        exibirItens();
    }

    private void carregarItens() {
        try {
            itens = itemController.listarTodos();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar itens.");
        }
    }

    private void exibirItens() {

        panelCards.removeAll();

        int x = 90;
        int y = 20;
        int w = 335;
        int h = 196;
        int gapX = 40;
        int gapY = 35;
        int colunas = 3;
        int contador = 0;
        int yMax = 0;

        for (ItemCardapio item : itens) {
            JPanel card = criarCard(item);
            card.setBounds(x, y, w, h);
            panelCards.add(card);

            contador++;

            if (contador % colunas == 0) {
                x = 90;
                y += h + gapY;
            } else {
                x += w + gapX;
            }

            yMax = Math.max(yMax, y + h);
        }

        int alturaMinima = 470;
        panelCards.setPreferredSize(new Dimension(1280, Math.max(alturaMinima, yMax + 20)));
        panelCards.revalidate();
        panelCards.repaint();
    }

    private JPanel criarCard(ItemCardapio item) {

        JPanel p = new EstilizacaoRedonda.PainelRedondo(null, 60, 4, corPaletaVermelho, null);
        p.setLayout(null);

        JLabel img = new JLabel();
        img.setBounds(26, 20, 90, 90);

        try {
            if (item.getFotoUrl() != null) {
                URL u = getClass().getResource("/assets/imagens/itens/" + item.getFotoUrl());
                if (u != null) {
                    ImageIcon iconOriginal = new ImageIcon(u);
                    Image imgEscalada = iconOriginal.getImage().getScaledInstance(82, 82, Image.SCALE_SMOOTH);
                    ImageIcon iconRedimensionado = new ImageIcon(imgEscalada);
                    img.setIcon(iconRedimensionado);
                }
            }
        } catch (Exception ignored) {}

        p.add(img);

        JLabel nome = new JLabel(item.getNome());
        nome.setFont(new Font("Dialog", Font.PLAIN, 20));
        nome.setForeground(Color.WHITE);
        nome.setBounds(134, 12, 180, 26);
        p.add(nome);

        JTextArea desc = new JTextArea(item.getDescricao());
        desc.setFont(new Font("SansSerif", Font.PLAIN, 14));
        desc.setForeground(corPaletaBege);
        desc.setBackground(corPaletaVermelho);
        desc.setWrapStyleWord(true);
        desc.setLineWrap(true);
        desc.setBounds(127, 50, 180, 80);
        desc.setEditable(false);
        desc.setOpaque(true);
        p.add(desc);

        JLabel preco = new JLabel("R$ " + String.format("%.2f", item.getPreco()).replace('.', ','));
        preco.setFont(new Font("Dialog", Font.BOLD, 20));
        preco.setForeground(Color.WHITE);
        preco.setBounds(26, 150, 120, 26);
        p.add(preco);

        EstilizacaoRedonda.BotaoRedondo botaoAdicionar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Adicionar",
                        corPaletaPreto,
                        corPaletaPretoInteracao,
                        corPaletaPreto,
                        25
                );
        botaoAdicionar.setBounds(165, 148, 130, 32);
        botaoAdicionar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botaoAdicionar.setForeground(Color.WHITE);
        p.add(botaoAdicionar);

        botaoAdicionar.addActionListener(e -> {
            pedido.adicionarItem(item);
            JOptionPane.showMessageDialog(this, item.getNome() + " adicionado ao pedido!");
        });

        return p;
    }
}
