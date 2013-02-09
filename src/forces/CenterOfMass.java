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

    public final String FORCE_NAME = "centerOfMass";

    private double magnitude;
    private double exponentValue;
    private List<Mass> list;
    private Point2D centerOfMass;

    public CenterOfMass (double mag, double exp) {
        magnitude = mag;
        exponentValue = exp;
    }

    public CenterOfMass () {
        magnitude = 1;
        exponentValue = 1;
    }

    /**
     * Calculate the force on the mass. Override the method in the super class.
     */
    @Override
    public Vector calculateForce (Mass mass) {
        getCenterOfMass();
        Point2D myPosition = new Point2D.Double(mass.getX(), mass.getY());
        double weight = magnitude * Math.exp(exponentValue);

        if (exponentValue > 0) {
            Vector force = new Vector(myPosition, centerOfMass);
            force.setMagnitude(weight);
            return force;
        }
        else {
            Vector force = new Vector(centerOfMass, myPosition);
            force.setMagnitude(weight);
            return force;
        }

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
    private void getCenterOfMass () {
        double totalMass = 0;
        double x = 0;
        double y = 0;
        for (Mass m : list) {
            totalMass = totalMass + m.getMass();
            x = x + m.getX() * m.getMass();
            y = y + m.getY() * m.getMass();
        }
        centerOfMass = new Point2D.Double(x / totalMass, y / totalMass);
    }

}
