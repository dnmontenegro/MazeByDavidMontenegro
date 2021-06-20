package gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import generation.MazeContainer;
import generation.MazeFactory;
import generation.Order;
import generation.StubOrder;
import gui.Robot.Direction;

/**
 * Class: WizardTest
 * 
 * Responsibilities: Test the wizard algorithm
 * 
 * Collaborators: Wizard
 * 
 * @author David Montenegro
 *
 */

public class WizardTest {

	private Wizard wizard;
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
		
		stubOrder = new StubOrder(13, 4, Order.Builder.DFS, true);
		mazeFactory.order(stubOrder);
		mazeFactory.waitTillDelivered();
		mazeConfig4 = (MazeContainer)stubOrder.getMaze();
		controller = new Controller();
		basicRobot = new BasicRobot();
		wizard = new Wizard();
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
	public void testWizard() {
		assertNotNull(mazeFactory);
		assertNotNull(stubOrder);
		assertNotNull(mazeConfig0);
		assertNotNull(mazeConfig1);
		assertNotNull(mazeConfig2);
		assertNotNull(mazeConfig3);
		assertNotNull(mazeConfig4);
		assertNotNull(controller);
		assertNotNull(basicRobot);
		assertNotNull(wizard);
	}
	
	/**
	 * This method checks if the drive2Exit() method works on maze of size 3 without rooms.
	 * Correct behavior: true if robot can see the exit
	 */
	@Test
	public void testDrive2Exit3() {
		controller.switchFromGeneratingToPlaying(mazeConfig3);
		basicRobot.setController(controller);
		wizard.setRobot(basicRobot);
		wizard.setMaze(mazeConfig3);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			wizard.drive2Exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(basicRobot.canSeeThroughTheExitIntoEternity(Direction.FORWARD));
	}
	
	/**
	 * This method checks if the drive2Exit() method works on maze of size 4 with rooms.
	 * Correct behavior: true if robot can see the exit
	 */
	@Test
	public void testDrive2Exit4() {
		controller.switchFromGeneratingToPlaying(mazeConfig4);
		basicRobot.setController(controller);
		wizard.setRobot(basicRobot);
		wizard.setMaze(mazeConfig4);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			wizard.drive2Exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(basicRobot.canSeeThroughTheExitIntoEternity(Direction.FORWARD));
	}
	
	/**
	 * This method checks if the drive1Step2Exit() method works.
	 * Correct behavior: true when robot moves 1 step toward the exit
	 */
	@Test
	public void testDrive1Step2Exit0() {
		controller.switchFromGeneratingToPlaying(mazeConfig0);
		basicRobot.setController(controller);
		wizard.setRobot(basicRobot);
		wizard.setMaze(mazeConfig0);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			assertTrue(wizard.drive1Step2Exit());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks if the drive1Step2Exit() method works.
	 * Correct behavior: false when at exit
	 */
	@Test
	public void testDrive1Step2Exit1() {
		controller.switchFromGeneratingToPlaying(mazeConfig1);
		basicRobot.setController(controller);
		wizard.setRobot(basicRobot);
		wizard.setMaze(mazeConfig1);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		while(!basicRobot.isAtExit()) {
			try {
				wizard.drive1Step2Exit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			assertFalse(wizard.drive1Step2Exit());
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
		wizard.setRobot(basicRobot);
		wizard.setMaze(mazeConfig0);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			wizard.drive2Exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(wizard.getEnergyConsumption() > 0);
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
		wizard.setRobot(basicRobot);
		wizard.setMaze(mazeConfig0);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			wizard.drive2Exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(wizard.getPathLength() > 0);
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
		wizard.setRobot(basicRobot);
		wizard.setMaze(mazeConfig1);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			wizard.drive2Exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(wizard.getPathLength() > 0);
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
		wizard.setRobot(basicRobot);
		wizard.setMaze(mazeConfig2);
		BasicSensor sensorForward = new BasicSensor();
		basicRobot.addDistanceSensor(sensorForward, Direction.FORWARD);
		BasicSensor sensorLeft = new BasicSensor();
		basicRobot.addDistanceSensor(sensorLeft, Direction.LEFT);
		try {
			wizard.drive2Exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(wizard.getPathLength() > 0);
	}
}

