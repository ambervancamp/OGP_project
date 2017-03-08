package asteroids.model;

import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class to represent spaceships, their parameters and possible movements.
 * 
 * @invar  	Each ship can have its orientation as orientation.
 *        	| canHaveAsOrientation(this.getOrientation())
 *
 * @invar  	The radius of each ship must be a valid radius for any ship.
 *       	| isValidRadius(getRadius())
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
	 *			The x-coordinate velocity for this new ship, in km/s.
	 * @param 	yVelocity
	 * 			The y-coordinate velocity for this new ship, in km/s.
	 * @param	radius
	 * 			The radius of this new ship, in km. 
	 * @param	orientation
	 * 			The orientation of this new ship, in radians. The orientation of a ship 
	 * 			facing right is 0, a ship facing up is at angle Math.PI/2, a ship facing left 
	 * 			is at angle Math.PI and a ship facing down is at angle 3*Math.PI/2. 
	 * 
	 * @pre 	This new ship can have the given orientation as its orientation. 
	 * 			| canHaveAsOrientation(orientation)
	 *      
	 * @post 	The orientation of this new ship is equal to the given orientation.
	 *       	| new.getOrientation() == orientation
	 *       
	 * @effect 	The radius of this new ship is set to the given radius. 
	 * 			| this.setRadius(radius)
	 * 
	 * @throws	IllegalArgumentException
	 * 			...
	 * 
	 * 
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) 
			throws IllegalArgumentException {
		this.setx(x);
		this.sety(y);
		this.setxVelocity(xVelocity);
		this.setyVelocity(yVelocity);
		this.setRadius(radius);
		this.setOrientation(orientation);
	}
	
	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction. 
	 * 
	 * @post  	The position of this new ship is (0,0).
	 *        	| new.getPosition() == {0,0}
	 *        
	 * @post  	The velocity of this new ship is (0,0).
	 *        	| new.getVelocity() == {0,0}
	 *        
	 * @post  	The radius of this new ship is MIN_RADIUS.
	 *        	| new.getRadius() == MIN_RADIUS
	 *        
	 * @post  	The orientation of this new ship is 0.
	 *        	| new.getOrientation() == 0
	 *        
	 * @effect 	The result is a circle with radius MIN_RADIUS 
	 * 			centered on (0, 0) facing right and with speed zero.
	 */
	public Ship() {
		this(0,0,0,0,MIN_RADIUS,0);
	}

		
	/**
	 * Set the orientation of this ship.
	 * 
	 * @param 	orientation
	 *          The new orientation for this ship in radians.
	 *          
	 * @post 	The orientation of this new ship is equal to the given orientation. 
	 * 			| new.getOrientation() == orientation        
	 * 
	 */
	@Basic
	@Raw
	@Immutable
	public void setOrientation(double orientation) {
		assert this.canHaveAsOrientation(orientation);
		if (orientation == null);
			this.orientation = 0;
			//default
		this.orientation = orientation;
		
		// Final wegdoen zodat this.orientation ok is?
		// een double kan geen null zijn?
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
	 * @return 	True if and only if the orientation angle is positive and smaller then 2*PI.
	 * 			| result == 
	 * 				0 <= orientation && orientation < 2*Math.PI
	 */
	@Raw
	public boolean canHaveAsOrientation(double orientation) {
		if (this == null)
			return false;
		if (0 <= orientation && orientation < 2*Math.PI)
			return true;
		return false;
		
		// Ook aanpasbaar uitwerken (met MIN_ en MAX_ORIENTATON) of niet nodig?
	}

	/**
	 * Variable registering the orientation of this ship.
	 */
	private final double orientation;
	


	/**
	 * Return the radius of this ship.
	 */
	@Basic
	@Raw
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Check whether the given radius is a valid radius for any ship.
	 * 
	 * @param 	radius
	 *          The radius to check.
	 * @return 	Returns true if and only if the radius is bigger then the
	 * 			minimum radius.
	 * 			| result == radius >= MIN_RADIUS
	 */
	public static boolean isValidRadius(double radius) {
		return radius >= MIN_RADIUS;
	}

	/**
	 * Set the radius of this ship to the given radius.
	 * 
	 * @param 	radius
	 *          The new radius for this ship.
	 *          
	 * @post 	The radius of this new ship is equal to the given radius. 
	 * 			| new.getRadius() == radius
	 * 
	 * @throws 	IllegalRadiusException
	 *          The given radius is not a valid radius for any ship. 
	 *          | ! isValidRadius(getRadius())
	 */
	@Raw
	public void setRadius(double radius) throws IllegalArgumentException {
		if (!isValidRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
		// IllegalOrientationException ok? Vormt IFacade dit om tot ModelException?
	}

	/**
	 * Variable registering the radius of this ship.
	 */
	private double radius;
	
	private static final double MIN_RADIUS = 10;
	
	
	/**
	 * Change the ship’s velocity based on the current velocity v, 
	 * its orientation thetha, and on a given amount a. 
	 * 
	 * @param 	a
	 * 			Given factor to accelerate this ship.
	 * 
	 * @post	a is larger then 0.
	 * 			| canHaveAsA(a)
	 * 
	 * @post	The new velocity is slower then or equals the speed of light.
	 * 			| new velocity <= SPEED_OF_LIGHT
	 * 
	 * @post	The new velocity is equal to the calculated velocity, or when 
	 * 			it exceeds the speed of light, it's reduced to the speed of light.
	 * 			|
	 * 
	 */
	public void thrust(double a) {
		
		if (!canHaveAsA(a)){
			a = 0;
			return;
		}
		
		double[] velocity = this.getVelocity();
		double orientation = this.getOrientation();
			
		double new_xVelocity = velocity[0] + a*Math.cos(orientation);
		double new_yVelocity = velocity[1] + a*Math.sin(orientation);
			
		setVelocity(new double[] {new_xVelocity,new_yVelocity});
		// Juiste syntax voor attributen?
		
		if (getSpeed(this) > SPEED_OF_LIGHT){
			// Aangenomen dat 'this' op dit moment al is aangepast naar nieuwe snelheid.
			...
			// Uitdenken hoe je snelheid gelijk kan stellen aan c maar toch richting 
			// kan behouden 
		}
	}	
	
	
	/**
	 * ...
	 * 
	 * @param 	a
	 * 			...
	 * 
	 * @return	...
	 * 			...
	 */
	public boolean canHaveAsA(double a){
		return (a>0 || Double.isNaN(a));
	}
	
	/**
	 * ...
	 * 
	 * @param 	ship
	 * 			...
	 * 
	 * @return	...
	 * 			...
	 */
	public double getSpeed(Ship ship) {
		double[] velocity = ship.getVelocity();
		double orientation = ship.getOrientation();
		double new_xVelocity = velocity[0] + a*Math.cos(orientation);
		double new_yVelocity = velocity[1] + a*Math.sin(orientation);
		double speed = Math.sqrt(Math.pow(new_xVelocity,2) + Math.pow(new_yVelocity,2));
		return speed;
	}
		
	public static double SPEED_OF_LIGHT = 300000;
	
	// Nadenken over private/public functies (en variabelen)
	// Nadenken over default waardes, kan null als waarde aangenomen worden?
	// -> verwerk in andere functies die setten en getten.

}
