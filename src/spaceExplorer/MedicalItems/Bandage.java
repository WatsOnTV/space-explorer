package spaceExplorer.MedicalItems;

import spaceExplorer.Consumable;

/** Represents a Bandage item, which extends the Consumable abstract class.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class Bandage extends Consumable{

	/** 
	 * Initializes Bandage with predefined values.
	*/
	public Bandage() {
		super("Bandage", 0, 6, 6);
	}

}