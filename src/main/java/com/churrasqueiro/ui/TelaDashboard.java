package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class TelaDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int LARGURA = 1280;
	private static final int ALTURA = 720;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDashboard frame = new TelaDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Color corPaletaVermelho = new Color(179,13,36);
	Color corPaletaBege = new Color(227,202,187);
	Color corPaletaPreto = new Color(0,0,0);
	Color corPaletaPretoInteração = new Color(35,35,35);
	
	public TelaDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGURA, ALTURA);
		setResizable(false);
		setTitle("Dashboard - Churrasqueiro");
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
			} catch (java.io.IOException e) {
				System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
			}
		}
		
		JLabel gruposLabel = new JLabel("Gestão");
		gruposLabel.setBounds(570, 34, 208, 38);
		gruposLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		gruposLabel.setForeground(corPaletaBege);
		contentPane.add(gruposLabel);
		
		JLabel pedidosLabel = new JLabel("Pedidos");
		pedidosLabel.setBounds(215, 34, 208, 38);
		pedidosLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		pedidosLabel.setForeground(corPaletaBege);
		contentPane.add(pedidosLabel);
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setBounds(20, 13, 92, 79);
		logoLabel.setIcon(new ImageIcon(TelaDashboard.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
		contentPane.add(logoLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 102, 1266, 581);
		panel.setBackground(corPaletaBege);
		contentPane.add(panel);
		panel.setLayout(null);
		
		final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoVoltar.setBounds(1132, 513, 104, 38);
		panel.add(botaoVoltar);
		botaoVoltar.setFont(new Font("SansSerif", Font.BOLD, 18));
		botaoVoltar.setForeground(new Color(255, 255, 255));
		botaoVoltar.setBackground(new Color(0, 0, 0));
		botaoVoltar.addActionListener(e -> {
			dispose();
			TelaMenuPrincipal principal = new TelaMenuPrincipal();
			principal.setVisible(true);
		});
		
		JPanel dashboardPanel = new JPanel();
		dashboardPanel.setBounds(949, 0, 317, 102);
		dashboardPanel.setBackground(corPaletaBege);
		contentPane.add(dashboardPanel);
		dashboardPanel.setLayout(null);
		
		JLabel dashbordLabel = new JLabel("Dashboard");
		dashbordLabel.setForeground(corPaletaPreto);
		dashbordLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
		dashbordLabel.setBounds(73, 35, 208, 38);
		dashboardPanel.add(dashbordLabel);

        ChartPanel chartPanelTopVendas = criarGraficoBarrasHorizontais();
        chartPanelTopVendas.setBounds(50, 50, 500, 250);
        chartPanelTopVendas.setBackground(corPaletaBege);
        chartPanelTopVendas.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(chartPanelTopVendas);
        
        ChartPanel chartPanelMelhoresFaturamentos = criarGraficoBarrasVerticais();
        chartPanelMelhoresFaturamentos.setBounds(50, 320, 500, 230); 
        chartPanelMelhoresFaturamentos.setBackground(corPaletaBege);
        chartPanelMelhoresFaturamentos.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(chartPanelMelhoresFaturamentos);
	}

    private ChartPanel criarGraficoBarrasHorizontais() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(170, "Quantidade", "4 Queijos");
        dataset.setValue(100, "Quantidade", "BURGUER 2");
        dataset.setValue(80, "Quantidade", "BURGUER 3");
        dataset.setValue(53, "Quantidade", "BURGUER 4");
        dataset.setValue(40, "Quantidade", "BURGUER 5");
        dataset.setValue(30, "Quantidade", "BURGUER 6");

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

        chart.setBackgroundPaint(corPaletaBege);

        return new ChartPanel(chart);
    }

    private ChartPanel criarGraficoBarrasVerticais() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(5000, "Faturamento", "17/10/2025");
        dataset.setValue(2500, "Faturamento", "18/10/2025");
        dataset.setValue(1200, "Faturamento", "19/10/2025");
        dataset.setValue(800, "Faturamento", "20/10/2025");

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

        chart.setBackgroundPaint(corPaletaBege);

        return new ChartPanel(chart);
    }
}