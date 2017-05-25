package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression implements Expression<ConstantExpression> {
	
	private ConstantExpression expression;
	private SourceLocation sourceLocation;
	
	// Assumed that it can only be a constantExpression.
	public ChangeSignExpression(ConstantExpression expression, SourceLocation sourceLocation) {
		this.setExpression(expression);
		this.setSourceLocation(sourceLocation);
	}

	@Override
	public ConstantExpression evaluate(Ship ExecutingShip, Function ExecutingFunction) {
		this.getExpression().setValue(-1*(this.getExpression().evaluate(ExecutingShip,ExecutingFunction)));
		return this.getExpression();
	}
	// Evaluate to the expression, not the value. But it is the value that is negated.

	public ConstantExpression getExpression() {
		return expression;
	}

	public void setExpression(ConstantExpression expression) {
		this.expression = expression;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

}
