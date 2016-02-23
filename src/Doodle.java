import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Doodle extends JFrame {
	
	private static int width = 600;
	private static int height = 600;
	
	private static GamePanel gamePanel;
	
	public Doodle() {
		super();
		this.setTitle("Doodle");
		this.setSize(width, height);
		this.getContentPane().setLayout(new GridLayout());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
	}
	
	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Doodle frame = new Doodle();
				gamePanel = new GamePanel();
				frame.add(gamePanel);
			}
		});
	}
}
