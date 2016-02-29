import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Doodle extends JFrame {
	
	private static int width = 600;
	private static int height = 600;
	
	private static GamePanel gamePanel;
	private static Model model;
	
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
				Doodle frame = new Doodle(); // main
				model = new Model(); // model
				gamePanel = new GamePanel(model); // view + controller
				model.setGamePanel(gamePanel);
				frame.setContentPane(gamePanel);
				frame.setMinimumSize(new Dimension(500, 450));
				
				frame.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent e) {
						model.resized();
					}
				});
			}
		});
	}
}
