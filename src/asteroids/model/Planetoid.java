package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 *	A class to represent planetoids as a special kind of minor planets.
 *
 * @author amber_000
 *
 */
public class Planetoid extends MinorPlanet {

	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.density = 0.917*Math.pow(10,12);
		this.mass = 4.0/3.0*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
	}
	
	@Override
	public double getRadius() {
		return this.radius;
	}
	// De radius wordt kleiner bij het bewegen van een plantoid
}
