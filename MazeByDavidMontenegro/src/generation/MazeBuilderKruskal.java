package generation;

import java.util.ArrayList;

public class MazeBuilderKruskal extends MazeBuilder implements Runnable {
	
	public MazeBuilderKruskal() {
		// Call to super
	}

	@Override
	protected void generatePathways() {
		// Create candidates list of wallboards
		// Create matrix to keep track of slots
		// Go through candidates list randomly removing a wallboard
		// If neighboring slot is not yet in the path then the wallboard is deleted
		// The matrix keeps track of the current path
	}
	
	private Wallboard extractWallboardFromCandidateSetRandomly(final ArrayList<Wallboard> candidates) {
		// Taken from MazeBuilderPrim class
		// Remove a random candidate from list of candidates and return it
		return null;
	}
	
	private void updateListOfWallboards(ArrayList<Wallboard> wallboards) {
		// Loop through the maze
		// Create a new wallboard
		// Set wallboards to all directions of a slot
		// Add wallboards that aren't on the maze borders to the candidates list
	}
}
