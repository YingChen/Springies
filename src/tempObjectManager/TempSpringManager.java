package tempObjectManager;

import java.awt.Point;

import simulation.Mass;
import simulation.Model;
import simulation.Spring;

/**
 * This works as a temporary spring manager that creates, modifies and removes
 * the object
 * 
 * 
 * @author Ying Chen
 * 
 */
public class TempSpringManager extends TempObjectManager {
	private Mass tempMass;
	private Spring tempSpring;

	public TempSpringManager(Model m) {
		super(m);
	}

	@Override
	public void createTempObject(Point position) {
		tempMass = new Mass(position.getX(), position.getY(), 0);
		Mass closestMass = findClosestMass(position);
		// Check if the closest Mass is null. Return if yes
		if (closestMass == null)
			return;

		tempSpring = new Spring(tempMass, closestMass, length(position,
				closestMass.getX(), closestMass.getY()), 0.5);

		getModel().add(tempMass);
		getModel().add(tempSpring);

	}

	@Override
	public void dragTempObject(Point position) {
		tempMass.setCenter(position.getX(), position.getY());
	}

	@Override
	public void deleteTempObject() {
		getModel().removeObject(tempMass);
		getModel().removeObject(tempSpring);
		tempMass = null;
		tempSpring = null;
	}

}
