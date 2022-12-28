package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Halle Northway
 *  
 *  The Town class constructs and populates a 2D array
 *  representing the town grid, where each cell is a
 *  different TownCell type representing either customers,
 *  outages, or unoccupied cells.
 */
public class Town {
	
	private int length, width;
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * 
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length; // rows
		this.width = width; // columns
		grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File file = new File(inputFileName);
		Scanner scnr = new Scanner(file);
		
		length = scnr.nextInt();
		width = scnr.nextInt();
		grid = new TownCell[length][width];
		
		int i = 0; // rows
		int j = 0; // columns
		
		while (scnr.hasNext()) {
			String currentCell = scnr.next();
			if (currentCell.equals("C")) {
				grid[i][j] = new Casual(this, i, j);
			} else if (currentCell.equals("S")) {
				grid[i][j] = new Streamer(this, i, j);
			} else if (currentCell.equals("R")) {
				grid[i][j] = new Reseller(this, i, j);
			} else if (currentCell.equals("O")) {
				grid[i][j] = new Outage(this, i, j);
			} else if (currentCell.equals("E")) {
				grid[i][j] = new Empty(this, i, j);
			}	
			
			if (j == width - 1) {
				i++;
				j = -1;
			}
			j++;	
		}
		scnr.close();
	}
	
	/**
	 * Returns width of the grid.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * 
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 * 
	 * @param seed for random number generator
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		int i = 0; // rows
		int j = 0; // columns
		
		while (i < length) {
			int cellNum = rand.nextInt(5);
			
			if (cellNum == 0) {
				grid[i][j] = new Reseller(this, i, j);
			} else if (cellNum == 1) {
				grid[i][j] = new Empty(this, i, j);
			} else if (cellNum == 2) {
				grid[i][j] = new Casual(this, i, j);
			} else if (cellNum == 3) {
				grid[i][j] = new Outage(this, i, j);
			} else if (cellNum == 4) {
				grid[i][j] = new Streamer(this, i, j);
			}
			
			if (j == width - 1) {
				i++;
				j = -1;
			}
			j++;
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 * 
	 * @return string of town grid 
	 */
	@Override
	public String toString() {
		String s = "";
		int i = 0; // rows
		int j = 0; // columns
		
		while (i < length) {
			if (grid[i][j].who() == State.RESELLER) {
				s = s + "R";
			} else if (grid[i][j].who() == State.EMPTY) {
				s = s + "E";
			} else if (grid[i][j].who() == State.CASUAL) {
				s = s + "C";
			} else if (grid[i][j].who() == State.OUTAGE) {
				s = s + "O";
			} else if (grid[i][j].who() == State.STREAMER) {
				s = s + "S";
			}
			
			if (j == width - 1) {
				i++;
				j = -1;
				if (i != length) {
					s = s + "\n";
				}
			} else {
				s = s + " ";
			}
			j++;
			
		}
		return s;
	}
}
