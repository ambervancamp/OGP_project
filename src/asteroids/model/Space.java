package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of possible worlds a ship can be located in
 * 
 * @invar 
 * 
 * @version 1.0
 * 
 * @author Amber Van Camp & Jasper Vanmeerbeeck
 *
 */
public abstract class Space {
	
	/**
	 * Initialize this new world with width and height
	 * 
	 * @param 	width
	 * 			the width of this world
	 * @param	height
	 * 			the height of this world
	 * 
	 */
	@Raw 
	@Model
	protected Space(double width, double height, Ship ship, Bullet bullet) throws IllegalArgumentException{
		setWidth(width);
		setHeight(height);
		setShip(ship);
		setBullet(bullet);
	}
	
	/**
	 * Check whether this world still exists.
	 */
	@Basic
	public boolean isTerminated(){
		if (this.getHeight() == Double.NaN && this.getWidth()== Double.NaN)
			return false;
		return true;
	}
	
	/**
	 * Terminate this world
	 * 
	 * @effect 	The ships and bullets, if any, are unset from this world
	 * 			| unsetShip() && unsetBullet()
	 * @post	The height and width may have changed
	 * 			|new.canHaveAsWidth(new.getWidth())
	 *			|new.can>HaveAsheigth(new,getHeight())
	 * 			
	 */
	public void terminate(){
		unsetShip();
		unsetBullets();
		this.width = Double.NaN;
		this.height = Double.NaN;
	}
	
	private double width = Double.POSITIVE_INFINITY;
	private double height = Double.POSITIVE_INFINITY;
	private double maxWidth = Double.POSITIVE_INFINITY;
	private double maxHeight = Double.POSITIVE_INFINITY;
	
	/**
	 * 
	 * @param 	width
	 * 			The new width of the world.
	 * @post 	The width of this world is the same as the given width
	 * 			|new.getWidth() == width
	 * @throws 	IllegalArgumentException
	 * 			The given width is not a possible width for this world.
	 * 			| !canHaveAsWidth(width)
	 */
	public void setWidth(double width){
		if (!canHaveAsWidth(width))
			this.width = maxWidth;
		this.width = width;
	}
	
	/**
	 * 
	 * @param 	height
	 * 			The new height of the world.
	 * @post 	The height of this world is the same as the given height.
	 * 			|new.getheight() == height
	 * @throws 	IllegalArgumentException
	 * 			The given height is not a possible height for this world.
	 * 			| !canHaveAsHeight(height)
	 */
	public
	public void setHeight(double height){
		if (!canHaveAsWidth(width))
			this.height=maxHeight;
		this.height = height;
	}
	
	/**
	 * Return the width of this world.
	 */
	@Basic
	public double getWidth(){
		return this.width;
	}
	
	/**
	 * Return the Height of this world
	 */
	@Basic 
	public double getHeight(){
		return this.height;
	}
	
	/**
	 * Check whether the given width is a possible width of this world.
	 * @param 	width
	 * 			The width to be checked
	 * @return	True if and only if this world is termiated,
	 * 			or if the given width is effective, not negative and smaller than the maximum width
	 * 			| result ==
	 * 			| (isTermiated() ||
	 * 			|( (width != Double.NaN) && (0<width<maxWidth))
	 */
	@Raw
	public boolean canHaveAsWidth(double width){
		if (isTerminated())
			return true;
		else if (this.getWidth() == Double.NaN || this.getWidth() < 0 || this.getWidth()>maxWidth);
			return false;
	}
	
	/**
	 * Check whether the given height is a possible height of this world.
	 * @param 	height
	 * 			The height to be checked
	 * @return	True if and only if this world is termiated,
	 * 			or if the given height is effective, not negative and smaller than the maximum width
	 * 			| result ==
	 * 			| (isTermiated() ||
	 * 			|( (height != Double.NaN) && (0<height<maxHeight))
	 */
	@Raw 
	public boolean canHaveAsHeight(double Height){
		if (isTerminated())
			return true;
		if (this.getHeight() == Double.NaN || this.getHeight() < 0 || this.getHeight()>maxHeight);
			return false;
	}
	
	/**
	 * Return the ships in this world
	 */
	public List<Ship> getShip(){
		return new ArrayList<Ship>();
	}
	
