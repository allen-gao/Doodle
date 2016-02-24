import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class StrokeButton extends JButton {
	
	BasicStroke stroke;
	
	public StrokeButton(BasicStroke stroke) {
		this.stroke = stroke;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		g2.setColor(Color.BLACK);
		g2.setStroke(this.stroke);
		g2.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
	}
}
