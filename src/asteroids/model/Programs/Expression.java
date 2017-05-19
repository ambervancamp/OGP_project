package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

//interface met generische parameter T
public interface Expression<T> {
	// T = type = boolean/entity/variable
	// enkel wijzer naar statement
	
	//Statement statement = new Statement(new SourceLocation(0,0));	
	
	
	public abstract Object evaluate();

}
