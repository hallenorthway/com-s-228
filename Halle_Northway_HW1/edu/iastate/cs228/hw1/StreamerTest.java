package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import org.junit.Test;

public class StreamerTest {

	/**
	 * In all tests, the town grid is populated by
	 * the ISP4x4 text file.
	 */
	
	// Check that cell 2,1 is a streamer customer.
	@Test
	public void who_StreamerCell_Streamer() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(State.STREAMER, town.grid[2][1].who());
	}
	
	// Check that grid of cell 2,1 is town.
	@Test
	public void current_StreamerCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[2][1].plain);
	}
	
	// Check that column of cell 2,1 is 1.
	@Test
	public void current_StreamerCellCol_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[2][1].col);
	}
	
	// Check that row of cell 2,1 is 2.
	@Test
	public void current_StreamerCellRow_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(2, town.grid[2][1].row);
	}
	
	// Census cell 2,1, check that type in next town cycle is outage.
	@Test
	public void next_StreamerCell_Outage2a() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[2][1].census(TownCell.nCensus);
		
		assertEquals(State.OUTAGE, town.grid[2][1].next(town).who());
	}
	
	// Check that grid of cell 2,1 in next town cycle is town.
	@Test
	public void next_StreamerCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[2][1].next(town).plain);
	}
	
	// Check that column of cell 2,1 in next town cycle is 1.
	@Test
	public void next_StreamerCellCol_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[2][1].next(town).col);
	}
	
	// Check that row of cell 2,1 in next town cycle is 2.
	@Test
	public void next_StreamerCellRow_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(2, town.grid[2][1].next(town).row);
	}
	
	// Adjust grid to test rule 2b at cell 2,1 by removing any
	// resellers in the neighborhood, which removes conditions
	// for rule 2a. Check that cell 2,1 becomes empty.
	@Test
	public void next_StreamerCell_Empty2b() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[3][2] = new Empty(town, 3, 2);
		town.grid[2][1].census(TownCell.nCensus);
		
		assertEquals(State.EMPTY, town.grid[2][1].next(town).who());
	}
}
