package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

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
	
	/**
	 * Return the density of this asteroid.
	 * 
	 * @return	Returns the density of this asteroid.
	 */
	@Override
	@Basic 
	@Raw 
	@Immutable
	public double getDensity() {
		return this.density;
	}
	
	/**
	 * Return the mass of this asteroid computed by its radius and density.
	 * 
	 * @return	Returns the mass of this asteroid.
	 */
	@Override
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Variable registering the density of this asteroid.
	 */
	private final double density;	
	
	/**
	 * Variable registering the mass of this asteroid.
	 */
	private double mass;
	
	@Override
	public void move(double duration){
		if (!canHaveAsDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
	}
}
