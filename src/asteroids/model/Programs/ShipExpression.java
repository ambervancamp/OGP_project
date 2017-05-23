package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ShipExpression extends EntityExpression<Ship>{
	
	public ShipExpression(SourceLocation location) {
		super(location);
	}

	@Override
	public Ship evaluate(Ship ExecutingShip) throws ClassNotFoundException {
		return (Ship) ExecutingShip.getClosestEntityOfClass("Ship");
	}
}
