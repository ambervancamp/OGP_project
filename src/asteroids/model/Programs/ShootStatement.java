package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class ShootStatement extends ActionStatement{

	public ShootStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void execute() {
		while(this.getProgram().getExecuteTime() < 0.2){
			;
			// Put on hold.
		}
		this.getProgram().getShip().fireBullet();
		this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
	}

}
