import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	private int defaultWindowWidth = 584;
	private JSlider slider;
	private int sliderButtonWidthOffset = 350;
	private int sliderWidthDefault = defaultWindowWidth - sliderButtonWidthOffset;
	private int sliderHeight = 40;
	private JButton playForward;
	private JButton playBack;
	private JButton start;
	private JButton end;
	private int animateInt = 0; // 1 if forward, -1 if backward, 0 if neutral
	
	private Timer sliderTimerF;
	private Timer sliderTimerB;
	
	public SouthPanel(Model model) {
		this.model = model;
		model.addObserver(this);
		this.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		Border margin = new EmptyBorder(2, 0, 0, 0);
		this.setBorder(BorderFactory.createCompoundBorder(margin, border));
		
		
		playForward = new JButton("Play (F)");
		playForward.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				animateInt = 1;
				int delay = 50; //milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						int newIndex = model.getLineIndex() + 5;
						if (animateInt != 1) {
							sliderTimerF.stop();
						}
						else if (model.getLineIndexMax() <= newIndex) {
							model.setLineIndex(model.getLineIndexMax());
							sliderTimerF.stop();
							animateInt = 0;
						}
						else {
							model.setLineIndex(newIndex);
						}
					}
				};
				sliderTimerF = new Timer(delay, taskPerformer);
				sliderTimerF.start();				
			}
		});
		this.add(playForward);
		
		playBack = new JButton("Play (B)");
		playBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				animateInt = -1;
				int delay = 50; //milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						int newIndex = model.getLineIndex() - 5;
						if (animateInt != -1) {
							sliderTimerB.stop();
						}
						else if (newIndex <= 0) {
							model.setLineIndex(0);
							sliderTimerB.stop();
							animateInt = 0;
						}
						else {
							model.setLineIndex(newIndex);
						}
					}
				};
				sliderTimerB = new Timer(delay, taskPerformer);
				sliderTimerB.start();				
			}
		});
		this.add(playBack);
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
		slider.setBackground(Color.WHITE);
		slider.setPreferredSize(new Dimension(sliderWidthDefault, sliderHeight));
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
	
		start = new JButton("Start");
		start.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				model.setLineIndex(0);
			}
		});
		this.add(start);
		
		end = new JButton("End");
		end.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				model.setLineIndex(model.getLineIndexMax());
			}
		});
		this.add(end);
	}
	
	public void resized() {
		slider.setPreferredSize(new Dimension(this.getWidth() - sliderButtonWidthOffset, sliderHeight));
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
		playForward.setEnabled(model.getLineIndex() != model.getLineIndexMax());
		playBack.setEnabled(model.getLineIndex() != 0);
		start.setEnabled(model.getLineIndex() != 0);
		end.setEnabled(model.getLineIndex() != model.getLineIndexMax());
	}
}
