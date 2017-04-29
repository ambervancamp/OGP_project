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
	 * 			
	 * 
	 */
	@Raw 
	@Model
	protected Space(double width, double height) throws IllegalArgumentException{
		setWidth(width);
		setHeight(height);
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
	 */
	@Basic
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	/**
	 * Terminate this world
	 * 
	 * @effect 	The entities, if any, are deletet from this world
	 * 		
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
	 * @throws 	IllegalArgumentException
	 * 			The given width is not a possible width for this world.
	 * 			
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
	 * @throws 	IllegalArgumentException
	 * 			The given height is not a possible height for this world.
	 * 		
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
	 * 			
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
	 * 			
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
	 * Check whether this space has the given entity as one of its
	 * entities.
	 * 
	 * @param  	entity
	 *         	The entity to check.
	 *         
	 * @return 	The given entity is registered at some position as
	 *         	an entity of this space.
	 */
	public boolean hasAsEntity(@Raw RoundEntity entity) {
		return this.entities.contains(entity);
	}
	
	/**
	 * 
	 * @param 	entity
	 * 			The entity to check
	 * @return	If this entity is terminated, true if and only if
	 * 			the given intity is not effective.
	 * @return	False if and only if the entity is already located in this world
	 * @return	False if and only if the entity doesn't fit the boundaries of this world.
	 * @return	False if and only if the given intity is 
	 * 			either not effective and not terminated
	 */
	@Raw 
	public boolean canHaveAsEntity(RoundEntity entity){
		return (!this.isTerminated() && entity != null && !entity.isTerminated());
	}
	
	/**
	 * Check whether the given entity can significantly be put in this space, regarding the
	 * boundaries of this space. If the space
	 * is an unbound space, it has no boundaries, so it will always be true.
	 * 
	 * @param 	entity
	 * 			The entity to check
	 * @return 	False if the entity or world is terminated or the entity doesn't belong to the given space
	 * @return	True if the x position lies between 0 and the width of this space and
	 * 					the y position lies between 0 and the height of this space.  
	 */
	public boolean fitBoundary(RoundEntity entity){
		if (!this.canHaveAsEntity(entity))
			return false;
		if (this instanceof UnboundSpace)
			return true;
		if (entity.getxPosition()+entity.getRadius() <= 1.01*this.getWidth() &&
				entity.getxPosition()-entity.getRadius() >= 0 &&
				entity.getyPosition()+entity.getRadius() <= 1.01*this.getHeight() &&
				entity.getyPosition()-entity.getRadius() >= 0)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param 	entity
	 * 			The new entity in this world.
	 * @throws 	IllegalArgumentException
	 * 			The given entity is not effective or can not be placed in this world.
	 *@post		The entity will be in the list of entities of this world.
	 */
	public void addEntity(RoundEntity entity) throws IllegalArgumentException{
		// This function expects that the entity is already pointing to this world.		
		if (!canHaveAsEntity(entity) || (entity.getSpace() != this) || this.hasAsEntity(entity))
			throw new IllegalArgumentException();					
		this.entities.add(entity);
	}
	
	/**
	 * 
	 * @param 	entity
	 * 			the entity we want to remove from this world
	 * @throws 	IllegalArgumentExeption
	 * 			The entity is not effective or still belongs to the given world
	 * @post	The entity will not be in the lists of entities in this world
	 */
	public void deleteEntity(RoundEntity entity) throws IllegalArgumentException{
		// This function expects that the given entity does not reference this world.
		if (!canHaveAsEntity(entity) || entity.getSpace() != null || !this.hasAsEntity(entity))
			throw new IllegalArgumentException();
		entities.remove(entity);
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
		for (RoundEntity entity : entities){
			if (entity.xPosition == xPosition && entity.yPosition == yPosition)
				return entity;
		}
		return null;
	}
	
	/**
	 * 
	 * @return 	Double.POSITIVE_INFINITY if this entity is terminated.
	 * @return	The time of the first collision. This will be with a wall or with an other entity.
	 */
	public double getTimeNextCollision(){
		double smallestTime = Double.POSITIVE_INFINITY;
		if (this.isTerminated())
			return smallestTime;
		for (RoundEntity firstEntity : entities){
			for(RoundEntity secondEntity : entities){
				if (firstEntity != secondEntity)
					smallestTime =  Math.min(smallestTime, firstEntity.getTimeToCollision(secondEntity));
			}
			smallestTime = Math.min(smallestTime, firstEntity.getTimeToHitWall());
		}
		return smallestTime;
	}
	
	/**
	 * A method that gives the position of the first next collision
	 * @return	The position of collision
	 * 
	 * @throws 	IllegalArgumentException	
	 * 			If the world is terminated
	 */
	public double [] getPositionNextCollision() throws IllegalArgumentException{
		if(this.isTerminated())
			throw new IllegalArgumentException();
		for (RoundEntity firstEntity : entities)
			for (RoundEntity secondEntity : entities)
				if (firstEntity != secondEntity)
					if(this.getTimeNextCollision() == firstEntity.getTimeToCollision(secondEntity))
						return firstEntity.getCollisionPosition(secondEntity);
					else if (firstEntity.getTimeToHitWall() == this.getTimeNextCollision())
						return firstEntity.getPositionOfHitWall();
					else
						throw new IllegalArgumentException();
		return new double[] {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY};
	}
	
	
	/**
	 * A method that consists of a set of sets in which one or 2 entities collide. 
	 * Respectively with the wall or with each other.
	 * @return  The set of sets of collisionPoints.
	 * 			
	 */
	public Set<Set<RoundEntity>> getCollisions(){
		Set<Set<RoundEntity>> collisionPoints = new HashSet<>();
		for (RoundEntity entity : entities){
			if (entity.hasHitWall() && this.getTimeNextCollision() == entity.getTimeToHitWall()){
				Set<RoundEntity> coll = new HashSet<>();
				coll.add(entity);
				collisionPoints.add(coll);
			}
			for (RoundEntity other : entities){
				if (other != entity && entity.canAsCollision(other) &&
						this.getTimeNextCollision() == entity.getTimeToCollision(other)){
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
				entityThatHitWall.setVelocityAfterEntityHitWall();
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
	 * @param 	duration
	 * 			The duration of the evolving if the world.
	 * @param 	collisionListener
	 * 			A list of all collisions that happen during the evolving of the world.
	 */
	public void evolve(double duration, CollisionListener collisionListener) 
			throws IllegalArgumentException{
		if (this.isTerminated())
			throw new IllegalArgumentException();
		double timeToNextHit = getTimeNextCollision();
		while (timeToNextHit <= duration){
			for (RoundEntity entity : entities){
				if (entity instanceof Ship){
					((Ship) entity).thrust(((Ship) entity).getAcceleration(), duration);
				entity.move(timeToNextHit);
				}
				// moet dit ook automatisch, zodat al degenen die gethrust worden hierin passen?
				// Dan zou een bullet eigenlijk ook gewoon een thrust moeten hebben,
				// maar zou deze altijd 0 moeten zijn...
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
	 * @return	a list of all ships in this world
	 */
	public List<Ship> getWorldShips(){
		List<Ship> ships = new ArrayList<Ship>();
		if (this.isTerminated())
			return ships;
		for (RoundEntity entity : entities)
			if (entity instanceof Ship)
				ships.add((Ship) entity);
		return ships;
	}

	/** 
	 * @return	a list of all bullets in this world
	 */
	public List<Bullet> getWorldBullets(){
		List<Bullet> bullets = new ArrayList<Bullet>();
		if (this.isTerminated())
			return bullets;
		for (RoundEntity entity : entities)
			if (entity instanceof Bullet)
				bullets.add((Bullet) entity);
		return bullets;
	}
	
	public List<MinorPlanet> getWorldMinorPlanet(){
		List<MinorPlanet> minorPlanets = new ArrayList<MinorPlanet>();
		if (this.isTerminated())
			return minorPlanets;
		for (RoundEntity entity : entities)
			if (entity instanceof MinorPlanet)
				minorPlanets.add((MinorPlanet)entity);
		return minorPlanets;
	}
//	All these list should be made automatically, so without changing the class world
//	--> moet nog gedaan worden ambie, maar weeet even niet hoe we dat kunnen doen
	double smallestTimeToCollision = Double.POSITIVE_INFINITY;
} 
