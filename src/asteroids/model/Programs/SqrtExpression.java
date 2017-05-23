package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class SqrtExpression implements Expression<Double> {
	
	private ConstantExpression e;
	private SourceLocation location;
	
	public SqrtExpression(ConstantExpression e, SourceLocation location) {
		setLocation(location);
		setE(e);
	}

	@Override
	public Double evaluate() {
		return Math.sqrt(this.getE().evaluate());
	}

	public ConstantExpression getE() {
		return e;
	}

	public void setE(ConstantExpression e) {
		this.e = e;
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}

}
