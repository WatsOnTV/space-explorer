package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import spaceExplorer.SolarSystem;

class SolarSystemTest {

	@Test
	void testSolarSystem() {
		SolarSystem solarSystem = new SolarSystem(3);
		assertEquals(4, solarSystem.getPlanets().size());
		assertEquals(2, solarSystem.getLostParts().size());
		solarSystem = new SolarSystem(10);
		assertEquals(14, solarSystem.getPlanets().size());
		assertEquals(6, solarSystem.getLostParts().size());
	}

	@Test
	void testGetPlanetName() {
		SolarSystem solarSystem = new SolarSystem(10);
		assertNotNull(solarSystem.getPlanetName(5));
	}

}
