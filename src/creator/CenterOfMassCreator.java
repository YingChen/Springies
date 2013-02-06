package creator;

import java.util.Scanner;

import forces.CenterOfMass;

import simulation.Factory;

<<<<<<< HEAD
public class CenterOfMassCreator extends Creator {
=======
public class CenterOfMassCreator extends Creator{
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb

	public CenterOfMassCreator(Factory f) {
		super(f);
	}
<<<<<<< HEAD

	@Override
	public void create(Scanner line) {
		double magnitude = line.nextDouble();
		double exponentValue = line.nextDouble();
		CenterOfMass com = new CenterOfMass(magnitude, exponentValue);
		com.addMassList(myFactory.getMasses());
		myFactory.addForceToModel(com);
=======
	
	@Override
	public void create(Scanner line){
		double magnitude = line.nextDouble();
        double exponentValue = line.nextDouble();
    	CenterOfMass com= new CenterOfMass(magnitude, exponentValue);
    	com.addMassList(myFactory.getMasses());
    	myFactory.addForceToModel(com);
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb
	}

}
