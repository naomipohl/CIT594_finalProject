package slidePuzzle;

import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) {
		// Create game board
		GameBoard g = new GameBoard(16, 1);

		// Populate the board
		g.populateDis();

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
					InternalTile parent = g.findParent(g.walker, g.root);
					InternalTile grandparent = g.findParent(parent, g.root);

					// Perform the merge on the leaf tile
					LeafTile l = g.walker.merge(parent, grandparent);

					// Update the display
					g.traverseTree(g.root);
					g.printBoard();
					g.wipeDisplay();
				}
			}
			// Rotate
			else if (userInput.equals("rotate")) {
				// Find current tile's parent
				InternalTile parent = g.findParent(g.walker, g.root);

				// Perform the rotation
				Tile l = g.walker.rotate(parent, g.walker.getLocation());

				g.walker = (LeafTile) l;

				// Update the display
				g.traverseTree(g.root);
				g.printBoard();
				g.wipeDisplay();
			}
			// Split
			else if (userInput.equals("split")) {
				// If trying to split min size tiles, throw error
				if (g.walker.getDepth() == g.minDepth) {
					System.out.println("Tiles too small- cannot perform split");
				} else {
					InternalTile parent = g.findParent(g.walker, g.root);
					// Split the current tile
					g.walker.split(parent);

					// Update the display
					g.traverseTree(g.root);
					g.printBoard();
					g.wipeDisplay();
				}
			}
			// Swap UP
			else if (userInput.equals("su") && g.walker.getStartRow() - g.walker.getDepth() >= 0) {
				// Find current tile's neighbor
				LeafTile l = g.walker.swap("su", g.root);

				if (l.getDepth() == g.walker.getDepth()) {
					g.walker = l;

					// Update the display
					g.traverseTree(g.root);
					g.printBoard();
					g.wipeDisplay();
				} else {
					System.out.println("Must swap tiles of same width");
				}

			}
			// Swap DOWN
			else if (userInput.equals("sd") && g.walker.getStartRow() + g.walker.getDepth() <= g.maxDepth - 1) {
				// Find current tile's neighbor
				LeafTile l = g.walker.swap("sd", g.root);

				if (l.getDepth() == g.walker.getDepth()) {
					g.walker = l;

					// Update the display
					g.traverseTree(g.root);
					g.printBoard();
					g.wipeDisplay();
				} else {
					System.out.println("Must swap tiles of same width");
				}
			}
			// Swap LEFT
			else if (userInput.equals("sl") && g.walker.getStartCol() - g.walker.getDepth() >= 0) {
				// Find current tile's neighbor
				LeafTile l = g.walker.swap("sl", g.root);

				if (l.getDepth() == g.walker.getDepth()) {
					g.walker = l;

					// Update the display
					g.traverseTree(g.root);
					g.printBoard();
					g.wipeDisplay();
				} else {
					System.out.println("Must swap tiles of same width");
				}
			}
			// Swap RIGHT
			else if (userInput.equals("sr") && g.walker.getStartCol() + g.walker.getDepth() <= g.maxDepth - 1) {
				// Find current tile's neighbor
				LeafTile l = g.walker.swap("sr", g.root);

				if (l.getDepth() == g.walker.getDepth()) {
					g.walker = l;

					// Update the display
					g.traverseTree(g.root);
					g.printBoard();
					g.wipeDisplay();
				} else {
					System.out.println("Must swap tiles of same width");
				}

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


