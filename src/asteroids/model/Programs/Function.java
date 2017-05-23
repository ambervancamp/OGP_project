package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class Function{
	
	private SourceLocation sourceLocation;
	private Program program;
	private String functionname;
	private Statement body;
	
	public Function(String functionName, Statement body, SourceLocation sourceLocation) {
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
	
	public void execute(){
		
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

	public String getFunctionname() {
		return functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}
}
