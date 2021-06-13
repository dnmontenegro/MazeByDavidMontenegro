package generation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MazeBuilderKruskalTest extends MazeFactoryTest {

	private MazeFactory mazeFactory;
	private StubOrder stubOrder;
	private StubOrder stubOrder2;
	private StubOrder stubOrder3;
	private MazeContainer mazeConfig;
	private MazeContainer mazeConfig2;
	
	
	/**
	 * This method setups the MazeFactory and StubOrder objects.
	 */
	@Before
	public void setUp() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.Kruskal, true);
		stubOrder2 = new StubOrder(13, 1, Order.Builder.Kruskal, false);
		stubOrder3 = new StubOrder(13, 0, Order.Builder.Eller, true);
	}
	
	/**
	 * This method occurs upon test exit.
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * This method checks if fields are not null.
	 * Correct behavior: Fields are not null.
	 */
	@Test
	public void testMazeFactory() {
		assertNotNull(mazeFactory);
		assertNotNull(stubOrder);
		assertNotNull(stubOrder2);
	}
	
	/**
	 * This method checks if MazeFactory's order() method returns true with DFS input. It checks if order() returns false 
	 * when one order is currently being processed. It checks if order() returns true with Prim input. It also checks if 
	 * order() returns false when the algorithm input is not covered.
	 * Correct behavior: Returns true for DFS input, returns false when order is currently being processed, returns true
	 * for Prim input, returns false when algorithm input is not covered.
	 */
	@Test
	public void testMazeFactoryOrder() {

		assertTrue(mazeFactory.order(stubOrder));
		mazeFactory.waitTillDelivered();
		mazeFactory.order(stubOrder);
		assertFalse(mazeFactory.order(stubOrder2));
		mazeFactory.waitTillDelivered();
		assertTrue(mazeFactory.order(stubOrder2));
		mazeFactory.waitTillDelivered();
		assertFalse(mazeFactory.order(stubOrder3));
	}
	
	/**
	 * This method checks if calling MazeFactory's cancel() method with no thread running does not interrupt future operations. 
	 * It checks if calling cancel() works when an order is being processed.
	 * Correct behavior: After unnecessary use of cancel() an order can successfully be processed and cancel() properly cancels
	 * an order.
	 */
	@Test
	public void testMazeFactoryCancel() {
		mazeFactory.cancel();
		assertTrue(mazeFactory.order(stubOrder));
		mazeFactory.waitTillDelivered();
		mazeFactory.order(stubOrder2);
		mazeFactory.cancel();
		
	}
	
	/**
	 * This method checks if calling MazeFactory's waitTillDelivered() method with no thread running does not interrupt 
	 * future operations.
	 * Correct behavior: After unnecessary use of waitTillDelivered() an order can successfully be processed.
	 */
	@Test
	public void testMazeFactoryWaitTillDelivered() {
		mazeFactory.waitTillDelivered();
		assertTrue(mazeFactory.order(stubOrder));
	}
	
	/**
	 * This method checks if the maze has only one exit. After getting the maze and the maze distance, the method
	 *  loops through the maze checking if the distance to the exit of any slot is one meaning it is right at the exit.
	 *  Correct behavior: Number of exits is equal to one.
	 */
	@Test
	public void testMazeOneExit() {
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
	
	/**
	 * This method checks if the maze's exit is reachable from any slot in the maze. After getting the maze and the maze distance, 
	 * the method loops through the maze checking if the distance to the exit of every slot is greater than zero.
	 *  Correct behavior: Distance to exit of all slots is greater than zero.
	 */
	@Test
	public void testMazeExitReachable() {
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
	
	/**
	 * This method checks if the maze is a perfect maze. After getting the maze and the maze floorplan, the method loops through 
	 * the maze checking if any slot in the maze is in a room.
	 *  Correct behavior: The maze with no rooms should be true while the maze with rooms should be false.
	 */
	@Test
	public void testPerfectMaze() {
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
