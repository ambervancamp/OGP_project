package asteroids.model;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class to represent spaceships, their parameters and possible movements.
 * 
 * @invar  	Each ship can have its x-coordinate as x-coordinate.
 *        	| canHaveAsxPosition(this.getxPosition())
 *        
 * @invar  	Each ship can have its y-coordinate as y-coordinate.
 *        	| canHaveAsyPosition(this.getyPosition())
 *        
 * @invar  	Each ship can have its x-velocity as x-velocity.
 *        	| canHaveAsxVelocity(this.getVelocity()[0])
 *
 * @invar  	Each ship can have its y-velocity as y-velocity.
 *        	| canHaveAsyVelocity(this.getVelocity()[1])
 * 
 * @invar  	Each ship can have its orientation as orientation.
 *        	| canHaveAsOrientation(this.getOrientation())
 *        
 * @invar  	Each ship can have its radius as radius.
 *        	| canHaveAsRadius(this.getRadius())
 *  
 * 
 * @author Amber_000 && Jasper_000
 */

public class Ship {
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
	 * @post	The x-position will be equal to the given x-position.
	 * 			|new.getxPosition() == xPosition
	 * 
	 * @post	The y-position will be equal to the given y-position.
	 * 			|new.getyPosition() == yPosition
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given xPosition or yPosition is not a valid position for any ship.
	 * 			| !canHaveAsxPosition(xPosition) || !canHaveAsyPosition(yPosition)
	 * 
	 * @effect 	The given xVelocity and yVelocity are set as the xVelocity and yVelocity
	 * 			of this new ship.
	 *       	| setVelocity(xVelocity,yVelocity)
	 * 
	 * @post 	If the velocity in x-direction is not accepted, the velocity will 
	 * 			be set at 0
	 * 			| if (!canHaveAsxVelocity(xVelocity)) 
	 * 				then xVelocity() = 0 
	 * 
	 * @post 	If the velocity in y-direction is not accepted, the velocity will 
	 * 			be set at 0
	 * 			| if (!canHaveAsyVelocity(yVelocity))
	 * 				then yVelocity = 0 	
	 * 
	 * @post 	If the speed exceeds the maximum speed, the speed will be lowered, 
	 * 			in such a way that the direction will stay the same.
	 * 			| if getSpeed() > getMaxSpeed()
	 * 				then setPosition = getVelocity*getMaxSpeed()/speed   
	 * 
	 * @post 	The highest possible value for the speed of this ship is getMaxSpeed().
	 *		 	| new.getMaxSpeed() == 30000
	 *       
	 * @post 	The radius of this new ship is equal to the given radius. 
	 * 			| new.getRadius() == radius
	 * 
	 * @throws 	IllegalArgumentException
	 *          The given radius is not a valid radius for any ship. 
	 *          | ! canHaveAsRadius(getRadius())
	 * 
	 * @post  	The lowest possible value for the radius of this ship is getMinRadius().
	 * 			| new.getMinRadius() == 10
	 * 
	 * @pre		The given orientation must be a valid orientation for this new ship.
     *       	| canHaveAsOrientation(orientation)
     *       
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
	 * orientation. 
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
	 * @effect 	The result is a circle with radius getMinRadius() 
	 * 			centered on (0, 0) facing right and with speed zero.
	 */
	public Ship() {
		this(0,0,0,0,getMinRadius(),0);
	}
	
	/**
	 * A method that returns the x coordinate of the ship
	 * 
	 * @return 	The method returns the x-position of the ship.
	 * 			| result == this.xPosition
	 */
	@Basic
	@Raw
	@Immutable
	private double getxPosition(){
		return this.xPosition;
	}
	
