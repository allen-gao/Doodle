import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class SouthPanel extends JPanel {
	public SouthPanel() {
		this.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border margin = new EmptyBorder(2, 0, 0, 0);
		this.setBorder(BorderFactory.createCompoundBorder(margin, border));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
