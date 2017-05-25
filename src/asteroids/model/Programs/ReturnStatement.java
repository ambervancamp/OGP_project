package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement{
	// Can only take place in a function body.
	
	private Expression<?> value;
	
	public ReturnStatement(Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public void execute() throws ClassNotFoundException {
		// Effective return happens in Function.
		Function ExecutingFunction = this.getFunction();
		Ship ExecutingShip = this.getProgram().getShip();		
		ExecutingFunction.setReturnValue(this.getValue());
		// .evaluate(ExecutingShip, ExecutingFunction)
		ExecutingFunction.setReturnReached(true);
	}
	// Werken met klasse Optional?

	public Expression<?> getValue() {
		return value;
	}

	public void setValue(Expression<?> value) {
		this.value = value;
	}

}
