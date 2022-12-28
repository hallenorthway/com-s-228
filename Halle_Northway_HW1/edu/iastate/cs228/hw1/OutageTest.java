package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import org.junit.Test;

public class OutageTest {

	/**
	 * In all tests, the town grid is populated by
	 * the ISP4x4 text file.
	 */
	
	// Check that cell 0,0 is an outage.
	@Test
	public void who_OutageCell_Outage() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(State.OUTAGE, town.grid[0][0].who());
	}
	
	// Check that grid of cell 0,0 is town.
	@Test
	public void current_OutageCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[0][0].plain);
	}
	
	// Check that column of cell 0,0 is 0.
	@Test
	public void current_OutageCellCol_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(0, town.grid[0][0].col);
	}
	
	// Check that row of cell 0,0 is 0.
	@Test
	public void current_OutageCellRow_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(0, town.grid[0][0].row);
	}
	
	// Census cell 0,0, check that type in next town cycle is empty.
	@Test
	public void next_OutageCell_Empty4() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[0][0].census(TownCell.nCensus);
		
		assertEquals(State.EMPTY, town.grid[0][0].next(town).who());
	}
	
	// Check that grid of cell 0,0 in next town cycle is town.
	@Test
	public void next_OutageCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[0][0].next(town).plain);
	}
	
	// Check that column of cell 0,0 in next town cycle is 0.
	@Test
	public void next_OutageCellCol_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(0, town.grid[0][0].next(town).col);
	}
	
	// Check that row of cell 0,0 in next town cycle is 0.
	@Test
	public void next_OutageCellRow_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(0, town.grid[0][0].next(town).row);
	}
}
