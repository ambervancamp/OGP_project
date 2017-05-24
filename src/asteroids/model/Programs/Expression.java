package asteroids.model.Programs;

import asteroids.model.Ship;

public interface Expression<T> {
		
	public abstract T evaluate(Ship ExecutingShip, Function ExecutingFunction) throws ClassNotFoundException;
}
