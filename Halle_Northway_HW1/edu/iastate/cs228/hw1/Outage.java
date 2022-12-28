package edu.iastate.cs228.hw1;

/**
 * @author Halle Northway
 * 
 * Represents a town cell with an outage.
 */
public class Outage extends TownCell {
	
	public Outage(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	public State who() {
		return State.OUTAGE;
	}

	@Override
	public TownCell next(Town tNew) {
		
		plain = tNew;
		this.census(nCensus);

		return new Empty(tNew, row, col); // 4, ignores 6a, 6b, and 7
	}

}
