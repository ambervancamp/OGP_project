package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement{
	
	private ConstantExpression angle;
	
	public TurnStatement(ConstantExpression angle, SourceLocation location) {
		super(location);
		this.setAngle(angle);		
	}

	@Override
	public void execute() {
		Ship ExecutingShip = this.getProgram().getShip();
		this.getProgram().getShip().turn(this.getAngle().evaluate(ExecutingShip));
	}

	public ConstantExpression getAngle() {
		return angle;
	}

	public void setAngle(ConstantExpression angle) {
		this.angle = angle;
	}

}
