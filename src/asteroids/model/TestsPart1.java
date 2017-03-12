package asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestsPart1 {
	@Test
	public void testCanHaveAsxPosition(){
		assertFalse(Ship.canHaveAsxPosition(-1));
		assertFalse(Ship.canHaveAsxPosition(Double.NaN));
		assertTrue(Ship.canHaveAsxPosition(0));
		assertTrue(Ship.canHaveAsxPosition(5.21));
		assertTrue(Ship.canHaveAsxPosition(Double.POSITIVE_INFINITY));
		assertFalse(Ship.canHaveAsxPosition(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsyPosition(){
		assertFalse(Ship.canHaveAsyPosition(-1));
		assertFalse(Ship.canHaveAsyPosition(Double.NaN));
		assertTrue(Ship.canHaveAsyPosition(0));
		assertTrue(Ship.canHaveAsyPosition(5.21));
		assertTrue(Ship.canHaveAsyPosition(Double.POSITIVE_INFINITY));	
		assertFalse(Ship.canHaveAsyPosition(Double.NEGATIVE_INFINITY));
	}

	@Test
	public void testCanHaveAsxVelocity(){
		assertTrue(Ship.canHaveAsxVelocity(-1));
		assertFalse(Ship.canHaveAsxVelocity(Double.NaN));
		assertTrue(Ship.canHaveAsxVelocity(0));
		assertTrue(Ship.canHaveAsxVelocity(5.21));
		assertTrue(Ship.canHaveAsxVelocity(Double.POSITIVE_INFINITY));	
		assertTrue(Ship.canHaveAsxVelocity(Double.NEGATIVE_INFINITY));
	}
	@Test
	public void testCanHaveAsyVelocity(){
		assertTrue(Ship.canHaveAsyVelocity(-1));
		assertFalse(Ship.canHaveAsyVelocity(Double.NaN));
		assertTrue(Ship.canHaveAsyVelocity(0));
		assertTrue(Ship.canHaveAsyVelocity(5.21));
		assertTrue(Ship.canHaveAsyVelocity(Double.POSITIVE_INFINITY));	
		assertTrue(Ship.canHaveAsyVelocity(Double.NEGATIVE_INFINITY));
	}
	@Test
	public void testGetPosition(){
		Ship ship = new Ship(5,7,-3.1235,999999,12,5*Math.PI/4);
		assertNotNull(ship.getPosition()[0]);
		assertNotNull(ship.getPosition()[1]);
		assertNotEquals(4,ship.getPosition()[0],0.001);
		assertEquals(5,ship.getPosition()[0],0.001);
		assertNotEquals(4,ship.getPosition()[1],0.001);
		assertEquals(7,ship.getPosition()[1],0.001);	
	}
	
	@Test
	public void testGetVelocity(){
		Ship ship = new Ship(5,7,-3.1235,999999,12,5*Math.PI/4);
		assertNotNull(ship.getVelocity()[0]);
		assertNotNull(ship.getVelocity()[1]);
		assertEquals(-3.1235,ship.getVelocity()[0],0.001);
		assertNotEquals(5,ship.getVelocity()[0],0.001);
		assertEquals(999999,ship.getVelocity()[1],0.001);
		assertNotEquals(7,ship.getVelocity()[1],0.001);	
	}
	
	@Test
	public void testCanHaveAsSpeed(){
		assertFalse(Ship.canHaveAsSpeed(-1));
		assertFalse(Ship.canHaveAsSpeed(Double.NaN));
		assertFalse(Ship.canHaveAsSpeed(Double.POSITIVE_INFINITY));
		assertTrue(Ship.canHaveAsSpeed(12543));
		assertTrue(Ship.canHaveAsSpeed(0));
	}
	
	
	public void testMove(){
		Ship ship = new Ship(5,7,-3,2,12,5*Math.PI/4);
		double duration = 1;
		assertEquals(Ship.move(duration)[0]);		
	}
	// Hier zitten dus problemen met het feit dat de methode move niet static is, 
	// maar dat die tests wel static zijn?
	
	public void testTurn(){
		Ship ship = new Ship(5,7,-3,2,12,5*Math.PI/4);
		double angle = 1;
		assertEquals(Ship.move(angle)[0]);		
	}
	// Zelfde probleem als bij move
	
	
	Ship ship;
	@Before
	public void setup(){
		ship = new Ship(5,7,-3,2,12,5*Math.PI/4);
	}
	public void testSetPosition(){
		Ship ship = Ship.setPosition(4,3);
		assertNotNull(ship.getPosition()[0]);
		assertNotNull(ship.getPosition()[1]);
		assertEquals(4,ship.getPosition()[0],0.001);
		assertNotEquals(5,ship.getPosition()[0],0.001);
		assertEquals(3,ship.getPosition()[1],0.001);
		assertNotEquals(7,ship.getPosition()[1],0.001);
	}
	
	@Before
	public void testSetShipVelocity(){
		
	}
}
