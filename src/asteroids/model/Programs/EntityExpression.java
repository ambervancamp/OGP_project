package asteroids.model.Programs;

import asteroids.model.RoundEntity;
import asteroids.part3.programs.SourceLocation;

public abstract class EntityExpression<T extends RoundEntity> implements Expression<T>{
	// Hoe definieren dat entityExpression een RoundEntity teruggeeft, maar
	// subklassen geven afzonderlijk ship, asteroid, bullet,... terug?
		
	private SourceLocation sourceLocation;
	
	public EntityExpression(SourceLocation sourceLocation){
		this.setSourceLocation(sourceLocation);
	}
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	// Use higher order functions (stream)
}
