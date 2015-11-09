public class Zumbi extends Personagem {

	public Zumbi(int x, int y, int d){
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
		if(direcao == DIRECAO.N) nova = new Posicao(x,y-2);
		else if(direcao == DIRECAO.S) nova = new Posicao(x,y+2);
		/*
		 * TODO
		 */		
		return nova;
	}
	
	@Override
	public String toString(){ return "Zumbi"; }
	
}
