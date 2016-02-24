import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WestPanel extends JPanel {
	public WestPanel() {
		this.add(new JButton("test"));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
