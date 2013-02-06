package creator;

import java.util.Scanner;

import forces.Viscosity;

import simulation.Factory;

/**
 * This creator creates viscosity force objects
 * 
 * @author Ying Chen
 * 
 */

public class ViscosityCreator extends Creator {

	public ViscosityCreator(Factory f) {
		super(f);
	}

	@Override
	public void create(Scanner line) {
		double scale = line.nextDouble();

		getFactory().addForceToModel(new Viscosity(scale));
	}

}
