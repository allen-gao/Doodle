import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.JButton;

public class Model extends Observable {
	
	private GamePanel gamePanel;
	private ArrayList<Color> colorArray;
	private Color currentColor;
	private ArrayList<BasicStroke> strokeArray;
	private BasicStroke currentStroke;
	private JButton currentStrokeButton;
	private ArrayList<ArrayList<Line>> drawnLines;
	private int lineIndex;
	private int lineIndexMax;
	private int lastStrokeIndex;
	private int lastLineIndex;
	
	public Model() {
		colorArray = new ArrayList<Color>(Arrays.asList(
				Color.BLACK, 
				Color.BLUE,
				Color.CYAN,
				Color.DARK_GRAY,
				Color.GRAY,
				Color.GREEN,
				Color.YELLOW,
				Color.LIGHT_GRAY,
				Color.MAGENTA,
				Color.ORANGE,
				Color.PINK,
				Color.RED,
				Color.WHITE)
		);
		currentColor = colorArray.get(0);
		
		strokeArray = new ArrayList<BasicStroke>();
		strokeArray.add(new BasicStroke(1));
		strokeArray.add(new BasicStroke(3));
		strokeArray.add(new BasicStroke(5));
		strokeArray.add(new BasicStroke(7));
		strokeArray.add(new BasicStroke(9));
		currentStroke = strokeArray.get(0);
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public ArrayList<Color> getColorArray() {
		return this.colorArray;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<BasicStroke> getStrokeArray() {
		return strokeArray;
	}

	public BasicStroke getCurrentStroke() {
		return currentStroke;
	}

	public void setCurrentStroke(BasicStroke currentStroke) {
		this.currentStroke = currentStroke;
		setChanged();
		notifyObservers();
	}

	public JButton getCurrentStrokeButton() {
		return currentStrokeButton;
	}

	public void setCurrentStrokeButton(JButton currentStrokeButton) {
		this.currentStrokeButton = currentStrokeButton;
		setChanged();
		notifyObservers();
	}

	public ArrayList<ArrayList<Line>> getDrawnLines() {
		return drawnLines;
	}

	public void setDrawnLines(ArrayList<ArrayList<Line>> drawnLines) {
		this.drawnLines = drawnLines;
		setChanged();
		notifyObservers(this.drawnLines);
		this.lineIndex = this.drawnLines.size() * 100;
	}

	public int getLineIndex() {
		return lineIndex;
	}

	public void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;
		setChanged();
		notifyObservers("lineIndex");
	}

	public int getLineIndexMax() {
		return lineIndexMax;
	}

	public void setLineIndexMax(int lineIndexMax) {
		// warning: infinite recursion if notifying SouthPanel
		this.lineIndexMax = lineIndexMax;
	}

	public int getLastStrokeIndex() {
		return lastStrokeIndex;
	}

	public void setLastStrokeIndex(int lastStrokeIndex) {
		this.lastStrokeIndex = lastStrokeIndex;
	}

	public int getLastLineIndex() {
		return lastLineIndex;
	}

	public void setLastLineIndex(int lastLineIndex) {
		this.lastLineIndex = lastLineIndex;
	}
	
	public void resized() {
		gamePanel.resized();
	}
	
}
