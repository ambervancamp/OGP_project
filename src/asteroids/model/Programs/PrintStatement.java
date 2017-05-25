package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {
	
	private Expression<Type> value;
	
	public PrintStatement(Expression<Type> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setValue(value);
	}

	@Override
	public void execute() throws ClassNotFoundException {
		Function ExecutingFunction = this.getFunction();
		Ship ExecutingShip = this.getProgram().getShip();
		this.getProgram().getPrintResults().add(this.getValue().evaluate(ExecutingShip, ExecutingFunction));
		System.out.println(this.getValue().evaluate(ExecutingShip, ExecutingFunction) == null ? "" : this.getValue().evaluate(ExecutingShip, ExecutingFunction).toString());	
	}
	// werkt deze tostring() zoals we het zouden willen?

	public Expression<Type> getValue() {
		return value;
	}

	public void setValue(Expression<Type> value) {
		this.value = value;
	}
}
