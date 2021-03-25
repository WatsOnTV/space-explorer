package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import spaceExplorer.Crew;
import spaceExplorer.CrewMember;

class CrewTest {
	
	@Test
	void buildCrewTest() {
		Crew crew = new Crew();
		assertEquals(0, crew.crewSize(4));
	}
	
	@Test
	void addMemberTest() {
		Crew crew = new Crew();
		crew.addMember("Test Name 1", 3, 0);
		crew.addMember("Test Name 2", 5, 1);
		crew.addMember("Test Name 3", 1, 2);
		crew.addMember("Test Name 4", 0, 3);
		crew.addMember("Test Name 5", 2, 0);
		
		assertEquals("Test Name 5", crew.getCrewList(4)[0].getName());
		assertEquals("Scavenger", crew.getCrewList(4)[0].getType());
		assertEquals("Test Name 4", crew.getCrewList(4)[3].getName());
		assertEquals("Test Name 4 (Engineer)", crew.getCrewList(4)[3].toString());
		assertEquals(4, crew.crewSize(4));
	}
	
	@Test
	void removeDeadTest() {
		Crew crew = new Crew();
		crew.addMember("Test Name 1", 1, 0);
		crew.addMember("Test Name 2", 3, 1);
		crew.addMember("Test Name 3", 5, 2);
		
		CrewMember[] crewList = crew.getCrewList(3);
		crewList[0].setDead();
		crewList[2].setDead();
		crew.removeDead();
		crewList = crew.getCrewList(3);
		
		assertEquals(1, crew.crewSize(3));
		assertEquals("Test Name 2", crewList[0].getName());
	}
	
	@Test
	void addMoneyTest() {
		Crew crew = new Crew();
		assertEquals(50, crew.getMoney());
		crew.addMoney(500);
		assertEquals(550, crew.getMoney());
		crew.addMoney(-500);
		assertEquals(50, crew.getMoney());
	}
	
	@Test
	void crewMemberAttributesTest() {
		Crew crew = new Crew();
		crew.addMember("Test Name 1", 1, 0);
		crew.addMember("Test Name 2", 0, 1);
		crew.addMember("Test Name 3", 5, 2);
		CrewMember[] crewList = crew.getCrewList(3);
		
		assertEquals(100, crewList[0].getHealth());
		assertEquals(100, crewList[0].getFood());
		assertEquals(100, crewList[0].getEnergy());
		assertEquals(2, crewList[0].getActions());
		crewList[0].setHealth(-200);
		crewList[0].setFood(-50);
		crewList[0].setEnergy(200);
		crewList[0].useActions(3);
		assertEquals(0, crewList[0].getHealth());
		assertEquals(50, crewList[0].getFood());
		assertEquals(100, crewList[0].getEnergy());
		assertEquals(0, crewList[0].getActions());
		
		
		crewList[1].setHasSpacePlague(true);
		assertTrue(crewList[1].hasSpacePlague());
		
		crewList[1].health(100);
		crewList[1].food(100);
		crewList[1].energy(100);
		double healthBefore = crewList[1].getHealth();
		crewList[1].nextDay();
		if(crewList[1].getHealth() == healthBefore) {
			fail("Health not reduced due to space plague.");
		}
		
		crewList[1].setFood(-100);
		crewList[1].health(100);
		crewList[1].energy(100);
		healthBefore = crewList[1].getHealth();
		crewList[1].nextDay();
		if(crewList[1].getHealth() == healthBefore) {
			fail("Health not reduced due to hunger.");
		}
		
		crewList[1].setEnergy(-100);
		crewList[1].food(100);
		crewList[1].health(100);
		healthBefore = crewList[1].getHealth();
		double foodBefore = crewList[1].getFood();
		crewList[1].nextDay();
		if(crewList[1].getHealth() == healthBefore) {
			fail("Health not reduced due to low energy.");
		}
		if(crewList[1].getFood() == foodBefore) {
			fail("Food not reduced due to low energy.");
		}
		
		crewList[2].useActions(1);
		crewList[2].useActions(1);
		crewList[2].addActions(1);
		assertEquals(1, crewList[2].getActions());
	}

}
