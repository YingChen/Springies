package forces;

import simulation.Mass;
import util.Vector;

public class Gravity extends Force {

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
		return new Vector(myDirection, myMagnitude*mass.getMass());
	}
	
	

}
