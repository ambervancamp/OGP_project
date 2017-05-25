package asteroids.model.Programs;

import asteroids.model.RoundEntity;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class GetVYExpression implements Expression<Double> {
	
	private Expression<? extends RoundEntity> e;
	private SourceLocation location;
	
	public GetVYExpression(Expression<? extends RoundEntity> e, SourceLocation location) {
		setE(e);
		setLocation(location);	}

	@Override
	public Double evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		return this.getE().evaluate(ExecutingShip, ExecutingFunction).getyVelocity();
	}

	public Expression<? extends RoundEntity> getE() {
		return e;
	}

	public void setE(Expression<? extends RoundEntity> e) {
		this.e = e;
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}

}
