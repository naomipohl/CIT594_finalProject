package slidePuzzle;

import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) {
		// Create game board
		GameBoard g = new GameBoard(16, 1);

		// Populate the board
		g.populateBoard(g.root);

		// Initialize scanner
		Scanner sc = new Scanner(System.in);

		// Print welcome message
		System.out
				.println("Welcome to the sliding tile game!\n" + "You are currently in the location marked with a 'W', "
						+ "and your objective is to get to the dog tile, which is "
						+ "marked with a 'D'. You can perform the following operations: "
						+ "Merge ('merge'), Split('split'), Swap ('su', 'sd', 'sl', or 'sr'), "
						+ "and Rotate ('rotate'). You can quit at any point by typing 'quit'. "
						+ "Let's get started- catch that dog!");

		// User plays game
		String userInput = null;

		while (true) {

			// First check whether the user has already won
			if (g.walker.equals(g.dog)) {
				sc.close();
				System.out.println("You won!");
				break;
			}

			userInput = sc.nextLine();

			// Merge
			if (userInput.equals("merge")) {
				// If trying to merge max size tiles, throw error
				if (g.walker.getDepth() == g.maxDepth) {
					System.out.println("Tiles too large- cannot perform merge");
				} else {
					// Find current tile's parent & grandparent
					InternalTile parent = g.findParent(g.walker);
					InternalTile grandparent = g.findParent(parent);

					// Perform the merge
					LeafTile l = parent.merge(grandparent);

					// Update the display
					g.updateDisplay(l);
				}
			}
			// Rotate
			else if (userInput.equals("rotate")) {
				// Find current tile's parent
				InternalTile parent = g.findParent(g.walker);

				// Perform the rotation
				parent.rotate();

				// Update the display
				// TODO inserted by naomi [Apr 28, 2019, 1:10:06 PM]
				// Why does updateDisplay take in leaf node????
				g.updateDisplay((LeafTile) parent.getNE());
			}
			// Split
			else if (userInput.equals("split")) {
				// If trying to split min size tiles, throw error
				if (g.walker.getDepth() == g.minDepth) {
					System.out.println("Tiles too small- cannot perform split");
				} else {
					// Split the current tile
					InternalTile parent = g.walker.split();

					g.updateDisplay((LeafTile) parent.getNE());
				}
			}
			// Swap UP
			else if (userInput.equals("su")) {
				// Find current tile's parent
				InternalTile parent = g.findParent(g.walker);

				parent.swap("su", g.walker.getLocation());

			}
			// Swap DOWN
			else if (userInput.equals("sd")) {
				// Find current tile's parent
				InternalTile parent = g.findParent(g.walker);

				parent.swap("sd", g.walker.getLocation());
			}
			// Swap LEFT
			else if (userInput.equals("sl")) {
				// Find current tile's parent
				InternalTile parent = g.findParent(g.walker);

				parent.swap("sl", g.walker.getLocation());

			}
			// Swap RIGHT
			else if (userInput.equals("sr")) {
				// Find current tile's parent
				InternalTile parent = g.findParent(g.walker);

				parent.swap("sr", g.walker.getLocation());

			} else if (userInput.equals("quit")) {
				System.out.println("Goodbye!");

				// Close scanner
				sc.close();
				break;
			}
			// Invalid user input
			else {
				System.out.println("Invalid input- try again!");
			}
		}

	}

}

