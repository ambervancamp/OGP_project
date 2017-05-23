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
public abstract class MinorPlanet extends RoundEntity{
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
			if (this.hasSpace()){
				this.removeOutSpace();	
				this.isTerminated = true;
			}
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
	
	@Override
	public void resolveCollision(RoundEntity other){
		if (!other.inSameSpace(this) || other == this)
			throw new IllegalArgumentException();
		else if (other instanceof MinorPlanet)
			this.setVelocityAfterBounce(other);
		else
			other.resolveCollision(this);
	}
}
