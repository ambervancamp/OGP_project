package asteroids.model.Programs;

import asteroids.model.Ship;

public interface Expression<T> {
	// T = type = boolean/entity/variable
	// enkel wijzer naar statement	
		
	public abstract T evaluate(Ship ExecutingShip) throws ClassNotFoundException;
}
