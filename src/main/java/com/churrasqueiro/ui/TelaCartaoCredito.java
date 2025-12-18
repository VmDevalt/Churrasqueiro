package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.Font;
import java.time.YearMonth;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.churrasqueiro.entities.PedidoEmMontagem;
import com.churrasqueiro.utils.FontsConstants;

public class TelaCartaoCredito extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final int LARGURA = 600;
    private static final int ALTURA = 420;

    private JPanel contentPane;

    private EstilizacaoRedonda.CaixaTextoRedonda campoNomeTitular;
    private EstilizacaoRedonda.CaixaTextoRedonda campoNumeroCartao;
    private EstilizacaoRedonda.CaixaTextoRedonda campoDataVencimento;
    private EstilizacaoRedonda.CaixaTextoRedonda campoCvv;

    private JLabel labelBandeiraTexto;

    private boolean pagamentoConfirmado = false;
    private PedidoEmMontagem pedido;

    private final Color corPaletaVermelho = new Color(179,13,36);
    private final Color corPaletaBege = new Color(227,202,187);
    private final Color corPaletaVermelhoInteracao = new Color(200,50,50);
    private final Color corPaletaVermelhoPressionado = new Color(150,0,0);
    private final Color corPaletaCinza = new Color(140,127,127);

    public TelaCartaoCredito(JFrame parent, PedidoEmMontagem pedido) {
        super(parent, "Pagamento com Cartão", true);
        this.pedido = pedido;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(677, 470);
        setResizable(false);
        setLocationRelativeTo(parent);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(corPaletaBege);
        setContentPane(contentPane);

        montarTela();
        configurarListenerBandeira();
    }

    private void montarTela() {

        JLabel titulo = new JLabel("Dados do Cartão");
        titulo.setBounds(200, 20, 260, 30);
        titulo.setForeground(corPaletaVermelho);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        contentPane.add(titulo);

        campoNomeTitular = criarCampo(260, 80, 220, 30);
        campoNumeroCartao = criarCampo(260, 130, 220, 30);
        campoDataVencimento = criarCampo(260, 180, 140, 30);
        campoCvv = criarCampo(260, 230, 140, 30);

        contentPane.add(criarLabel("Nome do titular", 60, 80));
        contentPane.add(criarLabel("Número do cartão", 60, 130));
        contentPane.add(criarLabel("Validade (MM/AA)", 60, 180));
        contentPane.add(criarLabel("CVV", 60, 230));

        labelBandeiraTexto = new JLabel("DESCONHECIDO");
        labelBandeiraTexto.setBounds(500, 130, 120, 30);
        labelBandeiraTexto.setForeground(corPaletaVermelho);
        labelBandeiraTexto.setFont(new Font("SansSerif", Font.BOLD, 14));
        contentPane.add(labelBandeiraTexto);

        EstilizacaoRedonda.BotaoRedondo botaoConfirmar =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Confirmar Pagamento",
                        corPaletaVermelho,
                        corPaletaVermelhoInteracao,
                        corPaletaVermelhoPressionado,
                        35
                );

        botaoConfirmar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        botaoConfirmar.setForeground(corPaletaBege);
        botaoConfirmar.setBounds(308, 348, 286, 35);
        contentPane.add(botaoConfirmar);

        botaoConfirmar.addActionListener(e -> confirmarPagamento());

        EstilizacaoRedonda.BotaoRedondo botaoFake =
                new EstilizacaoRedonda.BotaoRedondo(
                        "Gerar cartão fake",
                        corPaletaCinza,
                        corPaletaVermelhoInteracao,
                        corPaletaVermelhoPressionado,
                        25
                );
        botaoFake.setText("Gerar cartão");

        botaoFake.setFont(FontsConstants.MONTSERRAT_BOLD_14);
        botaoFake.setForeground(Color.BLACK);
        botaoFake.setBounds(66, 350, 205, 35);
        contentPane.add(botaoFake);

        botaoFake.addActionListener(e -> gerarCartaoFake());
    }

    private EstilizacaoRedonda.CaixaTextoRedonda criarCampo(int x, int y, int w, int h) {
        EstilizacaoRedonda.CaixaTextoRedonda campo =
                new EstilizacaoRedonda.CaixaTextoRedonda(
                        "",
                        corPaletaVermelho,
                        corPaletaBege,
                        corPaletaCinza,
                        2,
                        35
                );
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setBounds(x, y, w, h);
        contentPane.add(campo);
        return campo;
    }

    private JLabel criarLabel(String texto, int x, int y) {
        JLabel label = new JLabel(texto);
        label.setForeground(corPaletaVermelho);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setBounds(x, y, 200, 30);
        return label;
    }

    private void confirmarPagamento() {

        String nome = campoNomeTitular.getText().trim().toUpperCase();
        String numero = campoNumeroCartao.getText().replaceAll("\\s", "");
        String validade = campoDataVencimento.getText();
        String cvv = campoCvv.getText();

        if (!validarNome(nome)) {
            JOptionPane.showMessageDialog(this, "Nome do titular inválido.");
            return;
        }

        if (!validarNumeroCartao(numero)) {
            JOptionPane.showMessageDialog(this, "Número do cartão inválido.");
            return;
        }

        if (!validarValidade(validade)) {
            JOptionPane.showMessageDialog(this, "Data de vencimento inválida ou cartão vencido.");
            return;
        }

        BandeiraCartao bandeira = identificarBandeira(numero);

        if (!validarCvv(cvv, bandeira)) {
            JOptionPane.showMessageDialog(this, "CVV inválido para a bandeira.");
            return;
        }

        pagamentoConfirmado = true;

        JOptionPane.showMessageDialog(
                this,
                "Pagamento com cartão confirmado.",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();
    }

    private void configurarListenerBandeira() {

        campoNumeroCartao.getDocument().addDocumentListener(new DocumentListener() {

            private void atualizar() {
                BandeiraCartao bandeira =
                        identificarBandeira(campoNumeroCartao.getText());
                labelBandeiraTexto.setText(bandeira.name());
            }

            public void insertUpdate(DocumentEvent e) { atualizar(); }
            public void removeUpdate(DocumentEvent e) { atualizar(); }
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    private BandeiraCartao identificarBandeira(String numero) {

        numero = numero.replaceAll("\\s", "");

        if (numero.isEmpty()) return BandeiraCartao.DESCONHECIDO;

        if (numero.startsWith("4")) return BandeiraCartao.VISA;

        if (numero.length() >= 2) {
            int dois = Integer.parseInt(numero.substring(0, 2));
            int quatro = numero.length() >= 4 ? Integer.parseInt(numero.substring(0, 4)) : 0;

            if ((dois >= 51 && dois <= 55) || (quatro >= 2221 && quatro <= 2720))
                return BandeiraCartao.MASTERCARD;

            if (dois == 34 || dois == 37)
                return BandeiraCartao.AMEX;

            if (dois == 50 || (dois >= 56 && dois <= 69))
                return BandeiraCartao.MAESTRO;

            if (numero.startsWith("4011") || numero.startsWith("4312") || numero.startsWith("4389"))
                return BandeiraCartao.ELO;
        }

        return BandeiraCartao.DESCONHECIDO;
    }

    private void gerarCartaoFake() {

        BandeiraCartao[] bandeiras = {
            BandeiraCartao.VISA,
            BandeiraCartao.MASTERCARD,
            BandeiraCartao.AMEX,
            BandeiraCartao.ELO,
            BandeiraCartao.MAESTRO
        };

        BandeiraCartao bandeira =
                bandeiras[new Random().nextInt(bandeiras.length)];

        campoNomeTitular.setText("CLIENTE");
        campoNumeroCartao.setText(gerarNumeroFake(bandeira));
        campoDataVencimento.setText(gerarValidade());
        campoCvv.setText(gerarCvv(bandeira));
    }

    private String gerarNumeroFake(BandeiraCartao bandeira) {

        Random r = new Random();
        String prefixo;

        switch (bandeira) {
            case VISA:
                prefixo = "4";
                break;
            case MASTERCARD:
                prefixo = "51";
                break;
            case AMEX:
                prefixo = "34";
                break;
            case MAESTRO:
                prefixo = "56";
                break;
            case ELO:
                prefixo = "4011";
                break;
            default:
                prefixo = "4";
                break;
        }

        int tamanho = (bandeira == BandeiraCartao.AMEX) ? 15 : 16;
        StringBuilder numero = new StringBuilder(prefixo);

        while (numero.length() < tamanho - 1) {
            numero.append(r.nextInt(10));
        }

        numero.append(calcularLuhn(numero.toString()));
        return numero.toString();
    }

    private int calcularLuhn(String base) {
        int soma = 0;
        boolean alternar = true;

        for (int i = base.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(base.charAt(i));
            if (alternar) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            soma += n;
            alternar = !alternar;
        }
        return (10 - (soma % 10)) % 10;
    }

    private String gerarValidade() {
        YearMonth ym = YearMonth.now().plusYears(3);
        return String.format("%02d/%02d", ym.getMonthValue(), ym.getYear() % 100);
    }

    private String gerarCvv(BandeiraCartao bandeira) {
        Random r = new Random();
        return bandeira == BandeiraCartao.AMEX
                ? String.valueOf(1000 + r.nextInt(9000))
                : String.valueOf(100 + r.nextInt(900));
    }

    private boolean validarNome(String nome) {
        return nome.matches("[A-ZÁ-Ú ]{5,}");
    }

    private boolean validarNumeroCartao(String numero) {

        if (!numero.matches("\\d{13,19}")) return false;

        int soma = 0;
        boolean alternar = false;

        for (int i = numero.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(numero.charAt(i));
            if (alternar) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            soma += n;
            alternar = !alternar;
        }
        return soma % 10 == 0;
    }

    private boolean validarValidade(String validade) {

        if (!validade.matches("\\d{2}/\\d{2}")) return false;

        int mes = Integer.parseInt(validade.substring(0, 2));
        int ano = Integer.parseInt(validade.substring(3, 5)) + 2000;

        if (mes < 1 || mes > 12) return false;

        YearMonth hoje = YearMonth.now();
        YearMonth cartao = YearMonth.of(ano, mes);

        return !cartao.isBefore(hoje);
    }

    private boolean validarCvv(String cvv, BandeiraCartao bandeira) {
        if (bandeira == BandeiraCartao.AMEX) {
            return cvv.matches("\\d{4}");
        }
        return cvv.matches("\\d{3}");
    }

    public boolean isPagamentoConfirmado() {
        return pagamentoConfirmado;
    }

    public enum BandeiraCartao {
        VISA,
        MASTERCARD,
        AMEX,
        ELO,
        MAESTRO,
        DESCONHECIDO
    }
}
