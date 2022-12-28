package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

public class TownTest {
	
	// Check that the width for Town(1,3) is 3.
	@Test
	public void town_Width_3() {
		Town town = new Town(1, 3);
		assertEquals(3, town.getWidth());
	}
	
	// Check that the length for Town(1,3) is 1.
	@Test
	public void town_Length_1() {
		Town town = new Town(1, 3);
		assertEquals(1, town.getLength());
	}
	
	// Check that the grid length in Town(1,3) is also set to 1.
	@Test
	public void town_GridLength_1() {
		Town town = new Town(1, 3);
		assertEquals(1, town.grid.length);
	}
	
	// Check that town populated with IPS4x4 file has width of 4.
	@Test
	public void fileTown_Width_4() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		assertEquals(4, town.getWidth());
	}
	
	// Check that town populated with IPS4x4 file has length of 4.
	@Test
	public void fileTown_Length_4() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		assertEquals(4, town.getLength());
	}
	
	// Check that town grid populated with IPS4x4 file also has length of 4.
	@Test
	public void fileTown_GridLength_4() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		assertEquals(4, town.grid.length);
	}
	
	// Check that town grid populated with ISP4x4 outputs correctly.
	@Test
	public void fileTown_toString() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		assertEquals("O R O R\n"
					+ "E E C O\n"
					+ "E S O S\n"
					+ "E O R R", town.toString());
	}
	
	// Check that width for Town(2,3) randomly generated with seed 10 is 3.
	@Test
	public void randTown_Width_3() {
		Town town = new Town(2, 3);
		town.randomInit(10);
		assertEquals(3, town.getWidth());
	}
	
	// Check that length for Town(2,3) randomly generated with seed 10 is 2.
	@Test
	public void randTown_Length_2() {
		Town town = new Town(2, 3);
		town.randomInit(10);
		assertEquals(2, town.getLength());
	}
	
	// Check that grid for Town(2,3) randomly generated with seed 10 outputs correctly.
	@Test
	public void randTown_toString() {
		Town town = new Town(2, 3);
		town.randomInit(10);
		assertEquals("O R O\n"
					+ "R E E", town.toString());
	}
	
}
