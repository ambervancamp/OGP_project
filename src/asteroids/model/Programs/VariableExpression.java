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
	public Expression<?> evaluate(Ship ExecutingShip) {
		HashMap<String, Expression<?>> variables = ExecutingShip.getProgram().getVariables();
		return variables.get(this.getVariableName());
		// De constantExpression nog executen of niet? 
		// Moet deze functie de expression returnen, of de waarde achter de expression?
		// Let op, expression<?> kan nog steeds eender welk type zijn, dus na execute
		// staat type niet vast
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
