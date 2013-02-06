package forces;

import simulation.Mass;
import util.Vector;

/**
 * This is designed for viscosity force objects
 * @author Eric Wu
 *
 */
public class Viscosity extends Vector implements Force {
	
	public final static String FORCE_NAME = "viscosity";

	private double kValue;
	
	public Viscosity (double scale) {
		kValue = scale;
	}
	
	/**
	 * Calculate the force on the mass
	 */
	@Override
	public Vector calculateForce(Mass mass) {
		
		Vector temp = mass.getVelocity();
		temp.negate();
		temp.scale(kValue);
		return temp;
	}
}
