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
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WestPanel extends JPanel implements Observer {
	
	private Model model;
	private ArrayList<Color> colorArray;
	private ArrayList<BasicStroke> strokeArray;
	private int preferredWidth = 60;
	private WestPanel self = this;
	private JButton colorChooser;
	
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
		
		colorChooser = new JButton();
		colorChooser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Color color = JColorChooser.showDialog(self, "Choose a colour", model.getCurrentColor());
				if (color != null) {
					model.setCurrentColor(color);
				}
			}
		});
		colorChooser.setBackground(model.getCurrentColor());
		colorChooser.setPreferredSize(new Dimension(20, 20));
		this.add(colorChooser);
		
		this.strokeArray = model.getStrokeArray();
		for (int i = 0; i < this.strokeArray.size(); i++) {
			BasicStroke stroke = this.strokeArray.get(i);
			StrokeButton strokeButton = new StrokeButton(stroke);
			strokeButton.setPreferredSize(new Dimension(50, 20));
			strokeButton.setBackground(Color.WHITE);
			strokeButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (model.getCurrentStrokeButton() != null) {
						model.getCurrentStrokeButton().setBorder(BorderFactory.createEmptyBorder());
					}
					model.setCurrentStrokeButton(strokeButton);
					model.setCurrentStroke(stroke);
				}
			});
			this.add(strokeButton);
			if (i == 0) {
				model.setCurrentStrokeButton(strokeButton);
			}
		}

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		colorChooser.setBackground(model.getCurrentColor());
		model.getCurrentStrokeButton().setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
