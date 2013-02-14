package forces;

import simulation.Mass;
import util.Vector;


/**
 * This is designed for viscosity force objects
 * 
 * @author Eric Wu
 * 
 */
public class Viscosity extends Vector implements Force {

    private final String FORCE_NAME = "viscosity";

    private double kValue;
    
    private boolean isInEffect;

    public Viscosity (double scale) {
        kValue = scale;
        isInEffect = true;
    }

    public Viscosity () {
        kValue = 1;
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
        temp.negate();
        temp.scale(kValue);
        return temp;
    }
}
