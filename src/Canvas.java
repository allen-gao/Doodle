import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Canvas extends JPanel implements Observer {
	
	private Canvas canvas = this;
	
	private Model model;
	private JScrollPane scrollPane;
	private int x;
	private int y;
	private int lastX;
	private int lastY;
	private boolean startDraw = true;
	private Color drawColor;
	private ArrayList<ArrayList<Line>> drawnLines;
	private BasicStroke currentStroke;
	private int largestX = 0;
	private int largestY = 0;
	private int largestOffset = 50;
	
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
				if (model.isFitWindow() && model.getDrawnLines() != null && (model.getLastWidth() != canvas.getWidth() || model.getLastHeight() != canvas.getHeight())) {
					model.setLastWidth(canvas.getWidth());
					model.setLastHeight(canvas.getHeight());
					for (int i = 0; i < model.getDrawnLines().size(); i++) {
						ArrayList<Line> stroke = model.getDrawnLines().get(i);
						for (int j = 0; j < stroke.size(); j++) {
							Line line = stroke.get(j);
							line.setLastX1(line.getX1());
							line.setLastX2(line.getX2());
							line.setLastY1(line.getY1());
							line.setLastY2(line.getY2());
						}
					}
				}
				if (model.getLineIndex() == model.getLineIndexMax()) { // no overwriting
					lastList = drawnLines.get(drawnLines.size() - 1);
					lastList.add(new Line(x, y, lastX, lastY, x, y, lastX, lastY, drawColor, currentStroke));
					model.setDrawnLines(drawnLines);
				}
				else if (model.getLineIndex() == 0) {
					ArrayList<ArrayList<Line>> drawnLines = model.getDrawnLines();
					drawnLines.subList(0, drawnLines.size()).clear();
					drawnLines.add(new ArrayList<Line>());
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

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
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
		if (model.isFitWindow()) {
			ArrayList<ArrayList<Line>> drawnLines = model.getDrawnLines();
			if (drawnLines != null) {
				for (int i = 0; i < drawnLines.size(); i++) {
					ArrayList<Line> stroke = drawnLines.get(i);
					for (int j = 0; j < stroke.size(); j++) {
						Line line = stroke.get(j);
						double xPercent = (double)this.getWidth() / (double)model.getLastWidth();
						double yPercent = (double)this.getHeight() / (double)model.getLastHeight();
						line.setX1((int)((double)line.getLastX1() * xPercent));
						line.setX2((int)((double)line.getLastX2() * xPercent));
						line.setY1((int)((double)line.getLastY1() * yPercent));
						line.setY2((int)((double)line.getLastY2() * yPercent));
					}
				}
				model.setDrawnLines(drawnLines);
				repaint();
			}
		}
	}
	
	public void setLargestXY() {
		this.largestX = 0;
		this.largestY = 0;
		if (model.getDrawnLines() != null) {
			for (int i = 0; i < model.getDrawnLines().size(); i++) {
				ArrayList<Line> stroke = model.getDrawnLines().get(i);
				for (int j = 0; j < stroke.size(); j++) {
					Line line = stroke.get(j);
					if (line.getX1() > this.largestX) {
						this.largestX = line.getX1();
					}
					if (line.getX2() > this.largestX) {
						this.largestX = line.getX2();
					}
					if (line.getY1() > this.largestY) {
						this.largestY = line.getY1();
					}
					if (line.getY2() > this.largestY) {
						this.largestY = line.getY2();
					}
				}
			}
			this.setPreferredSize(new Dimension(this.largestX + this.largestOffset, this.largestY + this.largestOffset));
			this.scrollPane.revalidate();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == "lineIndex") {
			repaint();
		}
		if (!model.isFitWindow()) {
			this.setLargestXY();
		}
		System.out.println(model.isFitWindow());
		if (arg == "fitWindow") {
			if (model.isFitWindow()) {
				this.scrollPane.setPreferredSize(new Dimension(this.largestX + this.largestOffset, this.largestY + this.largestOffset));
				repaint();
				this.scrollPane.setPreferredSize(null);
			}
		}
		this.drawColor = model.getCurrentColor();
		this.currentStroke = model.getCurrentStroke();
	}
}
