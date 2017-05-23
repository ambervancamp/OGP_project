package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class MultiplicationExpression implements Expression<Double> {
	
	private SourceLocation sourceLocation;
	private ConstantExpression e1;
	private ConstantExpression e2;
	
	public MultiplicationExpression(ConstantExpression e1, ConstantExpression e2, SourceLocation location) {
		setSourceLocation(location);
		setE1(e1);
		setE2(e2);
	}

	@Override
	public Double evaluate(Ship ExecutingShip) {
		return this.getE2().evaluate(ExecutingShip) * this.getE1().evaluate(ExecutingShip);
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public ConstantExpression getE1() {
		return e1;
	}

	public void setE1(ConstantExpression e1) {
		this.e1 = e1;
	}

	public ConstantExpression getE2() {
		return e2;
	}

	public void setE2(ConstantExpression e2) {
		this.e2 = e2;
	}

}
