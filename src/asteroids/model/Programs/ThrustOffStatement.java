package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class ThrustOffStatement extends ActionStatement{

	public ThrustOffStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void execute() {
		while(this.getProgram().getExecuteTime() < 0.2){
			;
			// Put on hold.
		}
		this.getProgram().getShip().thrustOff();
		this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
	}

}
