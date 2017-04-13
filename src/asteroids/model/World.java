package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;

public class World extends Space {
	
	@Raw 
	public World(double width, double height) throws IllegalArgumentException{
		super(width,height);
	}

}
