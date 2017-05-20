package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 *	A class to represent planetoids as a special kind of minor planets.
 *
 * @author amber_000
 *
 */
public class Planetoid extends MinorPlanet {

	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance)
			throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.density = 0.917*Math.pow(10,12);
		this.mass = 4.0/3.0*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
		this.setTotalTraveledDistance(totalTraveledDistance);
	}
	
	
	public boolean canHaveAsRadius(){
		if (this.radius-this.getTotalTraveledDistance()*0.000001 > minRadius)
			return true;
		return false;
	}
	
	@Override
	public double getRadius(){
		if (!canHaveAsRadius())
			this.terminate();
		return (this.radius-this.getTotalTraveledDistance()*0.000001);
	}	
	// totalTraveledDistance moet gewijzigd worden tijdens zijn beweging
	
	/**
	 * Return the mass of this planetoid computed by its radius and density.
	 * 
	 * @return	Returns the mass of this planetoid.
	 */
	@Override
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Return the density of this planetoid.
	 * 
	 * @return	Returns the density of this planetoid.
	 */
	@Override
	@Basic 
	@Raw 
	@Immutable
	public double getDensity() {
		return this.density;
	}
	
	public double getTotalTraveledDistance(){
		return this.totalTraveledDistance;
	}
	

	private void setTotalTraveledDistance(double totalTraveledDistance) {
		this.totalTraveledDistance = totalTraveledDistance;
	}
	
	@Override 
	public void move(double duration){
		if (!canHaveAsDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
		this.setTotalTraveledDistance(totalTraveledDistance+this.getSpeed()*duration);
	}
	
	/**
	 * Variable registering the density of this planetoid.
	 */
	private final double density;	
	
	/**
	 * Variable registering the mass of this planetoid.
	 */
	private double mass;
	
	private final double minRadius = 5;
	
	private double totalTraveledDistance;
}
