package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public abstract class Statement {
	
	private SourceLocation sourceLocation;
	private Program program;
	private Function function = null;
	
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
	
	public abstract void execute() throws ClassNotFoundException;

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
}
