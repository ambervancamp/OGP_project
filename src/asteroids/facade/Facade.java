package asteroids.facade;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import asteroids.model.*;

import asteroids.part2.CollisionListener;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements asteroids.part2.facade.IFacade {

	/**
	 * Return the position of <code>ship</code> as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 */
	public double[] getShipPosition(Ship ship) throws ModelException {
		try {
		return ship.getPosition();
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the velocity of <code>ship</code> as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 */
	public double[] getShipVelocity(Ship ship) throws ModelException {
		try {
			return ship.getVelocity();
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the radius of <code>ship</code>.
	 */
	public double getShipRadius(Ship ship) throws ModelException {
		try {
			return ship.getRadius();
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the orientation of <code>ship</code> (in radians).
	 */
	public double getShipOrientation(Ship ship) throws ModelException {
		try {
			return ship.getOrientation();
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Update the direction of <code>ship</code> by adding <code>angle</code>
	 * (in radians) to its current direction. <code>angle</code> may be
	 * negative.
	 */
	public void turn(Ship ship, double angle) throws ModelException {
		try {
		ship.turn(angle);
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the distance between <code>ship1</code> and <code>ship2</code>.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 */
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Check whether <code>ship1</code> and <code>ship2</code> overlap. A ship
	 * always overlaps with itself.
	 */
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlap(ship2);
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the number of seconds until the first collision between
	 * <code>ship1</code> and <code>ship2</code>, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 */
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the first position where <code>ship1</code> and <code>ship2</code>
	 * collide, or <code>null</code> if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 */
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2);
		} catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**************
	 * SHIP: Basic methods
	 *************/

	/**
	 * Create a new non-null ship with the given position, velocity, radius,
	 * direction and mass.
	 * 
	 * The thruster of the new ship is initially inactive. The ship is not
	 * located in a world.
	 */
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException{
		try {
			return new Ship(x,y,xVelocity,yVelocity,radius,direction,mass);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Terminate <code>ship</code>.
	 */
	public void terminateShip(Ship ship) throws ModelException{
		try {
			ship.terminate();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}	
	}

	/**
	 * Check whether <code>ship</code> is terminated.
	 */
	public boolean isTerminatedShip(Ship ship) throws ModelException{
		try {
			return ship.isTerminated();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the total mass of <code>ship</code> (i.e., including bullets
	 * loaded onto the ship).
	 */
	public double getShipMass(Ship ship) throws ModelException{
		try {
			return ship.getMass();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**
	 * Return the world of <code>ship</code>.
	 */
	public World getShipWorld(Ship ship) throws ModelException {
		try {
			return ship.getWorld();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return whether <code>ship</code>'s thruster is active.
	 */
	public boolean isShipThrusterActive(Ship ship) throws ModelException{
		try {
			return ship.isThrusterOn();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Enables or disables <code>ship</code>'s thruster depending on the value
	 * of the parameter <code>active</code>.
	 */
	public void setThrusterActive(Ship ship, boolean active) throws ModelException{
		try {
			ship.thrustOn();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the acceleration of <code>ship</code>.
	 */
	public double getShipAcceleration(Ship ship) throws ModelException{
		try {
			return ship.getAcceleration();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}


	/**************
	 * BULLET: Basic methods
	 *************/

	/**
	 * Create a new non-null bullet with the given position, velocity and
	 * radius,
	 * 
	 * The bullet is not located in a world nor loaded on a ship.
	 */
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException{
		try {
			return new Bullet(x, y, xVelocity, yVelocity, radius);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**
	 * Terminate <code>bullet</code>.
	 */
	public void terminateBullet(Bullet bullet) throws ModelException{
		try {
			bullet.terminate();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**
	 * Check whether <code>bullet</code> is terminated.
	 */
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException{
		try {
			return bullet.isTerminated();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the position of <code>ship</code> as an array containing the
	 * x-coordinate, followed by the y-coordinate.
	 */
	public double[] getBulletPosition(Bullet bullet) throws ModelException{
		try {
			return bullet.getPosition();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the velocity of <code>ship</code> as an array containing the
	 * velocity along the X-axis, followed by the velocity along the Y-axis.
	 */
	public double[] getBulletVelocity(Bullet bullet) throws ModelException{
		try {
			return bullet.getVelocity();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the radius of <code>bullet</code>.
	 */
	public double getBulletRadius(Bullet bullet) throws ModelException{
		try {
			return bullet.getRadius();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the mass of <code>bullet</code>.
	 */
	public double getBulletMass(Bullet bullet) throws ModelException{
		try {
			return bullet.getMass();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the world in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned in a world, or
	 * if it is positioned on a ship.
	 */
	public World getBulletWorld(Bullet bullet) throws ModelException{
		try {
			return bullet.getWorld();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the ship in which <code>bullet</code> is positioned.
	 * 
	 * This method must return null if a bullet is not positioned on a ship.
	 */
	public Ship getBulletShip(Bullet bullet) throws ModelException{
		try {
			return bullet.getShip();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the ship that fired <code>bullet</code>.
	 */
	public Ship getBulletSource(Bullet bullet) throws ModelException{
		try {
			return bullet.getSource();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**************
	 * WORLD: Basic methods
	 *************/

	/**
	 * Create a new world with the given <code>width</code> and
	 * <code>height</code>.
	 */
	public World createWorld(double width, double height) throws ModelException{
		try {
			return new World(width, height);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Terminate <code>world</code>.
	 */
	public void terminateWorld(World world) throws ModelException{
		try {
			world.terminate();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Check whether <code>world</code> is terminated.
	 */
	public boolean isTerminatedWorld(World world) throws ModelException{
		try {
			return world.isTerminated();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return the size of <code>world</code> as an array containing the width,
	 * followed by the height.
	 */
	public double[] getWorldSize(World world) throws ModelException{
		try {
			return world.getSpaceSize();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return all ships located within <code>world</code>.
	 */
	public Set<? extends Ship> getWorldShips(World world) throws ModelException{
		try {
			Set<Ship> ships = new HashSet<Ship>(world.getWorldShips());
			return ships;
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Return all bullets located in <code>world</code>.
	 */
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException{
		try {
			Set<Bullet> bullets = new HashSet<Bullet>(world.getWorldBullets());
			return bullets;
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Add <code>ship</code> to <code>world</code>.
	 */
	public void addShipToWorld(World world, Ship ship) throws ModelException{
		try {
			ship.placeInSpace(world);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeShipFromWorld(World world, Ship ship) throws ModelException{
		try {
			ship.removeEntityFromWorld(world);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	// Moet nog verplaatst worden

	/**
	 * Add <code>bullet</code> to <code>world</code>.
	 */
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException{
		try {
			bullet.placeInSpace(world);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Remove <code>ship</code> from <code>world</code>.
	 */
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException{
		try {
			bullet.removeEntityFromWorld(world);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**************
	 * SHIP: Methods related to loaded bullets
	 *************/

	/**
	 * Return the set of all bullets loaded on <code>ship</code>.
	 * 
	 * For students working alone, this method may return null.
	 */
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException{
		try {
			Set<Bullet> bullets = new HashSet<Bullet>(ship.getBullets());
			return bullets;
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**
	 * Return the number of bullets loaded on <code>ship</code>.
	 */
	public int getNbBulletsOnShip(Ship ship) throws ModelException{
		try {
			return ship.getNbBullets();
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 */
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException{
		try {
			bullet.placeInShip(ship);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Load <code>bullet</code> on <code>ship</code>.
	 * 
	 * For students working alone, this method must not do anything.
	 */
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException{
		try {
			ship.placeBulletsInShip(bullets);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * Remove <code>ship</code> from <code>ship</code>.
	 */
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException{
		try {
			bullet.removeBulletFromShip(ship);
		} 
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}

	/**
	 * <code>ship</code> fires a bullet.
	 */
	public void fireBullet(Ship ship) throws ModelException;
	//METHODE MOET NOG WORDEN GEMAAKT

	
	/******************
	 * COLLISIONS
	 **************/

	/**
	 * Return the shortest time in which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double getTimeCollisionBoundary(Object object) throws ModelException{
		try{
			return ((RoundEntity) object).getTimeToHitWall();
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	//OBJECT VERANDEREN NAAR ENTITY? of casten?

	/**
	 * Return the first position at which the given entity will collide with the
	 * boundaries of its world.
	 */
	public double[] getPositionCollisionBoundary(Object object) throws ModelException{
		try{
			return ((RoundEntity) object).getPositionOfHitWall();
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	//implementatie van deze functie, met/zonder argument

	/**
	 * Return the shortest time in which the first entity will collide with the
	 * second entity.
	 */
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException{
		try{
			return ((RoundEntity) entity1).getTimeToCollision((RoundEntity) entity2);
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**
	 * Return the first position at which the first entity will collide with the
	 * second entity.
	 */
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException{
		try{
			return ((RoundEntity) entity1).getCollisionPosition((RoundEntity) entity2);
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**
	 * Return the time that must pass before a boundary collision or an entity
	 * collision will take place in the given world. Positive Infinity is
	 * returned if no collision will occur.
	 */
	public double getTimeNextCollision(World world) throws ModelException{
		try{
			return world.getTimeNextCollision();
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	/**
	 * Return the position of the first boundary collision or entity collision
	 * that will take place in the given world. Null is returned if no collision
	 * will occur.
	 */
	public double[] getPositionNextCollision(World world) throws ModelException{
		try{
			return world.getPositionNextCollision();
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**
	 * Advance <code>world</code> by <code>dt<code> seconds. 
	 * 
	 * To enable explosions within the UI, notify <code>collisionListener</code>
	 * whenever an entity collides with a boundary or another entity during this
	 * method. <code>collisionListener</code> may be null. If
	 * <code>collisionListener</code> is <code>null</code>, do not call its
	 * notify methods.
	 */
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException{
		try{
			world.evolve(dt, collisionListener);
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	/**
	 * Return the entity at the given <code>position</code> in the given
	 * <code>world</code>.
	 */
	public Object getEntityAt(World world, double x, double y) throws ModelException{
		try{
			world.getEntityAt(x,y);
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	
	/**
	 * Return a set of all the entities in the given world.
	 */
	public Set<? extends Object> getEntities(World world) throws ModelException{
		try{
			Set<RoundEntity> entities = new HashSet<RoundEntity>(world.getEntities());
			return entities;
		}
		catch (Exception exc) {
			throw new ModelException(exc.getMessage());
		}
	}
	// Eventueel hergedefinieerd naar return set, wegens constante functie

}
