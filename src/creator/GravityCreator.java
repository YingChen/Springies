package creator;

import java.util.Scanner;

import forces.Gravity;

import simulation.Factory;


/**
 * This creator creates gravity force objects
 * @author Ying Chen
 *
 */
public class GravityCreator extends Creator{

	public GravityCreator(Factory f) {
		super(f);
	}
	
	@Override
	public void create(Scanner line){
		int direction = line.nextInt();
        int magnitude = line.nextInt();

        getFactory().addForceToModel(new Gravity(direction, magnitude));
	}

}
