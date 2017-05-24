package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

@Value

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

	
// 	All methods handling the termination of a minor planet.
	
	@Override
	public void terminate() {
		if (!this.isTerminated()){
			if (this.hasSpace()){
				this.removeOutSpace();	
				this.isTerminated = true;
			}
		}
	}

//	All methods related to the radius of a minor planet.
	
	/**
	 * Returns the absolute minimum radius of all minor planets.
	 */
	@Override
	public double getMinRadius() {
		return 5;
	}
	
//	All methods related to the denssity of a 
	
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
	
//	All methods related to the mass of aa minor planet.
	
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
	
//	All methods related to the moving and solving of collisions.

	@Override
	public void move(double duration){
		if (!canHaveAsDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
	}

	/**
	 * @effect	if the other entity is a minor planet too, their velocity will be changed
	 * 
	 * @effect	if the other entity is none of the above, we resolve the collision in that class
	 * 			| other.resolveCollision(this)
	 */
	
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
