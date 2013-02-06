package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

<<<<<<< HEAD
import forces.*;
=======
import creator.CenterOfMassCreator;
import creator.Creator;
import creator.GravityCreator;
import creator.MassCreator;
import creator.MuscleCreator;
import creator.RepulsionCreator;
import creator.SpringCreator;
import creator.ViscosityCreator;

import forces.*;

>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb

/**
 * XXX
 * 
 * @author Robert C. Duvall
 */
public class Factory {
<<<<<<< HEAD
	// data file keywords
	private static final String MASS_KEYWORD = "mass";
	// private static final String FIXEDMASS_KEYWORD = "mass";
	private static final String SPRING_KEYWORD = "spring";
	private static final String MUSCLES_KEYWORD = "muscle";
	private static final String GRAVITY_KEYWORD = "gravity";
	private static final String VISCOSITY_KEYWORD = "viscosity";
	private static final String CENTEROFMASS_KEYWORD = "centermass";
	private static final String REPULSION_KEYWORD = "wall";

	// mass IDs
	Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();

	/**
	 * XXX.
	 */
	public void loadModel(Model model, File modelFile) {
		try {
			Scanner input = new Scanner(modelFile);
			while (input.hasNext()) {
				Scanner line = new Scanner(input.nextLine());
				if (line.hasNext()) {
					String type = line.next();
					if (MASS_KEYWORD.equals(type)) {
						model.add(massCommand(line));
					}
					/*
					 * else if (FIXEDMASS_KEYWORD.equals(type)) {
					 * model.add(fixedMassCommand(line)); }
					 */
					else if (SPRING_KEYWORD.equals(type)) {
						model.add(springCommand(line));
					} else if (MUSCLES_KEYWORD.equals(type)) {
						model.add(muscleCommand(line));
					} else if (GRAVITY_KEYWORD.equals(type)) {
						model.add(gravityCommand(line));
					} else if (VISCOSITY_KEYWORD.equals(type)) {
						model.add(viscosityCommand(line));
					} else if (CENTEROFMASS_KEYWORD.equals(type)) {
						model.add(centerOfMassCommand(line));
					} else if (REPULSION_KEYWORD.equals(type)) {
						model.add(repulsionCommand(line));
					}
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			// should not happen because File came from user selection
			e.printStackTrace();
		}
	}

	// create mass from formatted data
	private Mass massCommand(Scanner line) {
		int id = line.nextInt();
		double x = line.nextDouble();
		double y = line.nextDouble();
		double mass = line.nextDouble();
		Mass result = new Mass(x, y, mass);
		myMasses.put(id, result);
		return result;
	}

	/*
	 * private Mass fixedMassCommand (Scanner line) { int id = line.nextInt();
	 * double x = line.nextDouble(); double y = line.nextDouble(); double mass =
	 * line.nextDouble(); Mass result = new FixedMass(x, y, mass);
	 * myMasses.put(id, result); return result; }
	 */
	// create spring from formatted data
	private Spring springCommand(Scanner line) {
		Mass m1 = myMasses.get(line.nextInt());
		Mass m2 = myMasses.get(line.nextInt());
		double restLength = line.nextDouble();
		double ks = line.nextDouble();
		return new Spring(m1, m2, restLength, ks);
	}

	private Spring muscleCommand(Scanner line) {
		Mass m1 = myMasses.get(line.nextInt());
		Mass m2 = myMasses.get(line.nextInt());
		double restLength = line.nextDouble();
		double ks = line.nextDouble();
		double amp = line.nextDouble();
		return new Muscle(m1, m2, restLength, ks, amp);
	}

	private Force gravityCommand(Scanner line) {
		int direction = line.nextInt();
		int magnitude = line.nextInt();
		return new Gravity(direction, magnitude);
	}

	private Force viscosityCommand(Scanner line) {
		double scale = line.nextDouble();
		return new Viscosity(scale);
	}

	private Force centerOfMassCommand(Scanner line) {
		double magnitude = line.nextDouble();
		double exponentValue = line.nextDouble();
		CenterOfMass com = new CenterOfMass(magnitude, exponentValue);
		com.addMassList(getMasses());
		return com;
	}

	private Force repulsionCommand(Scanner line) {
		int wallID = line.nextInt();
		double magnitude = line.nextDouble();
		double exponentValue = line.nextDouble();
		return new Repulsion(wallID, magnitude, exponentValue);
	}

	private List<Mass> getMasses() {
		List<Mass> list = new ArrayList<Mass>();
		for (Mass m : myMasses.values()) {
			list.add(m);
		}
		return list;
	}
=======
    // data file keywords
    private static final String MASS_KEYWORD = "mass";
    private static final String SPRING_KEYWORD = "spring";
    private static final String MUSCLES_KEYWORD = "muscle";
    private static final String GRAVITY_KEYWORD = "gravity";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String CENTEROFMASS_KEYWORD = "centermass";
    private static final String REPULSION_KEYWORD = "wall";

    private Model myModel;
    // mass IDs
    Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();
    private Map<String, Creator> myCreators;
    
    public Factory(){
    	myCreators = new HashMap<String, Creator>();
    	initCreators();
    }
    
    private void initCreators(){
    	myCreators.put(MASS_KEYWORD, new MassCreator(this));
    	myCreators.put(SPRING_KEYWORD, new SpringCreator(this));
    	myCreators.put(MUSCLES_KEYWORD, new MuscleCreator(this));
    	myCreators.put(GRAVITY_KEYWORD, new GravityCreator(this));
    	myCreators.put(VISCOSITY_KEYWORD, new ViscosityCreator(this));
    	myCreators.put(CENTEROFMASS_KEYWORD, new CenterOfMassCreator(this));
    	myCreators.put(REPULSION_KEYWORD, new RepulsionCreator(this));
    }

    /**
     * XXX.
     */
    public void loadModel (Model model, File modelFile) {
    	myModel = model;
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if(!myCreators.containsKey(type)){
                    	System.out.println("Error!");
                    }
                    else{
                    	Creator creator = myCreators.get(type);
                    	creator.create(line);
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    public List<Mass> getMasses(){
    	List<Mass> list = new ArrayList<Mass>();
    	for(Mass m: myMasses.values()){
    		list.add(m);
    	}
    	return list;
    }
    
    
    public void addMassToModel(int id,Mass m){
    	myMasses.put(id, m);
    	myModel.add(m);
    }
    
    public void addForceToModel(Force f){
    	myModel.add(f);
    }
    
    public void addSpringToModel(Spring s){
    	myModel.add(s);
    }
    
    public Mass getMass(int id){
    	return myMasses.get(id);
    }
    
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb
}
