package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ConstantExpression implements Expression<Double> {
	
	private double value;
	private SourceLocation location;
	
	public ConstantExpression(double value, SourceLocation location) {
		this.setLocation(location);
		this.setValue(value);
	}

	@Override
	public Double evaluate(Ship ExecutingShip) {
		return this.getValue();
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}

}
