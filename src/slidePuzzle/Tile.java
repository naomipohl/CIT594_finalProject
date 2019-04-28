package slidePuzzle;
/**
 * Tile represents a spatial unit on the sliding puzzle. Tiles
 * can be merged, rotated, split and swapped. 
 * @author Hindes, Kelley, Pohl, Weiss
 *
 */
public interface Tile {
	
	
	/**
	 * Merges four sibling tiles into a single tile. If merge() is called on 
	 * an internal tile, a leaf tile of the same depth and location is 
	 * returned. If merge() is called on a leaf tile, the parent
	 * internal tile of the four sibling tiles becomes a leaf tile and that
	 * leaf tile is returned. 
	 * Merge will not be performed if current depth >= maxDepth. This 
	 * error checking is done in main method. 
	 * @return new leaf tile created by merging
	 */
	public LeafTile merge(InternalTile parent, InternalTile grandparent);
	
	/**
	 * Rotates four sibling tiles 180 degrees. 
	 * Test for rotate will verify tile's location/coordinates.
	 * @return the tile on which rotate is called in new rotated position
	 */
	public Tile rotate();

	/**
	 * Split divides tile into four leaf tiles. If an internal tile is split, 
	 * that internal tile is returned. If a leaf tile is split, the internal
	 * tile that is parent of the new leaf tiles is returned. The reference 
	 * to the original leaf tile (on which split is called) must be updated. 
	 * Split will not be performed if current depth <= minDepth. Error checking 
	 * will be done in main method. 
	 * 
	 * @return Parent of new four leaves
	 */
	public InternalTile split(InternalTile parent);
	
	/**
	 * Swap exchanges the location of the tile with an adjacent tile. The two 
	 * tiles may or may not have a common parent, however they must be 
	 * adjacent spatially and must have the same depth. Test will verify that 
	 * the tile in its swapped condition has correct location and parent.
	 * @param s String input by user. "sr" moves the tile to the right. 
	 * "sl" moves the tile to the left. "su" moves the tile up. "sd" moves the 
	 * tile down.
	 */
	public void swap(String s);

	/**
	 * Returns a boolean value based on data type
	 * @return true if tile is a LeafTile and false if tile is an InternalTile
	 */
	public boolean isLeaf();
}
