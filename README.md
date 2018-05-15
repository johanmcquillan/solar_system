# SolarSystem

This is a simple Java app to (roughly) model a 2-dimensional orbit of the planets of our solar system, written as part of an undergraduate computing course.

## Getting Started

An Apache build file is included, so the easiest way to run the app is to call ```ant run``` in the repository root directory.

If you choose to compile this manually, note that the main class is ```com.jmcquillan.solarsystem.SolarSystem```.

## Implementation

The mathematics used are relatively rudimentary.

A simple Euler integration method is used to propagate the simulation through time. This leads to drift over long simulation lengths, as is most evidenced by observing Mercury, which crashes into the sun. The Verlet algorithm is known to have less drift for a periodic trajectory, and will be implemented (hopefully) soon.

Only 2-body interactions between the sun and each body are considered. n-body interactions are a possible expansion, however the resulting chaotic behaviour would be incorrect unless an appropriate integration method is used, as explained above.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
