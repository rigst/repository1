import javax.swing.SwingUtilities;


public class App {
	public static void main(String args[]){
		Tabuleiro tab = Tabuleiro.getInstance();
		tab.setTabuleiro(2,3);
	
		System.out.println(tab.toString());
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JanelaPrincipal.getInstance();
				tab.addObserver(JanelaPrincipal.getInstance());
				//new JanelaHistoria();
				
			}
		});
		
		
	}
}
