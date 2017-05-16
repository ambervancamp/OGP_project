package asteroids.model;

/**
 *	A class to represent asteroids as a special kind of minor planets.
 *
 *
 * @author amber_000
 */
public class Asteroid  extends MinorPlanet {

	public Asteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.density = 2.65*Math.pow(10,12);
		this.mass = 4.0/3.0*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
	}
}
