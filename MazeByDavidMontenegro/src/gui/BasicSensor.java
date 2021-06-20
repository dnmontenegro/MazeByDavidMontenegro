package gui;

import generation.CardinalDirection;
import generation.Maze;
import generation.MazeContainer;
import gui.Robot.Direction;
/**
 * Class: BasicSensor
 * 
 * Responsibilities: Measure distance from an obstacle
 * 
 * Collaborators: DistanceSensor, MazeContainer, CardinalDirection
 * 
 * @author David Montenegro
 *
 */

public class BasicSensor implements DistanceSensor {
	
	private MazeContainer maze;
	private CardinalDirection direction;

	/**
	 * Constructor that setups initial objects.
	 */
	public BasicSensor() {
		//Set maze to null
		//Set direction to null
	}

	/**
	 * This method checks the distance to an obstacle.
	 */
	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		//Check if battery level is 0
		//Keep track of current position
		//Keep track of distance traveled
		//Keep checking for walls in the current direction
		//If position being searched is exit then return max int value
		//Else increase distance traveled and advance position
		//Return the distance
		return 0;
	}

	/**
	 * This method sets the maze to the parameter.
	 */
	@Override
	public void setMaze(Maze maze) {
		//Set the maze to the parameter
	}
	
	/**
	 * This method sets the direction to the parameter.
	 */
	@Override
	public void setSensorDirection(Direction mountedDirection) {
		//Set the direction to the parameter
	}

	/**
	 * This method gets the energy amount needed for sensing.
	 */
	@Override
	public float getEnergyConsumptionForSensing() {
		//Return 1.0f
		return 0;
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		//Throw new UnsupportedOperationException
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		//Throw new UnsupportedOperationException
	}

}
