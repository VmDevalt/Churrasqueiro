package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

import com.churrasqueiro.business.CaixaController;
import com.churrasqueiro.entities.Caixa;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontsConstants;

public class TelaGestao extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panelVermelho;
    private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private EstilizacaoRedonda.BotaoRedondo botaoCriarConta;
    private EstilizacaoRedonda.BotaoRedondo botaoRelatorio;
    private EstilizacaoRedonda.BotaoRedondo botaoConfiguracoes;
    private EstilizacaoRedonda.BotaoRedondo botaoItens;
    private EstilizacaoRedonda.BotaoRedondo botaoCaixa;
    private JLabel lblCaixaStatus;
    private final CaixaController caixaController = new CaixaController();
    
    Color corPaletaVermelho = new Color(179,13,36);
    Color corPaletaBege = new Color(227,202,187);
    Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteracao = new Color(35,35,35);
    Color corPaletaCinza = new Color(140,127,127);
    Color corPaletaBegeInteracao = new Color(245,225,210);
    Color corPaletaBegePressionado = new Color(200,175,160);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaGestao frame = new TelaGestao();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaGestao() {
        Usuario usuarioLogado = TelaLogin.getUsuarioLogado();

        setTitle("Gestão - Churrasqueiro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA, ALTURA);
        setResizable(false);
        setLocationRelativeTo(null);

        panelVermelho = new JPanel();
        panelVermelho.setBackground(corPaletaVermelho);
        panelVermelho.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelVermelho);
        panelVermelho.setLayout(null);

        JPanel panelBranco = new JPanel();
        panelBranco.setBackground(corPaletaBege);
        panelBranco.setBounds(0, 102, 1280, 609);
        panelVermelho.add(panelBranco);
        panelBranco.setLayout(null);

        if (usuarioLogado.getTipo().trim().equalsIgnoreCase("ADMIN")) {
            botaoCriarConta = new EstilizacaoRedonda.BotaoRedondo(
                    "Criar Conta",
                    corPaletaVermelho,
                    corPaletaVermelhoInteracao,
                    corPaletaVermelhoPressionado,
                    35
            );
            botaoCriarConta.setBounds(370, 361, 209, 38);
            panelBranco.add(botaoCriarConta);
            botaoCriarConta.setForeground(corPaletaBege);
            botaoCriarConta.setBackground(corPaletaVermelho);
            botaoCriarConta.setFont(FontsConstants.MONTSERRAT_BOLD_17);
            botaoCriarConta.addActionListener(e -> {
                dispose();
                TelaCadastro telaCadastro = new TelaCadastro();
                telaCadastro.setVisible(true);
            });
        }

        JLabel lblCaixa = new JLabel("Caixa:");
        lblCaixa.setForeground(Color.RED);
        lblCaixa.setFont(FontsConstants.MONTSERRAT_BOLD_22);
        lblCaixa.setBounds(394, 43, 89, 46);
        panelBranco.add(lblCaixa);

        lblCaixaStatus = new JLabel("...");
        lblCaixaStatus.setForeground(Color.BLACK);
        lblCaixaStatus.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        lblCaixaStatus.setBounds(472, 44, 120, 46);
        panelBranco.add(lblCaixaStatus);

        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = hoje.format(formatter);

        JLabel lblData = new JLabel(dataFormatada);
        lblData.setForeground(Color.BLACK);
        lblData.setFont(FontsConstants.MONTSERRAT_BOLD_25);
        lblData.setBounds(629, 41, 176, 46);
        panelBranco.add(lblData);

        botaoRelatorio = new EstilizacaoRedonda.BotaoRedondo(
                "Relatórios",
                corPaletaVermelho,
                corPaletaVermelhoInteracao,
                corPaletaVermelhoPressionado,
                35
        );
        botaoRelatorio.setBounds(707, 370, 209, 38);
        panelBranco.add(botaoRelatorio);
        botaoRelatorio.setForeground(corPaletaBege);
        botaoRelatorio.setBackground(corPaletaVermelho);
        botaoRelatorio.setFont(FontsConstants.MONTSERRAT_BOLD_17);
        botaoRelatorio.addActionListener(e -> {
            dispose();
            TelaRelatorios telaRelatorio = new TelaRelatorios();
            telaRelatorio.setVisible(true);
        });

        botaoConfiguracoes = new EstilizacaoRedonda.BotaoRedondo(
                "Configurações",
                corPaletaVermelho,
                corPaletaVermelhoInteracao,
                corPaletaVermelhoPressionado,
                35
        );
        botaoConfiguracoes.setBounds(370, 262, 209, 38);
        panelBranco.add(botaoConfiguracoes);
        botaoConfiguracoes.setForeground(corPaletaBege);
        botaoConfiguracoes.setBackground(corPaletaVermelho);
        botaoConfiguracoes.setFont(FontsConstants.MONTSERRAT_BOLD_17);
        botaoConfiguracoes.addActionListener(e -> {
            dispose();
            TelaConfiguracoes telaConfiguracoes = new TelaConfiguracoes();
            telaConfiguracoes.setVisible(true);
        });

        botaoItens = new EstilizacaoRedonda.BotaoRedondo(
                "Itens",
                corPaletaVermelho,
                corPaletaVermelhoInteracao,
                corPaletaVermelhoPressionado,
                35
        );
        botaoItens.setBounds(707, 262, 209, 38);
        panelBranco.add(botaoItens);
        botaoItens.setForeground(corPaletaBege);
        botaoItens.setBackground(corPaletaVermelho);
        botaoItens.setFont(FontsConstants.MONTSERRAT_BOLD_17);
        botaoItens.addActionListener(e -> {
            dispose();
            TelaItens telaItens = new TelaItens();
            telaItens.setVisible(true);
        });

        botaoCaixa = new EstilizacaoRedonda.BotaoRedondo(
                "Abrir/Fechar Caixa",
                corPaletaVermelho,
                corPaletaVermelhoInteracao,
                corPaletaVermelhoPressionado,
                35
        );
        botaoCaixa.setBounds(382, 91, 210, 24);
        panelBranco.add(botaoCaixa);
        botaoCaixa.setForeground(corPaletaBege);
        botaoCaixa.setBackground(corPaletaVermelho);
        botaoCaixa.setFont(FontsConstants.MONTSERRAT_BOLD_17);
        botaoCaixa.addActionListener(e -> acaoCaixa());

        EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo(
                "Voltar",
                corPaletaPreto,
                corPaletaPretoInteracao,
                corPaletaPreto,
                35
        );
        botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
        botaoVoltar.setForeground(corPaletaBege);
        botaoVoltar.setBackground(corPaletaPreto);
        botaoVoltar.setBounds(1115, 30, 120, 38);
        panelVermelho.add(botaoVoltar);
        botaoVoltar.addActionListener(e -> {
            dispose();
            TelaMenuPrincipal telaMenuPrincipal = new TelaMenuPrincipal();
            telaMenuPrincipal.setVisible(true);
        });

        JLabel logoLabel = new JLabel("");
        logoLabel.setIcon(new ImageIcon(TelaGestao.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        logoLabel.setBounds(20,13, 92, 79);
        panelVermelho.add(logoLabel);

        JLabel pedidosLabel = new JLabel("Pedidos");
        pedidosLabel.setBounds(215, 30, 208, 38);
        pedidosLabel.setForeground(corPaletaBege);
        pedidosLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        pedidosLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelVermelho.add(pedidosLabel);

        pedidosLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    TelaPedidos telaPedidos = new TelaPedidos();
                    telaPedidos.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            TelaGestao.this,
                            "Tela de Pedidos ainda não está disponível.",
                            "Aviso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
       			 UIManager.put("OptionPane.background", corPaletaBege);
    	         UIManager.put("Panel.background", corPaletaBege);
    	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                pedidosLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pedidosLabel.setForeground(corPaletaBege);
            }
        });

        JLabel dashboardLabel = new JLabel("Dashboard");
        dashboardLabel.setForeground(corPaletaBege);
        dashboardLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        dashboardLabel.setBounds(798, 30, 240, 38);
        dashboardLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelVermelho.add(dashboardLabel);
        
                JPanel panelSelecionado = new JPanel();
                panelSelecionado.setBounds(480, 0, 240, 102);
                panelVermelho.add(panelSelecionado);
                panelSelecionado.setBackground(corPaletaBege);
                panelSelecionado.setLayout(null);
                        
                                JLabel gestaoLabel = new JLabel("Gestão");
                                gestaoLabel.setBounds(48, 30, 152, 38);
                                panelSelecionado.add(gestaoLabel);
                                gestaoLabel.setForeground(corPaletaPreto);
                                gestaoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);

        dashboardLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    TelaDashboard telaDashboard = new TelaDashboard();
                    telaDashboard.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            TelaGestao.this,
                            "Tela de Dashboard ainda não está disponível.",
                            "Aviso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
       			 UIManager.put("OptionPane.background", corPaletaBege);
    	         UIManager.put("Panel.background", corPaletaBege);
    	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dashboardLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dashboardLabel.setForeground(corPaletaBege);
            }
        });

        java.net.URL url = getClass().getResource("/assets/imagens/iconeJanela.png");
        if (url != null) {
            try {
                Image icon = ImageIO.read(url);
                setIconImage(icon);
            } catch (java.io.IOException e) {
                System.err.println("Falha de I/O ao ler a imagem: " + e.getMessage());
            }
        }

        atualizarStatusCaixa();
    }

    private void atualizarStatusCaixa() {
        try {
            Optional<Caixa> caixaAberto = caixaController.buscarCaixaAberto();
            if (caixaAberto.isPresent()) {
                lblCaixaStatus.setText("Aberto");
                lblCaixaStatus.setForeground(new Color(0, 128, 0));
            } else {
                lblCaixaStatus.setText("Fechado");
                lblCaixaStatus.setForeground(Color.BLACK);
            }
        } catch (DatabaseException e) {
            lblCaixaStatus.setText("Erro");
            lblCaixaStatus.setForeground(Color.ORANGE);
            System.err.println("Erro ao buscar status do caixa: " + e.getMessage());
        }
    }

    private void acaoCaixa() {
        try {
            Optional<Caixa> caixaAbertoOpt = caixaController.buscarCaixaAberto();

            if (!caixaAbertoOpt.isPresent()) {

                int opcao = JOptionPane.showConfirmDialog(
                        this,
                        "Não há caixa aberto no momento.\nDeseja abrir um novo caixa?",
                        "Abrir Caixa",
                        JOptionPane.YES_NO_OPTION
                );
   			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);

                if (opcao != JOptionPane.YES_OPTION) {
                    return;
                }

                String saldoInicialStr = JOptionPane.showInputDialog(
                        this,
                        "Informe o saldo inicial do caixa:",
                        "Abrir Caixa",
                        JOptionPane.QUESTION_MESSAGE
                );
   			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);
                
                if (saldoInicialStr == null) return;

                String metaStr = JOptionPane.showInputDialog(
                        this,
                        "Informe a meta de faturamento do dia:",
                        "Abrir Caixa",
                        JOptionPane.QUESTION_MESSAGE
                );
   			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);
                
                if (metaStr == null) return;

                double saldoInicial = Double.parseDouble(saldoInicialStr.trim().replace(",", "."));
                double meta = Double.parseDouble(metaStr.trim().replace(",", "."));

                Caixa caixaCriado = caixaController.abrirCaixa(saldoInicial, meta);

                JOptionPane.showMessageDialog(
                        this,
                        "Caixa aberto com sucesso!\nID: " + caixaCriado.getId(),
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );
   			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);

                atualizarStatusCaixa();
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente fechar o caixa?",
                    "Fechar Caixa",
                    JOptionPane.YES_NO_OPTION
            );
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);

            if (confirm == JOptionPane.YES_OPTION) {
                caixaController.fecharCaixaAutomatico();
                JOptionPane.showMessageDialog(this, "Caixa fechado com sucesso!");
                atualizarStatusCaixa();
            }
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaPreto);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Valores inválidos ao abrir o caixa.\nUse apenas números (ex: 100.00).",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
            
        } catch (ControllerException | DatabaseException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
			 UIManager.put("OptionPane.background", corPaletaBege);
	         UIManager.put("Panel.background", corPaletaBege);
	         UIManager.put("OptionPane.messageForeground", corPaletaVermelho);
        }
    }
}
