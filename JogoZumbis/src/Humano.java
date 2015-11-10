public class Humano extends Personagem {

	public Humano(int x, int y){
		setPos(new Posicao(x,y));
		setDirecao(sorteiaDirecao());
	}
	
	@Override
	public String toString(){ return "Humano " + super.toString(); }

}