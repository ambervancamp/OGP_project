package asteroids.model;

public class Ship {
	/**
	 * @invar  Each ship can have its xVelocity as xVelocity .
	 *       | canHaveAsxVelocity(this.getxVelocity())
	 */

	/**
	 * Initialize this new ship with given xVelocity.
	 * 
	 * @param xVelocity
	 *            The xVelocity for this new ship.
	 * @post If the given xVelocity is a valid xVelocity for any ship, the
	 *       xVelocity of this new ship is equal to the given xVelocity.
	 *       Otherwise, the xVelocity of this new ship is equal to 0. | if
	 *       (isValidxVelocity(xVelocity)) | then new.getxVelocity() ==
	 *       xVelocity | else new.getxVelocity() == 0
	 */
	public Ship(double xVelocity) {
		if (!canHaveAsxVelocity(xVelocity))
			xVelocity = 0;
		this.xVelocity = xVelocity;
	}

	/**
	 * Return the xVelocity of this ship.
	 */
	@Basic
	@Raw
	@Immutable
	public double getxVelocity() {
		return this.xVelocity;
	}

	/**
	 * Check whether this ship can have the given xVelocity as its xVelocity.
	 * 
	 * @param xVelocity
	 *            The xVelocity to check.
	 * @return | result ==
	 */
	@Raw
	public boolean canHaveAsxVelocity(double xVelocity) {
		return false;
	}

	/**
	 * Variable registering the xVelocity of this ship.
	 */
	private final double xVelocity;

}
