import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Model extends Observable {
	
	private ArrayList<Color> colorArray;
	private Color currentColor;
	
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
	
}
