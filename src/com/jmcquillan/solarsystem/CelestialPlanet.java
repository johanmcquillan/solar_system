package com.jmcquillan.solarsystem;

import java.awt.Color;

/** Models a planet performing complete orbits about the sun.
 * Uses polar coordinates and Lagrangian mechanics to simulate motion.
 * All calculations in astronomical units and years.
 * @author Johan
 */
public class CelestialPlanet extends CelestialBody {

    /** Constructor **/
    public CelestialPlanet(String Name, double R, double Rdot, double Theta, double Thetadot, Color Colour, int Size) {
        super(Name, R, Rdot, Theta, Thetadot, Colour, Size);
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
    public void Increment() {
        // Calculate acceleration as given in the javadoc
        double rdotdot = r*thetadot*thetadot - MG / (r*r);
        double thetadotdot = -2*rdot*thetadot / r;

        // Increment velocity
        rdot = rdot + rdotdot * dt;
        thetadot = thetadot + thetadotdot * dt;
        
        // Increment position
        r = r + rdot * dt;
        theta = theta + thetadot * dt;
        
        // Correct if theta is not between 0 and 2*pi
        double n = theta / Math.PI;
        if ((n > 2) || (n < 0)) theta = theta - theta * (n-1);
    }
}
