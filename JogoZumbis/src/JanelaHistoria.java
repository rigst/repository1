import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class JanelaHistoria extends JDialog{
	
	public JanelaHistoria(Frame parent){
		super(parent);
		initUI();
	}
	
	private void initUI(){
		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
		
		JTextPane pane = new JTextPane();
		pane.setContentType("text/html");
		String text = "<align=justify><div><p>Um acidente na barragem da cidade de Mariana"
				+ " em MG causa o vazamento de uma enorme quantidade de lama que destrói distritos,"
				+ " mata pessoas e animais. Com a lama havia uma grande quantidade de resíduos tóxicos. "
				+ "A população é então orientada a deixar as casas que foram atingidas no acidente e ir para "
				+ "abrigos.</p><p>Enquanto a população migra para as cidades que não foram atingidas pelo acidente,"
				+ " Xingu, um cão que havia sido arrastado pela lama, é levado por sua dona a um abrigo com "
				+ "inúmeras pessoas. Por estar machucado, os funcionários do local permitem que ele fique dentro "
				+ "do abrigo junto com sua família.</p><p>Tarde da noite, o cão morre. Quando sua respiração para, "
				+ "seus músculos começam a apodrecer e seu sangue se espalha para todo o lado. "
				+ "Xingu é agora um cão zumbi.</p><p>Xingu ataca sua dona e várias outras pessoas, que morrem e após "
				+ "algum tempo retornam como zumbis. Esses começam a sair do abrigo, percorrendo a cidade e mordendo "
				+ "mais humanos. Mais cachorros viram zumbis e saem espalhando o caos pelo mundo.</p><p>Percebendo isso, as "
				+ "autoridades chamam o exército para ajudar a limpar a cidade. Os soldados têm a função de exterminar "
				+ "os zumbis e cães zumbis.</p></p></div>"
				+ "<p><b>Humano</b> : Não possui força ou armas para matar nenhum zumbi.</p><p><b>"
				+ "Soldado</b> : Extermina"
				+ " todos os zumbis ao seu redor.</p><p><b>Zumbi</b> : Mata humanos.</p><p><b>Cão Zumbi</b> : Transforma humanos em zumbis.</p>";
		pane.setText(text);
		pane.setEditable(false);
		textPanel.add(pane);
		
		add(textPanel);
		setResizable(false);
		setMinimumSize(new Dimension(700,550));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Apocãolipse");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getParent());
	}
	
}