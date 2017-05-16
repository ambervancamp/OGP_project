package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 *	A class to represent bullets as a special kind of round entities.
 *	Bullets have a position, velocity, radius, density and mass.
 * 
 * @invar  	The mass of each bullet must be a valid mass for any
 *         	bullet.
 *       	| canHaveAsMass(getMass())
 * 
 * @invar  	Each bullet can have its density as density .
 *       	| canHaveAsDensity(this.getDensity())
 *       
 * @invar  	Each bullet must have a proper space, or ship.
 *       	| hasProperSpace() || hasProperShip()
 * 
 * @invar  	The source of each bullet must be a valid source for any
 *        	 bullet.
 *       	| isValidSource(getSource())
 *        
 * @author amber_000
 *
 */
public class Bullet extends RoundEntity {
	
	/**
	 * Initialize this new bullet with the given parameters.
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
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.density = 7.8*Math.pow(10,12);
		this.mass = 4.0/3.0*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
		// No setter for density or mass because final variables.
	}
	
	/**
	 * Terminate this bullet.
	 *
	 * @post   	If this bullet isn't already terminated, it is terminated.
	 *       
	 * @effect	If this bullet is in a space, it is removed.
	 * 
	 * @effect	If this bullet is in a ship, it is removed.
	 */
	@Override
	public void terminate() {
		if (!this.isTerminated()){	
			this.removeOutSpace();
			this.removeOutShip();
			this.isTerminated = true;
		}
	}
	
	/**
	 * Return the lowest possible value for the radius of all bullets, in km.
	 * 
	 * @return	Returns the minimum radius, which equals to 1
	 */
	@Override
	@Basic
	@Raw
	public double getMinRadius() {
		return 1.0;
	}
	
	/**
	 * Return the density of this bullet.
	 * 
	 * @return	Returns the density of this bullet.
	 */
	@Override
	@Basic 
	@Raw 
	@Immutable
	public double getDensity() {
		return this.density;
	}
	
	/**
	 * Check whether this bullet can have the given density as its density.
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
	 * Variable registering the density of this bullet.
	 */
	private final double density;		
	
	/**
	 * Return the mass of this bullet computed by its radius and density.
	 * 
	 * @return	Returns the mass of this bullet.
	 */
	@Override
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Check whether the given mass is a valid mass for any bullet.
	 * 
	 * @param  	mass
	 *         	The mass to check.
	 *         
	 * @return 	The given mass must be a number.
	 */
	public boolean canHaveAsMass(double mass) {
		return !Double.isNaN(mass);
	}
		
	/**
	 * Variable registering the mass of this bullet.
	 */
	private double mass;

	/**
	 * Return the ship where this bullet is placed in.
	 * 
	 * @return	Returns the ship this round entity is placed in.
	 */
	@Basic
	@Raw
	public Ship getShip(){
		return this.ship;
	}
	
	/**
	 * Register the given ship as the ship where this bullet is placed in.
	 * 
	 * @param 	ship
	 * 			The given ship to set the bullet in.
	 * 		
	 * @throws 	IllegalArgumentException
	 * 			The given ship is not valid.
	 */
	void setShip(Ship ship) throws IllegalArgumentException{
		if (!canHaveAsShip(ship))
			throw new IllegalArgumentException();
		this.ship = ship;
	}
	
	/**
	 * Checks whether the given ship is a valid ship for any bullet.
	 * 
	 * @param 	ship
	 * 			The given ship to check if this bullet can be placed in.
	 * 
	 * @return 	False if the given ship is not effective or null, or this bullet is terminated.
	 */
	public boolean canHaveAsShip(Ship ship){
		return (ship != null || (!ship.isTerminated()) || (!this.isTerminated()));
	}
	
	/**
	 * Check whether this bullet has a proper ship.
	 * 
	 * @return 	True if and only if this bullet can have its ship
	 *         	as its ship, and if that ship, if it is effective, in turn references
	 *         	this bullet.
	 */
	public boolean hasProperShip() {
		return canHaveAsShip(this.getShip()) && (this.getShip().hasAsBullet(this));
	}
	
	/**
	 * Checks if this bullet is located in a ship.
	 */
	public boolean hasShip(){
		return (this.getShip()!=null);
	}
	
	/**
	 * Checks if this bullet is located in either a ship, or a space.
	 */
	public boolean isLocated(){
		return this.hasShip() || this.hasSpace();
	}
	
	/**
	 * Set ship from this bullet to given ship. If it's already in a ship, it's replaced to 
	 * the new ship. If it's already in a space, it's replaced to the new ship. 
	 * Also places bullet in given ship.
	 * 
	 * @param 	ship
	 * 			The given ship to put the bullet in.
	 * 
	 * @effect	The given ship will be the new ship of this round entity.
	 * 
	 * @post 	If the bullet is already placed in a space, first it will be removed from
	 * 			that space. Then it will set ship as its ship.
	 * 
	 * @post	If the bullet is already placed in a ship, first it will be removed from
	 * 			that ship. Then it will set ship as its ship.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the space is not valid.
	 */
	public void placeInShip(Ship ship) throws IllegalArgumentException {
		if ((!canHaveAsShip(ship)))
			throw new IllegalArgumentException();
		if (this.hasSpace()){
			this.removeOutSpace();
			this.setShip(ship);
			ship.addBullet(this);
		}
		else if (this.hasShip()){
			this.removeOutShip();
			this.setShip(ship);
			ship.addBullet(this);
		}
		else {
			this.setShip(ship);
			ship.addBullet(this);	
			// Else statement used in constructor
		}
	}
	//BULLETS MOETEN BINNEN GRENZEN VAN SHIP BEWEGEN!!!!
	
