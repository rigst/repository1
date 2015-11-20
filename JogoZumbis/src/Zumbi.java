public class Zumbi extends Personagem {

	public Zumbi(int x, int y){
		setPos(new Posicao(x,y));
		setDirecao(sorteiaDirecao());
	}
		
	@Override
	public String toString(){ return "Zumbi" + super.toString(); }

	@Override
	public boolean mesmoTipo(Personagem p) {
		return(p instanceof Zumbi);
	}

	@Override
	public void ataca(Personagem p) {
		p.atacado(this);
		Tabuleiro.getInstance().moverPersonagem(this.getPos(),p.getPos());
		
	}

	@Override
	public void atacado(Personagem p) {
		setVivo(false);
		Tabuleiro.getInstance().deletaPersonagem(this);		
	}

	@Override
	public void avancaJogada() {
		Posicao prox = proxPos();
		if(Tabuleiro.getInstance().ocupado(prox)){
			Personagem aux = Tabuleiro.getInstance().getPersonagem(prox);
			if(mesmoTipo(aux)){ //MUDA DE DIRECAO
				setDirecao(sorteiaDirecao());
				avancaJogada();
			}else{ //ATACA 
				ataca(aux);
			}
		}
		else{
			Tabuleiro.getInstance().moverPersonagem(this.getPos(),prox);
			this.setPos(prox);
		}
	}
}
