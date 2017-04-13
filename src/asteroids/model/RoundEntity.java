package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of round things that are defined by a position, velocity and radius.
 * It also has a mass, density, and can be placed in a space.
 * 
 * @invar 	Each ship can have its position, determined by x- and y-coordinate, as position.
 * 			| canHaveAsPosition(this.getPosition())
 *        
 * @invar 	Each ship can have its velocity, determined by x- and y-coordinate, as velocity.
 * 			| canHaveAsVelocity(this.getVelocity)
 *        
 * @invar  	Each ship can have its radius as radius.
 *        	| canHaveAsRadius(this.getRadius())
 * 
 * @author amber_000
 */
@Value
public abstract class RoundEntity {
	
	
	/**
	 * Initialize this new round entity with given parameters.
	 *
	 * @param x
	 * 
	 * @param y
	 * 
	 * @param xVelocity
	 * 
	 * @param yVelocity
	 * 
	 * @param radius
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
	 */
	protected RoundEntity(double x, double y, double xVelocity, double yVelocity, double radius) 
			throws IllegalArgumentException {
		this.setPosition(x,y);
		if (!canHaveAsVelocity(xVelocity, yVelocity)){
			this.xVelocity = 0;
			this.yVelocity = 0;
		}
		this.setVelocity(xVelocity, yVelocity);
		if (!canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
	}
	
//	/**
//	 * Create a new round entity with a default position, velocity and radius. 
//	 * 
//	 * @post  	The position of this new ship is (0,0).
//	 *        	| new.getPosition() == {0,0}
//	 *        
//	 * @post  	The velocity of this new ship is (0,0).
//	 *        	| new.getVelocity() == {0,0}
//	 *        
//	 * @post  	The radius of this new ship is getMinRadius.
//	 *        	| new.getRadius() == getMinRadius()
//	 *        
//	 * @effect 	The result is a circle with radius getMinRadius() centered on (0, 0) with no speed.
//	 */
//	protected RoundEntity() throws IllegalArgumentException {
//		this(0,0,0,0,getMinRadius());
//	}
	
	/**
	 * Terminate this round entity.
	 *
	 * @post   This round entity is terminated.
	 *       | new.isTerminated()
	 */
	public void terminate() {
		// SET ROUND ENTITY FREE OF ITS LOCATION IT IS IN
		// IN DOCUMENTATIE: @effect this round entity is freed from its space its in
		// 					| this.setapart()
		this.isTerminated = true;
	}

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
	 * @return	Returns true if and only if the xPostion and yPosition are numbers.
	 * 			| result == !Double.isNaN(xPosition) && !Double.isNaN(yPosition)
	 */
	@Raw
	@Immutable
	public static boolean canHaveAsPosition(double x, double y){
		return !Double.isNaN(x) && !Double.isNaN(y);
		
		// POSTION FOR ROUND ENTITIES IS RESTRICTED BY THE AREA THEY'RE IN:
		// EITHER AN UNBOUND SPACE (NO RESTRICTIONS), A WORLD (BOUNDARIES)
		// OR FOR A BULLET ALSO A SHIP (BOUNDARIES)
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
		return !Double.isNaN(xVelocity) && !Double.isNaN(yVelocity);
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
	 * Return the highest possible value for the speed of round entities, always
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
	// Depends on the round entity it is used on.
	// Ships have maxspeed = speed of light, this is fixed for all ships.
	// This fixedness and also the numerical value may vary throughout development.
	// (but will never exceed the speed of light.)
	// Bullets have maxspeed = speed of light, this is fixed for all bullets. 
	// This fixedness and also the numerical value may vary throughout development.
	// (but will never exceed the speed of light.)
	//-> can always override so perfect like this
	
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
	protected static int ABSOLUTE_MAX_SPEED = 300000;	
	
	/**
	 * Return the radius of this round entity.
	 */
	@Basic
	@Raw
	@Immutable
	public double getRadius() {
		//KAN GEEN CLONE UITVOEREN OP EEN DOUBLE??
		return this.radius;
	}

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
	public static boolean canHaveAsRadius(double radius) {
		return (!Double.isNaN(radius) && radius >= getMinRadius());
	}
	// PROBLEEM MET GEBRUIK VAN GETMINRADIUS HIER OMDAT DEZE NIET ALS STATIC STAAT AANGEDUID
	// STATIC LUKT NIET BIJ GETMINRADIUS OMDAT DEZE ABSTRACT IS
	
	/**
	 * Return the lowest possible value for the radius of this round enity.
	 */
	@Basic
	@Raw
	public abstract double getMinRadius();
	// Depends on the round entity it is used on.
	// Ships have minradius = 10, bullets = 1; 
	// This minradius is fixed for all ships, and all bullets.
	// throughout the development this number can change.
	
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
	// Depends on the round entity it is used on.
	// Bullets have fixed density 7,8*10^12, for all bullets the same. Not mentioned if this number
	// can change throughout the development.
	// Ships have at least density 1,42*10^12, the actual density can vary for ships.
	// Not mentioned if this number can change throughout development.
	
	/**
	 * Return the mass of this round entity.
	 */
	@Basic
	@Raw
	public abstract double getMass();
	// Depends on the round entity it is used on.
	// Bullets have fixed mass (derived from density, and always smaller then double.MAX_VALUE)
	// for all bullets the same. Not mentioned if this number can change throughout the development.
	// Ships have a varying mass (derived from the density, and always smaller then double.MAX_VALUE), 
	// the actual mass can vary for ships because the mass of the bullets they're carrying is also added. 
	// Not mentioned if this number can change throughout development.
	
	/**
	 * Return the space where this round entity is placed in.
	 * A round entity always needs to be placed in a space, and can't therefore be null.
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
	 * Register the given space as the space where this round entity is placed in.
	 * 
	 * @param 	space
	 * 			| The given space to set the round entity in.
	 * 		
	 * @throws 	IllegalArgumentException
	 * 			The given space is not valid.
	 * 			| !canHaveAsSpace(space)
	 */
	private void setSpace(Space space) throws IllegalArgumentException{
		if (!canHaveAsSpace(space))
			throw new IllegalArgumentException();
		this.space = space;
	}
	
	/**
	 * 
	 * @param 	space
	 * 			| The given space to check if this round entity can be placed in.
	 * 
	 * @return 	False if the given space is not effective or null, or this round entity is terminated.
	 *       	| if (space == null || (space.isTerminated()) || (this.isTerminated()))
	 *       	|   then result == false
	 *       
	 * @return	True if all the above isn't true.
	 * 			| else
	 * 				result == true
	 */
	public boolean canHaveAsSpace(Space space){
		if (space == null || (space.isTerminated()) || (this.isTerminated()))
			return false;
		else
			return true;
	}
	// Terminate functies voor SPACE moeten bestaan
	// OPM: import statement nodig om functies van Space te gebruiken?
	
	/**
	 * Check whether this round entity has a proper space.
	 * 
	 * @return True if and only if this round entity can have its space
	 *         as its space, and if that space, if it is effective, in turn references
	 *         this round entity.
	 *       | result ==
	 *       |   canHaveAsSpace(this.getSpace()) && (this.getSpace().getEntity() == this)
	 *
	 * @return
	 */
	public boolean hasProperSpace() {
		return canHaveAsSpace(this.getSpace()) && (this.getSpace().getEntity() == this);
	}
	// equivalent in Space: checkers en setters voor meervoudigheid
	// bv ship of bullet mag niet al ergens anders in zitten
	
	/**
	 * Check whether this round entity has a space.
	 *
	 * @return 	True if this person references an effective space,
	 *         	false otherwise.
	 *       	| result == (this.getSpace != null)
	 */
	public boolean hasSpace(){
		return (this.getSpace != null);
	}
	// 	NEED FOR HASSPACE FUNCTION BECAUSE ENTITY MIGHT NOT HAVE A SPACE YET WHEN CREATED
	
	/**
	 * 
	 * @param space
	 * @throws IllegalArgumentException
	 */
	public void PlaceInSpace(Space space) throws IllegalArgumentException {
		if ((!canHaveAsSpace(space)))
			throw new IllegalArgumentException();
		if (this.hasSpace()){
			this.RemoveOutSpace();
			this.setSpace(space);
			space.setEntity(this);
		}
		else {
			this.setSpace(space);
			space.setEntity(this);	
		}
	}
	// SPACE moet een functie 'setEntity' hebben.
	// used in subclasses in constructor to place in space
	
	/**
	 * Remove this round entity from the space it's in, if any.
	 * 
	 * @return 	The round entity will not be placed in any space.
	 * 			| !new.hasSpace()
	 * 
	 * @post	The former space of this round entity, if any, is no longer
	 *		   	its space.
	 *       	| if (this.hasSpace())
	 *       	|   then ! (new (this.getSpace())).hasEntity(this)) 
	 */
	private void RemoveOutSpace(){
		// If statement in principle not necessary, because RemoveOutSpace() is only used when
		// sure the round entity has a space (no boundary case).
		if (this.hasSpace()){
			this.getSpace().RemoveEntity();
			this.space = null;
			// Can not use 'setSpace' because it does not allow to set a space to null.	
		}		
	}
	//ZOMAAR REMOVEN MAG NIET, ELKE ENTITY MOET ZICH ERGENS BEVINDEN.
	//ENKEL NODIG WANNEER EEN ROUND ENTITY VOLLEDIG VERWIJDERD WORDT, WANNEER DEZE HERPLAATST WORDT
	//OF NET WORDT AANGEMAAKT.
	//SPACE MOET EEN FUNCTIE 'RemoveEntity' HEBBEN
	//SPACE MOET EEN FUNCTIE 'hasEntity' HEBBEN
	
	/**
	 * Variable registering the space this round entity is placed in.
	 */
	protected Space space = null;
	// SPACE ALS SUPER KLASSE -> WORLD / UNBOUND SPACE
	// ELKE BULLET EN SHIP MOETEN IN 1 EN SLECHTS 1 SPACE ZITTEN;
	// TENZIJ: BULLETS KUNNEN OOK IN SHIPS ZITTEN (MEERVOUDIGE ASSOCIATION VOOR SHIP)
	// !NOOIT IN SPACES OF SHIPS TEZAMEN ZITTEN
	// als default is de space null, maar moet in elke constructor bij de subklasses meteen
	// ingesteld worden.	
}
