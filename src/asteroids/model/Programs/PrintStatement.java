package asteroids.model.Programs;

import asteroids.model.RoundEntity;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {
	
	private Expression<?> value;
	
	public PrintStatement(Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setValue(value);
	}

	@Override
	public void execute() throws ClassNotFoundException {
		Function ExecutingFunction = this.getFunction();
		Ship ExecutingShip = this.getProgram().getShip();
		this.getProgram().getPrintResults().add(this.getValue().evaluate(ExecutingShip, ExecutingFunction));
		
		if(this.getValue().evaluate(ExecutingShip, ExecutingFunction) instanceof RoundEntity)
			// When entity: Individual toString methods for entities. Also check if null.
			System.out.println(this.getValue().evaluate(ExecutingShip, ExecutingFunction) == null ? "null" : this.getValue().evaluate(ExecutingShip, ExecutingFunction).toString());		
		else	
			// When boolean or double.
			System.out.println(String.valueOf(this.getValue().evaluate(ExecutingShip, ExecutingFunction)));	
	}

	public Expression<?> getValue() {
		return value;
	}

	public void setValue(Expression<?> value) {
		this.value = value;
	}
}
