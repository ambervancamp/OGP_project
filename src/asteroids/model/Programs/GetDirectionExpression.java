package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression implements Expression<Double> {
	
	private SourceLocation location;
	
	public GetDirectionExpression(SourceLocation location) {
		setLocation(location);	
	}

	@Override
	public Double evaluate(Ship ExecutingShip) {
		return ExecutingShip.getOrientation();
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}
}
