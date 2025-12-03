package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import com.churrasqueiro.business.RelatoriosController;
import com.churrasqueiro.exceptions.DatabaseException;

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

    private final RelatoriosController relController = new RelatoriosController();

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

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(corPaletaVermelho);
        contentPane.setLayout(null);

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha ao carregar ícone da janela.");
            }
        }

        JPanel panel = new JPanel();
        panel.setBounds(0, 102, 1266, 581);
        panel.setBackground(corPaletaBege);
        panel.setLayout(null);
        contentPane.add(panel);

        JLabel vendasLabel = new JLabel("Vendas");
        vendasLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        vendasLabel.setForeground(corPaletaPreto);
        vendasLabel.setBounds(145, 101, 173, 45);
        panel.add(vendasLabel);

        labelValorVendas = new JLabel("R$ 0,00");
        labelValorVendas.setFont(new Font("SansSerif", Font.BOLD, 45));
        labelValorVendas.setForeground(corPaletaVermelho);
        labelValorVendas.setBounds(97, 145, 350, 51);
        panel.add(labelValorVendas);

        vendasComboBox = new JComboBox<>();
        vendasComboBox.setBounds(427, 122, 160, 25);
        panel.add(vendasComboBox);

        JLabel maisVendidosLabel = new JLabel("Mais Vendido");
        maisVendidosLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        maisVendidosLabel.setForeground(corPaletaPreto);
        maisVendidosLabel.setBounds(747, 101, 253, 45);
        panel.add(maisVendidosLabel);

        itemMaisVendidoLabel = new JLabel("N/A");
        itemMaisVendidoLabel.setForeground(corPaletaVermelho);
        itemMaisVendidoLabel.setFont(new Font("SansSerif", Font.BOLD, 45));
        itemMaisVendidoLabel.setBounds(723, 145, 500, 51);
        panel.add(itemMaisVendidoLabel);

        unidadesLabel = new JLabel("0 unidades");
        unidadesLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        unidadesLabel.setBounds(916, 195, 200, 28);
        panel.add(unidadesLabel);

        maisVendidoComboBox = new JComboBox<>();
        maisVendidoComboBox.setBounds(1123, 122, 160, 25);
        panel.add(maisVendidoComboBox);

        String[] opcoes = { "HOJE", "ÚLTIMA SEMANA", "ÚLTIMO MÊS", "ÚLTIMO ANO" };
        for (String item : opcoes) {
            vendasComboBox.addItem(item);
            maisVendidoComboBox.addItem(item);
        }

        vendasComboBox.addActionListener(e -> atualizarRelatorios());
        maisVendidoComboBox.addActionListener(e -> atualizarRelatorios());

        JLabel relatoriosLabel = new JLabel("Relatórios");
        relatoriosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        relatoriosLabel.setForeground(corPaletaBege);
        relatoriosLabel.setBounds(487, 34, 208, 38);
        contentPane.add(relatoriosLabel);

        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaRelatorios.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        logoLabel.setBounds(30, 10, 92, 82);
        contentPane.add(logoLabel);

        EstilizacaoRedonda.BotaoRedondo botaoVoltar =
                new EstilizacaoRedonda.BotaoRedondo("Voltar", corPaletaPreto, corPaletaPretoInteracao, corPaletaPreto, 35);

        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
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
    }

    private void atualizarRelatorios() {
        try {
            String periodoVendas = vendasComboBox.getSelectedItem().toString();
            String periodoMaisVendido = maisVendidoComboBox.getSelectedItem().toString();

            double total = relController.getTotalVendas(periodoVendas);
            String item = relController.getItemMaisVendido(periodoMaisVendido);
            int unidades = relController.getUnidadesMaisVendido(periodoMaisVendido);

            labelValorVendas.setText("R$ " + String.format("%.2f", total).replace('.', ','));
            itemMaisVendidoLabel.setText(item);
            unidadesLabel.setText(unidades + " unidades");

        } catch (DatabaseException ex) {
            System.err.println("Erro ao atualizar relatórios: " + ex.getMessage());
        }
    }
}
