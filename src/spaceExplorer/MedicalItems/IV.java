package spaceExplorer.MedicalItems;

import spaceExplorer.Consumable;

/** Represents an IV item, which extends the Consumable abstract class.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class IV extends Consumable{

	/** 
	 * Initializes IV with predefined values.
	*/
	public IV() {
		super("IV", 0, 15, 14);
	}
	
}