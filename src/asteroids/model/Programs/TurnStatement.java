package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement{
	
	private Expression<Double> angle;
	
	public TurnStatement(Expression angle, SourceLocation location) {
		super(location);
		this.setAngle(angle);		
	}

	@Override
	public void execute() {
		this.getProgram().getShip().turn(this.getAngle());
	}

	public Expression<Double> getAngle() {
		return angle;
	}

	public void setAngle(Expression<Double> angle) {
		this.angle = angle;
	}

}
