package creator;

import java.util.Scanner;

import forces.Repulsion;

import simulation.Factory;

/**
 * This creator creates repulsion force objects
 * 
 * @author Ying Chen
 * 
 */
public class RepulsionCreator extends Creator {

	public RepulsionCreator(Factory f) {
		super(f);
	}

	@Override
	public void create(Scanner line) {
		int wallID = line.nextInt();
		double magnitude = line.nextDouble();
		double exponentValue = line.nextDouble();

		getFactory().addForceToModel(
				new Repulsion(wallID, magnitude, exponentValue));
	}

}
