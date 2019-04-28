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
		LeafTile retTile = new LeafTile(this.depth, this.location, 0, 0);

		// Set grandparent's child equal to the new leaf node
		if (this.location == 1) {
			grandparent.setNE(retTile);
		} else if (this.location == 2) {
			grandparent.setNW(retTile);
		} else if (this.location == 2) {
			grandparent.setSW(retTile);
		} else {
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
		
		//TODO inserted by naomi [Apr 28, 2019, 12:39:45 PM]
		// Update leaf tile coordinates

		// Update leaf tile locations
		if (this.getNE().isLeaf()) {
			((LeafTile) this.getNE()).setLocation(1);
		} else {
			((InternalTile) this.getNE()).setLocation(1);
		}

		if (this.getNW().isLeaf()) {
			((LeafTile) this.getNW()).setLocation(2);
		} else {
			((InternalTile) this.getNW()).setLocation(2);
		}

		if (this.getSW().isLeaf()) {
			((LeafTile) this.getSW()).setLocation(3);
		} else {
			((InternalTile) this.getSW()).setLocation(3);
		}

		if (this.getSE().isLeaf()) {
			((LeafTile) this.getSE()).setLocation(4);
		} else {
			((InternalTile) this.getSE()).setLocation(4);
		}

		// Returns this tile
		return null;
	}

	@Override
	public InternalTile split() {
		// Impossible for internal tile
		return this;
	}

	@Override
	public void swap(String s, int currentLocation) {
		// If the two nodes are children of this node, SWAP NORMALLY
		// Swap up or down
		if ((s.equals("su") && (currentLocation == 3 || currentLocation == 4))
				|| (s.equals("sd") && (currentLocation == 1 || currentLocation == 2))) {
			if (currentLocation == 3 || currentLocation == 2) {
				Tile NW = this.getNW();
				Tile SW = this.getSW();
				this.setNW(SW);
				this.setSW(NW);

				// Update locations
				if (this.getNW().isLeaf()) {
					((LeafTile) this.getNW()).setLocation(2);
				} else {
					((InternalTile) this.getNW()).setLocation(2);
				}

				if (this.getSW().isLeaf()) {
					((LeafTile) this.getSW()).setLocation(3);
				} else {
					((InternalTile) this.getSW()).setLocation(3);
				}
				
				//TODO inserted by naomi [Apr 28, 2019, 12:43:17 PM]
				// Update leaf coordinates

			} else {
				Tile NE = this.getNE();
				Tile SE = this.getSE();
				this.setNE(SE);
				this.setSE(NE);

				// Update locations
				if (this.getNE().isLeaf()) {
					((LeafTile) this.getNE()).setLocation(1);
				} else {
					((InternalTile) this.getNE()).setLocation(1);
				}

				if (this.getSE().isLeaf()) {
					((LeafTile) this.getSE()).setLocation(4);
				} else {
					((InternalTile) this.getSE()).setLocation(4);
				}
				
				//TODO inserted by naomi [Apr 28, 2019, 12:43:17 PM]
				// Update leaf coordinates
			}
		}
		// Swap right or left
		else if ((s.equals("sr") && (currentLocation == 2 || currentLocation == 3))
				|| (s.equals("sl") && (currentLocation == 1 || currentLocation == 4))) {
			if (currentLocation == 2 || currentLocation == 1) {
				Tile NW = this.getNW();
				Tile NE = this.getNE();
				this.setNW(NE);
				this.setNE(NW);

				// Update locations
				if (this.getNW().isLeaf()) {
					((LeafTile) this.getNW()).setLocation(2);
				} else {
					((InternalTile) this.getNW()).setLocation(2);
				}

				if (this.getNE().isLeaf()) {
					((LeafTile) this.getNE()).setLocation(1);
				} else {
					((InternalTile) this.getNE()).setLocation(1);
				}
				
				//TODO inserted by naomi [Apr 28, 2019, 12:43:17 PM]
				// Update leaf coordinates
			} else {
				Tile SW = this.getSW();
				Tile SE = this.getSE();
				this.setSW(SE);
				this.setSE(SW);

				// Update locations
				if (this.getSW().isLeaf()) {
					((LeafTile) this.getSW()).setLocation(3);
				} else {
					((InternalTile) this.getSW()).setLocation(3);
				}

				if (this.getSE().isLeaf()) {
					((LeafTile) this.getSE()).setLocation(4);
				} else {
					((InternalTile) this.getSE()).setLocation(4);
				}
				
				//TODO inserted by naomi [Apr 28, 2019, 12:43:17 PM]
				// Update leaf coordinates

			}
		}
		
		else {
			System.out.println("Swap is too complicated :/ Try again!");
		}
	}

	@Override
	public boolean isLeaf() {
		// Always returns false
		return false;
	}
}

