import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Random;

public class Tabuleiro {
	public Personagem[][] tab;
	public int numJogada;
	public int numHumanos, numZumbis;
	
	public static Tabuleiro tabuleiro = null; 
	
	private Tabuleiro(){
		tab = new Personagem[5][5];
		numHumanos = 0; numZumbis = 0;
	}
	
	public void setNumHumanos(int n) { numHumanos = n; }
	public void setNumZumbis(int n) { numZumbis = n; }
	
	public void setTabuleiro(Personagem[][] mat){
		tab = mat;
	}
	
	public void setTabuleiro(int numHumanos, int numZumbis){
		Random r = new Random();
		int x = 0, y = 0;
		
		//Coloca null em todas as posições
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				tab[i][j] = null;
			}
		}
		
		//HUMANOS
		for(int i=0; i<numHumanos; i++){
			do{
				x = Math.abs(r.nextInt()) % 5;
				y = Math.abs(r.nextInt()) % 5;
				if(!ocupado(x,y)) { tab[x][y] = new Humano(x,y); break;}
			}while(ocupado(x,y));
		}
		
		//ZUMBIS
		for(int i=0; i<numZumbis; i++){
			do{	x = Math.abs(r.nextInt()) % 5;
			y = Math.abs(r.nextInt()) % 5;
			if(!ocupado(x,y)) {tab[x][y] = new Zumbi(x,y); break;}
			}while(ocupado(x,y));
		}
		
		this.numJogada = 0;
		this.numHumanos = numHumanos;
		this.numZumbis = numZumbis;
		
	}
	
	public static Tabuleiro getInstance(){
		if(tabuleiro == null) return new Tabuleiro();
		else return tabuleiro;
	}
	
	public boolean existePosicao(Posicao p){
		int x = p.getX(), y = p.getY();
		if(x >= 0 && x < tab.length && y >= 0 && y < tab.length) return true;
		return false;
		
	}
	
	public boolean ocupado(Personagem[][] mat, int x, int y){
		if(mat[x][y] == null) return false;
		else {
			return true;
		}
	}
	
	public boolean ocupado(Posicao p, Personagem[][] mat){
		int x = p.getX(), y = p.getY();
		return ocupado(mat,x,y);
	}
	
	public boolean ocupado(int x, int y){
		if(tab[x][y] == null) return false;
		else {
			return true;
		}
	}
	
	public boolean ocupado(Posicao p){
		int x = p.getX(), y = p.getY();
		return ocupado(x,y);
	}
	
	public Personagem getPersonagem(Posicao p){
		return tab[p.getX()][p.getY()];
	}

	public void setaNaoMovidos(){
		//DIZ QUE TODOS NAO FORAM MOVIDOS
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				Personagem aux = tab[i][j];
				if(aux != null) aux.setMovido(false);
			}
		}
	}
	
	public void moverPersonagem(Personagem p, Posicao pos, Personagem[][] aux, int i, int j){
		if(ocupado(pos,aux)){
			Personagem p2 = aux[pos.getX()][pos.getY()];
			
			if(mesmoTipo(p,p2)) {aux[i][j] = p; p.setMovido(true); System.out.println("MESMO TIPO Não move"); }
			else {
				System.out.println("ATACA");
				ataca(p, p2, aux);
			}
		}
		else{
			aux[pos.getX()][pos.getY()] = p;
			p.setMovido(true); 
			p.setPos(pos);
			aux[i][j] = null;
		}
	}
	
	public void avancaJogada(){
		setaNaoMovidos();
			
		Personagem[][] aux = new Personagem[tab.length][tab.length];
		
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				Personagem p = tab[i][j];

				if(p != null && !p.getMovido()){
					Posicao pos = p.proxPos();
	System.out.println("Personagem : " + p + "\tir para :" + pos);
					moverPersonagem(p,pos,aux,i,j);
				}
			}
		}
		tab = aux;
	}
	
	public void ataca(Personagem p1, Personagem p2, Personagem[][] aux){
		System.out.println("Atacante : " + p1 + " Atacado : " + p2);
		
		aux[p2.getPos().getX()][p2.getPos().getY()] = p1;
		aux[p1.getPos().getX()][p1.getPos().getY()] = null;
		if(p2 instanceof Zumbi) numZumbis--;
		else if(p2 instanceof Humano) numHumanos--;		
	}
	
	/******CONSERTAR*****/
	public boolean mesmoTipo(Personagem p, Personagem p2){
		if(p instanceof Humano && p2 instanceof Humano) return true;
		else if(p instanceof Humano && p2 instanceof Zumbi) return false;
		else if(p instanceof Zumbi && p2 instanceof Humano) return false;
		return true;
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
