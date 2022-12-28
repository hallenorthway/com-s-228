package edu.iastate.cs228.hw1;

/**
 * @author Halle Northway
 * 
 * Represents a town cell occupied by a Streamer customer.
 */
public class Streamer extends TownCell {
	
	public Streamer(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.STREAMER;
	}

	@Override
	public TownCell next(Town tNew) {
		
		plain = tNew;
		this.census(nCensus);
		
		if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) { // 6a
			return new Reseller(tNew, row, col);
		} else if (nCensus[RESELLER] > 0) { // 2a
			return new Outage(tNew, row, col);
		} else if (nCensus[OUTAGE] > 0) { //2b
			return new Empty(tNew, row, col);
		} else if (nCensus[CASUAL] >= 5) { // 6b
			return new Streamer(tNew, row, col);
		} else { // 7
			return new Streamer(tNew, row, col);
		}
	}

}