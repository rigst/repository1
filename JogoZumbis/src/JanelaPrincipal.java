import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ComponentColorModel;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
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
	private JPanel pnTab = null;
	private int numeroDePassos;
	private String receb;
	
	public static JanelaPrincipal getInstance(){
		if(janela == null){
			JanelaPrincipal j = new JanelaPrincipal();
			janela = j;
		}
		return janela;
	}
	
	
	
	private JanelaPrincipal(){
		//Janela
		this.numeroDePassos = 0;
		this.receb = "NADA";
		setSize(1000,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Simulador Zumbi");
		
		setResizable(false);
		setLocationRelativeTo(null);
				
		//Painel Principal
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		mainPanel.setLayout(new BorderLayout());
		//mainPanel.setBackground(new Color(0, 0, 0));
				
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
		salvar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Tabuleiro.getInstance().gravaArquivo("armazenado.txt");
				JOptionPane.showMessageDialog(getParent(), "Salvo com sucesso!","Mensagem",1);
			}
		});
		
		JButton carregar = new JButton("Carregar");
		carregar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Tabuleiro.getInstance().leArquivo("armazenado.txt");
				JOptionPane.showMessageDialog(getParent(), "Carregado com sucesso!","Mensagem",1);
			}
		});
		
		JButton recuperar = new JButton("Recuperar");
		recuperar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Tabuleiro.getInstance().leArquivo("inicio.txt");
				JOptionPane.showMessageDialog(getParent(), "Recuperado com sucesso!","Mensagem",1);
			}
		});
		
		JButton historia = new JButton("História");
		historia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JanelaHistoria a = new JanelaHistoria(JanelaPrincipal.getInstance());
				a.setVisible(true);
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
						
	public void botaoAvancaJogada(JButton b, JTextField c){  // botao avanca, um metodo pra ficar mais claro
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){  // quando eu chamo o botao avancar
                System.out.println("Avanca jogada"); 
                if(c.getText().equals("")&& Tabuleiro.getInstance().getNumeroHumanos() != 0){ // cai nesse if se o JTextField estiver em branco
            				JOptionPane.showMessageDialog(getParent(), "Campos não podem estar em branco!","Mensagem",0);
                }
                else if (Tabuleiro.getInstance().getNumeroHumanos() == 0 && 
                		 Tabuleiro.getInstance().getNumeroZumbis() == 0){ // cai nesse se o tabuleiro estiver vazio e colocarmos jogadas para avancar
    				JOptionPane.showMessageDialog(getParent(), "Ainda não há personagens no tabuleiro!","Mensagem",0);

                }
                else {
                int passos = setJtextField(c);  // o valor do JTextField e capturado e colocado em uma variavel
                if(passos == -1){
                	JOptionPane.showMessageDialog(getParent(), "Valor Inválido!","Mensagem",0);
                	return;
                }
                System.out.println("Numero de passos: "+passos);
				Tabuleiro.getInstance().avancaJogada(passos); }// recebe o numero de passos digitado no textField
                //c.setText(null);

			} 
		});
	}
	
	public int setJtextField(JTextField a ){    // um metodo para pegar o conteudo do JtextField que e uma string e passar para int
		int passos = 0;
		try{
			passos = Integer.parseInt(a.getText());
			if(passos <= 0) throw new NumberFormatException();
		}
		catch(NumberFormatException e){
			passos = -1;
		}
		return passos;
	}
	
	
	public void botaoIniciar(JButton b, JTextField c, JTextField d){  // botao avanca, um metodo pra ficar mais claro
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){  // quando eu chamo o botao avancar
                System.out.println("Avanca jogada"); 
                if(c.getText().equals("") || d.getText().equals("")){ // cai nesse if se o JTextField estiver em branco
            				JOptionPane.showMessageDialog(getParent(), "Nenhum campo pode ficar em branco!","Mensagem",0);
                }
                else {
                	int numHumanos = setJtextField(c);  // o valor do JTextField e capturado e colocado em uma variavel
                	int numZumbi = setJtextField(d);  // o valor do JTextField e capturado e colocado em uma variavel
                	if(numHumanos == -1 || numZumbi == -1){
                		JOptionPane.showMessageDialog(getParent(), "Valor Inválido!","Mensagem",0);
                		return;
                	}        
                	
                	System.out.println("Numero de Zumbi: "+numZumbi);
                	System.out.println("Numero de Humanos: "+numHumanos);
                	Tabuleiro.getInstance().setTabuleiro(numHumanos, numZumbi);
				 
                	c.setText(null);
                	d.setText(null);
                }
			} 
		});
	}
	
	
	public void criaMenuInferior(JPanel mainPanel){
		//Painel da Esquerda
		JPanel pnEsq = new JPanel();
		pnEsq.setLayout(new BoxLayout(pnEsq, BoxLayout.X_AXIS));
		JTextField numero = new JTextField();	
		numero.setText("1");
		numero.setMaximumSize(new Dimension(200,30));
		JButton avancar = new JButton("Avançar");
		pnEsq.add(numero);
		pnEsq.add(avancar);
		botaoAvancaJogada(avancar, numero); //chamando o metodo que aciona o botao.....
		//Painel da Direita
		JPanel pnDir = new JPanel();
		pnDir.setLayout(new GridLayout(2,1));
		
			//Painel Direita Superior
			JPanel pnDirSup = new JPanel();
			pnDirSup.setLayout(new GridLayout(2,2));
			JLabel numHumText = new JLabel("Número de Humanos : ");
				JTextField numHum = new JTextField();
				numHum.setText("1");
			JLabel numZumText = new JLabel("Número de Zumbis : ");
				JTextField numZum = new JTextField();
				numZum.setText("1");
			pnDirSup.add(numHumText);
			pnDirSup.add(numHum);
			pnDirSup.add(numZumText);
			pnDirSup.add(numZum);
			//Painel Direita Inferior
			JPanel pnDirInf = new JPanel();
			pnDirInf.setLayout(new BoxLayout(pnDirInf, BoxLayout.X_AXIS));
			JButton iniciar = new JButton("Iniciar");
			pnDirInf.add(iniciar);
			botaoIniciar(iniciar, numHum, numZum);
			
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
	
	private ImageIcon humano = new ImageIcon(System.getProperty("user.dir")
			+ "/Images/humano.jpg");
	
	private ImageIcon grama = new ImageIcon(System.getProperty("user.dir")
			+ "/Images/grama.jpg");
	
	private ImageIcon zumbi = new ImageIcon(System.getProperty("user.dir")
			+ "/Images/zumbi.jpg");
	
	private void desenhaTabuleiro(JPanel pnTabGeral) {
		int tam = Tabuleiro.getInstance().getSize();
		tabuleiro = null;
		tabuleiro = new JPanel[tam][tam];

		for (int y = 0; y < tam; y++){
			for (int x = 0; x < tam; x++) {
				Personagem persona = Tabuleiro.getInstance().getPersonagem(new Posicao(y,x));
				tabuleiro[y][x] = new JPanel();
				if(persona == null) {
					tabuleiro[y][x].add(new JLabel(this.grama));
				}
				else if(persona instanceof Humano){
					tabuleiro[y][x].add(new JLabel(this.humano));
				}
				else if(persona instanceof Zumbi){
					tabuleiro[y][x].add(new JLabel(this.zumbi));
				}

				pnTabGeral.add(tabuleiro[y][x]);
			}
		}
		pnTab = pnTabGeral;
	}
	
	public void atualizaTabuleiro(List<Personagem> lp){
		for(Personagem p : lp)
			atualizaPos(p.getPos(),p);
		
	}
	
	public void atualizaPos(Posicao pos, Personagem p){
		JLabel img = null;
		int y = pos.getX(), x = pos.getY();
		
		tabuleiro[y][x].removeAll();
		
		if(p == null) tabuleiro[y][x].add(new JLabel(grama)); 
		else if(p instanceof Humano) 
			tabuleiro[y][x].add(new JLabel(humano));
		else if (p instanceof Zumbi) tabuleiro[y][x].add(new JLabel(zumbi));
		
		tabuleiro[y][x].validate();	
	}
		
	public JPanel criaPainelGrafico(){
		JPanel pn = new JPanel();
		JButton bt = new JButton("AAA");
		pn.add(bt);
		return pn;
	}

	
	public void limpaTabuleiro(){
		int tam = tabuleiro.length;
		
		JLabel img = null;
		for(int i=0; i<tam; i++){
			for(int j=0; j<tam; j++){
				tabuleiro[i][j].removeAll();
				tabuleiro[i][j].add(new JLabel(grama));
				tabuleiro[i][j].validate();
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
        System.out.println("*********");
		System.out.println(Tabuleiro.getInstance());
		if(o instanceof Tabuleiro){
			limpaTabuleiro();
			List<Personagem> lp = Tabuleiro.getInstance().getLista();
			atualizaTabuleiro(lp);
			pnTab.updateUI();
		}
	}
}
