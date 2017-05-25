package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 *	A class to represent planetoids as a special kind of minor planets.
 *
 * 	@invar	The total traveled distance of a planetoid will be a valid distance.
 * 			| canHaveAsTotalTraveledDistance(totalTraveledDistance)
 */
public class Planetoid extends MinorPlanet {

	public Planetoid(double x, double y, double xVelocity, double yVelocity, double radius, double totalTraveledDistance)
			throws IllegalArgumentException {
		super(x, y, xVelocity, yVelocity, radius);
		this.density = 0.917*Math.pow(10,12);
		this.mass = 4.0/3.0*Math.PI*Math.pow(this.getRadius(),3)*this.getDensity();
		this.setTotalTraveledDistance(totalTraveledDistance);
	}
	
//	All methods related to the radius of a planetoid
	
	/**
	 * A variable registering the minimum radius of a planetoid.
	 */
	private final double minRadius = 5;
	
	/**
	 * 
	 * A method to check whether the radius is a valid radius
	 * 
	 * @return 	true if and only if the radius minus the distance traveled*0.000001 
	 * 			is larger than the minimum radius.
	 * 
	 */
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

//	All methods related to the mass of a planetoid.
	
	/**
	 * Variable registering the mass of this planetoid.
	 */
	private double mass;
	
	/**
	 * Return the mass of this planetoid computed by its radius and density.
	 * 
	 * @return	Returns the mass of this planetoid.
	 */
	@Override
	public double getMass() {
		return this.mass;
	}
	
//	All methods related to the density of the planetoid.
	
	/**
	 * Variable registering the density of this planetoid.
	 */
	private final double density;	
	
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
	
//	All methods related to the total traveled distance of a planetoid.
	
	/**
	 * A variable registering the total traveled distance of a planetoid.
	 */
	private double totalTraveledDistance;
	
	/**
	 * A mehod to check whether the traveled distance is valid
	 * 
	 * @return	true if and only if the totalTraveledDistance is greater than 0
	 */
	public boolean canHaveAsTotalTraveledDistance(){
		return (this.totalTraveledDistance > 0);
	}
	
	/**
	 * A method to set the totalTraveledDistance with a given amount
	 * 
	 * @param 	totalTraveledDistance
	 * 			the amount we want the totalTraveledDistance to set with.
	 */
	private void setTotalTraveledDistance(double totalTraveledDistance) {
		this.totalTraveledDistance = totalTraveledDistance;
	}
	
	/**
	 * A method to get the totalTraveledDistance of a planetoid
	 * 
	 * @return	the totalTraveledDistance
	 */
	public double getTotalTraveledDistance(){
		return this.totalTraveledDistance;
	}
	

	@Override 
	public void move(double duration){
		if (!canHaveAsDuration(duration))
			throw new IllegalArgumentException();
		setPosition(getPositionAfterMoving(duration)[0],getPositionAfterMoving(duration)[1]);
		this.setTotalTraveledDistance(totalTraveledDistance+this.getSpeed()*duration);
	}
	
	/**
	 * Return the string value of this Planetoid.
	 * 
	 * @return 	The string value of this Planetoid.
	 * 			| result == "Planetoid"
	 */
	@Override
	public String toString(){
		return "Planetoid";
	}	
}
