import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import javax.swing.Scrollable;
import java.util.ArrayList;

public class Tabuleiro{
	public Personagem[][] tab;
	public ArrayList<Personagem> lista;
	public int tam;
	public int numJogada;
	public int numHumanos, numZumbis;
	
	public static Tabuleiro tabuleiro = null; 
	
	public static Tabuleiro getInstance(){
		if(tabuleiro == null) {
			tabuleiro = new Tabuleiro();
		}
		return tabuleiro;
	}
	
	private Tabuleiro(){
		tam = 100;
		tab = new Personagem[tam][tam];
		lista = new ArrayList<Personagem>();
		numHumanos = 0; numZumbis = 0;
		gravaArquivo("inicio.txt");
	}
	
	public int getSize(){ return tam; }
	
	public void setTabuleiro(Personagem[][] mat){
		tab = mat;
		gravaArquivo("inicio.txt");
	}
	
	public void setTabuleiro(int numHumanos, int numZumbis){
		Random r = new Random();
		int x = 0, y = 0;
		
		if(numHumanos + numZumbis > getSize()*getSize()) return;
		
		//Coloca null em todas as posições
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				tab[i][j] = null;
			}
		}
		
		//HUMANOS
		for(int i=0; i<numHumanos; i++){
			do{
				x = Math.abs(r.nextInt()) % tam;
				y = Math.abs(r.nextInt()) % tam;
				if(!ocupado(x,y)) { Humano h = new Humano(x,y); tab[x][y] = h; lista.add(h); break;}
			}while(ocupado(x,y));
		}
		
		//ZUMBIS
		for(int i=0; i<numZumbis; i++){
			do{	x = Math.abs(r.nextInt()) % tam;
			y = Math.abs(r.nextInt()) % tam;
			if(!ocupado(x,y)) {Zumbi z = new Zumbi(x,y); tab[x][y] = z; lista.add(z); break;}
			}while(ocupado(x,y));
		}
		
		this.numJogada = 0;
		this.numHumanos = numHumanos;
		this.numZumbis = numZumbis;

		gravaArquivo("inicio.txt");
	}

	public void setNumHumanos(int n) { numHumanos = n; }
	
	public void setNumZumbis(int n) { numZumbis = n;}
	
	public boolean existePosicao(Posicao p){
		int x = p.getX(), y = p.getY();
		if(x >= 0 && x < tab.length && y >= 0 && y < tab.length) return true;
		return false;
		
	}
	
	public boolean ocupado(int x, int y){
		if(tab[x][y] != null) return true;
		return false;
	}
	
	public boolean ocupado(Posicao p){
		int x = p.getX(), y = p.getY();
		if(tab[x][y] != null) return true;
		return false;
	}
	
	public Personagem getPersonagem(Posicao p){
		return tab[p.getX()][p.getY()];
	}

	public void moverPersonagem(Posicao posInicial, Posicao posFinal){
		tab[posFinal.getX()][posFinal.getY()] = tab[posInicial.getX()][posInicial.getY()];
		tab[posInicial.getX()][posInicial.getY()] = null;			
	}
	
	public void deletaPersonagem(Personagem p){
		Posicao pos = p.getPos();
		lista.remove(tab[pos.getX()][pos.getY()]);
		tab[pos.getX()][pos.getY()] = null;
	}
	
	public void avancaJogada(){
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				Personagem aux = tab[i][j];
				aux.avancaJogada();
			}
		}
	}
		
	public Personagem[][] leArquivo(String s){
		Personagem[][] aux = null;
		int nH = 0, nZ = 0;
		Path path = Paths.get(s);
		
		try (BufferedReader rd = Files.newBufferedReader(path,Charset.defaultCharset())) {
			String line = "";
			while ((line = rd.readLine()) != null) {
				if(aux == null) { aux = new Personagem[Integer.parseInt(line)][Integer.parseInt(line)]; continue; }
				
				char tipo = line.charAt(0);
				int posX = Integer.parseInt("" + line.charAt(2));
				int posY = Integer.parseInt("" + line.charAt(4));
				
				Personagem persona;
				if(tipo == 'H'){ persona = new Humano(posX, posY); nH++;}
				else { persona = new Zumbi(posX, posY); nZ++; }
				
				aux[posX][posY] = persona;
			}
		} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
		}
		
		setNumHumanos(nH);
		setNumZumbis(nZ);
		
		return aux;
	}
	
	public void gravaArquivo(String nomeArq){
		Path path1 = Paths.get(nomeArq);
		Personagem p;
		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1, Charset.defaultCharset()))) {
			writer.format("%s%n",tab.length); // grava o tamanho do tabuleiro
			for(int i = 0; i <tab.length;i++){ // percorre o tab
				for(int j=0;j<tab.length;j++){
					if(tab[i][j] == null) continue;  // se encontrar posicoes vazias continua......

					p = tab[i][j]; // guarda o personagem da posicao
					if(p instanceof Humano){ // verifica qual personagem é....
						char hum = 'H'; // variavel criada pra humano
						writer.format("%s,%s,%s%n",hum,p.getPos().getX(), p.getPos().getY()); } // escreve no arquivo H e linha + coluna
					else{
						char zum = 'Z';
						writer.format("%s,%s,%s%n",zum,p.getPos().getX(), p.getPos().getY());  // // escreve no arquivo Z e linha + coluna
					}
				}
			}
		}
		catch (IOException x) { System.err.format("Erro de E/S: %s%n", x);}
	}
	
	@Override
	public String toString(){
		String msg = "Humanos : " + numHumanos + "\nZumbis : " + numZumbis + "\n";
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				if(tab[i][j] == null) msg += "--------\t";
				else msg += tab[i][j] + "\t";
			}
			msg += "\n";
		}
		return msg;
	}

}
