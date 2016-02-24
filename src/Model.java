import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Model extends Observable {
	
	private ArrayList<Color> colorArray;
	private Color currentColor;
	private ArrayList<BasicStroke> strokeArray;
	private BasicStroke currentStroke;
	
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
	
}
