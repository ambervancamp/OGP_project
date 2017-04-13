package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class to represent spaceships as a special kind of round entities.
 * In addition to a position, velocity, radius, density and mass, ships also have an orientation.
 *  
 * @invar  	Each ship can have its orientation as orientation.
 *        	| canHaveAsOrientation(this.getOrientation())
 * 
 * @invar  	The mass of each ship must be a valid mass for any
 *         	ship.
 *       	| canHaveAsMass(getMass())
 * 
 * @invar  	Each ship can have its density as density .
 *       	| canHaveAsDensity(this.getDensity())
 *        
 * @author Amber_000 && Jasper_000
 */

public class Ship extends RoundEntity {
	/**
	 * Initialize this new ship with the given parameters.
	 * 
	 * @param	x
	 * 			The x-coordinate of the position of this new ship, in kilometer.
	 * 
	 * @param	y
	 * 			The y-coordinate of the position of this new ship, in kilometer.
	 * 
	 * @param 	xVelocity
	 *			The x-coordinate velocity for this new ship, in kilometer/second.
	 *
	 * @param 	yVelocity
	 * 			The y-coordinate velocity for this new ship, in kilometer/second.
	 * 
	 * @param	radius
	 * 			The radius of this new ship, in kilometer. 
	 * 
	 * @param	orientation
	 * 			The orientation of this new ship, in radians. The orientation of a ship 
	 * 			facing right is 0, a ship facing up is at angle Math.PI/2, a ship facing left 
	 * 			is at angle Math.PI and a ship facing down is at angle 3*Math.PI/2. 
	 * 
	 * @param	mass
	 * 			The given mass of this new ship, in kg.
	 * 
	 * @pre		The given orientation must be a valid orientation for this new ship.
     *       	| canHaveAsOrientation(orientation)
     * 
     * @post   	If the calculated density, based on parameter mass, is a valid density for any ship,
	 *         	the density of this new ship is equal to the given
	 *         	density. Otherwise, the density of this new ship is equal to MIN_DENSITY.
	 *       	| if (isValidDensity(density))
	 *       	|   then new.getDensity() == density
	 *       	|   else new.getDensity() == MIN_DENSITY
     * 
     * @post   	If the given mass is a valid mass for any ship, the mass of this new 
	 * 			ship is equal to the given mass. Otherwise, the mass of this new ship is equal
	 *         	to 5.948*Math.pow(10,15).
	 *      	| if (canHaveAsMass(mass))
	 *       	|   then new.getMass() == mass
	 *       	|   else new.getMass() == 5.948*Math.pow(10,15)
     * 
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, 
			double mass) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.setOrientation(orientation);
		
		double density = mass/(4/3*Math.PI*Math.pow(this.getRadius(),3));
		if (!canHaveAsDensity(density))
			this.density = MIN_DENSITY;
		else
			this.density = density;
		
		if (!canHaveAsMass(mass))
			mass = 5.948*Math.pow(10,15);
		this.setMass(mass);
		// No 'else' needed, setMass sets the new defined mass.
		
		UnboundSpace unboundspace = UnboundSpace(width, height);
		this.PlaceInSpace(unboundspace);
		// Ships need to be associated with an unbound space until associated with a world.
		// Wait for definition of uboundspace constructor for width and height.
	}
	
	/**
	 * Create a new ship with a default position, velocity, radius,
	 * orientation and mass.
	 * 
	 * @post  	The position of this new ship is (0,0).
	 *        	| new.getPosition() == {0,0}
	 *        
	 * @post  	The velocity of this new ship is (0,0).
	 *        	| new.getVelocity() == {0,0}
	 *        
	 * @post  	The radius of this new ship is getMinRadius.
	 *        	| new.getRadius() == getMinRadius
	 *        
	 * @post  	This new ship is facing right.
	 *        	| new.getOrientation() == 0
	 *        
	 * @post	The mass of this new ship is derived from the minimum density
	 * 			and current radius, this is the minimum radius. So the default mass
	 * 			is 5,948*10^15.
	 * 			| new.getMass() == 5,948*10^15
	 *        
	 * @effect 	The result is a circle with radius getMinRadius() 
	 * 			centered on (0, 0) facing right and with speed zero.
	 */
	public Ship() {
		this(0,0,0,0,getMinRadius(),0,5.948*Math.pow(10,15));
	}
		
