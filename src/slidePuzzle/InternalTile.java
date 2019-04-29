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
	public LeafTile merge(InternalTile parent, InternalTile grandparent) {
		// Turn current node into a leaf node
		LeafTile retTile = new LeafTile(this.depth, this.location, ((LeafTile) this.getNW()).getStartRow(), ((LeafTile) this.getNW()).getStartCol());

		// Set grandparent's child equal to the new leaf node
		if (this.location == 1) {
			grandparent.setNE(retTile);
		} else if (this.location == 2) {
			grandparent.setNW(retTile);
		} else if (this.location == 3) {
			grandparent.setSW(retTile);
		} else if (this.location == 4) {
			grandparent.setSE(retTile);
		}

		return retTile;
	}

	@Override
	public Tile rotate(InternalTile parent, int location) {
		
		if (location == 1) {
			return this.getSW();
		}
		else if (location == 2) {
			return this.getSE();
		}
		else if (location == 3) {
			return this.getNE();
		}
		else {
			return this.getNW();
		}
		
	}

	@Override
	public InternalTile split(InternalTile i) {
		// Impossible for internal tile
		return this;
	}

	@Override
	public LeafTile swap(String s, Tile root) {
		return null;
	}

	@Override
	public boolean isLeaf() {
		// Always returns false
		return false;
	}
}
