public class Humano extends Personagem {

	public Humano(int x, int y){
		setPos(new Posicao(x,y));
		setDirecao(sorteiaDirecao());
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
	
	@Override
	public boolean mesmoTipo(Personagem p){
		return(p instanceof Humano);
	}
	
	@Override
	public void ataca(Personagem p){
		p.atacado(this);
		Tabuleiro.getInstance().moverPersonagem(this.getPos(),p.getPos());
	}
	
	@Override
	public void atacado(Personagem p){
		setVivo(false);
		Tabuleiro.getInstance().deletaPersonagem(this);
	}
	
	@Override
	public String toString(){ return "Humano " + super.toString(); }


	

}