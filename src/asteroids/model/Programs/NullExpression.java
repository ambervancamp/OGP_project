package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class NullExpression extends EntityExpression<Null>{
	// How to get return type null?
	
	public NullExpression(SourceLocation location) {
		super(location);
	}

	@Override
	public Object evaluate(Ship ExecutingShip) {
		return null;
	}

}
