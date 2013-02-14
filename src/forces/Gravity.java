package forces;

import simulation.Mass;
import util.Vector;


/**
 * This is designed for the gravity force objects
 * 
 * @author Eric Wu
 * 
 */
public class Gravity extends Vector implements Force {

    private int myDirection;
    private int myMagnitude;
    private boolean isInEffect;

    private final String FORCE_NAME = "gravity";

    public Gravity (int direction, int magnitude) {
        myDirection = direction;
        myMagnitude = magnitude;
        isInEffect = true;
    }

    public Gravity () {
        myDirection = 0;
        myMagnitude = 1;
    }
    
    public boolean isInEffect () {
        return isInEffect;
    }
    
    @Override
    public void toggleEffect () {
        isInEffect = !isInEffect;
    }

    public String getName () {
        return FORCE_NAME;
    }
    

    /**
     * Calculate the force on the mass
     */
    @Override
    public Vector calculateForce (Mass mass) {
        Vector temp = mass.getVelocity();
        temp.sum(new Vector(myDirection, myMagnitude * mass.getMass()));
        return temp;
    }
}
