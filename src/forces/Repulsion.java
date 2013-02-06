package forces;

import java.awt.Point;

import simulation.Mass;
import util.Vector;
import view.Canvas;

/**
 * This is designed for repulsion force objects
 * @author Eric Wu
 *
 */
public class Repulsion extends Vector implements Force {

	public final static String FORCE_NAME = "repulsion";

	private int wallID;
	private double magnitude;
	private double exponentValue;

	public Repulsion(int wall, double mag, double exp) {
		wallID = wall;
		magnitude = mag;
		exponentValue = exp;
	}

	/**
	 * Calculate the force on the mass
	 */
	@Override
	public Vector calculateForce(Mass mass) {
		double dx = 0;
		double dy = 0;
		double vectorDirection = 0;
		if (wallID == 1) {
			vectorDirection = -180;
			dx = mass.getX();
			dy = 0;
		} else if (wallID == 2) {
			vectorDirection = -270;
			dx = Canvas.WIDTH;
			dy = mass.getY();
		} else if (wallID == 3) {
			vectorDirection = 0;
			dx = Canvas.HEIGHT;
			dy = mass.getX();
		} else if (wallID == 4) {
			vectorDirection = -90;
			dx = 0;
			dy = mass.getY();
		}
		double vectorMagnitude = magnitude
				* Math.pow(Vector.distanceBetween(
						new Point((int) dx, (int) dy),
						new Point((int) mass.getX(), (int) mass.getY())),
						exponentValue);

		Vector temp = new Vector(vectorDirection, vectorMagnitude);
		temp.sum(mass.getVelocity());
		return temp;
	}

}
