import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	public GamePanel() {
		this.setLayout(new BorderLayout());
		this.add(new SouthPanel(), BorderLayout.SOUTH);
		this.add(new Canvas(), BorderLayout.CENTER);
		this.add(new WestPanel(),  BorderLayout.WEST);
		this.add(new NorthPanel(), BorderLayout.NORTH);
		
		this.setBackground(Color.WHITE);
	}
}
