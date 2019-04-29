package slidePuzzle;

public class InternalTile implements Tile {

	private Tile NE;
	private Tile NW;
	private Tile SW;
	private Tile SE;
	private int depth;
	private int location;

	public InternalTile(Tile NE, Tile NW, Tile SW, Tile SE) {
		this.setNE(NE);
		this.setNW(NW);
		this.setSW(SW);
		this.setSE(SE);
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
	public LeafTile merge(InternalTile parent, InternalTile grandparent, LeafTile l) {
		int parentSR = 0;
		int parentSC = 0;
		if (l.getLocation() == 1) {
			parentSR = l.getStartRow();
			parentSC = l.getStartCol() - l.getDepth();
		}
		else if (l.getLocation() == 2) {
			parentSR = l.getStartRow();
			parentSC = l.getStartCol();
		}
		else if (l.getLocation() == 3) {
			parentSR = l.getStartRow() - l.getDepth();
			parentSC = l.getStartCol();
		}
		else {
			parentSR = l.getStartRow() - l.getDepth();
			parentSC = l.getStartCol() - l.getDepth();
		}
		
		int startRow = 0;
		int startCol = 0;
		if (parent.getLocation() == 1) {
			startRow = parentSR;
			startCol = parentSC - parent.getDepth();
		}
		else if (parent.getLocation() == 2) {
			startRow = parentSR;
			startCol = parentSC;
		}
		else if (parent.getLocation() == 3) {
			startRow = parentSR - parent.getDepth();
			startCol = parentSC;
		}
		else {
			startRow = parentSR - parent.getDepth();
			startCol = parentSC - parent.getDepth();
		}
		
		// Turn current node into a leaf node
		LeafTile retTile = new LeafTile(this.depth, this.location, startRow, startCol);

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
