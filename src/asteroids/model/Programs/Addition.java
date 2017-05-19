package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class Addition implements Expression{

	private SourceLocation location;
	private Expression e1;
	private Expression e2;

	public Addition(Expression e1, Expression e2, SourceLocation location) {
		this.location = location;
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Double evaluate(){
		// TODO evaluate moet nog argumenten krijgen
		Object value1 = e1.evaluate();
		Object value2 = e2.evaluate();
		if (!(value1 instanceof Double) || !(value2 instanceof Double)){
			throw new IllegalArgumentException();
		}
		Double doubleValue1 = (Double) value1;
		Double doubleValue2 = (Double) value2;		
		return doubleValue1 + doubleValue2;
	}

}
