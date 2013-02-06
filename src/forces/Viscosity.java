package forces;

import simulation.Mass;
import util.Vector;

<<<<<<< HEAD
public class Viscosity extends Vector implements Force {
=======
public class Viscosity extends Force {
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb
	
	public final static String FORCE_NAME = "viscosity";

	double kValue;
	

	public Viscosity (double scale) {
		kValue = scale;
	}

	@Override
	public Vector calculateForce(Mass mass) {
<<<<<<< HEAD
		Vector temp = mass.getVelocity();
		temp.negate();
		temp.scale(kValue);
		return temp;
=======
		mass.getVelocity().negate();
		return new Vector(mass.getVelocity().getDirection(), kValue*mass.getVelocity().getMagnitude());
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb
	}
}
