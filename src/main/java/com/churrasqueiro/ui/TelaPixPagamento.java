package com.churrasqueiro.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.churrasqueiro.business.PedidoController;
import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.service.PixPaymentResponse;

public class TelaPixPagamento extends JFrame {

    private static final long serialVersionUID = 1L;

    private PedidoEmMontagem pedido;
    private PixPaymentResponse pixResponse;

    public TelaPixPagamento(PedidoEmMontagem pedido, PixPaymentResponse pixResponse) {
        this.pedido = pedido;
        this.pixResponse = pixResponse;

        setTitle("Pagamento via PIX - Churrasqueiro");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(227, 202, 187));
        setContentPane(content);

        JLabel lblTitulo = new JLabel("Escaneie o QR Code para pagar", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        content.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblQr = new JLabel("", SwingConstants.CENTER);
        ImageIcon qrIcon = criarImagemQrDeBase64(pixResponse.getQrCodeBase64());
        lblQr.setIcon(qrIcon);
        content.add(lblQr, BorderLayout.CENTER);

        JPanel panelSul = new JPanel();
        panelSul.setBackground(new Color(227, 202, 187));
        panelSul.setLayout(new BoxLayout(panelSul, BoxLayout.Y_AXIS));

        JTextArea txtCopiaCola = new JTextArea("PIX Copia e Cola:\n" + pixResponse.getQrCode());
        txtCopiaCola.setWrapStyleWord(true);
        txtCopiaCola.setLineWrap(true);
        txtCopiaCola.setEditable(false);
        txtCopiaCola.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtCopiaCola.setBackground(new Color(227, 202, 187));
        txtCopiaCola.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelSul.add(txtCopiaCola);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground(new Color(227, 202, 187));

        JButton btnJaPagou = new JButton("OK, já pagou (simular)");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotoes.add(btnJaPagou);
        panelBotoes.add(btnCancelar);

        panelSul.add(panelBotoes);

        content.add(panelSul, BorderLayout.SOUTH);

        btnJaPagou.addActionListener(e -> {
            try {
                PedidoController controller = new PedidoController();
                controller.salvar(pedido);

                JOptionPane.showMessageDialog(
                        TelaPixPagamento.this,
                        "Pagamento confirmado (simulado) e pedido salvo com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();
                TelaPedidos telaPedidos = new TelaPedidos();
                telaPedidos.setVisible(true);

            } catch (ControllerException | DatabaseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        TelaPixPagamento.this,
                        "Erro ao salvar pedido: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnCancelar.addActionListener(e -> {
            dispose();
            TelaPedidos telaPedidos = new TelaPedidos();
            telaPedidos.setVisible(true);
        });
    }

    private ImageIcon criarImagemQrDeBase64(String base64) {
        try {
            if (base64.startsWith("data:image")) {
                int commaIndex = base64.indexOf(',');
                if (commaIndex != -1) {
                    base64 = base64.substring(commaIndex + 1);
                }
            }

            byte[] bytes = Base64.getDecoder().decode(base64);
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
            if (img == null) {
                return null;
            }
            Image escala = img.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
            return new ImageIcon(escala);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
