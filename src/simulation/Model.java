package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
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

    private Spring tempSpring;
    private Mass tempMass;

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

    public void incrementSize (int size) {
        Dimension d = new Dimension(myView.getWidth() + size, myView.getHeight() + size);
        myView.setSize(d);
    }

    public void decrementSize (int size) {
        if (myView.getWidth() < 0 || myView.getHeight() > 0) return;
        Dimension d = new Dimension(myView.getWidth() - size, myView.getHeight() - size);
        myView.setSize(d);
    }

    public void updateForces () {
        for (Mass m : myMasses) {
            for (Force force : myForces) {
                if (force.isInEffect())
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
            if (force.getName().equals(forceName) || isCorrectWallID(force, forceName)) {
                force.toggleEffect();
                forceExists = true;
            }
        }

        if (!forceExists) {
            addDefaultForce(forceName);
        }
    }

    private boolean isCorrectWallID (Force force, String forceName) {
        if (!force.getName().equals(ForceNames.VISCOSITY)) return false;
        if (!force.getClass().equals(Repulsion.class)) return false;
        return (((Repulsion) force).getWallID() == Integer.parseInt(forceName));
    }

    /**
     * I thought about shifting this responsibility to the Creator.java class... but
     * then decided against it, because I would still need the same if structure
     * in Creator, so it wouldn't save lines of code per se.
     * 
     * @param forceName
     */
    private void addDefaultForce (String forceName) {
        if (forceName.equals(ForceNames.GRAVITY)) {
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

    public void createTempSpring (Point position) {
        tempMass = new Mass(position.getX(), position.getY(), 0);
        Mass closestMass = findClosestMass(position);
        tempSpring =
                new Spring(tempMass, closestMass, length(position, closestMass.getX(),
                                                         closestMass.getY()), 0.5);

        myMasses.add(tempMass);
        mySprings.add(tempSpring);
    }

    private Mass findClosestMass (Point p) {
        Mass closestMass = null;
        double closestLength = Double.POSITIVE_INFINITY;
        for (Mass m : myMasses) {
            double length = length(p, m.getX(), m.getY());
            // System.out.println("length is "+length);
            if (length < closestLength) {
                closestMass = m;
                closestLength = length;
            }
        }
        if (closestMass == null)
            System.out.println("NUll!!!!");
        return closestMass;
    }

    private double length (Point p, double x, double y) {
        // System.out.println(p.getX()+" "+p.getY()+" "+x+" "+y);
        return Math.pow((p.getX() - x), 2) + Math.pow((p.getY() - y), 2);
    }

    public void dragTempSpring (Point p) {
        tempMass.setCenter(p.getX(), p.getY());
    }

    public void removeTempObjects () {
        myMasses.remove(tempMass);
        mySprings.remove(tempSpring);
        tempMass = null;
        tempSpring = null;
    }

}