	/**
	 * A method that returns the y coordinate of the ship.
	 *  
	 * @return 	The method returns the y-position of the ship.
	 * 			| result == this.xPosition
	 * 
	 */
	@Basic
	@Raw
	@Immutable
	private double getyPosition(){
		return this.yPosition;
	}
	
	
	/**
	 * Calculate the x- and y-coordinate of the position of this ship.
	 * 
	 * @return	The method returns the x-position and y-position of the ship as a double[].
	 * 			| result == {this.getxPosition(), this.getyPosition()}
	 */
	@Basic
	@Raw
	@Immutable
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
	 * @post	The new x-position will be equal to the given x-position
	 * 			|new.getxPosition() == xPosition
	 * 
	 * @post	The new y-position will be equal to the given y-position
	 * 			|new.getyPosition() == yPosition
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given yPosition and xPosition can't be less than zero.
	 * 			| !canHaveAsxPosition(xPosition) || !canHaveAsyPosition(yPosition)
	 * 
	 */
	@Raw
	@Basic
	public void setPosition(double xPosition, double yPosition) throws IllegalArgumentException {
		if (!canHaveAsxPosition(xPosition) || !canHaveAsyPosition(yPosition))
			throw new IllegalArgumentException();
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;	
	}
	
	
	/**
	 * Checks whether the given x-position is valid.
	 *  
	 * @param 	xPosition
	 * 			The given x-position of the ship to check.
	 * 
	 * @return	True if and only if the x-position is a number.
	 * 			|result == !Double.isNan(xPosition)
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsxPosition(double xPosition){
		return !Double.isNaN(xPosition);
	}
	
	/**
	 * Checks whether the given y-position is valid.
	 *  
	 * @param 	yPosition
	 * 			The given y-position of the ship to check.
	 * 
	 * @return	True if and only if the y-position is a number.
	 * 			|result == != Double.isNan(yPosition)
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsyPosition(double yPosition){
		return !Double.isNaN(yPosition);
	}
	
	/**
	 * Gives the velocity of the given ship in x-direction.
	 * 
	 * @return 	Returns the xVelocity of the ship in kilometer/second.
	 * 			|result == new.xVelocity
	 * 
	 */
	@Basic
	@Raw
	@Immutable
	private double getxVelocity(){
		return this.xVelocity;
	}
	
	/**
	 * Gives the velocity of the given ship in y-direction.
	 * 
	 * @return 	Returns the yVelocity of the ship in kilometer/second.
	 * 			|result == new.yVelocity
	 * 
	 */
	@Basic
	@Raw
	@Immutable
	private double getyVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * A method that calculates the total speed of a ship.
	 * 
	 * @effect 	Calculates the root of the sum of squares of the x- and y-velocity.
	 * 			|Math.sqrt(Math.pow(this.getxVelocity(),2)+Math.pow(this.getyVelocity(),2))
	 */
	@Basic
	@Raw
	@Immutable
	private double  getSpeed(){
		double speed = Math.sqrt(Math.pow(this.getxVelocity(),2)+Math.pow(this.getyVelocity(),2));
		return speed;
	}
	
	/**
	 * Return the x- and y-coordinate of the velocity of this ship.
	 * 
	 * @return	Returns the x- and y-coordinate of the velocity of this ship as a double[].
	 * 			| result == {this.getxVelocity(), this.getyVelocity()}
	 */
	@Basic
	@Raw
	@Immutable
	public double[] getVelocity(){
		double[] velocity = {this.getxVelocity(), this.getyVelocity()};
		return velocity;
	}
	
