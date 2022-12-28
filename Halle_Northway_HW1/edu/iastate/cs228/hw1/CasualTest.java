package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

public class CasualTest {
	
	/**
	 * In all tests, the town grid is populated by
	 * the ISP4x4 text file.
	 */
	
	// Check that cell 1,2 is a casual customer.
	@Test
	public void who_CasualCell_Casual() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(State.CASUAL, town.grid[1][2].who());
	}
	
	// Check that grid of cell 1,2 is town.
	@Test
	public void current_CasualCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[1][2].plain);
	}
	
	// Check that column of cell 1,2 is 2.
	@Test
	public void current_CasualCellCol_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(2, town.grid[1][2].col);
	}
	
	// Check that row of cell 1,2 is 1.
	@Test
	public void current_CasualCellRow_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[1][2].row);
	}
	
	// Census cell 1,2, check that type in next town cycle is outage.
	@Test
	public void next_CasualCell_Outage1a() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[1][2].census(TownCell.nCensus);
		
		assertEquals(State.OUTAGE, town.grid[1][2].next(town).who());
	}
	
	// Check that grid of cell 1,2 in next town cycle is town.
	@Test
	public void next_CasualCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[1][2].next(town).plain);
	}
	
	// Check that column of cell 1,2 in next town cycle is 2.
	@Test
	public void next_CasualCellCol_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(2, town.grid[1][2].next(town).col);
	}
	
	// Check that row of cell 1,2 in next town cycle is 1.
	@Test
	public void next_CasualCellRow_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[1][2].next(town).row);
	}
	
	// Adjust grid to test rule 1b at cell 1,2 by removing any
	// resellers in the neighborhood, which removes conditions
	// for rule 1a. Check that cell 1,2 becomes streamer customer.
	@Test
	public void next_CasualCell_Streamer1b() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[0][1] = new Outage(town, 0, 1);
		town.grid[0][3] = new Outage(town, 0, 1);
		town.grid[1][2].census(TownCell.nCensus);
		
		assertEquals(State.STREAMER, town.grid[1][2].next(town).who());
	}
}