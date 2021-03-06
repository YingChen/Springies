package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * This performs as the spring objects in the game
 * 
 * @author Robert C. Duvall revised by Ying Chen
 */
public class Spring extends Sprite {
    // reasonable default values
    private static final Pixmap DEFUALT_IMAGE = new Pixmap("spring.gif");
    private static final int IMAGE_HEIGHT = 20;

    protected Mass myStart;
    protected Mass myEnd;
    protected double myLength;
    protected double myK;

    /**
     * Constructor
     */
    public Spring (Mass start, Mass end, double length, double kVal) {
        super(DEFUALT_IMAGE, getCenter(start, end), getSize(start, end));
        myStart = start;
        myEnd = end;
        myLength = length;
        myK = kVal;
    }

    /**
     * Paint the object onto the panel
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(getColor(myStart.distance(myEnd) - myLength));
        pen.drawLine((int) myStart.getX(), (int) myStart.getY(),
                     (int) myEnd.getX(), (int) myEnd.getY());
    }

    /**
     * Update the status of the object
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        double dx = myStart.getX() - myEnd.getX();
        double dy = myStart.getY() - myEnd.getY();
        // apply hooke's law to each attached mass
        Vector force = getForce(dx, dy);

        myStart.applyForce(force);
        force.negate();
        myEnd.applyForce(force);
        // update sprite values based on attached masses
        setCenter(getCenter(myStart, myEnd));
        setSize(getSize(myStart, myEnd));
        setVelocity(Vector.angleBetween(dx, dy), 0);
    }

    /**
     * returns the Vector that is used to change the start and end points for
     * the spring.
     * 
     * @param dx
     * @param dy
     * @return
     */
    protected Vector getForce (double dx, double dy) {
        return new Vector(Vector.angleBetween(dx, dy), myK
                                                       *
                                                       (myLength - Vector.distanceBetween(dx, dy)));
    }

    /**
     * Convenience method.
     */
    protected Color getColor (double diff) {
        if (Vector.fuzzyEquals(diff, 0))
            return Color.BLACK;
        else if (diff < 0.0)
            return Color.BLUE;
        else return Color.RED;
    }

    // compute center of this spring
    private static Location getCenter (Mass start, Mass end) {
        return new Location((start.getX() + end.getX()) / 2,
                            (start.getY() + end.getY()) / 2);
    }

    // compute size of this spring
    private static Dimension getSize (Mass start, Mass end) {
        return new Dimension((int) start.distance(end), IMAGE_HEIGHT);
    }
}
