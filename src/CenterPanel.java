import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class CenterPanel extends JPanel {
	
	public int x;
	public int y;
	
	public CenterPanel() {
		super();
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		//this.add(new JColorChooser());
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				repaint();
			}
		};
		
		this.addMouseMotionListener(mouseAdapter);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 5, 5);
	}
}
