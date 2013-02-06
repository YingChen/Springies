package creator;

import java.util.Scanner;

import simulation.Factory;

/**
 * This class performs as superclass for all classes that create specific objects in the game
 * @author Ying Chen
 *
 */

public class Creator {
	private Factory myFactory;
	
	public Creator(Factory f){
		myFactory = f;
	}

	protected Factory getFactory(){
		return myFactory;
	}
	
	/**
	 * Create a object from the content of the scanner
	 * This is usually overrode by its concrete classes
	 * @param line
	 */
	public void create(Scanner line){
	
	}
}
