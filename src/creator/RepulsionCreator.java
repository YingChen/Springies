package creator;

import java.util.Scanner;

import forces.Repulsion;

import simulation.Factory;

public class RepulsionCreator extends Creator{

	public RepulsionCreator(Factory f) {
		super(f);
	}
	
	@Override
	public void create(Scanner line){
		int wallID = line.nextInt();
        double magnitude = line.nextDouble();
        double exponentValue = line.nextDouble();
    	myFactory.addForceToModel(new Repulsion(wallID, magnitude, exponentValue));
	}

}
