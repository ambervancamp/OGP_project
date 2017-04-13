package asteroids.model;

public class Collision {

	/**
	 * a method to check whether two entities may collide
	 * @param 	firstEntity
	 * 			The first entity that could collide
	 * @param 	secondEntity
	 * 			The second entity that could collide
	 * @return	True if and only if the 2 entities exist and are in the some world
	 * 			|!firstEntity.isTerminated() &&
	 * 			| !secondEntity.isTerminated() &&
	 * 			| firstEntity.getSpace() == secondEntity.getSpace()
	 */
	public boolean canAsCollision(RoundEntity firstEntity,RoundEntity secondEntity){
		if (!firstEntity.isTerminated() && !secondEntity.isTerminated() && firstEntity.getSpace() == secondEntity.getSpace())
			return true;
		return false;
	}
	
	/**
	 * a method to check whether an entity may collide with the 'wall' of the world
	 * @param 	firstEntity
	 * 			The entity that could hit the 'wall' of the world.
	 * @param 	Space
	 * 			The world to which 'wall' the entity may collide.
	 * @return	True if and only if the entity and space exist and the entity is located in the world
	 * 			|!Entity.isTerminated() &&
	 * 			| !space.isTerminated() &&
	 * 			| Entity.getSpace() == space
	 */
	public boolean canHitWall(RoundEntity entity,Space space){
		if (!entity.isTerminated() && !space.isTerminated() && entity.getSpace() == space)
			return true;
		return false;
	}
}
