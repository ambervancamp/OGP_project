package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class EqualToExpression extends BooleanExpression{
	
	private Expression<?> e1;
	private Expression<?> e2;
	private SourceLocation sourceLocation;
	
	public EqualToExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		setSourceLocation(location);
		setE1(e1);
		setE2(e2);
	}

	@Override
	public Boolean evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		if (this.getE1().getClass() != this.getE2().getClass())
			return false;
		return (this.getE1().evaluate(ExecutingShip, ExecutingFunction) == this.getE2().evaluate(ExecutingShip, ExecutingFunction));
	}

	public Expression<?> getE1() {
		return e1;
	}

	public void setE1(Expression<?> e1) {
		this.e1 = e1;
	}

	public Expression<?> getE2() {
		return e2;
	}

	public void setE2(Expression<?> e2) {
		this.e2 = e2;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
