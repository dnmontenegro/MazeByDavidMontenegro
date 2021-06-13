package generation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MazeFactoryTest {
	
	private MazeFactory mazeFactory;
	private StubOrder stubOrder;
	private MazeContainer mazeConfig;
	
	@Before
	public void setUp() {
		// Setup MazeFactory
		// Setup StubOrder
		// Order maze from the MazeFactory
		// Wait until the order is done
		// Get maze from the StubOrder
	}
	
	@After
	public void tearDown() throws Exception {
		// Test exit
	}
	
	@Test
	public final void testMazeFactory() {
		// Check if objects are not null
	}
	
	@Test
	public final void testMazeFactorOrder() {
		// Check if method returns true with valid input
		// Check if method returns false when one order is currently being processed
	}
	
	@Test
	public final void testMazeFactoryCancel() {
		// Check if objects are set to null
	}
	
	@Test
	public final void testMazeFactoryWaitTillDelivered() {
		// Check if objects are set to null
	}
	
	@Test
	public final void testMazeOneExit() {
		// Keep track of number of exits
		// Get distance from exit object of maze
		// Loop through with maze width and height 
		// If distance to the exit is 1 then increase number of exits
		// Check if number of exits is 1
	}
	
	@Test
	public final void testMazeExitReachable() {
		// Get distance from exit object of maze
		// Loop through with maze width and height 
		// Check if distance to the exit is always greater than 0
	}
	
	@Test
	public final void testPerfectMaze() {
		// Keep track if maze is perfect
		// Get distance from exit object of maze
		// Loop through with maze width and height 
		// If distance to the exit is 0 then maze is perfect is false
		// Check if maze is perfect is true
	}
	
	
	
}
