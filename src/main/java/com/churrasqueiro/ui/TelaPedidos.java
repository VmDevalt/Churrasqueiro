package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.business.PedidoController;
import com.churrasqueiro.entities.PedidoResumo;
import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.NotaFiscalPdfUtil;


public class TelaPedidos extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    private JPanel topoVermelho;
    private JPanel areaConteudo;

    private final PedidoController pedidoController = new PedidoController();
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPedidos frame = new TelaPedidos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPedidos() {
    	Usuario usuarioLogado = TelaLogin.getUsuarioLogado();

        Color corPaletaVermelho = new Color(179, 13, 36);
        Color corPaletaBege = new Color(227, 202, 187);
        Color corPaletaVermelhoInteracao = new Color(200, 50, 50);
        Color corPaletaVermelhoPressionado = new Color(150, 0, 0);
        Color corPaletaPreto = new Color(0, 0, 0);
        Color corPaletaPretoInteracao = new Color(35, 35, 35);

        setTitle("Pedidos - Churrasqueiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        topoVermelho = new JPanel();
        topoVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        topoVermelho.setBackground(corPaletaVermelho);
        topoVermelho.setLayout(null);
        setContentPane(topoVermelho);

        java.net.URL urlIcone = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (urlIcone != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(urlIcone);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }
        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(20, -2, 92, 82);
        java.net.URL urlLogoPeq = getClass().getResource("/assets/imagens/iconeJanelaPequena.png");
        if (urlLogoPeq != null) {
            logoLabel.setIcon(new ImageIcon(urlLogoPeq));
        }
        topoVermelho.add(logoLabel);

        JPanel painelSelecionado = new JPanel();
        painelSelecionado.setBackground(corPaletaBege);
        painelSelecionado.setBounds(170, 0, 260, 77);
        painelSelecionado.setLayout(null);
        topoVermelho.add(painelSelecionado);

        JLabel pedidosLabel = new JLabel("Pedidos");
        pedidosLabel.setForeground(corPaletaPreto);
        pedidosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        pedidosLabel.setBounds(55, 10, 200, 57);
        painelSelecionado.add(pedidosLabel);

        JLabel gestaoLabel = new JLabel("Gestão");
        
        if(usuarioLogado.getTipo().trim().equalsIgnoreCase("ADMIN")) {
        	gestaoLabel.setForeground(corPaletaBege);
        } else {
        	gestaoLabel.setForeground(new Color(216, 173, 173));
        }
        
        gestaoLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        gestaoLabel.setBounds(530, 19, 200, 38);
        gestaoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topoVermelho.add(gestaoLabel);

        gestaoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                	if(usuarioLogado.getTipo().trim().equalsIgnoreCase("ADMIN")) {
	                    TelaGestao telaGestao = new TelaGestao();
	                    telaGestao.setVisible(true);
	                    dispose();
                	} else {
                        JOptionPane.showMessageDialog(
                                TelaPedidos.this,
                                "Área restrita para Administradores.",
                                "Aviso",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                	}
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            TelaPedidos.this,
                            "Tela de Gestão ainda não está disponível.",
                            "Aviso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gestaoLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(usuarioLogado.getTipo().trim().equalsIgnoreCase("ADMIN")) {
            		gestaoLabel.setForeground(corPaletaBege);
            	} else {
	            	gestaoLabel.setForeground(new Color(216, 173, 173));
	            }
            }
        });

        JLabel dashboardLabel = new JLabel("Dashboard");
        
        if(usuarioLogado.getTipo().trim().equalsIgnoreCase("ADMIN")) {
        	dashboardLabel.setForeground(corPaletaBege);
        } else {
        	dashboardLabel.setForeground(new Color(216, 173, 173));
        }
        
        dashboardLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        dashboardLabel.setBounds(800, 19, 230, 38);
        dashboardLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        topoVermelho.add(dashboardLabel);

        dashboardLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                	if(usuarioLogado.getTipo().trim().equalsIgnoreCase("ADMIN")) {
	                    TelaDashboard telaDashboard = new TelaDashboard();
	                    telaDashboard.setVisible(true);
	                    dispose();
                	} else {
                        JOptionPane.showMessageDialog(
                                TelaPedidos.this,
                                "Área restrita para Administradores.",
                                "Aviso",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                	}
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            TelaPedidos.this,
                            "Tela de Dashboard ainda não está disponível.",
                            "Aviso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dashboardLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	if(usuarioLogado.getTipo().trim().equalsIgnoreCase("ADMIN")) {
            		dashboardLabel.setForeground(corPaletaBege);
	            } else {
	            	dashboardLabel.setForeground(new Color(216, 173, 173));
	            }
            }
        });

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
        topoVermelho.add(botaoVoltar);
        botaoVoltar.addActionListener(e -> {
            TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal();
            telaMenuPrincipal.setVisible(true);
            dispose();
        });

        areaConteudo = new JPanel();
        areaConteudo.setBackground(corPaletaBege);
        areaConteudo.setBounds(0, 74, LARGURA, ALTURA - 74);
        areaConteudo.setLayout(null);
        topoVermelho.add(areaConteudo);
        EstilizacaoRedonda.BotaoRedondo botaoNovoPedido =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Novo Pedido",
                        corPaletaVermelho,
                        corPaletaVermelhoInteracao,
                        corPaletaVermelhoPressionado,
                        35
                );
        botaoNovoPedido.setBounds(300, 25, 260, 38);
        botaoNovoPedido.setFont(new Font("SansSerif", Font.PLAIN, 17));
        botaoNovoPedido.setForeground(corPaletaBege);
        areaConteudo.add(botaoNovoPedido);
        botaoNovoPedido.addActionListener(e -> {
            PedidoEmMontagem pedido = new PedidoEmMontagem();
            TelaCardapio telaCardapio = new TelaCardapio(pedido);
            telaCardapio.setVisible(true);
            dispose();
        });
        JLabel lblPreparo = new JLabel("Pedidos em Preparo");
        lblPreparo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblPreparo.setForeground(corPaletaPreto);
        lblPreparo.setBounds(320, 90, 200, 22);
        areaConteudo.add(lblPreparo);

        JLabel lblProntos = new JLabel("Pedidos Prontos");
        lblProntos.setForeground(corPaletaPreto);
        lblProntos.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblProntos.setBounds(830, 90, 200, 22);
        areaConteudo.add(lblProntos);
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setForeground(corPaletaVermelho);
        sep.setBounds(640, 120, 2, 500);
        areaConteudo.add(sep);
        JScrollPane scrollPreparo = new JScrollPane();
        scrollPreparo.setBounds(200, 120, 320, 450);
        scrollPreparo.setBorder(null);
        scrollPreparo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPreparo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPreparo.getVerticalScrollBar().setUnitIncrement(16);
        areaConteudo.add(scrollPreparo);

        JPanel conteudoPreparo = new JPanel();
        conteudoPreparo.setBackground(corPaletaBege);
        conteudoPreparo.setLayout(null);
        scrollPreparo.setViewportView(conteudoPreparo);
        JScrollPane scrollPronto = new JScrollPane();
        scrollPronto.setBounds(720, 120, 320, 450);
        scrollPronto.setBorder(null);
        scrollPronto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPronto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPronto.getVerticalScrollBar().setUnitIncrement(16);
        areaConteudo.add(scrollPronto);

        JPanel conteudoPronto = new JPanel();
        conteudoPronto.setBackground(corPaletaBege);
        conteudoPronto.setLayout(null);
        scrollPronto.setViewportView(conteudoPronto);
        carregarPedidosNasColunas(
                conteudoPreparo,
                conteudoPronto,
                corPaletaBege,
                corPaletaVermelho,
                corPaletaVermelhoInteracao,
                corPaletaVermelhoPressionado
        );
    }

    private void carregarPedidosNasColunas(
            JPanel conteudoPreparo,
            JPanel conteudoPronto,
            Color corPaletaBege,
            Color corPaletaVermelho,
            Color corPaletaVermelhoInteracao,
            Color corPaletaVermelhoPressionado
    ) {

        int yPreparo = 10;
        int yPronto = 10;

        try {
            List<PedidoResumo> pedidosPreparo =
                    pedidoController.listarPedidosPorStatus("Em Preparo");
            List<PedidoResumo> pedidosProntos =
                    pedidoController.listarPedidosPorStatus("Pronto");
            for (PedidoResumo p : pedidosPreparo) {
                EstilizacaoRedonda.PainelRedondo cardPreparo =
                        criarCardPedidoPreparo(
                                corPaletaBege,
                                corPaletaVermelho,
                                corPaletaVermelhoInteracao,
                                corPaletaVermelhoPressionado,
                                p
                        );
                cardPreparo.setBounds(10, yPreparo, 300, 380);
                conteudoPreparo.add(cardPreparo);
                yPreparo += 390;
            }
            for (PedidoResumo p : pedidosProntos) {
                EstilizacaoRedonda.PainelRedondo cardPronto =
                        criarCardPedidoPronto(
                                corPaletaBege,
                                corPaletaVermelho,
                                corPaletaVermelhoInteracao,
                                corPaletaVermelhoPressionado,
                                p
                        );
                cardPronto.setBounds(10, yPronto, 300, 380);
                conteudoPronto.add(cardPronto);
                yPronto += 390;
            }

        } catch (DatabaseException | ControllerException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar pedidos: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        conteudoPreparo.setPreferredSize(new Dimension(300, Math.max(yPreparo, 10)));
        conteudoPronto.setPreferredSize(new Dimension(300, Math.max(yPronto, 10)));

        conteudoPreparo.revalidate();
        conteudoPronto.revalidate();
    }

    private void recarregarTela() {
        TelaPedidos nova = new TelaPedidos();
        nova.setVisible(true);
        dispose();
    }

    private EstilizacaoRedonda.PainelRedondo criarCardPedidoPreparo(
            Color corBege,
            Color corVermelho,
            Color corVermelhoInteracao,
            Color corVermelhoPressionado,
            PedidoResumo pedido) {

        EstilizacaoRedonda.PainelRedondo card =
                new EstilizacaoRedonda.PainelRedondo(null, 40, 2, corBege, null);
        card.setBorder(new LineBorder(corVermelho, 2, true));
        card.setLayout(null);

        String dataFormatada = pedido.getDataHora() != null
                ? pedido.getDataHora().format(fmt)
                : "";

        JTextArea txtInfo = new JTextArea(
                "Mesa: " + pedido.getNumeroMesa() + "\n" +
                        "Garçom: " + pedido.getGarconLogin() + "\n" +
                        "Data e Hora: " + dataFormatada + "\n\n" +
                        "Desconto: R$" + String.format("%.2f", pedido.getDesconto()).replace('.', ',') + "\n" +
                        "Acréscimo: R$" + String.format("%.2f", pedido.getAcrescimo()).replace('.', ',') + "\n" +
                        "Total: R$" + String.format("%.2f", pedido.getTotal()).replace('.', ',') + "\n" +
                        "Forma de Pagamento: " + pedido.getFormaPagamento() + "\n"
        );
        txtInfo.setBounds(20, 15, 260, 150);
        txtInfo.setForeground(corVermelho);
        txtInfo.setBackground(corBege);
        txtInfo.setFont(new Font("Dialog", Font.PLAIN, 14));
        txtInfo.setEditable(false);
        card.add(txtInfo);

        EstilizacaoRedonda.PainelRedondo painelItens =
                new EstilizacaoRedonda.PainelRedondo(null, 40, 2, corVermelho, null);
        painelItens.setBorder(new LineBorder(corVermelho, 2, true));
        painelItens.setBounds(20, 190, 260, 110);
        painelItens.setLayout(null);
        card.add(painelItens);

        StringBuilder sb = new StringBuilder();
        if (pedido.getItensDescricao() != null) {
            for (String linha : pedido.getItensDescricao()) {
                sb.append(linha).append("\n");
            }
        }

        JTextArea txtItens = new JTextArea(sb.toString());
        txtItens.setBounds(10, 10, 240, 90);
        txtItens.setForeground(corBege);
        txtItens.setBackground(corVermelho);
        txtItens.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtItens.setEditable(false);
        painelItens.add(txtItens);

        EstilizacaoRedonda.BotaoRedondo botaoPronto =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Pedido Pronto",
                        corVermelho,
                        corVermelhoInteracao,
                        corVermelhoPressionado,
                        35
                );
        botaoPronto.setBounds(70, 330, 160, 32);
        botaoPronto.setForeground(corBege);
        botaoPronto.setFont(new Font("SansSerif", Font.PLAIN, 13));
        card.add(botaoPronto);

        botaoPronto.addActionListener(e -> {
            try {
                pedidoController.alterarStatusPedido(pedido.getId(), "Pronto");
                recarregarTela();
            } catch (DatabaseException | ControllerException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao atualizar pedido: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        return card;
    }

    private EstilizacaoRedonda.PainelRedondo criarCardPedidoPronto(
            Color corBege,
            Color corVermelho,
            Color corVermelhoInteracao,
            Color corVermelhoPressionado,
            PedidoResumo pedido) {

        EstilizacaoRedonda.PainelRedondo card =
                new EstilizacaoRedonda.PainelRedondo(null, 40, 2, corBege, null);
        card.setBorder(new LineBorder(corVermelho, 2, true));
        card.setLayout(null);

        String dataFormatada = pedido.getDataHora() != null
                ? pedido.getDataHora().format(fmt)
                : "";

        JTextArea txtInfo = new JTextArea(
                "Mesa: " + pedido.getNumeroMesa() + "\n" +
                        "Garçom: " + pedido.getGarconLogin() + "\n" +
                        "Data e Hora: " + dataFormatada + "\n\n" +
                        "Desconto: R$" + String.format("%.2f", pedido.getDesconto()).replace('.', ',') + "\n" +
                        "Acréscimo: R$" + String.format("%.2f", pedido.getAcrescimo()).replace('.', ',') + "\n" +
                        "Total: R$" + String.format("%.2f", pedido.getTotal()).replace('.', ',') + "\n" +
                        "Forma de Pagamento: " + pedido.getFormaPagamento() + "\n"
        );
        txtInfo.setBounds(20, 15, 260, 150);
        txtInfo.setForeground(corVermelho);
        txtInfo.setBackground(corBege);
        txtInfo.setFont(new Font("Dialog", Font.PLAIN, 14));
        txtInfo.setEditable(false);
        card.add(txtInfo);

        EstilizacaoRedonda.PainelRedondo painelItens =
                new EstilizacaoRedonda.PainelRedondo(null, 40, 2, corVermelho, null);
        painelItens.setBorder(new LineBorder(corVermelho, 2, true));
        painelItens.setBounds(20, 190, 260, 110);
        painelItens.setLayout(null);
        card.add(painelItens);

        StringBuilder sb = new StringBuilder();
        if (pedido.getItensDescricao() != null) {
            for (String linha : pedido.getItensDescricao()) {
                sb.append(linha).append("\n");
            }
        }

        JTextArea txtItens = new JTextArea(sb.toString());
        txtItens.setBounds(10, 10, 240, 90);
        txtItens.setForeground(corBege);
        txtItens.setBackground(corVermelho);
        txtItens.setFont(new Font("Dialog", Font.PLAIN, 13));
        txtItens.setEditable(false);
        painelItens.add(txtItens);

        EstilizacaoRedonda.BotaoRedondo botaoPreparando =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Voltar p/ Preparo",
                        corVermelho,
                        corVermelhoInteracao,
                        corVermelhoPressionado,
                        35
                );
        botaoPreparando.setBounds(20, 330, 140, 32);
        botaoPreparando.setForeground(corBege);
        botaoPreparando.setFont(new Font("SansSerif", Font.PLAIN, 13));
        card.add(botaoPreparando);

        EstilizacaoRedonda.BotaoRedondo botaoImprimir =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Imprimir",
                        corVermelho,
                        corVermelhoInteracao,
                        corVermelhoPressionado,
                        35
                );
        botaoImprimir.setBounds(170, 330, 110, 32);
        botaoImprimir.setForeground(corBege);
        botaoImprimir.setFont(new Font("SansSerif", Font.PLAIN, 13));
        card.add(botaoImprimir);
        botaoImprimir.addActionListener(e -> {
            try {
                java.io.File pdfGerado = NotaFiscalPdfUtil.gerarNotaFiscalPedido(pedido);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao gerar nota fiscal: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        botaoPreparando.addActionListener(e -> {
            try {
                pedidoController.alterarStatusPedido(pedido.getId(), "Em Preparo");
                recarregarTela();
            } catch (DatabaseException | ControllerException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao atualizar pedido: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        return card;
    }
}
