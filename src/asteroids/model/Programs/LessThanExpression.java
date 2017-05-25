package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class LessThanExpression extends BooleanExpression{
	
	private Expression<? extends Double> e1;
	private Expression<? extends Double> e2;
	private SourceLocation sourceLocation;
	
	public LessThanExpression(Expression<? extends Double> e1, Expression<? extends Double> e2, SourceLocation location) {
		setSourceLocation(location);
		setE1(e1);
		setE2(e2);
	}

	@Override
	public Boolean evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		return (this.getE1().evaluate(ExecutingShip, ExecutingFunction) < this.getE2().evaluate(ExecutingShip, ExecutingFunction));
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

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

}
