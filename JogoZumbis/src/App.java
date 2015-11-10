
public class App {
	public static void main(String args[]){
		/*Tabuleiro tab = Tabuleiro.getInstance();
		tab.setTabuleiro(4,4);
		System.out.println(tab);
		tab.avancaJogada();
		System.out.println(tab);		*/
		
		Tabuleiro tab = Tabuleiro.getInstance();
		tab.setTabuleiro(tab.leArquivo("modelo.txt"));
		System.out.println(tab);
	}
}
