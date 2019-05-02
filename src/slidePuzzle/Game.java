package slidePuzzle;

/**
 * Game represents a sliding tile game. The object of the game is for a 
 * user to help a dog walker get to the dog by performing operations
 * on the sliding tiles. This class constructs the game board, places 
 * the dog and dog walker on the board, and prints the board to the 
 * console. The game board is constructed from a quadtree (referred to
 * as a tile). This class holds the root tile and uses the root to find
 * the parent of a tile in one of its methods. 
 * @author Hindes, Kelley, Pohl, Weiss
 *
 */
public interface Game {
	
	/**
	 * Creates a quadtree with Internal and Leaf Tiles based on values
	 * for the maximum and minimum tile depth. The quadtree is created
	 * with a randomized number of splits on the leaf tiles. 
	 * @return root of quadtree
	 */
	public Tile createTree();

	/**
	 * Randomly places the dog in a
	 * tile
	 * @param root of quadtree
	 * @return the tile in which the dog
	 * was placed
	 */
	public Tile placeDog(Tile root);
	
	/**
	 * Randomly places the walker in a 
	 * tile
	 * @param root of quadtree
	 * @return the tile in which the walker
	 * was placed
	 */
	public Tile placeWalker(InternalTile root);
	
	/**
	 * Updates display based on depth and starting points of leaf
	 * @param leaf
	 */
	public void updateDisplay(LeafTile leaf);
	
	/**
	 * Helper method for console printing
	 */
	public void populateDis();	
	
	/**
	 * Traverses the tree and prints the game board
	 */
	public void printBoard();
	
	
	/**
	 * Clears display on console.
	 */
	public void wipeDisplay();
	
	/**
	 * Recursively calls updateDisplay on all children leaf tiles 
	 * of the input param tile.
	 * @param tile - the tile to be traversed
	 */
	public void traverseTree(Tile tile);
	
	
	/**
	 * Finds the parent tile of any tile
	 * @param toFind the tile of which to find the parent 
	 * @param startingTile either the root or some tile above toFind in 
	 * quadtree hierarchy
	 * @return InternalTile the parent
	 */
	public InternalTile findParent(Tile toFind, Tile startingTile);
	
}
