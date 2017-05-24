package asteroids.model;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

/**
 * A class of possible worlds a ship can be located in
 * 
 * @invar 	the Space has a valid space width and height.
 * 			| canHaveAswidth(width) && canHaveAsHeight(height)
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
	 */
	@Raw 
	@Model
	protected Space(double width, double height){
		setWidth(width);
		setHeight(height);
	}
	
//	All methods related to the termination of the space.
	
	/**
	 * Variable registering whether this world is terminated
	 */
	protected boolean isTerminated = false;
	
	/**
	 * Check whether this world isn't terminated.
	 * 
	 * @return 	True if and only if this world is terminated
	 */
	@Basic
	public boolean isTerminated(){
		return this.isTerminated;
	}
	
	/**
	 * A method to terminate this world.
	 * @effect 	The entities, if any, are deletet from this world
	 */
	public void terminate(){
		Set<RoundEntity> entitiesToDelete = new HashSet<RoundEntity> ();
		for (RoundEntity entity : getEntities()){
			// TODO map eh
			entitiesToDelete.add(entity);
		}
		for (RoundEntity entityToDelete : entitiesToDelete){
			this.deleteEntity(entityToDelete);
			entityToDelete.isTerminated = true;
		}
		this.isTerminated = true;
	}
	
//	All methods related to the width and height of the space.
	
	/**
	 * A variable registering the width of the space.
	 */
	private double width;
	
	/**
	 * A variable registering the height of the space.
	 */
	private double height;
	
	/**
	 * A variable registering the maximum width of the space.
	 */
	private double maxWidth = Double.MAX_VALUE;
	
	/**
	 * A variable registering the maximum height of the space.
	 */
	private double maxHeight = Double.MAX_VALUE;
	
	/**
	 * Check whether the given width is a possible width for this space.
	 * @param 	width
	 * 			The width to be checked
	 * @return	True if and only if this space is termiated,
	 * 			or if the given width is effective, not negative and smaller than the maximum width
	 * 			
	 */
	@Raw
	public boolean canHaveAsWidth(double width){
		if (Double.isNaN(width)|| width < 0 || width>maxWidth)
			return false;
		return true;
	}
	
	/**
	 * Check whether the given height is a possible height of this space.
	 * @param 	height
	 * 			The space to be checked
	 * @return	True if and only if this space is termiated,
	 * 			or if the given height is effective, not negative and smaller than the maximum width
	 * 			
	 */
	@Raw 
	public boolean canHaveAsHeight(double height){
		if (Double.isNaN(height) || height < 0 || height>maxHeight)
			return false;
		return true;
	}	
	
	/**
	 * 
	 * @param 	width
	 * 			The new width of the world.
	 * @effect	if the given width isn't a valid width, the width will be set to maxWidth
	 * 			otherwise, the width of the space will be set to the given width.
	 * 			
	 */
	public void setWidth(double width){
		if (!canHaveAsWidth(width))
			this.width = maxWidth;
		else
			this.width = width;
	}
	
	/**
	 * 
	 * @param 	height
	 * 			The new height of the world.
	 * @effect	if the given height isn't a valid height, the height will be set to maxHeight
	 * 			otherwise, the height of the space will be set to the given height.
	 * 			
	 */
	public void setHeight(double height){
		if (!canHaveAsHeight(height))
			this.height = maxHeight;
		else
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
	 * A method that returns the width and height of the space as a double [] 
	 * @return a list with the width and height of the space.
	 */
	public double[] getSpaceSize(){
		return new double[] {this.getWidth(), this.getHeight()};	
	}
	
//	All methods related to the entities of this space.
	
	/**
	 * a list of all the entities that are located in this world
	 */
	Set<RoundEntity> entities = new HashSet<RoundEntity>();	
	
//	/**
//	 * A map of all the entities that are located in this world
//	 */
//	Map<double[],RoundEntity> entities2 = new HashMap<double[],RoundEntity> ();
	
	//TODO mapdink eh
	
	/**
	 * A method that gives the entities of this space.
	 * @return the entities of this space.
	 */
	public Set<RoundEntity> getEntities(){
		return this.entities;
//		return (Set<RoundEntity>) this.entities2.values();
	}
	//TODO mapdink eh
	
	/**
	 * A method to check whether this space has the given entity as one of its entities.
	 * 
	 * @param  	entity
	 *         	The entity to check.
	 *         
	 * @return 	True if and only if the given space contains this entity.
	 */
	public boolean hasAsEntity(@Raw RoundEntity entity) {
//		return this.entities.contains(entity);s
		return this.getEntities().contains(entity);
	}
	//TODO mapdink eh
	
	/** A method to check whether this space can have the given entity as one of its entities.
	 * 
	 * @param 	entity
	 * 			The entity to check
	 * 
	 * @return	if and only if the given space is not terminated and
	 * 			the given entity is not null en the given entity is not terminated
	 */
	@Raw 
	public boolean canHaveAsEntity(RoundEntity entity){
		return (!this.isTerminated() && entity != null && !entity.isTerminated());
	}
	
	/**
	 * A method to check whether the given entity can significantly be put in this space,
	 * regarding the boundaries of this space. 
	 * If the space is an unbound space, it has no boundaries, so it will always be true.
	 * 
	 * @param 	entity
	 * 			The entity to check
	 * 
	 * @return 	False if the entity or world is terminated or the entity doesn't belong to the given space.
	 * 
	 * @return	True if the x position lies between 0 and the significantly width of this space and
	 * 					the y position lies between 0 and the significantly height of this space.  
	 * 			or the space is an unbound space
	 */
	public boolean fitBoundary(RoundEntity entity){
//		if (!this.canHaveAsEntity(entity))
//			return false;
		if (this instanceof UnboundSpace)
			return true;
		if (entity.getxPosition()+entity.getRadius() <= 1.01*this.getWidth() &&
				entity.getxPosition()-entity.getRadius() >= 0 &&
				entity.getyPosition()+entity.getRadius() <= 1.01*this.getHeight() &&
				entity.getyPosition()-entity.getRadius() >= 0)
			return true;
		return false;
	}
	// TODO de haakjes wegdoen, ook in de documentatie
	
	/**
	 * A method that adds an entity to the set of entities of this world.
	 * 
	 * @param 	entity
	 * 			The new entity we want to add to this world.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given entity is not effective or can not be placed in this world.
	 * 
	 * @post	The entity will be added to the list of entities of this world.
	 */
	public void addEntity(RoundEntity entity) throws IllegalArgumentException{
		// This function expects that the entity is already pointing to this world.	
		if (!canHaveAsEntity(entity) || (entity.getSpace() != this) || this.hasAsEntity(entity))
			throw new IllegalArgumentException();					
		this.entities.add(entity);
//		this.entities2.put(entity.getPosition(),entity);
//		TODO map eh
	}
	
	/**
	 * A method that removes an entity from the world.
	 * 
	 * @param 	entity
	 * 			the entity we want to remove from this world.
	 * 
	 * @throws 	IllegalArgumentExeption
	 * 			The entity is not effective or still belongs to the given world.
	 * 
	 * @post	The entity will be removed from the lists of entities of this world.
	 */
	public void deleteEntity(RoundEntity entity) throws IllegalArgumentException{
		// This function expects that the given entity does not reference this world.
		if (!canHaveAsEntity(entity) || entity.getSpace() == null || !this.hasAsEntity(entity))
			throw new IllegalArgumentException();
		entities.remove(entity);
//		entities2.remove(entity.getPosition(),entity);
		//TODO map eh
	}
	
	/**
	 * A method that returns the entity at the given position.
	 * 
	 * @param 	xPosition
	 * 			The given x-position of the coordinate.
	 * 
	 * @param 	yPosition
	 * 			The given x-position of the coordinate.
	 * 
	 * @return	The entity, if one, which is located at the given position
	 */
	public RoundEntity getEntityAt(Double xPosition, Double yPosition){
		if (this.isTerminated())
			return null;
//		for (RoundEntity entity : entities){
		for (RoundEntity entity : getEntities()){
			//TODO map eh
			if (entity.xPosition == xPosition && entity.yPosition == yPosition)
				return entity;
		}
		return null;
	}
	
//	All methods related to the collisions of this space.
	
	/**
	 * A method that returns the time to the first collision that will happen next.
	 * 
	 * @return 	Double.POSITIVE_INFINITY if this space is terminated or if the space is an unbound space.
	 * 
	 * @return	The time of the first collision. This will be with a wall or with an other entity.
	 */
	public double getTimeNextCollision(){
		double smallestTime = Double.POSITIVE_INFINITY;
		if (this.isTerminated() || this instanceof UnboundSpace)
			return smallestTime;
//		for (RoundEntity firstEntity : entities){
		for (RoundEntity firstEntity : getEntities()){
//			for(RoundEntity secondEntity : entities){
			for(RoundEntity secondEntity : getEntities()){
				//TODO map eh
				if (firstEntity != secondEntity && firstEntity.getTimeToCollision(secondEntity)!=-0.0){
					if (firstEntity.getTimeToCollision(secondEntity) < smallestTime)
					smallestTime =  firstEntity.getTimeToCollision(secondEntity);
				}
			}
			if (firstEntity.getTimeToHitWall() < smallestTime)
			smallestTime = firstEntity.getTimeToHitWall();
		}
		return smallestTime;
	}
	
	/**
	 * A method that returns the position of the first collision that will happen next.
	 * 
	 * @return	The position of the first collision. This will be with a wall or with an other entity.
	 * 
	 * @throws 	IllegalArgumentException	
	 * 			If the space is terminated or is an unbound space
	 */
	public double [] getPositionNextCollision() throws IllegalArgumentException{
		if(this.isTerminated() || this instanceof UnboundSpace)
			throw new IllegalArgumentException();
		double timeNextCollision = this.getTimeNextCollision();
//		for (RoundEntity firstEntity : entities){
		for (RoundEntity firstEntity : getEntities()){
			//TODO map eh
//			for (RoundEntity secondEntity : entities){
			for (RoundEntity secondEntity : getEntities()){
				//TODO map eh
				if (firstEntity != secondEntity){
					if(timeNextCollision == firstEntity.getTimeToCollision(secondEntity))
						return firstEntity.getCollisionPosition(secondEntity);
				}
			}
			if (firstEntity.getTimeToHitWall() == timeNextCollision){
				return firstEntity.getPositionOfHitWall();
			}
		}
		return new double[] {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY};
	}
	
	
	/**
	 * A method that consists of a set of sets in which two entities collide, or an entity collides with the wall.
	 * example: {{planetoid},{ship,bullet}} --> the planetoid hit the wall, and the ship hit the bullet.
	 * 
	 * @return  The set of sets of collisionPositions.
	 * 			
	 * @throws 	IllegalArgumentException	
	 * 			If the space is terminated or is an unbound space
	 */
	public Set<Set<RoundEntity>> getCollisions(){
		if (this.isTerminated() || this instanceof UnboundSpace)
			throw new IllegalArgumentException();
		Set<Set<RoundEntity>> collisionPosition = new HashSet<>();
//		for (RoundEntity entity : entities){
		for (RoundEntity entity : getEntities()){
			// TODO map eh
			if (entity.hasHitWall() && this.getTimeNextCollision() == entity.getTimeToHitWall()){
				Set<RoundEntity> coll = new HashSet<>();
				coll.add(entity);
				collisionPosition.add(coll);
			}
//			for (RoundEntity other : entities){
			for (RoundEntity other : getEntities()){
				//TODO map eh
				if (other != entity && entity.canAsCollision(other) &&
						this.getTimeNextCollision() == entity.getTimeToCollision(other)){
					Set<RoundEntity> coll = new HashSet<>();
					coll.add(other); coll.add(entity);
					collisionPosition.add(coll);
				}
			}
		}
		return collisionPosition;
	}
	
	/**
	 * A method that let a world change and move with a given duration. 
	 * This method will take into account the possibility that entities will collide
	 * before the given duration has expired.
	 *
	 * @param 	duration
	 * 			The duration of the evolving if the world.
	 * 
	 * @param 	collisionListener
	 * 			A list of all collisions that happen during the evolving of the world.
	 */
	public void evolve(double duration, CollisionListener collisionListener) 
			throws IllegalArgumentException{
		if (this.isTerminated() || duration < 0 || Double.isNaN(duration))
			throw new IllegalArgumentException();
//		List<RoundEntity> possibleEntities = new ArrayList<RoundEntity> (entities);
		List<RoundEntity> possibleEntities = new ArrayList<RoundEntity> (getEntities());
		//TODO map eh

		double timeToNextHit = getTimeNextCollision();
		while (timeToNextHit <= duration){
			Set<Set<RoundEntity>> getCollisions = this.getCollisions();
			for (RoundEntity entity : possibleEntities){
				if (entity instanceof Ship){
					((Ship) entity).thrust(((Ship) entity).getAcceleration(), duration);
				entity.move(timeToNextHit);}
				else
					entity.move(timeToNextHit);
				}
				// moet dit ook automatisch, zodat al degenen die gethrust worden hierin passen?
				// Dan zou een bullet eigenlijk ook gewoon een thrust moeten hebben,
				// maar zou deze altijd 0 moeten zijn...
			for (Set<RoundEntity> collision : getCollisions){
				if (collision.size() == 1){
					RoundEntity entityThatHitWall = (RoundEntity)collision.toArray()[0];
					if (entityThatHitWall instanceof Bullet){
						((Bullet) entityThatHitWall).setNbWallHits(((Bullet) entityThatHitWall).getNbWallHits()+1);
						if (collisionListener != null){
							collisionListener.boundaryCollision(entityThatHitWall, 
																entityThatHitWall.getPositionOfHitWall()[0],
																entityThatHitWall.getPositionOfHitWall()[1]);
						}
					}
					entityThatHitWall.resolveCollision();
					}
				
				else if (collision.size() == 2){
					RoundEntity firstEntity = (RoundEntity) collision.toArray()[0];
					RoundEntity secondEntity = (RoundEntity) collision.toArray()[1];
					if(firstEntity instanceof Bullet || secondEntity instanceof Bullet){
						if (collisionListener != null){
							collisionListener.objectCollision(firstEntity,secondEntity,
															  firstEntity.getCollisionPosition(secondEntity)[0],
															  firstEntity.getCollisionPosition(secondEntity)[1]);
						}
					}
					firstEntity.resolveCollision(secondEntity);
					}
			}
			duration = duration-timeToNextHit;
			timeToNextHit = this.getTimeNextCollision();
			}
		if (duration > 0){
//			for (RoundEntity entity : entities){
			for (RoundEntity entity : getEntities()){
			//TODO map eh
				if (!entity.isTerminated){
					if (entity instanceof Ship){
						((Ship) entity).thrust(((Ship) entity).getAcceleration(), duration);
						entity.move(duration);
						}
					else
						entity.move(duration);
				}
				}
			}
		}

	/**
	 * A method that returns a set of entities of a certain class.
	 * @param 	cls
	 * 			The given classe of which type you want the entities of.
	 * @return	a set of the given class
	 * @throws 	ClassNotFoundException
	 */
	public Set<? extends RoundEntity> getEntityOfClass(Class<?> cls) throws ClassNotFoundException {
		Set<RoundEntity> result = new HashSet<RoundEntity>();
//		Set<RoundEntity> entitiesInThisWorld = this.getEntities();
		Set<RoundEntity> entitiesInThisWorld = (Set<RoundEntity>) this.getEntities();
		//TODO map 
		
		for (RoundEntity entity : entitiesInThisWorld) {
			if (entity.getClass().isAssignableFrom(cls)) {
				result.add(entity);
			}
		}		
		return result;
	}
} 
