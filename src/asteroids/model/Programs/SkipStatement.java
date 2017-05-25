package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class SkipStatement extends ActionStatement{

	public SkipStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void execute() {
		while(this.getProgram().getExecuteTime() < 0.2){
			;
			// Put on hold.
		}
		this.getProgram().setExecuteTime(this.getProgram().getExecuteTime()-0.2);
	}
	// Zal dit zoals we willen effectief niets doen?

}
