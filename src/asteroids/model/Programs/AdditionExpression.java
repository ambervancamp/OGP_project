package asteroids.model.Programs;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class AdditionExpression implements Expression<Double>{

	private SourceLocation location;
	private Expression<? extends Double> e1;
	private Expression<? extends Double> e2;

	public AdditionExpression(Expression<? extends Double> e1, Expression<? extends Double> e2, SourceLocation location) {
		this.setLocation(location);
		this.setE1(e1);
		this.setE2(e2);
	}
	
	public Double evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException{		
		return this.getE2().evaluate(ExecutingShip, ExecutingFunction) + this.getE1().evaluate(ExecutingShip, ExecutingFunction);
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}

	public Expression<? extends Double> getE2() {
		return e2;
	}

	public void setE2(Expression<? extends Double> e2) {
		this.e2 = e2;
	}

	public Expression<? extends Double> getE1() {
		return e1;
	}

	public void setE1(Expression<? extends Double> e1) {
		this.e1 = e1;
	}

}
