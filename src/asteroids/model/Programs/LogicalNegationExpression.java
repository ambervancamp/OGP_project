package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class LogicalNegationExpression extends BooleanExpression {
		
	private SourceLocation sourceLocation;
	private BooleanExpression expression;
	
	public LogicalNegationExpression(BooleanExpression expression, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setExpression(expression);
	}

	@Override
	public Boolean evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		return !this.getExpression().evaluate(ExecutingShip, ExecutingFunction);
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public BooleanExpression getExpression() {
		return expression;
	}

	public void setExpression(BooleanExpression expression) {
		this.expression = expression;
	}

}
