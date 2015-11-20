import javax.swing.SwingUtilities;


public class App {
	public static void main(String args[]){
		Tabuleiro tab = Tabuleiro.getInstance();
		tab.setTabuleiro(6,6);
	
		System.out.println(tab);
		System.out.println("LISTA : " + tab.getLista());
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JanelaPrincipal.getInstance();
				tab.addObserver(JanelaPrincipal.getInstance());
				//new JanelaHistoria();
				
			}
		});
		
		
	tab.avancaJogada(1);
	System.out.println(tab);
	}
}
