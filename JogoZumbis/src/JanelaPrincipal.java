import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ComponentColorModel;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JanelaPrincipal extends JFrame implements Observer{
	private JPanel[][] tabuleiro;
	private static JanelaPrincipal janela;
	
	public static JanelaPrincipal getInstance(){
		if(janela == null){
			JanelaPrincipal j = new JanelaPrincipal();
			janela = j;
		}
		return janela;
	}
	
	private JanelaPrincipal(){
		//Janela
		setSize(1000,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JogoZumbi");
		setResizable(true);
		setLocationRelativeTo(null);
				
		//Painel Principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(100, 100, 204));
				
		//Menu Superior
		criaMenuSuperior(mainPanel);
		
		//Menu Inferior
		criaMenuInferior(mainPanel);
		
		//Cria Centro
		criaCentro(mainPanel);
		
		//Adiciona Painel Principal na Janela
		setContentPane(mainPanel);
		setVisible(true);
	}
	
	public void criaMenuSuperior(JPanel mainPanel){
		//Cria Botões
		JButton salvar = new JButton("Salvar");
		JButton carregar = new JButton("Carregar");
		JButton recuperar = new JButton("Recuperar");

		JButton historia = new JButton("História");
		historia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*JanelaHistoria a = new JanelaHistoria(JanelaPrincipal.getInstance());
				a.setVisible(true);*/
				Tabuleiro.getInstance().avancaJogada(1);
			}
		});
		
		//Cria Painel dos Botões
		JPanel pnMenu = new JPanel();
		pnMenu.setLayout(new FlowLayout());
		pnMenu.add(salvar);
		pnMenu.add(carregar);
		pnMenu.add(recuperar);
		pnMenu.add(historia);
		
		//Coloca Painel dos Botões no Painel Principal
		mainPanel.add(pnMenu, BorderLayout.NORTH);
		
		janela = this;
	}
	
	public void criaMenuInferior(JPanel mainPanel){
		//Painel da Esquerda
		JPanel pnEsq = new JPanel();
		pnEsq.setLayout(new BoxLayout(pnEsq, BoxLayout.X_AXIS));
		JTextField numero = new JTextField();
		numero.setMaximumSize(new Dimension(200,30));
		JButton avancar = new JButton("Avançar");
		pnEsq.add(numero);
		pnEsq.add(avancar);
		
		//Painel da Direita
		JPanel pnDir = new JPanel();
		pnDir.setLayout(new GridLayout(2,1));
		
			//Painel Direita Superior
			JPanel pnDirSup = new JPanel();
			pnDirSup.setLayout(new GridLayout(2,2));
			JLabel numHumText = new JLabel("Número de Humanos : ");
				JTextField numHum = new JTextField();
			JLabel numZumText = new JLabel("Número de Zumbis : ");
				JTextField numZum = new JTextField();
			pnDirSup.add(numHumText);
			pnDirSup.add(numHum);
			pnDirSup.add(numZumText);
			pnDirSup.add(numZum);
			//Painel Direita Inferior
			JPanel pnDirInf = new JPanel();
			pnDirInf.setLayout(new BoxLayout(pnDirInf, BoxLayout.X_AXIS));
			JButton novo = new JButton("Novo");
			pnDirInf.add(novo);
			
		pnDir.add(pnDirSup);
		pnDir.add(pnDirInf);
			
		//Painel Geral
		JPanel pnGeral = new JPanel();
		pnGeral.setLayout(new GridLayout(1,2));
		pnGeral.add(pnEsq);
		pnGeral.add(pnDir);
		
		//Coloca Painel dos Botões no Painel Principal
		mainPanel.add(pnGeral,BorderLayout.SOUTH);
	}
	
	public void criaCentro(JPanel mainPanel){
		JPanel divisorCentro = new JPanel();
		divisorCentro.setLayout(new GridLayout(1,2));
	
		JPanel centroEsq = criaPainelTab();
		JPanel centroDir = criaPainelGrafico();
		JScrollPane jsp = new JScrollPane(centroEsq);
		
		divisorCentro.add(jsp);
		divisorCentro.add(centroDir);
	
		mainPanel.add(divisorCentro, BorderLayout.CENTER);
	}
	
	public JPanel criaPainelTab(){
		JPanel pnGeral = new JPanel(new GridLayout(Tabuleiro.getInstance().getSize(),Tabuleiro.getInstance().getSize()));
		desenhaTabuleiro(pnGeral);
		return pnGeral;
	}
	
	private ImageIcon kingBlack = new ImageIcon(System.getProperty("user.dir")
			+ "/Images/bk.png");
	
	private ImageIcon grama = new ImageIcon(System.getProperty("user.dir")
			+ "/Images/grama.png");
	
	private ImageIcon zumbi = new ImageIcon(System.getProperty("user.dir")
			+ "/Images/zumbi.jpg");
	
	private void desenhaTabuleiro(JPanel pnTabGeral) {
		int tam = Tabuleiro.getInstance().getSize();
		tabuleiro = new JPanel[tam][tam];
		
		for (int y = 0; y < tam; y++){
			for (int x = 0; x < tam; x++) {
				Personagem persona = Tabuleiro.getInstance().getPersonagem(new Posicao(y,x));
				tabuleiro[y][x] = new JPanel();
				if(persona == null) {
					tabuleiro[y][x].add(new JLabel(this.grama));
				}
				else if(persona instanceof Humano){
					tabuleiro[y][x].add(new JLabel(this.kingBlack));
				}
				else if(persona instanceof Zumbi){
					tabuleiro[y][x].add(new JLabel(this.zumbi));
				}
				
				pnTabGeral.add(tabuleiro[y][x]);
			}
		}
	}
	
	public void atualizaTabuleiro(Personagem p){
		Personagem persona = Tabuleiro.getInstance().getPersonagem(p.getPos());
		JLabel img;
		int y = p.getPos().getX();
		int x = p.getPos().getY();
		
		if(persona == null) img = new JLabel(grama);
		else if(persona instanceof Humano) img = new JLabel(kingBlack);
		else if (persona instanceof Zumbi) img = new JLabel(zumbi);
		else img = new JLabel(grama);
				
		tabuleiro[y][x].removeAll();
		tabuleiro[y][x].add(img);
		tabuleiro[y][x].validate();	
	}
		
	public JPanel criaPainelGrafico(){
		JPanel pn = new JPanel();
		JButton bt = new JButton("AAA");
		pn.add(bt);
		return pn;
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if(o instanceof Tabuleiro){
			Personagem p = (Personagem)arg;
			atualizaTabuleiro(p);
		}
	}

}
