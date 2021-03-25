package spaceExplorer.FoodItems;

import spaceExplorer.Consumable;

/** Represents a PackagedMeal item, which extends the Consumable abstract class.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class PackagedMeal extends Consumable{
	
	/** 
	 * Initializes PackagedMeal with predefined values.
	*/
	 public PackagedMeal() {
		super("Packaged Meal", 35, 0, 14);
	 }
}
