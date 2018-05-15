# SolarSystem

This is a simple Java app to (roughly) model a 2-dimensional orbit of the planets of our solar system, written as part of an undergraduate computing course.

## Getting Started

An Apache build file is included, so the easiest way to run the app is to call ```ant run``` in the repository root directory.

If you choose to compile this manually, note that the main class is ```com.jmcquillan.solarsystem.SolarSystem```.

## Implementation

The mathematics used are relatively rudimentary.

A simple Euler integration method is used to propagate the simulation through time. This leads to drift over long simulation lengths, as is most evidenced by observing Mercury, which crashes into the sun. The Verlet algorithm is known to have less drift for a periodic trajectory, and will be implemented (hopefully) soon.

Only 2-body interactions between the sun and each body are considered. n-body interactions are a possible expansion, however the resulting chaotic behaviour would be incorrect unless an appropriate integration method is used, as explained above.

Polar coordinates are the most natural coordinate system to use, with the Sun located at <img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/648bc95f900f2eb70957b396497d7a22.svg?invert_in_darkmode" align=middle width=37.903635pt height=21.10812pt/>. The kinetic and potential energies are 
<p align="center"><img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/fd953720ded30d9b3a957eebc7ae1b53.svg?invert_in_darkmode" align=middle width=272.4117pt height=33.5874pt/></p>

where <img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/5201385589993766eea584cd3aa6fa13.svg?invert_in_darkmode" align=middle width=12.876435pt height=22.38192pt/> is Newton's gravitational constant, <img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/fb97d38bcc19230b0acd442e17db879c.svg?invert_in_darkmode" align=middle width=17.67348pt height=22.38192pt/> is the mass of the Sun, and <img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/0e51a2dede42189d77627c4d742822c3.svg?invert_in_darkmode" align=middle width=14.379255pt height=14.10255pt/> is the mass of the orbiting body.

The Lagrangian then becomes
<p align="center"><img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/c643b091d079f2bda71de3496f8471b7.svg?invert_in_darkmode" align=middle width=205.5669pt height=57.313575pt/></p>
and the two Euler-Lagrange equations,
<p align="center"><img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/8ec6f48eb758dc0bbb451d244cb3c168.svg?invert_in_darkmode" align=middle width=271.5636pt height=39.30498pt/></p>
yield the following equations of motion:
<p align="center"><img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/ce9e6d1ef967576ad503650250376bf1.svg?invert_in_darkmode" align=middle width=228.11085pt height=37.671975pt/></p>
These are then numerically integrated to simulate the orbital trajectory.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgements

The <img src="https://rawgit.com/johanmcquillan/solar_system/master/svgs/c068b57af6b6fa949824f73dcb828783.svg?invert_in_darkmode" align=middle width=42.05817pt height=22.407pt/> equations in this document were compile with ```readme2tex``` from the repository <https://github.com/leegao/readme2tex>.
