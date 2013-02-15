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

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myForces = new ArrayList<Force>();
        // myRemovedForces = new ArrayList<Force>();
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

    public void incrementSize (int size) {
        Dimension d = new Dimension(myView.getWidth() + size, myView.getHeight() + size);
        myView.setSize(d);
    }

    public void decrementSize (int size) {
        System.out.println(myView.getWidth()+" by "+myView.getHeight());
        if (myView.getWidth() < 0 || myView.getHeight() < 0) return;
        Dimension d = new Dimension(myView.getWidth() - size, myView.getHeight() - size);
        myView.setSize(d);
    }

    public void updateForces () {
        for (Mass m : myMasses) {
            for (Force force : myForces) {
                if (force.isInEffect()) {
                    m.applyForce(force);
                }
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
     * if forceName is in myForces, then it is moved to myRemovedForces if
     * forceName is in myRemovedForces, then it is moved to myForces if
     * forceName is in neither, then it is created with default values and put
     * into myForces
     * 
     * @param forceName
     */
    public void toggleForceByName (String forceName) {
        boolean forceExists = false;
        for (Force force : myForces) {
            if (force.getName().equals(forceName)
                || isCorrectWallID(force, forceName)) {
                force.toggleEffect();
                System.out.println(force.isInEffect());
                forceExists = true;
            }
        }

        if (!forceExists) {
            addDefaultForce(forceName);
        }
    }

    private boolean isCorrectWallID (Force force, String forceName) {
        if (!force.getName().equals(ForceNames.VISCOSITY))
            return false;
        // if (!force.getClass().equals(Repulsion.class)) return false;
        if (!(force instanceof Repulsion))
            return false;
        return (((Repulsion) force).getWallID() == Integer.parseInt(forceName));
    }

    /**
     * I thought about shifting this responsibility to the Creator.java class...
     * but then decided against it, because I would still need the same if
     * structure in Creator, so it wouldn't save lines of code per se.
     * 
     * @param forceName
     */
    private void addDefaultForce (String forceName) {
        if (forceName.equals(ForceNames.GRAVITY)) {
            System.out.println("Gravity");
            myForces.add(new Gravity());
        }
        else if (forceName.equals(ForceNames.VISCOSITY)) {
            myForces.add(new Viscosity());
        }
        else if (forceName.equals(ForceNames.CENTER_OF_MASS)) {
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

    public void clearAll () {
        myMasses.clear();
        mySprings.clear();
        myForces.clear();
    }

    public List<Mass> getMassList () {
        return myMasses;
    }

    public void removeObject (Mass m) {
        myMasses.remove(m);
    }

    public void removeObject (Spring s) {
        mySprings.remove(s);
    }

}
