package asteroids.model.Programs;

import java.util.List;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class FunctionInvocationExpression implements Expression<Expression<?>> {
	// Type T voor expression, wat de evaluate effectief returned.
	
	private SourceLocation sourceLocation;
	private String functionName;
	private List<Expression<?>> actualArgs;
	
	public FunctionInvocationExpression(String functionName, List<Expression<?>> actualArgs, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setFunctionName(functionName);
		setActualArgs(actualArgs);
	}
	// Wat kan actual arguments allemaal zijn?

	@Override
	public Expression<?> evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException {
//		Statement functionBody = ExecutingShip.getProgram().getFunctionsMap().get(functionName);
//		return functionBody.execute();
		List<Function> AllFunctions = ExecutingShip.getProgram().getFunctions();
		for(Function function: AllFunctions){
			if (function.getFunctionname() == this.getFunctionName())
				return function.execute(this.getActualArgs());
				// Moet hier effectief return staan?				
		}
		return null;
		// Will never happen normally.
		// Null value to guarentee always Expression<?> is returned.
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public List<Expression<?>> getActualArgs() {
		return actualArgs;
	}

	public void setActualArgs(List<Expression<?>> actualArgs) {
		this.actualArgs = actualArgs;
	}
	


}
