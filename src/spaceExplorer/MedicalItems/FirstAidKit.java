package spaceExplorer.MedicalItems;

import spaceExplorer.Consumable;

/** Represents a FirstAidKit item, which extends the Consumable abstract class.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class FirstAidKit extends Consumable{

	/** 
	 * Initializes FirstAidKit with predefined values.
	*/
	public FirstAidKit() {
		super("First Aid Kit", 0, 10, 9);
	}

}