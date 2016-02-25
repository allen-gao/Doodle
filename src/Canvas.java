import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private ArrayList<ArrayList<Line>> drawnLines;
	private BasicStroke currentStroke;
	
	public Canvas(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		this.drawColor = model.getCurrentColor();
		this.currentStroke = model.getCurrentStroke();
		this.drawnLines = new ArrayList<ArrayList<Line>>();
		
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
				ArrayList<Line> lastList = drawnLines.get(drawnLines.size() - 1);
				lastList.add(new Line(x, y, lastX, lastY, drawColor, currentStroke));
				model.setDrawnLines(drawnLines);
				repaint();
			}
			
			public void mousePressed(MouseEvent e) {
				drawnLines.add(new ArrayList<Line>());
			}
			
			public void mouseReleased(MouseEvent e) {
				startDraw = true;
				model.setDrawnLines(drawnLines);
			}
		};
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		int lineIndex = model.getLineIndex();
		int lineIndexMax = model.getLineIndexMax();
		int strokeIndex = (int) Math.floor(lineIndex / 100); // completed lines
		int partialPercentage = lineIndex % 100;
		if (lineIndex == lineIndexMax) {
			partialPercentage = 100;
		}
		System.out.println(strokeIndex);
		System.out.println(partialPercentage);
		int partialIndex;
		for (int i = 0; i < strokeIndex; i++) {
			ArrayList<Line> lineList = drawnLines.get(i);
			if (i == strokeIndex - 1) {
				partialIndex = (int) Math.floor(partialPercentage * lineList.size() / 100);
			}
			else {
				partialIndex = lineList.size();
			}
			for (int j = 0; j < partialIndex; j++) {
				Line line = lineList.get(j);
				g2.setColor(line.getColor());
				g2.setStroke(line.getStroke());
				g2.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "lineIndex") {
			repaint();
		}
		this.drawColor = model.getCurrentColor();
		this.currentStroke = model.getCurrentStroke();
	}
}
