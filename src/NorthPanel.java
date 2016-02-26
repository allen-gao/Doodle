import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class NorthPanel extends JPanel implements Observer {
	
	Model model;
	JMenuBar menuBar;
	
	public NorthPanel(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border margin = new EmptyBorder(0, 0, 2, 0);
		this.setBorder(BorderFactory.createCompoundBorder(margin, border));
		
		menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		JMenuItem loadItem = new JMenuItem("Load");
		fileMenu.add(loadItem);
		
		JMenu viewMenu = new JMenu("View");
		menuBar.add(viewMenu);
		JMenuItem fullSizeItem = new JMenuItem("Full Size");
		viewMenu.add(fullSizeItem);
		JMenuItem fitItem = new JMenuItem("Fit Window");
		viewMenu.add(fitItem);
		
		this.add(menuBar);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
