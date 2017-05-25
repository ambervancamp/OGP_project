package asteroids.model;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.util.ModelException;

public class TestsPart1 {
	
	private static final double EPSILON = 0.0001;
	
//	@Test
//	public void testje(){
//		Statement()
//		Program program = new Program()
//		this.getProgram().getPrintResults().add(this.getValue().evaluate(ExecutingShip, ExecutingFunction));
//	}
//	
	
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
		assertTrue(ship.isTerminated());
		assertEquals(ship.getNbBullets(),0, EPSILON);
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
		//assertEquals(-3.1235*ship.getMaxSpeed()/ship.getSpeed(),ship.getVelocity()[0],EPSILON);
		assertNotEquals(456158,ship.getVelocity()[0],EPSILON);
		assertEquals(30000,ship.getVelocity()[1],EPSILON);
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
		
		Bullet bullet1 = new Bullet(5, 5, 100, 200, 7);
		bullet1.placeInShip(ship2);
		assertEquals(7.1206689313885504*Math.pow(10, 16), ship2.getMass(),EPSILON);
		
		Ship ship3 = new Ship(4,5,2,3,15,2,6*Math.pow(10,16));
		Bullet bullet2 = new Bullet(2.5, 6, 100, 200, 7);
		Bullet bullet3 = new Bullet(5, 4, 101, 20, 7);
		List<Bullet> bullets1 = new ArrayList<Bullet>();
		bullets1.add(bullet2);
		bullets1.add(bullet3);
		assertEquals(bullets1.size(),2);
		ship3.placeBulletsInShip(bullets1);
		assertEquals(8.2413378627771008*Math.pow(10, 16), ship3.getMass(),EPSILON);
	}
	
	@Test
	public void testPlaceShipInWorld() throws ClassNotFoundException{
		World world = new World(8000, 8000);
		RoundEntity firstShip = new Ship(100, 100, -10, 20, 10, Math.PI, 10E12);
		RoundEntity secondShip = new Ship(200, 100, -5, 20, 15, Math.PI/3, 20E12);
		RoundEntity thirdShip = new Ship(100, 200, -10, 0, 20, Math.PI/4, 15E12);
		RoundEntity firstBullet = new Bullet(500,100,0,0,10);
		firstShip.placeInSpace(world); 
		secondShip.placeInSpace(world);
		thirdShip.placeInSpace(world);
		assertEquals(3, world.getEntityOfClass(firstShip.getClass()).size());
		assertEquals(0, world.getEntityOfClass(firstBullet.getClass()).size());
	}
	

	
	/**
	 * BULLET
	 * @throws ClassNotFoundException 
	 */

	@Test
	public void testPlaceBulletInWorld() throws ClassNotFoundException{
		World world = new World(8000, 8000);
		Bullet firstBullet = new Bullet(1000, 800, 100, 200, 7);
		firstBullet.placeInSpace(world);
		assertEquals(1,world.getEntityOfClass(firstBullet.getClass()).size());
		assertEquals(world,firstBullet.getWorld());
	}
	
	/**
	 * WORLD
	 */
	
	/**
	 * ROUND ENTITIES ACTIONS
	 */
	
	@Test
	public void testMove(){
		Ship ship = new Ship(5,7,-3,2,12,5*Math.PI/4,1); 
		ship.move(1);
		assertEquals(2,ship.getPosition()[0],EPSILON);
		assertEquals(9,ship.getPosition()[1],EPSILON);		
		assertNotEquals(7,ship.getPosition()[0],EPSILON);		
		assertNotEquals(Double.NaN,ship.getPosition()[1],EPSILON);	
		ship.move(1);
		assertEquals(-1,ship.getPosition()[0],EPSILON);
		assertEquals(11,ship.getPosition()[1],EPSILON);		
	}

	@Test	
	public void testTurn(){
		Ship ship = new Ship(5,7,-3,2,12,5*Math.PI/4,1);
		ship.turn(-Math.PI/4);
		assertEquals(Math.PI,ship.getOrientation(),EPSILON);	
		assertNotEquals(-Math.PI/4,ship.getOrientation(),EPSILON);
	}	
	
	@Test
	public void testThrust(){
		Ship ship1 = new Ship(4,5,2,3,15,0,1);
		ship1.thrust(2,1);
		assertTrue(ship1.getVelocity()[0] == 4);
		assertTrue(ship1.getVelocity()[1] == 3);
		
		Ship ship2 = new Ship(4,5,2,3,15,0,1);
		ship1.thrust(0,5);
		assertTrue(ship2.getVelocity()[0] == 2);
		assertTrue(ship2.getVelocity()[1] == 3);	
	}
	
	@Test
	public void testGetDistanceBetween() {
		Ship ship1 = new Ship(0,25,2,3,10,2,1);
		Ship ship2 = new Ship(0,25,2,3,10,2,1);
		assertFalse(ship1.getDistanceBetween(ship2)==0);

		Ship ship3 = new Ship(0,20,2,3,15,2,1);
		Ship ship4 = new Ship(0,-5,3,5,10,2,1);
		assertTrue(ship3.getDistanceBetween(ship4) == 0);
		
		Ship ship5 = new Ship(0,20,2,3,15,2,1);
		Ship ship6 = new Ship(0,5,3,5,10,2,1);
		assertTrue(ship5.getDistanceBetween(ship6) == -10);
		
		Ship ship7 = new Ship(0,20,2,3,15,2,1);
		Ship ship8 = new Ship(0,-15,3,5,10,2,1);
		assertTrue(ship7.getDistanceBetween(ship8) == 10);
		
	}
	
	@Test
	public void testOverlap() {
		Ship ship1 = new Ship(0,25,2,3,10,2,1);
		Ship ship2 = new Ship(0,-5,3,5,10,2,1);
		assertFalse(ship1.overlap(ship2));
		
		Ship ship3 = new Ship(0,20,2,3,15,2,1);
		Ship ship4 = new Ship(0,-5,3,5,10,2,1);
		assertFalse(ship3.overlap(ship4));
		
		Ship ship5 = new Ship(0,20,2,3,15,2,1);
		Ship ship6 = new Ship(0,5,3,5,10,2,1);
		assertTrue(ship5.overlap(ship6));
	}
		
	Ship ship1;
	Ship ship2;
	Ship other1;
	Space world1;
	@Before
	public void setup(){
		ship1 = new Ship(20,50,10,0,10,5*Math.PI/4,1000);
		ship2 = new Ship(400,500,20,30,15,2,1000);
		other1 =  new Ship(60,50,-10,0,20,2.5,1000);
		world1 = new World(1000,2000);
		ship1.placeInSpace(world1);
		ship2.placeInSpace(world1);
		other1.placeInSpace(world1);
	}
	
	@Test 
	public void testGetTimeToCollision(){
		assertTrue(ship1.inSameSpace(ship2));
		assertEquals(Double.POSITIVE_INFINITY, ship2.getTimeToCollision(other1), EPSILON);
		assertEquals(0.5,ship1.getTimeToCollision(other1),EPSILON);
		
	}
	
	@Test 
	public void testGetCollisionPosition(){
		assertEquals(35,ship1.getCollisionPosition(other1)[0],EPSILON);
		assertEquals(50,ship1.getCollisionPosition(other1)[1],EPSILON);
	}
	

	@Test
	public void testCreateWorld() throws ModelException, ClassNotFoundException {
		World world = new World(1000, 800);
		assertEquals(1000, world.getSpaceSize()[0], EPSILON);
		assertEquals(800, world.getSpaceSize()[1], EPSILON);
		RoundEntity ship = new Ship(10,10,10,10,10,Math.PI, 10);
		RoundEntity bullet = new Bullet(10,10,10,10,10);
		assertTrue(world.getEntityOfClass(ship.getClass()).isEmpty());
		assertTrue(world.getEntityOfClass(bullet.getClass()).isEmpty());
		assertFalse(world.isTerminated());
		world.terminate();
		assertTrue(world.isTerminated());
	}
	
	@Test
	public void testEntity() throws ModelException, ClassNotFoundException {
		World world = new World(1000, 800);
		RoundEntity entity = new Bullet(400, 20, -7, 3, 5);
		assertTrue(world.fitBoundary(entity));
		entity.placeInSpace(world);
		assertEquals(1,world.getEntityOfClass(entity.getClass()).size());
		assertEquals(entity, world.getEntityAt((double)400, (double)20));
		UnboundSpace unboundspace = new UnboundSpace();
		entity.placeInSpace(unboundspace);
		assertEquals(0,world.getEntityOfClass(entity.getClass())
				.size());
		assertEquals(0,world.getEntities().size() );
	}
	

	@Test 
	public void testEvolveWorld() throws ModelException{
		World world = new World(8000, 8000);
		RoundEntity firstShip = new Ship(90, 100, -10, 0, 10, Math.PI, 10);
		RoundEntity secondShip = new Ship(210, 100, -50, 0, 10, Math.PI/3, 10);
		RoundEntity thirdShip = new Ship(60,100,-25,0,10,Math.PI,10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);thirdShip.placeInSpace(world);
		world.evolve(1, null);
		assertEquals(firstShip.getVelocity()[0],-10,EPSILON);
		assertEquals(firstShip.getVelocity()[1],0,EPSILON);
		assertEquals(secondShip.getVelocity()[0],-50,EPSILON);
		assertEquals(secondShip.getVelocity()[1],0,EPSILON);
		assertEquals(thirdShip.getVelocity()[0],-25,EPSILON);
		assertEquals(thirdShip.getVelocity()[1],0,EPSILON);
	} 
	
	@Test 
	public void testEvolve() throws ModelException{
		World world = new World(8000, 8000);
		RoundEntity firstShip = new Ship(90, 100, -10, 0, 10, Math.PI, 10);
		RoundEntity secondShip =new Ship(230, 100, -50, 0, 10, Math.PI/3, 10);
		RoundEntity thirdShip = new Ship(60,100,-25,0,10,Math.PI,10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);thirdShip.placeInSpace(world);
		
		assertFalse(thirdShip.isTerminated());
		assertEquals(thirdShip.getTimeToHitWall(),2,EPSILON);
		assertEquals(thirdShip.getPositionOfHitWall()[0],0,EPSILON);
		assertEquals(thirdShip.getPositionOfHitWall()[1],100,EPSILON);
		assertTrue(thirdShip.hasHitWall());
		world.evolve(3, null);
		// collision with wall after 2 seconds (thirdShip)
		// collision between 2 entities after 3 seconds
		assertEquals(firstShip.getVelocity()[0],-50,EPSILON);
		assertEquals(firstShip.getVelocity()[1],0,EPSILON);
		assertEquals(secondShip.getVelocity()[0],-10,EPSILON);
		assertEquals(secondShip.getVelocity()[1],0,EPSILON);
		assertEquals(thirdShip.getVelocity()[0],25,EPSILON);
		assertEquals(thirdShip.getVelocity()[1],0,EPSILON);
		
	
	}
	
	@Test public void testGetTimeNextCollision() throws ModelException {
		World world1 = new World(5000, 5000);
		Ship firstShip = new Ship(100, 120, 30, 0, 50, Math.PI, 1.1E18);
		Ship secondShip = new Ship(300, 120, 10, 0, 50, Math.PI, 1.1E18);
		firstShip.placeInSpace(world1);
		secondShip.placeInSpace(world1);
		assertTrue(firstShip.inSameSpace(secondShip));
		assertFalse(firstShip.overlap(secondShip));
		assertTrue(firstShip.canAsCollision(secondShip));
		assertEquals(world1.getTimeNextCollision(),5,EPSILON);
	}
	
	@Test
	public void testGetTimeAndPlaceToNextCollision() throws ModelException{
		World world = new World(8000, 8000);
		RoundEntity firstShip =  new Ship(90, 100, -10, 0, 10, Math.PI, 15);
		RoundEntity secondShip = new Ship(210, 100, -50, 0, 10, Math.PI/3, 10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);
		assertEquals(firstShip.getSpace(),secondShip.getSpace());
		assertEquals(world.getTimeNextCollision(),5.0/2.0,EPSILON);
		assertTrue(world.getPositionNextCollision()[0] == 75);
		assertTrue(world.getPositionNextCollision()[1] == 100);	
	}
	
	@Test
	public void testAddBulletsAndShipsToWorld() throws ModelException {
		World world = new World(5000, 5000);
		Ship firstShip = new Ship(100, 120, 10, 0, 50, Math.PI, 1.1E18);
		Ship secondShip = new Ship(200, 120, 10, 0, 50, Math.PI, 1.1E18);
		Ship thirdShip = new Ship(4950, 120, 10, 0, 200, Math.PI, 1.1E18);
		Bullet bullet = new Bullet(100, 200, -1, 2, 2);
		firstShip.placeInSpace(world);
		secondShip.placeInSpace(world);
		assertFalse(world.fitBoundary(thirdShip));
		assertEquals(firstShip.getSpace(),world);
		List<RoundEntity> entity = new ArrayList<>(Arrays.asList(firstShip, secondShip));
		assertTrue(entity.containsAll(world.getEntities()));
		bullet.placeInSpace(world);
	}
	
	
}