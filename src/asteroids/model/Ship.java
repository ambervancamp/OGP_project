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
	 * @post 	The radius of this new ship is equal to the given radius. 
	 * 			| new.getRadius() == radius
	 * 
	 * @throws 	IllegalRadiusException
	 *          The given radius is not a valid radius for any ship. 
	 *          | ! isValidRadius(getRadius()) 
	 * 
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
				throws IllegalArgumentException {
		this.setPosition(x, y);
		this.setVelocity(xVelocity,yVelocity);
		if (!canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
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
		this(0,0,0,0,getMinRadius(),0);
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
	private void setOrientation(double orientation) {
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
	public static boolean canHaveAsOrientation(double orientation) {
		if (Double.isNaN(orientation))
			return false;
		if (0 <= orientation && orientation < 2*Math.PI)
			return true;
		return false;
	}

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
	public static boolean canHaveAsRadius(double radius) {
		return (radius >= getMinRadius() && !Double.isNaN(radius));
	}


	/**
	 * Return the lowest possible value for the radius of all ships.
	 *
	 * @return 	The lowest possible value for the radius of all ships is 10 km.
	 *		 	| result == 10
	 */
	@Basic
	private static int getMinRadius() {
		return 10;
	}	
	
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
	 * 			| new velocity <= getMaxSpeed()
	 * 
	 * @post	The new velocity is equal to the calculated velocity, or when 
	 * 			it exceeds the speed of light, it's reduced to the speed of light.
	 * 			|
	 * 
	 */
	public void thrust(double a) {
		if (this != null){
			
			if (!canHaveAsA(a)){
				a = 0;
				return;
			}
			
			double [] velocity = {getxVelocity(),getyVelocity()};
			double orientation = this.getOrientation();
				
			double xVelocity = velocity[0] + a*Math.cos(orientation);
			double yVelocity = velocity[1] + a*Math.sin(orientation);
				
			this.setVelocity(xVelocity, yVelocity);
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
	public double getDistanceBetween(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		
		double new_x = this.getxPosition() - other.getxPosition();
		double new_y = this.getyPosition() - other.getyPosition();
		double distance_between_centers = Math.sqrt(Math.pow(new_x, 2) + Math.pow(new_y, 2));
		double distance = distance_between_centers - this.getRadius() - other.getRadius();
		
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
		if (ship == null || this == null)
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
	private double getxPosition(){
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
	private double getyPosition(){
		return this.yPosition;
	}
	
	/**
	 * Calculate the x- and y-coordinate of the position of this ship.
	 * 
	 * @return
	 */
	public double[] getPosition(){
		double[] position = {this.getxPosition(), this.getyPosition()};
		return position;
	}
	
	/**
	 * Set the x-position and y-position of this ship to the given value.
	 * 
	 * @param 	xPosition
	 * 			Given x-position.
	 * 
	 * @param 	Position
	 * 			Given y-position.
	 * 
	 * @post	the new x-position will be equal to the given x-position
	 * 			|new.getxPosition() == xPosition
	 * 
	 * @post	the new y-position will be equal to the given y-position
	 * 			|new.getyPosition() == yPosition
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given yPosition and xPosition can't be less than zero.
	 * 			| !canHaveAsxPosition(xPosition) || !canHaveAsyPosition(yPosition)
	 * 
	 */
	public void setPosition(double xPosition, double yPosition) throws IllegalArgumentException {
		if (!canHaveAsxPosition(xPosition) || !canHaveAsyPosition(yPosition))
			throw new IllegalArgumentException();
		
		this.xPosition = xPosition;
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
	public static boolean canHaveAsxPosition(double xPosition){
		if (Double.isNaN(xPosition) || xPosition<0)
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
	public static boolean canHaveAsyPosition(double yPosition){
		if (Double.isNaN(yPosition) || yPosition<0)
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
	 * 			| if getSpeed() > getMaxSpeed()
	 * 			setPosition = getVelocity*getMaxSpeed()/speed
	 */
	public void setVelocity(double xVelocity,double yVelocity){
		if (!canHaveAsxVelocity(xVelocity))
			xVelocity = 0;
		if (!canHaveAsyVelocity(yVelocity))
			yVelocity = 0;
		if (!canHaveAsSpeed(this.getSpeed())){
			this.xVelocity = xVelocity*getMaxSpeed()/(this.getSpeed());
			this.yVelocity = yVelocity*getMaxSpeed()/(this.getSpeed());
		}
		if (canHaveAsSpeed(this.getSpeed())){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;
		}
	}
	
	public static boolean canHaveAsxVelocity(double xVelocity){
		if (Double.isNaN(xVelocity))
			return false;
		return true;
	}
	
	public static boolean canHaveAsyVelocity(double yVelocity){
		if (Double.isNaN(yVelocity))
			return false;
		return true;
	}
	
	/**
	 * a method that gives the velocity of the given ship in x-direction
	 * the velocity is defined in kilometer/s
	 * 
	 * @return
	 * 			returns the xVelocity of the ship in kilometer/s
	 */
	private double getxVelocity(){
		return this.xVelocity;
	}
	
	/**
	 * a method that gives the velocity of the given ship in y-direction
	 * the velocity is defined in kilometer/s
	 * 
	 * @return
	 * 			returns the yVelocity of the ship in kilometer/s
	 */
	private double getyVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * checks whether the speed is a possible speed
	 * 
	 * @param 	speed
	 * 			the speed the ship has at a given moment
	 * @return 	true if and only if the speed is less or equal to the maximum speed=the speed of light
	 * 			| result == speed > getMaxSpeed()
	 */
	public static boolean canHaveAsSpeed(double speed){
		if (speed > getMaxSpeed() || Double.isNaN(speed) || speed < 0)
			return false;
		return true;
	}
	
	/**
	 * A method that calculates the total speed of a ship
	 * 
	 * @effect 	calculates the root of the sum of squares of the x- and y-velocity
	 * 			|Math.sqrt(Math.pow(this.getxVelocity(),2)+Math.pow(this.getyVelocity(),2))
	 */
	private double  getSpeed(){
		double speed = Math.sqrt(Math.pow(this.getxVelocity(),2)+Math.pow(this.getyVelocity(),2));
		return speed;
	}
	
	/**
	 * Calculate the x- and y-coordinate of the velocity of this ship.
	 * 
	 * @return
	 */
	public double[] getVelocity(){
		double[] velocity = {this.getxVelocity(), this.getyVelocity()};
		return velocity;
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
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
	}
	
	/**
	 * @param 	duration
	 * 			the duration of the movement			
	 * @return the new position after moving in a list of two elements.
	 */	
	public double [] getPositionAfterMoving(double duration){
			return new double[] {getPosition()[0]+getVelocity()[0]*duration,
			getPosition()[0]+getVelocity()[0]*duration};	
	}
	/**
	 * checks whether the duration of the movement is a possible duration
	 * 
	 * @param 	duration
	 * 			the duration of the movement
	 * @return	true if and only if the duration is greater than or equal to zero
	 * 			| result == duration >= 0
	 */
	public static boolean canHaveAsDuration(double duration){
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
	
	/**
	 * Return the highest possible value for the speed of all ships.
	 *
	 * @return 	The highest possible value for the speed of all ships is the speed
	 * 			of light = 30000 km/s.
	 *		 	| result == 30000
	 */
	@Basic
	private static int getMaxSpeed() {
		return 30000;
	}
	
	
	
	// DECLARATIE VARIABELEN
	
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	
	/**
	 * Variable registering the xPosition of this ship.
	 */
	private double xPosition;
	
	/**
	 * Variable registering the yPosition of this ship.
	 */
	private double yPosition;
	
	/**
	 * Variable registering the xVelocity of this ship.
	 */
	private double xVelocity;
	
	/**
	 * Variable registering the yVelocity of this ship.
	 */
	private double yVelocity;
	
	/**
	 * A method that gives the time between a collision of 2 ships
	 * 
	 * @param 	other
	 * 			a second ship with which you want to see if this ship collides
	 * @post	the time to collision will be positive infinity if the ships are moving away from each other
	 * 			|if this.getDeltaDistanceVelocity(other) >= 0
	 * 				timeToCollision = Double.POSITIVE_INFINITY
	 * @post 	the time to collision will be positive infinity if the ships are never separetad 	
	 * 			by less than the sum of there radii
	 * 			|if this.getD <= 0
	 * 				timeToCollision = Double.POSITIVE_INFINITY
	 * @post	the time to collision will have a value if the previous conditions where false
	 * 			|timeToCollision =  -(getDeltaDistanceVelocity(other)+
	 * 								Math.sqrt(getD(other)))/getDeltaPowVelocity(other)
	 * @throws 	NullPointerException
	 * 			the method will throw a nullpointerexception if one of the 2 ships doesn't exist
	 * 			| other == null || this == null
	 */
	public double getTimeToCollision(Ship other) throws NullPointerException{
		double timeToCollision;
		if (this.getDeltaDistanceVelocity(other) >= 0)
			timeToCollision = Double.POSITIVE_INFINITY;
		if (other == null || this == null)
			throw new NullPointerException();
		if (getD(other) <= 0)
			timeToCollision = Double.POSITIVE_INFINITY;
		else
			timeToCollision = -(getDeltaDistanceVelocity(other)+Math.sqrt(getD(other)))/getDeltaPowVelocity(other);
			// d will be negatif if the ships overlap
		return timeToCollision;
	}
	
	/**
	 * a method that calculates the distance in x- and y-direction between the centra of the ships 
	 * 
	 * @param 	other
	 * 			a second ship with which you want to calculate the difference with
	 * @post 	the deltaDistance will be a list of the difference between the 2 centra of the ship
	 * 			|deltaDistance =  {other.getxPosition()-this.getxPosition(),
				other.getyPosition()-this.getyPosition()}
	 * @throws 	NullPointerException
	 * 			the method will throw a nullpointerexception if one of the 2 ships doesn't exist
	 * 			| other == null || this == null
	 */
	private double [] getDeltaDistance(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double [] deltaDistance = {other.getxPosition()-this.getxPosition(),
				other.getyPosition()-this.getyPosition()};
		return deltaDistance;
	}
	
	/**
	 * a method that calculates the difference in velocity in x- and y-direction between two ships 
	 * 
	 * @param 	other
	 * 			a second ship with which you want to calculate the difference with
	 * @post 	the deltaVelocity will be a list of the difference in velocitybetween the 2 ships
	 * 			| deltaVelocity = {other.getxVelocity()-this.getxVelocity(),
				other.getyVelocity()-this.getyVelocity()};
	 * @throws 	NullPointerException
	 * 			the method will throw a nullpointerexception if one of the 2 ships doesn't exist
	 * 			| other == null || this == null
	 */	
	private double [] getDeltaVelocity(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double [] deltaVelocity = {other.getxVelocity()-this.getxVelocity(),
				other.getyVelocity()-this.getyVelocity()};
		return deltaVelocity;
	}
	
	/**
	 * a method that calculates the square of the difference in position between 
	 * the centres of the two ships 
	 * 
	 * @param 	other
	 * 			a second ship with which you want to calculate the difference with
	 * @post	the deltaPowDistance will be the sum of the squares of the x- and y- in 
	 * 			distance between the centra of the ships
	 * 			| deltaPowDistance = Math.pow(getDeltaDistance(other)[0],2)+
					Math.pow(getDeltaDistance(other)[1],2);
	 * @throws 	NullPointerException
	 * 			the method will throw a nullpointerexception if one of the 2 ships doesn't exist
	 * 			| other == null || this == null
	 */		
	private double getDeltaPowDistance(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double deltaPowDistance = Math.pow(this.getDeltaDistance(other)[0],2)+
					Math.pow(this.getDeltaDistance(other)[1],2);
		return deltaPowDistance;
	}

	/**
	 * a method that calculates the square of the difference in velocity between two ships 
	 * 
	 * @param 	other
	 * 			a second ship with which you want to calculate the difference with
	 * @post	the deltaPowVelocity will be the sum of the squares of the x- and y- difference in 
	 * 			velocity between the ships
	 * 			| double deltaPowVelocity = Math.pow(getDeltaVelocity(other)[0], 2)+
					Math.pow(getDeltaVelocity(other)[1], 2);
	 * @throws 	NullPointerException
	 * 			the method will throw a nullpointerexception if one of the 2 ships doesn't exist
	 * 			| other == null || this == null
	 */		
	private double getDeltaPowVelocity(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double deltaPowVelocity = Math.pow(this.getDeltaVelocity(other)[0], 2)+
					Math.pow(this.getDeltaVelocity(other)[1], 2);
		return deltaPowVelocity;
	}
	
	/**
	 * a method that calculates the scalarProduct of the difference in position and velocity between two ships 
	 * 
	 * @param 	other
	 * 			a second ship with which you want to calculate the difference with
	 * @post 	the deltaDistanceVelocity will be the sum of the product of x- and y-distance and
	 * 			difference in velocity of the two ships
	 * 			| deltaDistanceVelocity = (getDeltaVelocity(other)[0]*getDeltaDistance(other)[0])+
					(getDeltaVelocity(other)[1]*getDeltaDistance(other)[1]);
	 * @throws 	NullPointerException
	 * 			the method will throw a nullpointerexception if one of the 2 ships doesn't exist
	 * 			| other == null || this == null
	 */			
	private double getDeltaDistanceVelocity(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double deltaDistanceVelocity = (this.getDeltaVelocity(other)[0]*this.getDeltaDistance(other)[0])+
						(this.getDeltaVelocity(other)[1]*this.getDeltaDistance(other)[1]);
		return deltaDistanceVelocity;
	}
	/**
	 * a method that calculates d between two ships 
	 * 
	 * @param 	other
	 * 			a second ship with which you want to calculate d with
	 * @post 	the d will be thee square of the deltaDistanceVelocitylowered by the product of 
	 * 			the deltaPowVelocity with the difference between deltaPowVelocity and 
	 * 			the distance between the two ships
	 * 			| d = Math.pow(getDeltaDistanceVelocity(other),2)-
					(getDeltaPowVelocity(other))*(getDeltaPowDistance(other)-this.getDistanceBetween());
	 * @throws 	NullPointerException
	 * 			the method will throw a nullpointerexception if one of the 2 ships doesn't exist
	 * 			| other == null || this == null
	 */		
	private double getD(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double d = Math.pow(this.getDeltaDistanceVelocity(other),2)-
					(this.getDeltaPowVelocity(other))*(this.getDeltaPowDistance(other)
													-Math.pow((this.getRadius()+other.getRadius()),2));
		return d;
	}
	/**
	 * 
	 * @param 	other
	 * 			a second ship with which you want to calculate the collision position with
	 * @post 	the collisionPoint will be the point where the two ships hit 
	 * 			| collisionPoint = this.getPositionAfterMoving(time)+
									this.getRadius()*getDeltaDistance(other)/getD(other),
	 * @throws 	NullPointerException
	 * 			the method will throw a nullpointerexception if one of the 2 ships doesn't exist
	 * 			| other == null || this == null
	 * the method will try to calculate the hitting point, if they neve hit each other, 
	 * we will catch it with an IllegalArgumentException
	 */
	public double [] getCollisionPosition(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		if (this.getTimeToCollision(other) == Double.POSITIVE_INFINITY)
			throw new IllegalArgumentException();
		try{
			double time = this.getTimeToCollision(other);
		double dt = this.getTimeToCollision(other);
		double [] newDeltaDistance = 	{other.getPositionAfterMoving(dt)[0]-this.getPositionAfterMoving(dt)[0],
										other.getPositionAfterMoving(dt)[1]-this.getPositionAfterMoving(dt)[1]}; 
		double [] collisionPoint = {this.getPositionAfterMoving(time)[0]+
									this.getRadius()*newDeltaDistance[0]/
									(this.getRadius()+other.getRadius()),
									this.getPositionAfterMoving(time)[1]+
									this.getRadius()*newDeltaDistance[1]/
									(this.getRadius()+other.getRadius())};
		// position of hit = 	position of ship after moving till contact+
		//						(difference in centra qua x-and y direction*
		// 						radius of the first schip/distance two centra)
		return collisionPoint;
		} catch (IllegalArgumentException noCollision) {
			return null;
		}
	}
}
