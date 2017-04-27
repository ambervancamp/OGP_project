package asteroids.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class TestJaps {
	/**
	 * FACADE
	 */
	
	private static final double EPSILON = 0.0001;

	
	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	@Test
	public void testCreateWorld() throws ModelException {
		World world = facade.createWorld(1000, 800);
		assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
		assertTrue(facade.getWorldShips(world).isEmpty());
		assertTrue(facade.getWorldBullets(world).isEmpty());
		assertFalse(facade.isTerminatedWorld(world));
		facade.terminateWorld(world);
		assertTrue(facade.isTerminatedWorld(world));
	}
	
	@Test
	public void testEntity() throws ModelException {
		World world = facade.createWorld(1000, 800);
		RoundEntity entity = facade.createBullet(400, 20, -7, 3, 5);
		assertTrue(world.fitBoundary(entity));
		entity.placeInSpace(world);
		assertEquals(1,facade.getWorldBullets(world).size());
		assertEquals(entity, world.getEntityAt((double)400, (double)20));
		UnboundSpace unboundspace = new UnboundSpace();
		entity.placeInSpace(unboundspace);
		assertEquals(0,facade.getWorldBullets(world).size());
		assertEquals(0,facade.getEntities(world).size() );
	}
	
	@Test
	public void testShip() throws ModelException {
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(100, 100, -10, 20, 10, Math.PI, 10E12);
		RoundEntity secondShip = facade.createShip(200, 100, -5, 20, 15, Math.PI/3, 20E12);
		RoundEntity thirdShip = facade.createShip(100, 200, -10, 0, 20, Math.PI/4, 15E12);
		firstShip.placeInSpace(world); 
		secondShip.placeInSpace(world);
		thirdShip.placeInSpace(world);
		assertEquals(3, facade.getWorldShips(world).size());
		assertEquals(0,facade.getWorldBullets(world).size());
		RoundEntity firstBullet = facade.createBullet(1000, 800, 100, 200, 7);
		firstBullet.placeInSpace(world);
		assertEquals(1,facade.getWorldBullets(world).size());
		assertEquals(world,facade.getBulletWorld((Bullet) firstBullet));
	}
	
	@Test
	public void testGetTimeAndPlaceToNextCollision() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 15);
		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);
		assertEquals(firstShip.getSpace(),secondShip.getSpace());
		assertEquals(world.getTimeNextCollision(),5/2,EPSILON);
		assertTrue(world.getPositionNextCollision() == new double[] {75,100});
		
	}
	
	@Test 
	public void testEvolveWorld() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 10);
		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
		RoundEntity thirdShip = facade.createShip(60,100,-25,0,10,Math.PI,10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);thirdShip.placeInSpace(world);
		world.evolve(1, null);
		firstShip.getVelocityAfterShipHitShip(secondShip);
		assertEquals(firstShip.getVelocity(),new double[] {-10,0});
		assertEquals(secondShip.getVelocity(),new double[] {-50,0});
		assertEquals(thirdShip.getVelocity(),new double[] {-25,0});
	} 
	
	@Test 
	public void testGetEntityToHitWall() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 10);
		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
		RoundEntity thirdShip = facade.createShip(60,100,-25,0,10,Math.PI,10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);thirdShip.placeInSpace(world);
		assertFalse(thirdShip.isTerminated());
		assertEquals(2,thirdShip.getTimeToHitWall(),EPSILON);
		//assertEquals(thirdShip.getPositionOfHitWall(), new double[] {0,100});
		assertTrue(thirdShip.hasHitWall());
		thirdShip.getVelocityAfterEntityHitWall();
		assertEquals(thirdShip.getVelocity(),new double[] {10,0});
		assertFalse(4==firstShip.getTimeToHitWall());
	}
	
	@Test
	public void testAddBulletsAndShipsWorld() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship firstShip = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
		Ship secondShip = facade.createShip(200, 120, 10, 0, 50, Math.PI, 1.1E18);
		Ship thirdShip = facade.createShip(4950, 120, 10, 0, 200, Math.PI, 1.1E18);
		Bullet bullet = facade.createBullet(100, 150, -1, 2, 2);
		facade.addShipToWorld(world, firstShip);
		facade.addShipToWorld(world, secondShip);
		assertFalse(world.fitBoundary(thirdShip));
		assertEquals(firstShip.getSpace(),world);
		List<RoundEntity> entity = new ArrayList<>(Arrays.asList(firstShip, secondShip));
		assertEquals(world.getEntities(),entity);
		facade.addBulletToWorld(world, bullet);	
	}
	
}
