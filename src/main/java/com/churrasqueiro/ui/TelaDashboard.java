package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

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
import com.toedter.calendar.JDateChooser;

public class TelaDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;

    private final Color corPaletaVermelho = new Color(179, 13, 36);
    private final Color corPaletaBege = new Color(227, 202, 187);
    private final Color corPaletaPreto = new Color(0, 0, 0);
    private final Color corPaletaPretoInteracao = new Color(35, 35, 35);

    private JPanel contentPane;
    private JPanel panel;
    private JDateChooser dateInicio;
    private JDateChooser dateFim;
    private ChartPanel chartPanelTopVendas;
    private ChartPanel chartPanelMelhoresFaturamentos;
    private ChartPanel chartPanelPagamento;

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
    }

    public TelaDashboard() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setTitle("Dashboard - Churrasqueiro");
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(corPaletaVermelho);
        setContentPane(contentPane);

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                java.awt.Image icon = javax.imageio.ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException ignored) {}
        }

        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(20, 13, 92, 79);
        logoLabel.setIcon(new ImageIcon(TelaDashboard.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        contentPane.add(logoLabel);

        JLabel pedidosLabel = new JLabel("Pedidos");
        pedidosLabel.setBounds(215, 34, 208, 38);
        pedidosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        pedidosLabel.setForeground(corPaletaBege);
        pedidosLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(pedidosLabel);

        pedidosLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    TelaPedidos telaPedidos = new TelaPedidos();
                    telaPedidos.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            TelaDashboard.this,
                            "Tela de Pedidos ainda não está disponível.",
                            "Aviso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                pedidosLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                pedidosLabel.setForeground(corPaletaBege);
            }
        });

        JLabel gestaoLabel = new JLabel("Gestão");
        gestaoLabel.setBounds(570, 34, 208, 38);
        gestaoLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        gestaoLabel.setForeground(corPaletaBege);
        gestaoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentPane.add(gestaoLabel);
        gestaoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    TelaGestao telaGestao = new TelaGestao();
                    telaGestao.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            TelaDashboard.this,
                            "Tela de Gestão ainda não está disponível.",
                            "Aviso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                gestaoLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                gestaoLabel.setForeground(corPaletaBege);
            }
        });

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

        panel = new JPanel();
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

        controller = new DashboardController();

        JLabel lblPeriodo = new JLabel("Período:");
        lblPeriodo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblPeriodo.setForeground(corPaletaPreto);
        lblPeriodo.setBounds(50, 10, 80, 25);
        panel.add(lblPeriodo);

        dateInicio = new JDateChooser();
        dateInicio.setBounds(130, 10, 150, 25);
        dateInicio.setDateFormatString("dd/MM/yyyy");
        panel.add(dateInicio);

        JLabel lblAte = new JLabel("até");
        lblAte.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblAte.setForeground(corPaletaPreto);
        lblAte.setBounds(290, 10, 30, 25);
        panel.add(lblAte);

        dateFim = new JDateChooser();
        dateFim.setBounds(330, 10, 150, 25);
        dateFim.setDateFormatString("dd/MM/yyyy");
        panel.add(dateFim);

        EstilizacaoRedonda.BotaoRedondo btnFiltrar =
                new EstilizacaoRedonda.BotaoRedondo("Aplicar filtro", corPaletaPreto, corPaletaPretoInteracao, corPaletaPreto, 35);
        btnFiltrar.setBounds(500, 8, 160, 30);
        btnFiltrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnFiltrar.setForeground(Color.WHITE);
        btnFiltrar.setBackground(corPaletaPreto);
        btnFiltrar.addActionListener(e -> aplicarFiltroDatas());
        panel.add(btnFiltrar);

        carregarGraficos(null, null);
    }

    private void aplicarFiltroDatas() {
        Date inicio = dateInicio.getDate();
        Date fim = dateFim.getDate();

        if (inicio == null && fim == null) {
            carregarGraficos(null, null);
            return;
        }

        if (inicio == null || fim == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione as duas datas (início e fim).",
                    "Período incompleto",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        LocalDate dataInicio = inicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFim = fim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (dataFim.isBefore(dataInicio)) {
            JOptionPane.showMessageDialog(
                    this,
                    "A data final não pode ser menor que a data inicial.",
                    "Período inválido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        carregarGraficos(dataInicio, dataFim);
    }

    private void carregarGraficos(LocalDate dataInicio, LocalDate dataFim) {
        if (controller == null) {
            return;
        }

        if (chartPanelTopVendas != null) {
            panel.remove(chartPanelTopVendas);
        }
        if (chartPanelMelhoresFaturamentos != null) {
            panel.remove(chartPanelMelhoresFaturamentos);
        }
        if (chartPanelPagamento != null) {
            panel.remove(chartPanelPagamento);
        }

        try {
            DefaultCategoryDataset datasetTop;
            DefaultCategoryDataset datasetFaturamento;
            DefaultPieDataset datasetFormas;

            if (dataInicio != null && dataFim != null) {
                datasetTop = controller.obterTopMaisVendidos(dataInicio, dataFim);
                datasetFaturamento = controller.obterFaturamentoPorDia(dataInicio, dataFim);
                datasetFormas = controller.obterFormasPagamento(dataInicio, dataFim);
            } else {
                datasetTop = controller.obterTopMaisVendidos();
                datasetFaturamento = controller.obterFaturamentoPorDia();
                datasetFormas = controller.obterFormasPagamento();
            }

            chartPanelTopVendas = criarGraficoBarrasHorizontais(datasetTop);
            chartPanelTopVendas.setBounds(50, 50, 500, 250);
            panel.add(chartPanelTopVendas);

            chartPanelMelhoresFaturamentos = criarGraficoBarrasVerticais(datasetFaturamento);
            chartPanelMelhoresFaturamentos.setBounds(50, 320, 500, 230);
            panel.add(chartPanelMelhoresFaturamentos);

            chartPanelPagamento = criarGraficoPizza(datasetFormas);
            chartPanelPagamento.setBounds(600, 120, 600, 380);
            panel.add(chartPanelPagamento);

            panel.revalidate();
            panel.repaint();

        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar dados do Dashboard:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.getRangeAxis().setTickLabelPaint(corPaletaPreto);
        plot.getDomainAxis().setTickLabelPaint(corPaletaPreto);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, corPaletaVermelho);
        renderer.setDrawBarOutline(false);

        chart.setBackgroundPaint(corPaletaBege);
        chart.setAntiAlias(true);
        chart.getTitle().setPaint(corPaletaPreto);

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
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.getRangeAxis().setTickLabelPaint(corPaletaPreto);
        plot.getDomainAxis().setTickLabelPaint(corPaletaPreto);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, corPaletaVermelho);
        renderer.setDrawBarOutline(false);

        chart.setBackgroundPaint(corPaletaBege);
        chart.setAntiAlias(true);
        chart.getTitle().setPaint(corPaletaPreto);

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
        plot.setLabelPaint(corPaletaPreto);

        chart.setBackgroundPaint(corPaletaBege);
        chart.getTitle().setPaint(corPaletaPreto);

        return new ChartPanel(chart);
    }
}
