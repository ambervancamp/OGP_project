package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class LogicalNegationExpression extends BooleanExpression {
		
	private SourceLocation sourceLocation;
	private Expression<? extends Boolean> expression;
	
	public LogicalNegationExpression(Expression<? extends Boolean> expression, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setExpression(expression);
	}

	@Override
	public Boolean evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		return !this.getExpression().evaluate(ExecutingShip, ExecutingFunction);
	}
	// Evaluates to boolean.

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public Expression<? extends Boolean> getExpression() {
		return expression;
	}

	public void setExpression(Expression<? extends Boolean> expression) {
		this.expression = expression;
	}

}
