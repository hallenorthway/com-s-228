package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

public class TownCellTest {

	/**
	 * In all tests, the town grid is populated by
	 * the ISP4x4 text file.
	 */
	
	// Check if the grid of cell 3,0 is town.
	@Test
	public void current_TownCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[3][0];
		assertEquals(town, townCell.plain);
	}
	
	// Check if the column of cell 3,0 is 0.
	@Test
	public void current_TownCellCol_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[3][0];
		assertEquals(0, townCell.col);
	}
	
	// Check if the row of cell 3,0 is 3.
	@Test
	public void current_TownCellRow_3() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[3][0];
		assertEquals(3, townCell.row);
	}
	
	// These next tests check the census counts for each TownCell type for cell 2,2.
	@Test
	public void census_TownCellReseller_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][2];
		
		townCell.census(TownCell.nCensus);
		assertEquals(2, TownCell.nCensus[0]);
	}
	
	@Test
	public void census_TownCellEmpty_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][2];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[1]);
	}
	
	@Test
	public void census_TownCellCasual_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][2];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[2]);
	}
	
	@Test
	public void census_TownCellOutage_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][2];
		
		townCell.census(TownCell.nCensus);
		assertEquals(2, TownCell.nCensus[3]);
	}
	
	@Test
	public void census_TownCellStreamer_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][2];
		
		townCell.census(TownCell.nCensus);
		assertEquals(2, TownCell.nCensus[4]);
	}
	
	// These next tests check the census counts for each TownCell type for cell 0,1, an edge cell.
	@Test
	public void census_Edge1TownCellReseller_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][1];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[0]);
	}
	
	@Test
	public void census_Edge1TownCellEmpty_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][1];
		
		townCell.census(TownCell.nCensus);
		assertEquals(2, TownCell.nCensus[1]);
	}
	
	@Test
	public void census_Edge1TownCellCasual_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][1];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[2]);
	}
	
	@Test
	public void census_Edge1TownCellOutage_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][1];
		
		townCell.census(TownCell.nCensus);
		assertEquals(2, TownCell.nCensus[3]);
	}
	
	@Test
	public void census_Edge1TownCellStreamer_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][1];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[4]);
	}
	
	// These next tests check the census counts for each TownCell type for cell 2,0, an edge cell.
	@Test
	public void census_Edge2TownCellReseller_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[0]);
	}
	
	@Test
	public void census_Edge2TownCellEmpty_3() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(3, TownCell.nCensus[1]);
	}
	
	@Test
	public void census_Edge2TownCellCasual_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[2]);
	}
	
	@Test
	public void census_Edge2TownCellOutage_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[3]);
	}
	
	@Test
	public void census_Edge2TownCellStreamer_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[2][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[4]);
	}
	
	// These next tests check the census counts for each TownCell type for cell 0,0, a corner cell.
	@Test
	public void census_Corner1TownCellReseller_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[0]);
	}
	
	@Test
	public void census_Corner1TownCellEmpty_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(2, TownCell.nCensus[1]);
	}
	
	@Test
	public void census_Corner1TownCellCasual_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[2]);
	}
	
	@Test
	public void census_Corner1TownCellOutage_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[3]);
	}
	
	@Test
	public void census_Corner1TownCellStreamer_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[0][0];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[4]);
	}
	
	// These next tests check the census counts for each TownCell type for cell 3,3, a corner cell.
	@Test
	public void census_Corner2TownCellReseller_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[3][3];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[0]);
	}
	
	@Test
	public void census_Corner2TownCellEmpty_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[3][3];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[1]);
	}
	
	@Test
	public void census_Corner2TownCellCasual_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[3][3];
		
		townCell.census(TownCell.nCensus);
		assertEquals(0, TownCell.nCensus[2]);
	}
	
	@Test
	public void census_Corner2TownCellOutage_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[3][3];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[3]);
	}
	
	@Test
	public void census_Corner2TownCellStreamer_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		TownCell townCell = town.grid[3][3];
		
		townCell.census(TownCell.nCensus);
		assertEquals(1, TownCell.nCensus[4]);
	}
}