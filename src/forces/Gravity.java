package forces;

import simulation.Mass;
import util.Vector;

<<<<<<< HEAD
public class Gravity extends Vector implements Force {
=======
public class Gravity extends Force {
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb

	//Vector force;
	private int myDirection;
	private int myMagnitude;
	
	public final static String FORCE_NAME = "gravity";
	
	public Gravity(int direction, int magnitude) {
		myDirection = direction;
		myMagnitude = magnitude;
	}

	@Override
	public Vector calculateForce(Mass mass) {
<<<<<<< HEAD
		Vector temp = mass.getVelocity();
		temp.sum(new Vector(myDirection, myMagnitude*mass.getMass()));
		return temp;
=======
		return new Vector(myDirection, myMagnitude*mass.getMass());
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb
	}
	
	

}
