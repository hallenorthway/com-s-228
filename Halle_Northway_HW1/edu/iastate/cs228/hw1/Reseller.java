package edu.iastate.cs228.hw1;

/**
 * @author Halle Northway
 * 
 * Represents a town cell occupied by a Reseller customer.
 */
public class Reseller extends TownCell {
	
	public Reseller(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.RESELLER;
	}

	@Override
	public TownCell next(Town tNew) {
		
		plain = tNew;
		this.census(nCensus);
		
		if (nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) { // 3a and 3b
			return new Empty(tNew, row, col);
		} else if (nCensus[CASUAL] >= 5) { // 6b
			return new Streamer(tNew, row, col);
		} else { // 7
			return new Reseller(tNew, row, col);
		}
	}

}
