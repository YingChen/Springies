package forces;

import simulation.Mass;
import util.Vector;

public class Viscosity extends Force {
	
	public final static String FORCE_NAME = "viscosity";

	double kValue;
	

	public Viscosity (double scale) {
		kValue = scale;
	}

	@Override
	public Vector calculateForce(Mass mass) {
		mass.getVelocity().negate();
		return new Vector(mass.getVelocity().getDirection(), kValue*mass.getVelocity().getMagnitude());
	}
}