	/**
	 * Sets the velocity of the spaceship to the given velocity.
	 * 
	 * @param 	xVelocity
	 * 			The velocity of the ship in the x-direction, in kilometer/second.
	 * 
	 * @param	yVelocity
	 * 			The velocity of the ship in the y-direction, in kilometer/second.
	 * 
	 * @post 	If the velocity in x-direction is not accepted,
	 * 			the velocity well be set at 0.
	 * 			| if (!canHaveAsxVelocity(xVelocity)) 
	 * 				then xVelocity() = 0 	
	 * 
	 * @post 	If the velocity in y-direction is not accepted,
	 * 			the velocity well be set at 0.
	 * 			| if if (!canHaveAsyVelocity(yVelocity))
	 * 				then yVelocity = 0 	
	 * 
	 * @post	The new velocity is slower than or equals getMaxSpeed().
	 * 			| new velocity <= getMaxSpeed()
	 * 
	 * @post	The new velocity is equal to the calculated velocity. Or when 
	 * 			it exceeds getMaxSpeed, it's reduced to getMaxSpeed.
	 * 			|new.getxVelocity = xVelocity;
	 *			|new.getyVelocity = yVelocity;
	 *			|if (!canHaveAsSpeed(this.getSpeed())){
	 *			|	new.getxVelocity = xVelocity*getMaxSpeed()/(this.getSpeed());
	 *			|	new.getyVelocity = yVelocity*getMaxSpeed()/(this.getSpeed());
	 */
	@Raw
	public void setVelocity(double xVelocity,double yVelocity){
		if (!canHaveAsxVelocity(xVelocity))
			xVelocity = 0;
		if (!canHaveAsyVelocity(yVelocity))
			yVelocity = 0;
		
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		
		if (!canHaveAsSpeed(this.getSpeed())){
			this.xVelocity = xVelocity*getMaxSpeed()/(this.getSpeed());
			this.yVelocity = yVelocity*getMaxSpeed()/(this.getSpeed());
		}
		
	}
	
	
	/**
	 * Check whether the given xVelocity is a valid xVelocity for any ship.
	 * 
	 * @param 	xVelocity
	 *          The xVelocity to check.
	 *          
	 * @return 	Returns true if and only if the xVelocity is a number.
	 * 			| result == !Double.isNaN(xVelocity)
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsxVelocity(double xVelocity){
		return !Double.isNaN(xVelocity);
	}
	
	/**
	 * Checks whether the given y-velocity is valid.
	 *  
	 * @param 	yVelocity
	 * 			The given yVelocity of the ship to check.
	 * 
	 * @return	True if and only if the y-velocity is a number.
	 * 			|result == != Double.isNan(yVelocity)
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsyVelocity(double yVelocity){
		return !Double.isNaN(yVelocity);
	}	
		
	/**
	 * Checks whether the speed is a possible speed
	 * 
	 * @param 	speed
	 * 			The given speed to check.
	 * 
	 * @return 	True if and only if the speed is a number, less or equal 
	 * 			to the maximum speed, and bigger then 0.
	 * 			| result == speed <= getMaxSpeed() && !Double.isNaN(speed) && speed >= 0
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsSpeed(double speed){
		return (speed <= getMaxSpeed() && !Double.isNaN(speed) && speed >= 0);
	}
	
	/**
	 * Return the highest possible value for the speed of all ships.
	 *
	 * @return 	The highest possible value for the speed of all ships is the speed
	 * 			of light = 30000 kilometer/second.
	 *		 	| result == 30000
	 */
	@Basic
	private static int getMaxSpeed() {
		return 300000;
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
		if (0 <= orientation && orientation < 2*Math.PI && !Double.isNaN(orientation))
			return true;
		return false;
	}

	/**
	 * Return the radius of this ship.
	 */
	@Basic
	@Raw
	@Immutable
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Check whether the given radius is a valid radius for any ship.
	 * 
	 * @param 	radius
	 *          The radius to check.
	 *          
	 * @return 	Returns true if and only if the radius is a number bigger 
	 * 			than or equal to the minimum radius.
	 * 			| result == (radius >= getMinRadius() && !Double.isNaN(radius))
	 */
	@Raw
	public static boolean canHaveAsRadius(double radius) {
		return (radius >= getMinRadius() && !Double.isNaN(radius));
	}


	/**
	 * Return the lowest possible value for the radius of all ships.
	 *
	 * @return 	The lowest possible value for the radius of all ships is 10 kilometer.
	 *		 	| result == 10
	 */
	@Basic
	@Raw
	private static int getMinRadius() {
		return 10;
	}	
	
