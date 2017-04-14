package asteroids.model;

import be.kuleuven.cs.som.annotate.Raw;

@Value
public class UnboundSpace extends Space{
	
	@Raw
	public UnboundSpace(double width,double height){
		super(width,height);
	}
}