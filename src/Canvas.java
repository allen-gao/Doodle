import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Observer {
	
	private Model model;
	private int x;
	private int y;
	private int lastX;
	private int lastY;
	private boolean startDraw = true;
	
	
	public Canvas(Model model) {
		this.model = model;
		model.addObserver(this);
		
		this.setBackground(Color.WHITE);
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (startDraw) {
					lastX = e.getX();
					lastY = e.getY();
					startDraw = false;
				}
				else {
					lastX = x;
					lastY = y;
				}
				x = e.getX();
				y = e.getY();
				repaint();
			}
			
			public void mouseReleased(MouseEvent e) {
				startDraw = true;
			}
		};
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawLine(lastX, lastY, x, y);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
