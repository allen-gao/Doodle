import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	public GamePanel() {
		this.setLayout(new BorderLayout());
		this.add(new SouthPanel(), BorderLayout.SOUTH);
		this.add(new CenterPanel(), BorderLayout.CENTER);
		this.add(new WestPanel(),  BorderLayout.WEST);
		this.add(new NorthPanel(), BorderLayout.NORTH);
		
		this.setBackground(Color.RED);
	}
}
