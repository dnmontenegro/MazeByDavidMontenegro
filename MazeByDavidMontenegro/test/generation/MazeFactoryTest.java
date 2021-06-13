package generation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MazeFactoryTest {
	
	private MazeFactory mazeFactory;
	private StubOrder stubOrder;
	private StubOrder stubOrder2;
	private StubOrder stubOrder3;
	private MazeContainer mazeConfig;
	private MazeContainer mazeConfig2;
	
	@Before
	public void setUp() {
		// Setup MazeFactory
		// Setup StubOrder
		// Setup StubOrder2
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, true);
		stubOrder2 = new StubOrder(13, 1, Order.Builder.Prim, false);
		stubOrder3 = new StubOrder(13, 0, Order.Builder.Eller, true);
	}
	
	@After
	public void tearDown() throws Exception {
		// Test exit
	}
	
	@Test
	public void testMazeFactory() {
		// Check if objects are not null
		assertNotNull(mazeFactory);
		assertNotNull(stubOrder);
		assertNotNull(stubOrder2);
	}
	
	@Test
	public void testMazeFactoryOrder() {
		// Check if method returns true with DFS input
		// Check if method returns false when one order is currently being processed
		// Check if method returns true with Prim input
		// Check if method returns false when algorithm is not covered
		assertTrue(mazeFactory.order(stubOrder));
		mazeFactory.waitTillDelivered();
		mazeFactory.order(stubOrder);
		assertFalse(mazeFactory.order(stubOrder2));
		mazeFactory.waitTillDelivered();
		assertTrue(mazeFactory.order(stubOrder2));
		mazeFactory.waitTillDelivered();
		assertFalse(mazeFactory.order(stubOrder3));
	}
	
	@Test
	public void testMazeFactoryCancel() {
		// Check if calling method with no thread running does not interrupt future operations
		// Check if calling method works when an order is being processed
		mazeFactory.cancel();
		assertTrue(mazeFactory.order(stubOrder));
		mazeFactory.waitTillDelivered();
		mazeFactory.order(stubOrder2);
		mazeFactory.cancel();
		
	}
	
	@Test
	public void testMazeFactoryWaitTillDelivered() {
		// Check if calling method with no thread running does not interrupt future operations
		mazeFactory.waitTillDelivered();
		assertTrue(mazeFactory.order(stubOrder));
	}
	
	
	@Test
	public void testMazeOneExit() {
		// Order maze from the MazeFactory
		// Wait until the order is done
		// Get maze from the StubOrder
		// Keep track of number of exits
		// Get distance from exit object of maze
		// Loop through with maze width and height 
		// If distance to the exit is 1 then increase number of exits
		// Check if number of exits is 1
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		int numberOfExits = 0;
		Distance distance = mazeConfig.getMazedists();
		for (int i = 0; i < mazeConfig.getWidth(); i++) {
			for (int j = 0; j < mazeConfig.getHeight(); j++) {
				if (distance.getDistanceValue(i, j) == 1)
					numberOfExits++;
			}
		}
		assertEquals(1, numberOfExits);
	}
	
	@Test
	public void testMazeExitReachable() {
		// Order maze from the MazeFactory
		// Wait until the order is done
		// Get maze from the StubOrder
		// Get distance from exit object of maze
		// Loop through with maze width and height 
		// Check if distance to the exit is always greater than 0
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		Distance distance = mazeConfig.getMazedists();
		for (int i = 0; i < mazeConfig.getWidth(); i++) {
			for (int j = 0; j < mazeConfig.getHeight(); j++) {
				assertTrue(distance.getDistanceValue(i, j) > 0);
			}
		}
	}
	
	@Test
	public void testPerfectMaze() {
		// Order maze from the MazeFactory
		// Wait until the order is done
		// Get maze from the StubOrder
		// Keep track if maze is perfect
		// Get floorplan object of maze
		// Loop through with maze width and height 
		// If any spot has no walls then maze is perfect is false
		// Check if maze is perfect is true
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		boolean isPerfect = true;
		Floorplan floorplan = mazeConfig.getFloorplan();
		for (int i = 0; i < mazeConfig.getWidth(); i++) {
			for (int j = 0; j < mazeConfig.getHeight(); j++) {
				if (floorplan.isInRoom(i, j))
					isPerfect = false;
				else
					continue;
			}
		}
		assertTrue(isPerfect);
		mazeFactory.order(stubOrder2);
		mazeFactory.waitTillDelivered();
		mazeConfig2 = (MazeContainer)stubOrder2.getMaze();
		boolean isPerfect2 = true;
		Floorplan floorplan2 = mazeConfig2.getFloorplan();
		for (int i = 0; i < mazeConfig2.getWidth(); i++) {
			for (int j = 0; j < mazeConfig2.getHeight(); j++) {
				if (floorplan2.isInRoom(i, j))
					isPerfect2 = false;
				else
					continue;
			}
		}
		assertFalse(isPerfect2);
	}
	
	
	
}
