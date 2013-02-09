package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import forces.*;
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
    private List<Force> myRemovedForces;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myForces = new ArrayList<Force>();
        myRemovedForces = new ArrayList<Force>();
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

    public void setSize (int x, int y) {
        for (Mass m : myMasses) {
            m.setSize(x, y);
        }
    }

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

    /**
     * if forceName is in myForces, then it is moved to myRemovedForces
     * if forceName is in myRemovedForces, then it is moved to myForces
     * if forceName is in neither, then it is created with default values
     * and put into myForces
     * 
     * @param forceName
     */
    public void toggleForceByName (String forceName) {
        boolean forceExists = false;
        for (Force force : myForces) {
            if (force.FORCE_NAME.equals(forceName) || isCorrectWallID(force, forceName)) {
                toggleForce(force);
                forceExists = true;
            }
        }
        if (!forceExists) {
            for (Force force : myRemovedForces) {
                if (force.FORCE_NAME.equals(forceName) || isCorrectWallID(force, forceName)) {
                    toggleForce(force);
                    forceExists = true;
                }
            }
        }
        if (!forceExists) {
            addDefaultForce(forceName);
        }
    }

    public void addDefaultForce (String forceName) {
        if (forceName.equals("gravity")) {
            myForces.add(new Gravity());
        }
        else if (forceName.equals("Viscosity")) {
            myForces.add(new Viscosity());
        }
        else if (forceName.equals("centerOfMass")) {
            myForces.add(new CenterOfMass());
        }
        else if (forceName.equals("1")) {
            myForces.add(new Repulsion(1));
        }
        else if (forceName.equals("2")) {
            myForces.add(new Repulsion(2));
        }
        else if (forceName.equals("3")) {
            myForces.add(new Repulsion(3));
        }
        else if (forceName.equals("4")) {
            myForces.add(new Repulsion(4));
        }
    }

    private boolean isCorrectWallID (Force force, String forceName) {
        if (!force.FORCE_NAME.equals("repulsion")) return false;
        if (!force.getClass().equals(Repulsion.class)) return false;
        return (((Repulsion) force).getWallID() == Integer.parseInt(forceName));
    }

    public void toggleForce (Force force) {
        if (myForces.contains(force)) {
            myRemovedForces.add(force);
            myForces.remove(force);
        }
        else {
            myForces.add(force);
            myRemovedForces.remove(force);
        }
    }

    public void clearAll () {
        myMasses.clear();
        mySprings.clear();
        myForces.clear();
    }

}
