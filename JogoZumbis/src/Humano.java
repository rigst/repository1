public class Humano extends Personagem {

	/*@ assignable pos;
	 	assignable direcao;
	 	requires existePosicao(pos);
		ensures getPos() == pos;
	@*/	
	public Humano(Posicao pos){
		setPos(pos);
		setDirecao(sorteiaDirecao());
	}
	
	/*@ 	
	 		requires p != null;
	 		assignable vivo;
	 		Tabuleiro.getInstance().getNumeroHumanos() == \old(Tabuleiro.getInstance().getNumeroHumanos())-1
	 	also
	 		requires (p instanceof Cachorro)
	 		ensures Tabuleiro.getInstance().getPersonagem(getPos()) instanceof Zumbi;
	 		ensures Tabuleiro.getInstance().getNumeroZumbis() == \old(Tabuleiro.getInstance().getNumeroZumbis())+1;
	@*/
	@Override
	public void atacado(Personagem p){
		setVivo(false);
		Posicao pos = this.getPos();
		Tabuleiro.getInstance().deletaPersonagem(this);
		if(p instanceof Cachorro){
			Tabuleiro.getInstance().criaPersona(2, pos);
		}
	}
	
	/*@ 
 		requires \same;
 		ensures \same;
 	@*/
	public void avancaJogada() { //NÃO ATACA
		if(!Tabuleiro.getInstance().existePosVaziaAoRedor(this.getPos())) return; //se não tem para onde ir

		Posicao prox = proxPos();
		if(!Tabuleiro.getInstance().ocupado(prox)){
			Tabuleiro.getInstance().moverPersonagem(this.getPos(),prox);
			this.setPos(prox);
		}else{
			setDirecao(sorteiaDirecao());
			avancaJogada();
		}
	}
	
	
	/*@
			requires p instanceof Humano;
			ensures \result;
		also
			requires (!p instanceof Humano);
			ensures !\result;
	@*/
	@Override
	public /*@ pure @*/ boolean mesmoTipo(Personagem p){
		return(p instanceof Humano);
	}
	
	/*@
			requires \same;
			ensures \same;
			ensures getPos() == p.getPos(); 
	@*/
	@Override
	public void ataca(Personagem p){
		p.atacado(this);
		Tabuleiro.getInstance().moverPersonagem(this.getPos(),p.getPos());
		this.setPos(p.getPos());
	}
	
	
	@Override
	public /*@ pure @*/ String toString(){ return "Humano " + super.toString(); }

}