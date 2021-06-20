package gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.After;
import org.junit.Test;

import generation.CardinalDirection;
import generation.MazeContainer;
import generation.MazeFactory;
import generation.Order;
import generation.StubOrder;
import gui.Robot.Direction;
import gui.Robot.Turn;

/**
 * Class: BasicRobotTest
 * 
 * Responsibilities: Test the different robot methods
 * 
 * Collaborators: BasicRobot
 * 
 * @author David Montenegro
 *
 */

public class BasicRobotTest {
	
	
	private MazeFactory mazeFactory;
	private StubOrder stubOrder;
	private MazeContainer mazeConfig;
	private Controller controller;
	private BasicRobot basicRobot;
	
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
	public void testBasicRobot() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		assertNotNull(mazeFactory);
		assertNotNull(stubOrder);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		assertNotNull(mazeConfig);
		controller = new Controller();
		assertNotNull(controller);
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		assertNotNull(basicRobot);
	}
	
	/**
	 * This method checks if the setController() method works.
	 * Correct behavior: basicRobot can get current direction
	 */
	@Test
	public void testSetController() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		assertTrue(basicRobot.getCurrentDirection() != null);
	}
	
	/**
	 * This method checks if the addDistanceSensor() method works.
	 * Correct behavior: basicRobot can detect distance from an obstacle
	 */
	@Test
	public void testAddDistanceSensor() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		assertTrue(basicRobot.distanceToObstacle(Direction.FORWARD) != -1);
	}
	
	/**
	 * This method checks if the getCurrentPosition() method works.
	 * Correct behavior: basicRobot can get current position
	 */
	@Test
	public void testGetCurrentPosition() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		try {
			assertEquals(1, basicRobot.getCurrentPosition()[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertEquals(1, basicRobot.getCurrentPosition()[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the getCurrentDirection() method works.
	 * Correct behavior: basicRobot can get current direction
	 */
	@Test
	public void testGetCurrentDirection() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		CardinalDirection direction = basicRobot.getCurrentDirection();
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		assertEquals(direction, basicRobot.getCurrentDirection());
	}
	
	/**
	 * This method checks if the getBatteryLevel() method works.
	 * Correct behavior: battery level decreases by 4 after one move
	 */
	@Test
	public void testGetBatteryLevel() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		assertFalse(basicRobot.hasStopped());
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		assertEquals(1996.0f, basicRobot.getBatteryLevel());
	}
	
	/**
	 * This method checks if the setBatteryLevel() method works.
	 * Correct behavior: robot stops after making one move as battery has been set to 4
	 */
	@Test
	public void testSetBatteryLevel() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		assertFalse(basicRobot.hasStopped());
		basicRobot.setBatteryLevel(4);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		assertTrue(basicRobot.hasStopped());
	}
	
	/**
	 * This method checks if the getEnergyForFullRotation() method works.
	 * Correct behavior: getEnergyForFullRotation() returns 12.0f
	 */
	@Test
	public void testGetEnergyForFullRotation() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		assertEquals(12.0f, basicRobot.getEnergyForFullRotation());
	}
	
	/**
	 * This method checks if the getEnergyForStepForward() method works.
	 * Correct behavior: getEnergyForStepForward() returns 4.0f
	 */
	@Test
	public void testGetEnergyForStepForward() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		assertEquals(4.0f, basicRobot.getEnergyForStepForward());
	}
	
	/**
	 * This method checks if the getOdometerReading method works.
	 * Correct behavior: getOdometerReading returns the correct number of moves
	 */
	@Test
	public void testGetOdometerReading() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		assertEquals(1, basicRobot.getOdometerReading());
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		assertEquals(2, basicRobot.getOdometerReading());
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(2);
		assertEquals(4, basicRobot.getOdometerReading());
	}
	
	/**
	 * This method checks if the resetOdometer method works.
	 * Correct behavior: resetOdometer results in an odometer reading of 0
	 */
	@Test
	public void testResetOdometer() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		assertEquals(1, basicRobot.getOdometerReading());
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		assertEquals(2, basicRobot.getOdometerReading());
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(2);
		assertEquals(4, basicRobot.getOdometerReading());
		basicRobot.resetOdometer();
		assertEquals(0, basicRobot.getOdometerReading());
	}
	
	/**
	 * This method checks if the rotate() method works.
	 * Correct behavior: rotate() method turns the direction of the robot
	 */
	@Test
	public void testRotate() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		CardinalDirection direction = basicRobot.getCurrentDirection();
		basicRobot.rotate(Turn.RIGHT);
		assertNotEquals(direction, basicRobot.getCurrentDirection());
	}
	
	/**
	 * This method checks if the move() method works.
	 * Correct behavior: move() method goes one step
	 */
	@Test
	public void testMove() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		int x = 0;
		int y = 0;
		try {
			x = basicRobot.getCurrentPosition()[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			y = basicRobot.getCurrentPosition()[1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		try {
			assertEquals(x, basicRobot.getCurrentPosition()[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertEquals(y-1, basicRobot.getCurrentPosition()[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the jump() method works.
	 * Correct behavior: jump() method goes over wall
	 */
	@Test
	public void testJump() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		int x = 0;
		int y = 0;
		try {
			x = basicRobot.getCurrentPosition()[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			y = basicRobot.getCurrentPosition()[1];
		} catch (Exception e) {
			e.printStackTrace();
		}
		basicRobot.jump();
		try {
			assertEquals(x + 1, basicRobot.getCurrentPosition()[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertEquals(y, basicRobot.getCurrentPosition()[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the isAtExit() method works.
	 * Correct behavior: isAtExit() returns true
	 */
	@Test
	public void testIsAtExit() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(2);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(3);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(3);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		assertTrue(basicRobot.isAtExit());
	}
	
	/**
	 * This method checks if the isInsideRoom() method works.
	 * Correct behavior: isInsideRoom() returns false
	 */
	@Test
	public void testIsInsideRoom() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		assertFalse(basicRobot.isInsideRoom());
	}
	
	/**
	 * This method checks if the hasStopped() method works.
	 * Correct behavior: false for default robot, true when robot has a battery level of 0, 
	 * true after robot has been stopped already, true when robot is stopped by trying to move into a wall
	 */
	@Test
	public void testHasStopped() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		assertFalse(basicRobot.hasStopped());
		basicRobot.setBatteryLevel(0);
		assertTrue(basicRobot.hasStopped());
		basicRobot.setBatteryLevel(2000);
		assertTrue(basicRobot.hasStopped());
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		basicRobot.move(1);
		assertTrue(basicRobot.hasStopped());
	}
	
	/**
	 * This method checks if the distanceToObstacle() method works.
	 * Correct behavior: canSeeThroughTheExitIntoEternity returns true
	 */
	@Test
	public void testDistanceToObstacle() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorBackward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorBackward, Direction.BACKWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		BasicSensor sensorRight = new BasicSensor();
		basicRobot.addDistanceSensor(sensorRight, Direction.RIGHT);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(2);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.RIGHT);
		assertEquals(3, basicRobot.distanceToObstacle(Direction.FORWARD));
		assertEquals(0, basicRobot.distanceToObstacle(Direction.BACKWARD));
		assertEquals(0, basicRobot.distanceToObstacle(Direction.LEFT));
		assertEquals(1, basicRobot.distanceToObstacle(Direction.RIGHT));
	}
	
	/**
	 * This method checks if the canSeeThroughTheExitIntoEternity() method works.
	 * Correct behavior: canSeeThroughTheExitIntoEternity returns true
	 */
	@Test
	public void testCanSeeThroughTheExitIntoEternity() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		controller.switchFromGeneratingToPlaying(mazeConfig);
		basicRobot = new BasicRobot();
		basicRobot.setController(controller);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(2);
		basicRobot.rotate(Turn.LEFT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(3);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(3);
		basicRobot.rotate(Turn.RIGHT);
		basicRobot.move(1);
		basicRobot.rotate(Turn.LEFT);
		assertTrue(basicRobot.canSeeThroughTheExitIntoEternity(Direction.FORWARD));
	}
	
	/**
	 * This method checks if the startFailureAndRepairProcess() method works.
	 * Correct behavior: startFailureAndRepairProcess() throws exception
	 */
	@Test
	public void testStartFailureAndRepairProcess() {
		basicRobot = new BasicRobot();
		assertThrows(UnsupportedOperationException.class, ()-> basicRobot.startFailureAndRepairProcess(Direction.FORWARD, 0, 0));
	}
	
	/**
	 * This method checks if the stopFailureAndRepairProcess() method works.
	 * Correct behavior: stopFailureAndRepairProcess() throws exception
	 */
	@Test
	public void testStopFailureAndRepairProcess() {
		basicRobot = new BasicRobot();
		assertThrows(UnsupportedOperationException.class, ()-> basicRobot.stopFailureAndRepairProcess(Direction.FORWARD));
	}
	

}
