package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement{
	
	private Expression<Double> angle;
	
	public TurnStatement(Expression<Double> angle, SourceLocation location) {
		super(location);
		this.setAngle(angle);		
	}

	@Override
	public void execute() throws ClassNotFoundException {
		Function ExecutingFunction = this.getFunction();
		Ship ExecutingShip = this.getProgram().getShip();
		while(this.getProgram().getExecuteTime() < 0.2){
			;
			// Put on hold.
		}
		this.getProgram().getShip().turn(this.getAngle().evaluate(ExecutingShip,ExecutingFunction));
		this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
	}

	public Expression<Double> getAngle() {
		return angle;
	}

	public void setAngle(Expression<Double> angle) {
		this.angle = angle;
	}

}
