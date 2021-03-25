package spaceExplorer;

/** Represents a food/medical item.
 * @author Karna Malone
 * @author Daniel Watson
*/
abstract public class Consumable implements Item{
	private String name;
    private float feeds;
    private float heals;
    private double price;
    
    /**
     * Creates a new instance of Item
     * @param name The name of the item
     * @param feeds The amount the item will feed the player
     * @param heals The amount the item will heal the player
     * @param price The price value of the item
     */
    public Consumable(String name, float feeds, float heals, double price) { 
    	this.name = name;
    	this.feeds = feeds;
    	this.heals = heals;
    	this.price = price;
    }
    
    /**
     * returns the string representation of the item
     * @return string representation of the item
     */
    public String toString() {
    	return getName();
    }
    
    /**
     * Gets the name of the item
     * @return the name of the food item
     */
	public String getName() {
		return name;
	}

    /**
     * Sets the feed value
     * @param feeds the new feed value
     */
    public void setFeeds (float feeds) {
        this.feeds = feeds;
    }

    /**
     * Gets the amount the item will feed
     * @return the value of feeds
     */
    public float getFeeds () {
        return feeds;
    }
    
    /**
     * Set the heal amount
     * @param heals the new value of heals
     */
    public void setHeals (float heals) {
        this.heals = heals;
    }

    /**
     * Get the heal value
     * @return the heal value
     */
    public float getHeals () {
        return heals;
    }

    /**
     * Sets the price value of the item
     * @param price the new price value
     */
    public void setPrice (float price) {
        this.price = price;
    }

    /**
     * Gets the price value of the item
     * @return the price value
     */
    public double getPrice() {
        return price;
    }
}