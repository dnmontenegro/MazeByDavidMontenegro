package gui;

import generation.Maze;
import generation.MazeContainer;
/**
 * Class: WallFollower
 * 
 * Responsibilities: Leads a robot by following the left hand wall
 * 
 * Collaborators: RobotDriver, BasicRobot, MazeContainer
 * 
 * @author David Montenegro
 *
 */

public class WallFollower implements RobotDriver {
	
	private BasicRobot basicRobot;
	private MazeContainer maze;

	/**
	 * Constructor that setups initial objects.
	 */
	public WallFollower() {
		//Set basicRobot to null
		//Set maze to null
	}

	/**
	 * This method sets the robot to the parameter.
	 */
	@Override
	public void setRobot(Robot r) {
		//Set the robot to the parameter
	}

	/**
	 * This method sets the maze to the parameter.
	 */
	@Override
	public void setMaze(Maze maze) {
		//Set the maze to the parameter
	}

	/**
	 * This method leads the robot to the exit using the strategy of following the left hand wall.
	 */
	@Override
	public boolean drive2Exit() throws Exception {
		//Loop until the robot is at the exit position
			//Check if the robot has stopped
				//If so throw exception
			//Check if the there is a wall to the left
				//If not then rotate left and move one step
			//Else check if there is a wall in front of the robot
				//If so then turn right
			//Else move one step forward
		//If at exit position rotate until robot is facing forward towards the exit and then return true
		//Otherwise return false
		return false;
	}
	
	/**
	 * This method leads the robot one step towards the exit using the strategy of following the left hand wall.
	 */
	@Override
	public boolean drive1Step2Exit() throws Exception {
		//Check if the robot has stopped
			//If so throw exception
		//Check if the there is a wall to the left
			//If not then rotate left and move one step and return true
		//Else check if there is a wall in front of the robot
			//If so then turn right and return false
		//Else move one step forward and return true
		//If at exit position rotate until robot is facing forward towards the exit and then return false
		//Otherwise return false
		return false;
	}

	/**
	 * This method gets the amount of energy used during traversal of the maze.
	 */
	@Override
	public float getEnergyConsumption() {
		//Return 2000 subtracted by the battery level of the robot
		return 0;
	}

	/**
	 * This method gets the amount of cells traveled during traversal of the maze.
	 */
	@Override
	public int getPathLength() {
		//Return the odometer reading of the robot
		return 0;
	}

}
