import java.util.Random;


public abstract class Personagem {
	public enum DIRECAO{ N,S,L,O,NE,NO,SO,SE}
	
	private boolean vivo = true;
	private DIRECAO direcao;
	private Posicao pos;

	public abstract boolean mesmoTipo(Personagem p);
	public abstract void ataca(Personagem p);
	public abstract void atacado(Personagem p);

	public Posicao getPos() { return pos; }
	public void setPos(Posicao p) { pos = p;} 
	
	public boolean getVivo() { return vivo;} 
	public void setVivo(boolean b) { vivo = b; } 
	
	public DIRECAO getDirecao() { return direcao; }
	public void setDirecao(DIRECAO dir) { direcao = dir; }
	public static DIRECAO sorteiaDirecao(){
		Random r = new Random();
		int numDirecao = r.nextInt() % 8;
		switch(numDirecao){
			case 0 : return DIRECAO.N;
			case 1 : return DIRECAO.S;
			case 2 : return DIRECAO.L;
			case 3 : return DIRECAO.O;
			case 4 : return DIRECAO.NE;
			case 5 : return DIRECAO.NO;
			case 6 : return DIRECAO.SE;
			case 7 : return DIRECAO.SO;	
			default : return DIRECAO.N;
		}		
	}
	
	public abstract void avancaJogada();
	
	public Posicao proxPos() {
		int x, y;
		x = pos.getX(); y = pos.getY();
		Posicao nova = null;
		
		if(direcao == DIRECAO.N) nova = new Posicao(x,y-1);
		else if(direcao == DIRECAO.S) nova = new Posicao(x,y+1);
		else if(direcao == DIRECAO.L) nova = new Posicao(x+1,y);
		else if(direcao == DIRECAO.O) nova = new Posicao(x-1,y);
		else if(direcao == DIRECAO.NE) nova = new Posicao(x+1,y-1);
		else if(direcao == DIRECAO.NO) nova = new Posicao(x-1,y-1);
		else if(direcao == DIRECAO.SE) nova = new Posicao(x+1,y+1);
		else nova = new Posicao(x-1,y+1);
		
		if(!Tabuleiro.getInstance().existePosicao(nova)){
			direcao = sorteiaDirecao();
			return this.proxPos();
		}
		return nova;
	}
	
	public String toString() { return "(" + pos.getX() + "," + pos.getY() + ")";}
	
}
