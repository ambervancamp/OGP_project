package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

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
	// DE RADIUS MOET KLEINER WORDEN BIJ HET BEWEGEN!!!!! Bij move()?
	
	/**
	 * Return the mass of this planetoid computed by its radius and density.
	 * 
	 * @return	Returns the mass of this planetoid.
	 */
	@Override
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Return the density of this planetoid.
	 * 
	 * @return	Returns the density of this planetoid.
	 */
	@Override
	@Basic 
	@Raw 
	@Immutable
	public double getDensity() {
		return this.density;
	}
	
	/**
	 * Variable registering the density of this planetoid.
	 */
	private final double density;	
	
	/**
	 * Variable registering the mass of this planetoid.
	 */
	private double mass;
}
