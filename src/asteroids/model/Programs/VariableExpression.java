package asteroids.model.Programs;

import java.util.HashMap;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class VariableExpression implements Expression<Expression<?>> {
	
	private String variableName;
	private SourceLocation location;
	
	public VariableExpression(String variableName, SourceLocation sourceLocation) {
		this.setLocation(location);
		this.setVariableName(variableName);
	}
	
	@Override
	public Expression<?> evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
		HashMap<String, Expression<?>> variables = ExecutingShip.getProgram().getVariables();
		return variables.get(this.getVariableName());
		// Verwachten ze de return van de eigenlijke value of van de expression? .evaluate(ExecutingShip, ExecutingFunction) of niet?
		// Indien toch value: werk met enumeratie Type, en casting
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
