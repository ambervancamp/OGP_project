package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class IfThenElseStatement extends Statement{
	
	private Expression<? extends Boolean> condition;
	private Statement ifBody;
	private Statement elseBody;
	
	public IfThenElseStatement(Expression<? extends Boolean> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(sourceLocation);
		setIfBody(ifBody);
		ifBody.setProgram(this.getProgram());
		setElseBody(elseBody);
		elseBody.setProgram(this.getProgram());
		setCondition(condition);
	}

	@Override
	public void execute() throws ClassNotFoundException {
		Function ExecutingFunction = this.getFunction();
		Ship ExecutingShip = this.getProgram().getShip();
		if (this.getCondition().evaluate(ExecutingShip, ExecutingFunction))
			this.getIfBody().execute();

		else
			// Else body optional
			if (this.getElseBody() != null)
				this.getElseBody().execute();			
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

	public Expression<? extends Boolean> getCondition() {
		return this.condition;
	}

	public void setCondition(Expression<? extends Boolean> condition) {
		this.condition = condition;
	}

}
