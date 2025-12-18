package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.churrasqueiro.business.CaixaController;
import com.churrasqueiro.business.RelatoriosController;
import com.churrasqueiro.entities.Caixa;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontsConstants;

public class TelaRelatorios extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    private JLabel labelValorVendas;
    private JLabel itemMaisVendidoLabel;
    private JLabel unidadesLabel;

    private JComboBox<String> vendasComboBox;
    private JComboBox<String> maisVendidoComboBox;
    private JLabel lblCaixaHojeValor;
    private JLabel lblCaixaComecouValor;
    private JLabel lblMetaFaturamentoValor;

    private final RelatoriosController relController = new RelatoriosController();
    private final CaixaController caixaController = new CaixaController();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaRelatorios frame = new TelaRelatorios();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaRelatorios() {

        Color corPaletaVermelho = new Color(179, 13, 36);
        Color corPaletaBege = new Color(227, 202, 187);
        Color corPaletaPreto = new Color(0, 0, 0);
        Color corPaletaPretoInteracao = new Color(35, 35, 35);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, LARGURA, ALTURA);
        setResizable(false);
        setTitle("Relatórios - Churrasqueiro");
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(corPaletaVermelho);
        contentPane.setLayout(null);

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                Image icon = ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha ao carregar ícone da janela.");
            }
        }

        JPanel panel = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaBege,null);
        panel.setBounds(-25, 102, 1291, 581);
        panel.setBackground(corPaletaBege);
        panel.setLayout(null);
        contentPane.add(panel);

        JLabel vendasLabel = new JLabel("Vendas");
        vendasLabel.setFont(FontsConstants.MONTSERRAT_BOLD_34);
        vendasLabel.setForeground(corPaletaPreto);
        vendasLabel.setBounds(145, 101, 173, 45);
        panel.add(vendasLabel);

        labelValorVendas = new JLabel("R$ 0,00");
        labelValorVendas.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        labelValorVendas.setForeground(corPaletaVermelho);
        labelValorVendas.setBounds(97, 145, 350, 51);
        panel.add(labelValorVendas);

        vendasComboBox = new EstilizacaoRedonda.ComboBoxRedondo<>(new String[]{}, corPaletaBege, corPaletaVermelho, 2, 20);
        vendasComboBox.setBounds(427, 122, 160, 30);
        vendasComboBox.setFont(FontsConstants.MONTSERRAT_REGULAR_15);

        panel.add(vendasComboBox);

        JLabel maisVendidosLabel = new JLabel("Mais Vendido");
        maisVendidosLabel.setFont(FontsConstants.MONTSERRAT_BOLD_34);
        maisVendidosLabel.setForeground(corPaletaPreto);
        maisVendidosLabel.setBounds(747, 101, 253, 45);
        panel.add(maisVendidosLabel);

        itemMaisVendidoLabel = new JLabel("N/A");
        itemMaisVendidoLabel.setForeground(corPaletaVermelho);
        itemMaisVendidoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        itemMaisVendidoLabel.setBounds(723, 145, 500, 51);
        panel.add(itemMaisVendidoLabel);

        unidadesLabel = new JLabel("0 unidades");
        unidadesLabel.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        unidadesLabel.setBounds(916, 195, 200, 28);
        panel.add(unidadesLabel);

        maisVendidoComboBox =  new EstilizacaoRedonda.ComboBoxRedondo<>(new String[]{}, corPaletaBege, corPaletaVermelho, 2, 20);
        maisVendidoComboBox.setBounds(1076, 120, 160, 30);
        maisVendidoComboBox.setFont(FontsConstants.MONTSERRAT_REGULAR_15);

        panel.add(maisVendidoComboBox);

        String[] opcoes = { "HOJE", "ÚLTIMA SEMANA", "ÚLTIMO MÊS", "ÚLTIMO ANO" };
        for (String item : opcoes) {
            vendasComboBox.addItem(item);
            maisVendidoComboBox.addItem(item);
        }

        vendasComboBox.setSelectedItem("ÚLTIMO ANO");
        maisVendidoComboBox.setSelectedItem("ÚLTIMO ANO");

        vendasComboBox.addActionListener(e -> atualizarRelatorios());
        maisVendidoComboBox.addActionListener(e -> atualizarRelatorios());

        JLabel tituloCaixaLabel = new JLabel("Caixa");
        tituloCaixaLabel.setFont(FontsConstants.MONTSERRAT_BOLD_32);
        tituloCaixaLabel.setForeground(corPaletaPreto);
        tituloCaixaLabel.setBounds(190, 320, 200, 40);
        panel.add(tituloCaixaLabel);

        JLabel caixaHojeLabel = new JLabel("Caixa hoje:");
        caixaHojeLabel.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        caixaHojeLabel.setForeground(corPaletaPreto);
        caixaHojeLabel.setBounds(90, 370, 150, 25);
        panel.add(caixaHojeLabel);

        lblCaixaHojeValor = new JLabel("Nenhum caixa aberto");
        lblCaixaHojeValor.setFont(FontsConstants.MONTSERRAT_BOLD_28);
        lblCaixaHojeValor.setForeground(corPaletaVermelho);
        lblCaixaHojeValor.setBounds(41, 400, 350, 40);
        panel.add(lblCaixaHojeValor);

        JLabel setaLabel = new JLabel("➜");
        setaLabel.setFont(FontsConstants.MONTSERRAT_BOLD_28);
        setaLabel.setForeground(corPaletaPreto);
        setaLabel.setBounds(350, 400, 40, 40);
        panel.add(setaLabel);

        JLabel caixaComecouLabel = new JLabel("Caixa começou:");
        caixaComecouLabel.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        caixaComecouLabel.setForeground(corPaletaPreto);
        caixaComecouLabel.setBounds(480, 370, 200, 25);
        panel.add(caixaComecouLabel);

        lblCaixaComecouValor = new JLabel("Nenhum caixa aberto");
        lblCaixaComecouValor.setFont(FontsConstants.MONTSERRAT_BOLD_28);
        lblCaixaComecouValor.setForeground(corPaletaVermelho);
        lblCaixaComecouValor.setBounds(427, 400, 350, 40);
        panel.add(lblCaixaComecouValor);

        JLabel metaTituloLabel = new JLabel("Meta de Faturamento");
        metaTituloLabel.setFont(FontsConstants.MONTSERRAT_BOLD_32);
        metaTituloLabel.setForeground(corPaletaPreto);
        metaTituloLabel.setBounds(780, 320, 370, 25);
        panel.add(metaTituloLabel);

        JLabel metaSubLabel = new JLabel("de hoje");
        metaSubLabel.setFont(FontsConstants.MONTSERRAT_BOLD_16);
        metaSubLabel.setForeground(corPaletaPreto);
        metaSubLabel.setBounds(1030, 330, 100, 40);
        panel.add(metaSubLabel);

        lblMetaFaturamentoValor = new JLabel("Nenhum caixa aberto");
        lblMetaFaturamentoValor.setFont(FontsConstants.MONTSERRAT_BOLD_32);
        lblMetaFaturamentoValor.setForeground(corPaletaVermelho);
        lblMetaFaturamentoValor.setBounds(839, 394, 400, 50);
        panel.add(lblMetaFaturamentoValor);

        JLabel relatoriosLabel = new JLabel("Relatórios");
        relatoriosLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        relatoriosLabel.setForeground(corPaletaBege);
        relatoriosLabel.setBounds(522, 34, 230, 38);
        contentPane.add(relatoriosLabel);

        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        logoLabel.setBounds(30, 12, 92, 82);
        contentPane.add(logoLabel);

        EstilizacaoRedonda.BotaoRedondo botaoVoltar =
                new EstilizacaoRedonda.BotaoRedondo("Voltar", corPaletaPreto, corPaletaPretoInteracao, corPaletaPreto, 35);

        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setBounds(1132, 34, 104, 38);
        contentPane.add(botaoVoltar);

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaGestao telaGestao = new TelaGestao();
                telaGestao.setVisible(true);
                dispose();
            }
        });

        atualizarRelatorios();
        atualizarResumoCaixa();
    }

    private String formatarMoeda(double valor) {
        return "R$ " + String.format("%.2f", valor).replace('.', ',');
    }

    private void atualizarRelatorios() {
        try {
            String periodoVendas = vendasComboBox.getSelectedItem().toString();
            String periodoMaisVendido = maisVendidoComboBox.getSelectedItem().toString();

            double total = relController.getTotalVendas(periodoVendas);
            String item = relController.getItemMaisVendido(periodoMaisVendido);
            int unidades = relController.getUnidadesMaisVendido(periodoMaisVendido);

            labelValorVendas.setText(formatarMoeda(total));
            itemMaisVendidoLabel.setText(item);
            unidadesLabel.setText(unidades + " unidades");

        } catch (DatabaseException ex) {
            System.err.println("Erro ao atualizar relatórios: " + ex.getMessage());
        }
    }

    private void atualizarResumoCaixa() {
        try {
            Optional<Caixa> caixaOpt = caixaController.buscarCaixaAberto();

            if (caixaOpt.isPresent()) {
                Caixa c = caixaOpt.get();
                lblCaixaHojeValor.setText(formatarMoeda(c.getSaldoAtual()));
                lblCaixaComecouValor.setText(formatarMoeda(c.getSaldoInicial()));
                lblMetaFaturamentoValor.setText(formatarMoeda(c.getMetaFaturamento()));
            } else {
                lblCaixaHojeValor.setText("Nenhum caixa aberto");
                lblCaixaComecouValor.setText("Nenhum caixa aberto");
                lblMetaFaturamentoValor.setText("Nenhum caixa aberto");
            }

        } catch (DatabaseException ex) {
            System.err.println("Erro ao atualizar resumo do caixa: " + ex.getMessage());
            lblCaixaHojeValor.setText("Erro");
            lblCaixaComecouValor.setText("Erro");
            lblMetaFaturamentoValor.setText("Erro");
        }
    }
}
