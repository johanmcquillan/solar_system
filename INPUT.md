# SolarSystem

This is a simple Java app to (roughly) model a 2-dimensional orbit of the planets of our solar system, written as part of an undergraduate computing course.

## Getting Started

An Apache build file is included, so the easiest way to run the app is to call ```ant run``` in the repository root directory.

If you choose to compile this manually, note that the main class is ```com.jmcquillan.solarsystem.SolarSystem```.

## Mathematical Overview

The mathematics used are relatively rudimentary.

A simple Euler integration method is used to propagate the simulation through time. This leads to drift over long simulation lengths, as is most evidenced by observing Mercury, which crashes into the sun. The Verlet algorithm is known to have less drift for a periodic trajectory, and will be implemented (hopefully) soon.

Only 2-body interactions between the sun and each body are considered. $n$-body interactions are a possible expansion, however the resulting chaotic behaviour would be incorrect unless an appropriate integration method is used, as explained above.

Polar coordinates are the most natural coordinate system to use, with the Sun located at $r = 0$. The kinetic and potential energies are 
$$
    T = \frac{m}{2}\left(\dot{r}^2 + r^2\dot{\vartheta}^2\right); \qquad
    V = -\frac{GMm}{r};
$$

where $G$ is Newton's gravitational constant, $M$ is the mass of the Sun, and $m$ is the mass of the orbiting body.

The Lagrangian then becomes
$$
    \begin{aligned}
        \mathcal{L} & = T - V, \\
                    & = \frac{m}{2}\left( \dot{r}^2 + r^2\dot{\vartheta}^2 \right) + \frac{GMm}{r};
    \end{aligned}
$$
and the two Euler-Lagrange equations,
$$
    \frac{\mathop{}\!\mathrm{d}}{\mathop{}\!\mathrm{d} t} \left(\frac{\partial \mathcal{L}}{\partial \dot{r}}\right) = \frac{\partial \mathcal{L}}{\partial r}; \qquad
    \frac{\mathop{}\!\mathrm{d}}{\mathop{}\!\mathrm{d} t} \left(\frac{\partial \mathcal{L}}{\partial \dot{\vartheta}}\right) = \frac{\partial \mathcal{L}}{\partial \vartheta};
$$
yield the following equations of motion:
$$
    \ddot{r} = r\dot{\vartheta}^2 - \frac{GM}{r^2}; \qquad
    \ddot{\vartheta} = -2 \frac{\dot{\vartheta}\dot{r}}{r}.
$$
These are then numerically integrated to simulate the orbital trajectory.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgements

The $\text{\LaTeX}$ equations in this document were compile with ```readme2tex``` from the repository <https://github.com/leegao/readme2tex>.
