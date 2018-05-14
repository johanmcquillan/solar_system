package com.jmcquillan.solarsystem;

import java.awt.Color;

/** Models a planet performing complete orbits about the sun.
 * Uses polar coordinates and Lagrangian mechanics to simulate motion.
 * All calculations in astronomical units and years.
 * @author Johan G. McQuillan
 */
public class CelestialPlanet extends CelestialBody {

    /** Constructor for CelestialBody.
     * @param name Name of the body.
     * @param r Initial distance from the Sun in AU.
     * @param rDot Initial radial velocity in AU/year.
     * @param theta Initial angle in rad.
     *              0 is measured directly above the Sun.
     *              Theta increases anti-clockwise.
     * @param thetaDot Initial angular velocity in rad/year.
     * @param colour Colour of body.
     * @param size Image size of body.
     */
    public CelestialPlanet(String name, double r, double rDot, double theta, double thetaDot, Color colour, int size) {
        super(name, r, rDot, theta, thetaDot, colour, size);
    }
    
    /** Calculates new position and velocity.
     * The time step of increment given by dt.
     * 
     * Using Langragian mechanics and considering 
     * only the interaction between this orbiter 
     * and the sun, the equations of motion may
     * be shown to be:
     * r'' = r*theta'^2 - M*G / r^2;
     * theta'' = -2*r'*theta' / r;
     * where ' indicates the first derivative
     * with respect to time, and '' the second.
     * 
     * Unlike the superclass, theta is constrained to lie between 0 and 2*pi.
     * This is because CelestialBody objects may not be doing complete orbits, whereas planets are.
     * If theta is unconstrained, after a certain number of orbits it will be too big to store.
     */
    public void increment() {
        // Calculate acceleration as given in the javadoc
        double rDDot = this.r * this.thetaDot * this.thetaDot - MG / (this.r*this.r);
        double thetaDDot = -2 * this.rDot * this.thetaDot / this.r;

        // increment velocity
		this.rDot = this.rDot + rDDot * dt;
		this.thetaDot = this.thetaDot + thetaDDot * dt;
        
        // increment position
		this.r = this.r + this.rDot * dt;
		this.theta = this.theta + this.thetaDot * dt;
        
        // Correct if theta is not between 0 and 2*pi
        double n = this.theta / Math.PI;
        if ((n > 2) || (n < 0)) this.theta = this.theta - this.theta * (n-1);
    }
}
