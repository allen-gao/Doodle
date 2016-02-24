import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class NorthPanel extends JPanel {
	public NorthPanel() {
		this.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border margin = new EmptyBorder(0, 0, 2, 0);
		this.setBorder(BorderFactory.createCompoundBorder(margin, border));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
