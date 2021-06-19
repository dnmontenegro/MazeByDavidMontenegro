package gui;

import generation.CardinalDirection;
import generation.Maze;
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

	public BasicSensor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int distanceToObstacle(int[] currentPosition, CardinalDirection currentDirection, float[] powersupply)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaze(Maze maze) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSensorDirection(Direction mountedDirection) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getEnergyConsumptionForSensing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void startFailureAndRepairProcess(int meanTimeBetweenFailures, int meanTimeToRepair)
			throws UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopFailureAndRepairProcess() throws UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

}
