package com.churrasqueiro.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
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
import com.churrasqueiro.utils.FontsConstants;

public class TelaPixPagamento extends JFrame {

    private static final long serialVersionUID = 1L;

    private PedidoEmMontagem pedido;
    private PixPaymentResponse pixResponse;

    private final Color corVermelho = new Color(179,13,36);
    private final Color corBege = new Color(227,202,187);
    private final Color corBegeInteracao = new Color(245,225,210);
    private final Color corBegePressionado = new Color(200,175,160);
    private final Color corPreto = new Color(0,0,0);
    private final Color corPretoInteracao = new Color(35,35,35);
    

    public TelaPixPagamento(PedidoEmMontagem pedido, PixPaymentResponse pixResponse) {
        this.pedido = pedido;
        this.pixResponse = pixResponse;

        setTitle("Pagamento via PIX - Churrasqueiro");
        setSize(600, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(corBege);
        setContentPane(content);

        JLabel lblTitulo = new JLabel("Escaneie o QR Code para pagar", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        content.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblQr = new JLabel("", SwingConstants.CENTER);
        ImageIcon qrIcon = criarImagemQrDeBase64(pixResponse.getQrCodeBase64());
        lblQr.setIcon(qrIcon);
        content.add(lblQr, BorderLayout.CENTER);

        JPanel panelSul = new JPanel();
        panelSul.setBackground(corBege);
        panelSul.setLayout(new BoxLayout(panelSul, BoxLayout.Y_AXIS));

        JTextArea txtCopiaCola = new JTextArea("PIX Copia e Cola:\n" + pixResponse.getQrCode());
        txtCopiaCola.setWrapStyleWord(true);
        txtCopiaCola.setLineWrap(true);
        txtCopiaCola.setEditable(false);
        txtCopiaCola.setFont(FontsConstants.MONTSERRAT_LIGHT_13);
        txtCopiaCola.setBackground(corBege);
        txtCopiaCola.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelSul.add(txtCopiaCola);

         java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (Exception ignored) {}
        }
        
        EstilizacaoRedonda.BotaoRedondo btnCopiar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Copiar código PIX",
                        corVermelho,
                        new Color(200,50,50),
                        new Color(150,0,0),
                        35
                );
        btnCopiar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        btnCopiar.setForeground(Color.WHITE);
        btnCopiar.setAlignmentX(CENTER_ALIGNMENT);

        btnCopiar.addActionListener(e -> {
            Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(new StringSelection(pixResponse.getQrCode()), null);
            JOptionPane.showMessageDialog(this, "Código PIX copiado!", "Copiado",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel painelBtnCopiar = new JPanel();
        painelBtnCopiar.setBackground(corBege);
        painelBtnCopiar.add(btnCopiar);
        panelSul.add(painelBtnCopiar);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setBackground(corBege);

        EstilizacaoRedonda.BotaoRedondo btnJaPagou =
                new EstilizacaoRedonda.BotaoRedondo(
                        "OK, já pagou",
                        corVermelho,
                        new Color(200,50,50),
                        new Color(150,0,0),
                        35
                );
        btnJaPagou.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        btnJaPagou.setForeground(Color.WHITE);

        EstilizacaoRedonda.BotaoRedondo btnCancelar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Cancelar",
                        corPreto,
                        corPretoInteracao,
                        corPreto,
                        35
                );
        btnCancelar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        btnCancelar.setForeground(Color.WHITE);

        panelBotoes.add(btnJaPagou);
        panelBotoes.add(btnCancelar);
        panelSul.add(panelBotoes);

        content.add(panelSul, BorderLayout.SOUTH);

        btnJaPagou.addActionListener(e -> {
            try {
                PedidoController controller = new PedidoController();
                controller.salvar(pedido);

                JOptionPane.showMessageDialog(
                        this,
                        "Pagamento confirmado (simulado) e pedido salvo com sucesso!",
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
        });

        btnCancelar.addActionListener(e -> {
            dispose();
            new TelaPedidos().setVisible(true);
        });
    }

    private ImageIcon criarImagemQrDeBase64(String base64) {
        try {
            if (base64.startsWith("data:image")) {
                int idx = base64.indexOf(',');
                base64 = base64.substring(idx + 1);
            }
            byte[] bytes = Base64.getDecoder().decode(base64);
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
            Image escala = img.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
            return new ImageIcon(escala);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
