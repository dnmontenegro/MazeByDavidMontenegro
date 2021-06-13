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
	private MazeContainer mazeConfig;
	
	@Before
	public void setUp() {
		// Setup MazeFactory
		// Setup StubOrder
		// Setup StubOrder2
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, true);
		stubOrder2 = new StubOrder(13, 0, Order.Builder.Prim, true);
	}
	
	@After
	public void tearDown() throws Exception {
		// Test exit
	}
	
	@Test
	public final void testMazeFactory() {
		// Check if objects are not null
		assertNotNull(mazeFactory);
		assertNotNull(stubOrder);
		assertNotNull(stubOrder2);
	}
	
	@Test
	public final void testMazeFactorOrder() {
		// Check if method returns true with valid input
		// Check if method returns false when one order is currently being processed
		assertTrue(mazeFactory.order(stubOrder));
		mazeFactory.order(stubOrder);
		assertFalse(mazeFactory.order(stubOrder2));
	}
	
	
	@Test
	public final void testMazeOneExit() {
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
	public final void testMazeExitReachable() {
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
	public final void testPerfectMaze() {
		// Order maze from the MazeFactory
		// Wait until the order is done
		// Get maze from the StubOrder
		// Keep track if maze is perfect
		// Get distance from exit object of maze
		// Loop through with maze width and height 
		// If distance to the exit is 0 then maze is perfect is false
		// Check if maze is perfect is true
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		boolean isPerfect = true;
		Distance distance = mazeConfig.getMazedists();
		for (int i = 0; i < mazeConfig.getWidth(); i++) {
			for (int j = 0; j < mazeConfig.getHeight(); j++) {
				if (distance.getDistanceValue(i, j) == 0)
					isPerfect = false;
			}
		}
		assertTrue(isPerfect);
	}
	
	
	
}
