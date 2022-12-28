package edu.iastate.cs228.hw1;

/**
 * @author Halle Northway
 * 
 * Represents an unoccupied town cell.
 */
public class Empty extends TownCell {
	
	public Empty(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.EMPTY;
	}

	@Override
	public TownCell next(Town tNew) {
		
		plain = tNew;
		this.census(nCensus);
		
		if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) { // 6a
			return new Reseller(tNew, row, col);
		} else if (tNew.grid[row][col].who() == State.EMPTY) { // 5
			return new Casual(tNew, row, col);
		} else if (nCensus[CASUAL] >= 5) { // 6b
			return new Streamer(tNew, row, col);
		} else { // 7
			return new Empty(tNew, row, col);
		}
	}

}
