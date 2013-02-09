package forces;

import simulation.Mass;
import util.Vector;

/**
 * This is designed for the gravity force objects
 * 
 * @author Eric Wu
 * 
 */
public class Gravity extends Vector implements Force {

	private int myDirection;
	private int myMagnitude;

	public final static String FORCE_NAME = "gravity";

	public Gravity(int direction, int magnitude) {
		myDirection = direction;
		myMagnitude = magnitude;
	}

	/**
	 * Calculate the force on the mass
	 */
	@Override
	public Vector calculateForce(Mass mass) {
		Vector temp = mass.getVelocity();
		temp.sum(new Vector(myDirection, myMagnitude * mass.getMass()));
		return temp;
	}
}