	/**
	 * Set the orientation of this ship.
	 * 
	 * @param 	orientation
	 *          The new orientation for this ship in radians.
	 *          
	 * @pre		The given orientation must be a valid orientation for this new ship.
     *       	| canHaveAsOrientation(orientation)       
	 * 
	 */
	@Basic
	@Raw
	@Immutable
	private void setOrientation(double orientation) {
		assert canHaveAsOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * Return the orientation of this ship.
	 * 
	 * @return	Returns the current orientation of this ship.
	 * 			| result == this.orientation
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
	 *          
	 * @return 	True if and only if the orientation angle is a number,
	 * 			positive and smaller than 2*PI.
	 * 			| result == 
	 * 				0 <= orientation && orientation < 2*Math.PI && !Double.isNaN(orientation)
	 */
	@Raw
	public static boolean canHaveAsOrientation(double orientation) {
		if (!Double.isNaN(orientation) && 0 <= orientation && orientation < 2*Math.PI)
			return true;
		return false;
	}
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;

//	/**
//	 * Return the radius of this ship.
//	 */
//	@Override
//	@Basic
//	@Raw
//	@Immutable
//	public double getRadius() {
//		return this.radius;
//	}
//
//	/**
//	 * Check whether the given radius is a valid radius for any ship.
//	 * 
//	 * @param 	radius
//	 *          The radius to check.
//	 *          
//	 * @return 	Returns true if and only if the radius is a number bigger 
//	 * 			than or equal to the minimum radius.
//	 * 			| result == (radius >= getMinRadius() && !Double.isNaN(radius))
//	 */
//	@Raw
//	public static boolean canHaveAsRadius(double radius) {
//		return (!Double.isNaN(radius) && radius >= getMinRadius());
//	}


	/**
	 * Return the lowest possible value for the radius of all ships.
	 *
	 * @return 	The lowest possible value for the radius of all ships is 10 kilometer.
	 *		 	| result == 10
	 */
	@Override
	@Basic
	@Raw
	public static double getMinRadius() {
		return 10;
	}

	/**
	 * Return the mass of this ship. This is the mass of the ship itself, 
	 * but also the bullets it is carrying.
	 * 
	 * @return 	The current mass of this ship.
	 * 			| result == this.mass
	 */
	@Override
	@Basic 
	@Raw
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Return the mass of this ship itself, without any bullets if any.
	 * 
	 * @return 	The mass of this ship.
	 * 			| result == 4/3*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity()
	 */
	@Basic 
	@Raw
	public double getShipMass() {
		return 4/3*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
	}
	
	/**
	 * Check whether the given mass is a valid mass for any ship.
	 *  
	 * @param  	mass
	 *         	The mass to check.
	 *         
	 * @return 	The given mass must be a number smaller then Double.MAX_VALUE
	 * 			and bigger then or equal to the mass of only the ship itself (not the bullets).
	 *       	| result == !Double.isNaN(mass) 
	 *       	|	&& mass >= 4/3*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity()
	 *			|	&& mass < Double.MAX_VALUE
	 */
	public boolean canHaveAsMass(double mass) {
		return !Double.isNaN(mass) && mass >= 4/3*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity()
				&& mass < Double.MAX_VALUE;
	}
	//Canhaveasmass hangt af van radius, dit verschilt van schip to schip dus is bijgevolg
	//geen static functie.
	
	
	/**
	 * Set the mass of this ship to the given mass.
	 * 
	 * @param  	mass
	 *         	The new mass for this ship.
	 *         
	 * @post   	If the given mass is a valid mass for any ship,
	 *         	the mass of this new ship is equal to the given mass.
	 * 			| if (isValidMass(mass))
	 *       	|   then new.getMass() == mass
	 */
	@Raw
	public void setMass(double mass) {
		if (canHaveAsMass(mass))
			this.mass = mass;
	}
	
	/**
	 * Variable registering the mass of this ship.
	 */
	private double mass;

	/**
	 * Return the density of this ship.
	 */
	@Override
	@Basic 
	@Raw 
	@Immutable
	public double getDensity() {
		return this.density;
	}
	
	/**
	 * Check whether this ship can have the given density as its density.
	 *  
	 * @param  density
	 *         The density to check.
	 * @return 
	 *       | result == 
	*/
	@Raw
	public boolean canHaveAsDensity(double density) {
		return !Double.isNaN(density) && density >= MIN_DENSITY;
	}
	
	/**
	 * Variable registering the density of this ship.
	 */
	private final double density;

	/**
	 * Variable registering the minimum density of all ships.
	 */
	private final double MIN_DENSITY = 1.42*Math.pow(10,12);

	
	/**
	 * Change the position of the ship based on the current position, 
	 * velocity and a given time duration. 
	 * 
	 * @param 	duration
	 * 			The duration of the movement.
	 * 
	 * @effect 	The position of the ship will be changed to the 
	 * 			new position after the given time, speed and direction
	 * 			| setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1])
	 */
	@Raw
	public void move(double duration) throws IllegalArgumentException{
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
	}
	
	/**
	 * A method that turns the ship according to the given angle.
	 * 
	 * @param 	angle
	 * 			The angle to turn.
	 * 
	 * @pre 	The ship can have the total angle as a valid angle.
	 * 			| canHaveAsOrientation(angle+getOrientation)
	 * 
	 * @post	The new orientation will be changed to the sum of the previous 
	 * 			and the new angle.
	 * 			|new.getOrientation() == angle+getOrientation()
	 * 			
	 */
	@Raw
	public void turn(double angle){
		assert(canHaveAsOrientation(angle+getOrientation()));
		setOrientation(angle+getOrientation());	
	}
	
	/**
	 * Change the ships velocity based on the current velocity, 
	 * its orientation, and a given amount a. 
	 * 
	 * @param 	a
	 * 			Given factor to accelerate this ship.
	 * 
	 * @post	A must be valid, if not, the ship will not accelerate
	 * 			and keep its previous velocity.
	 * 			| if (!canHaveAsAcceleration(a))
	 *				then new.getVelocity = this.getVelocity;
	 *
	 * @post 	The ship will accelerate with a factor a and keep its orientation.
	 * 			The new xVelocity and yVelocity are set as the xVelocity and yVelocity
	 * 			of this new ship.
	 * 			| setVelocity(this.getxVelocity() + a*Math.cos(this.getOrientation()), 
	 * 			|						this.getyVelocity() + a*Math.sin(this.getOrientation()))
	 *
	 */
	@Raw
	@Immutable
	public void thrust(double a) {
		if (this != null){
			
			if (!canHaveAsAcceleration(a)){
				return;
			}
				
			double xVelocity = this.getxVelocity() + a*Math.cos(this.getOrientation());
			double yVelocity = this.getyVelocity() + a*Math.sin(this.getOrientation());
				
			this.setVelocity(xVelocity, yVelocity);
		}
	}	
	
	
	/**
	 * Check whether the given factor a is a valid acceleration factor a for any ship.
	 * 
	 * @param 	a
	 * 			Factor that determines how much to accelerate a ship.
	 * 
	 * @return	Returns true if and only if a is a number bigger 
	 * 			than or equal to 0.
	 * 			| result == (a>=0 && !Double.isNaN(a)))
	 */
	@Raw
	public static boolean canHaveAsAcceleration(double a){
		return (!Double.isNaN(a) && a>=0);
	}
		
}
