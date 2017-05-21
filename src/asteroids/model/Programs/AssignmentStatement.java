package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends Statement{
	
	private String variableName;
	private ConstantExpression value;
	
	public AssignmentStatement(String variableName, ConstantExpression value, SourceLocation sourceLocation){
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
	public ConstantExpression getValue() {
		return value;
	}
	public void setValue(ConstantExpression value) {
		this.value = value;
	}
	
	@Override
	public void execute() {
		this.getProgram().getVariables().put(this.getVariableName(), this.getValue());
	}
}
