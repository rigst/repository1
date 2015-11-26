public class Cachorro extends Zumbi {

	/*@ 
 		requires \same
 		ensures \same
	@*/	
	public Cachorro(Posicao p){
		super(p);
	}
	
	/*@
		requires \same;
		ensures \same;
	@*/
	@Override
	public void ataca(Personagem p) {
		p.atacado(this);
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

	@Override
	public /*@ pure @*/ String toString(){ return "Cachorro " + super.toString(); }

}