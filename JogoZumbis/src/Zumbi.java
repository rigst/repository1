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
		// TODO Auto-generated method stub
		
	}
	
}
