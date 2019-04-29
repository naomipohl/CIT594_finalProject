package slidePuzzle;

import java.util.Scanner;

public class PlayGame {

	public static void main(String[] args) {
		// Create game board
		GameBoard g = new GameBoard(16, 1);

		// Populate the board
		g.populateDis();

		// Place the dog and the walker
		g.placeDog((InternalTile) g.root);
		g.placeWalker((InternalTile) g.root);

		// Initialize scanner
		Scanner sc = new Scanner(System.in);

		// Print welcome message
		System.out.println(
				"Welcome to the sliding tile game!\n" + "You are currently in the location marked with a 'W',\n"
						+ "and your objective is to get to the dog tile, which is"
						+ "marked with a 'D'. You can perform the following operations:\n"
						+ "Merge ('merge'), Split('split'), Swap ('su', 'sd', 'sl', or 'sr'),"
						+ "and Rotate ('rotate'). \nYou can quit at any point by typing 'quit'.\n"
						+ "Let's get started- catch that dog!");

		// Print the initial display
		g.traverseTree(g.root);
		g.printBoard();

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
				if (g.walker.getDepth() == g.maxDepth / 2) {
					// Find current tile's parent & grandparent
					InternalTile parent = g.findParent(g.walker, g.root);

					// Check that we are merging leaf tiles only
					if (!parent.getNE().isLeaf() || !parent.getNW().isLeaf() || !parent.getSW().isLeaf()
							|| !parent.getSE().isLeaf()) {
						System.out.println("All tiles being merged must be of same size");
					} else {
						sc.close();
						System.out.println("You won!");
						break;
					}
				} else {
					// Find current tile's parent & grandparent
					InternalTile parent = g.findParent(g.walker, g.root);

					// Check that we are merging leaf tiles only
					if (!parent.getNE().isLeaf() || !parent.getNW().isLeaf() || !parent.getSW().isLeaf()
							|| !parent.getSE().isLeaf()) {
						System.out.println("All tiles being merged must be of same size");
					} else {
						InternalTile grandparent = g.findParent(parent, g.root);

						// Perform the merge on the leaf tile
						LeafTile m = g.walker.merge(parent, grandparent, g.walker);
						System.out.println(m);

						g.walker = m;

						// Update the display
						g.traverseTree(g.root);
						g.printBoard();
						g.wipeDisplay();
					}
				}
			}
			// Rotate
			else if (userInput.equals("rotate")) {
				// Find current tile's parent
				InternalTile parent = g.findParent(g.walker, g.root);

				// Perform the rotation
				Tile l = g.walker.rotate(parent, g.walker.getLocation());

				if (l.isLeaf()) {
					g.walker = (LeafTile) l;
				} else {
					g.walker = (LeafTile) ((InternalTile) l).getNW();
				}

				// Update the display
				g.traverseTree(g.root);
				g.printBoard();
				g.wipeDisplay();
			}
			// Split
			else if (userInput.equals("split")) {
				// If trying to split min size tiles, throw error
				if (g.walker.getDepth() == g.minDepth + 1) {
					System.out.println("Tiles too small- cannot perform split");
				} else {
					InternalTile parent = g.findParent(g.walker, g.root);
					// Split the current tile
					InternalTile t = g.walker.split(parent);
					System.out.println(t.getNE());
					System.out.println(t.getNW());
					System.out.println(t.getSW());
					System.out.println(t.getSE());

					if (g.walker.getLocation() == 1) {
						g.walker = (LeafTile) ((InternalTile) t.getNE()).getNW();
					}

					else if (g.walker.getLocation() == 2) {
						g.walker = (LeafTile) ((InternalTile) t.getNW()).getNW();
					}

					else if (g.walker.getLocation() == 3) {
						g.walker = (LeafTile) ((InternalTile) t.getSW()).getNW();
					}

					else {
						g.walker = (LeafTile) ((InternalTile) t.getSE()).getNW();
					}

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
