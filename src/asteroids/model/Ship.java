package asteroids.model;

import java.util.*;

import asteroids.model.Programs.*;
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
 * @invar	Each ship must have a proper space.
 * 			| hasProperSpace()
 * 
 *  
 * @invar	Each ship must have a proper bullets.
 * 			| hasProperBullets()
 *      
 */

public class Ship extends RoundEntity {
	/**
	 * Initialize this new ship with the given parameters, as a non-terminated ship with 
	 * no bullets yet.
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
	 *       	| if (canHaveAsDensity(density))
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
	 * @post   	This new ship has no bullets yet.
	 *       	| new.getNbBullets() == 0
     * 
	 */
	@Raw
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, 
			double mass) throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.setOrientation(orientation);
		if (!canHaveAsMass(mass)){
			mass = 4.0/3.0*Math.PI*Math.pow(this.getRadius(),3)*MIN_DENSITY;
		}
		this.setMass(mass);
		
		double density = this.getMass()/(4.0/3.0*Math.PI*Math.pow(this.getRadius(),3));
		if (!canHaveAsDensity(density))
			this.density = MIN_DENSITY;
		else
			this.density = density;
			// No setter for density because it is final variable.
	}
	
	
// All methods handling the termination of a ship.
	
	/**
	 * Terminate this round entity, its bullets if any and remove it out of its space.
	 *
	 * @post   	If this ship isn't already terminated, it is terminated.
	 *       	| if (!this.isTerminated())
	 *       	|	then new.isTerminated()
	 *       
	 * @effect	If this ship is in a space, it is removed.
	 * 			| if (this.hasSpace())
	 * 			| 	then this.removeOutSpace()
	 * 
	 * @effect	If this ship has bullets, then these bullets are terminated.
	 * 			| if (getNbBullets()!=0)
	 *			| @see implementation
	 */
	@Override
	public void terminate() {
		if (!this.isTerminated()){
			if (this.hasSpace()){
				this.removeOutSpace();
			}		
			
			if (getNbBullets()!=0){
				for (Bullet bullet: bullets){
					this.removeBullet(bullet);
					bullet.terminate();
				}}
			this.isTerminated = true;
		}
	}
	
//	All methods related to the orientation of the ship
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
	
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

//	All methods related to the radius of the ship
//	The other methods, for setting, getting etc. are defined in RoundEntity
	
	/**
	 * Return the lowest possible value for the radius of all ships.
	 *
	 * @return 	The lowest possible value for the radius of all ships is 10 kilometer.
	 *		 	| result == 10
	 */
	@Override
	@Basic
	@Raw
	public double getMinRadius() {
		return 10.0;
	}

//	All methods related to the density of a ship
	
	/**
	 * Variable registering the density of this ship.
	 */
	private final double density;

	/**
	 * Variable registering the minimum density of all ships.
	 */
	private final double MIN_DENSITY = 1.42*Math.pow(10,12);
	
	/**
	 * Check whether this ship can have the given density as its density.
	 *  
	 * @param  	density
	 *         	The density to check.
	 *         
	 * @return 	The density must be a number bigger than the MIN_DENSITY
	 *       	| result == !Double.isNaN(density) && density >= MIN_DENSITY
	*/
	@Raw
	public boolean canHaveAsDensity(double density) {
		return !Double.isNaN(density) && density >= MIN_DENSITY;
	}
	
	/**
	 * Return the density of this ship.
	 * 
	 * @return	the density of this ship
	 * 			| result == this.density
	 */
	@Override
	@Basic 
	@Raw 
	@Immutable
	public double getDensity() {
		return this.density;
	}
	
