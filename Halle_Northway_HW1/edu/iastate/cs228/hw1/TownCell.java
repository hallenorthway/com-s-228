package edu.iastate.cs228.hw1;

/**
 * @author Halle Northway
 * 
 * The TownCell class constructs an individual cell
 * within the town grid and performs a census of the
 * cells neighbor types.
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	// Constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	// Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * As the census method checks the surrounding neighbors, this
	 * helper method ensures that the rows and columns being checked
	 * are within the town grid boundaries.
	 * 
	 * @param row
	 * @param col
	 * @return 0 for out of bounds, 1 for within bounds
	 */
	protected int gridBoundCheck (int r, int c) {
		int length = plain.getLength();
		int width = plain.getWidth();
		if ((r >= length || r < 0) || (c >= width || c < 0)) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/**
	 * Returns the index of the respective TownCell type of a particular
	 * neighbor, in which the index is then used to update the counts
	 * in the neighbor census.
	 * 
	 * @param neighbor
	 * @return index of neighbor type
	 */
	protected int neighborCheck (State neighbor) {
		if (neighbor == State.RESELLER) {
			return 0;
		} else if (neighbor == State.EMPTY) {
			return 1;
		} else if (neighbor == State.CASUAL) {
			return 2;
		} else if (neighbor == State.OUTAGE) {
			return 3;
		} else if (neighbor == State.STREAMER) {
			return 4;
		} else {
			return -1; // Produces an error.
		}
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood.
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// Zero the counts of all customers.
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		int i = row - 1; // rows
		int j = col - 1; // columns
		
		// Conditions evaluated in for loop check cells 3x3 boundaries, whereas
		// gridBoundCheck() method checks for town grid boundaries at any location.
		for (i = (i <= 0) ? 0 : i; i <= row + 1; i++) {
			j = col - 1;
			for (j = (j <= 0) ? 0 : j; j <= col + 1; j++ ) {
				if (gridBoundCheck(i, j) == 0) {
					break;
				} if (i == row && j == col) {
					continue;
				} else {
					nCensus[neighborCheck(plain.grid[i][j].who())]++;
				}
			}
			
		}
	}


	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State: state of cell
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);

}