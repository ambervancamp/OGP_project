package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class AdditionExpression implements Expression<Double>{

	private SourceLocation location;
	private ConstantExpression e1;
	private ConstantExpression e2;

	public AdditionExpression(ConstantExpression e1, ConstantExpression e2, SourceLocation location) {
		this.setLocation(location);
		this.setE1(e1);
		this.setE2(e2);
	}
	
	public Double evaluate(Ship ExecutingShip, Function ExecutingFunction){		
		return this.getE2().evaluate(ExecutingShip, ExecutingFunction) + this.getE1().evaluate(ExecutingShip, ExecutingFunction);
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}

	public ConstantExpression getE2() {
		return e2;
	}

	public void setE2(ConstantExpression e2) {
		this.e2 = e2;
	}

	public ConstantExpression getE1() {
		return e1;
	}

	public void setE1(ConstantExpression e1) {
		this.e1 = e1;
	}

}