//	All methods related to the mass of a ship
	
	/**
	 * Variable registering the mass of this ship.
	 */
	private double mass;
	
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
		return !Double.isNaN(mass) && mass >= 4.0/3.0*Math.PI*Math.pow(this.getRadius(),3)*MIN_DENSITY
				&& mass < Double.MAX_VALUE;
	}	
	
	/**
	 * Set the mass of this ship to the given mass.
	 * 
	 * @param  	mass
	 *         	The new mass for this ship.
	 *         
	 * @post   	If the given mass is a valid mass for any ship,
	 *         	the mass of this new ship is equal to the given mass.
	 * 			| if (canHaveAsMass(mass))
	 *       	|   then new.getMass() == mass
	 */
	@Raw
	public void setMass(double mass) {
		if (canHaveAsMass(mass))
			this.mass = mass;
	}
	
	/**
	 * Return the mass of this ship. This is the mass of the ship itself and the mass of the bullets it is carrying.
	 * 
	 * @return 	The current mass of this ship in kg.
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
	 * 			| result == 4/3*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity(), in kg.
	 */
	@Basic 
	@Raw
	public double getShipMass() {
		return 4.0/3.0*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
	}
	
	/**
	 * Return the total mass of this ship itself, with all of its bullets, if any.
	 * 
	 * @return 	The total mass of this ship, in kg.
	 * 			| result == this.getShipMass()
	 */
	@Basic 
	@Raw
	public double getTotalMass(){
		double total_mass = this.getShipMass();
		
		for (Bullet bullet: bullets) {
			total_mass += bullet.getMass();
		}
		return total_mass;		
	}
	
//	All methods related to the movement of the ship.
	
	/**
	 * A method that turns the ship according to the given angle.
	 * 
	 * @param 	angle
	 * 			The angle to turn.
	 * 
	 * @pre 	The ship can have the total angle as a valid angle.
	 * 			| canHaveAsOrientation(angle+getOrientation())
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
	 * Check whether the given factor a is a valid acceleration factor a for any ship.
	 * 
	 * @param 	acceleration
	 * 			Factor that determines how much to accelerate a ship.
	 * 
	 * @return	Returns true if and only if acceleration is a number bigger than or equal to 0 and
	 * 			acceleration is a finit acceleration.
	 * 			| result == (acceleration>=0 && !Double.isNaN(acceleration)))
	 */
	@Raw
	public static boolean canHaveAsAcceleration(double acceleration){
		return (!Double.isNaN(acceleration) && acceleration>=0);
	}	
	
	/**
	 * Change the ships velocity based on the current velocity, 
	 * its orientation, a given acceleration and a duration.
	 * 
	 * @param 	acceleration
	 * 			Given factor to accelerate this ship.
	 * 
	 * @param	duration
	 * 			Given duration to calculate acceleration with.
	 * 
	 * @post	Acceleration and duration must be valid parameters, if not, the ship will not accelerate
	 * 			and keep its previous velocity.
	 * 			| if (!canHaveAsAcceleration(acceleration) || !canHaveAsAcceleration(acceleration))
	 *			|	then new.getVelocity = this.getVelocity;
	 *
	 *
	 * @post 	The ship will accelerate with a factor acceleration and keep its orientation.
	 * 			The new xVelocity and yVelocity are set as the xVelocity and yVelocity
	 * 			of this new ship.
	 * 			| setVelocity(this.getxVelocity() + acceleration*Math.cos(this.getOrientation()), 
	 * 			|			  this.getyVelocity() + acceleration*Math.sin(this.getOrientation()))
	 *
	 */
	@Raw
	@Immutable
	public void thrust(double acceleration, double duration) {
		if (!canHaveAsDuration(duration) || !canHaveAsAcceleration(acceleration))
			return;
		
		double xVelocity = this.getxVelocity() + acceleration*duration*Math.cos(this.getOrientation());
		double yVelocity = this.getyVelocity() + acceleration*duration*Math.sin(this.getOrientation());		
		this.setVelocity(xVelocity, yVelocity);
	}	
	
	/**
	 * Variable registering whether the thruster of this round entity is enabled or disabled.
	 */
	private boolean thruster = false;
		
	/**
	 * Return a boolean indicating whether or not this round entity's thruster is enabled.
	 * 
	 * @return 	Returns the status of the ship's thruster.
	 *         	| result == this.thruster
	 */
	public boolean isThrusterOn() {
		return this.thruster;
	}
	
	/**
	 * Turn on the thruster.
	 * 
	 * @post   The status of the thruster is now on.
	 *         | this.thruster = true
	 */
	public void thrustOn() {
		this.thruster = true;
	}

	/**
 	 * Turn off the thruster.	 
 	 *
 	 * @post   The status of the thruster is now off.
	 *         | thruster = false
	 */
	public void thrustOff() {
		this.thruster = false;
	}
	

	/**
	 * Get the driving force behind the acceleration of a ship.
	 * 
	 * @return	The default value of the driving force is 1.1*Math.pow(10, 21).
	 * 			| result == 1.1*Math.pow(10, 21)
	 */
	public double getForce(){
		return 1.1*Math.pow(10, 18);
	}
	
	
	/**
	 * Return the acceleration of this ship. If the thruster is on, it has an acceleration,
	 * derived by the force it is driven by and the mass of this ship (a = F/m).
	 *
	 * @return	Returns acceleration = 0 if thruster is not enabled.
	 * 			| result == 0       
	 * @return 	Returns the acceleration of this ship if thruster is enabled.
	 *         	| result == this.getForce()/this.getMass()
	 */
	public double getAcceleration() {
		if (!this.isThrusterOn()) 
			return 0.0;
		return this.getForce()/this.getMass();
	}
	
