package com.jmcquillan.solarsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/** 2D animation of the solar system.
 * Distances are in astronomical units.
 * Times are in years.
 *
 * @author Johan G. McQuillan
 */
public class SolarPanel extends JPanel implements ActionListener {
    private ArrayList<CelestialBody> celestials = new ArrayList<CelestialBody>();
    private Timer animationTimer;
    private static int delay = 10;
    private double t;
    private double dt = 0.0002;
    private double scaler = 1;
    private int sunRadius = 20;
    Ellipse2D.Double sun = new Ellipse2D.Double(-sunRadius/2, -sunRadius/2, sunRadius, sunRadius);
    public boolean names; // Displays names if true
    public boolean radii; // Displays radial lines for planets if true
    public boolean details; // Displays radius and speed if true

    public SolarPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        CelestialBody.setDeltaTime(dt);

        // Initialise toggleable booleans
        this.names = true;
        this.radii = false;
        this.details = true;

        /* Initial conditions for planets from Wikipedia (they don't have to be perfect).
         * The initial radius is (roughly) the average of the aphelion and perihelion of the orbit.
         * The initial angle for all planets is pi/2 (vertically above the sun).
         * The initial radial velocity for all planets is 0.
         * The initial angular velocity for all planets is 2*pi/T where T is the orbital period in Earth years*/
        celestials.add(new CelestialPlanet("Mercury", 0.38, 0, Math.PI/2, 2*Math.PI/0.24, Color.WHITE, 6));
        celestials.add(new CelestialPlanet("Venus", 0.72, 0, Math.PI/2, 2*Math.PI/0.6, Color.YELLOW, 7));
        celestials.add(new CelestialPlanet("Earth", 1, 0, Math.PI/2, 2*Math.PI, new Color(0,190,255,255), 10));
        celestials.add(new CelestialPlanet("Mars", 1.53, 0, Math.PI/2, 2*Math.PI/1.88, new Color(255,77,0,255), 10));
        celestials.add(new CelestialPlanet("Jupiter", 5.2, 0, Math.PI/2, 2*Math.PI/11.8, Color.ORANGE, 15));
        celestials.add(new CelestialPlanet("Saturn", 9.5, 0, Math.PI/2, 2*Math.PI/29.45, new Color(255,174,91,255), 13));
        celestials.add(new CelestialPlanet("Uranus", 19, 0, Math.PI/2, 2*Math.PI/84, new Color(91,255,251,255), 12));
        celestials.add(new CelestialPlanet("Neptune", 30, 0, Math.PI/2, 2*Math.PI/164.8, new Color(98,119,255), 11));
        // Comets are added with arbitrary positions and velocities
        celestials.add(new CelestialBody("Comet I", 2.5, -0.3, 1.2*Math.PI, -0.5, Color.ORANGE, 2));
        celestials.add(new CelestialBody("Comet II", 3, -3, 0.3*Math.PI, -0.5, Color.MAGENTA, 2));

        // Start animation
        animationTimer = new Timer(delay,this);
        animationTimer.start();
    }

    /** Scale a Cartesian coordinate for display on the screen.
     *
     * @param x Coordinate to scale.
     * @return Scaled coordinate ie. pixel.
     */
    public int scale(double x) {return (int) (scaler * getHeight()/7 * x);}

    public void actionPerformed(ActionEvent event) {
        this.t += dt;
        // Loop over all bodies and calculate new positions
        for (CelestialBody C : celestials) C.increment();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        // Convert to graphics2D to allow ellipse drawing
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        int height = getHeight();
        int width = getWidth();

        // Fill in background
        g2.setColor(new Color(20,20,20,255));
        g2.fillRect(0, 0, width, height);

        // Display time
        g2.setFont(new Font("Monospaced", Font.BOLD, 15));
        g2.setColor(Color.WHITE);
        g2.drawString(String.format("Time: %1.3f years",t), 50, 50);

        // Move centre to the origin
        g2.translate(width/2, height/2);
        g2.setFont(new Font("Monospaced",Font.BOLD,12));
        
        // Loop over all bodies
        for (CelestialBody C : this.celestials) {
            // Get planet size, Cartesian coords, and colour
            int size = C.getSize();
            int x = (scale(C.getX()));
            int y = (-scale(C.getY()));
            g2.setColor(C.getColour());

            // Draw planet
            g2.fill(new Ellipse2D.Double(x-size/2,y-size/2,size,size));

            // Check if buttons are toggled and display names, details, and radii
            if (this.names) g2.drawString(C.getName(), x+5, y-5);
            if (this.details) {
                // Display distance from sun and speed
                g2.drawString(String.format("r = %1.2f AU",C.getR()), x+7, y+5);
                g2.drawString(String.format("v = %1.2f km/s",C.getV()), x+7, y+14);
            }
            // Do not show radii for CelestialBody, only CelestialPlanet
            if (this.radii && C.getClass() == CelestialPlanet.class) g2.drawLine(0, 0, x, y);
        }
        // Draw and label sun
        g2.setColor(Color.YELLOW);
        g2.fill(this.sun);
        g2.setFont(new Font("Monospaced",Font.BOLD,20));
        if (this.names) g2.drawString("Sol", 10, -10);
    }

    /** Start the animation. */
    public void start() {this.animationTimer.start();}

    /** Stop the animation. */
    public void stop() {this.animationTimer.stop();}

    /** Set new animation delay.
     *
     * @param delay New delay
     */
    public void setDelay(int delay) {
        this.animationTimer.stop();
        this.animationTimer.setDelay(delay);
        SolarPanel.delay = delay;
        this.animationTimer.start();
    }

    /** Set new animation scale
     *
     * @param s New scaler, as a percentage
     */
    public void setScale(int s) {this.scaler = 0.01*s;}
}
