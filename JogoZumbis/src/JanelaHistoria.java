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
		String text = "<p><b>Nossa História de Zumbi</b></p>"
				+ "<p>Era uma noite muito calma.. "
				+ "Zumbis apareceram.</p>";
		pane.setText(text);
		pane.setEditable(false);
		textPanel.add(pane);
		
		add(textPanel);
		
		setMinimumSize(new Dimension(250,250));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("História");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getParent());
	}
	
}
