import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class SouthPanel extends JPanel implements Observer {
	
	private Model model;
	
	public SouthPanel(Model model) {
		this.model = model;
		model.addObserver(this);
		
		this.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border margin = new EmptyBorder(2, 0, 0, 0);
		this.setBorder(BorderFactory.createCompoundBorder(margin, border));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
