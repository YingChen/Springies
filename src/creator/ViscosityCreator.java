package creator;

import java.util.Scanner;

import forces.Viscosity;

import simulation.Factory;

public class ViscosityCreator extends Creator{

	public ViscosityCreator(Factory f) {
		super(f);
	}
	
	@Override
	public void create(Scanner line){
		double scale = line.nextDouble();
		myFactory.addForceToModel(new Viscosity(scale));
	}

}
