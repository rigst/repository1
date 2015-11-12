import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class JanelaHistoria extends JFrame{
	public JanelaHistoria(){
		setSize(500,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("História");
		setResizable(false);
		setLocationRelativeTo(null);
			
		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
		add(textPanel);
		
		JTextPane pane = new JTextPane();
		pane.setContentType("text/html");
		String text = "<p><b>Closing windows using the mouse wheel</b></p>"
				+ "<p>Clicking with the mouse wheel on an editor tab closes the window. "
				+ "This method works also with dockable windows or Log window tabs.</p>";
		pane.setText(text);
		pane.setEditable(false);
		textPanel.add(pane);
		
		
		setVisible(true);
	}
}
