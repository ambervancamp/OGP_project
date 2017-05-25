package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ParameterExpression implements Expression<Expression<?>> {
	
	private String parameterName;
	private SourceLocation sourceLocation;
	
	public ParameterExpression(String parameterName, SourceLocation sourceLocation) {
		this.setParameterName(parameterName);
		this.setSourceLocation(sourceLocation);
	}

	@Override
	public Expression<?> evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		return ExecutingFunction.getParameters().get(parameterName);
		// Verwachten ze de return van de eigenlijke value of van de expression? .evaluate(ExecutingShip, ExecutingFunction) of niet?
		// Indien toch value: werk met enumeratie Type, en casting
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
