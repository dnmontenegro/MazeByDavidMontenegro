package generation;

import java.util.ArrayList;

public class MazeBuilderKruskal extends MazeBuilder implements Runnable {
	
	public MazeBuilderKruskal() {
		// Call to super
		super();
	}

	@Override
	protected void generatePathways() {
		// Create candidates list of wallboards
		// Create matrix to keep track of slots
		// Go through candidates list randomly removing a wallboard
		// If neighboring slot is not yet in the path then the wallboard is deleted
		// The matrix keeps track of the current path
		final ArrayList<Wallboard> candidates = new ArrayList<Wallboard>();
		updateListOfWallboards(candidates);
		
		int count = 1;
		int[][] board = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				board[i][j] = count;
				count++;
			}
		}
		
		Wallboard curWallboard;
		while(!candidates.isEmpty()){
			curWallboard = extractWallboardFromCandidateSetRandomly(candidates);
			if (board[curWallboard.getX()][curWallboard.getY()] != board[curWallboard.getNeighborX()][curWallboard.getNeighborY()]) {
				floorplan.deleteWallboard(curWallboard);
				int value = board[curWallboard.getX()][curWallboard.getY()];
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (board[i][j] == value)
							board[i][j] = board[curWallboard.getNeighborX()][curWallboard.getNeighborY()];
					}
				}
			}
		}
	}
	
	private Wallboard extractWallboardFromCandidateSetRandomly(final ArrayList<Wallboard> candidates) {
		// Remove a random candidate from list of candidates and return it
		// Taken from MazeBuilderPrim class
		return candidates.remove(random.nextIntWithinInterval(0, candidates.size()-1)); 
	}
	
	private void updateListOfWallboards(ArrayList<Wallboard> wallboards) {
		// Loop through the maze
		// Create a new wallboard
		// Set wallboards to all directions of a slot
		// Add wallboards that aren't on the maze borders to the candidates list
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Taken from MazeBuilderPrim class
				Wallboard wallboard = new Wallboard(i, j, CardinalDirection.East) ;
				for (CardinalDirection cd : CardinalDirection.values()) {
					wallboard.setLocationDirection(i, j, cd);
					if (floorplan.canTearDown(wallboard))
					{
						wallboards.add(new Wallboard(i, j, cd));
					}
				}
			}
		}
	}
}
