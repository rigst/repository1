
public abstract class Personagem {
	public enum DIRECAO{ N,S,L,O,NE,NO,SO,SE}
	
	public DIRECAO direcao;
	public Posicao pos;

	public abstract Posicao proxPos();

}
