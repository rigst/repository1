
public class App {
	public static void main(String args[]){
		Tabuleiro tab = Tabuleiro.getInstance(4,3);
		System.out.println(tab);
		tab.avancaJogada();
		System.out.println(tab);
		
	}
}
