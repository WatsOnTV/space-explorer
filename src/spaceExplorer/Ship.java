package spaceExplorer;

import java.util.ArrayList;


/** Represents the current game ship.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class Ship {

	private double shields;
    private String name;
    private SolarSystem solarSystem;
    private int currentPlanet = 0;
    private int numPartsNeeded;
    private int numPartsFound = 0;
    
    ArrayList<ShipPart> partsNeeded = new ArrayList<ShipPart>();
    ArrayList<Consumable> foodItems = new ArrayList<Consumable>();
    ArrayList<Consumable> medicalItems = new ArrayList<Consumable>();
    

	/** Initializes Ship with shield level 100.
	*/
    public Ship() {
    	shields = 100;
    }
    
    
    /**
     * Returns the parts needed for the ship in a text format.
     * @param breakStyle Text to be used as a line break
     * @return Missing ship parts
     */
    public String getStringPartsNeeded(String breakStyle) {
    	String parts = "";
    	for(int i = 0; i < partsNeeded.size(); i++) {
    		parts += "- " + partsNeeded.get(i);
    		parts += breakStyle;
    	}
    	return parts;
    }
    
    public String getStringFoodItems() {
    	String inv = "Food Items:\n";
    	for(int i = 0; i < foodItems.size(); i++) {
    		inv += "- " + foodItems.get(i);
    		inv += "\r\n";
    	}
    	return inv;
    }
    
    public String getStringMedicalItems() {
    	String inv = "Medical Items:\n";
    	for(int i = 0; i < medicalItems.size(); i++) {
    		inv += "- " + medicalItems.get(i);
    		inv += "\r\n";
    	}
    	return inv;
    }
    
    /** Returns the current planet's name.
	 * @return Planet name.
	*/
    public String getCurrentPlanetName() {
    	return solarSystem.getPlanetName(currentPlanet);
    }
    
    /**
     * Generates solar system according to the number of days in the game
     * @param maxDays Number of days the game will last for.
     */
    public void generateSolarSystem(int maxDays) {
    	solarSystem = new SolarSystem(maxDays);
    	partsNeeded = solarSystem.getLostParts();
    	numPartsNeeded = partsNeeded.size();
    	addDefaultInventory();
    }
    
    /**
     * initialises the default inventory
     */
    private void addDefaultInventory() {
    	//default food items
    	addFood(solarSystem.randomFood());
    	addFood(solarSystem.randomFood());
    	addFood(solarSystem.randomFood());
    	
    	//default medical items
    	addMedical(solarSystem.randomMeds());
    	addMedical(solarSystem.randomMeds());
    }
    
    /**
     * Returns the current planet
     * @return Current planet
     */
    public Planet getCurrentPlanet(){
    	return solarSystem.getPlanets().get(currentPlanet);
    }
    
    /**
     * Increases the planet number by 1
     */
    public void gotoNextPlanet(){
    	if(currentPlanet + 1 < this.solarSystem.getPlanets().size()) {
    		currentPlanet++;
    	}else {
    		currentPlanet = 0;
    	}
    }
    
    /**
     * Returns the number of parts needed for the rocket
     * @return Number of parts
     */
    public int getNumPartsNeeded(){
    	return numPartsNeeded;
    }
    
    /**
     * Returns the number of parts found for the rocket
     * @return Number of parts
     */
    public int getNumPartsFound(){
    	return numPartsFound;
    }
    
    /**
     * Increase number of parts found by one
     */
    public void partFound(){
    	this.numPartsFound++;
    }
    
    /**
     * Add an item to foodItems
     * @param item Food item to be added
     */
    public void addFood(Consumable item){
    	foodItems.add(item);
    }
    
    /**
     * Remove a item from foodItems
     * @param item Food item to be removed
     */
    public void removeFood(Consumable item) {
    	foodItems.remove(item);
    }

    /**
     * Add an item to medicalItems
     * @param item Medical item to be added
     */
    public void addMedical(Consumable item) {
    	medicalItems.add(item);
    }
    
    /**
     * Remove a item from medicalItems
     * @param item Medical item to be removed
     */
    public void removeMedical(Consumable item) {
    	medicalItems.remove(item);
    }

    /**
     * Set the value of shields
     * @param amount the new value of shields
     */
    public void setShields (double amount) {
        this.shields = amount;
    }

    /**
     * Get the value of ships shields
     * @return the shields percentage as type int
     */
    public int getShields () {
        return (int) Math.round(shields);
    }

    /**
     * Set the name of the ship
     * @param name The new value of name
     */
	public void setName (String name) {
        this.name = name;
    }

    /**
     * Get the name of the ship
     * @return the name of the ship
     */
    public String getName () {
        return name;
    }

    /**
     * Get the value of partsNeeded
     * @return the value of partsNeeded
     */
    public ArrayList<ShipPart> getPartsNeeded () {
        return partsNeeded;
    }

    /**
     * Get the value of foodItems
     * @return the value of foodItems
     */
    public ArrayList<Consumable> getFoodItems () {
        return foodItems;
    }
    
    /**
     * Get a String array of all available food's names
     * @return Food string array
     */
    public String[] getFoodItemNames() {
        String[] foods = new String[foodItems.size()];
    	for(int i = 0; i < foodItems.size(); i++) {
    		foods[i] = foodItems.get(i).getName();
    	}
    	return foods;
    }

    /**
     * Get the value of medicalItems
     * @return the value of medicalItems
     */
    public ArrayList<Consumable> getMedicalItems () {
        return medicalItems;
    }
    
    /**
     * Get a String array of all available medical item's names
     * @return Meds string array
     */
    public String[] getMedsItemNames() {
        String[] meds = new String[medicalItems.size()];
    	for(int i = 0; i < medicalItems.size(); i++) {
    		meds[i] = medicalItems.get(i).getName();
    	}
    	return meds;
    }

}
