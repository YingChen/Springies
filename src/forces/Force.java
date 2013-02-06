package forces;

import simulation.Mass;
import util.Vector;

<<<<<<< HEAD
public interface Force {
	
	public Vector calculateForce(Mass mass);
=======
public abstract class Force extends Vector {

	public Vector force;
	
	public String FORCE_NAME;
	
	public abstract Vector calculateForce(Mass mass);
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb
	
}
