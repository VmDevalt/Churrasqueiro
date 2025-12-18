package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.churrasqueiro.business.PedidoController;
import com.churrasqueiro.entities.ItemCardapio;
import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontsConstants;

public class TelaPedidoConfirmar extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    private JPanel panelVermelho;
    private PedidoEmMontagem pedido;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new TelaPedidoConfirmar(new PedidoEmMontagem()).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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
        Color corPaletaPretoInteracao = new Color(35,35,35);
        Color corPaletaCinza = new Color(140,127,127);

        setTitle("Novo Pedido - Churrasqueiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        panelVermelho = new JPanel();
        panelVermelho.setBackground(corPaletaVermelho);
        panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelVermelho.setLayout(null);
        setContentPane(panelVermelho);

        JPanel panelBege = new JPanel();
        panelBege.setBackground(corPaletaBege);
        panelBege.setBounds(0, 104, 1280, 609);
        panelBege.setLayout(null);
        panelVermelho.add(panelBege);

        carregarIcone();

        JLabel titulo = new JLabel("Confirmar Pedido");
        titulo.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        titulo.setForeground(Color.BLACK);
        titulo.setBounds(450, 30, 380, 35);
        panelBege.add(titulo);

        final EstilizacaoRedonda.PainelRedondo panelResumo =
                new EstilizacaoRedonda.PainelRedondo(null, 60, 4, corPaletaVermelho, null);
        panelResumo.setBounds(360, 109, 544, 342);
        panelBege.add(panelResumo);
        panelResumo.setLayout(null);

        JTextArea resumo = new JTextArea();
        resumo.setFont(FontsConstants.MONTSERRAT_BOLD_16);
        resumo.setForeground(corPaletaBege);
        resumo.setBackground(corPaletaVermelho);
        resumo.setEditable(false);
        resumo.setBounds(12, 12, 520, 318);
        panelResumo.add(resumo);

        resumo.setText(montarResumoPedido());

        EstilizacaoRedonda.BotaoRedondo botaoConfirmar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Confirmar",
                        corPaletaVermelho,
                        corPaletaVermelhoInteracao,
                        corPaletaVermelhoPressionado,
                        35
                );

        botaoConfirmar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        botaoConfirmar.setForeground(corPaletaBege);
        botaoConfirmar.setBounds(565, 509, 182, 38);
        panelBege.add(botaoConfirmar);

        botaoConfirmar.addActionListener(e -> confirmarPedido());

        EstilizacaoRedonda.BotaoRedondo botaoVoltar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Voltar",
                        corPaletaPreto,
                        corPaletaPretoInteracao,
                        corPaletaPreto,
                        35
                );

        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setBounds(1131, 32, 104, 38);
        panelVermelho.add(botaoVoltar);

        botaoVoltar.addActionListener(e -> {
            new TelaNovoPedido(pedido).setVisible(true);
            dispose();
        });

        JLabel logo = new JLabel(new ImageIcon(
                TelaPedidoConfirmar.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        logo.setBounds(30, 14, 92, 82);
        panelVermelho.add(logo);

        JLabel novoPedido = new JLabel("Novo Pedido");
        novoPedido.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        novoPedido.setForeground(corPaletaBege);
        novoPedido.setBounds(500, 25, 280, 52);
        panelVermelho.add(novoPedido);
    }

    private void confirmarPedido() {

        String forma = pedido.getFormaPagamento();
        String formaNormalizada = forma == null ? "" : forma.trim().toLowerCase();

        if (formaNormalizada.equals("pix")) {

            try {
                com.churrasqueiro.service.MercadoPagoPixService pixService =
                        new com.churrasqueiro.service.MercadoPagoPixService();

                com.churrasqueiro.service.PixPaymentResponse pixResponse =
                        pixService.criarPagamentoPix(pedido);

                TelaPixPagamento telaPix = new TelaPixPagamento(pedido, pixResponse);
                telaPix.setVisible(true);

                dispose();
                return;

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao iniciar pagamento PIX: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
        }

        if (
            formaNormalizada.contains("cart") ||
            formaNormalizada.contains("credito") ||
            formaNormalizada.contains("crédito") ||
            formaNormalizada.contains("debito") ||
            formaNormalizada.contains("débito")
        ) {

            TelaCartaoCredito telaCartao = new TelaCartaoCredito(this, pedido);
            telaCartao.setVisible(true);

            if (!telaCartao.isPagamentoConfirmado()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pagamento não confirmado. Pedido não finalizado.",
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }

        try {
            PedidoController controller = new PedidoController();
            controller.salvar(pedido);

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido salvo com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();
            new TelaPedidos().setVisible(true);

        } catch (ControllerException | DatabaseException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar pedido: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String montarResumoPedido() {

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
            mapaItem.putIfAbsent(id, item);
        });

        mapaItem.forEach((id, item) -> {
            sb.append("- ")
              .append(mapaQtd.get(id)).append("x ")
              .append(item.getNome())
              .append(" (R$ ")
              .append(String.format("%.2f", item.getPreco()).replace('.', ','))
              .append(")\n");
        });

        sb.append("------------------------------------------------------------\n");
        sb.append("TOTAL FINAL: R$ ")
          .append(String.format("%.2f", pedido.getTotalFinal()).replace('.', ','));

        return sb.toString();
    }

    private void carregarIcone() {
        try {
            var url = getClass().getResource("/assets/imagens/iconeJanela.png");
            if (url != null) {
                setIconImage(javax.imageio.ImageIO.read(url));
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar ícone");
        }
    }
}
