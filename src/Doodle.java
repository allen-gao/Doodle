import java.awt.GridLayout;

import javax.swing.JFrame;

public class Doodle extends JFrame {
	
	private int width = 600;
	private int height = 600;
	
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
		Doodle frame = new Doodle();
	}
}
