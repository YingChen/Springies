package simulation;

import java.awt.Dimension;

import util.Vector;

/**
 * This is designed for the muscle objects
 * @author Eric Wu
 *
 */
public class Muscle extends Spring {
	private double amplitude;
	private double startTime;

	public Muscle(Mass start, Mass end, double length, double kVal, double amp) {
		super(start, end, length, kVal);
		amplitude = amp;
		startTime = System.currentTimeMillis();
	}

	/**
	 * Update the status of the muscle
	 */
	@Override
	public void update(double elapsedTime, Dimension bounds) {
		double dx = myStart.getX() - myEnd.getX();
		double dy = myStart.getY() - myEnd.getY();
		double currentTime = System.currentTimeMillis();
		// apply hooke's law to each attached mass
		System.out.println(myLength*amplitude*Math.cos(myK*(currentTime-startTime)));
		Vector force = new Vector(Vector.angleBetween(dx, dy), myLength*amplitude*Math.cos(myK*(currentTime-startTime)));
		myStart.applyForce(force);
		force.negate();
		myEnd.applyForce(force);
		// update sprite values based on attached masses
		setCenter(getCenter(myStart, myEnd));
		setSize(getSize(myStart, myEnd));
		setVelocity(Vector.angleBetween(dx, dy), 0);
	}

}
