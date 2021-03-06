package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import forces.Force;
import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;


/**
 * This is designed for mass objects in the game
 * 
 * @author Robert C. Duvall revised by Ying Chen
 */
public class Mass extends Sprite {
    // reasonable default values
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");

    private double myMass;
    private Vector myAcceleration;

    /**
     * Constructor
     */
    public Mass (double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myMass = mass;
        myAcceleration = new Vector();
    }

    /**
     * This updates the status of the object
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
        applyForce(getBounce(bounds));
        // convert force back into Mover's velocity
        getVelocity().sum(myAcceleration);
        myAcceleration.reset();
        // move mass by velocity
        super.update(elapsedTime, bounds);
    }

    /**
     * This paints the object onto the panel
     */
    @Override
    public void paint (Graphics2D pen) {
        pen.setColor(Color.BLACK);
        pen.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(),
                     (int) getHeight());
    }

    /**
     * Use the given force to change this mass's acceleration.
     */
    public void applyForce (Vector force) {
        // Calculate the net force as a result of all Forces
        myAcceleration.sum(force);
    }

    public void applyForce (Force force) {
        myAcceleration.sum(force.calculateForce(this));
    }

    public void setForce (Force force) {
        myAcceleration = force.calculateForce(this);
    }

    /**
     * Convenience method.
     */
    public double distance (Mass other) {
        // this is a little awkward, so hide it
        return new Location(getX(), getY()).distance(new Location(other.getX(),
                                                                  other.getY()));
    }

    // check for move out of bounds
    private Vector getBounce (Dimension bounds) {
        final double IMPULSE_MAGNITUDE = 2;
        Vector impulse = new Vector();
        if (getLeft() < 0) {
            impulse = new Vector(RIGHT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getRight() > bounds.width) {
            impulse = new Vector(LEFT_DIRECTION, IMPULSE_MAGNITUDE);
        }
        if (getTop() < 0) {
            impulse = new Vector(DOWN_DIRECTION, IMPULSE_MAGNITUDE);
        }
        else if (getBottom() > bounds.height) {
            impulse = new Vector(UP_DIRECTION, IMPULSE_MAGNITUDE);
        }
        impulse.scale(getVelocity().getRelativeMagnitude(impulse));
        return impulse;
    }

    public double getMass () {
        return myMass;
    }

    public void setSize (int x, int y) {
        mySize = new Dimension(x, y);
    }
}
