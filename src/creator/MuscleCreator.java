package creator;

import java.util.Scanner;

import simulation.Factory;
import simulation.Mass;
import simulation.Muscle;

/**
 * This creator creates muscle objects
 * 
 * @author Ying Chen
 * 
 */

public class MuscleCreator extends Creator {

	public MuscleCreator(Factory f) {
		super(f);
	}

	@Override
	public void create(Scanner line) {

		Mass m1 = getFactory().getMass(line.nextInt());
		Mass m2 = getFactory().getMass(line.nextInt());
		double restLength = line.nextDouble();
		double ks = line.nextDouble();
		double amp = line.nextDouble();
		getFactory().addSpringToModel(new Muscle(m1, m2, restLength, ks, amp));

	}

}
