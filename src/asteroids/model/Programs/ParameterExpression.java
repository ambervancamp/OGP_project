package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ParameterExpression implements Expression<Type> {
	
	private String parameterName;
	private SourceLocation sourceLocation;
	
	public ParameterExpression(String parameterName, SourceLocation sourceLocation) {
		this.setParameterName(parameterName);
		this.setSourceLocation(sourceLocation);
	}

	@Override
	public Type evaluate(Ship ExecutingShip) {
		return (Type) this.getParameterName();
	}
	// return the value of the parameter??

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
