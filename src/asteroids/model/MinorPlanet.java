package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class to represent minor planets as a special kind of round entities.
 * 
 * @invar  	The mass of each minor planet must be a valid mass for any
 *         	minor planet.
 *       	| canHaveAsMass(getMass())
 * 
 * @invar  	Each minor planet can have its density as density .
 *       	| canHaveAsDensity(this.getDensity())
 *       
 * @invar  	Each minor planet must have a proper space.
 *       	| hasProperSpace()
 * 
 * @author amber_000
 *
 */
abstract class MinorPlanet extends RoundEntity{
	/**
	 * Initialize this new round entity with given parameters.
	 *
	 * @param	x
	 * 			The x-coordinate of the position of this new minor planet, in kilometer.
	 * 
	 * @param	y
	 * 			The y-coordinate of the position of this new minor planet, in kilometer.
	 * 
	 * @param 	xVelocity
	 *			The x-coordinate velocity for this new minor planet, in kilometer/second.
	 *
	 * @param 	yVelocity
	 * 			The y-coordinate velocity for this new minor planet, in kilometer/second.
	 * 
	 * @param	radius
	 * 			The radius of this new minor planet, in kilometer.
	 */
	@Raw
	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
	}

	@Override
	public void terminate() {
		if (!this.isTerminated()){
			if (this.hasSpace())
				this.removeOutSpace();	
			this.isTerminated = true;
		}
	}
	
	/**
	 * Returns the absolute minimum radius of all minor planets.
	 */
	@Override
	public double getMinRadius() {
		return 5;
	}
	
	/**
	 * Return the density of this bullet.
	 * 
	 * @return	Returns the density of this bullet.
	 */
	@Override
	@Basic 
	@Raw 
	@Immutable
	public double getDensity() {
		return this.density;
	}
	
	/**
	 * Check whether this minor planet can have the given density as its density.
	 *  
	 * @param  	density
	 *         	The density to check.
	 *         
	 * @return 	The density must be a number.
	*/
	@Raw
	public boolean canHaveAsDensity(double density) {
		return !Double.isNaN(density);
	}
	
	/**
	 * Variable registering the density of this minor planet.
	 */
	protected double density; 
//	change this eh
	
	/**
	 * Return the mass of this minor planet computed by its radius and density.
	 * 
	 * @return	Returns the mass of this minor planet.
	 */
	@Override
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Check whether the given mass is a valid mass for any minor planet.
	 * 
	 * @param  	mass
	 *         	The mass to check.
	 *         
	 * @return 	The given mass must be a number.
	 */
	public boolean canHaveAsMass(double mass) {
		return !Double.isNaN(mass);
	}
		
	/**
	 * Variable registering the mass of this minor planet.
	 */
	protected double mass;	
}
