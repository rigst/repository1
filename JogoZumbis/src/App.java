import javax.swing.SwingUtilities;


public class App {
	public static void main(String args[]){
		Tabuleiro tab = Tabuleiro.getInstance();
		System.out.println(tab);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JanelaPrincipal.getInstance();
				tab.addObserver(JanelaPrincipal.getInstance());				
			}
		});
	}
}
