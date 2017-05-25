package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends Statement{
	
	private String variableName;
	private Expression<?> value;
	
	public AssignmentStatement(String variableName, Expression<?> value, SourceLocation sourceLocation){
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
	public Expression<?> getValue() {
		return value;
	}
	public void setValue(Expression<?> value) {
		this.value = value;
	}
	
	@Override
	public void execute() {
		this.getProgram().getVariables().put(this.getVariableName(), this.getValue());
	}
}