//	All methods related to the bulletss of this ship.
	
	/**
	 * Variable referencing a list collecting all the bullets
	 * of this ship.
	 * 
	 * @invar  	The referenced list is effective.
	 *       	| bullets != null
	 *       
	 * @invar  	Each bullet registered in the referenced list is
	 *         	effective and not yet terminated.
	 *       	| for each bullet in bullets:
	 *       	|   ( (bullet != null) &&
	 *       	|     (! bullet.isTerminated()) )
	 *       
	 * @invar  	No bullet is registered at several positions
	 *         	in the referenced list.
	 *       	| for each I,J in 0..bullets.size()-1:
	 *       	|   ( (I == J) ||
	 *       	|     (bullets.get(I) != bullets.get(J))
	 */
	private final List<Bullet> bullets = new ArrayList<Bullet>();
	
	/**
	 * Return the bullets of this ship as a list.
	 * 
	 * @return 	The current bullets of this ship.
	 * 			| result == this.bullets
	 */
	@Basic 
	@Raw
	public List<Bullet> getBullets() {
		return this.bullets;
	}	
	
	/**
	 * Check whether this ship can have the given bullet
	 * as one of its bullets.
	 * 
	 * @param  	bullet
	 *         	The bullet to check.
	 *         
	 * @return 	True if and only if the given bullet and this ship are effective.
	 *       	| result ==
	 *       	|   (bullet != null) &&
	 *       	|   !bullet.isTerminated() && 
	 *       	|	!this.isTerminated())
	 */
	@Raw
	public boolean canHaveAsBullet(Bullet bullet) {
		return (bullet != null) && !bullet.isTerminated() && !this.isTerminated();
	}
	
	/**
	 * Return the number of bullets associated with this ship.
	 * 
	 * @return	the number of bullets
	 * 			| result == bullets.size()
	 */
	@Basic
	@Raw
	public int getNbBullets() {
		return bullets.size();
	}
	
	/**
	 * Return the bullet associated with this ship at the
	 * given index.
	 * 
	 * @param  	index
	 *         	The index of the bullet to return.
	 *         
	 * @return	the bullet at the given index.
	 * 			| result == bullets.get(index-1)
	 *         
	 * @throws 	IndexOutOfBoundsException
	 *         	The given index is not positive or it exceeds the
	 *         	number of bullets for this ship.
	 *       	| (index < 1) || (index > getNbBullets())
	 */
	@Basic
	@Raw
	public Bullet getBulletAt(int index) throws IndexOutOfBoundsException {
		if (index < 1 || index > this.getBullets().size())
			throw new IndexOutOfBoundsException();
		return bullets.get(index - 1);
	}

	/**
	 * Check whether this ship can have the given bullet
	 * as one of its bullets at the given index.
	 * 
	 * @param  	bullet
	 *         	The bullet to check.
	 *         
	 * @return 	False if the given index is not positive or exceeds the
	 *         	number of bullets for this ship + 1.
	 *       	| if ( (index < 1) || (index > getNbBullets()+1) )
	 *       	|   then result == false
	 *         	Otherwise, false if this ship cannot have the given
	 *         	bullet as one of its bullets.
	 *       	| else if ( ! this.canHaveAsBullet(bullet) )
	 *       	|   then result == false
	 *         	Otherwise, true if and only if the given bullet is
	 *         	not registered at another index than the given index.
	 *       	| else result ==
	 *       	|   for each I in 1..getNbBullets():
	 *       	|     (index == I) || (getBulletAt(I) != bullet)
	 */
	@Raw
	public boolean canHaveAsBulletAt(Bullet bullet, int index) {
		if ((index < 1) || (index > getNbBullets() + 1))
			return false;
		if (!this.canHaveAsBullet(bullet))
			return false;
		for (int i = 1; i < getNbBullets(); i++)
			if ((i != index) && (getBulletAt(i) == bullet))
				return false;
		return true;
	}

	/**
	 * Check whether this ship has proper bullets attached to it.
	 * 
	 * @return 	True if and only if this ship can have each of the
	 *         	bullets attached to it as a bullet at the given index,
	 *         	and if each of these bullets references this ship as
	 *         	the ship to which they are attached.
	 *       	| result ==
	 *       	|   for each I in 1..getNbBullets():
	 *       	|     ( this.canHaveAsBulletAt(getBulletAt(I) &&
	 *       	|       (getBulletAt(I).getShip() == this) )
	 */
	public boolean hasProperBullets() {
		for (int i = 1; i <= getNbBullets(); i++) {
			if (!canHaveAsBulletAt(getBulletAt(i), i))
				return false;
			if (getBulletAt(i).getShip() != this)
				return false;
		}
		return true;
	}
	//Function is used in the invariant of this class
	

	/**
	 * Check whether this ship has the given bullet as one of its
	 * bullets.
	 * 
	 * @param  	bullet
	 *         	The bullet to check.
	 * @return 	True if and only if the bullet is registered at some place in the bullets of the ship.
	 *       	| this.bullets.contains(bullet)
	 */
	public boolean hasAsBullet(@Raw Bullet bullet) {
		return this.bullets.contains(bullet);
	}

	/**
	 * Add the given bullet to the list of bullets of this ship. It is expected
	 * that this bullet already points to this ship.
	 * 
	 * @param  	bullet
	 *         	The bullet to be added.
	 * 
	 * @throws	The given bullet is not valid or
	 * 			the given bullet doesn't already reference
	 *         	this ship, or this ship already has the given
	 *         	bullet as one of its bullets.
	 *       	| !canHaveAsBullet(bullet) || (bullet.getShip() != this) || (this.hasAsBullet(bullet))
	 *       
	 * @post   	The number of bullets of this ship is
	 *         	incremented by 1.
	 *       	| new.getNbBullets() == getNbBullets() + 1
	 *       
	 * @post   	This ship has the given bullet as its very last bullet.
	 *       	| new.getBulletAt(getNbBullets()+1) == bullet
	 */
	public void addBullet(@Raw Bullet bullet) throws IllegalArgumentException{
		// This function expects that bullet is already pointing to this ship.		
		if (!canHaveAsBullet(bullet) || (bullet.getShip() != this) || (this.hasAsBullet(bullet)))
			throw new IllegalArgumentException();
		
		this.bullets.add(bullet);
		
		this.setMass(this.getTotalMass());
		// Bullet influences mass.
	}

	
	/**
	 * Add the given collection of bullets to the list of bullets of this ship. It is expected
	 * that all bullets already point to this ship.
	 * 
	 * @param  	bullets
	 *         	The collection of bullets to be added.
	 *
	 * @throws	IllegalArgumentException
	 * 			A bullet isn't effective,
	 * 			A bullet doesn't already reference this ship,
	 * 			or this ship has on of the given bullets as one of its bullets.
	 *       	| for (Bullet bullet: bullets){
	 *			|	!canHaveAsBullet(bullet) || bullet.getShip() != this || this.hasAsBullet(bullet)
	 *       
	 * @post   	The number of bullets of this ship is
	 *         	incremented by bullets.size().
	 *       	| new.getNbBullets() == getNbBullets() + bullets.size()
	 *       
	 * @post   	This ship has the given collection of bullets as its very last bullets.
	 *       	| new.getBulletAt(getNbBullets()+bullets.size()) == bullets.get(bullets.size())
	 */
	public void addBullets(@Raw Collection<Bullet> bullets) throws IllegalArgumentException{
		// This function expects that all bullets are already pointing to this ship.	
		for (Bullet bullet: bullets){
			if (!canHaveAsBullet(bullet) || bullet.getShip() != this || this.hasAsBullet(bullet))
				throw new IllegalArgumentException();
		}
		
		this.bullets.addAll(bullets);
		// addAll is defined for collections.
		
		this.setMass(this.getTotalMass());
		// Bullets influence mass.
	}	
	
	/**
	 * Remove the given bullet from the list of bullets of this ship.
	 * 
	 * @param  	bullet
	 *         	The bullet to be removed.
	 *  
	 * @throws	IllegalArgumentException
	 * 			The given bullet is not effective
	 * 			or this ship does not have the given bullet as one of its bullets
	 * 			or the given bullet does reference any ship.
	 *       	| !canHaveAsBullet(bullet) || !this.hasAsBullet(bullet) || (!(bullet.getShip() == null))
	 *       
	 * @post   	The number of bullets of this ship is
	 *         	decremented by 1.
	 *       	| new.getNbBullets() == getNbBullets() - 1
	 *       
	 * @post   	This ship no longer has the given bullet as
	 *         	one of its bullets.
	 *       	| !new.hasAsBullet(bullet)
	 *       
	 * @post   	All bullets registered at an index beyond the index at
	 *         	which the given bullet was registered, are shifted
	 *         	one position to the left.
	 *      	| for each I,J in 1..getNbBullets():
	 *       	|   if ( (getBulletAt(I) == bullet) and (I < J) )
	 *      	|     then new.getBulletAt(J-1) == getBulletAt(J)
	 */
	@Raw
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		// This function expects that the given bullet does not reference this ship.
		if (!canHaveAsBullet(bullet) || !this.hasAsBullet(bullet) || (bullet.getShip() != null))
			throw new IllegalArgumentException();
		bullets.remove(bullet);
		this.setMass(this.getTotalMass());
	}
	
	/**
	 * Add this collection of bullets to the given ship. If some of its bullets are already in a ship,
	 * replace them to this ship. If some of its bullets are already in a space, replace
	 * them to this ship. Also places all bullets to this ship.
	 * 
	 * @param 	bullets
	 * 			The given collection of bullet to put in this ship.
	 * 
	 * @effect	The bullets will be in this ship.
	 * 			| this.getBullets() = bullets
	 * 
	 * @post 	If one of the bullets is already placed in a space, first it will be removed from
	 * 			that space. Then it will set ship as its ship.
	 * 			| if (bullet.hasSpace())
	 *			| 	then bullet.removeOutSpace()
	 *			|		 bullet.setShip(this)
	 * 
	 * @post	If one of the bullets is already placed in a ship, first it will be removed from
	 * 			that ship. Then it will set ship as its ship.
	 * 			| if (bullet.hasShip())
	 *			|	then bullet.removeOutShip()
	 *			|		 bullet.setShip(this)
	 * 
	 * @throws 	IllegalArgumentException
	 * 			If one of the bullets is not valid.
	 * 			| !canHaveAsBullet(bullet)
	 */
	public void placeBulletsInShip(Collection<Bullet> bullets) throws IllegalArgumentException{
		for (Bullet bullet: bullets){	
			if (!canHaveAsBullet(bullet)){
				throw new IllegalArgumentException();
			}
			else if (bullet.hasSpace()){
				bullet.removeOutSpace();
				bullet.setShip(this);
			}
			else if (bullet.hasShip()){
				bullet.removeOutShip();
				bullet.setShip(this);
			}
			else {
				bullet.setShip(this);
			}
		}
		this.addBullets(bullets);
	}
	
	/**
	 * A method to check whether the given bullet apparently lies within this ship.
	 * 
	 * @param 	bullet
	 * 			The bullet we want to check.
	 * 
	 * @return	True if and only if the distance between each boundary of this ship
	 * 			and the centre of the given bullet is >= 0.99*the radius of the given bullet.
	 * 			|@see implementation
	 * 
	 * @throws	IllegalArgumentException
	 * 			If this ship is terminated or the given bullet is terminated or null, 
	 * 			or this ship doesn't have the given bullet as its bullet.
	 */
	public boolean withinBoundary(Bullet bullet) throws IllegalArgumentException{
		if (this.isTerminated() || bullet.isTerminated() || bullet == null || this.hasAsBullet(bullet))
			throw new IllegalArgumentException();
		if (this.getxPosition()+this.getRadius()-bullet.getxPosition() >= 0.99*bullet.getRadius() &&
			bullet.getxPosition()-(this.getxPosition()-this.getRadius()) >= 0.99*bullet.getRadius() &&
			this.getyPosition()+this.getRadius()-bullet.getyPosition() >= 0.99*bullet.getRadius() &&
			bullet.getyPosition()-(this.getyPosition()-this.getRadius()) >= 0.99*bullet.getRadius())
			return true;
		return false;
	}
		
	/**
	 * 
	 * A variable referencing all bullets that are fired by the ship
	 * 
	 * @invar  	The referenced list is effective.
	 *       	| firedBullets != null
	 *       
	 * @invar  	Each bullet registered in the referenced list is
	 *         	effective and not yet terminated.
	 *       	| for each bullet in firedBullets:
	 *       	|   ( (bullet != null) &&
	 *       	|     (! bullet.isTerminated()) )
	 *       
	 * @invar  	No bullet is registered at several positions
	 *         	in the referenced list.
	 *       	| for each I,J in 0..firedBullets.size()-1:
	 *       	|   ( (I == J) ||
	 *       	|     (firedBullets.get(I) != firedBullets.get(J))
	 */
	public Set<Bullet> firedBullets = new HashSet<Bullet> ();
	
	/**
	 * return a random bullet  in the list of bullets fired by the ship
	 * 
	 * @return	All bullets that are fired by this ship.
	 * 			| if firedBullets.size() == 0
	 * 			| result == null
	 * 			otherwise
	 * 			| index = this.firedBullets.size()*Math.random();
	 * 			| result == (Bullet) firedBullets.toArray()[index]
	 */
	public Bullet getFiredBullet(){
		if (firedBullets.size() == 0)
			return null;
		double index = this.firedBullets.size()*Math.random();
		return (Bullet) firedBullets.toArray()[(int) index];
	}
		
	/**
	 * A method that fires a bullet
	 * 
	 * @post	if the ship has bullets that don't collide right after firing, 
	 * 			their velocity and position will be changed.
	 * 			| @see implementation
	 * 
	 * @post	if a bullet is fired, he will be removed from the ships collection of bullets.
	 * 			| bullet.removeOutShip() 
	 * 
	 * @post	if a bullet immediately hits a wall, the bullet will be terminated
	 * 			|if (bullet.hasHitWall())
	 *			|	bullet.terminate();
	 *
	 * @post	if a bullet immediately hits an other RoundEntity, they will be both terminated
	 * 			| if (entity.currentlyCollide(bullet)){
	 *			|		entity.terminate();
	 *			|		bullet.terminate();
	 */
	public void fireBullet(){
		if (this.hasWorld() && this.getNbBullets() != 0){
			Bullet bullet = this.getBulletAt(this.getNbBullets());
			double fireOrientation = this.getOrientation();
			double fireDistance = 1.01*(this.getRadius()+bullet.getRadius());
			double xFirePosition = this.getxPosition() + fireDistance * Math.cos(fireOrientation);
			double yFirePosition = this.getyPosition() + fireDistance * Math.sin(fireOrientation);
			double xFireVelocity = 250*Math.cos(fireOrientation);
			double yFireVelocity = 250*Math.sin(fireOrientation);
			bullet.setPosition(xFirePosition, yFirePosition);
			bullet.setVelocity(xFireVelocity, yFireVelocity);
			bullet.setSource(this);
			
			try{
				bullet.placeInSpace(this.getWorld());
				firedBullets.add(bullet);
			}
			catch (IllegalArgumentException exc){
				if (bullet.hasHitWall())
					bullet.terminate();
				for (RoundEntity entity : this.getSpace().getEntities()){
					//TODO map eh
					if (entity.currentlyCollide(bullet)){
						entity.terminate();
						bullet.terminate();
					}				
				}		
			}
		}
	}
	
