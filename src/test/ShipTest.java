package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import spaceExplorer.*;
import spaceExplorer.FoodItems.*;
import spaceExplorer.MedicalItems.*;

class ShipTest {
	Ship ship = new Ship();
	SolarSystem planets = new SolarSystem(3);
	
	@Test
	void testFindingParts() {
		assertEquals(ship.getNumPartsFound(), 0);
		ship.partFound();
		assertEquals(ship.getNumPartsFound(), 1);
	}
	
	@Test
	void testShields() {
		assertEquals(ship.getShields(), 100);
		ship.setShields(50);
		assertEquals(ship.getShields(), 50);
		ship.setShields(100);
		assertEquals(ship.getShields(), 100);
	}
	
	@Test
	void testNaming() {
		ship.setName("Falcon Heavy");
		assertEquals(ship.getName(), "Falcon Heavy");
	}
	
	@Test
	void testShipInventory() {
		Consumable foodItem1 = new Burger();
		Consumable foodItem2 = new Cookie();
		Consumable foodItem3 = new Cookie();
		Consumable foodItem4 = new Fruit();
		Consumable foodItem5 = new Burger();
		Consumable foodItem6 = new Pasta();
		ship.addFood(foodItem1); 
		ship.addFood(foodItem2);
		ship.addFood(foodItem3);
		ship.addFood(foodItem4);
		ship.addFood(foodItem5);
		ship.addFood(foodItem6);
		
		ship.removeFood(foodItem5);
		
		ArrayList<Consumable> foodItems = new ArrayList<Consumable>();
		foodItems.addAll(Arrays.asList(foodItem1, foodItem2, foodItem3, foodItem4,  foodItem6));
		assertEquals(foodItems, ship.getFoodItems());
		assertEquals(foodItems.get(0).getName(), ship.getFoodItemNames()[0]);
		assertEquals(foodItems.get(3).getName(), ship.getFoodItemNames()[3]);


		assertEquals(foodItems.get(0).getName(), ship.getFoodItemNames()[0]);
		assertEquals(foodItems.get(3).getName(), ship.getFoodItemNames()[3]);

		Consumable medItem1 = new Morphine();
		Consumable medItem2 = new IV();
		Consumable medItem3 = new Bandage();
		Consumable medItem4 = new Bandage();
		Consumable medItem5 = new SpacePlagueOintment();
		Consumable medItem6 = new FirstAidKit();
		
		ship.addMedical(medItem1);
		ship.addMedical(medItem2);
		ship.removeMedical(medItem1);
		ship.addMedical(medItem3);
		ship.addMedical(medItem4);
		ship.addMedical(medItem5);
		ship.addMedical(medItem6);
		
		ArrayList<Consumable> medItems = new ArrayList<Consumable>();
		medItems.addAll(Arrays.asList(medItem2, medItem3, medItem4, medItem5,  medItem6));
		assertEquals(medItems, ship.getMedicalItems());
		assertEquals(medItems.get(0).getName(), ship.getMedsItemNames()[0]);
		assertEquals(medItems.get(3).getName(), ship.getMedsItemNames()[3]);


		assertEquals(medItems.get(0).getName(), ship.getMedsItemNames()[0]);
		assertEquals(medItems.get(3).getName(), ship.getMedsItemNames()[3]);

	}

	@Test
	void getStringFoodItemsTest() {
		Consumable foodItem1 = new Burger();
		Consumable foodItem2 = new Cookie();
		ship.addFood(foodItem1); 
		ship.addFood(foodItem2);
		assertEquals(ship.getStringFoodItems(), "Food Items:\n- Burger\r\n- Cookie\r\n");
	}
	
	@Test
	void getStringMedicalItemsTest() {
		Consumable medItem1 = new Morphine();
		Consumable medItem2 = new IV();
		ship.addMedical(medItem1);
		ship.addMedical(medItem2);
		assertEquals(ship.getStringMedicalItems(), "Medical Items:\n- Morphine\r\n- IV\r\n");
	}
	
	@Test
	void planetOpperationsTest() {
		ship.generateSolarSystem(3);
		Planet previousPlanet = ship.getCurrentPlanet();
		assertEquals(ship.getCurrentPlanetName(), previousPlanet.toString());
		assertEquals(ship.getNumPartsNeeded(), ship.getPartsNeeded().size());
		
		ship.gotoNextPlanet();
		
		if(previousPlanet == ship.getCurrentPlanet()) {
			fail("Next planet failed.");
		}
		
		ship.gotoNextPlanet();
		ship.gotoNextPlanet();
		ship.gotoNextPlanet();
		
		assertEquals(ship.getCurrentPlanet(), previousPlanet);
	}

}

