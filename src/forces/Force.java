package forces;

import simulation.Mass;
import util.Vector;


/**
 * This is the interface for the force objects
 * 
 * @author Eric Wu
 * 
 */
public interface Force {

    public final String FORCE_NAME = "force";

    public Vector calculateForce (Mass mass);
}
