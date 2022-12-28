package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * @author Halle Northway
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * 
	 * @param tOld: old/current Town object
	 * @return new town object
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		for (int i = 0; i < tOld.getLength(); i++) {
			for (int j = 0; j < tOld.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tOld);
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * 
	 * @param town
	 * @return monthly profit
	 */
	public static int getProfit(Town town) {
		int casualCount = 0;
		
		for (int i = 0; i < town.getLength(); i++) {
			for (int j = 0; j < town.getWidth(); j++) {
				if (town.grid[i][j].who() == State.CASUAL) {
					casualCount++;
				}
			}
		}
		
		int monthlyProfit = ((town.getWidth() * 10) * casualCount) / (town.getLength() * 10);
		
		return monthlyProfit;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * @throws FileNotFoundException, InputMismatchException
	 * 
	 */
	public static void main(String []args) throws FileNotFoundException, InputMismatchException {
		Scanner input = new Scanner(System.in);
		
		Town town = null;
		int profit = 0;
		int option;
				
		while(true) {
			try {
				System.out.print("How to populate grid (type 1 or 2):\n"
						+ "1: From a file.\n"
						+ "2: Randomly with seed.\n");
				System.out.println("Please select an option: ");
				option = input.nextInt();
				if (option != 1 && option != 2) {
					System.out.println("Please input 1 or 2. Try again.\n");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please input 1 or 2. Try again.\n");
				input.nextLine();
			}
		}
		
		if (option == 1) {
			while(true) {
				try {
					System.out.println("Please enter file path: ");
					String filePath = input.next();
					town = new Town(filePath);
					break;
				} catch (FileNotFoundException e) {
					System.out.println("File not found. Try again.\n");
					input.nextLine();
				}
			}
			
		} else {
			while(true) {
				try {
					System.out.println("Provide rows, cols, and seed integer separated by spaces: ");
					int rows = input.nextInt();
					int cols = input.nextInt();
					int seed = input.nextInt();
					town = new Town(rows, cols);
					town.randomInit(seed);
					break;
				} catch (InputMismatchException i) {
					System.out.println("Please input integers. Try again.\n");
					input.nextLine();
				}
			}
		}
		
		int i = 0;
		while (i < 12) {
			profit += ISPBusiness.getProfit(town);
			town = ISPBusiness.updatePlain(town);
			i++;
		}
		
		int maxProfit = (town.getLength() * town.getWidth()) * 12;
		double profitUtilization = (profit / (double)maxProfit) * 100;
		System.out.printf("Profit utilization: %.2f%%", profitUtilization);
		
		input.close();
	}
}
