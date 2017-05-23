package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class EqualToExpression extends BooleanExpression{
	// Equalto moet toepasbaar zijn op elk mogelijk object
	
	private Object e1;
	private Object e2;
	private SourceLocation sourceLocation;
	
	public EqualToExpression(Object e1, Object e2, SourceLocation location) {
		setSourceLocation(location);
		setE1(e1);
		setE2(e2);
	}

	@Override
	public Boolean evaluate() {
		if (this.getE1().getClass() != this.getE2().getClass())
			return false;
		if (this.getE1() instanceof Expression && this.getE2() instanceof Expression)
			return (((Expression) this.getE1()).evaluate() == ((Expression) this.getE2()).evaluate());
			// Oke zo met het casten?
		else
			return this.getE1().equals(this.getE2());
	}

	public Object getE1() {
		return e1;
	}

	public void setE1(Object e1) {
		this.e1 = e1;
	}

	public Object getE2() {
		return e2;
	}

	public void setE2(Object e2) {
		this.e2 = e2;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
