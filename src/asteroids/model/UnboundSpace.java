package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;


public class UnboundSpace extends Space{
	
	@Raw
	public UnboundSpace(){
		super(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY);
	}
}