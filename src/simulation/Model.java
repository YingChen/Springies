package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
<<<<<<< HEAD
import java.util.HashMap;
=======
import java.awt.Point;
>>>>>>> Part 3 [2] Mouse Dragging by Ying
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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
<<<<<<< HEAD
    private Map<Force, Boolean> myForcesStatus;
=======
    
    private Spring tempSpring;
    private Mass tempMass;
>>>>>>> Part 3 [2] Mouse Dragging by Ying

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myMasses = new ArrayList<Mass>();
        mySprings = new ArrayList<Spring>();
        myForces = new ArrayList<Force>();
        myForcesStatus = new HashMap<Force, Boolean>();
        resetForcesStatus();
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
                if (myForcesStatus.get(force))
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

    private void resetForcesStatus () {
        for (Force force : myForcesStatus.keySet())
            myForcesStatus.put(force, true);
    }
<<<<<<< HEAD

    public void clearAll () {
        myMasses.clear();
        mySprings.clear();
        myForces.clear();
    }

=======
    
    public void createTempSpring(Point position){
    	tempMass = new Mass(position.getX(),position.getY(),0);
    	Mass closestMass = findClosestMass(position);
    	tempSpring = new Spring(tempMass,closestMass,length(position,closestMass.getX(),closestMass.getY()),0.5);
    	
    	myMasses.add(tempMass);
    	mySprings.add(tempSpring);
    }
    
    private Mass findClosestMass(Point p){
    	Mass closestMass = null;
    	double closestLength = Double.POSITIVE_INFINITY;
    	for(Mass m: myMasses){
    		double length = length(p,m.getX(),m.getY());
    		//System.out.println("length is "+length);
    		if(length < closestLength){
    			closestMass = m;
    			closestLength = length;
    		}
    	}
    	if(closestMass == null)
    		System.out.println("NUll!!!!");
    	return closestMass;
    }
    
    private double length(Point p, double x, double y){
    	//System.out.println(p.getX()+" "+p.getY()+" "+x+" "+y);
    	return Math.pow((p.getX()-x), 2)+Math.pow((p.getY()-y), 2);
    }
    
    public void dragTempSpring(Point p){
    	tempMass.setCenter(p.getX(), p.getY());
    }
    
    public void removeTempObjects(){
    	myMasses.remove(tempMass);
    	mySprings.remove(tempSpring);
    	tempMass = null;
    	tempSpring = null;
    }
    
>>>>>>> Part 3 [2] Mouse Dragging by Ying
}
