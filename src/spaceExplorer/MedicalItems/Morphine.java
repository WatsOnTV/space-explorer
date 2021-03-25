package spaceExplorer.MedicalItems;

import spaceExplorer.Consumable;

/** Represents a Morphine item, which extends the Consumable abstract class.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class Morphine extends Consumable{

	/** 
	 * Initializes Morphine with predefined values.
	*/
	public Morphine() {
		super("Morphine", 0, 20, 18);
	}

}