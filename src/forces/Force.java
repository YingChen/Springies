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

    public Vector calculateForce (Mass mass);

    public String getName ();

    public boolean isInEffect ();

    public void toggleEffect ();
}
