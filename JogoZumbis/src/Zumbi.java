public class Zumbi extends Personagem {

	public Zumbi(int x, int y){
		setPos(new Posicao(x,y));
		setDirecao(sorteiaDirecao());
	}
		
	@Override
	public String toString(){ return "Zumbi" + super.toString(); }
	
}
