import java.util.Random;

public class Tabuleiro {
	public Personagem[][] tab;
	public int numJogada = 0;
	public int numHumanos, numZumbis;
	
	public static Tabuleiro tabuleiro = null; 
	
	private Tabuleiro(int numHumanos, int numZumbis){
		Random r = new Random();
		int x = 0, y = 0, d = 0;
		tab = new Personagem[10][10];
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				tab[i][j] = null;
			}
		}
		
		//HUMANOS
		for(int i=0; i<numHumanos; i++){
			do{
				//x = r.nextInt() % 101;
				//y = r.nextInt() % 101;
				x = Math.abs(r.nextInt()) % 10;
				y = Math.abs(r.nextInt()) % 10;
				d = Math.abs(r.nextInt()) % 2;
				if(!ocupado(x,y)) {System.out.println("COLOCOU "); tab[x][y] = new Humano(x,y,d); break;}
			}while(ocupado(x,y));
		}
		
		//ZUMBIS
		for(int i=0; i<numZumbis; i++){
			do{
				//x = r.nextInt() % 101;
				//y = r.nextInt() % 101;
				x = Math.abs(r.nextInt()) % 10;
				y = Math.abs(r.nextInt()) % 10;
				d = Math.abs(r.nextInt()) % 2; //total são 8
				if(!ocupado(x,y)) {tab[x][y] = new Zumbi(x,y,d); break;}
			}while(ocupado(x,y));
		}
		
		this.numHumanos = numHumanos;
		this.numZumbis = numZumbis;
		
	}
	
	public static Tabuleiro getInstance(int numHumanos, int numZumbis){
		if(tabuleiro == null) return new Tabuleiro(numHumanos, numZumbis);
		else return tabuleiro;
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
	
	public void avancaJogada(){
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				Personagem p = tab[i][j];
				
				if(p == null) continue;
				
				Posicao pos = p.proxPos();
				System.out.println("pos :" + pos);
				if(ocupado(pos)){
					Personagem p2 = getPersonagem(pos);
					if(mesmoTipo(p,p2)) {System.out.println("Não move"); }
					else {System.out.println("Outro lado");}
				}
			}
		}
	}
	
	/******CONSERTAR*****/
	public boolean mesmoTipo(Personagem p, Personagem p2){
		if(p instanceof Humano && p2 instanceof Humano) return true;
		else if(p instanceof Humano && p2 instanceof Zumbi) return false;
		else if(p instanceof Zumbi && p2 instanceof Humano) return false;
		return true;
	}
	
	@Override
	public String toString(){
		String msg = "";
		for(int i=0; i<tab.length; i++){
			for(int j=0; j<tab.length; j++){
				if(tab[i][j] == null) msg += "-\t";
				else msg += tab[i][j] + "\t";
			}
			msg += "\n";
		}
		return msg;
	}
}
