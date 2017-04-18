package asteroids.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class TestsPart1 {
	
	private static final double EPSILON = 0.0001;
	
	/**
	 * SHIP
	 */
	
	@Test
	public void testCreateShip(){
		Ship ship = new Ship(1,1,3,4,12,Math.PI,50);
		assertFalse(ship.hasWorld());
	}
	
	@Test
	public void testTerminateShip(){
		Ship ship = new Ship(40,50,3,4,12,Math.PI,50);
		ship.terminate();
		assertFalse(ship.hasSpace());
	}
		
	@Test
	public void testGetPosition(){
		Ship ship = new Ship(5,7,3,4,12,5*Math.PI/4,1);
		assertNotNull(ship.getPosition()[0]);
		assertNotNull(ship.getPosition()[1]);
		assertNotEquals(4,ship.getPosition()[0],EPSILON);
		assertEquals(5,ship.getPosition()[0],EPSILON);
		assertNotEquals(4,ship.getPosition()[1],EPSILON);
		assertEquals(7,ship.getPosition()[1],EPSILON);	
	}
	
	@Test
	public void testGetVelocity(){
		Ship ship = new Ship(5,7,-3.1235,999999,12,5*Math.PI/4,1);
		assertNotNull(ship.getVelocity()[0]);
		assertNotNull(ship.getVelocity()[1]);
		assertEquals(-0.937050937046366,ship.getVelocity()[0],EPSILON);
		assertNotEquals(456158,ship.getVelocity()[0],EPSILON);
		assertEquals(300000,ship.getVelocity()[1],EPSILON);
		assertNotEquals(7,ship.getVelocity()[1],EPSILON);	
	}

	
	@Test
	public void testgetOrientation(){
		Ship ship1 = new Ship(4,5,2,3,15,2,1);
		assertNotNull(ship1.getOrientation());
		assertEquals(2, ship1.getOrientation(),EPSILON);
	}
	
	
	@Test
	public void testGetRadius(){
		Ship ship1 = new Ship(4,5,2,3,15,2,1);
		assertNotNull(ship1.getRadius());
		assertEquals(15, ship1.getRadius(),EPSILON);
	}
	
	@Test
	public void testGetMass(){
		Ship ship1 = new Ship(4,5,2,3,15,2,1);
		assertNotNull(ship1.getMass());
		assertEquals(2.0074777056438776*Math.pow(10,16),ship1.getMass(),EPSILON);
		Ship ship2 = new Ship(4,5,2,3,15,2,6*Math.pow(10,16));
		assertEquals(6*Math.pow(10,16), ship2.getMass(),EPSILON);
		
	}
	
	@Test
	public void testPlaceShipInWorld(){
		World world = new World(8000, 8000);
		RoundEntity firstShip = new Ship(100, 100, -10, 20, 10, Math.PI, 10E12);
		RoundEntity secondShip = new Ship(200, 100, -5, 20, 15, Math.PI/3, 20E12);
		RoundEntity thirdShip = new Ship(100, 200, -10, 0, 20, Math.PI/4, 15E12);
		firstShip.placeInSpace(world); 
		secondShip.placeInSpace(world);
		thirdShip.placeInSpace(world);
		assertEquals(3, world.getWorldShips().size());
		assertEquals(0, world.getWorldBullets().size());
	}	

	
//	/**
//	 * BULLET
//	 */
//
//	@Test
//	public void testPlaceBulletInWorld(){
//		World world = new World(8000, 8000);
//		Bullet firstBullet = new Bullet(1000, 800, 100, 200, 7);
//		firstBullet.placeInSpace(world);
//		assertEquals(1,world.getWorldBullets().size());
//		assertEquals(world,firstBullet.getWorld());
//	}
//	
//	/**
//	 * WORLD
//	 */
//	
//	/**
//	 * ROUND ENTITIES ACTIONS
//	 */
//	
//	@Test
//	public void testMove(){
//		Ship ship = new Ship(5,7,-3,2,12,5*Math.PI/4,1);
//		ship.move(1);
//		assertEquals(2,ship.getPosition()[0],EPSILON);
//		assertEquals(9,ship.getPosition()[1],EPSILON);		
//		assertNotEquals(7,ship.getPosition()[0],EPSILON);		
//		assertNotEquals(Double.NaN,ship.getPosition()[1],EPSILON);		
//	}
//
//	@Test	
//	public void testTurn(){
//		Ship ship = new Ship(5,7,-3,2,12,5*Math.PI/4,1);
//		ship.turn(-Math.PI/4);
//		assertEquals(Math.PI,ship.getOrientation(),EPSILON);	
//		assertNotEquals(-Math.PI/4,ship.getOrientation(),EPSILON);
//	}	
//	
//	@Test
//	public void testThrust(){
//		Ship ship1 = new Ship(4,5,2,3,15,0,1);
//		ship1.thrust(2,1);
//		assertTrue(ship1.getVelocity()[0] == 4);
//		assertTrue(ship1.getVelocity()[1] == 3);
//		
//		Ship ship2 = new Ship(4,5,2,3,15,0,1);
//		ship1.thrust(0,5);
//		assertTrue(ship2.getVelocity()[0] == 2);
//		assertTrue(ship2.getVelocity()[1] == 3);
//		
//	}
//	
//	@Test
//	public void testCanHaveAsA() {
//		assertFalse(Ship.canHaveAsAcceleration(-1));
//		assertTrue(Ship.canHaveAsAcceleration(0));
//		assertFalse(Ship.canHaveAsAcceleration(Double.NaN));
//		assertTrue(Ship.canHaveAsAcceleration(3));
//	}
//	
//	@Test
//	public void testGetDistanceBetween() {
//		Ship ship1 = new Ship(0,25,2,3,10,2,1);
//		Ship ship2 = new Ship(0,25,2,3,10,2,1);
//		assertTrue(ship1.getDistanceBetween(ship2) == 0);
//		
//		Ship ship3 = new Ship(0,20,2,3,15,2,1);
//		Ship ship4 = new Ship(0,-5,3,5,10,2,1);
//		assertTrue(ship3.getDistanceBetween(ship4) == 0);
//		
//		Ship ship5 = new Ship(0,20,2,3,15,2,1);
//		Ship ship6 = new Ship(0,5,3,5,10,2,1);
//		assertTrue(ship5.getDistanceBetween(ship6) == -10);
//		
//		Ship ship7 = new Ship(0,20,2,3,15,2,1);
//		Ship ship8 = new Ship(0,-15,3,5,10,2,1);
//		assertTrue(ship7.getDistanceBetween(ship8) == 10);
//		
//	}
//	
//	@Test
//	public void testOverlap() {
//		Ship ship1 = new Ship(0,25,2,3,10,2,1);
//		Ship ship2 = new Ship(0,-5,3,5,10,2,1);
//		assertFalse(ship1.overlap(ship2));
//		
//		Ship ship3 = new Ship(0,20,2,3,15,2,1);
//		Ship ship4 = new Ship(0,-5,3,5,10,2,1);
//		assertFalse(ship3.overlap(ship4));
//		
//		Ship ship5 = new Ship(0,20,2,3,15,2,1);
//		Ship ship6 = new Ship(0,5,3,5,10,2,1);
//		assertTrue(ship5.overlap(ship6));
//	}	
//	
//	Ship ship;
//	Ship other;
//	@Before
//	public void setup(){
//		ship = new Ship(10,40,10,5,10,5*Math.PI/4,1);
//		other =  new Ship(50,50,-10,0,20,2.5,1);
//	}
//	
//	@Test
//	public void testSetPosition(){
//		ship.setPosition(4,3);
//		assertNotNull(ship.getPosition()[0]);
//		assertNotNull(ship.getPosition()[1]);
//		assertEquals(4,ship.getPosition()[0],EPSILON);
//		assertNotEquals(5,ship.getPosition()[0],EPSILON);
//		assertEquals(3,ship.getPosition()[1],EPSILON);
//		assertNotEquals(7,ship.getPosition()[1],EPSILON);
//	}
//	
//	@Test
//	public void testSetVelocity(){
//		ship.setVelocity(3,4);
//		assertEquals(3,ship.getVelocity()[0],EPSILON);
//		assertNotEquals(5,ship.getVelocity()[0],EPSILON);
//		assertEquals(4,ship.getVelocity()[1],EPSILON);
//		assertNotEquals(Double.NaN,ship.getVelocity()[1],EPSILON);	
//	}
//	
//	@Test 
//	public void testGetTimeToCollision(){
//		assertEquals(2-Math.sqrt(36*17)/17,ship.getTimeToCollision(other),EPSILON);
//		assertNotEquals(Double.POSITIVE_INFINITY,ship.getTimeToCollision(other),EPSILON);
//	}
//	
//	@Test 
//	public void testGetCollisionPosition(){
//		//assertEquals(25.14928745,ship.getCollisionPosition(other)[0],EPSILON);
//		assertEquals(45.1492875,ship.getCollisionPosition(other)[1],EPSILON);
//	}
//	
//	/**
//	 * FACADE
//	 */
//	
//	IFacade facade;
//
//	@Before
//	public void setUp() {
//		facade = new Facade();
//	}
//
//	@Test
//	public void testCreateWorld() throws ModelException {
//		World world = facade.createWorld(1000, 800);
//		assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
//		assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
//		assertTrue(facade.getWorldShips(world).isEmpty());
//		assertTrue(facade.getWorldBullets(world).isEmpty());
//		assertFalse(facade.isTerminatedWorld(world));
//		facade.terminateWorld(world);
//		assertTrue(facade.isTerminatedWorld(world));
//	}
//	
//	@Test
//	public void testEntity() throws ModelException {
//		World world = facade.createWorld(1000, 800);
//		RoundEntity entity = facade.createBullet(400, 20, -7, 3, 5);
//		assertTrue(world.fitBoundary(entity));
//		entity.placeInSpace(world);
//		assertEquals(1,facade.getWorldBullets(world).size());
//		assertEquals(entity, world.getEntityAt((double)400, (double)20));
//		UnboundSpace unboundspace = new UnboundSpace();
//		entity.placeInSpace(unboundspace);
//		assertEquals(0,facade.getWorldBullets(world).size());
//		assertEquals(0,facade.getEntities(world).size() );
//	}
//	
//	@Test
//	public void testShip() throws ModelException {
//		World world = facade.createWorld(8000, 8000);
//		RoundEntity firstShip = facade.createShip(100, 100, -10, 20, 10, Math.PI, 10E12);
//		RoundEntity secondShip = facade.createShip(200, 100, -5, 20, 15, Math.PI/3, 20E12);
//		RoundEntity thirdShip = facade.createShip(100, 200, -10, 0, 20, Math.PI/4, 15E12);
//		firstShip.placeInSpace(world); 
//		secondShip.placeInSpace(world);
//		thirdShip.placeInSpace(world);
//		assertEquals(3, facade.getWorldShips(world).size());
//		assertEquals(0,facade.getWorldBullets(world).size());
//		RoundEntity firstBullet = facade.createBullet(1000, 800, 100, 200, 7);
//		firstBullet.placeInSpace(world);
//		assertEquals(1,facade.getWorldBullets(world).size());
//		assertEquals(world,facade.getBulletWorld((Bullet) firstBullet));
//	}
//	
//	@Test
//	public void testGetTimeAndPlaceToNextCollision() throws ModelException{
//		World world = facade.createWorld(8000, 8000);
//		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 15);
//		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
//		firstShip.placeInSpace(world);secondShip.placeInSpace(world);
//		assertEquals(firstShip.getSpace(),secondShip.getSpace());
//		assertEquals(world.getTimeNextCollision(),5/2,EPSILON);
//		assertTrue(world.getPositionNextCollision() == new double[] {75,100});
//		
//	}
//	
//	@Test 
//	public void testEvolveWorld() throws ModelException{
//		World world = facade.createWorld(8000, 8000);
//		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 10);
//		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
//		RoundEntity thirdShip = facade.createShip(60,100,-25,0,10,Math.PI,10);
//		firstShip.placeInSpace(world);secondShip.placeInSpace(world);thirdShip.placeInSpace(world);
//		world.evolve(1, null);
//		firstShip.getVelocityAfterShipHitShip(secondShip);
//		assertEquals(firstShip.getVelocity(),new double[] {-10,0});
//		assertEquals(secondShip.getVelocity(),new double[] {-50,0});
//		assertEquals(thirdShip.getVelocity(),new double[] {-25,0});
//	} 
//	
//	@Test 
//	public void testGetEntityToHitWall() throws ModelException{
//		World world = facade.createWorld(8000, 8000);
//		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 10);
//		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
//		RoundEntity thirdShip = facade.createShip(60,100,-25,0,10,Math.PI,10);
//		firstShip.placeInSpace(world);secondShip.placeInSpace(world);thirdShip.placeInSpace(world);
//		assertFalse(thirdShip.isTerminated());
//		assertEquals(2,thirdShip.getTimeToHitWall(),EPSILON);
//		//assertEquals(thirdShip.getPositionOfHitWall(), new double[] {0,100});
//		assertTrue(thirdShip.hasHitWall());
//		thirdShip.getVelocityAfterEntityHitWall();
//		assertEquals(thirdShip.getVelocity(),new double[] {10,0});
//		assertFalse(4==firstShip.getTimeToHitWall());
//	}
//	
//	
}