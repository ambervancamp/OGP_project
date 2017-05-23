package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement{
	
	private Expression value;
	
	// Hoe het type definieren dat value moet zijn? Moet een expression zijn..
	public ReturnStatement(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Expression execute() {
		return this.getValue().evaluate();
		// Moet de expression gereturned worden, of moet de Value eerst
		// geevalueerd worden en dan gereturned?
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

}
