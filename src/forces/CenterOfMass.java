package forces;

import java.awt.geom.Point2D;
import java.util.List;
import simulation.Mass;
import util.Vector;


/**
 * This is designed for centerOfMass force objects
 * 
 * @author Ying Chen
 * 
 */
public class CenterOfMass extends Vector implements Force {

    private final String FORCE_NAME = "centerOfMass";

    private double magnitude;
    private double exponentValue;
    private List<Mass> list;
    private Point2D centerOfMass;
    private boolean isInEffect;

    public CenterOfMass (double mag, double exp) {
        magnitude = mag;
        exponentValue = exp;
        isInEffect = true;
    }

    public CenterOfMass () {
        this(1, 1);
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
     * Calculate the force on the mass. Override the method in the super class.
     */
    @Override
    public Vector calculateForce (Mass mass) {
        centerOfMass = getCenterOfMass();
        Point2D myPosition = new Point2D.Double(mass.getX(), mass.getY());
        double weight = magnitude * Math.exp(exponentValue);

        Vector force;
        if (exponentValue > 0) {
            force = new Vector(myPosition, centerOfMass);
        }
        else {
            force = new Vector(centerOfMass, myPosition);
        }

        force.setMagnitude(weight);
        return force;

    }

    /**
     * Assign the list of all masses
     * 
     * @param l
     */
    public void addMassList (List<Mass> l) {
        list = l;
    }

    /**
     * Calculate the center of all the masses
     */
    private Point2D getCenterOfMass () {
        double totalMass = 0;
        double x = 0;
        double y = 0;
        for (Mass m : list) {
            totalMass += m.getMass();
            x = x + m.getX() * m.getMass();
            y = y + m.getY() * m.getMass();
        }
        return new Point2D.Double(x / totalMass, y / totalMass);
    }

}
