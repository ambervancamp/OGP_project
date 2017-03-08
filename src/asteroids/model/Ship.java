package asteroids.model;

import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class to represent spaceships, their parameters and possible movements.
 * 
 * @invar  Each ship can have its orientation as orientation.
 *        | canHaveAsOrientation(this.getOrientation())
 *  
 * 
 * @author amber_000
 */

public class Ship {

	/**
	 * Initialize this new ship with given parameters.
	 * 
	 * @param	x
	 * 			The x-coordinate of the position of this new ship, in km.
	 * @param	y
	 * 			The y-coordinate of the position of this new ship, in km.
	 * @param 	xVelocity
	 *			The x-coordinate velocity for this new ship.
	 * @param 	yVelocity
	 * 			The y-coordinate velocity for this new ship.
	 * @param	radius
	 * 			The radius of this new ship.
	 * @param	orientation
	 * 			The orientation of this new ship, in radians.
	 * 
	 * @post 	...
	 * 
	 * @effect	...
	 * 
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) {
		setx(x);
		sety(y);
		setxVelocity(xVelocity);
		setyVelocity(yVelocity);
		setRadius(radius);
		setOrientation(orientation);
	}

	
	/**
	 * Set the orientation of this ship.
	 * 
	 * @param 	orientation
	 *          The orientation for this new ship.
	 *          
	 * @post 	The orientation of this new ship is equal to the given orientation.
	 *       	| new.getOrientation() == orientation
	 *       
	 * @throws 	IllegalOrientationException
	 *          This new ship cannot have the given orientation as its
	 *          orientation. 
	 *          | ! canHaveAsOrientation(this.getOrientation())
	 */
	@Basic
	@Raw
	@Immutable
	public void setOrientation(double orientation) throws IllegalOrientationException {
		if (!canHaveAsOrientation(orientation))
			throw new IllegalOrientationException();
		this.orientation = orientation;
		
		// IllegalOrientationException vervangen door ModelException? IFacade enkel ModelExeption, maar herkent 
		// deze wanneer een exeption gegooid wordt en maar deze daar dan een ModelException van?
	}

	/**
	 * Return the orientation of this ship.
	 */
	@Basic
	@Raw
	@Immutable
	public double getOrientation() {
		return this.orientation;
	}

	/**
	 * Check whether this ship can have the given orientation as its
	 * orientation.
	 * 
	 * @param 	orientation
	 *          The orientation to check.
	 * @return	True if and only if the orientation angle is positive
	 * 			and smaller then 2*PI.
	 * 			| result == 0 <= orientation && orientation < 2*Math.PI
	 */
	@Raw
	public boolean canHaveAsOrientation(double orientation) {
		return 0 <= orientation && orientation < 2*Math.PI;
	}

	/**
	 * Variable registering the orientation of this ship.
	 */
	private final double orientation;
	

}
