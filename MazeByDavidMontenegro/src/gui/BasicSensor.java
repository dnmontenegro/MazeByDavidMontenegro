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
	private Direction direction;

	/**
	 * Constructor that setups initial objects.
	 */
	public BasicSensor() {
		maze = null;
		direction = null;
	}

	/**
	 * This method checks the distance to an obstacle.
	 */
	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		if(powersupply[0] < getEnergyConsumptionForSensing())
			throw new Exception();
		int[] position = currentPosition;
		int distanceTraveled = 0;
		while(maze.getFloorplan().hasNoWall(position[0], position[1], currentDirection) == true) {
			if(maze.getFloorplan().isExitPosition(position[0], position[1]) == true)
				return Integer.MAX_VALUE;
			distanceTraveled++;
			position[0] = position[0] + currentDirection.getDirection()[0];
			position[1] = position[1] + currentDirection.getDirection()[1];
		}
		return distanceTraveled;
	}

	/**
	 * This method sets the maze to the parameter.
	 */
	@Override
	public void setMaze(Maze maze) {
		this.maze = (MazeContainer) maze;
	}
	
	/**
	 * This method sets the direction to the parameter.
	 */
	@Override
	public void setSensorDirection(Direction mountedDirection) {
		direction = mountedDirection;
	}

	/**
	 * This method gets the energy amount needed for sensing.
	 */
	@Override
	public float getEnergyConsumptionForSensing() {
		return 1.0f;
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method throws an UnsupportedOperationException.
	 */
	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
