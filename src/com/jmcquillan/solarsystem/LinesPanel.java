package com.jmcquillan.solarsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

/** JPanel containing some lines and text. */
public class LinesPanel extends JPanel {
	
	/** Constructor just sets size of panel. */
	public LinesPanel(int width, int height) {
		setPreferredSize(new Dimension(width,height));
	}
	
	/**
	 * Must override this method, which is called
	 * by the Swing framework whenever the display
	 * needs updating.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // call superclass method first
		int width = getWidth();
		int height = getHeight();
		for (double r=0.0; r<1.0; r+=0.05) {
			int x = (int) (width*r);
			int y = height - (int) (height*r);
			g.drawLine(x, 0, 0, y);
		}
		Font f = new Font("TimesRoman",Font.BOLD,28);
		g.setFont(f);
		g.drawString("Some lines",width/2,height/2);
	}
}