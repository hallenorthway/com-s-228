package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads encoded file and decodes it.
 * 
 * @author Halle N
 */
public class Main {
	
	/**
	 * The encoded string.
	 */
	public String encodingString = "";
	
	/**
	 * The bit representing the encoded string.
	 */
	public String bitString = "";
		
	/**
	 * Decodes the bit string.
	 * 
	 * @param codes the tree containing the encoded characters
	 * @param msg the bits representing the encoded string
	 */
	public void decode(MsgTree codes, String msg) {
		int i = 0;
		String decoded = "";
		MsgTree current = codes;

		while (i < msg.length()) {
			while (current.right != null && current.left != null) {
				if (msg.charAt(i) == '0') {
					current = current.left;
				} else if (msg.charAt(i) == '1') {
					current = current.right;
				}
				i++;
			}
			if (current.payloadChar == '*') {
				decoded += ' ';
			} else if (current.payloadChar == '#') {
				decoded += '\n';
			} else {
				decoded += current.payloadChar;
			}
			current = codes;
		}
		System.out.println(decoded);
	}
	
	/**
	 * Reads file and reconstructs/unzips message from file.
	 */
	public static void main(String args[]) {
		// create object of the message
		Main message = new Main();
		
		int lines = 0;
		
		try {
			// read file from user input
			Scanner scnr = new Scanner(System.in);
			System.out.println("Please enter filename to decode: ");
			File file = new File(scnr.next());
			
			// count how many lines are in the file
			Scanner lineCount = new Scanner(file);
			while (lineCount.hasNextLine()) {
				lineCount.nextLine();
				lines++;
			}
			lineCount.close();
			
			// use the number of lines to read the respective encoded string
			Scanner lineScnr = new Scanner(file);
			for (int i = 0; i < lines - 1; i++) {
				String line = lineScnr.nextLine();
				
				Scanner wordScnr = new Scanner(line);
				while (wordScnr.hasNext()) {
					message.encodingString += wordScnr.next();
					
					// uses * for spaces, # for newlines
					if (!(wordScnr.hasNext()) && i == lines - 2) {
						continue;
					} else if (!(wordScnr.hasNext()) && i + 1 == 1) {
						message.encodingString += "#";
					} else {
						message.encodingString += "*";
					}
				}
				wordScnr.close();
			}
			lineScnr.close();
			
			// use the number of lines to find the respective bit string
			Scanner bitScnr = new Scanner(file);
			for (int i = 0; i < lines; i++) {
				if ((lines == 3 && i == 2) || (lines == 2 && i == 1)) {
					message.bitString += bitScnr.nextLine();
				} else {
					bitScnr.nextLine();
				}
			}
			bitScnr.close();
			
			scnr.close();
			
			// create tree representing message
			MsgTree messageTree = new MsgTree(message.encodingString);
					
			// print results
			System.out.println("\ncharacter code");
			System.out.println("-------------------------");
			MsgTree.printCodes(messageTree, "");
			System.out.println("\nMESSAGE:");
			message.decode(messageTree, message.bitString);
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
}
