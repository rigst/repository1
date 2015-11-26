import java.util.Random;


public abstract class Personagem {
	public enum DIRECAO{ N,S,L,O,NE,NO,SO,SE}
	
	private /*@ spec_public @*/ boolean vivo = true;
	private /*@ spec_public @*/ DIRECAO direcao;
	
	/*@ invariant (0 <= pos.getX() && pos.getX() < Tabuleiro.getInstance().getSize()) 
					&& (0 <= pos.getY() && pos.getY() < Tabuleiro.getInstance().getSize());
	@*/
	private /*@ spec_public @*/ Posicao pos;
		
	public /*@ pure @*/ abstract boolean mesmoTipo(Personagem p);
	
	/*@
	 		requires p != null;
	 		assignable pos;
	 	also
	 		requires this instanceof Humano && p instanceof Zumbi;
	 	also
	 		requires this instanceof Humano && p instanceof Zumbi;
	 @*/
	public abstract void ataca(Personagem p); //RECEBE PERSONAGEM A ATACAR
	
	/*@
	  		requires p != null;
	  		assignable vivo;
	  	also
	  		requires (p instanceof Humano);
	  		ensures Tabuleiro.getInstance().getNumeroHumanos() == \old(Tabuleiro.getInstance().getNumeroHumanos())-1;
	  	also		
	 		requires (p instanceof Zumbi);
	  		ensures Tabuleiro.getInstance().getNumeroZumbis() == \old(Tabuleiro.getInstance().getNumeroZumbis())-1;
	 @*/
	public void atacado(Personagem p){	//RECEBE PERSONAGEM QUE O ATACOU
		setVivo(false);
		Tabuleiro.getInstance().deletaPersonagem(this);
	}

	//@ \result == pos;
	public /*@ pure @*/ Posicao getPos() { return pos; }
	/*@ assignable pos;
		ensures p == getPos();
	@*/
	public void setPos(Posicao p) { pos = p;} 
	
	//@ \result == vivo;
	public /*@ pure @*/ boolean getVivo() { return vivo;} 
	/*@ assignable vivo;
		ensures b == getVivo();
	@*/
	public void setVivo(boolean b) { vivo = b; } 
	
	//@ \result == direcao;
	public /*@ pure @*/ DIRECAO getDirecao() { return direcao; }
	/*@ assignable direcao;
		ensures dir == getDirecao();
	@*/
	public void setDirecao(DIRECAO dir) { direcao = dir; }
	public static /*@ pure @*/ DIRECAO sorteiaDirecao(){
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
	
	/*@ 
	 	assignable pos;
		assignable direcao;
	@*/
	public void avancaJogada() {
		if(!Tabuleiro.getInstance().existePosVaziaAoRedor(this.getPos())) return; //se não tem para onde ir
		
		Posicao prox = proxPos();
		if(Tabuleiro.getInstance().ocupado(prox)){
			Personagem aux = Tabuleiro.getInstance().getPersonagem(prox);
			if(!mesmoTipo(aux)){ //ATACA 
				ataca(aux);
			}else{
				setDirecao(sorteiaDirecao());
				avancaJogada();
			}
		}
		else{
			Tabuleiro.getInstance().moverPersonagem(this.getPos(),prox);
			this.setPos(prox);
		}
	}
	
	//@ assignable direcao;
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
	
	public /*@ pure @*/ String toString() { return "(" + pos.getX() + "," + pos.getY() + ")";}
	
}
