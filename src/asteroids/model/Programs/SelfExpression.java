package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class SelfExpression extends EntityExpression<Ship>{

	public SelfExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Ship evaluate(Ship ExecutingShip) {
		return ExecutingShip;
	}

}