	/**
	 * 
	 * @param 	ship
	 * 			The ship to check
	 * @return	If this ship is terminated, true if and only if
	 * 			the given ship is not effective.
	 * 			| if(this.isTermiated())
	 * 			| then result == (ship==null)
	 * @return	False if and only if the ship is already located in this world
	 * 			| ship in ships
	 * @return	True if and only if the given ship is 
	 * 			either not effective, not terminated and not yet positioned in this world
	 * 			| else result == (ship==null) || (!ship.isTereminated())
	 */
	@Raw 
	public boolean canHaveAsShip(Ship ship){
		if (this.isTerminated())
			return(ship == null);
		for (Ship otherShip : ships)
			if (otherShip == ship)
				return false;
		if (ship==null || !ship.isTerminated())
			return true;
	}
	
	/**
	 * 
	 * @param 	ship
	 * 			The new ship in this world.
	 * @throws 	IllegalArgumentException
	 * 			The given ship is not effective or can not be placed in this world
	 * 			| (ship == null)||
	 * 			| !canHaveAsShip(ship)
	 */
	public void addShip(Ship ship) throws IllegalArgumentException{
		if (ship == null || !canHaveAsShip(ship))
			throw new IllegalArgumentException();
		for (Ship otherShip : ships){
			if (ship.overlap(otherShip))
				throw new IllegalArgumentException();
		}
		for (Bullet otherBullet : bullets){
			/ methode overlap zou moeten bestaan in bullet
			if (ship.overlap(otherBullet))
				throw new IllegalArgumentException();
		}					
		ships.add(ship);
		/** 
		 * ship moet ook in de world bijkomen
		 */
	}
	
	/**
	 * 
	 * @param 	ship
	 * 			the ship we want to remove from this world
	 * @post	The ship will not be in the lists of ships in this world
	 * 			| !hasAsShip(ship)	
	 */
	public void deleteShip(Ship ship) throws IllegalArgumentException{
		if (ship == null)
			throw new IllegalArgumentException();
		for (Ship otherShip : ships)
			if (otherShip == ship)
				ships.remove(ship);
		/**
		 * ship moet ook nog uit de world
		 */
		
	}
	

	/**
	 * Return the bullets in this world
	 */
	public List<Bullet> getBullet(){
		return new ArrayList<Bullet>();
	}
	
	/**
	 * 
	 * @param bullet
	 * 			The bullet to check
	 * @return	If this bullet is terminated, true if and only if
	 * 			the given bullet is not effective.
	 * 			| if(this.isTermiated())
	 * 			| then result == (bullet==null)
	 * @return	False if and only if the bullet is already located in this world
	 * 			| bullet in bullets
	 * @return	True if and only if the given bullets 
	 * 			either not effective, not terminated and not yet positioned in this world
	 * 			| else result == (bullet==null) || (!bullet.isTereminated())
	 */
	@Raw 
	public boolean canHaveAsBullet(Bullet bullet){
		if (this.isTerminated())
			return(bullet == null);
		for (Bullet otherBullet : bullets)
			if (otherBullet == bullet)
				return false;
		/ methode die kijkt of de bullet nog bestaat 
		if (bullet==null || !bullet.isTerminated())
			return true;
	}
	
	/**
	 * 
	 * @param 	bullet
	 * 			The new bullet in this world.
	 * @throws 	IllegalArgumentException
	 * 			The given bullet is not effective or can not be placed in this world
	 * 			| (bullet == null)||
	 * 			| !canHaveAsBullet(bullet)
	 */
	public void addBullet(Bullet bullet) throws IllegalArgumentException{
		if (bullet == null || !canHaveAsBullet(bullet))
			throw new IllegalArgumentException();
		for (Ship otherShip : ships){
			if (bullet.overlap(otherShip))
				throw new IllegalArgumentException();
		}
		for (Bullet otherBullet : bullets){
			/ methode overlap zou moeten bestaan in bullet
			if (bullet.overlap(otherBullet))
				throw new IllegalArgumentException();
		}					
		bullets.add(bullet);
		/** 
		 * bullet moet ook in de world bijkomen
		 */
	}
	
	/**
	 * 
	 * @param 	ship
	 * 			the ship we want to remove from this world
	 * @post	The ship will not be in the lists of ships in this world
	 * 			| !hasAsShip(ship)	
	 */
	public void deleteBullet(Bullet bullet) throws IllegalArgumentException{
		if (bullet == null)
			throw new IllegalArgumentException();
		for (Bullet otherBullet : bullets)
			if (otherBullet == bullet)
				bullets.remove(bullet);
		/**
		 * bullet moet ook nog uit de world
		 */
		
	}
	
	private Ship ship;
	private Bullet bullet;
	private final List<Ship> ships = new ArrayList<Ship>();
	private final List<Bullet> bullets = new ArrayList<Bullet>();
} 
