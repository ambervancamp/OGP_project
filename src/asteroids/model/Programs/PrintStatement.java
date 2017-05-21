package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {
	
	private Expression<Double> value;
	
	public PrintStatement(Expression<Double> value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.setValue(value);
	}

	@Override
	public void execute() {
		System.out.print(this.getValue().toString());	
	}
	// werkt deze to string zoals we het zouden willen?

	public Expression<Double> getValue() {
		return value;
	}

	public void setValue(Expression<Double> value) {
		this.value = value;
	}

}
