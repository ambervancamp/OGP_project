package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement{
	
	private BooleanExpression condition;
	private Statement body;
	
	public WhileStatement(BooleanExpression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		setCondition(condition);
		setBody(body);
	}
	
	@Override
	public void execute() throws ClassNotFoundException {
		Function ExecutingFunction = this.getFunction();
		Ship ExecutingShip = this.getProgram().getShip();

		while(this.getCondition().evaluate(ExecutingShip, ExecutingFunction))
			this.getBody().execute();
	}
	
//	new BreakStatement(sourceLocation){
//		
//	};
	
	// Inner class. Breakstatement can only occur in a while loop.
	public class BreakStatement extends Statement{

		public BreakStatement(SourceLocation sourceLocation) {
			super(sourceLocation);
		}

		@Override
		public void execute() throws ClassNotFoundException {
			break;		
		}
	}
	
	public BooleanExpression getCondition() {
		return condition;
	}

	public void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}

	public Statement getBody() {
		return body;
	}

	public void setBody(Statement body) {
		this.body = body;
	}

}
