package asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestsPart1 {
	
	private static final double EPSILON = 0.0001;

	@Test
	public void testCanHaveAsOrientation(){
		assertFalse(Ship.canHaveAsOrientation(-1));
		assertFalse(Ship.canHaveAsOrientation(2*Math.PI));
		assertFalse(Ship.canHaveAsOrientation(Double.NaN));
		assertTrue(Ship.canHaveAsOrientation(0));
		assertTrue(Ship.canHaveAsOrientation(2));
	}
	
	@Test
	public void testCanHaveAsRadius(){
		assertFalse(Ship.canHaveAsRadius(0));
		assertFalse(Ship.canHaveAsRadius(Double.NaN));
		assertTrue(Ship.canHaveAsRadius(10));
		assertTrue(Ship.canHaveAsRadius(15));
	}
	
	@Test
	public void testgetOrientation(){
		Ship ship1 = new Ship(4,5,2,3,15,2);
		Ship ship2 = new Ship();
		assertNotNull(ship1.getOrientation());
		assertNotNull(ship2.getOrientation());
		assertEquals(2, ship1.getOrientation(),EPSILON);
		assertEquals(0, ship2.getOrientation(),EPSILON);

	}
	
	@Test
	public void testGetRadius(){
		Ship ship1 = new Ship(4,5,2,3,15,2);
		Ship ship2 = new Ship();
		assertNotNull(ship1.getRadius());
		assertNotNull(ship2.getRadius());
		assertEquals(15, ship1.getRadius(),EPSILON);
		assertEquals(10, ship2.getRadius(),EPSILON);

	}
	
	@Test
	public void testThrust(){
		Ship ship1 = new Ship(4,5,2,3,15,0);
		ship1.thrust(2);
		assertTrue(ship1.getVelocity()[0] == 4);
		assertTrue(ship1.getVelocity()[1] == 3);
		
		Ship ship2 = new Ship(4,5,2,3,15,0);
		ship1.thrust(0);
		assertTrue(ship2.getVelocity()[0] == 2);
		assertTrue(ship2.getVelocity()[1] == 3);
		
	}
	
	@Test
	public void testCanHaveAsA() {
		assertFalse(Ship.canHaveAsA(-1));
		assertTrue(Ship.canHaveAsA(0));
		assertFalse(Ship.canHaveAsA(Double.NaN));
		assertTrue(Ship.canHaveAsA(3));
	}
	
	@Test
	public void testGetDistanceBetween() {
		Ship ship1 = new Ship(0,25,2,3,10,2);
		Ship ship2 = new Ship(0,25,2,3,10,2);
		assertTrue(ship1.getDistanceBetween(ship2) == 0);
		
		Ship ship3 = new Ship(0,20,2,3,15,2);
		Ship ship4 = new Ship(0,-5,3,5,10,2);
		assertTrue(ship3.getDistanceBetween(ship4) == 0);
		
		Ship ship5 = new Ship(0,20,2,3,15,2);
		Ship ship6 = new Ship(0,5,3,5,10,2);
		assertTrue(ship5.getDistanceBetween(ship6) == -10);
		
		Ship ship7 = new Ship(0,20,2,3,15,2);
		Ship ship8 = new Ship(0,-15,3,5,10,2);
		assertTrue(ship7.getDistanceBetween(ship8) == 10);
		
	}
	
	@Test
	public void testOverlap() {
		Ship ship1 = new Ship(0,25,2,3,10,2);
		Ship ship2 = new Ship(0,-5,3,5,10,2);
		assertFalse(ship1.overlap(ship2));
		
		Ship ship3 = new Ship(0,20,2,3,15,2);
		Ship ship4 = new Ship(0,-5,3,5,10,2);
		assertTrue(ship3.overlap(ship4));
		
		Ship ship5 = new Ship(0,20,2,3,15,2);
		Ship ship6 = new Ship(0,5,3,5,10,2);
		assertTrue(ship5.overlap(ship6));
	}
	
	// ANDER IEMAND
	
	
	
	
	@Test
	public void testCanHaveAsxPosition(){
		assertTrue(Ship.canHaveAsxPosition(-1));
		assertFalse(Ship.canHaveAsxPosition(Double.NaN));
		assertTrue(Ship.canHaveAsxPosition(0));
		assertTrue(Ship.canHaveAsxPosition(5.21));
		assertTrue(Ship.canHaveAsxPosition(Double.POSITIVE_INFINITY));
		assertTrue(Ship.canHaveAsxPosition(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsyPosition(){
		assertTrue(Ship.canHaveAsyPosition(-1));
		assertFalse(Ship.canHaveAsyPosition(Double.NaN));
		assertTrue(Ship.canHaveAsyPosition(0));
		assertTrue(Ship.canHaveAsyPosition(5.21));
		assertTrue(Ship.canHaveAsyPosition(Double.POSITIVE_INFINITY));	
		assertTrue(Ship.canHaveAsyPosition(Double.NEGATIVE_INFINITY));
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
		assertNotEquals(4,ship.getPosition()[0],EPSILON);
		assertEquals(5,ship.getPosition()[0],EPSILON);
		assertNotEquals(4,ship.getPosition()[1],EPSILON);
		assertEquals(7,ship.getPosition()[1],EPSILON);	
	}
	
	@Test
	public void testGetVelocity(){
		Ship ship = new Ship(5,7,-3.1235,999999,12,5*Math.PI/4);
		assertNotNull(ship.getVelocity()[0]);
		assertNotNull(ship.getVelocity()[1]);
		assertEquals(-3.1235,ship.getVelocity()[0],EPSILON);
		assertNotEquals(5,ship.getVelocity()[0],EPSILON);
		assertEquals(999999,ship.getVelocity()[1],EPSILON);
		assertNotEquals(7,ship.getVelocity()[1],EPSILON);	
	}
	
	@Test
	public void testCanHaveAsSpeed(){
		assertFalse(Ship.canHaveAsSpeed(-1));
		assertFalse(Ship.canHaveAsSpeed(Double.NaN));
		assertFalse(Ship.canHaveAsSpeed(Double.POSITIVE_INFINITY));
		assertTrue(Ship.canHaveAsSpeed(12543));
		assertTrue(Ship.canHaveAsSpeed(0));
	}
	
	@Test
	public void testMove(){
		Ship ship = new Ship(5,7,-3,2,12,5*Math.PI/4);
		ship.move(1);
		assertEquals(2,ship.getPosition()[0],EPSILON);
		assertEquals(9,ship.getPosition()[1],EPSILON);		
		assertNotEquals(7,ship.getPosition()[0],EPSILON);		
		assertNotEquals(Double.NaN,ship.getPosition()[1],EPSILON);		
	}

	@Test	
	public void testTurn(){
		Ship ship = new Ship(5,7,-3,2,12,5*Math.PI/4);
		ship.turn(-Math.PI/4);
		assertEquals(Math.PI,ship.getOrientation(),EPSILON);	
		assertNotEquals(-Math.PI/4,ship.getOrientation(),EPSILON);
	}	
	
	Ship ship;
	Ship other;
	@Before
	public void setup(){
		ship = new Ship(10,40,10,5,10,5*Math.PI/4);
		other =  new Ship(50,50,-10,0,20,2.5);
	}
	
	@Test
	public void testSetPosition(){
		ship.setPosition(4,3);
		assertNotNull(ship.getPosition()[0]);
		assertNotNull(ship.getPosition()[1]);
		assertEquals(4,ship.getPosition()[0],EPSILON);
		assertNotEquals(5,ship.getPosition()[0],EPSILON);
		assertEquals(3,ship.getPosition()[1],EPSILON);
		assertNotEquals(7,ship.getPosition()[1],EPSILON);
	}
	
	@Test
	public void testSetVelocity(){
		ship.setVelocity(3,4);
		assertEquals(3,ship.getVelocity()[0],EPSILON);
		assertNotEquals(5,ship.getVelocity()[0],EPSILON);
		assertEquals(4,ship.getVelocity()[1],EPSILON);
		assertNotEquals(Double.NaN,ship.getVelocity()[1],EPSILON);	
	}
	
	@Test 
	public void testGetTimeToCollision(){
		assertEquals(2-Math.sqrt(36*17)/17,ship.getTimeToCollision(other),EPSILON);
		assertNotEquals(Double.POSITIVE_INFINITY,ship.getTimeToCollision(other),EPSILON);
	}
	
	@Test 
	public void testGetCollisionPosition(){
		// assertEquals(25.14928745,ship.getCollisionPosition(other)[0],EPSILON);
		assertEquals(45.1492875,ship.getCollisionPosition(other)[1],EPSILON);
	}
}
