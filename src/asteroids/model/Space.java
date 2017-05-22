package asteroids.model;
 
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
		for (RoundEntity entity : entities)
			this.deleteEntity(entity);
		this.isTerminated = true;
	}
	
	private double width;
	private double height;
	private double maxWidth = Double.MAX_VALUE;
	private double maxHeight = Double.MAX_VALUE;
	
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
		else
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
	 * Check whether the given width is a possible width of this world.
	 * @param 	width
	 * 			The width to be checked
	 * @return	True if and only if this world is termiated,
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
	 * Check whether the given height is a possible height of this world.
	 * @param 	height
	 * 			The height to be checked
	 * @return	True if and only if this world is termiated,
	 * 			or if the given height is effective, not negative and smaller than the maximum width
	 * 			
	 */
	@Raw 
	public boolean canHaveAsHeight(double height){
		if (Double.isNaN(height) || height < 0 || height>maxHeight)
			return false;
		return true;
	}
	
	
	public Set<RoundEntity> getEntities(){
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
				if (firstEntity != secondEntity && firstEntity.getTimeToCollision(secondEntity)!=-0.0){
					if (firstEntity.getTimeToCollision(secondEntity) < smallestTime)
					smallestTime =  firstEntity.getTimeToCollision(secondEntity);
				}
			}
			if (firstEntity.getTimeToHitWall() < smallestTime)
			smallestTime = firstEntity.getTimeToHitWall();
		}
		return Math.abs(smallestTime);
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
		double timeNextCollision = this.getTimeNextCollision();
		for (RoundEntity firstEntity : entities){
			for (RoundEntity secondEntity : entities){
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
	
//	/**
//	 * A method that resolves collisions.
//	 * The method checks whether the given entity hits a wall or an other entity and
//	 * puts this in the collisionListener.
//	 * @param 	collisionListener
//	 * 			The given collisionListener
//	 * @throws 	IllegalArgumentException
//	 * 			if the given entity is terminated 
//	 * 	
//	 */
//	public void resolveCollision(CollisionListener collisionListener) throws IllegalArgumentException{
//		if (this.isTerminated())
//			throw new IllegalArgumentException();
//		if (this.getCollisions().isEmpty())
//			return;
//		for (Set<RoundEntity> collision : this.getCollisions()){
//			if (collision.size() == 1){
//				RoundEntity entityThatHitWall = (RoundEntity)collision.toArray()[0];
//				if (entityThatHitWall instanceof Bullet){
//				collisionListener.boundaryCollision(entityThatHitWall, 
//													entityThatHitWall.getPositionOfHitWall()[0],
//													entityThatHitWall.getPositionOfHitWall()[1]);
//				}
//			entityThatHitWall.setVelocityAfterEntityHitWall();
//			}
//			else if (collision.size() == 2){
//				RoundEntity firstEntity = (RoundEntity) collision.toArray()[0];
//				RoundEntity secondEntity = (RoundEntity) collision.toArray()[1];
//				if (firstEntity instanceof Bullet || secondEntity instanceof Bullet){
//				collisionListener.objectCollision(firstEntity,secondEntity,
//												  firstEntity.getCollisionPosition(secondEntity)[0],
//												  firstEntity.getCollisionPosition(secondEntity)[1]);
//				}
//				firstEntity.getVelocityAfterCollision(secondEntity);
//				
//				if (firstEntity instanceof Planetoid && firstEntity.getRadius() >= 30){
//					double orientation = (-Math.PI/2+Math.random()*Math.PI);
//					double[] spawnPosition = firstEntity.getPosition();
//					double spawnRadius = firstEntity.getRadius()/2;
//					double spawnSpeed = firstEntity.getSpeed()*1.5;
//					double [] spawnVelocity = {Math.cos(orientation)*spawnSpeed,Math.sin(orientation)*spawnSpeed};
//					Asteroid firstSpawn = new Asteroid(spawnPosition[0]+spawnRadius*Math.cos(orientation), 
//														spawnPosition[1]+spawnRadius*Math.sin(orientation),
//														spawnVelocity[0],spawnVelocity[1],spawnRadius);				
//					Asteroid secondSpawn = new Asteroid(spawnPosition[0]-spawnRadius*Math.cos(orientation), 
//														spawnPosition[1]-spawnRadius*Math.sin(orientation),
//														-spawnVelocity[0],-spawnVelocity[1],spawnRadius);
//					firstSpawn.setSpace(this); 
//					secondSpawn.setSpace(this);
//					firstEntity.terminate();
//				}				
//				}
//			}
//		}
	
	
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
		if (this.isTerminated() || duration < 0 || Double.isNaN(duration))
			throw new IllegalArgumentException();
		
		double timeToNextHit = getTimeNextCollision();
		while (timeToNextHit <= duration){
			for (RoundEntity entity : entities){
				if (entity instanceof Ship){
					((Ship) entity).thrust(((Ship) entity).getAcceleration(), duration);
				entity.move(timeToNextHit);}
				else if (entity instanceof Bullet && timeToNextHit == entity.getTimeToHitWall()){
					((Bullet) entity).setNbWallHits(((Bullet) entity).getNbWallHits()+1);
					entity.move(timeToNextHit);
					}
				else
					entity.move(timeToNextHit);
//				System.out.println(entity);
//				System.out.println(entity.getxPosition());
				}
				// moet dit ook automatisch, zodat al degenen die gethrust worden hierin passen?
				// Dan zou een bullet eigenlijk ook gewoon een thrust moeten hebben,
				// maar zou deze altijd 0 moeten zijn...
			for (RoundEntity firstEntity: entities){
				firstEntity.resolveCollision();
//				System.out.println(firstEntity.getxVelocity());
//				System.out.println(firstEntity.getyVelocity());
				for (RoundEntity secondEntity: entities){
					if (firstEntity != secondEntity){
						firstEntity.resolveCollision(secondEntity);
						
					}
				}
			}
			duration = duration-timeToNextHit;
			timeToNextHit = this.getTimeNextCollision();
//			System.out.println(this.getTimeNextCollision());
			}
		if (duration > 0){
			for (RoundEntity entity : entities){
				if (entity instanceof Ship){
					((Ship) entity).thrust(((Ship) entity).getAcceleration(), duration);
					entity.move(duration);
					}
				else
					entity.move(duration);
//				System.out.println(entity);
//				System.out.println(entity.getxVelocity());
//				System.out.println(entity.getxPosition());
				}
			}
		}
	
	// the entities die in een wereld zitten passen beter in de klasse en kunnen gebruikt worden 
	// zonder facade aan te passen of hun testfiles aan te passen, dus hier lijkt mij echt wel beter
	public Set<? extends RoundEntity> getEntityOfClass(Class<?> cls) {
		Set<RoundEntity> result = new HashSet<RoundEntity>();
		Set<RoundEntity> entitiesInThisWorld = this.getEntities();
		
		for (RoundEntity entity : entitiesInThisWorld) {
			if (entity.getClass().isAssignableFrom(cls)) {
				result.add(entity);
			}
		}		
		return result;
	}
	
	/**
	 * a list of all the entities that are located in this world
	 */
	Set<RoundEntity> entities = new HashSet<RoundEntity>();	

	double smallestTimeToCollision = Double.POSITIVE_INFINITY;
} 
