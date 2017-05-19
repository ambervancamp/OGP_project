package asteroids.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of round things that are defined by a position, velocity and radius.
 * It also has a mass, density, and can be placed in a space.
 * 
 * @invar 	Each round entity can have its position, determined by x- and y-coordinate, as position.
 * 			| canHaveAsPosition(this.getPosition())
 *        
 * @invar 	Each round entity can have its velocity, determined by x- and y-coordinate, as velocity.
 * 			| canHaveAsVelocity(this.getVelocity)
 *        
 * @invar  	Each round entity can have its radius as radius.
 *        	| canHaveAsRadius(this.getRadius())
 *        
 * @invar  	Each round entity must have a proper space.
 *       	| hasProperSpace()
 * 
 * @author amber_000
 */
@Value
public abstract class RoundEntity {
	
	
	/**
	 * Initialize this new round entity with given parameters.
	 *
	 * @param	x
	 * 			The x-coordinate of the position of this new round entity, in kilometer.
	 * 
	 * @param	y
	 * 			The y-coordinate of the position of this new round entity, in kilometer.
	 * 
	 * @param 	xVelocity
	 *			The x-coordinate velocity for this new round entity, in kilometer/second.
	 *
	 * @param 	yVelocity
	 * 			The y-coordinate velocity for this new round entity, in kilometer/second.
	 * 
	 * @param	radius
	 * 			The radius of this new round entity, in kilometer.
	 * 
	 * @post	The xPosition will be equal to the given x-coordinate and 
	 * 			the yPosition will be equal to the given y-coordinate.
	 * 			| new.getPosition() == {x, y}
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given position is not a valid position for any round entity.
	 * 			| !canHaveAsPosition(x, y)
	 * 
	 * @effect 	The given xVelocity and yVelocity are set as the xVelocity and yVelocity
	 * 			of this new round entity.
	 *       	| new.setVelocity(xVelocity,yVelocity)
	 *       
	 * @post 	If the velocity is not accepted, the velocity will 
	 * 			be set at 0.
	 * 			| if (!canHaveAsVelocity(xVelocity, yVelocity)) 
	 * 			|	then new.getVelocity() == (0,0)
	 *       
	 * @post 	If the speed exceeds the maximum speed, the velocity's magnitude
	 * 			will be lowered to the maximum speed, in such a way that the direction will stay the same.
	 * 			| if this.getSpeed() > getMaxSpeed()
	 * 				then new.getVelocity = this.getVelocity*getMaxSpeed()/this.getSpeed   
	 * 
	 * @post 	The highest possible value for the speed of this round entity is getMaxSpeed(),
	 * 			which is by default the absolute max speed for all round entities.
	 *		 	| new.getMaxSpeed() == ABSOLUTE_MAX_SPEED
	 *
	 * @post 	The radius of this new round entity is equal to the given radius. 
	 * 			| new.getRadius() == radius
	 * 
	 * @throws 	IllegalArgumentException
	 *          The given radius is not a valid radius for any round entity. 
	 *          | !canHaveAsRadius(this.getRadius())
	 * 
	 * @post 	The lowest possible value for the radius of this round entity is getMinRadius().
	 * 			| new.getRadius >= getMinRadius()
	 * 
	 * @effect	This ship is placed in a new unbound space.
	 * 			| this.placeInSpace(new.unboundspace)
	 * 
	 */
	protected RoundEntity(double x, double y, double xVelocity, double yVelocity, double radius) 
			throws IllegalArgumentException {
		this.setPosition(x,y);
		if (!canHaveAsVelocity(xVelocity, yVelocity)){
			xVelocity = 0;
			yVelocity = 0;
		}
		// Default value because of total programming style
		this.setVelocity(xVelocity, yVelocity);
		if (!canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
		// No setter for density because radius is final variable
		
		UnboundSpace unboundspace = new UnboundSpace();
		this.placeInSpace(unboundspace);
		// Round entities need to be associated with an unbound space until associated with another space.
	}
	
	/**
	 * Terminate this round entity.
	 */
	public abstract void terminate();
	
	/**
	 * Return a boolean indicating whether or not this round entity
	 * is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}

	/**
	 * Variable registering whether this round entity is terminated.
	 */
	protected boolean isTerminated = false;
	
	/**
	 * A method that returns the x-coordinate of the round entity
	 * 
	 * @return 	The method returns the x-position of the round entity.
	 * 			| result == this.xPosition
	 */
	@Basic
	@Raw
	@Immutable
	protected double getxPosition(){
		return this.xPosition;
	}
	//clone?
	
	/**
	 * A method that returns the y-coordinate of the round entity.
	 *  
	 * @return 	The method returns the y-position of the round entity.
	 * 			| result == this.yPosition
	 * 
	 */
	@Basic
	@Raw
	@Immutable
	protected double getyPosition(){
		return this.yPosition;
	}
	//clone?
	
	
	/**
	 * A method that returns the x- and y-coordinate of the position of this round entity.
	 * 
	 * @return	The method returns the x-position and y-position of the ship as a double[].
	 * 			| result == {this.getxPosition(), this.getyPosition()}
	 */
	@Basic
	@Raw
	@Immutable
	public double[] getPosition(){
		double[] position = {this.getxPosition(), this.getyPosition()};
		return position.clone();
	}
	
	/**
	 * Set the x-position and y-position of this ship to the given value.
	 * 
	 * @param 	x
	 * 			Given x-position.
	 * 
	 * @param 	y
	 * 			Given y-position.
	 * 
	 * @post	The new xPosition will be equal to the given x-coordinate.
	 * 			|new.getxPosition() == x
	 * 
	 * @post	The new yPosition will be equal to the given y-coordinate.
	 * 			|new.getyPosition() == y
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given y-coordinate and x-coordinate are not valid.
	 * 			| !canHaveAsPosition(x, y)
	 * 
	 */
	@Raw
	@Basic
	public void setPosition(double x, double y) throws IllegalArgumentException {
		if (!canHaveAsPosition(x, y))
			throw new IllegalArgumentException();
		// Houdt geen rekening met het feit dat schepen kunnen overlappen as ze geplaatst worden.
		// Hier moet toch ook rekening mee gehouden worden, of is da al te specifiek?
		this.xPosition = x;
		this.yPosition = y;	
	}
	
	/**
	 * Checks whether the given position, determined by x- and y-coordinate, is valid.
	 * 
	 * @param 	x
	 * 			| The given x-coordinate to check.
	 * 
	 * @param 	y
	 * 			| The given y-coordinate to check.
	 * 
	 * @return	Returns true if and only if the xPostion and yPosition are positive numbers.
	 * 			| result == !Double.isNaN(xPosition) && !Double.isNaN(yPosition)
	 */
	@Raw
	@Immutable
	public boolean canHaveAsPosition(double x, double y){
		return (!Double.isNaN(x) && !Double.isNaN(y));
	}
	
	/**
	 * Variable registering the xPosition of this ship.
	 */
	protected double xPosition;
	
	/**
	 * Variable registering the yPosition of this ship.
	 */
	protected double yPosition;
	
	/**
	 * Gives the velocity of the given round entity in x-direction.
	 * 
	 * @return 	Returns the xVelocity of the round entity in kilometer/second.
	 * 			|result == new.xVelocity
	 */
	@Basic
	@Raw
	@Immutable
	protected double getxVelocity(){
		return this.xVelocity;
	}
	
	/**
	 * Gives the velocity of the given round entity in y-direction.
	 * 
	 * @return 	Returns the yVelocity of the round entity in kilometer/second.
	 * 			|result == new.yVelocity
	 * 
	 */
	@Basic
	@Raw
	@Immutable
	protected double getyVelocity(){
		return this.yVelocity;
	}
	
	/**
	 * A method that calculates the total speed of this round entity.
	 * 
	 * @effect 	Calculates the root of the sum of squares of the x- and y-velocity.
	 * 			|Math.sqrt(Math.pow(this.getxVelocity(),2)+Math.pow(this.getyVelocity(),2))
	 */
	@Basic
	@Raw
	@Immutable
	public double  getSpeed(){
		double speed = Math.sqrt(Math.pow(this.getxVelocity(),2)+Math.pow(this.getyVelocity(),2));
		return speed;
	}
	
	/**
	 * Return the x- and y-coordinate of the velocity of this round entity.
	 * 
	 * @return	Returns the x- and y-coordinate of the velocity of this round entity as a double[].
	 * 			| result == {this.getxVelocity(), this.getyVelocity()}
	 */
	@Basic
	@Raw
	@Immutable
	public double[] getVelocity(){
		double[] velocity = {this.getxVelocity(), this.getyVelocity()};
		return velocity.clone();
	}
	
	/**
	 * Sets the velocity of the round entity to the given velocity.
	 * 
	 * @param 	xVelocity
	 * 			The velocity of the round entity in the x-direction, in kilometer/second.
	 * 
	 * @param	yVelocity
	 * 			The velocity of the round entity in the y-direction, in kilometer/second.		
	 * 
	 * @post	The new velocity is equal to the calculated velocity. 
	 * 			| new.getxVelocity = xVelocity
	 *			| new.getyVelocity = yVelocity
	 *
	 * @post	The magnitude of the new velocity is slower than or equals getMaxSpeed().
	 * 			| new.getVelocity <= getMaxSpeed()
	 * 
	 * @post	If the given velocity exceeds getMaxSpeed, its magnitude is reduced 
	 * 			to getMaxSpeed, without losing the orientation of the original velocity.
	 *			| if (!canHaveAsSpeed(this.getSpeed())){
	 *			|	new.getxVelocity = xVelocity*getMaxSpeed()/(this.getSpeed());
	 *			|	new.getyVelocity = yVelocity*getMaxSpeed()/(this.getSpeed());
	 */
	@Raw
	public void setVelocity(double xVelocity, double yVelocity){
		if (canHaveAsVelocity(xVelocity, yVelocity)){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;
			
			if (!canHaveAsSpeed(this.getSpeed())){
				this.xVelocity = xVelocity*getMaxSpeed()/(this.getSpeed());
				this.yVelocity = yVelocity*getMaxSpeed()/(this.getSpeed());
			}
		}
	}
	
	/**
	 * Check whether the given velocity is valid for any round entity. 
	 * 
	 * @param 	xVelocity
	 * 			| the xVelocity to check.
	 * 
	 * @param 	yVelocity
	 * 			| the yVelocity to check.
	 * 
	 * @return	Returns true if and only if the xVelocity and yVelocity are numbers.
	 * 			| result == !Double.isNaN(xVelocity) && !Double.isNaN(yVelocity)
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsVelocity(double xVelocity, double yVelocity){
		return (!Double.isNaN(xVelocity) && !Double.isNaN(yVelocity));
	}	
		
	/**
	 * Checks whether the given speed is a valid speed for round entities.
	 * 
	 * @param 	speed
	 * 			The given speed to check.
	 * 
	 * @return 	True if and only if the speed is a number, less or equal 
	 * 			to the maximum speed for round entities, and bigger then 0.
	 * 			| result == speed <= getMaxSpeed() && !Double.isNaN(speed) && speed >= 0
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsSpeed(double speed){
		return (!Double.isNaN(speed) && speed <= getMaxSpeed() && speed >= 0);
	}
	
	/**
	 * Return the highest possible value for the speed of this round entity, always
	 * smaller or equal to the absolute max speed.
	 *
	 * @return 	The highest possible value for the speed of round entities is by default
	 * 			the absolute highest possible value for the speed.
	 *		 	| result == ABSOLUTE_MAX_SPEED
	 */
	@Basic
	protected static double getMaxSpeed() {
		return ABSOLUTE_MAX_SPEED;
	}
	
	/**
	 * Variable registering the xVelocity of this ship.
	 */
	protected double xVelocity;
	
	/**
	 * Variable registering the yVelocity of this ship.
	 */
	protected double yVelocity;
	
	/**
	 * Constant reflecting the absolute highest possible value for the speed of all round entities.
	 *
	 * @return 	The absolute highest possible value for the speed of all round entities is the speed
	 * 			of light = 30000 kilometer/second.
	 *		 	| result == 30000
	 */
	protected static double ABSOLUTE_MAX_SPEED = 300000.0;	
	
	/**
	 * Return the radius of this round entity.
	 */
	@Basic
	@Raw
	@Immutable
	public double getRadius() {
		return this.radius;
	}
	//clone?

	/**
	 * Check whether the given radius is a valid radius for any round entity.
	 * 
	 * @param 	radius
	 *          The radius to check.
	 *          
	 * @return 	Returns true if and only if the radius is a number bigger 
	 * 			than or equal to the minimum radius.
	 * 			| result == (!Double.isNaN(radius) && radius >= getMinRadius())
	 */
	@Raw
	public boolean canHaveAsRadius(double radius) {
		return (!Double.isNaN(radius) && radius >= this.getMinRadius());
	}
	
	/**
	 * Return the lowest possible value for the radius of this round enity.
	 */
	@Basic
	@Raw
	public abstract double getMinRadius();
	
	/**
	 * Variable registering the radius of this ship.
	 */
	protected final double radius;

	/**
	 * Return the density of this round entity.
	 */
	@Basic
	@Raw
	public abstract double getDensity();
	
	/**
	 * Return the mass of this round entity.
	 */
	@Basic
	@Raw
	public abstract double getMass();
	
	/**
	 * Return the space where this round entity is placed in.
	 * 
	 * @return	Returns the space this round entity is placed in.
	 * 			| result == this.space
	 */
	@Basic
	@Raw
	public Space getSpace(){
		return this.space;
	}
	
	/**
	 * Return the world where this round entity is placed in, if any.
	 * 
	 * @return	Returns the world this round entity is placed in, if
	 * 			it isn't placed in a world it returns null.
	 * 			| if (this.getSpace() instanceof World)
	 *			|	result == this.getSpace()
	 *			| else
	 *			|	result == null
	 */
	@Basic
	@Raw
	public World getWorld(){ 
		if (this.getSpace() instanceof World)
			return (World) this.getSpace();
		return null;
	}
	
	/**
	 * Register the given space as the space where this round entity is placed in.
	 * 
	 * @param 	space
	 * 			The given space to set the round entity in.
	 * 		
	 * @throws 	IllegalArgumentException
	 * 			The given space is not valid.
	 * 			| !canHaveAsSpace(space)
	 */
	protected void setSpace(Space space) throws IllegalArgumentException{
		if (!canHaveAsSpace(space))
			throw new IllegalArgumentException();
		this.space = space;
	}
	
	/**
	 * Checks whether the given space is a valid space for any round entity.
	 * 
	 * @param 	space
	 * 			The given space to check if this round entity can be placed in.
	 * 
	 * @return 	False if the given space is not effective or null, or this round entity is terminated.
	 *       	| result == (space != null && (!space.isTerminated()) && (!this.isTerminated()))
	 */
	public boolean canHaveAsSpace(Space space){
		return (space != null && (!space.isTerminated()) && (!this.isTerminated()));
	}
	
	/**
	 * Check whether this round entity has a proper space.
	 * 
	 * @return 	True if and only if this round entity can have its space
	 *         	as its space, and if that space, if it is effective, in turn references
	 *         	this round entity.
	 *       	| result ==
	 *       	|   canHaveAsSpace(this.getSpace()) && (this.getSpace().hasEntity(this))
	 */
	public boolean hasProperSpace() {
		return canHaveAsSpace(this.getSpace()) && (this.getSpace().hasAsEntity(this));
	}
	
	/**
	 * Check whether this round entity has a space.
	 *
	 * @return 	True if this round entity references an effective space,
	 *         	false otherwise.
	 *       	| result == (this.getSpace != null)
	 */
	public boolean hasSpace(){
		return (this.getSpace() != null);
	}
	
	/**
	 * Check whether this round entity has a world.
	 * 
	 * @return	True if this round entity references an effective world,
	 *         	false otherwise.
	 *       	| result == (this.getSpace() instanceof World)
	 */
	public boolean hasWorld(){
		if (this.hasSpace())	
			return(this.getSpace() instanceof World);
		return false;
	}
	
	/**
	 * Check whether this round entity is placed in the same space as the given
	 * round entity.
	 * 
	 * @return	True if this round entity references the same space as the
	 * 			given entity.
	 *       	| result == (this.getSpace() == other.getSpace())
	 */
	public boolean inSameSpace(RoundEntity other){
		return this.getSpace().equals(other.getSpace());
	}
		
	/**
	 * Set space from this round entity to given space. If it's already in a space, it's replaced to 
	 * the new space. Also places round entity in given space.
	 * When the newly placed entity collides with another entity allready in this world,
	 * it throws an IllegalArgumentException.
	 * 
	 * @param 	space
	 * 			The given space to put the round entity in.
	 * 
	 * @effect	The given space will be the new space of this round entity.
	 * 			| this.setSpace(space)
	 * 
	 * @post 	If the round entity's already placed in a space, first it will be removed from
	 * 			that space. Then it will set space as its space.
	 * 			| if (this.hasSpace()){
	 *			|	then this.removeOutSpace();
	 *			|	this.setSpace(space);		
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the space is not valid.
	 * 			| !canHaveAsSpace(space)
	 * 
	 * @throws	IllegalArgumentException.
	 * 			If this entity overlaps with any entity already in the space.
	 * 			| for (RoundEntity entity: space.getEntities())
	 *			|	this.overlap(entity)
	 *
	 * @throws	IllegalArgumentException.
	 *			If this entity overlaps the boundaries of the given space.
	 *			| !space.fitBoundary(this)
	 */
	public void placeInSpace(Space space) throws IllegalArgumentException {
		if ((!canHaveAsSpace(space)))
			throw new IllegalArgumentException();
		for (RoundEntity entity: space.getEntities()){
			if (this.overlap(entity))
				throw new IllegalArgumentException();
		}
		if (!space.fitBoundary(this))
			throw new IllegalArgumentException();		
		if (this.hasSpace()){
			this.removeOutSpace();
			this.setSpace(space);
			space.addEntity(this);
		}
		else {
			this.setSpace(space);
			space.addEntity(this);	
			// Else statement used in constructor
		}
	}
	
	/**
	 * Remove the space from this round entity, if any. It is not placed into a new space.
	 * The round entity is also removed from the space.
	 * 
	 * @post 	The round entity will not be placed in any space.
	 * 			| !new.hasSpace()
	 * 
	 * @post	The former space of this round entity, if any, is no longer
	 *		   	its space.
	 *       	| if (this.hasSpace())
	 *       	|   then ! (new (this.getSpace())).hasEntity(this) 
	 */
	@Raw
	protected void removeOutSpace(){
		if (this.hasSpace()){
			Space space = this.getSpace();
			this.space = null;
			space.deleteEntity(this);
			// Can not use 'setSpace' because it does not allow to set a space to null.	
		}		
	}
	// If statement not necessary, because RemoveOutSpace() is only used when
	// sure the round entity has a space (no boundary case).
	
	/**
	 * Remove this round entity from given world, if it's placed in this world. 
	 * It is then replaced to a new unbound space.
	 * 
	 * @param	world
	 * 			The given world, to remove this entity from.
	 * 
	 * @post 	The round entity will be placed in a new unbound space.
	 * 			| this.getSpace() == new UnboundSpace()
	 * 
	 * @post	The former world of this round entity, if it was the given world, is no
	 * 			longer its space.
	 *       	| if (this.getWorld() == world)
	 *       	|   then ! (new (this.getSpace())).hasEntity(this) 
	 */
	public void removeEntityFromWorld(World world){
		if (this.canHaveAsSpace(world) && this.getWorld() == world){
			UnboundSpace unboundspace = new UnboundSpace();
			this.placeInSpace(unboundspace);
		}
	}
	// Be careful with the use of this function.

	/**
	 * @return	a list of all entities of this type placed in the given Space.
	 */
	public List<RoundEntity> getCertainEntities(Space space){
		List<RoundEntity> certainEntities = new ArrayList<RoundEntity>();
		if (space == null || space.isTerminated())
			return certainEntities;
		
		if (this instanceof Ship){
			for (RoundEntity entity : space.getEntities()){
				if(entity instanceof Ship)
					certainEntities.add((RoundEntity) entity);
			}
		}
		else if (this instanceof Bullet){
			for (RoundEntity entity : space.getEntities()){
				if(entity instanceof Bullet)
					certainEntities.add((RoundEntity) entity);
			}
		}
		
		else if (this instanceof Asteroid){
			for (RoundEntity entity : space.getEntities()){
				if(entity instanceof Asteroid)
					certainEntities.add((RoundEntity) entity);
			}
		}
		else if (this instanceof Planetoid){
			for (RoundEntity entity : space.getEntities()){
				if(entity instanceof Planetoid)
					certainEntities.add((RoundEntity) entity);
			}
		}
				
		return certainEntities;
	}	
	
	/**
	 * Variable registering the space this round entity is placed in.
	 */
	protected Space space = null;
	
	
	
	/**
	 * COLLISIONS - COLLISIONS - COLLISIONS - COLLISIONS - COLLISIONS - COLLISIONS 
	 */
	

	/**
	 * Get the position of a round entity after it's moved, given a duration.
	 * 
	 * @param 	duration
	 * 			The duration of the movement.
	 * 			
	 * @return 	The new position after moving as a double[].
	 * 			If the thruster is on, and the entity is a ship,
	 * 			the ship will accelerate and its position after moving will be changed:
	 * 			| result == {getPosition()[0]+getVelocity()[0]*duration+((Ship)this).getAcceleration()*duration*duration/2,
	 *			|			getPosition()[1]+getVelocity()[1]*duration+((Ship)this).getAcceleration()*duration*duration/2}
	 *			Otherwise, if the thruste is off, or the entity is a bullet
	 * 			|{getPosition()[0]+getVelocity()[0]*duration,
	 *			|getPosition()[1]+getVelocity()[1]*duration}
	 *
	 * @throws 	IllegalArgumentException
	 * 			The duration is not a valid duration or the entity it is used on is already
	 * 			terminated.
	 * 			| (!canHaveAsDuration(duration)) || this.isTerminated()
	 */	
	@Raw
	public double [] getPositionAfterMoving(double duration) 
			throws IllegalArgumentException{
		if (!canHaveAsDuration(duration) || this.isTerminated())
			throw new IllegalArgumentException();
		if (this instanceof Ship && ((Ship)this).isThrusterOn())
			return new double[] {this.getPosition()[0]+this.getVelocity()[0]*duration+((Ship)this).getAcceleration()*duration*duration/2,
					       		this.getPosition()[1]+this.getVelocity()[1]*duration+((Ship)this).getAcceleration()*duration*duration/2};
		else
			return new double[] {getPosition()[0]+getVelocity()[0]*duration,
				getPosition()[1]+getVelocity()[1]*duration};	
	}
	/**
	 * Change the position of the entity based on the current position, 
	 * velocity, a given time duration and, if the entity is a ship, accelerator and thruster. 
	 * 
	 * @param 	duration
	 * 			The duration of the movement.
	 * 
	 * @effect 	The position of the entity will be changed to the 
	 * 			new position after the given time, speed and direction
	 * 			| setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1])
	 * 
	 * @throws	IllegalArgumentException
	 * 			Is the given duration is not valid.
	 * 			| !canHaveAsDuration(duration)
	 */
	@Raw
	public void move(double duration) throws IllegalArgumentException{
		if (!canHaveAsDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
	}
	
	/**
	 * Change the velocity of the ship based on the current velocity, a given time duration
	 * and if the entity is a ship, accelerator and thruster. 
	 * @param 	duration
	 * 			The duration of the movement
	 * @return	The new velocity of the entity after the given time
	 * 			| @see implementation
	 * @throws 	IllegalArgumentException
	 * 			If the given duration is not a valid duration or this entity is already terminated.
	 * 			| !canHaveAsDuratio(duration) || this.isTerminated()
	 */
	public double [] getVelocityAfterMoving(double duration) 
			throws IllegalArgumentException{
		if(!canHaveAsDuration(duration) || this.isTerminated())
			throw new IllegalArgumentException();
		if (this instanceof Ship && ((Ship)this).isThrusterOn())
			return new double[] {this.getVelocity()[0]+((Ship)this).getAcceleration()*Math.cos(((Ship)this).getOrientation())*duration,
								this.getVelocity()[1]+((Ship)this).getAcceleration()*Math.cos(((Ship)this).getOrientation())*duration};
		return new double[] {this.getVelocity()[0],this.getVelocity()[1]};
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
		return (!Double.isNaN(duration) && duration >= 0);
	}
	
	/**
	 * 
	 * Return the distance between the given round entity and this round entity.
	 * The distance may be negative if both round entities overlap.
	 * 
	 * @param	other
	 * 			The given round entity. 
	 * 
	 * @post	The distance between a round entity and itself is zero.
	 * 			| if (distance == (-2*this.getRadius()))
	 *			|	then return 0;
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The round entities may not ever collide.
	 * 			| !canAsCollision(other)
	 */
	@Raw
	@Immutable
	public double getDistanceBetween(RoundEntity other){		
		double new_x = this.getxPosition() - other.getxPosition();
		double new_y = this.getyPosition() - other.getyPosition();
		double distance_between_centers = Math.sqrt(Math.pow(new_x, 2) + Math.pow(new_y, 2));
		double distance = distance_between_centers - this.getRadius() - other.getRadius();
		
		if (distance == (-2*this.getRadius()))
			return 0;
		
		return distance;	
	}
	
	/**
	 * Check if the given round entity significantly overlaps this round entity.
	 * 
	 * @param 	other
	 * 			The given round entity.
	 * 
	 * @return	Round entities overlap if the distance between them is less then or equals 0.
	 * 			| result == this.getDistanceBetween(other) <= 0
	 * 
	 * @throws 	NullPointerException()
	 * 			The given entity doesn't exist.
	 * 			| other == null
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The entities may not ever collide.
	 * 			| !canAsCollision(other)
	 */
	@Raw
	@Immutable
	public boolean overlap(RoundEntity other){		
		return (Math.sqrt(this.getDeltaPowDistance(other)) <= 0.99*(this.getRadius()+other.getRadius()));
	}
	
	
	/**
	 * A method that calculates the distance in x- and y-direction between the 
	 * centres of the round entities.
	 * 
	 * @param 	other
	 * 			The second round entity to check the distance between.
	 * 
	 * @post 	The deltaDistance will be a list of the difference between 
	 * 			the 2 centres of the round entities.
	 * 			| deltaDistance =  {other.getxPosition()-this.getxPosition(),
	 *			| other.getyPosition()-this.getyPosition()}
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The entities may not ever collide.
	 * 			| !canAsCollision(other) 
	 */
	@Raw
	@Immutable
	private double [] getDeltaDistance(RoundEntity other){
		double [] deltaDistance = {other.getxPosition()-this.getxPosition(),
				other.getyPosition()-this.getyPosition()};
		return deltaDistance;
	}
	

	/**
	 * A method that calculates the difference in velocity in x- and y-direction 
	 * between two round entities.
	 * 
	 * @param 	other
	 * 			The second round entity to check the difference in velocity between.
	 * 
	 * @post 	The deltaVelocity will be a double[] of the difference in 
	 * 			velocity between the 2 round entities.
	 * 			| deltaVelocity = {other.getxVelocity()-this.getxVelocity(),
	 *			| other.getyVelocity()-this.getyVelocity()};
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The entities may not ever collide.
	 * 			| !canAsCollision(other) 
	 */	
	@Raw
	@Immutable
	private double [] getDeltaVelocity(RoundEntity other){
		double [] deltaVelocity = {other.getxVelocity()-this.getxVelocity(),
				other.getyVelocity()-this.getyVelocity()};
		return deltaVelocity;
	}
	
	/**
	 * A method that calculates the square of the difference in position between 
	 * the centres of the two round entities.
	 * 
	 * @param 	other
	 * 			The second round entity to check the square of the difference in position between.
	 * 
	 * @post	The deltaPowDistance will be the sum of the squares of the x- and y- in 
	 * 			distance between the centres of the round entities.
	 * 			| deltaPowDistance = Math.pow(getDeltaDistance(other)[0],2)+
	 *			|	Math.pow(getDeltaDistance(other)[1],2);
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The entities may not ever collide. 
	 * 			| !canAsCollision(other)
	 */	
	@Raw
	@Immutable
	private double getDeltaPowDistance(RoundEntity other){
		double deltaPowDistance = Math.pow(this.getDeltaDistance(other)[0],2)+
					Math.pow(this.getDeltaDistance(other)[1],2);
		return deltaPowDistance;
	}

	/**
	 * A method that calculates the square of the difference in velocity 
	 * between two round entities.
	 * 
	 * @param 	other
	 * 			The second round entity to check the square of the difference in velocity between.
	 * 
	 * @post	The deltaPowVelocity will be the sum of the squares of the x- and 
	 * 			y- difference in velocity between the round entities.
	 * 			| double deltaPowVelocity = Math.pow(getDeltaVelocity(other)[0], 2)+
	 *			|	Math.pow(getDeltaVelocity(other)[1], 2);
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The entities may not ever collide.
	 * 			| !canAsCollision(other)
	 */	
	@Raw
	@Immutable
	private double getDeltaPowVelocity(RoundEntity other){
		double deltaPowVelocity = Math.pow(this.getDeltaVelocity(other)[0], 2)+
					Math.pow(this.getDeltaVelocity(other)[1], 2);
		return deltaPowVelocity;
	}
	
	/**
	 * A method that calculates the scalarProduct of the difference 
	 * in position and velocity between two round entities.
	 * 
	 * @param 	other
	 * 			The second round entity to check the scalarProduct of the difference 
	 * 			in position and velocity between.
	 * 
	 * @post 	The deltaDistanceVelocity will be the sum of the product of x- and 
	 * 			y-distance and difference in velocity of the two round entities.
	 * 			| deltaDistanceVelocity = (getDeltaVelocity(other)[0]*getDeltaDistance(other)[0])+
	 *			|	(getDeltaVelocity(other)[1]*getDeltaDistance(other)[1]);
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The entities may not ever collide.
	 * 			| !canAsCollision(other)
	 */		
	@Raw
	@Immutable
	private double getDeltaDistanceVelocity(RoundEntity other){
		double deltaDistanceVelocity = (this.getDeltaVelocity(other)[0]*this.getDeltaDistance(other)[0])+
						(this.getDeltaVelocity(other)[1]*this.getDeltaDistance(other)[1]);
		return deltaDistanceVelocity;
	}
	
	/**
	 * A method that calculates d between two round entities. 
	 * D is a value described by a mathematical formula,
	 * as seen in the body of this function. It is a value needed in other functions.
	 * 
	 * @param 	other
	 * 			A second round entity with which you want to calculate d with.
	 * 
	 * @post 	The d will be the square of the deltaDistanceVelocitylowered by the 
	 * 			product of the deltaPowVelocity with the difference between 
	 * 			deltaPowVelocity and the distance between the two round entities.
	 * 			| d = Math.pow(getDeltaDistanceVelocity(other),2)-
	 *			|	(getDeltaPowVelocity(other))*(getDeltaPowDistance(other)-this.getDistanceBetween());
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The entities may not ever collide.
	 * 			| !canAsCollision(other)
	 */	
	@Raw
	@Immutable
	private double getD(RoundEntity other){
		double d = Math.pow(this.getDeltaDistanceVelocity(other),2)-
					(this.getDeltaPowVelocity(other))*(this.getDeltaPowDistance(other)
													-Math.pow((this.getRadius()+other.getRadius()),2));
		return d;
	}
	
	/**
	 * a method to check whether two entities may collide
	 * @param 	entity
	 * 			The entity with which we could collide
	 * @return	True if and only if the 2 entities exist and are in the some world
	 * 			|!entity.isTerminated() &&
	 * 			| !this.isTerminated()
	 */
	public boolean canAsCollision(RoundEntity entity){
		return (entity != null && !entity.isTerminated() && !this.isTerminated());
	}
	// && entity.getSpace() == this.getSpace() uit if statement!!
	
	/**
	 * A method to check if 2 entities at their given position significantly collide at this moment.
	 * 
	 * @param 	other
	 * 			The other entity with which we are colliding
	 * 
	 * @return	true if and only if the given and other entity have a world that is equal and
	 * 			the distance between the centres of the given and other entity is between 99% and 101%
	 * 			of the sum of the objects’ radii.
	 */
	protected boolean currentlyCollide(RoundEntity other){
		return( this.canAsCollision(other) && this.inSameSpace(other) &&
				0.99*(this.getRadius()+other.getRadius()) <= this.getDeltaPowDistance(other) && 
				this.getDeltaPowDistance(other) <= 1.01 *(this.getRadius()+other.getRadius()));
	}
	
	/**
	 * A method that gives the time between a collision of 2 round entities. 
	 * This method does not apply on two round entities that overlap.
	 * 
	 * @param 	other
	 * 			A second round entity to check if this round entity collides with.
	 * 
	 * @return	Returns the time until collision with the other round entity.
	 * 			| result == -(getDeltaDistanceVelocity(other)+Math.sqrt(getD(other)))/getDeltaPowVelocity(other)
	 * 
	 * @return 	The time will be positive infinity if the round entities will never collide.
	 * 			| result == Double.POSITIVE_INFINITY
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The entities may not ever collide.
	 * 			| !canAsCollision(other)
	 */
	@Raw
	@Immutable
	public double getTimeToCollision(RoundEntity other) 
			throws IllegalArgumentException{
		if (!this.canAsCollision(other) || !this.inSameSpace(other))
			throw new IllegalArgumentException();
		if (this.getDeltaDistanceVelocity(other) >= 0 || getD(other) <= 0)
			return Double.POSITIVE_INFINITY;

		
		return -(getDeltaDistanceVelocity(other)+Math.sqrt(getD(other)))/getDeltaPowVelocity(other);
		// d will be negative if the ships overlap
	}
	
	/**
	 * Get the point of collision, if two round entities will ever collide. 
	 * 
	 * @param 	other
	 * 			A second round entity to calculate the collision position with.
	 * 
	 * @post 	The collisionPoint will be the point where the two round entities hit.
	 * 			| collisionPoint = this.getPositionAfterMoving(time)+
	 *			|					this.getRadius()*getDeltaDistance(other)/getD(other)
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The ships may not ever collide.
	 * 			| !canAsCollision(other)
	 * 
	 */	
	@Raw
	@Immutable
	public double [] getCollisionPosition(RoundEntity other) 
			throws IllegalArgumentException{
		if (!canAsCollision(other) 
				|| this.getTimeToCollision(other) == Double.POSITIVE_INFINITY 
				|| this.getSpace().getTimeNextCollision() != this.getTimeToCollision(other))
			throw new IllegalArgumentException();
		try{
		double dt = this.getTimeToCollision(other);
		double [] newDeltaDistance = 	{other.getPositionAfterMoving(dt)[0]-this.getPositionAfterMoving(dt)[0],
										other.getPositionAfterMoving(dt)[1]-this.getPositionAfterMoving(dt)[1]}; 
		double [] collisionPoint = {this.getPositionAfterMoving(dt)[0]+
									this.getRadius()*newDeltaDistance[0]/
									(this.getRadius()+other.getRadius()),
									this.getPositionAfterMoving(dt)[1]+
									this.getRadius()*newDeltaDistance[1]/
									(this.getRadius()+other.getRadius())};
		// position of hit = 	position of entity after moving till contact+
		//						(difference in centra qua x-and y direction*
		// 						radius of the first eentity/distance two centres)
		return collisionPoint;
		} catch (IllegalArgumentException noCollision) {
			return null;
		}
	}
	
	/**
	 * Get the time for an entity to hit the wall of its world
	 * 
	 * @return	Double.POSITIVE_INFINITY if and only if the entity is terminated or will never hit the wall
	 * 			| this.isTerminated() || (this.getyVelocity() == 0 && this.getyVelocity() == 0)
	 * @return 	zero if and only if the entity already hits a wall 
	 * 			| @see implemantation
	 * @return	the smallest possible time of hit with a wall
	 * 			| @see implementation
	 */	
	public double getTimeToHitWall(){
		if(this.isTerminated())
			return Double.POSITIVE_INFINITY;
		if (this.getxPosition() + this.getRadius() == this.getSpace().getWidth() ||
			this.getxPosition() - this.getRadius() == 0 ||
			this.getyPosition() + this.getRadius() == this.getSpace().getHeight() ||
			this.getyPosition() - this.getRadius() == 0)
			return 0;
		
		double timeToHitYWall=Double.POSITIVE_INFINITY;
		double timeToHitXWall=Double.POSITIVE_INFINITY;
		if (this.getyVelocity() > 0)
			timeToHitYWall = (space.getHeight()-this.getyPosition()+this.getRadius())/this.getyVelocity();
		else if (this.getyVelocity() < 0)
			timeToHitYWall = (-this.getyPosition()+this.getRadius())/this.getyVelocity();
		else
			timeToHitYWall = Double.POSITIVE_INFINITY;
		if (this.getxVelocity() > 0)
			timeToHitXWall = (space.getWidth()-this.getxPosition()+this.getRadius())/this.getxVelocity();
		else if (this.getxVelocity() < 0)
			timeToHitXWall = (-this.getxPosition()+this.getRadius())/this.getxVelocity();
		else
			timeToHitXWall = Double.POSITIVE_INFINITY;
		
		return Math.min(timeToHitXWall, timeToHitYWall);
	}
	
	
	/**
	 * A method to check where an entity hits a wall.
	 *
	 * @return	Positive_Infinity if the entity is terminated
	 * 			|this.isTerminated()
	 * 			
	 * 			Otherwise, return the position of hiet with the wall
	 * 			| @see implementation
	 * The nb of hits with the wall will be countered 
	 */
	public double [] getPositionOfHitWall(){
		if (this.isTerminated()){
			double [] coordinates = {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY};
			return coordinates;
			}
		if (this.getTimeToHitWall() != this.getSpace().getTimeNextCollision())
			return null;	
		if (this instanceof Bullet)
			((Bullet)this).setNbWallHits( ((Bullet)this).getNbWallHits() + 1);
		
		if (this.getPositionAfterMoving(this.getTimeToHitWall())[0]+this.getRadius() == space.getWidth()){
			return new double[] {this.getPositionAfterMoving(this.getTimeToHitWall())[0]+this.getRadius(),
								this.getPositionAfterMoving(this.getTimeToHitWall())[1]};		
		}
		else if (this.getPositionAfterMoving(this.getTimeToHitWall())[0]-this.getRadius() == 0)	
			return new double[] {this.getPositionAfterMoving(this.getTimeToHitWall())[0]-this.getRadius(),
							  this.getPositionAfterMoving(this.getTimeToHitWall())[1]};
		else if (this.getPositionAfterMoving(this.getTimeToHitWall())[1] + this.getRadius() == space.getHeight())
			return new double[] {this.getPositionAfterMoving(this.getTimeToHitWall())[0],
							  this.getPositionAfterMoving(this.getTimeToHitWall())[1]+this.getRadius()};
		else
			return new double[] {this.getPositionAfterMoving(this.getTimeToHitWall())[0],
					this.getPositionAfterMoving(this.getTimeToHitWall())[1]-this.getRadius()};
		
		}

	
	/**
	 * Checks if the entity has hit a wall.
	 *
	 * @return	True if and only if this entity hits a wall in normal time
	 * 			or the time to hit a wall is smaller than the tim eto hit an other entity.
	 * 			| other.isTerminated() && this.getTimeToHitWall()!=Double.POSITIVE_INFINITY
	 * 			| this.getTimeToHitWall() <= this.getTimeToCollision(other)
	 * 			
	 */
	public boolean hasHitWall(){
		if (!this.isTerminated() && this.hasSpace() && this.getTimeToHitWall() != Double.POSITIVE_INFINITY)
			return true;
		for (RoundEntity other : space.getEntities())
			if (this.getTimeToHitWall() <= this.getTimeToCollision(other))
				return true;
		return false;
	}
	
	/**
	 * A method that changes an entity its velocity after he hit a wall.
	 * If the entity is a bullet, the nb of times the entity has hit a wall will be changed to his previous, updated with 1
	 * @throws 	IllegalArgumentException
	 * 			If the given entity is terminated.
	 * 			| this.isTerminated() || this.hasSpace()
	 * @effect	If the entity is a bullet that has hit the wall as many times as the maximum number of hits, 
	 * 			the bullet will be terminated.
	 * 			| this.terminate()
	 * @effect	If the entity hits a vertical boundary, its x-velocity will be negated.
	 * 			| this.setVelocity(-this.getxVelocity(), this.getyVelocity());
	 * @effect 	If the entity hits a horizontal boundary, its y-velocity will be negated.
	 * 			|this.setVelocity(this.getxVelocity(), -this.getyVelocity());
	 */
	
	public void setVelocityAfterEntityHitWall() throws IllegalArgumentException{
		double[] positionOfHitWall = null;
		if (this.isTerminated() || !this.hasWorld())
			throw new IllegalArgumentException();
		else if (this.hasHitWall() && this instanceof Bullet && ((Bullet)this).getNbWallHits() >= ((Bullet)this).getMaxNbWallHits())
			this.terminate();
		else
			positionOfHitWall = this.getPositionOfHitWall();
			if (positionOfHitWall[0] == 0 || positionOfHitWall[0] == this.getSpace().getWidth())
				this.setVelocity(-this.getxVelocity(), this.getyVelocity());
			if (positionOfHitWall[1] == 0 || positionOfHitWall[1] == this.getSpace().getHeight()){
				this.setVelocity(this.getxVelocity(), -this.getyVelocity());
			if (this instanceof Bullet)
				((Bullet)this).setNbWallHits(((Bullet)this).getNbWallHits()+1);
		}
	}
	
	
	/**
	 * A method that changes the ships or planetoids velocity, if they hit and are both in the same world.
	 * @param 	other
	 * 			The other ship or planetoid with which we want to check the given ship or planetoid collides with.
	 * @throws 	IllegalArgumentException
	 * 			If the given or the other entity is terminated or not a the same kind of entity,
	 * 			or the 2 entities are not in the same world.
	 * 			| @see implementation
	 * @effect 	both the velocities will be changed
	 * 			| @see implementation
	 */
	
	// nog minorPlanet in documentatie
	public void setVelocityAfterBounce(RoundEntity other) throws IllegalArgumentException{
		if (this.isTerminated() || other.isTerminated() || this.getSpace() != other.getSpace())
			throw new IllegalArgumentException();
		else if ( this instanceof Ship && other instanceof Ship ||
			 this instanceof MinorPlanet && other instanceof MinorPlanet){
			double J;
			J = 2*other.getMass()*this.getMass()*this.getDeltaDistanceVelocity(other)/
					( (this.getRadius()+other.getRadius())*(this.getMass()+other.getMass()));
			
			double JX = J*this.getDeltaDistance(other)[0]/(this.getRadius()+other.getRadius());
			double JY = J*this.getDeltaDistance(other)[1]/(this.getRadius()+other.getRadius());
			
			double [] VelocityThis = {this.getVelocity()[0]+JX/this.getMass(),
							this.getVelocity()[1]+JY/this.getMass()};
			double [] VelocityOther = {other.getVelocity()[0]-JX/other.getMass(),
					other.getVelocity()[1]-JY/other.getMass()};
			this.setVelocity(VelocityThis[0], VelocityThis[1]);
			other.setVelocity(VelocityOther[0], VelocityOther[1]);
		}
		else
			throw new IllegalArgumentException();
	}

	
	protected void resolveCollision() throws IllegalArgumentException{
		if (this.isTerminated() || this == null)
			throw new IllegalArgumentException();
		else if (this.hasHitWall())
			this.setVelocityAfterEntityHitWall();
	}

	protected abstract void resolveCollision(RoundEntity other);
		
}
