import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private Model model;
	
	public GamePanel(Model model) {
		this.model = model;
		
		this.setLayout(new BorderLayout());
		this.add(new SouthPanel(model), BorderLayout.SOUTH);
		this.add(new WestPanel(model),  BorderLayout.WEST);
		this.add(new NorthPanel(model), BorderLayout.NORTH);
		this.add(new Canvas(model), BorderLayout.CENTER);
	}
}
