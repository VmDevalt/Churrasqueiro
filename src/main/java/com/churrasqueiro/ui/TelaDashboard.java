package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.churrasqueiro.business.DashboardController;
import com.churrasqueiro.exceptions.DatabaseException;

public class TelaDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    private final Color corPaletaVermelho = new Color(179, 13, 36);
    private final Color corPaletaBege = new Color(227, 202, 187);
    private final Color corPaletaPreto = new Color(0, 0, 0);
    private final Color corPaletaPretoInteracao = new Color(35, 35, 35);

    private DashboardController controller;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaDashboard frame = new TelaDashboard();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    };

    public TelaDashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setTitle("Dashboard - Churrasqueiro");
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(corPaletaVermelho);

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException ignored) {}
        }

        JLabel pedidosLabel = new JLabel("Pedidos");
        pedidosLabel.setBounds(215, 34, 208, 38);
        pedidosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        pedidosLabel.setForeground(corPaletaBege);
        contentPane.add(pedidosLabel);

        JLabel gruposLabel = new JLabel("Gestão");
        gruposLabel.setBounds(570, 34, 208, 38);
        gruposLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        gruposLabel.setForeground(corPaletaBege);
        contentPane.add(gruposLabel);

        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(20, 13, 92, 79);
        logoLabel.setIcon(new ImageIcon(TelaDashboard.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        contentPane.add(logoLabel);

        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setBounds(949, 0, 317, 102);
        dashboardPanel.setBackground(corPaletaBege);
        dashboardPanel.setLayout(null);
        contentPane.add(dashboardPanel);

        JLabel dashbordLabel = new JLabel("Dashboard");
        dashbordLabel.setForeground(corPaletaPreto);
        dashbordLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        dashbordLabel.setBounds(73, 35, 208, 38);
        dashboardPanel.add(dashbordLabel);

        JPanel panel = new JPanel();
        panel.setBounds(0, 102, 1266, 581);
        panel.setBackground(corPaletaBege);
        panel.setLayout(null);
        contentPane.add(panel);

        EstilizacaoRedonda.BotaoRedondo botaoVoltar =
                new EstilizacaoRedonda.BotaoRedondo("Voltar", corPaletaPreto, corPaletaPretoInteracao, corPaletaPreto, 35);
        botaoVoltar.setBounds(1132, 513, 104, 38);
        botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setBackground(corPaletaPreto);
        botaoVoltar.addActionListener(e -> {
            dispose();
            TelaMenuPrincipal principal = new TelaMenuPrincipal();
            principal.setVisible(true);
        });
        panel.add(botaoVoltar);

        try {
            controller = new DashboardController();

            DefaultCategoryDataset datasetTop = controller.obterTopMaisVendidos();
            DefaultCategoryDataset datasetFaturamento = controller.obterFaturamentoPorDia();
            DefaultPieDataset datasetFormas = controller.obterFormasPagamento();

            ChartPanel chartPanelTopVendas = criarGraficoBarrasHorizontais(datasetTop);
            chartPanelTopVendas.setBounds(50, 50, 500, 250);
            panel.add(chartPanelTopVendas);

            ChartPanel chartPanelMelhoresFaturamentos = criarGraficoBarrasVerticais(datasetFaturamento);
            chartPanelMelhoresFaturamentos.setBounds(50, 320, 500, 230);
            panel.add(chartPanelMelhoresFaturamentos);

            ChartPanel chartPanelPagamento = criarGraficoPizza(datasetFormas);
            chartPanelPagamento.setBounds(600, 120, 600, 380);
            panel.add(chartPanelPagamento);

        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar dados do Dashboard:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        setLocationRelativeTo(null);
    }

    private ChartPanel criarGraficoBarrasHorizontais(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Top Mais Vendidos",
                null,
                "Quantidade",
                dataset,
                PlotOrientation.HORIZONTAL,
                false,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(corPaletaBege);
        plot.setRangeGridlinePaint(Color.lightGray);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, corPaletaVermelho);
        renderer.setDrawBarOutline(false);

        chart.setBackgroundPaint(corPaletaBege);
        chart.setAntiAlias(true);

        return new ChartPanel(chart);
    }

    private ChartPanel criarGraficoBarrasVerticais(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Dias Com Melhores Faturamentos",
                "Data",
                "R$",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(corPaletaBege);
        plot.setRangeGridlinePaint(Color.lightGray);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, corPaletaVermelho);
        renderer.setDrawBarOutline(false);

        chart.setBackgroundPaint(corPaletaBege);
        chart.setAntiAlias(true);

        return new ChartPanel(chart);
    }

    private ChartPanel criarGraficoPizza(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Formas de Pagamento",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(corPaletaBege);
        plot.setOutlineVisible(false);

        chart.setBackgroundPaint(corPaletaBege);

        return new ChartPanel(chart);
    }
}
