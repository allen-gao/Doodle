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
	private int lastWidth = 0;
	private int lastHeight = 0;
	long lastResizeEvent;
	
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
				
				ArrayList<Line> lastList;
				if (model.getLineIndex() == model.getLineIndexMax()) { // no overwriting
					lastList = drawnLines.get(drawnLines.size() - 1);
					lastList.add(new Line(x, y, lastX, lastY, drawColor, currentStroke));
					model.setDrawnLines(drawnLines);
				}
				else { // overwriting
					int strokeIndex = model.getLastStrokeIndex();
					int lineIndex = model.getLastLineIndex();
					ArrayList<ArrayList<Line>> drawnLines = model.getDrawnLines();
					drawnLines.subList(strokeIndex + 1, drawnLines.size()).clear();
					ArrayList<Line> lastStroke = drawnLines.get(strokeIndex);
					lastStroke.subList(lineIndex + 1, lastStroke.size()).clear();
					drawnLines.add(new ArrayList<Line>());
					model.setDrawnLines(drawnLines);
				}
				
				
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
		int strokeIndex = (int) Math.floor(lineIndex / 100) + 1; // completed lines
		int partialPercentage = lineIndex % 100;
		if (lineIndex == lineIndexMax) {
			partialPercentage = 100;
			strokeIndex -= 1;
		}
		int partialIndex;
		for (int i = 0; i < strokeIndex; i++) {	
			ArrayList<Line> lineList = drawnLines.get(i);
			model.setLastStrokeIndex(i);
			if (i != strokeIndex - 1) {
				partialIndex = lineList.size();
			}
			else {
				partialIndex = (int) Math.floor(partialPercentage * lineList.size() / 100);
			}	
			for (int j = 0; j < partialIndex; j++) {
				Line line = lineList.get(j);
				model.setLastLineIndex(j);
				g2.setColor(line.getColor());
				g2.setStroke(line.getStroke());
				g2.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
			}
		}	
	}
	
	public void resized() {
		if (this.lastWidth == 0 || this.lastHeight == 0) {
			this.lastWidth = this.getWidth();
			this.lastHeight = this.getHeight();
		}			
		ArrayList<ArrayList<Line>> drawnLines = model.getDrawnLines();
		if (drawnLines != null) {
			for (int i = 0; i < drawnLines.size(); i++) {
				ArrayList<Line> stroke = drawnLines.get(i);
				for (int j = 0; j < stroke.size(); j++) {
					Line line = stroke.get(j);
					double xPercent = (double)this.getWidth() / (double)lastWidth;
					double yPercent = (double)this.getHeight() / (double)lastHeight;
					line.setX1((int)((double)line.getX1() * xPercent));
					line.setX2((int)((double)line.getX2() * xPercent));
					line.setY1((int)((double)line.getY1() * yPercent));
					line.setY2((int)((double)line.getY2() * yPercent));
				}
			}
			model.setDrawnLines(drawnLines);
			this.lastWidth = this.getWidth();
			this.lastHeight = this.getHeight();
			repaint();
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
