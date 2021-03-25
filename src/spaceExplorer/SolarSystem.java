package spaceExplorer;

import spaceExplorer.FoodItems.*;
import spaceExplorer.MedicalItems.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/** Represents the collection of visitable planets in the current game.
 * @author Karna Malone
 * @author Daniel Watson
*/
public class SolarSystem {
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	private ArrayList<ShipPart> lostParts = new ArrayList<ShipPart>();
	private int numberOfPlanets;
	private int partAlowance;
	
	private ArrayList<String> planetNames = new ArrayList<String>();
	private ArrayList<String> partNames = new ArrayList<String>();
	
	/** Initializes SolarSystem.
	 * @param maxDays Number of days the game will last for.
	*/
	public SolarSystem(int maxDays) {
		numberOfPlanets = (int) Math.rint(maxDays*1.4);
		partAlowance = (int) Math.rint(2*maxDays/3);
		
		//Planet Names
		planetNames.addAll(Arrays.asList("Nimolia","Mudussia","Freea V","Rutzar","Parenujor","Japoria","Aluilia-RX","Cinrad","Xamendam","Thuenbel Prime","Penterth","Ziontica","Mudtea","Aciszar","Rusgus Prime","Olea-RX","Aslis","Osenuea","Aratan 3"));
		//Part Names
		partNames.addAll(Arrays.asList("Landing Leg","RCS Thruster","Nose Cone","Guidance Chip","'How to Fly a Rocket' Manual","Steering wheel","Grid Fin","Rocket Fuel","Warp Drive"));
		
		generatePlanets();
	}
	
	/** Generates planets in the solar system.
	*/
	private void generatePlanets() {
		for(int i = 0; i < numberOfPlanets; i++) {
			//Choose Name
			Random rand = new Random(); 
			String name = planetNames.get(rand.nextInt(planetNames.size()));
			planetNames.remove(name);
			
			planets.add(new Planet(name, generateLoot()));
		}
		Collections.shuffle(planets);
	}
	
	/** Generates items on individual planets.
	 * @return loot List of parts to be found.
	*/
	private ArrayList<Item> generateLoot(){
		ArrayList<Item> loot = new ArrayList<Item>();
		
		Random lootSelect = new Random();
		switch(lootSelect.nextInt(6)) {
		  	case 0:
		  		loot.add(randomFood());
		  	case 1:
				loot.add(randomMeds());
		  	case 2:
		  		loot.add(randomFood());
			    break;
		  	case 3:
		  		loot.add(randomMeds());
		  		loot.add(randomMeds());
			    break;
		  	case 4:
		  		loot.add(randomFood());
		  		loot.add(randomFood());
			    break;
		  	case 5:
		  		loot.add(randomFood());
		  		loot.add(randomMeds());
			    break;
		}
		
		if(partAlowance > 0) {
			//Choose part name
			Random randomName = new Random(); 
			String name = partNames.get(randomName.nextInt(partNames.size()));
			ShipPart part = new ShipPart(name);
			partNames.remove(name);
			lostParts.add(part);
			loot.add(part);
			partAlowance--;
		}
		return loot;
	}
	
	/** Returns a random food item.
	 * @return Food Item.
	*/
	public Consumable randomFood(){
		Consumable randomFood = null;
		Random rand = new Random();
		switch(rand.nextInt(6)) {
		  	case 0:
		  		randomFood = new Burger();
		  		break;
		  	case 1:
		  		randomFood = new Chocolate();
		  		break;
		  	case 2:
		  		randomFood = new Cookie();
			    break;
		  	case 3:
		  		randomFood = new Fruit();
			    break;
		  	case 4:
		  		randomFood = new PackagedMeal();
			    break;
		  	case 5:
		  		randomFood = new Pasta();
			    break;
		}
		return randomFood;
	}
	
	/** Returns a random medical item.
	 * @return Medical Item.
	*/
	public Consumable randomMeds(){
		Consumable randomMeds = null;
		Random rand = new Random();
		switch(rand.nextInt(4)) {
		  	case 0:
		  		randomMeds = new Bandage();
		  		break;
		  	case 1:
		  		randomMeds = new FirstAidKit();
		  		break;
		  	case 2:
		  		randomMeds = new IV();
			    break;
		  	case 3:
		  		randomMeds = new Morphine();
			    break;
		}
		return randomMeds;
	}
	
	/** Returns the planets name at the specified index.
	 * @param index Index of planet in solar system
	 * @return Planet name.
	*/
	public String getPlanetName(int index){
		return planets.get(index).toString();
	}
	
	/** Returns a list of parts to find.
	 * @return Parts list.
	*/
	public ArrayList<ShipPart> getLostParts(){
		return lostParts;
	}
	
	/** Returns an arraylist of the planets in the system.
	 * @return Planet list.
	*/
	public ArrayList<Planet> getPlanets(){
		return planets;
	}
}
