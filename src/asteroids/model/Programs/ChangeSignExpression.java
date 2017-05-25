package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression implements Expression<Double> {
	
	private Expression<? extends Double> expression;
	private SourceLocation sourceLocation;
	
	// Assumed that it can only be a constantExpression.
	public ChangeSignExpression(Expression<? extends Double> expression, SourceLocation sourceLocation) {
		this.setExpression(expression);
		this.setSourceLocation(sourceLocation);
	}

	@Override
	public Double evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
//		this.getExpression().setValue(-1*(this.getExpression().evaluate(ExecutingShip,ExecutingFunction)));
//		return this.getExpression();
		return -1*(this.getExpression().evaluate(ExecutingShip,ExecutingFunction));
	}
	// Evaluate to the expression, not the value. But it is the value that is negated.

	public Expression<? extends Double> getExpression() {
		return expression;
	}

	public void setExpression(Expression<? extends Double> expression) {
		this.expression = expression;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

}
