package slidePuzzle;
/**
 * Represents game board in sliding tile game. Contains
 * methods to setup game and print board to user.
 * @author Hindes, Kelley, Pohl, Weiss
 *
 */
public interface Game {
	
	/**
	 * Creates a random tree
	 * @return root of Quadtree
	 */
	public Tile createTree();
	
	
	/**
	 * Randomly places the dog in a
	 * tile
	 * @return the tile in which the dog
	 * was placed
	 */
	public Tile placeDog();
	
	/**
	 * Randomly places the walker in a 
	 * tile
	 * @return the tile in which the walker
	 * was placed
	 */
	public Tile placeWalker();
	
	/**
	 * Updates a 2D array of strings that represent display
	 * @param leaf
	 */
	public void updateDisplay(LeafTile leaf);
	
	/**
	 * Traverses the 2D array display into a string representation
	 * and prints the game board to console
	 * @param board
	 */
	public void printBoard();
	
}
