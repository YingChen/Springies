package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import creator.CenterOfMassCreator;
import creator.Creator;
import creator.GravityCreator;
import creator.MassCreator;
import creator.MuscleCreator;
import creator.RepulsionCreator;
import creator.SpringCreator;
import creator.ViscosityCreator;

import forces.*;


/**
 * This class reads the files and creates all the objects, including masses, springs and forces.
 * 
 * @author Robert C. Duvall
 * revised by Ying Chen
 */
public class Factory {

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
    

    /**
     * Initialize all the creators. These creators can be obtained via the keywords
     */
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

     * Load the files and initialize the game model
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


    /**
     * Return the list of masses
     * @return
     */
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

    /**
     * Get the mass via its id
     * @param id
     * @return
     */
    public Mass getMass(int id){
    	return myMasses.get(id);
    }
    
}
