import java.awt.BasicStroke;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;

public class BinaryModel implements Serializable {
	public ArrayList<ArrayList<Line>> drawnLines;
	public int lineIndex;
	public int lineIndexMax;
	public int lastStrokeIndex;
	public int lastLineIndex;
	public boolean fitWindow = false;
	public int lastWidth;
	public int lastHeight;
}
