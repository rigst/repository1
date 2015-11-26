public class Soldado extends Humano {

	/*@ 
	 	requires \same
	 	ensures \same
	@*/	
	public Soldado(Posicao p){
		super(p);
	}
	
	/*@
		requires \same
		ensures \same
	@*/
	@Override
	public void avancaJogada() {
		atacaZumbisAoRedor();	//ATACA TODOS ZUMBIS AO REDOR
		if(!Tabuleiro.getInstance().existePosVaziaAoRedor(this.getPos())) return; //se não tem para onde ir	
		
		Posicao prox = proxPos();
		if(Tabuleiro.getInstance().ocupado(prox)){
			Personagem aux2 = Tabuleiro.getInstance().getPersonagem(prox);
			if(mesmoTipo(aux2)){ //MUDA DE DIRECAO
				setDirecao(sorteiaDirecao());
				avancaJogada();
			}else{ //ATACA 
				ataca(aux2);
			}
		}
		else{
			Tabuleiro.getInstance().moverPersonagem(this.getPos(),prox);
			this.setPos(prox);
		}
	}
	
	/*@
		ensures (* não haverá mais nenhum zumbi ao redor do personagem *);
	@*/
	public void atacaZumbisAoRedor(){
		int x = getPos().getX();
		int y = getPos().getY();
		
		Personagem aux = null;
		for(int i=-1; i<=1; i++){
			for(int j=-1; j<=1; j++){
				if(x+i >= 0 && x+i < Tabuleiro.getInstance().getSize() && y+j >= 0 && y+j < Tabuleiro.getInstance().getSize())
						aux = Tabuleiro.getInstance().getPersonagem(new Posicao(x+i,y+j));
						if(aux instanceof Zumbi){
							aux.atacado(this);
						}
			}
		}	
	}
	
	@Override
	public /*@ pure @*/ String toString(){ return "Soldado " + super.toString(); }

}