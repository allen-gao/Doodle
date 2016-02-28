import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Doodle extends JFrame {
	
	private static int width = 600;
	private static int height = 600;
	private static int delay = 100;
	
	private static GamePanel gamePanel;
	private static Model model;
	
	private static Timer resizeTimer;
	private static long lastResize = 0;
	
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
				
				frame.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent e) {
						if (resizeTimer == null) {
							ActionListener taskPerformer = new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									model.resized();
									resizeTimer.stop();
								}
							};
							resizeTimer = new Timer(delay, taskPerformer);
						}
						else if (lastResize + delay < System.currentTimeMillis()) {
							resizeTimer.restart();
						}
					}
				});
			}
		});
	}
}
