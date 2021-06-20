package gui;

import generation.CardinalDirection;
/**
 * Class: BasicRobot
 * 
 * Responsibilities: Perform move and rotate operations, obtain maze information like wall placements, utilize distance sensor
 * to measure distance from obstacles
 * 
 * Collaborators: Robot, Controller, BasicSensor, MazeContainer, Floorplan, CardinalDirection
 * 
 * @author David Montenegro
 *
 */

public class BasicRobot implements Robot {
	
	private Controller controller;
	private BasicSensor sensorNorth;
	private BasicSensor sensorSouth;
	private BasicSensor sensorEast;
	private BasicSensor sensorWest;
	private float batteryLevel;
	private int odometer;
	private boolean stopped;
	
	/**
	 * Constructor that setups initial objects.
	 */
	public BasicRobot() {
		//Set controller to null
		//Set sensors to null
		//Set battery level to 2000
		//Set odometer to 0
		//Set stopped to false
	}

	/**
	 * This method sets the controller to the parameter.
	 */
	@Override
	public void setController(Controller controller) {
		//Set the controller to the parameter
	}

	
	/**
	 * This method adds a specified distance sensor in the specified direction.
	 */
	@Override
	public void addDistanceSensor(DistanceSensor sensor, Direction mountedDirection) {
		//Switch cases for the 4 directions
		//For the given direction set correct BasicSensor to the given sensor parameter
	}

	/**
	 * This method gets the current position of the robot.
	 */
	@Override
	public int[] getCurrentPosition() throws Exception {
		//Return array using controller's current position method
		return null;
	}

	/**
	 * This method gets the current direction of the robot.
	 */
	@Override
	public CardinalDirection getCurrentDirection() {
		//Return array using controller's current direction method
		return null;
	}
	
	/**
	 * This method gets the battery level of the robot.
	 */
	@Override
	public float getBatteryLevel() {
		//Return battery level object
		return 0;
	}

	/**
	 * This method sets the battery level of the robot to the parameter.
	 */
	@Override
	public void setBatteryLevel(float level) {
		//Set the battery level to the parameter
	}

	/**
	 * This method gets the energy amount needed for a full rotation of the robot.
	 */
	@Override
	public float getEnergyForFullRotation() {
		//Return 12.0f
		return 0;
	}
	
	/**
	 * This method gets the energy amount needed for one step forward of the robot.
	 */
	@Override
	public float getEnergyForStepForward() {
		//Return 4.0f
		return 0;
	}

	/**
	 * This method gets the odometer reading.
	 */
	@Override
	public int getOdometerReading() {
		//Return odometer object
		return 0;
	}

	/**
	 * This method resets the odometer.
	 */
	@Override
	public void resetOdometer() {
		//Set odometer to 0
	}
	
	/**
	 * This method rotates the robot in the given direction.
	 */
	@Override
	public void rotate(Turn turn) {
		//Check if robot has stopped
		//Switch cases for the left, right, and around
		//For each check if robot has enough energy
		//If it has enough energy turn the robot
		//Decrease battery level
	}

	/**
	 * This method moves the robot for the given distance.
	 */
	@Override
	public void move(int distance) {
		//Check if robot has stopped
		//For each step check if robot has enough energy
		//If it has enough energy move the robot
		//Decrease battery level
		//Increase odometer
	}

	/**
	 * This method makes the robot jump over a wall.
	 */
	@Override
	public void jump() {
		//Check if robot has stopped
		//Make the robot jump
		//Increase odometer
		
	}

	/**
	 * This method checks if the robot is at the exit.
	 */
	@Override
	public boolean isAtExit() {
		//Check if robot's position is at exit.
		//Return false otherwise
		return false;
	}

	
	/**
	 * This method checks if the robot inside a room.
	 */
	@Override
	public boolean isInsideRoom() {
		//Check if robot's position is inside a room
		//Return false otherwise
		return false;
	}

	/**
	 * This method checks if the robot is out of battery or stopped by an obstacle.
	 */
	@Override
	public boolean hasStopped() {
		//Check if battery level is 0
		//Check if robot has stopped
		//Return false otherwise
		return false;
	}
	
	/**
	 * This method checks the distance of the robot to an obstacle.
	 */
	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {
		//Switch cases for the 4 directions
		//For the given direction check if a distance sensor is working
		//Use sensor's distance to obstacle method
		return 0;
	}

	/**
	 * This method checks if the robot can see the exit from its current position and direction.
	 */
	@Override
	public boolean canSeeThroughTheExitIntoEternity(Direction direction) throws UnsupportedOperationException {
		//Check if distance to obstacle is max int value
		//Return false otherwise
		return false;
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void startFailureAndRepairProcess(Direction direction, int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		//Throw new UnsupportedOperationException
		
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void stopFailureAndRepairProcess(Direction direction) throws UnsupportedOperationException {
		//Throw new UnsupportedOperationException
		
	}
	
}
