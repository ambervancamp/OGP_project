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
	 * @effect	This ship is placed in a new unbound space.	
	 */
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		
		UnboundSpace unboundspace = UnboundSpace(width, height);
		this.placeInSpace(unboundspace);
		// Ships need to be associated with an unbound space until associated with a world.
		// Wait for definition of uboundspace constructor for width and height.
	}
	
	/**
	 * Create a new bullet with a default position, velocity, radius.
	 * 
	 * @post  	The position of this new ship is (0,0).
	 *        
	 * @post  	The velocity of this new ship is (0,0).
	 *        
	 * @post  	The radius of this new ship is getMinRadius.
	 *        
	 * @effect 	The result is a circle with radius getMinRadius() 
	 * 			centered on (0, 0) with speed zero.
	 */
	public Bullet(){
		this(0,0,0,0,getMinRadius());
	}
	//DEFAULT LOCATIE 0,0??
	
	/**
	 * Return the lowest possible value for the radius of all bullets, in km.
	 * 
	 * @return	Returns the minimum radius, which equals to 1
	 */
	@Override
	@Basic
	@Raw
	public static double getMinRadius() {
		return 1;
	}
	//OVERRIDE PROBLEEM MET STATIC GETMINRADIUS
	
	/**
	 * Return the density for all bullets.
	 * 
	 * @return	Returns the density, which equals to 7.8*Math.pow(10,12)
	 */
	@Override
	public double getDensity() {
		return 7.8*Math.pow(10,12);
	}
	//eigenlijk static methode, maar zelfde probleem als bij getminradius()
	
	
	/**
	 * Return the mass of this bullet computed by its radius and density.
	 * 
	 * @return	Returns the mass, which equals to 
	 * 			4/3*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity()
	 */
	@Override
	public double getMass() {
		return 4/3*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
		// AANNAME DAT MASS ALTIJD KLEINER ZAL ZIJN DAN DOUBLE.MAX_VALUE
	}
	
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
	private void setShip(Ship ship) throws IllegalArgumentException{
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
	 *       
	 * @return	True if all the above isn't true.
	 */
	public boolean canHaveAsShip(Ship ship){
		if (ship == null || (ship.isTerminated()) || (this.isTerminated()))
			return false;
		else
			return true;
	}
	
	/**
	 * Check whether this bullet has a proper ship.
	 * 
	 * @return 	True if and only if this bullet can have its ship
	 *         	as its ship, and if that ship, if it is effective, in turn references
	 *         	this bullet.
	 */
	public boolean hasProperShip() {
		return canHaveAsShip(this.getShip()) && (this.getShip().hasBullet(this));
	}
	// SHIP MUST HAVE A METHOD 'HASBULLET'
	
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
	 * Variable registering the ship this round entity is placed in.
	 */
	private Ship ship = null;
	
	/**
	 * Return the ship that fired this bullet, if any.
	 * @return
	 */
	public Ship returnSource(Ship ship){
		return null;
		//IMPLEMENTATIE
	}
}