	/**
	 * Change the position of the ship based on the current position, 
	 * velocity and a given time duration. 
	 * 
	 * @param 	duration
	 * 			The duration of the movement.
	 * 
	 * @post 	The position of the ship will be changed to the 
	 * 			new position after the given time, speed and direction
	 * 			| setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1])
	 * 
	 * @throws 	IllegalArgumentException
	 * 			the duration is not a valid duration
	 * 			|(!isValidDuration(duration))
	 * 
	 */
	@Raw
	public void move(double duration) throws IllegalArgumentException{
		if (!canHaveAsDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
	}
	
	/**
	 * Get the position of a ship after it's moved.
	 * 
	 * @param 	duration
	 * 			The duration of the movement.
	 * 			
	 * @return 	The new position after moving as a double[].
	 * 			| result == {getPosition()[0]+getVelocity()[0]*duration, 
	 * 			|				getPosition()[1]+getVelocity()[1]*duration}
	 * 			
	 */	
	@Raw
	public double [] getPositionAfterMoving(double duration){
			return new double[] {getPosition()[0]+getVelocity()[0]*duration,
			getPosition()[1]+getVelocity()[1]*duration};	
	}
	/**
	 * Checks whether the duration of the movement is a valid duration.
	 * 
	 * @param 	duration
	 * 			The duration of the movement.
	 * 
	 * @return	True if and only if the duration is a number, 
	 * 			greater than or equal to zero.
	 * 			| result == duration >= 0 && !Double.isNaN(duration)
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsDuration(double duration){
		return (duration >= 0 && !Double.isNaN(duration));
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
	 * @post	A must be valid, if not, it's set to 0 and the ship will
	 * 			not accelerate.
	 * 			| if (!canHaveAsA(a))
	 *				then a = 0;
	 * 
	 * @post	The new velocity is slower than or equals getMaxSpeed().
	 * 			| new velocity <= getMaxSpeed()
	 * 
	 * @post	The new velocity is equal to the calculated velocity. Or when 
	 * 			it exceeds getMaxSpeed, it's reduced to getMaxSpeed.
	 * 			|new.getxVelocity = xVelocity;
	 *			|new.getyVelocity = yVelocity;
	 *			|if (!canHaveAsSpeed(this.getSpeed())){
	 *			|	new.getxVelocity = xVelocity*getMaxSpeed()/(this.getSpeed());
	 *			|	new.getyVelocity = yVelocity*getMaxSpeed()/(this.getSpeed());
	
	 */
	@Raw
	@Immutable
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
	 * Check whether the given factor a is a valid factor a for any ship.
	 * 
	 * @param 	a
	 * 			Factor that determines how much to accelerate a ship.
	 * 
	 * @return	Returns true if and only if a is a number bigger 
	 * 			than or equal to 0.
	 * 			| result == (a>=0 && !Double.isNaN(a)))
	 */
	@Raw
	public static boolean canHaveAsA(double a){
		return (a>=0 && !Double.isNaN(a));
	}
	
	
	/**
	 * 
	 * Return the distance between the given ship and this ship.
	 * The distance may be negative if both ships overlap
	 * 
	 * @param	ship
	 * 			The given ship. 
	 * 
	 * @post	The distance between a ship and itself is zero.
	 * 			| if (distance == (-2*this.getRadius()))
	 *				then return 0;		
	 * 
	 * @throws	NullPointerException()
	 * 			The given ship must exist.
	 * 			| ship == null
	 * 
	 * 
	 */
	@Raw
	@Immutable
	public double getDistanceBetween(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		
		double new_x = this.getxPosition() - other.getxPosition();
		double new_y = this.getyPosition() - other.getyPosition();
		double distance_between_centers = Math.sqrt(Math.pow(new_x, 2) + Math.pow(new_y, 2));
		double distance = distance_between_centers - this.getRadius() - other.getRadius();
		
		if (distance == (-2*this.getRadius()))
			return 0;
		
		return distance;	
	}
	
