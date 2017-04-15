package asteroids.model;

import java.util.*;

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
		
		UnboundSpace unboundspace = UnboundSpace();
		this.placeInSpace(unboundspace);
		// Bullets need to be associated with an unbound space until associated with a world or ship.
	}
	// Wait for definition of uboundspace constructor for width and height.

//	
//	/**
//	 * Create a new bullet with a default position, velocity, radius.
//	 * 
//	 * @post  	The position of this new ship is (0,0).
//	 *        
//	 * @post  	The velocity of this new ship is (0,0).
//	 *        
//	 * @post  	The radius of this new ship is getMinRadius.
//	 *        
//	 * @effect 	The result is a circle with radius getMinRadius() 
//	 * 			centered on (0, 0) with speed zero.
//	 */
//	public Bullet(){
//		this(0,0,0,0,getMinRadius());
//	}
//	//DEFAULT LOCATIE 0,0?? ----> Wordt nergens gebruikt in facade
	
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
	}
	// AANNAME DAT MASS ALTIJD KLEINER ZAL ZIJN DAN DOUBLE.MAX_VALUE

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
		if (this.hasShip()){
			this.removeOutShip();
			this.setShip(ship);
			ship.addBullet(this);
		}
		else {
			this.setShip(ship);
			ship.addBullet(this);	
		}
		// Else statement can only be reached in constructor, when bullet has no ship nor space yet.
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
	private void removeOutShip(){
		if (this.hasShip()){
			this.getShip().removeBullet(this);
			this.ship = null;
			// Can not use setShip() because it does not allow to set a ship to null.	
		}		
	}
	//ZOMAAR REMOVEN MAG NIET, ELKE ENTITY MOET ZICH ERGENS BEVINDEN.
	//ENKEL NODIG WANNEER EEN ROUND ENTITY VOLLEDIG VERWIJDERD WORDT, WANNEER DEZE HERPLAATST WORDT
	//OF NET WORDT AANGEMAAKT.
	
	// When dealing with 'death', use terminate to destroy a bullet or ship
	// Use placeInSpace to relocate it to an unbound space
	// NEVER USE REMOVEOUTSPACE!!! Then round entity has no place, which may never happen.
	
	//BULLETS BEWEEGDEN MEE MET SHIP, GEEF TERUG EIGEN SNELHEID
		
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
	 */
	@Override
	public void placeInSpace(Space space) throws IllegalArgumentException {
		if ((!canHaveAsSpace(space)))
			throw new IllegalArgumentException();
		if (this.hasSpace()){
			this.removeOutSpace();
			this.setSpace(space);
			space.addEntity(this);
		}
		if (this.hasShip()){
			this.removeOutShip();
			this.setSpace(space);
			space.addEntity(this);
		}
		else {
			this.setSpace(space);
			space.addEntity(this);	
		}
		// Else statement can only be reached in constructor, when bullet has no ship nor space yet.
	}
	
	/**
	 * Variable registering the ship this round entity is placed in.
	 */
	private Ship ship = null;
	
	/**
	 * Return the ship that fired this bullet, if any.
	 * @return
	 */
	public Ship returnSource(){
		return this.getShip();
		//IMPLEMENTATIE
	}
}