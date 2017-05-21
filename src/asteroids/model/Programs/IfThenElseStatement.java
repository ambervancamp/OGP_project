package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class IfThenElseStatement extends Statement{
	
	private Expression<Boolean> condition;
	private Statement ifBody;
	private Statement elseBody;
	
	public IfThenElseStatement(Expression<Boolean> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(sourceLocation);
		setIfBody(ifBody);
		setElseBody(elseBody);
		setCondition(condition);
	}

	@Override
	public void execute() {
		if (this.getCondition())
			this.ifBody.execute();

		else
			// Else body optional
			if (this.elseBody != null)
				this.elseBody.execute();			
	}

	public Statement getElseBody() {
		return elseBody;
	}

	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}

	public Statement getIfBody() {
		return ifBody;
	}

	public void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
	}

	public Expression<Boolean> getCondition() {
		return this.condition;
	}

	public void setCondition(Expression<Boolean> condition) {
		this.condition = condition;
	}

}
