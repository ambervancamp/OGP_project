package asteroids.model.Programs;

import asteroids.model.Asteroid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class AsteroidExpression extends EntityExpression<Asteroid>{

	public AsteroidExpression(SourceLocation location) {
		super(location);
	}

	@Override
	public Asteroid evaluate(Ship ExecutingShip) throws ClassNotFoundException {
		return (Asteroid) ExecutingShip.getClosestEntityOfClass(Asteroid.class);
	}

}
