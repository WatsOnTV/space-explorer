package spaceExplorer.FoodItems;

import spaceExplorer.Consumable;

/** Represents a Cookie item, which extends the Consumable abstract class.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class Cookie extends Consumable{
	/** 
	 * Initializes Cookie with predefined values.
	*/
	 public Cookie() {
		super("Cookie", 10, 0, 4.5);
	 }
}