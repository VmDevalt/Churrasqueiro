package com.churrasqueiro.ui;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

import com.churrasqueiro.business.PedidoController;
import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontsConstants;

public class TelaCartaoPagamentoGif extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel lblGif;
    private JLabel lblStatus;

    private EstilizacaoRedonda.BotaoRedondo btnOk;
    private EstilizacaoRedonda.BotaoRedondo btnCancelar;

    private final Color corVermelho = new Color(179, 13, 36);
    private final Color corVermelhoInteracao = new Color(200, 50, 50);
    private final Color corVermelhoPressionado = new Color(150, 0, 0);
    private final Color corBege = new Color(227, 202, 187);
    private final Color corPreto = new Color(0, 0, 0);
    private final Color corPretoInteracao = new Color(35, 35, 35);

    public TelaCartaoPagamentoGif(PedidoEmMontagem pedido) {

        setTitle("Pagamento com Cartão - Churrasqueiro");
        setSize(520, 470);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(null);
        content.setBackground(corBege);
        setContentPane(content);

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (Exception ignored) {}
        }
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };

        card.setBounds(50, 20, 420, 280);
        card.setLayout(null);
        card.setOpaque(false);
        content.add(card);

        try {
            URL urlGif = new URL(
                "https://http2.mlstatic.com/storage/cx-support-fcm-api/fcm-pub-os-test/cx-support-mario-frontend/mabarrella/newland-chip.gif"
            );

            ImageIcon gifOriginal = new ImageIcon(urlGif);

            Image imgScale = gifOriginal.getImage().getScaledInstance(
                    320, 240, Image.SCALE_DEFAULT
            );

            ImageIcon gifReduzido = new ImageIcon(imgScale);

            lblGif = new JLabel(gifReduzido);
            lblGif.setBounds(50, 20, 320, 240);
            card.add(lblGif);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar GIF: " + e.getMessage());
        }

        lblStatus = new JLabel("Processando pagamento...");
        lblStatus.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setBounds(40, 310, 440, 40);
        content.add(lblStatus);

        new Timer(2500, ev -> {
            lblStatus.setText("Pagamento aprovado ✓");
            lblStatus.setForeground(new Color(0, 140, 0));

            btnOk.setVisible(true);
            btnCancelar.setVisible(true);

        }) {{
            setRepeats(false);
            start();
        }};

        btnOk = new EstilizacaoRedonda.BotaoRedondo(
                "OK, finalizar pedido",
                corVermelho,
                corVermelhoInteracao,
                corVermelhoPressionado,
                35
        );
        btnOk.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnOk.setForeground(Color.WHITE);
        btnOk.setBounds(90, 360, 190, 40);
        btnOk.setVisible(false);
        content.add(btnOk);

        btnOk.addActionListener(ev -> {
            try {
                PedidoController controller = new PedidoController();
                controller.salvar(pedido);

                JOptionPane.showMessageDialog(
                        this,
                        "Pedido finalizado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();
                new TelaPedidos().setVisible(true);

            } catch (ControllerException | DatabaseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao salvar pedido: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnCancelar = new EstilizacaoRedonda.BotaoRedondo(
                "Cancelar",
                corPreto,
                corPretoInteracao,
                corPreto,
                35
        );
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBounds(300, 360, 120, 40);
        btnCancelar.setVisible(false);
        content.add(btnCancelar);

        btnCancelar.addActionListener(ev -> {
            dispose();
            new TelaPedidos().setVisible(true);
        });
    }
}
