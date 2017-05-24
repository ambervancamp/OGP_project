package asteroids.model.Programs;

import asteroids.model.RoundEntity;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class AnyExpression extends EntityExpression<RoundEntity>{

	public AnyExpression(SourceLocation location) {
		super(location);
	}

	@Override
	public RoundEntity evaluate(Ship ExecutingShip, Function ExecutingFunction) {
		return ExecutingShip.getAnyEntity();
	}

}
