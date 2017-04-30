package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public  class MinorPlanet extends RoundEntity{

	@Raw
	public MinorPlanet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		
		UnboundSpace unboundspace = new UnboundSpace();
		this.placeInSpace(unboundspace);
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getMinRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDensity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
