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
		LeafTile retTile = new LeafTile(this.depth, this.location, 0, 0);
		
		// set grandparent's child equal to the new leaf node
		if (this.location == 1) {
			grandparent.setNE(retTile);
		}
		else if (this.location == 2) {
			grandparent.setNW(retTile);
		}
		else if (this.location == 2) {
			grandparent.setSW(retTile);
		}
		else {
			grandparent.setSE(retTile);
		}
		
		return retTile;
	}

	@Override
	public Tile rotate() {
		// Shift every child over by 2
		Tile NE = this.getNE();
		Tile NW = this.getNW();
		Tile SW = this.getSW();
		Tile SE = this.getSE();
		
		this.setNE(SW);
		this.setNW(SE);
		this.setSW(NE);
		this.setSE(NW);
		
		// update leaf tile coordinates
		
		
		// update leaf tile locations
		if (this.getNE().isLeaf()) {
			((LeafTile) this.getNE()).setLocation(1);
		}
		else {
			((InternalTile) this.getNE()).setLocation(1);
		}
		
		if (this.getNW().isLeaf()) {
			((LeafTile) this.getNW()).setLocation(2);
		}
		else {
			((InternalTile) this.getNW()).setLocation(2);
		}
		
		if (this.getSW().isLeaf()) {
			((LeafTile) this.getSW()).setLocation(3);
		}
		else {
			((InternalTile) this.getSW()).setLocation(3);
		}
		
		if (this.getSE().isLeaf()) {
			((LeafTile) this.getSE()).setLocation(4);
		}
		else {
			((InternalTile) this.getSE()).setLocation(4);
		}
		
		// returns this tile
		return null;
	}

	@Override
	public InternalTile split() {
		// impossible for internal tile
		return this;
	}

	@Override
	public void swap(String s) {
		if (s.equals("su")) {
			// swap up
		}
		else if (s.equals("sr")) {
			// swap right
		}
		else if (s.equals("sl")) {
			// swap left
		}
		else {
			// swap down
		}
		// su, sr, sl, sd
		// check that tiles are of same size?
		// if the two nodes are children of this node,
		// swap as normal
		// else, go to grandparent, find cousins
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
}
