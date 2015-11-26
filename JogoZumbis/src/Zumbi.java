public class Zumbi extends Personagem {

	/*@ assignable pos;
 		assignable direcao;
 		requires existePosicao(pos);
		ensures getPos() == pos;
	@*/	
	public Zumbi(Posicao p){
		setPos(p);
		setDirecao(sorteiaDirecao());
	}

	/*@
			requires p instanceof Zumbi;
			ensures \result;
		also
			requires (!p instanceof Zumbi);
			ensures !\result;
	@*/
	@Override
	public boolean mesmoTipo(Personagem p) {
		return(p instanceof Zumbi);
	}

	/*@
		requires \same;
		ensures \same;
		ensures getPos() == p.getPos(); 
	@*/
	@Override
	public void ataca(Personagem p) {
		p.atacado(this);
		Tabuleiro.getInstance().moverPersonagem(this.getPos(),p.getPos());
		this.setPos(p.getPos());
	}
	
	/*@ 
 		assignable pos;
		assignable direcao;
	@*/
	@Override
	public void avancaJogada() {
		Humano hum = buscaHumanoAoRedor(getPos());
		if(hum != null){
			hum.atacado(this);
			Tabuleiro.getInstance().moverPersonagem(this.getPos(),hum.getPos());
			this.setPos(hum.getPos());
		}
		else if(Tabuleiro.getInstance().existePosVaziaAoRedor(this.getPos())){
			Posicao prox = proxPos();
			if(!Tabuleiro.getInstance().ocupado(prox)){
				Tabuleiro.getInstance().moverPersonagem(this.getPos(),prox);
				this.setPos(prox);
			}else{
				setDirecao(sorteiaDirecao());
				avancaJogada();
			}
		}
	}
	
	/*@
	 		requires (* não possui humanos ao redor *);
	 		ensures (* retorna null *);
	 	also 
	 		requires (* possui humanos ao redor *);
			ensures (* retorna o primeiro humano que encontrar *);
	@*/
	public Humano buscaHumanoAoRedor(Posicao p){
		Tabuleiro tab = Tabuleiro.getInstance();
		int x = p.getX(), y = p.getY();
		Posicao pos;
		
		for(int i=-1; i<=1; i++){
			for(int j=-1; j<=1; j++){
				pos = new Posicao(x+i, y+j); 
				if(tab.existePosicao(pos) && tab.getPersonagem(pos) instanceof Humano )
					return (Humano)tab.getPersonagem(pos);
			}	
		}		
		return null;
	}
	
	@Override
	public /*@ pure @*/ String toString(){ return "Zumbi" + super.toString(); }
}
