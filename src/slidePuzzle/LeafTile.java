package slidePuzzle;

public class LeafTile implements Tile {
	private int depth;
	private int location;
	private int startRow;
	private int startCol;
	
	public LeafTile(int depth, int location, int parentStartRow, int parentStartCol) {
		this.depth = depth;
		this.location = location;
		switch(location) {
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

	/**
	 * Merges four sibling tiles into a single leaf tile. The parent
	 * internal tile of the four sibling tiles becomes a leaf tile of
	 * the same depth and at same location. 
	 * Merge will not be performed if current depth >= maxDepth. This 
	 * error checking is done in main method. 
	 * @return new leaf tile 
	 */
	public LeafTile merge(InternalTile parent) {
		
		InternalTile grandparent = this.findParent(parent); //parent = parent of current leaf tile
		return parent.merge(grandparent);		//call merge on parent and update reference
								//  from grandparent
	}

	/**
	 * OLD IMPLEMENTATION
	 */
	/**
	public Tile merge(InternalTile root) {
		
		LeafTile current = this; 	//current leaf tile that action is being called on
		InternalTile parent = this.findParent(root); 	//parent of current leaf tile
		InternalTile grandparent = findParent(parent); 	//grandparent of current leaf tile
		LeafTile merged; 			//new merged leaf tile
		
		//find start coordinates of merged leaf (i.e., coordinates of leaf from location 2)
		int mergedStartRow = -1;
		int mergedStartCol = -1;

		if (current.getLocation() == 1) {
			mergedStartRow = current.getStartRow();
			mergedStartCol = current.getStartCol() - current.getDepth();
		} 
		else if (current.getLocation() == 2) {
			mergedStartRow = current.getStartRow();
			mergedStartCol = current.getStartCol();
		} 
		else if(current.getLocation() == 3) {
			mergedStartRow = current.getStartRow() - current.getDepth();
			mergedStartCol = current.getStartCol();
		} 
		else { //current at location 4
			mergedStartRow = current.getStartRow() - current.getDepth();
			mergedStartCol = current.getStartCol() - current.getDepth();
		} 
		
		
		//find start coordinates of grandparent 
		int gpStartRow = -1;
		int gpStartCol = -1;		

		if (parent.getLocation() == 1) {
			gpStartRow = mergedStartRow;
			gpStartCol = mergedStartCol - parent.getDepth();
		} 
		else if (parent.getLocation() == 2) {
			gpStartRow = mergedStartRow;
			gpStartCol = mergedStartCol;
		} 
		else if (parent.getLocation() == 3) {
			gpStartRow = mergedStartRow - parent.getDepth();
			gpStartCol = mergedStartCol;
		} 
		else { //parent at location 4
			gpStartRow = mergedStartRow - parent.getDepth();
			gpStartCol = mergedStartCol - parent.getDepth();
		}   
		
		//create new merged LeafTile in location and at depth of original leaf tile parent
		merged = new LeafTile(parent.getDepth(), parent.getLocation(), gpStartRow, gpStartCol);
		
		
		//create reference to new merged leaf tile from grandparent
		if (parent.getLocation() == 1) {
			grandparent.setNE(merged);
		} else if (parent.getLocation() == 2) {
			grandparent.setNW(merged);
		} else if (parent.getLocation() == 3) {
			grandparent.setSW(merged);
		} else {
			grandparent.setSE(merged);
		}
				
		return merged;
	}
	*/
	
	/**
	 * Rotate is a method called only on internal tiles.
	 */
	public Tile rotate() {
		return null;
	}

	
	public boolean isLeaf() {
		return true;
	}

	
	/**
	 * Split divides tile into four leaf tiles. If an internal tile is split, 
	 * that internal tile is returned. If a leaf tile is split, the parent
	 * of that original leaf tile is returned. It will be grandparent to the
	 * four new leaf tiles.  
	 * Split will not be performed if current depth <= minDepth. Error checking 
	 * will be done in main method. 
	 * 
	 * @return Parent tile of original leaf tile (Grandparent of new four leaf tiles)
	 */
	public InternalTile split(InternalTile parent) {
		
		// Create tile's 4 children
		LeafTile NE = new LeafTile((this.depth / 2), 1, this.startRow, this.startCol);
		LeafTile NW = new LeafTile((this.depth / 2), 2, this.startRow, this.startCol);
		LeafTile SW = new LeafTile((this.depth / 2), 3, this.startRow, this.startCol); 
		LeafTile SE = new LeafTile((this.depth / 2), 4, this.startRow, this.startCol); 
		
		// Turn current node into an internal node
		InternalTile tile = new InternalTile(NE, NW, SE, SW);
		tile.setDepth(this.depth);
		tile.setLocation(this.location);
		
		if (this.location == 1) {
			parent.setNE(tile);
		}
		else if (this.location == 2) {
			parent.setNW(tile);
		}
		else if (this.location == 3) { 
			parent.setSW(tile);
		}
		else {
			parent.setSE(tile); 
		}
		
		return parent;
	}


	
	
	//OLD IMPLEMENTATION
	/**
	public InternalTile split(Tile root) {
		// Create tile's 4 children
		LeafTile NE = new LeafTile((this.depth / 2), 1, this.startRow, this.startCol);
		LeafTile NW = new LeafTile((this.depth / 2), 2, this.startRow, this.startCol);
		LeafTile SE = new LeafTile((this.depth / 2), 3, this.startRow, this.startCol);
		LeafTile SW = new LeafTile((this.depth / 2), 4, this.startRow, this.startCol);
		
		// Turn current node into an internal node
		InternalTile tile = new InternalTile(NE, NW, SE, SW);
		tile.setDepth(this.depth);
		tile.setLocation(this.location);
		
		// If we are not splitting the first time,
		// traverse the tree until we find the parent of the node
		if (root instanceof InternalTile) {
			InternalTile parent = this.findParent((InternalTile) root);
			
			if (parent != null) {
				// Set the child appropriately
				if (this.location == 1) {
					parent.setNE((Tile) tile);
				}
				else if (this.location == 2) {
					parent.setNW((Tile) tile);
				}
				else if (this.location == 3) {
					parent.setSE((Tile) tile);
				}
				else {
					parent.setSW((Tile) tile);
				}
			}
			return parent;
		}
		
		// If we are splitting the first time
		else {
			return tile;
		}
		
	}
	*/
	
	/**
	 * Helper method to traverse tree and find parent node
	 * @param current
	 * @return
	 */
	private InternalTile findParent(InternalTile current) {
		/* //OLD IMPLEMENTATION: SEE NEW BELOW
		
		// Check whether we have found the parent
		if ((current.getDepth() == this.depth * 2) && 
				(current.getLocation() == this.parentLocation)) {
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
		//return null;
		*/
		
		InternalTile neParent = null;
		InternalTile nwParent = null;
		InternalTile seParent = null;
		InternalTile swParent = null;
		//If any of the child leaves==caller leaf, return this
				if(current.getNE().equals(this)||current.getNW().equals(this)||
				   current.getSE().equals(this)||current.getSW().equals(this)){
					System.out.println("getNE: " + current.getNE());
					System.out.println("getNW: " + current.getNW());
					System.out.println("getSE: " + current.getSE());
					System.out.println("getSW: " + current.getSW());
					System.out.println("this is: " + this);
					return current;
				}
				//recursively store the search result (possibly null) findParent within each child	
				else {
					System.out.println("here");
					if(current.getNE() instanceof InternalTile){
						neParent = this.findParent((InternalTile)current.getNE());
					}
					if(current.getNW() instanceof InternalTile){
						nwParent = this.findParent((InternalTile)current.getNW());
					}			
					if(current.getSE() instanceof InternalTile){
						seParent = this.findParent((InternalTile)current.getSE());
					}
					if(current.getSW() instanceof InternalTile){
						swParent = this.findParent((InternalTile)current.getSW());
					}				
				
				//if found a non-null matching parent: 
				if (neParent != null) return neParent;
				if (nwParent != null) return nwParent;
				if (seParent != null) return seParent;
				if (swParent != null) return swParent;	
					   
				//if no child and no recursive search resulted in a matching parent:
				return null;
				}
		
		
	}
	
	public void swap(String s) {
		// TODO Auto-generated method stub
		return;
	}

}


