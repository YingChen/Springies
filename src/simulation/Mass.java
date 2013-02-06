package simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.Scanner;
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb

import forces.Force;

import util.Location;
import util.Pixmap;
import util.Sprite;
import util.Vector;

/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Mass extends Sprite {
	// reasonable default values
	public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
	public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");

	private double myMass;
	private Vector myAcceleration;
	private boolean isFixed;

	/** Hello World **/
	/**
	 * XXX.
	 */
	public Mass(double x, double y, double mass) {
		super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
		myMass = mass;
		myAcceleration = new Vector();
		if (mass > 0)
			isFixed = false;
		else
			isFixed = true;
	}

<<<<<<< HEAD
	/**
	 * XXX.
	 */
	@Override
	public void update(double elapsedTime, Dimension bounds) {
		if (!isFixed) {
			applyForce(getBounce(bounds));
			// convert force back into Mover's velocity
			getVelocity().sum(myAcceleration);
			myAcceleration.reset();
			// move mass by velocity
			super.update(elapsedTime, bounds);
		}
	}

	/**
	 * XXX.
	 */
	@Override
	public void paint(Graphics2D pen) {
		pen.setColor(Color.BLACK);
		pen.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(),
				(int) getHeight());
	}

	/**
	 * Use the given force to change this mass's acceleration.
	 */
	public void applyForce(Vector force) {
		if (isFixed)
			return;
		// Calculate the net force as a result of all Forces
		myAcceleration.sum(force);
	}

	public void applyForce(Force force) {
		if (isFixed)
			return;
		myAcceleration.sum(force.calculateForce(this));
=======
    private double myMass;
    private Vector myAcceleration;
    private boolean isFixed;
    

    /** Hello World **/
    /**
     * XXX.
     */
    public Mass (double x, double y, double mass) {
        super(DEFUALT_IMAGE, new Location(x, y), DEFAULT_SIZE);
        myMass = mass;
        myAcceleration = new Vector();
        if(mass >0)
        	isFixed = false;
        else 
        	isFixed = true;
    }


    /**
     * XXX.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
    	if(!isFixed){
    		applyForce(getBounce(bounds));
    		// convert force back into Mover's velocity
    		getVelocity().sum(myAcceleration);
    		myAcceleration.reset();
    		// move mass by velocity
    		super.update(elapsedTime, bounds);
    	}
    }
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb

	}
	
	public void setForce(Force force) {
		if (isFixed)
			return;
		myAcceleration = force.calculateForce(this);

<<<<<<< HEAD
	}
=======
    /**
     * Use the given force to change this mass's acceleration.
     */
    public void applyForce (Vector force) {
    	if (isFixed) return;
    	// Calculate the net force as a result of all Forces
        myAcceleration.sum(force);
    }
    
    public void applyForce (Force force) {
    	if (isFixed) return;
    	myAcceleration.sum(force.calculateForce(this));
    
    }
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb

	/**
	 * Convenience method.
	 */
	public double distance(Mass other) {
		// this is a little awkward, so hide it
		return new Location(getX(), getY()).distance(new Location(other.getX(),
				other.getY()));
	}

	// check for move out of bounds
	private Vector getBounce(Dimension bounds) {
		final double IMPULSE_MAGNITUDE = 2;
		Vector impulse = new Vector();
		if (getLeft() < 0) {
			impulse = new Vector(RIGHT_DIRECTION, IMPULSE_MAGNITUDE);
		} else if (getRight() > bounds.width) {
			impulse = new Vector(LEFT_DIRECTION, IMPULSE_MAGNITUDE);
		}
		if (getTop() < 0) {
			impulse = new Vector(DOWN_DIRECTION, IMPULSE_MAGNITUDE);
		} else if (getBottom() > bounds.height) {
			impulse = new Vector(UP_DIRECTION, IMPULSE_MAGNITUDE);
		}
		impulse.scale(getVelocity().getRelativeMagnitude(impulse));
		return impulse;
	}

<<<<<<< HEAD
	public double getMass() {
		return myMass;
	}
=======
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
    
    public double getMass(){
    	return myMass;
    }
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb
}
