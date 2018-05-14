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
		this.solarPanel = new SolarPanel(200,200);
		this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.exitButton = new JButton("Exit");
        this.nameButton = new JButton("Toggle Names");
        this.lineButton = new JButton("Toggle Radii");
        this.detailButton = new JButton("Toggle Details");
        this.startButton.addActionListener(this);
        this.stopButton.addActionListener(this);
        this.exitButton.addActionListener(this);
        this.nameButton.addActionListener(this);
        this.lineButton.addActionListener(this);
        this.detailButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
        buttonPanel.add(this.startButton);
        buttonPanel.add(this.stopButton);
        buttonPanel.add(this.exitButton);
        buttonPanel.add(this.nameButton);
        buttonPanel.add(this.lineButton);
        buttonPanel.add(this.detailButton);

        // Create slider to change animation delay
        this.delayLabel = new JLabel("Delay / ms");
        this.delaySlider = new JSlider(SwingConstants.HORIZONTAL,0,50,10);
        this.delaySlider.setBounds( 20, 35, 10, 40 );
        this.delaySlider.setMajorTickSpacing( 10 );
        this.delaySlider.setMinorTickSpacing( 5 );
        this.delaySlider.setPaintTicks(true);
        this.delaySlider.setPaintLabels(true);
        this.delaySlider.addChangeListener(this);

        // Create slider to change scale
        this.scaleLabel = new JLabel("Scale / %");
        this.scaleSlider = new JSlider(SwingConstants.HORIZONTAL,10,100,100);
        this.scaleSlider.setBounds( 20, 35, 10, 40 );
        this.scaleSlider.setMajorTickSpacing( 10 );
        this.scaleSlider.setMinorTickSpacing( 5 );
        this.scaleSlider.setPaintTicks(true);
        this.scaleSlider.setPaintLabels(true);
        this.scaleSlider.addChangeListener(this);

        // Add to panel
        buttonPanel.add(this.delayLabel);
        buttonPanel.add(this.delaySlider);
        buttonPanel.add(this.scaleLabel);
        buttonPanel.add(this.scaleSlider);

        add(this.solarPanel);
        add(buttonPanel);
    }
    /** Respond to button clicks */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.startButton) start();
        else if (e.getSource() == this.stopButton) stop();
        else if (e.getSource() == this.lineButton) toggleRadii();
        else if (e.getSource() == this.nameButton) toggleNames();
        else if (e.getSource() == this.detailButton) toggleDetails();
        else if (e.getSource() == this.exitButton) System.exit(0);
    }
    /** Start animation when applet is started */
    public void start() {this.solarPanel.start();}
    /** Stop animation when applet is stopped */
    public void stop() {this.solarPanel.stop();}

    /** Toggles the display of names in the solarPanel **/
    public void toggleNames() {this.solarPanel.names = ! this.solarPanel.names;}
    
    /** Toggles the display of radial lines in the solarPanel **/
    public void toggleRadii() {this.solarPanel.radii = ! this.solarPanel.radii;}

    /** Toggles the display of orbital speed and radius in the solarPanel **/
    public void toggleDetails() {this.solarPanel.details = ! this.solarPanel.details;}

    /** Respond to slider changes **/
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == this.delaySlider) this.solarPanel.setDelay(this.delaySlider.getValue());
        else if (e.getSource() == this.scaleSlider) this.solarPanel.setScale(this.scaleSlider.getValue());
    }
}
