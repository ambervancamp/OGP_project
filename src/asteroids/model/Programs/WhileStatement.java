package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement{
	
	private Expression<Boolean> condition;
	private Statement body;
	
	public WhileStatement(Expression<Boolean> condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		setCondition(condition);
		setBody(body);
	}

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
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
