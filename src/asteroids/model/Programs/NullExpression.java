package asteroids.model.Programs;

import asteroids.model.RoundEntity;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class NullExpression extends EntityExpression<RoundEntity>{
	// Return type null solved with RoundEntity, okey like this?
	
	public NullExpression(SourceLocation location) {
		super(location);
	}

	@Override
	public RoundEntity evaluate(Ship ExecutingShip, Function ExecutingFunction) {
		return null;
	}
}
