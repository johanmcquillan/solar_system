package com.jmcquillan.solarsystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SolarSystem {

    /** Main method.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Animation demo");

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1500,1000);

                JPanel panel = new SolarGuiPanel();
                frame.add(panel);
                frame.setVisible(true);
            }
        }
        );
    }
}
