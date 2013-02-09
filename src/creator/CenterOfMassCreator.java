package creator;

import java.util.Scanner;

import forces.CenterOfMass;

import simulation.Factory;

public class CenterOfMassCreator extends Creator {

	public CenterOfMassCreator(Factory f) {
		super(f);
	}

	@Override
	public void create(Scanner line) {

		double magnitude = line.nextDouble();
		double exponentValue = line.nextDouble();

		CenterOfMass com = new CenterOfMass(magnitude, exponentValue);

		com.addMassList(getFactory().getMasses());

		getFactory().addForceToModel(com);

	}

}
