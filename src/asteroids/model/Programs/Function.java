package asteroids.model.Programs;

import java.util.HashMap;
import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class Function{
	
	private SourceLocation sourceLocation;
	private String functionname;
	private Statement body;
	
	private Boolean returnReached = false;
	private Type ReturnValue;	
	private Program program;
	private HashMap <String, Expression<?>> parameters = new HashMap<>();
	// Map with parameters belonging to this function.
	
	public Function(String functionName, Statement body, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setFunctionname(functionName);
		setBody(body);
		body.setFunction(this);
	}
	
	public Type execute(List<Expression<?>> actualArgs) throws ClassNotFoundException{	
		for(int i = 1; i <= actualArgs.size(); i++){
			this.getParameters().put("$" + Integer.toString(i), (Expression<?>)actualArgs.toArray()[i-1]);
		}
		while(!this.getReturnReached()){
			this.getBody().execute();
		}
		return this.getReturnValue();
	}
	// Function execution always ends with a return.
	
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
	
	public Boolean getReturnReached() {
		return this.returnReached;
	}

	public void setReturnReached(Boolean returnReached) {
		this.returnReached = returnReached;
	}

	public Type getReturnValue() {
		return ReturnValue;
	}

	public void setReturnValue(Type returnValue) {
		ReturnValue = returnValue;
	}
}
