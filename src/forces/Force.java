package forces;

import simulation.Mass;
import util.Vector;

public abstract class Force extends Vector {

	public Vector force;
	
	public String FORCE_NAME;
	
	public abstract Vector calculateForce(Mass mass);
	
}
