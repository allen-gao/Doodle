import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SouthPanel extends JPanel implements Observer {
	
	private Model model;
	private int ticks = 0;
	private JSlider slider;
	private Timer sliderTimer;
	
	public SouthPanel(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border margin = new EmptyBorder(2, 0, 0, 0);
		this.setBorder(BorderFactory.createCompoundBorder(margin, border));
		
		
		JButton play = new JButton("Play");
		play.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int delay = 50; //milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						int newIndex = model.getLineIndex() + 5;
						if (model.getLineIndexMax() <= newIndex) {
							model.setLineIndex(model.getLineIndexMax());
							sliderTimer.stop();
						}
						else {
							model.setLineIndex(newIndex);
						}
					}
				};
				sliderTimer = new Timer(delay, taskPerformer);
				sliderTimer.start();
				
				
				
			}
		});
		this.add(play);
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
		slider.setBackground(Color.WHITE);
		slider.setPreferredSize(new Dimension(350, 40));
		slider.setEnabled(false);
		slider.setPaintTicks(false);
		slider.setMajorTickSpacing(100);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				model.setLineIndex(slider.getValue());
			}
		});
		this.add(slider);
	
		JButton start = new JButton("Start");
		start.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				model.setLineIndex(0);
			}
		});
		this.add(start);
		
		JButton end = new JButton("End");
		end.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				model.setLineIndex(model.getLineIndexMax());
			}
		});
		this.add(end);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == model.getDrawnLines()) {
			if (model.getDrawnLines() != null) {
				this.ticks = model.getDrawnLines().size();
				slider.setMaximum(this.ticks * 100);
				slider.setValue(slider.getMaximum());
				model.setLineIndexMax(slider.getMaximum());
			}
			slider.setEnabled(this.ticks != 0);
			slider.setPaintTicks(this.ticks != 0);
		}
		if (arg == "lineIndex") {
			slider.setValue(model.getLineIndex());
		}
	}
}
