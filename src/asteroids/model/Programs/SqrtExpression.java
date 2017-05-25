package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class SqrtExpression implements Expression<Double> {
	
	private Expression<Double> e;
	private SourceLocation location;
	
	public SqrtExpression(Expression<Double> e, SourceLocation location) {
		setLocation(location);
		setE(e);
	}

	@Override
	public Double evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		return Math.sqrt(this.getE().evaluate(ExecutingShip, ExecutingFunction));
	}

	public Expression<Double> getE() {
		return e;
	}

	public void setE(Expression<Double> e) {
		this.e = e;
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}

}
