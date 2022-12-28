package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import org.junit.Test;

public class EmptyTest {

	/**
	 * In all tests, the town grid is populated by
	 * the ISP4x4 text file.
	 */
	
	// Check that cell 1,1 is empty.
	@Test
	public void who_EmptyCell_Empty() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(State.EMPTY, town.grid[1][1].who());
	}
	
	// Check that grid of cell 1,1 is town.
	@Test
	public void current_EmptyCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[1][1].plain);
	}
	
	// Check that column of cell 1,1 is 1.
	@Test
	public void current_EmptyCellCol_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[1][1].col);
	}
	
	// Check that row of cell 1,1 is 1.
	@Test
	public void current_EmptyCellRow_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[1][1].row);
	}
	
	// Census cell 1,1, check that type in next town cycle is casual.
	@Test
	public void next_EmptyCell_Casual5() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[1][1].census(TownCell.nCensus);
		
		assertEquals(State.CASUAL, town.grid[1][1].next(town).who());
	}
	
	// Check that grid of cell 1,1 in next town cycle is town.
	@Test
	public void next_EmptyCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[1][1].next(town).plain);
	}
	
	// Check that column of cell 1,1 in next town cycle is 1.
	@Test
	public void next_EmptyCellCol_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[1][1].next(town).col);
	}
	
	// Check that row of cell 1,1 in next town cycle is 1.
	@Test
	public void next_EmptyCellRow_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[1][1].next(town).row);
	}
	
	// Adjust grid to test rule 6a at cell 3,3 by changing the
	// reseller customer to an empty cell, which removes conditions
	// for rule 5. Check that cell 3,3 becomes reseller.
	@Test
	public void next_EmptyCell_Reseller6a() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[3][3] = new Empty(town, 3, 3);
		town.grid[3][3].census(TownCell.nCensus);
		
		assertEquals(State.RESELLER, town.grid[3][3].next(town).who());
	}
}
