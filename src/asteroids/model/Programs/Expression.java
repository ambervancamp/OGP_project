package asteroids.model.Programs;

import asteroids.part3.programs.SourceLocation;

//interface met generische parameter T
public interface Expression<T> {
	// T = type = boolean/entity/variable
	// enkel wijzer naar statement	
	
	public abstract T evaluate();

}
