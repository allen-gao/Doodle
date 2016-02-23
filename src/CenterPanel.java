import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CenterPanel extends JPanel {
	public CenterPanel() {
		super();
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g) {
		//g.setColor(Color.WHITE);
		//g.fillRect(0, 0, 600, 600);
	}
}
