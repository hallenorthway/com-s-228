package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import org.junit.Test;

public class ISPBusinessTest {
	
	/**
	 * In all tests, the town grid is populated by
	 * the ISP4x4 text file.
	 */

	// After updating, check that town length is 4.
	@Test
	public void update_TownLength_4() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		ISPBusiness.updatePlain(town);
		assertEquals(4, town.getLength());
	}
	
	// After updating, check that town width is 4.
	@Test
	public void update_TownWidth_4() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		ISPBusiness.updatePlain(town);
		assertEquals(4, town.getWidth());
	}
	
	// After 0 iterations, profit is 1.
	@Test
	public void profit_Iteration_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		assertEquals(1, ISPBusiness.getProfit(town));
	}
	
	// After 1 iterations, check town grid.
	@Test
	public void update_Iteration_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		assertEquals("E E E E\n"
				+ "C C O E\n"
				+ "C O E O\n"
				+ "C E E E", town.toString());
	}
	
	// After 1 iterations, profit is 4.
	@Test
	public void profit_Iteration1_4() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		assertEquals(4, ISPBusiness.getProfit(town));
	}
	
	// After 2 iterations, check town grid.
	@Test
	public void update_Iteration_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("R C C C\n"
				+ "C C E C\n"
				+ "C E C E\n"
				+ "C C C C", town.toString());
	}
	
	// After 2 iterations, profit is 12.
	@Test
	public void profit_Iteration2_12() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(12, ISPBusiness.getProfit(town));
	}
	
	// After 3 iterations, check town grid.
	@Test
	public void update_Iteration_3() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("E R R R\n"
				+ "R O C C\n"
				+ "R R S R\n"
				+ "R R C R", town.toString());
	}
	
	// After 3 iterations, profit is 3.
	@Test
	public void profit_Iteration3_3() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(3, ISPBusiness.getProfit(town));
	}
	
	// After 4 iterations, check town grid.
	@Test
	public void update_Iteration_4() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("R E E E\n"
				+ "E E R R\n"
				+ "E E R E\n"
				+ "E E R E", town.toString());
	}
	
	// After 4 iterations, profit is 0.
	@Test
	public void profit_Iteration4_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(0, ISPBusiness.getProfit(town));
	}
	
	// After 5 iterations, check town grid.
	@Test
	public void update_Iteration_5() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("E C C R\n"
				+ "C C E E\n"
				+ "C C E R\n"
				+ "C C E R", town.toString());
	}
	
	// After 5 iterations, profit is 8.
	@Test
	public void profit_Iteration5_8() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(8, ISPBusiness.getProfit(town));
	}
	
	// After 6 iterations, check town grid.
	@Test
	public void update_Iteration_6() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("R C O E\n"
				+ "R S C C\n"
				+ "R S C E\n"
				+ "R C R E", town.toString());
	}
	
	// After 6 iterations, profit is 5.
	@Test
	public void profit_Iteration6_5() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(5, ISPBusiness.getProfit(town));
	}
	
	// After 7 iterations, check town grid.
	@Test
	public void update_Iteration_7() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("E R E R\n"
				+ "E R S C\n"
				+ "E R O R\n"
				+ "E R E R", town.toString());
	}
	
	// After 7 iterations, profit is 1.
	@Test
	public void profit_Iteration7_1() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(1, ISPBusiness.getProfit(town));
	}
	
	// After 8 iterations, check town grid.
	@Test
	public void update_Iteration_8() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("R E R E\n"
				+ "C E O O\n"
				+ "C E E E\n"
				+ "R E R E", town.toString());
	}
	
	// After 8 iterations, profit is 2.
	@Test
	public void profit_Iteration8_2() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(2, ISPBusiness.getProfit(town));
	}
	
	// After 9 iterations, check town grid.
	@Test
	public void update_Iteration_9() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("E C E C\n"
				+ "O C E E\n"
				+ "O C C C\n"
				+ "E C E C", town.toString());
	}
	
	// After 9 iterations, profit is 8.
	@Test
	public void profit_Iteration9_8() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(8, ISPBusiness.getProfit(town));
	}
	
	// After 10 iterations, check town grid.
	@Test
	public void update_Iteration_10() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("R C C C\n"
				+ "E C C C\n"
				+ "E C S C\n"
				+ "R C R R", town.toString());
	}
	
	// After 10 iterations, profit is 9.
	@Test
	public void profit_Iteration10_9() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(9, ISPBusiness.getProfit(town));
	}
	
	// After 11 iterations, check town grid.
	@Test
	public void update_Iteration_11() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals("E R R R\n"
				+ "R O R R\n"
				+ "R O R R\n"
				+ "E R E E", town.toString());
	}
	
	// After 11 iterations, profit is 0.
	@Test
	public void profit_Iteration11_0() throws FileNotFoundException {
		Town town = new Town("ISP4x4");
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		town = ISPBusiness.updatePlain(town);
		assertEquals(0, ISPBusiness.getProfit(town));
	}
}
