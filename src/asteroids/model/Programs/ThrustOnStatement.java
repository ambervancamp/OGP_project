package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class ThrustOnStatement extends ActionStatement{

	public ThrustOnStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void execute() {
		this.getProgram().getShip().thrustOn();
	}

}
