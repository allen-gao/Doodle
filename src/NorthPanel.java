import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");		
		menuBar.add(fileMenu);
		JMenuItem saveItem = new JMenuItem(new AbstractAction("Save") {
		    public void actionPerformed(ActionEvent e) {
		    	saveModel();
		    }
		});
		fileMenu.add(saveItem);
		JMenuItem loadItem = new JMenuItem(new AbstractAction("Load") {
		    public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG & GIF Images", "jpg", "gif");
				chooser.setFileFilter(filter);
				
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
					System.out.println(chooser.getSelectedFile());
					
					BinaryModel bin = null;
				      try
				      {
				         FileInputStream fileIn = new FileInputStream(chooser.getSelectedFile());
				         ObjectInputStream in = new ObjectInputStream(fileIn);
				         bin = (BinaryModel) in.readObject();
				         in.close();
				         fileIn.close();
				      }catch(IOException i)
				      {
				         i.printStackTrace();
				         return;
				      }catch(ClassNotFoundException c)
				      {
				         System.out.println("Employee class not found");
				         c.printStackTrace();
				         return;
				      }
				}
		    }
		});
		fileMenu.add(loadItem);
		
		JMenu space = new JMenu("");
		space.setEnabled(false);
		menuBar.add(space);
		
		JMenu viewMenu = new JMenu("View");
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButtonMenuItem fullSizeItem = new JRadioButtonMenuItem(new AbstractAction("Full Size") {
		    public void actionPerformed(ActionEvent e) {
		    	model.setFitWindow(false);
		    }
		});
		fullSizeItem.setSelected(true);
		this.model.setFitWindow(false);
		buttonGroup.add(fullSizeItem);
		viewMenu.add(fullSizeItem);
		JRadioButtonMenuItem fitItem = new JRadioButtonMenuItem(new AbstractAction("Fit Window") {
		    public void actionPerformed(ActionEvent e) {
		    	model.setFitWindow(true);
		    }
		});
		buttonGroup.add(fitItem);
		viewMenu.add(fitItem);
		menuBar.add(viewMenu);
		
		this.add(menuBar);
	}
	
	public void saveModel() {
		BinaryModel bin = new BinaryModel();
		bin.drawnLines = model.getDrawnLines();
		 try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("C:/data/employee.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(bin);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
