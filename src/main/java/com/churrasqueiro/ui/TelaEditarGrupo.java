package com.churrasqueiro.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.churrasqueiro.business.CategoriaController;
import com.churrasqueiro.entities.Categoria;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.FontsConstants;

public class TelaEditarGrupo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int LARGURA = 1280;
    private static final int ALTURA = 720;
    private EstilizacaoRedonda.CaixaTextoRedonda campoNomeGrupo;
    private EstilizacaoRedonda.CaixaTextoRedonda campoDescricaoGrupo;
	private Categoria categoria;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarGrupo frame = new TelaEditarGrupo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEditarGrupo() {
	}

	private void carregarDados() {
		campoNomeGrupo.setText(categoria.getNome());
		campoDescricaoGrupo.setText(categoria.getDescricao());
	}
	
    Color corPaletaBege = new Color(227,202,187);
    Color corPaletaVermelho = new Color(179,13,36);
    Color corPaletaVermelhoInteracao = new Color(200,50,50);
    Color corPaletaVermelhoPressionado = new Color(150,0,0);
    Color corPaletaBranco = new Color(255,255,255);
    Color corPaletaPreto = new Color(0,0,0);
    Color corPaletaPretoInteração = new Color(35,35,35);
    Color corPaletaCinza = new Color(140,127,127);

	public TelaEditarGrupo(Categoria categoria) {
		this.categoria = categoria;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(LARGURA, ALTURA);
		setTitle("Editar Grupo - Churrasqueiro");
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
        contentPane.setLayout(null);
        
        JLabel gruposLabel = new JLabel("Grupos");
        gruposLabel.setBounds(560, 34, 208, 38);
        gruposLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        gruposLabel.setForeground(corPaletaBege);
        contentPane.add(gruposLabel);
        
        JLabel logoLabel = new JLabel("");
        logoLabel.setBounds(30, 12, 92, 82);
        logoLabel.setIcon(new ImageIcon(TelaEditarGrupo.class.getResource("/assets/imagens/iconeJanelaPequena.png")));
        contentPane.add(logoLabel);

        JPanel panel = new EstilizacaoRedonda.PainelRedondo(null,60,4,corPaletaBege,null);
        panel.setBounds(-25, 102, 1291, 581);
        panel.setBackground(corPaletaBege);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel editarGrupoLabel = new JLabel("Editar Grupo");
        editarGrupoLabel.setBounds(505, 27, 353, 68);
        panel.add(editarGrupoLabel);
        editarGrupoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_40);
        editarGrupoLabel.setForeground(corPaletaVermelho);
        
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setBounds(65, 145, 187, 32);
        panel.add(nomeLabel);
        nomeLabel.setFont(FontsConstants.MONTSERRAT_BOLD_20);
        nomeLabel.setForeground(corPaletaPreto);
        
        this.campoDescricaoGrupo = new EstilizacaoRedonda.CaixaTextoRedonda("Digite o nome do Grupo",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
        campoDescricaoGrupo.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
        campoDescricaoGrupo.setBounds(65, 280, 1135, 38);
		panel.add(campoDescricaoGrupo);
		campoDescricaoGrupo.setColumns(10);
		
		this.campoNomeGrupo = new EstilizacaoRedonda.CaixaTextoRedonda("Digite a descrição do Grupo",corPaletaVermelho,corPaletaBege,corPaletaCinza,2,35);
		campoNomeGrupo.setFont(FontsConstants.MONTSERRAT_LIGHT_10);
		campoNomeGrupo.setBounds(65, 179, 1135, 38);
		panel.add(campoNomeGrupo);
		campoNomeGrupo.setColumns(10);
		
		JLabel descricaoLabel = new JLabel("Descrição");
		descricaoLabel.setForeground(Color.BLACK);
		descricaoLabel.setFont(FontsConstants.MONTSERRAT_BOLD_20);
		descricaoLabel.setBounds(65, 246, 187, 32);
		panel.add(descricaoLabel);
		
		final EstilizacaoRedonda.BotaoRedondo botaoAtualizarGrupo = new EstilizacaoRedonda.BotaoRedondo("Atalizar Grupo",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoAtualizarGrupo.setText("Atualizar Grupo");
		botaoAtualizarGrupo.setBounds(537, 440, 240, 45);
		panel.add(botaoAtualizarGrupo);
		botaoAtualizarGrupo.setFont(FontsConstants.MONTSERRAT_BOLD_20);
		botaoAtualizarGrupo.setForeground(corPaletaBege);
		botaoAtualizarGrupo.setBackground(new Color(0, 0, 0));
		botaoAtualizarGrupo.addActionListener(e -> {
    try {
        categoria.setNome(campoNomeGrupo.getText().trim());
        categoria.setDescricao(campoDescricaoGrupo.getText().trim());

        CategoriaController controller = new CategoriaController();
        controller.atualizar(categoria);

        JOptionPane.showMessageDialog(this, "Grupo atualizado com sucesso.");
        dispose();
        new TelaItens().setVisible(true);

    } catch (ControllerException | DatabaseException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
	});
		
		carregarDados();	
		
		final EstilizacaoRedonda.BotaoRedondo botaoVoltar = new EstilizacaoRedonda.BotaoRedondo("Voltar",corPaletaPreto,corPaletaPretoInteração,corPaletaPreto,35);
		botaoVoltar.setBounds(1128, 32, 104, 38);
		contentPane.add(botaoVoltar);
		botaoVoltar.setFont(FontsConstants.MONTSERRAT_BOLD_18);
		botaoVoltar.setForeground(corPaletaBege);
		botaoVoltar.setBackground(new Color(0, 0, 0));
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TelaItens telaItens = new TelaItens();
				telaItens.setVisible(true);
			}
		});
	}
}
