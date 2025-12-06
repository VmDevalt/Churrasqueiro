package com.churrasqueiro.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import com.churrasqueiro.business.PedidoController;
import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;

public class TelaPedidoConfirmar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panelVermelho;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private JButton botaoNovoPedido;

    private PedidoEmMontagem pedido;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPedidoConfirmar frame = new TelaPedidoConfirmar(new PedidoEmMontagem());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaPedidoConfirmar(PedidoEmMontagem pedido) {

        this.pedido = pedido;

        Color corPaletaVermelho = new Color(179,13,36);
        Color corPaletaBege = new Color(227,202,187);
        Color corPaletaVermelhoInteracao = new Color(200,50,50);
        Color corPaletaVermelhoPressionado = new Color(150,0,0);
        Color corPaletaPreto = new Color(0,0,0);
        Color corPaletaPretoInteração = new Color(35,35,35);
        Color corPaletaCinza = new Color(140,127,127);
        Color corPaletaBegeInteracao = new Color(245,225,210);
        Color corPaletaBegePressionado = new Color(200,175,160);

        setTitle("Novo Pedido - Churrasqueiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        panelVermelho = new JPanel();
        panelVermelho.setBackground(new Color(179, 13, 36));
        panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelVermelho);
        panelVermelho.setLayout(null);

        JPanel panelBranco = new JPanel();
        panelBranco.setBackground(new Color(227,202,187));
        panelBranco.setBounds(0, 74, 1280, 609);
        panelVermelho.add(panelBranco);
        panelBranco.setLayout(null);

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }

        final EstilizacaoRedonda.PainelRedondo panelResumoPedido = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaVermelho,null);
        panelResumoPedido.setFocusable(true);
        panelResumoPedido.requestFocusInWindow();
        panelResumoPedido.setBounds(392, 109, 544, 342);
        panelBranco.add(panelResumoPedido);
        panelResumoPedido.setLayout(null);

        JTextArea textResumoPedido = new JTextArea();
        StringBuilder sb = new StringBuilder();

        sb.append("Mesa: ").append(pedido.getNumeroMesa()).append("\n");
        sb.append("Cliente: ").append(pedido.getNomeCliente()).append("\n");
        sb.append("Forma de Pagamento: ").append(pedido.getFormaPagamento()).append("\n");
        sb.append("Observações: ").append(
                pedido.getObservacoes() == null ? "" : pedido.getObservacoes()
        ).append("\n");
        sb.append("------------------------------------------------------------\n");
        sb.append("ITENS:\n");

        Map<Integer, Integer> mapaQtd = new LinkedHashMap<>();
        Map<Integer, ItemCardapio> mapaItem = new LinkedHashMap<>();

        pedido.getItens().forEach(item -> {
            int id = item.getId();
            mapaQtd.put(id, mapaQtd.getOrDefault(id, 0) + 1);
            if (!mapaItem.containsKey(id)) {
                mapaItem.put(id, item);
            }
        });

        mapaItem.forEach((id, item) -> {
            int quantidade = mapaQtd.get(id);

            sb.append("- ")
              .append(quantidade).append("x ")
              .append(item.getNome())
              .append(" (R$ ")
              .append(String.format("%.2f", item.getPreco()).replace('.', ','))
              .append(")\n");
        });

        sb.append("------------------------------------------------------------\n");
        sb.append("Total Itens: R$ ")
        .append(String.format("%.2f", pedido.getTotalItens()).replace('.', ','))
        .append("\n");
        sb.append("Acréscimo: R$ ")
        .append(String.format("%.2f", pedido.getAcrescimo()).replace('.', ','))
        .append("\n");
        sb.append("Desconto: R$ ")
        .append(String.format("%.2f", pedido.getDesconto()).replace('.', ','))
        .append("\n");
        sb.append("TOTAL FINAL: R$ ")
        .append(String.format("%.2f", pedido.getTotalFinal()).replace('.', ','))
        .append("\n");

        textResumoPedido.setText(sb.toString());
        textResumoPedido.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textResumoPedido.setColumns(2);
        textResumoPedido.setForeground(new Color(227,202,187));
        textResumoPedido.setBackground(new Color(179,13,36));
        textResumoPedido.setBounds(12, 12, 520, 318);
        panelResumoPedido.add(textResumoPedido);

        JLabel lblConfirmarPedido = new JLabel("Confirmar Pedido");
        lblConfirmarPedido.setForeground(Color.BLACK);
        lblConfirmarPedido.setFont(new Font("Dialog", Font.PLAIN, 22));
        lblConfirmarPedido.setBounds(555, 12, 182, 26);
        panelBranco.add(lblConfirmarPedido);

        final EstilizacaoRedonda.BotaoRedondo botaoConfirmar = new EstilizacaoRedonda.BotaoRedondo("Confirmar",corPaletaVermelho,corPaletaVermelhoInteracao,corPaletaVermelhoPressionado,35);
        botaoConfirmar.setText("Confirmar");
        botaoConfirmar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoConfirmar.setForeground(corPaletaBege);
        botaoConfirmar.setBackground(new Color(179,13,36));
        botaoConfirmar.setBounds(565, 509, 182, 38);
        panelBranco.add(botaoConfirmar);
        botaoConfirmar.addActionListener(e -> {

            String forma = pedido.getFormaPagamento();
            String formaNormalizada = forma == null ? "" : forma.trim();

            if (formaNormalizada.equalsIgnoreCase("pix")) {
                try {
                    com.churrasqueiro.service.MercadoPagoPixService pixService =
                            new com.churrasqueiro.service.MercadoPagoPixService();

                    com.churrasqueiro.service.PixPaymentResponse pixResponse =
                            pixService.criarPagamentoPix(pedido);

                    TelaPixPagamento telaPix = new TelaPixPagamento(pedido, pixResponse);
                    telaPix.setVisible(true);

                    dispose();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            TelaPedidoConfirmar.this,
                            "Erro ao iniciar pagamento PIX: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } else {
                try {
                    PedidoController controller = new PedidoController();
                    controller.salvar(pedido);

                    JOptionPane.showMessageDialog(
                            TelaPedidoConfirmar.this,
                            "Pedido salvo com sucesso!\nForma de pagamento: " + formaNormalizada,
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    dispose();
                    TelaPedidos telaPedidos = new TelaPedidos();
                    telaPedidos.setVisible(true);

                } catch (ControllerException | DatabaseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            TelaPedidoConfirmar.this,
                            "Erro ao salvar pedido: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setForeground(new Color(255, 255, 255));
        botaoVoltar.setBackground(new Color(0, 0, 0));
        botaoVoltar.setBounds(1131, 19, 104, 38);
        panelVermelho.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaNovoPedido telaNovo = new TelaNovoPedido(pedido);
                telaNovo.setVisible(true);
                dispose();
            }
        });

        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        logoLabel.setBounds(30, 0, 92, 82);
        panelVermelho.add(logoLabel);

        JLabel novoPedidoLabel = new JLabel("Novo Pedido");
        novoPedidoLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        novoPedidoLabel.setForeground(corPaletaBege);
        novoPedidoLabel.setBounds(485, 12, 261, 52);
        panelVermelho.add(novoPedidoLabel);
    }
}
