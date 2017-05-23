package asteroids.model.Programs;

import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetoidExpression extends EntityExpression<Planetoid>{

	public PlanetoidExpression(SourceLocation location) {
		super(location);
	}

	@Override
	public Planetoid evaluate(Ship ExecutingShip) throws ClassNotFoundException {
		return (Planetoid) ExecutingShip.getClosestEntityOfClass("Planetoid");
	}

}
