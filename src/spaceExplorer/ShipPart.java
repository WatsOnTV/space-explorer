package spaceExplorer;

/** Represents a missing part for the player to find.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class ShipPart implements Item{
	private String partName;
	
	public ShipPart(String name) {
		this.partName = name;
	}
	
	/** Returns part name.
	 * @return Part Name.
	*/
	public String toString() {
		return getName();
	}
	
	/** Returns part name.
	 * @return Part Name.
	*/
	public String getName() {
		return partName;
	}
}