//	All methods related to the moving and solving of collisions.

	@Override
	public void move(double duration){
		if (!canHaveAsDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
	}
	
	/**
	 * A method for resolving a collision with a ship
	 *
	 * @effect	if the other entity is a Ship, their velocity will be changed
	 * 			| if (other instanceof Ship)
	 * 			|	then this.setVelocityAfterBounce(other)
	 * @effect	if the other entity is an Asteroid, this ship will be terminated and the asteroid isn't affected
	 * 			| if (other instanceof Asteroid)
	 * 			| 	then this.terminate()
	 * @effect	if the other entity is a Planetoid, this ship will be spawned to an other position.
	 * 				the planetoid isn't affected.
	 * 			if the new position is not valid, this ship will be terminated
	 * 			| @see implementation
	 * 			 
	 * @effect	if the other entity is none of the above, we resolve the collision in that class
	 * 			| other.resolveCollision(this)
	 */
	
	@Override
	public void resolveCollision(RoundEntity other){
		if (!other.inSameSpace(this) || other == this)
			throw new IllegalArgumentException();
		if (other instanceof Ship)
			this.setVelocityAfterBounce(other);
		else if (other instanceof Asteroid)
			this.terminate();
		else if (other instanceof Planetoid){
			double x = this.getSpace().getWidth()*(new Random().nextDouble());
			double y = this.getSpace().getHeight()*(new Random().nextDouble());
			if (!this.canHaveAsPosition(x, y))
				this.terminate();
			this.setPosition(x, y);
			for (RoundEntity possibleEntityToHit : this.getSpace().getEntities()){	
				//TODO map eh
				if (this.overlap(possibleEntityToHit))
					this.terminate();
			}				
		}
		else
			other.resolveCollision(this);
	}
	
//	All methods related to the program of this ship
	
	/**
	 * 
	 * A method that returns the entity of classType cls, which distance with this ship is smallest.
	 * 
	 * @param 	cls
	 * 			the classe of which we want to find the closest entity of.
	 * 
	 * @return	null if this ship is terminated, or there are no entities of the given classe.
	 * 			| if (this.isTerminated() || this.getSpace().getEntityOfClass(cls).size() == 0)
	 * 			| result == null
	 * 
	 * @return	the ship which distance to this ship is the smallest otherwise
	 * 			| @see implemantation
	 * 
	 * @throws ClassNotFoundException
	 */
		
	//TODO hij throwt nooit de classnotFoundException, toch staat die ertussen, documentatie en code
	
	public RoundEntity getClosestEntityOfClass(Class<?> cls) throws ClassNotFoundException{
		RoundEntity shipClosest = null;
		if (this.isTerminated() || this.getSpace().getEntityOfClass(cls).size() == 0)
			return shipClosest;
		double maxDistance = Double.MAX_VALUE;
		for (RoundEntity entity : this.getSpace().getEntityOfClass(cls)){
			double distanceBetween = this.getDistanceBetween(entity);
			if (distanceBetween < maxDistance){
				maxDistance = distanceBetween;
				shipClosest = entity;
			}
		}
		return shipClosest;
	}
	
	/**
	 * 
	 * a method that returns a certain entity at a random position in the entities
	 * 
	 * @return 	the round entity at the given random index
	 * 			|this.getSpace().getEntities().toArray()[index];
	 * @throws 	IllegalArgumentException
	 * 			if this ship is terminated or the world of this ship is not a valid world.
	 * 			| this.isTerminated || this.canHaveAsSpace(this.getWorld())
	 */
	
	public RoundEntity getAnyEntity() throws IllegalArgumentException{
		if (this.isTerminated || this.canHaveAsSpace(this.getWorld()))
			throw new IllegalArgumentException();
		else{
			double index = this.getSpace().getEntities().size()*Math.random();
			return (RoundEntity) this.getSpace().getEntities().toArray()[(int) index];
			//TODO map eh
		}
	}
	
	/**
	 * 
	 * A variable registering the program of this ship.
	 */
	private Program program;
	
	/**
	 * 
	 * A method to set the given program to this ship.
	 * @param 	program
	 * 			The given program, we want the ship to have
	 * 
	 * @result 	set this ship with the given program
	 * 			| this.program = program
	 * 
	 * @result 	if the given program is valid, set this ship to the given program
	 * 			| program.setShip(this);
	 */
	public void setProgram(Program program){
		this.program = program;
		if (program != null)
			program.setShip(this);		
	}
	
	/**
	 * 
	 * A method that returns the program of this ship.
	 * 
	 * @return	the program of this ship
	 * 			| result == this.program
	 */
	public Program getProgram(){
		return this.program;
	}
	
	/**
	 * a method that execute the program of this ship
	 * @param 	duration
	 * 			the duration we want the program to be executed.
	 * @return	the executing process
	 * 			| this.program.execute(duration)
	 */
	public List<Object> executeProgram(Double duration) throws ClassNotFoundException{
		return this.program.execute(duration);
	}
	
	
}
