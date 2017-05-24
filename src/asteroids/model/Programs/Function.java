package asteroids.model.Programs;

import java.util.HashMap;
import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class Function{
	
	private SourceLocation sourceLocation;
	private String functionname;
	private Statement body;
	
	private Program program;
	private HashMap <String, Expression<?>> parameters = new HashMap<>();
	// extra map met parameters
	
	public Function(String functionName, Statement body, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setFunctionname(functionName);
		setBody(body);
		body.setFunction(this);
		// Verwijzen nu alle statements in body ook naar deze functie? -> in blockstatement ook verwijzingen
	}
	
	public Type execute(List<Expression<?>> actualArgs) throws ClassNotFoundException{	
		for(int i = 1; i <= actualArgs.size(); i++){
			this.getParameters().put("$" + Integer.toString(i), (Expression<?>)actualArgs.toArray()[i-1]);
		}
		this.getBody().execute();
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
	
	public HashMap <String, Expression<?>> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap <String, Expression<?>> parameters) {
		this.parameters = parameters;
	}
}
