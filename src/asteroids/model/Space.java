package asteroids.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

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
@Value
public abstract class Space {
	
	/**
	 * Initialize this new world with width and height
	 * 
	 * @param 	width
	 * 			the width of this world
	 * @param	height
	 * 			the height of this world
	 * @throws 	IllegalArgumentException
	 * 			If the given width or height isn't valid for this world.
	 * 			| !this.canHaveAsHeight(height) || !this.canHaveAsWidth(width)
	 * 
	 */
	@Raw 
	@Model
	protected Space(double width, double height) throws IllegalArgumentException{
		if (!this.canHaveAsHeight(height) || !this.canHaveAsWidth(width))
			throw new IllegalArgumentException();
		setWidth(width);
		setHeight(height);
		for (RoundEntity allEntities : entities)
			addEntity(allEntities);
	}
	
	/**
	 * 
	 * @return a list a width an height of the space.
	 */
	public double[] getSpaceSize(){
		return new double[] {this.getWidth(), this.getHeight()};	
	}
	/**
	 * Variable registering whether this world is terminated
	 */
	protected boolean isTerminated = false;
	
	/**
	 * Check whether this world still exists.
	 * @return 	True if and only if this world is terminated
	 * 			|this.isTerminated
	 */
	@Basic
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	/**
	 * Terminate this world
	 * 
	 * @effect 	The entities, if any, are unset from this world
	 * 			| deleteEntity(entity)
	 */
	public void terminate(){
		this.isTerminated = true;
		for (RoundEntity entity : entities)
			this.deleteEntity(entity);
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
	 * 			| (isTerminated() ||
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
	
	
	public List<RoundEntity> getEntities(){
		return entities;
	}
	/**
	 * 
	 * @param 	entity
	 * 			The entity to check
	 * @return	If this entity is terminated, true if and only if
	 * 			the given intity is not effective.
	 * 			| if(this.isTermiated())
	 * 			| then result == (entity==null)
	 * @return	False if and only if the entity is already located in this world
	 * 			| entity in entities
	 * @return	False if and only if the entity doesn't fit the boundaries of this world.
	 * 			@see implempentation
	 * @return	False if and only if the given intity is 
	 * 			either not effective and not terminated
	 * 			| else result == (entity==null) || (!ship.isTerminated())
	 */
	@Raw 
	public boolean canHaveAsEntity(RoundEntity entity){
		if (this.isTerminated())
			return(entity == null);
		for (RoundEntity otherEntity : entities)
			if (otherEntity != entity)
				return true;
		else if (entity!=null && entity.isTerminated())
			return  true;
		else if (fitBoundary(entity) == true)
			return true;
		return false;
	}
	
	/**
	 * Check whether the given entity can be put in the world.
	 * 
	 * @param 	entity
	 * 			The entity to check
	 * @return 	@see implementation
	 */
	public boolean fitBoundary(RoundEntity entity){
		if (entity.isTerminated())
			return true;
		else if (this.isTerminated())
			return true;
		else if (entity.getxPosition()+entity.getRadius() <= this.getWidth() &&
				entity.getxPosition()-entity.getRadius() >= 0 &&
				entity.getyPosition()+entity.getRadius() <= this.getHeight() &&
				entity.getyPosition()-entity.getRadius() >= 0)
			return true;
		return false;
	}

	/**
	 * 
	 * @param 	entity
	 * 			The new entity in this world.
	 * @throws 	IllegalArgumentException
	 * 			The given entity is not effective or can not be placed in this world
	 * 			| (entity == null)||
	 * 			| !canHaveAsEntity(entity)
	 */
	public void addEntity(RoundEntity entity) throws IllegalArgumentException{
		if (entity == null || !canHaveAsEntity(entity))
			throw new IllegalArgumentException();
		for (RoundEntity otherEntity : entities){
			if (entity.overlap(otherEntity))
				throw new IllegalArgumentException();
		}					
		entities.add(entity);
		/** 
		 * entity moet ook in de world bijkomen
		 */
	}
	
	/**
	 * 
	 * @param 	entity
	 * 			the entity we want to remove from this world
	 * @post	The entity will not be in the lists of entities in this world
	 * 			| !hasAsEntity(entity)	
	 */
	public void deleteEntity(RoundEntity entity) throws IllegalArgumentException{
		if (entity == null)
			throw new IllegalArgumentException();
		for (RoundEntity otherEntity : entities)
			if (otherEntity == entity)
				entities.remove(entity);
		
		/**
		 * entity moet ook nog uit de world?
		 */
		
	}
	
	/**
	 * 
	 * @param 	xPosition
	 * 			The given x-position of the coordinate we want to check.
	 * @param 	yPosition
	 * 			The given x-position of the coordinate we want to check.
	 * @return	The entity, if one, which is located at the given position
	 */
	public RoundEntity getEntityAt(Double xPosition, Double yPosition){
		if (this.isTerminated())
			return null;
		for (RoundEntity entity : entities)
			if (entity.xPosition == xPosition && entity.yPosition == yPosition)
				return entity;
		return null;
		}
	
	/**
	 * 
	 * @return 	Double.POSITIVE_INFINITY if this entity is terminated.
	 * 			| this.isTerminated()
	 * @return	The time of the first collision. This will be with a wall or with an other entity.
	 * 			|
	 */
	public double getTimeNextCollision(){
		double smallestTime = Double.POSITIVE_INFINITY;
		if (this.isTerminated())
			return smallestTime;
		for (RoundEntity firstEntity : entities)
			for(RoundEntity secondEntity : entities)
				smallestTime =  Math.min(smallestTime, firstEntity.getTimeToCollision(secondEntity));
		return smallestTime;
	}
	
	/**
	 * A method that consists of a set of sets in which one or 2 entities collide. 
	 * Respectively with the wall or cith each other.
	 * @return  The set of sets of collisionPoints.
	 * 			|@see implementation
	 */
	public Set<Set<RoundEntity>> getCollisions(){
		Set<Set<RoundEntity>> collisionPoints = new HashSet<>();
		for (RoundEntity entity : entities){
			if (entity.hasHitWall()){
				Set<RoundEntity> coll = new HashSet<>();
				coll.add(entity);
				collisionPoints.add(coll);
			}
			for (RoundEntity other : entities){
				if (other != entity && entity.canAsCollision(other)){
					Set<RoundEntity> coll = new HashSet<>();
					coll.add(other); coll.add(entity);
					collisionPoints.add(coll);
				}
			}
			
		}
		return collisionPoints;
	}
	
	/**
	 * A method that resolves collisions.
	 * The method checks whether the given entity hits a wall or an other entity and
	 * puts this in the collisionListener.
	 * @param 	collisionListener
	 * 			The given collisionListener
	 * @throws 	IllegalArgumentException
	 * 			if the given entity is terminated 
	 * 			| this.isTerminated()
	 * 
	 */
	public void resolveCollision(CollisionListener collisionListener) throws IllegalArgumentException{
		if (this.isTerminated())
			throw new IllegalArgumentException();
		if (this.getCollisions().isEmpty())
			return;
		for (Set<RoundEntity> collision : this.getCollisions()){
			if (collision.size() == 1){
				RoundEntity entityThatHitWall = (RoundEntity)collision.toArray()[0];
				collisionListener.boundaryCollision(entityThatHitWall, 
													entityThatHitWall.getxPosition(),
													entityThatHitWall.getyPosition());
				entityThatHitWall.getVelocityAfterEntityHitWall();
				}
			else if (collision.size() == 2){
				RoundEntity firstEntity = (RoundEntity) collision.toArray()[0];
				RoundEntity secondEntity = (RoundEntity) collision.toArray()[1];
				collisionListener.objectCollision(firstEntity,secondEntity,
												  firstEntity.getCollisionPosition(secondEntity)[0],
												  firstEntity.getCollisionPosition(secondEntity)[1]);
				firstEntity.getVelocityAfterEntityHitEntity(secondEntity);
			}
		}
	}
	
	
	/**
	 * A method that let a world change and move with a given duration.
	 * 
	 * @param 	world
	 * 			The world we want to evolve.
	 * @param 	duration
	 * 			The duration of the evolving if the world.
	 * @param 	collisionListener
	 * 			A list of all collisions that happen during the evolving of the world.
	 */
	public void evolve(World world,double duration, CollisionListener collisionListener) 
			throws IllegalArgumentException{
		if (this.isTerminated() || world.isTerminated())
			throw new IllegalArgumentException();
		double timeToNextHit = getTimeNextCollision();
		while (timeToNextHit <= duration){
			for (RoundEntity entity : entities){
				if (entity instanceof Ship){
					((Ship) entity).thrust(((Ship) entity).getAcceleration(), duration);
					entity.move(timeToNextHit);
				}
			this.resolveCollision(collisionListener);
			duration = duration-timeToNextHit;
			timeToNextHit = this.getTimeNextCollision();
			}
		}
		if (duration > 0){
			for (RoundEntity entity : entities){
				if (entity instanceof Ship){
					((Ship) entity).thrust(((Ship) entity).getAcceleration(), duration);
					entity.move(duration);
				}
			}
		}
	}
	/**
	 * a list of all the entities that are located in this world
	 */
	private List<RoundEntity> entities = new ArrayList<RoundEntity>();
	
	/**
	 * A list of all the ships that are located in this world
	 */
	private List<Ship> ships = new ArrayList<Ship>();
	
	/**
	 * A list of all bullets that are located in this world 
	 */
	private List<Bullet> bullets = new ArrayList<Bullet>();
	
	/**
	 *
	 * @return	a list of all ships in this world
	 */
	public List<Ship> getWorldShips(){
		if (this.isTerminated())
			return ships;
		for (RoundEntity entity : entities)
			if (entity instanceof Ship)
				ships.add((Ship) entity);
		return ships;
	}

	/**
	 * 
	 * @return	a list of all bullets in this world
	 */
	public List<Bullet> getWorldBullets(){
		if (this.isTerminated())
			return bullets;
		for (RoundEntity entity : entities)
			if (entity instanceof Bullet)
				bullets.add((Bullet) entity);
		return bullets;
	}
	
	/**
	 * A method that checks whether an entity is part of this world.
	 * @param 	entity
	 * 			The entity we want to check is located in this world.
	 * @return	True if and only if the given world and entity aren't terminated and
	 * 			the space of the entity equals the given space
	 * 			| !this.isTerminated() && !entity.isTerminated() && entity.getSpace() == this
	 */
	public boolean hasEntity(RoundEntity entity){
		if (!this.isTerminated() && !entity.isTerminated() && entity.getSpace() == this)
			return true;
		return false;
		
	}
	double smallestTimeToCollision = Double.POSITIVE_INFINITY;
} 
