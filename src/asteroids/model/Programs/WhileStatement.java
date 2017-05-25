package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement{
	
	private Expression<Boolean> condition;
	private Statement body;
	
	public WhileStatement(Expression<Boolean> condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		setCondition(condition);
		setBody(body);
		body.setProgram(this.getProgram());
	}
	
	@Override
	public void execute() throws ClassNotFoundException {
		Function ExecutingFunction = this.getFunction();
		Ship ExecutingShip = this.getProgram().getShip();

		while(this.getCondition().evaluate(ExecutingShip, ExecutingFunction) && !this.getProgram().getIsBreaking())
			this.getBody().execute();
		
		if(this.getProgram().getIsBreaking())
			this.getProgram().setIsBreaking(false);
	}
	
//	//enclosingObject.new InnerClassName(…
//	
//	// Inner class. Breakstatement can only occur in a while loop.
//	public class BreakStatement extends Statement{
//		
//		
//		
//		public BreakStatement(SourceLocation sourceLocation) {
//			super(sourceLocation);
//		}
//
//		@Override
//		public void execute() throws ClassNotFoundException {
//			break;		
//		}
//	}
	
	public Expression<Boolean> getCondition() {
		return condition;
	}

	public void setCondition(Expression<Boolean> condition) {
		this.condition = condition;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

}
