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
		RoundEntity entity = facade.createBullet(400, 20, -7, 12, 5);
		World world = facade.createWorld(1000, 800);
		assertTrue(world.fitBoundary(entity));
		world.addEntity(entity);
		assertEquals(1,facade.getWorldBullets(world).size());
		assertEquals(entity, world.getEntityAt((double)400, (double)20));
		world.deleteEntity(entity);
		assertEquals(0,facade.getWorldBullets(world).size());
		assertEquals(0,facade.getEntities(world).size() );
	}
	
	@Test
	public void testShip() throws ModelException {
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(100, 100, -10, 20, 10, Math.PI, 10);
		RoundEntity secondShip = facade.createShip(200, 100, -5, 20, 15, Math.PI/3, 20);
		RoundEntity thirdShip = facade.createShip(100, 200, -10, 0, 20, Math.PI/4, 15);
		assertEquals(3, facade.getWorldShips(world).size());
		assertEquals(0,facade.getWorldBullets(world));
		RoundEntity firstBullet = facade.createBullet(1000, 800, 100, 200, 7);
		assertEquals(1,facade.getWorldBullets(world));
		assertEquals(world,facade.getBulletWorld((Bullet) firstBullet));
	}
	
	
	
}