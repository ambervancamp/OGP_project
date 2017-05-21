package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement{
	
	private Expression<Object> value;
	// Deze value kan een aantal dingen zijn , maar ook niet eender wat,
	// hoe checken?
	
	public ReturnStatement(Expression<Object> value, SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Expression<Object> execute() {
		return this.getValue();
	}
	// Moet iets returnen, maar staat gedef als void

	public Expression<Object> getValue() {
		return value;
	}

	public void setValue(Expression<Object> value) {
		this.value = value;
	}

}
