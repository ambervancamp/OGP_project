package asteroids.model.Programs;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class BulletExpression extends EntityExpression<Bullet>{

	public BulletExpression(SourceLocation location) {
		super(location);
	}
	
	@Override
	public Bullet evaluate(Ship ExecutingShip, Function ExecutingFunction) {
		return ExecutingShip.getFiredBullet();
	}
	// Define function that returns one of the fired bullets
}
