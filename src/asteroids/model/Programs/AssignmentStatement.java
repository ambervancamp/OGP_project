package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends Statement{
	
	private String variableName;
	private Expression<Double> value;
	
	public AssignmentStatement(String variableName, Expression<Double> value, SourceLocation sourceLocation){
		super(sourceLocation);
		setVariableName(variableName);
		setValue(value);
	}
	
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public Expression<Double> getValue() {
		return value;
	}
	public void setValue(Expression<Double> value) {
		this.value = value;
	}
	
	@Override
	public void execute() {
		this.getProgram().getVariables().put(this.getVariableName(), this.getValue());
	}
}
