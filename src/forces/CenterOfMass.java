package forces;

import java.awt.geom.Point2D;
import java.util.List;

import simulation.Mass;
import util.Vector;

<<<<<<< HEAD
public class CenterOfMass extends Vector implements Force {
=======
public class CenterOfMass extends Force {
>>>>>>> 3b8f7a437a40f52c1fd580dc3f7f4a15b7f7c6cb

	public final static String FORCE_NAME = "centerOfMass";
	
	double magnitude;
	double exponentValue;
	private List<Mass> list;
	private Point2D centerOfMass;

	public CenterOfMass (double mag, double exp) {
		magnitude = mag;
		exponentValue = exp;
	}
	
	@Override
	public Vector calculateForce(Mass mass) {
		getCenterOfMass();
		Point2D myPosition = new Point2D.Double(mass.getX(), mass.getY());
		double weight = magnitude * Math.exp(exponentValue);
		
		if(exponentValue >0){
			Vector force = new Vector(myPosition, centerOfMass);
			force.setMagnitude(weight);
			return force;
		}
		else{
			Vector force = new Vector(centerOfMass, myPosition);
			force.setMagnitude(weight);
			return force;
		}
			
	}
	
	public void addMassList(List<Mass> l){
		list = l;
	}
	
	private void getCenterOfMass(){
		double totalMass = 0;
		double x = 0;
		double y = 0;
		for(Mass m: list){
			totalMass = totalMass + m.getMass();
			x = x + m.getX() * m.getMass();
			y = y + m.getY() * m.getMass();
		}
		centerOfMass = new Point2D.Double(x/totalMass, y/totalMass);
	}
	
}

