package asteroids.model.Programs;

import asteroids.model.MinorPlanet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetExpression extends EntityExpression<MinorPlanet>{

	public PlanetExpression(SourceLocation location) {
		super(location);
	}

	@Override
	public MinorPlanet evaluate(Ship ExecutingShip) {
		return ExecutingShip.getClosestEntityOfClass(MinorPlanet);
	}

}
