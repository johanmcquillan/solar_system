package com.jmcquillan.solarsystem;

import javax.swing.*;
/** Simple Swing application illustrating graphics. */
public class Lines extends JPanel {
    /** Create and display a JFrame containing a LinesPanel. */
    private static void createAndDisplayGui() {
        JFrame frame = new JFrame("Swing graphics example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LinesPanel panel = new LinesPanel(400,400);
        frame.add(panel); // Add panel to frame
        frame.pack(); // Set component sizes and layout
        frame.setVisible(true); // Display the resulting frame
    }
    /** Call method to create and display GUI. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndDisplayGui();
            }
        });
    }
}
