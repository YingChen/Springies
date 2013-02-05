package forces;

import simulation.Mass;
import util.Vector;

public interface Force {
	
	public Vector calculateForce(Mass mass);
	
}
