package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class ThrustOffStatement extends ActionStatement{

	public ThrustOffStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void execute() {
		this.getProgram().getShip().thrustOff();
	}

}
