package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

public class SkipStatement extends ActionStatement{

	public SkipStatement(SourceLocation location) {
		super(location);
	}

	@Override
	public void execute() {
		;
	}
	// Zal dit zoals we willen effectief niets doen?

}
