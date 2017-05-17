package asteroids.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		RoundEntity ship = new Ship(10,10,10,10,10,Math.PI, 10);
		RoundEntity bullet = new Bullet(10,10,10,10,10);
		assertTrue(ship.getCertainEntities(world).isEmpty());
		assertTrue(bullet.getCertainEntities(world).isEmpty());
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
		assertEquals(1,entity.getCertainEntities(world).size());
		assertEquals(entity, world.getEntityAt((double)400, (double)20));
		UnboundSpace unboundspace = new UnboundSpace();
		entity.placeInSpace(unboundspace);
		assertEquals(0,entity.getCertainEntities(world).size());
		assertEquals(0,facade.getEntities(world).size() );
	}
	
	@Test 
	public void testGetEntityToHitWall() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 10);
		RoundEntity secondShip = facade.createShip(230, 100, -50, 0, 10, Math.PI/3, 10);
		RoundEntity thirdShip = facade.createShip(60,100,-25,0,10,Math.PI,10);
		firstShip.placeInSpace(world);
		secondShip.placeInSpace(world);
		thirdShip.placeInSpace(world);
		assertFalse(thirdShip.isTerminated());
		assertEquals(thirdShip.getTimeToHitWall(),2,EPSILON);
		assertEquals(thirdShip.getPositionOfHitWall()[0],0,EPSILON);
		assertEquals(thirdShip.getPositionOfHitWall()[1],100,EPSILON);
		assertTrue(thirdShip.hasHitWall());
		world.evolve(3, null);
		assertEquals(thirdShip.getVelocity()[0],25,EPSILON);
		assertEquals(thirdShip.getVelocity()[1],0,EPSILON);
		assertEquals(secondShip.getVelocity()[0],-10,EPSILON);
	}
	
	@Test public void testGetTimeNextCollision() throws ModelException {
		World world1 = facade.createWorld(5000, 5000);
		Ship firstShip = facade.createShip(100, 120, 30, 0, 50, Math.PI, 1.1E18);
		Ship secondShip = facade.createShip(300, 120, 10, 0, 50, Math.PI, 1.1E18);
		facade.addShipToWorld(world1, firstShip);
		facade.addShipToWorld(world1, secondShip);
		assertTrue(firstShip.inSameSpace(secondShip));
		assertFalse(firstShip.overlap(secondShip));
		assertTrue(firstShip.canAsCollision(secondShip));
		assertEquals(world1.getTimeNextCollision(),5,EPSILON);
	}
	
	@Test
	public void testGetTimeAndPlaceToNextCollision() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 15);
		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
		firstShip.placeInSpace(world);secondShip.placeInSpace(world);
		assertEquals(firstShip.getSpace(),secondShip.getSpace());
		assertEquals(world.getTimeNextCollision(),5.0/2.0,EPSILON);
		assertTrue(world.getPositionNextCollision()[0] == 75);
		assertTrue(world.getPositionNextCollision()[1] == 100);	
	}
	
	@Test 
	public void testEvolveWorld() throws ModelException{
		World world = facade.createWorld(8000, 8000);
		RoundEntity firstShip = facade.createShip(90, 100, -10, 0, 10, Math.PI, 10);
		RoundEntity secondShip = facade.createShip(210, 100, -50, 0, 10, Math.PI/3, 10);
		RoundEntity thirdShip = facade.createShip(60,100,-25,0,10,Math.PI,10);
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
	public void testAddBulletsAndShipsWorld() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship firstShip = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
		Ship secondShip = facade.createShip(200, 120, 10, 0, 50, Math.PI, 1.1E18);
		Ship thirdShip = facade.createShip(4950, 120, 10, 0, 200, Math.PI, 1.1E18);
		Bullet bullet = facade.createBullet(100, 200, -1, 2, 2);
		facade.addShipToWorld(world, firstShip);
		facade.addShipToWorld(world, secondShip);
		assertFalse(world.fitBoundary(thirdShip));
		assertEquals(firstShip.getSpace(),world);
		List<RoundEntity> entity = new ArrayList<>(Arrays.asList(firstShip, secondShip));
		assertEquals(world.getEntities(),entity);
		facade.addBulletToWorld(world, bullet);	
	}
	
}