	/**
	 * Check if the given ship overlaps this ship.
	 * 
	 * @param 	ship
	 * 			The given ship.
	 * 
	 * @return	Ships overlap if the distance between them is less then or equals 0.
	 * 			| result == this.getDistanceBetween(ship) <= 0
	 * 
	 * @throws 	NullPointerException()
	 * 			The given ship must exist.
	 * 			| ship == null
	 */
	@Raw
	@Immutable
	public boolean overlap(Ship ship) throws NullPointerException {
		if (ship == null || this == null)
			throw new NullPointerException();
		
		return this.getDistanceBetween(ship) <= 0;
	}
	
	
	/**
	 * A method that gives the time between a collision of 2 ships. 
	 * This method does not apply on two ships that overlap.
	 * 
	 * @param 	other
	 * 			A second ship to check if this ship collides with.
	 * 
	 * @effect	Returns the time until collision with the other ship.
	 * 			| new.getTimeToCollision == timeToCollision
	 * 
	 * @effect 	The time will be positive infinity if the ships will never collide.
	 * 			| new.getTimeToCollision == Double.POSITIVE_INFINITY
	 * 
	 * @throws 	NullPointerException
	 * 			Throw a NullPointerException if one of the 2 ships doesn't exist.
	 * 			| other == null || this == null
	 */
	@Raw
	@Immutable
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
			// d will be negative if the ships overlap
		return timeToCollision;
	}
	
	/**
	 * A method that calculates the distance in x- and y-direction between the 
	 * centres of the ships.
	 * 
	 * @param 	other
	 * 			The second ship to check the distance between.
	 * 
	 * @post 	The deltaDistance will be a list of the difference between 
	 * 			the 2 centres of the ship.
	 * 			|deltaDistance =  {other.getxPosition()-this.getxPosition(),
	 *			other.getyPosition()-this.getyPosition()}
	 *			
	 * @throws 	NullPointerException
	 * 			the method will throw a NullPointerException if one of the 2 ships 
	 * 			doesn't exist
	 * 			| other == null || this == null
	 */
	@Raw
	@Immutable
	private double [] getDeltaDistance(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double [] deltaDistance = {other.getxPosition()-this.getxPosition(),
				other.getyPosition()-this.getyPosition()};
		return deltaDistance;
	}
	
	/**
	 * A method that calculates the difference in velocity in x- and y-direction 
	 * between two ships.
	 * 
	 * @param 	other
	 * 			The second ship to check the difference in velocity between.
	 * 
	 * @post 	The deltaVelocity will be a double[] of the difference in 
	 * 			velocity between the 2 ships.
	 * 			| deltaVelocity = {other.getxVelocity()-this.getxVelocity(),
	 *			other.getyVelocity()-this.getyVelocity()};
	 *
	 * @throws 	NullPointerException
	 * 			The method will throw a NullPointerException if one of the 
	 * 			2 ships doesn't exist.
	 * 			| other == null || this == null
	 */	
	@Raw
	@Immutable
	private double [] getDeltaVelocity(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double [] deltaVelocity = {other.getxVelocity()-this.getxVelocity(),
				other.getyVelocity()-this.getyVelocity()};
		return deltaVelocity;
	}
	
	/**
	 * A method that calculates the square of the difference in position between 
	 * the centres of the two ships.
	 * 
	 * @param 	other
	 * 			The second ship to check the square of the difference in position between.
	 * 
	 * @post	The deltaPowDistance will be the sum of the squares of the x- and y- in 
	 * 			distance between the centres of the ships.
	 * 			| deltaPowDistance = Math.pow(getDeltaDistance(other)[0],2)+
	 *				Math.pow(getDeltaDistance(other)[1],2);
	 *				
	 * @throws 	NullPointerException
	 * 			the method will throw a NullPointerException if one of the 
	 * 			2 ships doesn't exist.
	 * 			| other == null || this == null
	 */	
	@Raw
	@Immutable
	private double getDeltaPowDistance(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double deltaPowDistance = Math.pow(this.getDeltaDistance(other)[0],2)+
					Math.pow(this.getDeltaDistance(other)[1],2);
		return deltaPowDistance;
	}

	/**
	 * A method that calculates the square of the difference in velocity 
	 * between two ships.
	 * 
	 * @param 	other
	 * 			The second ship to check the square of the difference in velocity between.
	 * 
	 * @post	The deltaPowVelocity will be the sum of the squares of the x- and 
	 * 			y- difference in velocity between the ships.
	 * 			| double deltaPowVelocity = Math.pow(getDeltaVelocity(other)[0], 2)+
	 *				Math.pow(getDeltaVelocity(other)[1], 2);
	 *
	 * @throws 	NullPointerException
	 * 			The method will throw a NullPointerException if one of the 
	 * 			2 ships doesn't exist.
	 * 			| other == null || this == null
	 */	
	@Raw
	@Immutable
	private double getDeltaPowVelocity(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double deltaPowVelocity = Math.pow(this.getDeltaVelocity(other)[0], 2)+
					Math.pow(this.getDeltaVelocity(other)[1], 2);
		return deltaPowVelocity;
	}
	
	/**
	 * A method that calculates the scalarProduct of the difference 
	 * in position and velocity between two ships.
	 * 
	 * @param 	other
	 * 			The second ship to check the scalarProduct of the difference 
	 * 			in position and velocity between.
	 * 
	 * @post 	The deltaDistanceVelocity will be the sum of the product of x- and 
	 * 			y-distance and difference in velocity of the two ships.
	 * 			| deltaDistanceVelocity = (getDeltaVelocity(other)[0]*getDeltaDistance(other)[0])+
	 *				(getDeltaVelocity(other)[1]*getDeltaDistance(other)[1]);
	 *
	 * @throws 	NullPointerException
	 * 			The method will throw a nullpointerexception if one of the 
	 * 			2 ships doesn't exist.
	 * 			| other == null || this == null
	 */		
	@Raw
	@Immutable
	private double getDeltaDistanceVelocity(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double deltaDistanceVelocity = (this.getDeltaVelocity(other)[0]*this.getDeltaDistance(other)[0])+
						(this.getDeltaVelocity(other)[1]*this.getDeltaDistance(other)[1]);
		return deltaDistanceVelocity;
	}
	/**
	 * A method that calculates d between two ships.
	 * 
	 * @param 	other
	 * 			A second ship with which you want to calculate d with.
	 * 
	 * @post 	The d will be the square of the deltaDistanceVelocitylowered by the 
	 * 			product of the deltaPowVelocity with the difference between 
	 * 			deltaPowVelocity and the distance between the two ships.
	 * 			| d = Math.pow(getDeltaDistanceVelocity(other),2)-
	 *				(getDeltaPowVelocity(other))*(getDeltaPowDistance(other)-this.getDistanceBetween());
	 *
	 * @throws 	NullPointerException
	 * 			The method will throw a NullPointerException if one of the 
	 * 			2 ships doesn't exist.
	 * 			| other == null || this == null
	 */	
	@Raw
	@Immutable
	private double getD(Ship other) throws NullPointerException{
		if (other == null || this == null)
			throw new NullPointerException();
		double d = Math.pow(this.getDeltaDistanceVelocity(other),2)-
					(this.getDeltaPowVelocity(other))*(this.getDeltaPowDistance(other)
													-Math.pow((this.getRadius()+other.getRadius()),2));
		return d;
	}
	
	/**
	 * Get the point of collision if two ships will ever collide. 
	 * 
	 * @param 	other
	 * 			A second ship to calculate the collision position with.
	 * 
	 * @post 	The collisionPoint will be the point where the two ships hit.
	 * 			| collisionPoint = this.getPositionAfterMoving(time)+
									this.getRadius()*getDeltaDistance(other)/getD(other)
									
	 * @throws 	NullPointerException
	 * 			The method will throw a NullPointerException if one of the 
	 * 			2 ships doesn't exist.
	 * 			| other == null || this == null
	 * 
	 */
	@Raw
	@Immutable
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
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	
	
}
