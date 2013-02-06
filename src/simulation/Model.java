package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

import forces.Force;
import view.Canvas;


/**
 * This performs as the game model
 * 
 * @author Robert C. Duvall revised by Ying Chen	
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<Mass> myMasses;
    private List<Spring> mySprings;
    private List<Force> myForces;


    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myForces = new ArrayList<Force>();

    }
    

    /**
     * Draw all elements of the simulation.
     */
    public void paint (Graphics2D pen) {
        for (Spring s : mySprings) {
            s.paint(pen);
        }
        for (Mass m : myMasses) {
            m.paint(pen);
        }
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        Dimension bounds = myView.getSize();
        updateForces();
        for (Mass m : myMasses) {
            m.update(elapsedTime, bounds);
        }
        for (Spring s : mySprings) {
            s.update(elapsedTime, bounds);
        }
    }

    /**
     * update forces
     */
    
    public void updateForces () {
    	for (Mass m : myMasses) {
    		for (Force force : myForces) {
    			m.setForce(force);
    		}
    	}
    }
    /**
     * Add given mass to this simulation.
     */
    public void add (Mass mass) {
        myMasses.add(mass);
    }

    /**
     * Add given spring to this simulation.
     */
    public void add (Spring spring) {
        mySprings.add(spring);
    }
    
    /**
     * Add given forces to this simulation.
     */
    
    public void add (Force force) {
    	myForces.add(force);
    }
}
