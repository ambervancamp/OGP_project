package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;

public class UnboundSpace extends Space{
	
	@Raw
	public UnboundSpace(double width,double height){
		super(width,height);
	}
}