package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {
	
	private ConstantExpression value;
	
	public PrintStatement(ConstantExpression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setValue(value);
	}

	@Override
	public void execute() {
		Ship ExecutingShip = this.getProgram().getShip();
		System.out.println(this.getValue().evaluate(ExecutingShip).toString());	
	}
	// werkt deze tostring() zoals we het zouden willen?

	public ConstantExpression getValue() {
		return value;
	}

	public void setValue(ConstantExpression value) {
		this.value = value;
	}

}
