package gui;

import generation.CardinalDirection;
import gui.Constants.UserInput;
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
	private BasicSensor sensorLeft;
	private BasicSensor sensorRight;
	private BasicSensor sensorForward;
	private BasicSensor sensorBackward;
	private float[] batteryLevel;
	private int odometer;
	private boolean stopped;
	
	/**
	 * Constructor that setups initial objects.
	 */
	public BasicRobot() {
		controller = null;
		sensorLeft = null;
		sensorRight = null;
		sensorForward = null;
		sensorBackward = null;
		batteryLevel = new float[1];
		batteryLevel[0] = 2000.0f;
		odometer = 0;
		stopped = false;
	}

	/**
	 * This method sets the controller to the parameter.
	 * @param a controller
	 */
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * This method adds a specified distance sensor in the specified direction.
	 * @param a sensor and the direction to mount the sensor
	 */
	@Override
	public void addDistanceSensor(DistanceSensor sensor, Direction mountedDirection) {
		switch(mountedDirection) {
			case LEFT:
				sensorLeft = (BasicSensor) sensor;
				sensorLeft.setSensorDirection(mountedDirection);
				sensorLeft.setMaze(controller.getMazeConfiguration());
				break;
			case RIGHT:
				sensorRight = (BasicSensor) sensor;
				sensorRight.setSensorDirection(mountedDirection);
				sensorRight.setMaze(controller.getMazeConfiguration());
				break;
			case FORWARD:
				sensorForward = (BasicSensor) sensor;
				sensorForward.setSensorDirection(mountedDirection);
				sensorForward.setMaze(controller.getMazeConfiguration());
				break;
			case BACKWARD:
				sensorBackward = (BasicSensor) sensor;
				sensorBackward.setSensorDirection(mountedDirection);
				sensorBackward.setMaze(controller.getMazeConfiguration());
				break;
		}
	}

	/**
	 * This method gets the current position of the robot.
	 * @return the position of the robot
	 */
	@Override
	public int[] getCurrentPosition() throws Exception {
		if(controller.getMazeConfiguration().isValidPosition(controller.getCurrentPosition()[0], controller.getCurrentPosition()[1]))
			return controller.getCurrentPosition();
		else 
			throw new Exception();
	}

	/**
	 * This method gets the current direction of the robot.
	 * @return the direction of the robot
	 */
	@Override
	public CardinalDirection getCurrentDirection() {
		return controller.getCurrentDirection();
	}
	
	/**
	 * This method gets the battery level of the robot.
	 * @return the float value of the battery level
	 */
	@Override
	public float getBatteryLevel() {
		return batteryLevel[0];
	}

	/**
	 * This method sets the battery level of the robot to the parameter.
	 */
	@Override
	public void setBatteryLevel(float level) {
		batteryLevel[0] = level;
	}

	/**
	 * This method gets the energy amount needed for a full rotation of the robot.
	 * @return the float energy amount
	 */
	@Override
	public float getEnergyForFullRotation() {
		return 12.0f;
	}
	
	/**
	 * This method gets the energy amount needed for one step forward of the robot.
	 * @return the float energy amount
	 */
	@Override
	public float getEnergyForStepForward() {
		return 4.0f;
	}

	/**
	 * This method gets the odometer reading.
	 * @return the value of the odometer
	 */
	@Override
	public int getOdometerReading() {
		return odometer;
	}

	/**
	 * This method resets the odometer.
	 */
	@Override
	public void resetOdometer() {
		odometer = 0;
	}
	
	/**
	 * This method rotates the robot in the given direction.
	 * @param the direction to turn
	 */
	@Override
	public void rotate(Turn turn) {
		if(hasStopped() == false) {
			switch(turn) {
				case LEFT:
					if(getBatteryLevel() > getEnergyForFullRotation()*(1/4)) {
						controller.keyDown(UserInput.LEFT, 0);
						setBatteryLevel(getBatteryLevel() - getEnergyForFullRotation()*(1/4));
					}
					break;
				case RIGHT:
					if(getBatteryLevel() > getEnergyForFullRotation()*(1/4)) {
						controller.keyDown(UserInput.RIGHT, 0);
						setBatteryLevel(getBatteryLevel() - getEnergyForFullRotation()*(1/4));
					}
					break;
				case AROUND:
					if(getBatteryLevel() > getEnergyForFullRotation()*(1/2)) {
						controller.keyDown(UserInput.LEFT, 0);
						controller.keyDown(UserInput.LEFT, 0);
						setBatteryLevel(getBatteryLevel() - getEnergyForFullRotation()*(1/2));
					}
					break;
			}
		}
		else 
			stopped = true;
	}

	/**
	 * This method moves the robot for the given distance.
	 * @param the distance to move
	 */
	@Override
	public void move(int distance) {
		for (int i = 0; i < distance; i++) {
			if(hasStopped() == false && getBatteryLevel() > getEnergyForStepForward()) {
				if(distanceToObstacle(Direction.FORWARD) == 0) {
					stopped = true;
					break;
				}
				controller.keyDown(UserInput.UP, 0);
				setBatteryLevel(getBatteryLevel() - getEnergyForStepForward());
				odometer++;
			}
			else {
				stopped = true;
				break;
			}
		}
	}

	/**
	 * This method makes the robot jump over a wall.
	 */
	@Override
	public void jump() {
		if(hasStopped() == false) {
			controller.keyDown(UserInput.JUMP, 0);
			odometer++;
		}
		else {
			stopped = true;
		}
	}

	/**
	 * This method checks if the robot is at the exit.
	 * @return true if at exit position, false if otherwise
	 */
	@Override
	public boolean isAtExit() {
		if(controller.getMazeConfiguration().getDistanceToExit(controller.getCurrentPosition()[0], controller.getCurrentPosition()[1]) == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * This method checks if the robot inside a room.
	 * @return true if inside a room, false if otherwise
	 */
	@Override
	public boolean isInsideRoom() {
		if(controller.getMazeConfiguration().getFloorplan().isInRoom(controller.getCurrentPosition()[0], controller.getCurrentPosition()[1]) == true)
			return true;
		else
			return false;
	}

	/**
	 * This method checks if the robot is out of battery or stopped by an obstacle.
	 * @return true if stopped, false if otherwise
	 */
	@Override
	public boolean hasStopped() {
		if(getBatteryLevel() == 0) {
			stopped = true;
			return true;
		}
		else if(stopped == true)
			return true;
		else
			return false;
	}
	
	/**
	 * This method checks the distance of the robot to an obstacle.
	 * @param the direction to check
	 * @return the int value of the distance of the obstacle from the robot
	 */
	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {
		CardinalDirection sensorDirection = getCurrentDirection();
		int distance = -1;
		switch(direction) {
			case LEFT:
				try {
					distance = sensorLeft.distanceToObstacle(getCurrentPosition(), sensorDirection.rotateClockwise(), batteryLevel);
				} catch (Exception e) {
					throw new UnsupportedOperationException();
				}
				break;
			case RIGHT:
				try {
					distance = sensorRight.distanceToObstacle(getCurrentPosition(), sensorDirection.oppositeDirection().rotateClockwise(), batteryLevel);
				} catch (Exception e) {
					throw new UnsupportedOperationException();
				}
				break;
			case FORWARD:
				try {
					distance = sensorForward.distanceToObstacle(getCurrentPosition(), sensorDirection, batteryLevel);
				} catch (Exception e) {
					throw new UnsupportedOperationException();
				}
				break;
			case BACKWARD:
				try {
					distance = sensorBackward.distanceToObstacle(getCurrentPosition(), sensorDirection.oppositeDirection(), batteryLevel);
				} catch (Exception e) {
					throw new UnsupportedOperationException();
				}
				break;
		}
		return distance;
	}

	/**
	 * This method checks if the robot can see the exit from its current position and direction.
	 * @param the direction to check
	 * @return true if the robot can see the exit, false if otherwise
	 */
	@Override
	public boolean canSeeThroughTheExitIntoEternity(Direction direction) throws UnsupportedOperationException {
		if(distanceToObstacle(direction) == Integer.MAX_VALUE)
			return true;
		else
			return false;
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void startFailureAndRepairProcess(Direction direction, int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void stopFailureAndRepairProcess(Direction direction) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
}
