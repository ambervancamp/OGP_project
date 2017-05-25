package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class BreakStatement extends Statement{

	public BreakStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public void execute() throws ClassNotFoundException {
		this.getProgram().setIsBreaking(true);
	}

}
