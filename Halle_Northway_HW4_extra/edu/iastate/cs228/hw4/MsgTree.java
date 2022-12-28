package edu.iastate.cs228.hw4;

/**
 * Represents a node within the binary tree.
 * 
 * @author Halle N
 */
public class MsgTree {
	/**
	 * Character at node.
	 */
	public char payloadChar;
	
	/**
	 * Left node.
	 */
	public MsgTree left;
	
	/**
	 * Right node.
	 */
	public MsgTree right;
	
	/**
	 * Parent of node.
	 */
	public MsgTree parent;
	
	/**
	 * Constructor that builds the tree from a given string.
	 * 
	 * @param encodingString the given string to be encoded
	 */
	public MsgTree(String encodingString) {
		int i = 0;
		if (encodingString == null) {
			return;
		}
		MsgTree current = this;
		while (i < encodingString.length()) {
			MsgTree node = new MsgTree(' ');
			if (encodingString.charAt(i) == '^') {
				if (i == 0) {
					current.payloadChar = encodingString.charAt(i);
				}
				current.left = node;
				node.parent = current;
				current = current.left;
			} else {
				current.payloadChar = encodingString.charAt(i);
				if (i == encodingString.length() - 1) {
					break;
				}
				while (current.parent != null) {
					current = current.parent;
					
					if (current.right == null) {
						break;
					} else {
						continue;
					}
				}
				current.right = node;
				node.parent = current;
				current = current.right;
			}
			i++;
		}
	}
	
	/**
	 * Constructor for a single node with null children.
	 * 
	 * @param payloadChar the given character for the single node
	 */
	public MsgTree(char payloadChar) {
		this.payloadChar = payloadChar;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	/**
	 * Prints characters and their binary codes.
	 * 
	 * @param root the root of the tree
	 * @param code the binary code representing the character
	 */
	public static void printCodes(MsgTree root, String code) {
		if (root.left == null && root.right == null) {
			// assumes spaces and newlines are irrelevant, so does
			// not print the character representing them
			if (root.payloadChar == '*' || root.payloadChar == '#') {
				return;
			} else {
				System.out.println("   " + root.payloadChar + "\t  " + code);
			}
		} else {
			printCodes(root.left, code + "0");
			printCodes(root.right, code + "1"); 
		}
	}
}