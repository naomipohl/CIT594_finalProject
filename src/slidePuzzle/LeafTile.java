package slidePuzzle;

public class LeafTile implements Tile {
	private int depth;
	private int location;
	private int startRow;
	private int startCol;

	public LeafTile(int depth, int location, int parentStartRow, int parentStartCol) {
		this.depth = depth;
		this.location = location;
		switch (location) {
		case 1:
			this.startRow = parentStartRow;
			this.startCol = parentStartCol + depth;
			break;
		case 2:
			this.startRow = parentStartRow;
			this.startCol = parentStartCol;
			break;
		case 3:
			this.startRow = parentStartRow + depth;
			this.startCol = parentStartCol;
			break;
		case 4:
			this.startRow = parentStartRow + depth;
			this.startCol = parentStartCol + depth;
			break;
		}
	}

	public int getStartRow() {
		return startRow;
	}

	public int getStartCol() {
		return startCol;
	}

	public int getDepth() {
		return depth;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int i) {
		this.location = i;
	}

	/**
	 * Merges four sibling tiles into a single leaf tile. The parent internal tile
	 * of the four sibling tiles becomes a leaf tile of the same depth and at same
	 * location. Merge will not be performed if current depth >= maxDepth. This
	 * error checking is done in main method.
	 * 
	 * @return new leaf tile
	 */
	public LeafTile merge(InternalTile parent, InternalTile grandparent, LeafTile l) {
		// call merge on parent and update reference
		// from grandparent
		return parent.merge(parent, grandparent, l);
	}

	/**
	 * Rotate is a method called only on internal tiles.
	 */
	public Tile rotate(InternalTile parent, int location) {
		return parent.rotate(parent, location);
	}

	public boolean isLeaf() {
		return true;
	}

	/**
	 * Split divides tile into four leaf tiles. If an internal tile is split, that
	 * internal tile is returned. If a leaf tile is split, the parent of that
	 * original leaf tile is returned. It will be grandparent to the four new leaf
	 * tiles. Split will not be performed if current depth <= minDepth. Error
	 * checking will be done in main method.
	 * 
	 * @return Parent tile of original leaf tile (Grandparent of new four leaf
	 *         tiles)
	 */
	public InternalTile split(InternalTile parent) {

		// Create tile's 4 children
		LeafTile NE = new LeafTile((this.depth / 2), 1, this.startRow, this.startCol);
		LeafTile NW = new LeafTile((this.depth / 2), 2, this.startRow, this.startCol);
		LeafTile SW = new LeafTile((this.depth / 2), 3, this.startRow, this.startCol);
		LeafTile SE = new LeafTile((this.depth / 2), 4, this.startRow, this.startCol);

		// Turn current node into an internal node
		InternalTile tile = new InternalTile(NE, NW, SW, SE);
		tile.setDepth(this.depth);
		tile.setLocation(this.location);

		if (this.location == 1) {
			parent.setNE(tile);
		} else if (this.location == 2) {
			parent.setNW(tile);
		} else if (this.location == 3) {
			parent.setSW(tile);
		} else if (this.location == 4) {
			parent.setSE(tile);
		}

		return parent;
	}

	@Override
	public LeafTile swap(String s, Tile root) {
		if (s.equals("su")) {
			Tile l = findNeighbor(root, this.getStartRow() - this.getDepth(), this.getStartCol());
			return (LeafTile) l;
		} else if (s.equals("sd")) {
			Tile l = findNeighbor(root, this.getStartRow() + this.getDepth(), this.getStartCol());
			return (LeafTile) l;
		} else if (s.equals("sr")) {
			Tile l = findNeighbor(root, this.getStartRow(), this.getStartCol() + this.getDepth());
			return (LeafTile) l;
		} else {
			Tile l = findNeighbor(root, this.getStartRow(), this.getStartCol() - this.getDepth());
			return (LeafTile) l;
		}
	}

	private Tile findNeighbor(Tile tile, int rowToFind, int colToFind) {
		Tile nw = null;
		Tile ne = null;
		Tile sw = null;
		Tile se = null;

		if (tile.isLeaf()) {
			if (((LeafTile) tile).getStartRow() == rowToFind && ((LeafTile) tile).getStartCol() == colToFind) {
				return tile;
			}
		} else {
			nw = findNeighbor(((InternalTile) tile).getNW(), rowToFind, colToFind);
			ne = findNeighbor(((InternalTile) tile).getNE(), rowToFind, colToFind);
			sw = findNeighbor(((InternalTile) tile).getSW(), rowToFind, colToFind);
			se = findNeighbor(((InternalTile) tile).getSE(), rowToFind, colToFind);
		}

		if (nw != null)
			return nw;
		if (ne != null)
			return ne;
		if (sw != null)
			return sw;
		if (se != null)
			return se;
		return null;

	}

}
