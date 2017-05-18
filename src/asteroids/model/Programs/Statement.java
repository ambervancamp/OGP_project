package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class Statement {
	
	private SourceLocation sourceLocation;
	private Program program;
	
	public Statement(SourceLocation sourceLocation){
		this.setSourceLocation(sourceLocation);
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
