package gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.event.KeyListener;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import generation.MazeContainer;
import generation.MazeFactory;
import generation.Order;
import generation.StubOrder;
import gui.Robot.Direction;

/**
 * Class: WallFollowTestTest
 * 
 * Responsibilities: Test the wall follower algorithm
 * 
 * Collaborators: WallFollower
 * 
 * @author David Montenegro
 *
 */

public class WallFollowerTest {

	private WallFollower wallFollower;
	private MazeFactory mazeFactory;
	private StubOrder stubOrder;
	private MazeContainer mazeConfig0;
	private MazeContainer mazeConfig1;
	private MazeContainer mazeConfig2;
	private MazeContainer mazeConfig3;
	private MazeContainer mazeConfig4;
	private Controller controller;
	private BasicRobot basicRobot;
	
	/**
	 * This method setups the fields.
	 */
	@Before
	public void setUp() {
		mazeFactory = new MazeFactory();
		stubOrder = new StubOrder(13, 0, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig0 = (MazeContainer)stubOrder.getMaze();
		
		stubOrder = new StubOrder(13, 1, Order.Builder.DFS, true);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig1 = (MazeContainer)stubOrder.getMaze();
		
		stubOrder = new StubOrder(13, 2, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig2 = (MazeContainer)stubOrder.getMaze();
		
		stubOrder = new StubOrder(13, 3, Order.Builder.DFS, true);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig3 = (MazeContainer)stubOrder.getMaze();
		
		stubOrder = new StubOrder(14, 4, Order.Builder.DFS, false);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig4 = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		basicRobot = new BasicRobot();
		wallFollower = new WallFollower();
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
	public void testWallFollower() {
		assertNotNull(mazeFactory);
		assertNotNull(stubOrder);
		assertNotNull(mazeConfig0);
		assertNotNull(mazeConfig1);
		assertNotNull(mazeConfig2);
		assertNotNull(mazeConfig3);
		assertNotNull(mazeConfig4);
		assertNotNull(controller);
		assertNotNull(basicRobot);
		assertNotNull(wallFollower);
	}
	
	/**
	 * This method checks if the drive2Exit() method works on maze of size 3 without rooms.
	 * Correct behavior: 
	 */
	@Test
	public void testDrive2Exit3() {
		controller.switchFromGeneratingToPlaying(mazeConfig3);
		basicRobot.setController(controller);
		wallFollower.setRobot(basicRobot);
		wallFollower.setMaze(mazeConfig3);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			if(wallFollower.drive2Exit())
				assertTrue(basicRobot.canSeeThroughTheExitIntoEternity(Direction.FORWARD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the drive2Exit() method works on maze of size 4 with rooms.
	 * Correct behavior: 
	 */
	@Test
	public void testDrive2Exit4() {
		controller.switchFromGeneratingToPlaying(mazeConfig4);
		basicRobot.setController(controller);
		wallFollower.setRobot(basicRobot);
		wallFollower.setMaze(mazeConfig4);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			if(wallFollower.drive2Exit())
				assertTrue(basicRobot.canSeeThroughTheExitIntoEternity(Direction.FORWARD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the drive1Step2Exit() method works.
	 * Correct behavior: 
	 */
	@Test
	public void testDrive1Step2Exit0() {
		controller.switchFromGeneratingToPlaying(mazeConfig0);
		basicRobot.setController(controller);
		wallFollower.setRobot(basicRobot);
		wallFollower.setMaze(mazeConfig0);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			assertFalse(wallFollower.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wallFollower.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wallFollower.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the getEnergyConsumption() method works on maze of size 0.
	 * Correct behavior: energy used is greater than 0 if the getEnergyConsumption() method and by extension
	 * the drive2Exit() method are working
	 */
	@Test
	public void testGetEnergyConsumption0() {
		controller.switchFromGeneratingToPlaying(mazeConfig0);
		basicRobot.setController(controller);
		wallFollower.setRobot(basicRobot);
		wallFollower.setMaze(mazeConfig0);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			if(wallFollower.drive2Exit())
				assertTrue(wallFollower.getEnergyConsumption() > 0);
			System.out.print(wallFollower.getEnergyConsumption());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the GetPathLength() method works on maze of size 0.
	 * Correct behavior: number of cells is greater than 0 if the getPathLength() method and by extension
	 * the drive2Exit() method are working
	 */
	@Test
	public void testGetPathLength0() {
		controller.switchFromGeneratingToPlaying(mazeConfig0);
		basicRobot.setController(controller);
		wallFollower.setRobot(basicRobot);
		wallFollower.setMaze(mazeConfig0);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			if(wallFollower.drive2Exit())
				assertTrue(wallFollower.getPathLength() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the GetPathLength() method works on maze of size 1 without rooms.
	 * Correct behavior: number of cells is greater than 0 if the getPathLength() method and by extension
	 * the drive2Exit() method are working
	 */
	@Test
	public void testGetPathLength1() {
		controller.switchFromGeneratingToPlaying(mazeConfig1);
		basicRobot.setController(controller);
		wallFollower.setRobot(basicRobot);
		wallFollower.setMaze(mazeConfig1);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			if(wallFollower.drive2Exit())
				assertTrue(wallFollower.getPathLength() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the GetPathLength() method works on maze of size 2 with rooms.
	 * Correct behavior: number of cells is greater than 0 if the getPathLength() method and by extension
	 * the drive2Exit() method are working
	 */
	@Test
	public void testGetPathLength2() {
		controller.switchFromGeneratingToPlaying(mazeConfig2);
		basicRobot.setController(controller);
		wallFollower.setRobot(basicRobot);
		wallFollower.setMaze(mazeConfig2);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			if(wallFollower.drive2Exit())
				assertTrue(wallFollower.getPathLength() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
