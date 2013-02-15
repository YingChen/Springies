package tempObjectManager;

import java.awt.Point;
import java.util.List;

import simulation.Mass;
import simulation.Model;

/**
 * This works as the temporary object Manager created in the game. This manager
 * works to create, update and delete the object.
 * 
 * @author Ying Chen
 * 
 */
public abstract class TempObjectManager {
	private Model myModel;

	public TempObjectManager(Model m) {
		myModel = m;
	}

	/**
	 * Create a temporary object in the assigned position
	 * 
	 * @param position
	 */
	public abstract void createTempObject(Point position);

	/**
	 * Drag the temporary object to a new position
	 * 
	 * @param position
	 */
	public abstract void dragTempObject(Point position);

	/**
	 * Delete this object and free up all the data
	 */
	public abstract void deleteTempObject();

	/**
	 * Find the closest mass from the assigned position
	 * 
	 * @param p
	 * @return
	 */
	protected Mass findClosestMass(Point p) {
		Mass closestMass = null;
		List<Mass> myMasses = myModel.getMassList();
		double closestLength = Double.POSITIVE_INFINITY;
		for (Mass m : myMasses) {
			double length = length(p, m.getX(), m.getY());
			if (length < closestLength) {
				closestMass = m;
				closestLength = length;
			}
		}
		if (closestMass == null)
			System.out.println("NUll!!!!");
		return closestMass;
	}

	/**
	 * Calculate the length between two points
	 * 
	 * @param p
	 * @param x
	 * @param y
	 * @return
	 */
	protected double length(Point p, double x, double y) {
		return Math.pow((p.getX() - x), 2) + Math.pow((p.getY() - y), 2);
	}

	protected Model getModel() {
		return myModel;
	}

}
