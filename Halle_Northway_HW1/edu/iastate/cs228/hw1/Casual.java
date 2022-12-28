package edu.iastate.cs228.hw1;

/**
 * @author Halle Northway
 * 
 * Represents a town cell occupied by a Casual customer.
 */
public class Casual extends TownCell {
	
	public Casual(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.CASUAL;
	}

	@Override
	public TownCell next(Town tNew) {
		
		plain = tNew;
		this.census(nCensus);
				
		if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) { // 6a
			return new Reseller(tNew, row, col);
		} else if (nCensus[RESELLER] > 0) { // 1a
			return new Outage(tNew, row, col);
		} else if (nCensus[STREAMER] > 0) { // 1b
			return new Streamer(tNew, row, col);
		} else if (nCensus[CASUAL] >= 5) { // 6b
			return new Streamer(tNew, row, col);
		} else { // 7
			return new Casual(tNew, row, col);
		}
	}

}
