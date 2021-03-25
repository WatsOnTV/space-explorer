package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import spaceExplorer.*;
import spaceExplorer.FoodItems.*;
import spaceExplorer.MedicalItems.*;

class GameTest {

	@Test
	void purchaceItemTest() {
		Game game = new Game();
		
		int foodBefore = game.ship.getFoodItems().size();
		game.purchaseItem("Food", new Burger());
		int medsBefore = game.ship.getMedicalItems().size();
		game.purchaseItem("Meds", new Morphine());
		
		int foodAfter = game.ship.getFoodItems().size();
		int medsAfter = game.ship.getMedicalItems().size();
		
		if(foodBefore >= foodAfter) {
			fail("Food not added to ship.");
		}
		
		if(medsBefore >= medsAfter) {
			fail("Medical item not added to ship.");
		}
	}
	
	@Test
	void consumeTest() {
		Game game = new Game();
		game.setCrewSize(2);
		game.crew.addMoney(100);
		
		game.purchaseItem("Food", new Burger());
		game.purchaseItem("Meds", new Morphine());
		game.purchaseItem("Meds", new SpacePlagueOintment());
		
		game.crew.addMember("Crew Member 1", 0, 0);
		game.crew.addMember("Crew Member 2", 0, 1);
		
		CrewMember[] memberList = game.crew.getCrewList(game.getCrewSize());
		
		memberList[0].setFood(-50);
		memberList[1].setHealth(-50);
		
		double foodBefore = memberList[0].getFood();
		double HealthBefore = memberList[1].getHealth();
		
		game.consume("food", "Burger", memberList[0]);
		game.consume("meds", "Morphine", memberList[1]);
		
		double foodAfter = memberList[0].getFood();
		double HealthAfter = memberList[1].getHealth();
		
		if(foodBefore >= foodAfter) {
			fail("Consuming Food didn't increse the members food.");
		}
		
		if(HealthBefore >= HealthAfter) {
			fail("Consuming Meds didn't increse the members health.");
		}
		
		if(game.getScore() == 0) {
			fail("Score hasn't increased.");
		}
		
		memberList[0].setHasSpacePlague(true);
		game.consume("meds", "Space Plague Ointment", memberList[0]);
		
		assertFalse(memberList[0].hasSpacePlague());
	}
	
	@Test
	void repairShieldsTest() {
		Game game = new Game();
		game.setCrewSize(2);
		game.ship.setShields(10);
		
		game.crew.addMember("Crew Member 1", 0, 0);
		game.crew.addMember("Crew Member 2", 1, 1);
		CrewMember[] memberList = game.crew.getCrewList(game.getCrewSize());
		
		try {
			game.repairShields(memberList[0]);
	    } catch (NullPointerException e) {}
		try {
			game.repairShields(memberList[1]);
	    } catch (NullPointerException e) {}
		assertEquals(75, game.ship.getShields());
	}
	
	@Test
	void crewSlotsFullTest() {
		Game game = new Game();
		game.setCrewSize(2);

		assertFalse(game.crewSlotsFull());
		
		game.crew.addMember("Crew Member 1", 0, 0);
		
		assertFalse(game.crewSlotsFull());
		
		game.crew.addMember("Crew Member 2", 1, 1);

		assertTrue(game.crewSlotsFull());
	}
	
	@Test
	void calculateOvernightDeathsTest() {
		Game game = new Game();
		game.setCrewSize(4);
		
		game.crew.addMember("Crew Member 1", 0, 0);
		game.crew.addMember("Crew Member 2", 5, 1);
		game.crew.addMember("Crew Member 3", 3, 2);
		game.crew.addMember("Crew Member 4", 2, 3);
		CrewMember[] memberList = game.crew.getCrewList(game.getCrewSize());
		
		memberList[1].setDead();
		memberList[3].setDead();
		
		game.calculateOvernightDeaths();
		assertEquals(2, game.getCrewDied());
		
		game.resetDeathToll();
		assertEquals(0, game.getCrewDied());
		
		memberList[0].setDead();
		
		game.calculateOvernightDeaths();
		assertEquals(1, game.getCrewDied());
	}
	
	@Test
	void getMemberInfoTest() {
		Game game = new Game();
		game.setCrewSize(4);
		game.setMaxDays(4);

		game.crew.addMember("Crew Member 1", 0, 0);
		game.crew.addMember("Crew Member 2", 5, 1);
		game.crew.addMember("Crew Member 3", 3, 2);
		game.crew.addMember("Crew Member 4", 2, 3);
		
		assertEquals(game.getMemberInfo(0), "Crew Member 1 (Engineer)");
		assertEquals(game.getMemberInfo(1), "Crew Member 2 (Coffee Addict)");
		assertEquals(game.getMemberName(2), "Crew Member 3");
		assertEquals(game.getMemberName(3), "Crew Member 4");
		
		game.computeDailyScore();
		assertEquals(game.getScore(), 60);
	}
	
	@Test
	void basicOpperationsTest() {
		Game game = new Game();
		game.setMaxDays(4);
		assertEquals(game.getMaxDays(), 4);
		assertEquals(game.getDay(), 1);
	}
}
