package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement{
	
	private ConstantExpression angle;
	
	public TurnStatement(ConstantExpression angle, SourceLocation location) {
		super(location);
		this.setAngle(angle);		
	}

	@Override
	public void execute() {
		this.getProgram().getShip().turn(this.getAngle().evaluate());
	}

	public ConstantExpression getAngle() {
		return angle;
	}

	public void setAngle(ConstantExpression angle) {
		this.angle = angle;
	}

}
