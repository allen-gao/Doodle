import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WestPanel extends JPanel implements Observer {
	
	private Model model;
	private ArrayList<Color> colorArray;
	private ArrayList<BasicStroke> strokeArray;
	private int preferredWidth = 60;
	
	public WestPanel(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.setPreferredSize(new Dimension(this.preferredWidth, this.getHeight()));
		
		this.colorArray = model.getColorArray();
		for (int i = 0; i < this.colorArray.size(); i++) {
			JButton colorButton = new JButton();
			colorButton.setBackground(this.colorArray.get(i));
			colorButton.setPreferredSize(new Dimension(20, 20));
			colorButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					model.setCurrentColor(colorButton.getBackground());
				}
			});
			this.add(colorButton);
		}
		
		this.strokeArray = model.getStrokeArray();
		for (int i = 0; i < this.strokeArray.size(); i++) {
			BasicStroke stroke = this.strokeArray.get(i);
			StrokeButton strokeButton = new StrokeButton(stroke);
			strokeButton.setPreferredSize(new Dimension(50, 20));
			strokeButton.setBackground(Color.LIGHT_GRAY);
			strokeButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					model.setCurrentStroke(stroke);
				}
			});
			this.add(strokeButton);
		}

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
