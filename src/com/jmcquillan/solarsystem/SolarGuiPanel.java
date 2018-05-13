package com.jmcquillan.solarsystem;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SolarGuiPanel extends JPanel implements ActionListener, ChangeListener {
	private SolarPanel solarPanel; // panel containing animation
	private JButton startButton;
	private JButton stopButton;
	private JButton exitButton;
	private JButton nameButton;
	private JButton lineButton;
	private JButton detailButton;
	private JLabel delayLabel;
	private JLabel scaleLabel;
	private JSlider delaySlider;
	private JSlider scaleSlider;

	/** Create JPanel containing animation panel and buttons. */
	public SolarGuiPanel() {
		super();
		setPreferredSize(new Dimension(250,300));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		solarPanel = new SolarPanel(200,200);
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		exitButton = new JButton("Exit");
		nameButton = new JButton("Toggle Names");
		lineButton = new JButton("Toggle Radii");
		detailButton = new JButton("Toggle Details");
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		exitButton.addActionListener(this);
		nameButton.addActionListener(this);
		lineButton.addActionListener(this);
		detailButton.addActionListener(this);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(nameButton);
		buttonPanel.add(lineButton);
		buttonPanel.add(detailButton);

		// Create slider to change animation delay
		delayLabel = new JLabel("Delay / ms");
		delaySlider = new JSlider(SwingConstants.HORIZONTAL,0,50,10);
		delaySlider.setBounds( 20, 35, 10, 40 );
		delaySlider.setMajorTickSpacing( 10 );
		delaySlider.setMinorTickSpacing( 5 );
		delaySlider.setPaintTicks(true);
		delaySlider.setPaintLabels(true);
		delaySlider.addChangeListener(this);

		// Create slider to change scale
		scaleLabel = new JLabel("Scale / %");
		scaleSlider = new JSlider(SwingConstants.HORIZONTAL,10,100,100);
		scaleSlider.setBounds( 20, 35, 10, 40 );
		scaleSlider.setMajorTickSpacing( 10 );
		scaleSlider.setMinorTickSpacing( 5 );
		scaleSlider.setPaintTicks(true);
		scaleSlider.setPaintLabels(true);
		scaleSlider.addChangeListener(this);

		// Add to panel
		buttonPanel.add(delayLabel);
		buttonPanel.add(delaySlider);
		buttonPanel.add(scaleLabel);
		buttonPanel.add(scaleSlider);

		add(solarPanel);
		add(buttonPanel);
	}
	/** Respond to button clicks */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==startButton) start();
		else if (e.getSource()==stopButton) stop();
		else if (e.getSource()==lineButton) toggleRadii();
		else if (e.getSource()==nameButton) toggleNames();
		else if (e.getSource()==detailButton) toggleDetails();
		else if (e.getSource()==exitButton) System.exit(0);
	}
	/** Start animation when applet is started */
	public void start() {solarPanel.start();}
	/** Stop animation when applet is stopped */
	public void stop() {solarPanel.stop();}

	/** Toggles the display of names in the solarPanel **/
	public void toggleNames() {
		if (solarPanel.names) solarPanel.names = false;
		else solarPanel.names = true;
	}
	
	/** Toggles the display of radial lines in the solarPanel **/
	public void toggleRadii() {
		if (solarPanel.radii) solarPanel.radii = false;
		else solarPanel.radii = true;
	}

	/** Toggles the display of orbital speed and radius in the solarPanel **/
	public void toggleDetails() {
		if (solarPanel.details) solarPanel.details = false;
		else solarPanel.details = true;
	}

	/** Respond to slider changes **/
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == delaySlider) solarPanel.setDelay(delaySlider.getValue());
		else if (e.getSource() == scaleSlider) solarPanel.setScale(scaleSlider.getValue());
	}
}
