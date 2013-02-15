package simulation;

import util.Vector;


/**
 * This is designed for the muscle objects
 * 
 * @author Eric Wu
 * 
 */
public class Muscle extends Spring {
    private double amplitude;
    private double startTime;

    public Muscle (Mass start, Mass end, double length, double kVal, double amp) {
        super(start, end, length, kVal);
        amplitude = amp;
        startTime = System.currentTimeMillis();
    }

    /**
     * returns the Vector that is used to change the start and end points for
     * the spring.
     * 
     * @param dx
     * @param dy
     * @return
     */

    @Override
    protected Vector getForce (double dx, double dy) {
        double currentTime = System.currentTimeMillis();
        return new Vector(Vector.angleBetween(dx, dy), myLength * amplitude
                                                       * Math.cos(myK * (currentTime - startTime)));
    }

}
