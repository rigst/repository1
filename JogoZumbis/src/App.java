import javax.swing.SwingUtilities;


public class App {
	public static void main(String args[]){
		Tabuleiro tab = Tabuleiro.getInstance();
		tab.setTabuleiro(5,5);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JanelaPrincipal.getInstance();
				//new JanelaHistoria();
			}
		});
		
	}
}
