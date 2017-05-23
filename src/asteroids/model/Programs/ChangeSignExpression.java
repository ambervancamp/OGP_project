package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression implements Expression<Double> {
	// Can meegegeven expression alles zijn of enkel double?
	
	private ConstantExpression expression;
	private SourceLocation sourceLocation;
	
	public ChangeSignExpression(ConstantExpression expression, SourceLocation sourceLocation) {
		this.setExpression(expression);
		this.setSourceLocation(sourceLocation);
	}

	@Override
	public Double evaluate() {
		return -1*(this.getExpression().evaluate());
	}

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
