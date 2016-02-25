import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class StrokeButton extends JButton {
	BasicStroke stroke;
	
	public StrokeButton(BasicStroke stroke) {
		this.stroke = stroke;
		Border emptyBorder = BorderFactory.createEmptyBorder();
		this.setBorder(emptyBorder);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		g2.setColor(Color.BLACK);
		g2.setStroke(this.stroke);
		g2.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
	}
}