	/**
	 * Remove the ship from this bullet, if any. It is not placed into a new ship.
	 * The bullet is also removed from the ship.
	 * 
	 * @return 	The bullet will not be placed in any space.
	 * 
	 * @post	The former ship of this bullet, if any, is no longer
	 *		   	its ship.
	 */
	void removeOutShip(){
		if (this.hasShip()){
			Ship ship = this.getShip();
			this.ship = null;
			ship.removeBullet(this);
			// Can not use setShip() because it does not allow to set a ship to null.	
		}		
	}
	// If statement not necessary, because RemoveOutSpace() is only used when
	// sure the round entity has a space (no boundary case).	
	
	//BULLETS BEWOGEN MEE MET SHIP, GEEF TERUG EIGEN SNELHEID
	
	/**
	 * Remove this bullet from given ship, if it's placed in that ship. 
	 * The bullet is then replaced to a new unbound space.
	 * 
	 * @param	ship
	 * 			The given ship, to remove this bullet from.
	 * 
	 * @post 	The bullet will be placed in a new unbound space.
	 * 			| this.getSpace() == new.UnboundSpace()
	 * 
	 * @post	The former ship of this bullet, if it was the given ship, is no
	 * 			longer its ship.
	 *       	| if (this.getShip() == ship)
	 *       	|   then ! (new (this.getShip())).hasAsBullet(this) 
	 */
	public void removeBulletFromShip(Ship ship){
		if (this.getShip() == ship){
			UnboundSpace unboundspace = new UnboundSpace();
			this.placeInSpace(unboundspace);
		}
	}
		
	/**
	 * Set space from this bullet to given space. If the bullet is already in a space, it's replaced to 
	 * the new space. If the bullet is already in a ship, it's replaced to the new space.
	 * Also places bullet in given space.
	 * 
	 * @param 	space
	 * 			The given space to put the bullet in.
	 * 
	 * @effect	The given space will be the new space of this bullet.
	 * 
	 * @post 	If the bullet is already placed in a space, first it will be removed from
	 * 			that space. Then it will set space as its space.
	 * 
	 * @post	If the bullet is already placed in a ship, first it will be removed from 
	 * 			that ship. Then it will set space as its space.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If the space is not valid.
	 * 
	 * @throws	IllegalArgumentException.
	 * 			If this entity overlaps with any entity already in the space.
	 *
	 *@throws	IllegalArgumentException.
	 *			If this entity overlaps the boundaries of the given space.
	 */
	@Override
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
		else if (this.hasShip()){
			this.removeOutShip();
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
	 * Variable registering the ship this round entity is placed in.
	 */
	private Ship ship = null;

	/**
	 * Return the number of times a bullet has hit the wall.
	 * 
	 * @return	The number of times a bullet has hit the wall.
	 */
	public double getNbWallHits(){
		return this.nbWallHits;
	}
	
	/**
	 * Sets the number of wall hits at the given value.
	 * 
	 * @param 	value
	 * 			The given number of times a bullet has hit the wall.
	 */
	public void setNbWallHits(double value){
		 this.nbWallHits = value;
	}
	
	/**
	 * Return the maximum number of times a bullet can  hit the wall.
	 * 
	 * @return	The maximum number of times a bullet may hit the wall.
	 */
	public double getMaxNbWallHits(){
		return 2;
	}
	
	/**
	 * A variable registering the number off times a bullet has hit the wall.
	 */
	double nbWallHits = 0;	
	
	
	/**
	 * Return the source of this bullet.
	 */
	@Basic @Raw
	public Ship getSource() {
		return this.source;
	}
	
	/**
	 * Check whether the given source is a valid source for
	 * any bullet.
	 *  
	 * @param  	source
	 *         	The source to check.
	 *         
	 * @return 	The source must be a Ship, that is not yet terminated.
	 *       	| result == source!=null && (source instanceof Ship) && !source.isTerminated()
	*/
	public static boolean isValidSource(Ship source) {
		return source!=null && (source instanceof Ship) && !source.isTerminated();
	}
	
	/**
	 * Set the source of this bullet to the given source.
	 * 
	 * @param  	source
	 *         	The new source for this bullet.
	 *         
	 * @post   	The source of this new bullet is equal to
	 *         	the given source.
	 *       	| new.getSource() == source
	 *       
	 * @throws 	IllegalArgumentException
	 *         	The given source is not a valid source for any
	 *         	bullet.
	 *       	| ! isValidSource(getSource())
	 */
	@Raw
	public void setSource(Ship source) 
			throws IllegalArgumentException {
		if (! isValidSource(source))
			throw new IllegalArgumentException();
		this.source = source;
	}
	
	/**
	 * Variable registering the source of this bullet, the ship that fired this bullet.
	 */
	private Ship source = null;

}


