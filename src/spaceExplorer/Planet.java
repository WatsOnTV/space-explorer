package spaceExplorer;

import java.util.ArrayList;

/** Represents a Planet.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class Planet {
	
	private String name;
	private ArrayList<Item> containedItems = new ArrayList<Item>();
	
	/** Creates a new instance of Planet
	 * @param name The name of the planet
	 * @param containedItems an ArrayList containing the items the planet holds
	*/
	public Planet(String name, ArrayList<Item> containedItems) {
		this.name = name;
		this.containedItems = containedItems;
	}

	/** Gets the game of the planet
	 * @return the name of the planet
	*/
	public String toString() {
		return name;
	}
	
	/** Gets the Array of the items the planet holds
	 * @return an ArrayList of items the planet holds
	*/
	public ArrayList<Item> getContainedItems(){
		return containedItems;
	}
	
	/** Removes a given item from the planet
	 * @param item Item to be removed
	*/
	public void removeItem(Item item){
		containedItems.remove(item);
	}
}
