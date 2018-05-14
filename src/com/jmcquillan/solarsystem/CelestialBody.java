package com.jmcquillan.solarsystem;

import java.awt.Color;

/** Models a celestial object interacting with the sun.
 * Uses polar coordinates and Lagrangian mechanics to simulate motion.
 * All calculations in astronomical units and years.
 * @author Johan
 */
public class CelestialBody {
    protected int size;
    protected String name;
    protected double r;
    protected double rDot;
    protected double theta;
    protected double thetaDot;
    protected Color colour;
    protected static double dt; // Time step in years
    protected static final double MG = 39.42; // Solar Mass * Gravitational Constant in au^3/yr^2
    protected static final double AU = 1.496E8; // Astronomical Unit in km
    protected static final double SEC_IN_YEAR = 3.154E7; // Number of seconds in a year
    
    /** Constructor **/
    public CelestialBody(String Name, double r, double rDot, double theta, double thetaDot, Color colour, int size) {
        this.name = Name;
        this.r = r;
        this.rDot = rDot;
        this.theta = theta;
        this.thetaDot = thetaDot;
        this.colour = colour;
        this.size = size;
    }
    
    /** Calculates new position and velocity.
     * The time step of increment given by dt.
     * 
     * Using Langragian mechanics and considering 
     * only the interaction between this object
     * and the sun, the equations of motion may
     * be shown to be:
     * r'' = r*theta'^2 - M*G / r^2;
     * theta'' = -2*r'*theta' / r;
     * where ' indicates the first derivative
     * with respect to time, and '' the second.
     */
    public void Increment() {
        // Calculate acceleration as given in the javadoc
        double rDDot = this.r * this.thetaDot * this.thetaDot - MG / (this.r*this.r);
        double thetaDDot = -2 * this.rDot * this.thetaDot / r;
        
        // Increment velocities
		this.rDot = this.rDot + rDDot * dt;
		this.thetaDot = this.thetaDot + thetaDDot * dt;
        
        // Increment position
		this.r = this.r + this.rDot * dt;
		this.theta = this.theta + this.thetaDot * dt;
    }

    /** Set new time interval in years **/
    public static void SetDeltaTime(double dt) {CelestialBody.dt = dt;}
    
    /** Getter method for orbiter name **/
    public String getName() {return this.name;}
    
    /** Getter method for orbiter position in Cartesian **/
    public double getX() {return (this.r*Math.cos(this.theta));}
    public double getY() {return (this.r*Math.sin(this.theta));}
    
    /** Getter method for orbiter distance from sun **/
    public double getR() {return this.r;}
    
    /** Getter method for orbiter speed in km/s **/
    public double getV() {return Math.sqrt((Math.pow(this.rDot *AU/SEC_IN_YEAR, 2) +
										   (Math.pow(this.thetaDot * this.r*AU/SEC_IN_YEAR, 2))));}
    
    /** Getter method for orbiter size on panel display **/
    public int getSize() {return this.size;}
    
    /** Getter method for orbiter colour **/
    public Color getColour() {return this.colour;}
}
