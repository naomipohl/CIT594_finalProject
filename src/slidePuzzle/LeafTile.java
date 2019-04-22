package slidePuzzle;

public class LeafTile implements Tile {
	private int depth;
	private int location;
	private int parentLocation;
	
	public LeafTile(int depth, int location, int parentLocation) {
		this.depth = depth;
		this.location = location;
		this.parentLocation = parentLocation;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public int getLocation() {
		return location;
	}
	
	public int getParentLocation() {
		return parentLocation;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Tile merge() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void rotate() {
		// TODO Auto-generated method stub
		return;
	}
	
	InternalTile split(Tile root) {
		// Create tile's 4 children
		LeafTile NE = new LeafTile((this.depth / 2), 1, this.location);
		LeafTile NW = new LeafTile((this.depth / 2), 2, this.location);
		LeafTile SE = new LeafTile((this.depth / 2), 3, this.location);
		LeafTile SW = new LeafTile((this.depth / 2), 4, this.location);
		
		// Turn current node into an internal node
		InternalTile tile = new InternalTile(NE, NW, SE, SW);
		
		// If we are not splitting the first time,
		// traverse the tree until we find the parent of the node
		if (root instanceof InternalTile) {
			InternalTile parent = this.findParent((InternalTile) root);
			
			if (parent != null) {
				// Set the child appropriately
				if (this.location == 1) {
					parent.setNE((Tile) tile);
					System.out.println("Set NE");
				}
				else if (this.location == 2) {
					parent.setNW((Tile) tile);
					System.out.println("Set NW");
				}
				else if (this.location == 3) {
					parent.setSE((Tile) tile);
					System.out.println("Set SE");
				}
				else {
					parent.setSW((Tile) tile);
					System.out.println("Set SW");
				}
				
			}
			return parent;
		}
		
		// If we are splitting the first time
		else {
			return tile;
		}
		
	}
	
	/**
	 * Helper method to traverse tree and find parent node
	 * @param current
	 * @return
	 */
	private InternalTile findParent(InternalTile current) {
		// Check whether we have found the parent
		if ((current.getDepth() == this.depth * 2) && (current.getLocation() == this.parentLocation)) {
			return current;
		}
		
		// Recursively search the tree
		if (current.getNE() instanceof InternalTile) {
			findParent((InternalTile) current.getNE());
		}
		if (current.getNW() instanceof InternalTile) {
			findParent((InternalTile) current.getNW());
		}
		if (current.getSE() instanceof InternalTile) {
			findParent((InternalTile) current.getSE());
		}
		if (current.getSW() instanceof InternalTile) {
			findParent((InternalTile) current.getSW());
		}
		
		// If parent wasn't found, return null
		return null;
	}
	
	private void swap() {
		// TODO Auto-generated method stub
		return;
	}

}

