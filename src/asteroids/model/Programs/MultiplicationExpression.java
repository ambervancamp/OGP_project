package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class MultiplicationExpression implements Expression<Double> {
	
	private SourceLocation sourceLocation;
	private Expression<? extends Double> e1;
	private Expression<? extends Double> e2;
	
	public MultiplicationExpression(Expression<? extends Double> e1, Expression<? extends Double> e2, SourceLocation location) {
		setSourceLocation(location);
		setE1(e1);
		setE2(e2);
	}

	@Override
	public Double evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		return this.getE2().evaluate(ExecutingShip, ExecutingFunction) * this.getE1().evaluate(ExecutingShip, ExecutingFunction);
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public Expression<? extends Double> getE1() {
		return e1;
	}

	public void setE1(Expression<? extends Double> e1) {
		this.e1 = e1;
	}

	public Expression<? extends Double> getE2() {
		return e2;
	}

	public void setE2(Expression<? extends Double> e2) {
		this.e2 = e2;
	}

}
