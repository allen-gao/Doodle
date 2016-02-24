import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WestPanel extends JPanel implements Observer {
	
	private Model model;
	
	public WestPanel(Model model) {
		this.model = model;
		model.addObserver(this);
		
		this.add(new JButton("test"));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
