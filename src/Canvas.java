import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
	private Color drawColor;
	private ArrayList<Line> drawnLines;
	
	
	public Canvas(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		this.drawnLines = new ArrayList<Line>();
		
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
				drawnLines.add(new Line(x, y, lastX, lastY, drawColor));
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
		for (int i = 0; i < drawnLines.size(); i++) {
			System.out.println(i);
			Line line = drawnLines.get(i);
			g.setColor(line.getColor());
			g.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		this.drawColor = model.getCurrentColor();
	}
}
