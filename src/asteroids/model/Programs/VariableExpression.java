package asteroids.model.Programs;

import java.util.HashMap;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class VariableExpression implements Expression<Type> {
	
	private String variableName;
	private SourceLocation location;
	
	public VariableExpression(String variableName, SourceLocation sourceLocation) {
		this.setLocation(location);
		this.setVariableName(variableName);
	}
	
	@Override
	public Type evaluate(Ship ExecutingShip) {
		HashMap<String, Expression<?>> variables = ExecutingShip.getProgram().getVariables();
		return (Type) variables.get(this.getVariableName()).evaluate(ExecutingShip);
		// Gewerkt met enumeratie
		// Oke zo met casting?
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
