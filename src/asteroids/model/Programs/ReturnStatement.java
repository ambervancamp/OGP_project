package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement{
	
	private Expression<?> value;
	
	// Hoe het type definieren dat value moet zijn? Moet een expression zijn..
	public ReturnStatement(Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	// Returned een van de volgende types: double, boolean, entity -> enum maken
	@Override
	public Object execute() {
		Ship ExecutingShip = this.getProgram().getShip();
		return this.getValue().evaluate(ExecutingShip);
		// Moet de expression gereturned worden, of moet de Value eerst
		// geevalueerd worden en dan gereturned?
	}

	public Expression<?> getValue() {
		return value;
	}

	public void setValue(Expression<?> value) {
		this.value = value;
	}

}
