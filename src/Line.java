import java.awt.BasicStroke;
import java.awt.Color;
import java.io.Serializable;

public class Line implements Serializable {
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	private int lastX1;
	private int lastX2;
	private int lastY1;
	private int lastY2;
	
	private Color color;
	private BasicStroke stroke;
	
	
	public Line(int x1, int y1, int x2, int y2, int lastX1, int lastY1, int lastX2, int lastY2, Color color, BasicStroke stroke) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.lastX1 = lastX1;
		this.lastX2 = lastX2;
		this.lastY1 = lastY1;
		this.lastY2 = lastY2;
		this.color = color;
		this.stroke = stroke;
	}
	
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public BasicStroke getStroke() {
		return stroke;
	}

	public void setStroke(BasicStroke stroke) {
		this.stroke = stroke;
	}

	public int getLastX1() {
		return lastX1;
	}

	public void setLastX1(int lastX1) {
		this.lastX1 = lastX1;
	}

	public int getLastX2() {
		return lastX2;
	}

	public void setLastX2(int lastX2) {
		this.lastX2 = lastX2;
	}

	public int getLastY1() {
		return lastY1;
	}

	public void setLastY1(int lastY1) {
		this.lastY1 = lastY1;
	}

	public int getLastY2() {
		return lastY2;
	}

	public void setLastY2(int lastY2) {
		this.lastY2 = lastY2;
	}
}
