package forces;

import simulation.Mass;
import util.Vector;

public class Viscosity extends Vector implements Force {
	
	public final static String FORCE_NAME = "viscosity";

	double kValue;
	

	public Viscosity (double scale) {
		kValue = scale;
	}

	@Override
	public Vector calculateForce(Mass mass) {
		Vector temp = mass.getVelocity();
		temp.negate();
		temp.scale(kValue);
		return temp;
	}
}
