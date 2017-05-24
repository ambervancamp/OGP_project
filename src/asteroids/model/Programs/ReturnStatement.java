package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement{
	// Kan alleen maar plaatsvinden in een functionbody
	
	private Expression<?> value;
	
	public ReturnStatement(Expression<?> value, SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public Type execute() {
		// Hoe kan je van void over gaan naar return type
		Ship ExecutingShip = this.getProgram().getShip();
		return (Type) this.getValue().evaluate(ExecutingShip);
		// Gewerkt met enum, oke zo met casting?
	}
	// Werken met klasse Optional?

	public Expression<?> getValue() {
		return value;
	}

	public void setValue(Expression<?> value) {
		this.value = value;
	}

}
