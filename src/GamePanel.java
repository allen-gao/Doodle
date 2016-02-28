import java.awt.BorderLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private Model model;
	private SouthPanel southPanel;
	private WestPanel westPanel;
	private NorthPanel northPanel;
	private Canvas canvas;
	
	public GamePanel(Model model) {
		this.model = model;
		
		southPanel = new SouthPanel(model);
		westPanel = new WestPanel(model);
		northPanel = new NorthPanel(model);
		canvas = new Canvas(model);
		
		this.setLayout(new BorderLayout());
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(westPanel, BorderLayout.WEST);
		this.add(northPanel, BorderLayout.NORTH);
		this.add(canvas, BorderLayout.CENTER);
	}
	
	public void resized() {
		canvas.resized();
	}
}
