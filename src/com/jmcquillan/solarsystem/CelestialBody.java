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
	protected double rdot;
	protected double theta;
	protected double thetadot;
	protected Color colour;
	protected static double dt; // Time step in years
	protected static final double MG = 39.42; // Solar Mass * Gravitational Constant in au^3/yr^2
	protected static final double AU = 1.496E8; // Astronomical Unit in km
	protected static final double SEC_IN_YEAR = 3.154E7; // Number of seconds in a year
	
	/** Constructor **/
	public CelestialBody(String Name, double R, double Rdot, double Theta, double Thetadot, Color Colour, int Size) {
		this.name = Name;
		this.r = R;
		this.rdot = Rdot;
		this.theta = Theta;
		this.thetadot = Thetadot;
		this.colour = Colour;
		this.size = Size;
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
		double rdotdot = r*thetadot*thetadot - MG / (r*r);
		double thetadotdot = -2*rdot*thetadot / r;
		
		// Increment velocities
		rdot = rdot + rdotdot * dt;
		thetadot = thetadot + thetadotdot * dt;
		
		// Increment position
		r = r + rdot * dt;
		theta = theta + thetadot * dt;
	}

	/** Set new time interval in years **/
	public static void SetDeltaTime(double DT) {CelestialBody.dt = DT;}
	
	/** Getter method for orbiter name **/
	public String getName() {return name;}
	
	/** Getter method for orbiter position in Cartesian **/
	public double getX() {return (r*Math.cos(theta));}
	public double getY() {return (r*Math.sin(theta));}
	
	/** Getter method for orbiter distance from sun **/
	public double getR() {return r;}
	
	/** Getter method for orbiter speed in km/s **/
	public double getV() {return Math.sqrt((Math.pow(rdot*AU/SEC_IN_YEAR,2) + (Math.pow(thetadot*r*AU/SEC_IN_YEAR,2))));}
	
	/** Getter method for orbiter size on panel display **/
	public int getSize() {return size;}
	
	/** Getter method for orbiter colour **/
	public Color getColour() {return colour;}
}
