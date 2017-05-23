package asteroids.model.Programs;

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
	
	public Double evaluate(){
		// TODO evaluate moet nog argumenten krijgen
//		Object value1 = e1.evaluate();
//		Object value2 = e2.evaluate();
//		if (!(value1 instanceof Double) || !(value2 instanceof Double)){
//			throw new IllegalArgumentException();
//		}
//		Double doubleValue1 = (Double) value1;
//		Double doubleValue2 = (Double) value2;		
		return this.getE2().evaluate() + this.getE1().evaluate();
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
