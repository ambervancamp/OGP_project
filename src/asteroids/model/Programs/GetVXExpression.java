package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class GetVXExpression implements Expression<Double> {
	
	private EntityExpression e;
	private SourceLocation location;
	
	public GetVXExpression(EntityExpression e, SourceLocation location) {
		setE(e);
		setLocation(location);
	}

	@Override
	public Double evaluate(Ship ExecutingShip) {
		return this.getE().evaluate(ExecutingShip).getxVelocity();
	}

	public EntityExpression getE() {
		return e;
	}

	public void setE(EntityExpression e) {
		this.e = e;
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}

}
