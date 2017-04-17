package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.RoundEntity;
import asteroids.model.Ship;
import asteroids.model.Space;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class TestJaps{

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
		world.deleteEntity(entity);
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
		assertEquals(0,facade.getWorldBullets(world));
		RoundEntity firstBullet = facade.createBullet(1000, 800, 100, 200, 7);
		firstBullet.placeInSpace(world);
		assertEquals(1,facade.getWorldBullets(world));
		assertEquals(world,facade.getBulletWorld((Bullet) firstBullet));
	}
	
	@Test
	public void testGetTimeAndPlaceToNextCollision() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 15);
		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);
		assertEquals(firstShip.getSpace(),secondShip.getSpace());
		assertTrue(world.getTimeNextCollision()==5/2);
		assertTrue(world.getPositionNextCollision() == new double[] {75,100});
		
		RoundEntity thirdShip = facade.createShip(60,100,-25,18,10,Math.PI,20);
		thirdShip.placeInSpace(world);
		assertTrue(world.getTimeNextCollision() == 2);
		assertTrue(world.getPositionNextCollision() == new double[] {0,136});
		
	}
	
	@Test 
	public void testEvolveWorld() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 10);
		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
		RoundEntity thirdShip = facade.createShip(60,100,-25,0,10,Math.PI,10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);thirdShip.placeInSpace(world);
		world.evolve(3, null);
		firstShip.getVelocityAfterShipHitShip(secondShip);
		assertEquals(firstShip.getVelocity(),new double[] {-30,0});
		assertEquals(secondShip.getVelocity(),new double[] {90,0});
		assertEquals(thirdShip.getVelocity(),new double[] {25,0});
	} 
	
	@Test 
	public void testGetEntityToHitWall() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 10);
		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
		RoundEntity thirdShip = facade.createShip(60,100,-25,0,10,Math.PI,10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);thirdShip.placeInSpace(world);
		assertTrue(2==thirdShip.getTimeToHitWall());
		assertEquals(thirdShip.getPositionOfHitWall(),new double[] {0,100});
		assertTrue(thirdShip.hasHitWall());
		thirdShip.getVelocityAfterEntityHitWall();
		assertEquals(thirdShip.getVelocity(),new double[] {10,0});
		assertFalse(4==firstShip.getTimeToHitWall());
	}
	
	
}