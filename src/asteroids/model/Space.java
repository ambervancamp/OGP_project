package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of possible worlds a ship can be located in
 * 
 * @invar 
 * 
 * @version 1.0
 * 
 * @author Amber Van Camp & Jasper Vanmeerbeeck
 *
 */
public abstract class Space {
	
	/**
	 * Initialize this new world with width and height
	 * 
	 * @param 	width
	 * 			the width of this world
	 * @param	height
	 * 			the height of this world
	 * 
	 */
	@Raw 
	@Model
	protected Space(double width, double height) throws IllegalArgumentException{
		setWidth(width);
		setHeight(height);
	}
	
	@Basic
	public boolean isTerminated(){
		if (this.getHeight() == Double.NaN && this.getWidth()== Double.NaN)
			return false;
		return true;
	}
	
	public void terminate(){
		/**
		 * de schepen en kogels nog uit deze wereld verwijderen?
		 */
		this.width = Double.NaN;
		this.height = Double.NaN;
	}
	
	private double width = Double.POSITIVE_INFINITY;
	private double height = Double.POSITIVE_INFINITY;
	private double maxWidth = Double.POSITIVE_INFINITY;
	private double maxHeight = Double.POSITIVE_INFINITY;
	
	
	public void setWidth(double width) throws IllegalArgumentException{
		if (!canHaveAsWidth(width))
			throw new IllegalArgumentException();
		this.width = width;
	}
	
	public void setHeight(double height) throws IllegalArgumentException{
		if (!canHaveAsWidth(width))
			throw new IllegalArgumentException();
		this.height = height;
	}
	
	@Basic
	public double getWidth(){
		return this.width;
	}
	@Basic 
	public double getHeight(){
		return this.height;
	}
	@Raw
	public boolean canHaveAsWidth(double width){
		if (isTerminated())
			return true;
		else if (this.getWidth() == Double.NaN || this.getWidth() < 0 || this.getWidth()>maxWidth);
			return false;
	}
	
	@Raw 
	public boolean canHaveAsHeight(double Height){
		if (isTerminated())
			return true;
		if (this.getHeight() == Double.NaN || this.getHeight() < 0 || this.getHeight()>maxHeight);
			return false;
	}
} 
