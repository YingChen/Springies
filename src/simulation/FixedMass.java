package simulation;

import java.awt.Dimension;

import util.Pixmap;


/**
 * This class is designed for fixedmass object.It performs as the subclass of Mass. 
 * It only overrides the update() method.
 * 
 * @author Ying Chen
 */
public class FixedMass extends Mass {    

	// reasonable default values
    public static final Dimension DEFAULT_SIZE = new Dimension(16, 16);
    public static final Pixmap DEFUALT_IMAGE = new Pixmap("mass.gif");
    
    
    /**
     * Constructor of this class
     */
    public FixedMass (double x, double y, double mass) {
    	super (x, y, mass);
    }

    /**
     * This update the status of the object. Override the method in mass class.
     */
    @Override
    public void update (double elapsedTime, Dimension bounds) {
    	
    }


}
