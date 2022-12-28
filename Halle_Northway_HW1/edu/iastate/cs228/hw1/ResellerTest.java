package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import org.junit.Test;

public class ResellerTest {
	
	/**
	 * In all tests, the town grid is populated by
	 * the ISP4x4 text file.
	 */
	
	// Check that cell 0,1 is a reseller customer.
	@Test
	public void who_ResellerCell_Reseller() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(State.RESELLER, town.grid[0][1].who());
	}
	
	// Check that grid of cell 0,1 is town.
	@Test
	public void current_ResellerCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[0][1].plain);
	}
	
	// Check that column of cell 0,1 is 1.
	@Test
	public void current_ResellerCellCol_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[0][1].col);
	}
	
	// Check that row of cell 0,1 is 0.
	@Test
	public void current_ResellerCellRow_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(0, town.grid[0][1].row);
	}
	
	// Census cell 0,1, check that type in next town cycle is empty.
	@Test
	public void next_ResellerCell_Empty3a() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[0][1].census(TownCell.nCensus);
		
		assertEquals(State.EMPTY, town.grid[0][1].next(town).who());
	}
	
	// Check that grid of cell 0,1 in next town cycle is town.
	@Test
	public void next_ResellerCellTown_Town() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(town, town.grid[0][1].next(town).plain);
	}
	
	// Check that col of cell 0,1 in next town cycle is 1.
	@Test
	public void next_ResellerCellCol_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(1, town.grid[0][1].next(town).col);
	}
	
	// Check that row of cell 0,1 in next town cycle is 0.
	@Test
	public void next_ResellerCellRow_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		
		assertEquals(0, town.grid[0][1].next(town).row);
	}
	
	// Adjust grid to test rule 3b at cell 0,1 by adding
	// casual customers until there are 3 or more in the
	// neighborhood, which removes conditions for rule 3a.
	// Check that cell 0,1 becomes empty.
	@Test
	public void next_ResellerCell_Empty3b() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[2][2] = new Casual(town, 2, 2);
		town.grid[3][2] = new Casual(town, 3, 2);
		town.grid[3][1] = new Casual(town, 3, 1);
		town.grid[2][1] = new Reseller(town, 2, 1);
		town.grid[2][1].census(TownCell.nCensus);
		
		assertEquals(State.EMPTY, town.grid[2][1].next(town).who());
	}
	
	// Adjust grid to test rule 6b at cell 0,1 by adding
	// casual customers until there are 5 or more in the
	// neighborhood, which removes conditions all other
	// rules except 7. Check that cell 0,1 becomes a streamer.
	@Test
	public void next_ResellerCell_Streamer6b() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town.grid[0][1] = new Casual(town, 0, 1);
		town.grid[1][1] = new Casual(town, 1, 1);
		town.grid[1][3] = new Casual(town, 1, 3);
		town.grid[0][3] = new Casual(town, 0, 3);
		town.grid[0][2] = new Reseller(town, 0, 2);
		town.grid[0][2].census(TownCell.nCensus);
		
		assertEquals(State.STREAMER, town.grid[0][2].next(town).who());
	}
}
