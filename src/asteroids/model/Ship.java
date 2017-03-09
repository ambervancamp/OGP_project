package asteroids.model;
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
 * @author Amber_000 && Jasper_000
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
	 * 
	 * 
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
				throws IllegalArgumentException {
		this.setxPosition(x);
		this.setyPosition(y);
		this.setShipVelocity(xVelocity,yVelocity);
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
		
		this.orientation = orientation;
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
	 * @return 	True if and only if the orientation angle is positive and smaller than 2*PI.
	 * 			| result == 
	 * 				0 <= orientation && orientation < 2*Math.PI
	 */
	@Raw
	private boolean canHaveAsOrientation(double orientation) {
		if (Double.isNaN(orientation))
			return false;
		if (0 <= orientation && orientation < 2*Math.PI)
			return true;
		return false;
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
	 * The radius is given in kilometer.
	 * 
	 * @param 	radius
	 *          The radius to check.
	 * @return 	Returns true if and only if the radius is bigger than the
	 * 			minimum radius.
	 * 			| result == radius >= MIN_RADIUS
	 */
	private static boolean canHaveAsRadius(double radius) {
		return (radius > MIN_RADIUS && !Double.isNaN(radius));
	}

	/**
	 * Set the radius of this ship to the given radius.
	 * the radius is given in kilometer.
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
		if (!canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
	}

	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	
	/**
	 * Variable registering the minimum radius of all ships.
	 */
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
	 * @post	The new velocity is slower than or equals the speed of light.
	 * 			| new velocity <= maxSpeed
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
		
		double [] velocity = {getxVelocity(),getyVelocity()};
		double orientation = this.getOrientation();
			
		double new_xVelocity = velocity[0] + a*Math.cos(orientation);
		double new_yVelocity = velocity[1] + a*Math.sin(orientation);
			
		this.setShipVelocity(new_xVelocity, new_yVelocity);
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
	private boolean canHaveAsA(double a){
		return (a>0 && !Double.isNaN(a));
	}
	
	
	/**
	 * 
	 * Return the distance between the given ship and this ship.
	 * 
	 * @param	ship
	 * 			The given ship.
	 * 
	 * @throws	NullPointerException()
	 * 			The given ship must exist.
	 * 			| ship == null
	 * 
	 * 
	 */
	public double getDistanceBetween(Ship ship) throws NullPointerException{
		if (ship == null)
			throw new NullPointerException();
		
		double new_x = this.getxPosition() - ship.getxPosition();
		double new_y = this.getyPosition() - ship.getyPosition();
		double distance_between_centers = Math.sqrt(Math.pow(new_x, 2) + Math.pow(new_y, 2));
		double distance = distance_between_centers - this.getRadius() - ship.getRadius();
		
		if (distance == 2*this.getRadius())
			return 0;
		
		return distance;	
	}
	
	/**
	 * Check if the given ship overlaps this ship.
	 * 
	 * @param 	ship
	 * 			The given ship.
	 * 
	 * @throws 	NullPointerException()
	 * 			The given ship must exist.
	 * 			| ship == null
	 */
	public boolean overlap(Ship ship) throws NullPointerException {
		if (ship == null)
			throw new NullPointerException();
		
		if (this.getDistanceBetween(ship) <= 0)
			return true;
		
		return false;
	}
	
	
	// ANDER IEMAND
		
	
	/**
	 * a method that gives the x coordinate of the ship
	 * The position is defined in kilometer.
	 * 
	 * @return 
	 * 			the method returns the x-position of the ship
	 * 			| result == this.xPosition
	 */
	public double getxPosition(){
		return this.xPosition;
	}
	
	/**
	 * a method that gives the y coordinate of the ship
	 * the position is defined in kilometer
	 * 
	 * @return 
	 * 			the method returns the y-position of the ship
	 * 			| result == this.xPosition
	 * 
	 */
	public double getyPosition(){
		return this.yPosition;
	}
	
	/**
	 * this method sets the x-position of the ship to the given value
	 * 
	 * @post	the new x-position will be equal to the given x-position
	 * 			|new.getxPosition() == xPosition
	 * @param 	xPosition
	 * 			| the position of the ship in x coordinates
	 * 
	 * @throws 	IllegalArgumetnException
	 * 			the given yPosition can't be less than zero, 
	 * 			| xPosition < 0
	 */
	public void setxPosition(double xPosition) throws IllegalArgumentException{
		if (!canHaveAsxPosition(xPosition))
			throw new IllegalArgumentException();
		this.xPosition = xPosition;
	}
	
	/**
	 * this method sets the y-position of the ship to the given value
	 * 
	 * @post	the new y-position will be equal to the given y-position
	 * 			|new.getyPosition() == yPosition
	 * @param 	yPosition
	 * 			| the position of the ship in y coordinates
	 * @throws 	IllegalArgumetnException
	 * 			the given yPosition can't be less than zero, 
	 * 			| yPosition < 0
	 */
	public void setyPosition(double yPosition) throws IllegalArgumentException{
		if (!canHaveAsyPosition(yPosition))
			throw new IllegalArgumentException();
		this.yPosition = yPosition;
	}
	
	/**
	 * checks whether the given x-position is within the boundary of the play field
	 *  
	 * @param 	xPosition
	 * 			the given x-position of the ship to check
	 * @return	true if and only if the x-position is greater than or equal to zero
	 * 			|result == (xPosition >= 0)
	 */
	private boolean canHaveAsxPosition(double xPosition){
		if (xPosition < 0 || Double.isNaN(xPosition))
			return false;
		return true;
	}
	
	/**
	 * checks whether the given y-position is within the boundary of the play field
	 *  
	 * @param 	yPosition
	 * 			the given y-position of the ship to check
	 * @return	true if and only if the y-position is greater than or equal to zero
	 * 			|result == (yPosition >= 0)
	 */
	private boolean canHaveAsyPosition(double yPosition){
		if (yPosition < 0 || Double.isNaN(yPosition))
			return false;
		return true;
	}
	
	/**
	 * a method that sets the velocity of the spaceship
	 * 
	 * @param 	xVelocity
	 * 			the velocity of the ship in the x-direction, in km/s
	 * @param	yVelocity
	 * 			the velocity of the ship in the y-direction, in km/s
	 * 
	 * @post 	if the velocity in x-direction is less than 0 or not a number,
	 * 			the velocity well be set at 0
	 * 			| if xVelocity < 0 || Double.isNaN(xVelocity) 
	 * 				yVelocity() = 0 	
	 * @post 	if the velocity in y-direction is less than 0 or not a number,
	 * 			the velocity well be set at 0
	 * 			| if yVelocity() < 0 || Double.isNaN(yVelocity) 
	 * 				yVelocity = 0 	
	 * @post 	if the velocity exceeds the maximum speed, the velocity will be lowered, 
	 * 			in such a way that the direction will stay the same
	 * 			| if getShipSpeed() > maxSpeed
	 * 			setShipPosition = getShipVelocity*maxSpeed/speed
	 */
	public void setShipVelocity(double xVelocity,double yVelocity){
		if (Double.isNaN(xVelocity)||xVelocity < 0)
			xVelocity = 0;
		if (Double.isNaN(yVelocity)||yVelocity < 0)
			yVelocity = 0;
		if (!canHaveAsSpeed(this.getShipSpeed())){
			this.xVelocity = xVelocity*maxSpeed/(this.getShipSpeed());
			this.yVelocity = yVelocity*maxSpeed/(this.getShipSpeed());
		}
		if (canHaveAsSpeed(this.getShipSpeed())){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;
		}
		// eerste if statements canHave dingen van maken
	}
	
	/**
	 * a method that gives the velocity of the given ship in x-direction
	 * the velocity is defined in kilometer/s
	 * 
	 * @return
	 * 			returns the xVelocity of the ship in kilometer/s
	 */
	public double getxVelocity(){
		return this.xVelocity;
	}
	
	/**
	 * a method that gives the velocity of the given ship in y-direction
	 * the velocity is defined in kilometer/s
	 * 
	 * @return
	 * 			returns the yVelocity of the ship in kilometer/s
	 */
	public double getyVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * checks whether the speed is a possible speed
	 * 
	 * @param 	speed
	 * 			the speed the ship has at a given moment
	 * @return 	true if and only if the speed is less or equal to the maximum speed=the speed of light
	 * 			| result == speed>maxSpeed
	 */
	private boolean canHaveAsSpeed(double speed){
		if (speed > maxSpeed)
			return false;
		return true;
	}
	
	/**
	 * a method that calculates the speed, given the x- and y-velocity of a ship
	 * @effect 	calculates the root of the squares of the x- and y-velocity
	 * 			|Math.sqrt(Math.pow(this.getxVelocity(),2)+Math.pow(this.getyVelocity(),2))
	 */
	private double  getShipSpeed(){
		double speed = Math.sqrt(Math.pow(this.getxVelocity(),2)+Math.pow(this.getyVelocity(),2));
		return speed;
	}
	
	/**
	 *
	 * @param 	duration
	 * 			The duration of the movement
	 * @post 	the position of the ship will be changed to the new position after the given time, speed and direction
	 * 			| 
	 * @throws 	IllegalArgumentException
	 * 			the duration is not a valid duration
	 * 			|(!isValidDuration(duration))
	 * 
	 */
	public void move(double duration) throws IllegalArgumentException{
		if (!canHaveAsDuration(duration))
			throw new IllegalArgumentException();
		setxPosition(getPositionAfterMoving(duration)[0]);
		setyPosition(getPositionAfterMoving(duration)[1]);
	}
	
	/**
	 * @param 	duration
	 * 			the duration of the movement			
	 * @return the new position after moving in a list of two elements.
	 */	
	private double [] getPositionAfterMoving(double duration){
			return new double[] {getxPosition()+getxVelocity()*duration,
			getyPosition()+getyVelocity()*duration};	
	}
	/**
	 * checks whether the duration of the movement is a possible duration
	 * 
	 * @param 	duration
	 * 			the duration of the movement
	 * @return	true if and only if the duration is greater than or equal to zero
	 * 			| result == duration >= 0
	 */
	private boolean canHaveAsDuration(double duration){
		if (duration < 0 || Double.isNaN(duration))
			return false;
		return true;
	}
	
	/**
	 * a method that turns the ship around according to the given angle
	 * 
	 * @param 	angle
	 * 			the angle where there need to be turned around
	 * @pre 	the ship can have the extra angle as a possible angle
	 * 			| canHaveAsOrientation(angle+getOrientation)
	 * @post	the new orientation will be changed to the sum of the previous and the new angle
	 * 			|new.getOrientation() == angle+getOrientation()
	 * 			
	 */
	public void turn(double angle){
		assert(canHaveAsOrientation(angle+getOrientation()));
		setOrientation(angle+getOrientation());	
	}
	
	private static double maxSpeed = 300000;
	private double xPosition;
	private double yPosition;
	private double xVelocity;
	private double yVelocity;
}
