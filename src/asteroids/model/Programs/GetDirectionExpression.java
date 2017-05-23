package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression implements Expression<Double>  {
	// How to get acces to program running Ship
	
	private SourceLocation location;
	
	public GetDirectionExpression(SourceLocation location) {
		setLocation(location);	
	}

	@Override
	public Double evaluate() {
		//return this..evaluate().getOrientation();
	}

	public SourceLocation getLocation() {
		return location;
	}

	public void setLocation(SourceLocation location) {
		this.location = location;
	}
}
