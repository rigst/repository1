
public class GerenciadorPersona {
	private static GerenciadorPersona gp = null;
	public static GerenciadorPersona getInstance(){
		if(gp == null){ gp = new GerenciadorPersona();}
		return gp;
	}
	public Personagem createInstance(int i, Posicao p){
		switch(i){
			case 1 : return new Humano(p);
			case 2 : return new Zumbi(p);
			case 3 : return new Soldado(p);
			case 4 : return new Cachorro(p);
			default: return null;
		}
	}
}
