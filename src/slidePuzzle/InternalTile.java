package slidePuzzle;

public class InternalTile implements Tile {

	private Tile NE;
	private Tile NW;
	private Tile SE;
	private Tile SW;
	private int depth;
	private int location;

	public InternalTile(Tile NE, Tile NW, Tile SE, Tile SW) {
		this.setNE(NE);
		this.setNW(NW);
		this.setSE(SE);
		this.setSW(SW);
	}

	/* Getters & Setters */
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Tile getSE() {
		return SE;
	}

	public void setSE(Tile sE) {
		SE = sE;
	}

	public Tile getNE() {
		return NE;
	}

	public void setNE(Tile nE) {
		NE = nE;
	}

	public Tile getNW() {
		return NW;
	}

	public void setNW(Tile nW) {
		NW = nW;
	}

	public Tile getSW() {
		return SW;
	}

	public void setSW(Tile sW) {
		SW = sW;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	/* Interface methods */
	@Override
	public LeafTile merge(InternalTile grandparent) {
		// turn current node into a leaf node
		// find current node's parent
		// set grandparent's child equal to the new leaf node
		
		return null;
	}

	@Override
	public Tile rotate() {
		// shift every child over by 1
		// update leaf tile coordinates
		// update leaf tile locations
		
		// returns null in leafTile class
		return null;
	}

	@Override
	public InternalTile split() {
		// impossible for internal tile
		return this;
	}

	@Override
	public void swap(String s) {
		// su, sr, sl, sd
		// make sure not swapping off board
		// if the two nodes are children of this node,
		// swap as normal
		// else, go to grandparent, find cousins
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}

