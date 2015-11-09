public class Humano extends Personagem {

	public Humano(int x, int y, int d){
		pos = new Posicao(x,y);
		if(d == 0) direcao = DIRECAO.N;
		else if(d == 1) direcao = DIRECAO.S;
		/*
		 * TODO
		 */
	}
	
	@Override
	public Posicao proxPos() {
		int x, y;
		x = pos.getX(); y = pos.getY();
		Posicao nova = null;
		if(direcao == DIRECAO.N) nova = new Posicao(x,y-1);
		else if(direcao == DIRECAO.S) nova = new Posicao(x,y+1);
		/*
		 * TODO
		 */		
		return nova;
	}
	
	@Override
	public String toString(){ return "Humano"; }
	
	
}
