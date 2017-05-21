package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class VariableExpression implements Expression<String> {
	
	private String variableName;
	private SourceLocation location;
	
	public VariableExpression(String variableName, SourceLocation sourceLocation) {
		this.setLocation(location);
		this.setVariableName(variableName);
	}
	
	@Override
	public String evaluate() {
		return this.getVariableName();
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}


	public String getVariableName() {
		return variableName;
	}


	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
}
