package slidePuzzle;

public class GameBoard implements Game{
	
	public Tile root;
	public int maxDepth;
	public int minDepth;
	public LeafTile walker;
	public LeafTile dog;
	public String[][] dis;
	
	public GameBoard(int maxDepth, int minDepth) {
		this.maxDepth = maxDepth;
		this.minDepth = minDepth;
		this.root = createTree();
		this.dis = new String[maxDepth + 1][maxDepth];
	}
	
	private LeafTile findNeighbor(Tile tile, int rowToFind, int colToFind) {
		if(((LeafTile) tile).getStartRow() == rowToFind && ((LeafTile) tile).getStartCol() == colToFind) {
			return (LeafTile) tile;
		}
		findNeighbor(((InternalTile) tile).getNW());
		findNeighbor(((InternalTile) tile).getNE());
		findNeighbor(((InternalTile) tile).getSW());
		findNeighbor(((InternalTile) tile).getSE());
		return new LeafTile(-1,-1,-1,-1);
	}
	
	@Override
	public Tile createTree() {
		
		
		//initialize an internal tile of size 16 w/ four children size 8
		LeafTile ne8 = new LeafTile(8,1,0,0);
		LeafTile nw8 = new LeafTile(8,2,0,0);
		LeafTile se8 = new LeafTile(8,3,0,0);
		LeafTile sw8 = new LeafTile(8,4,0,0);
		
		
		//System.out.println("ne8 start row is: " + ne8.getStartRow());
		//System.out.println("ne8 start col is: " + ne8.getStartCol());
		
		
		
		
		InternalTile internal16 = new InternalTile(ne8, nw8, se8, sw8);
		internal16.setDepth(16);
		
		//LeafTile internal16 = new LeafTile(16,2,0,0);
		
		//uncomment this!
		
		Tile possibleNE = randomSplits(ne8,internal16);
		
		internal16.setNE(randomSplits(ne8,internal16));
		internal16.setNW(randomSplits(nw8,internal16));
		internal16.setSE(randomSplits(se8,internal16));
		internal16.setSW(randomSplits(sw8,internal16));
		
		
		System.out.println("Finished with random splitting");
		return internal16;
	}
	
	private Tile randomSplits(LeafTile origLeaf, Tile root) {
		
		
		Tile toReturn = origLeaf;
		
		
		//flip a coin to decide whether or not to split
		Random rand = new Random();
		int rand_binary_int = rand.nextInt()%2;
		if (rand_binary_int == 0) {
			System.out.println("We are returning an unsplit leaf!");
			 toReturn = origLeaf;
		}
		else if (rand_binary_int == 1 && ((LeafTile) toReturn).getDepth() > 2) {
			toReturn = ((LeafTile) toReturn).split(toReturn);
		}
		
		if (toReturn instanceof InternalTile && ((InternalTile) toReturn).getDepth() > 2) {
			System.out.println("WE ARE DOING RANDOM SPLITS YAY");
			if(((InternalTile) toReturn).getNE() instanceof LeafTile) {
				((InternalTile)toReturn).setNE(randomSplits(((LeafTile) ((InternalTile)toReturn).getNE()),root));
			}
			
			if(((InternalTile) toReturn).getNW() instanceof LeafTile) {
				((InternalTile)toReturn).setNW(randomSplits(((LeafTile) ((InternalTile)toReturn).getNW()),root));
			}
			if(((InternalTile) toReturn).getSE() instanceof LeafTile) {
				((InternalTile)toReturn).setSE(randomSplits(((LeafTile) ((InternalTile)toReturn).getSE()),root));
			}
			if(((InternalTile) toReturn).getSW() instanceof LeafTile) {
				((InternalTile)toReturn).setSW(randomSplits(((LeafTile) ((InternalTile)toReturn).getSW()),root));
			}
		}
		
		
		//may be either the leaf passed in OR the internal resulting from split
		return toReturn;
	}

// 	@Override
// 	public void populateBoard(Tile t) {
// 		populateBoard(t, 0, 0);
		
// 	}
	
// 	private void populateBoard(Tile root, int rowStart, int colStart){

// 		if(root instanceof InternalTile){ //if internal node just call fcn recursively on children
// 			InternalTile rootInternal = (InternalTile) root;
// 			populateBoard(rootInternal.getNW(), rowStart, colStart);
// 			populateBoard(rootInternal.getNE(), rowStart, colStart + rootInternal.getDepth()/2);
// 			populateBoard(rootInternal.getSE(), rowStart + rootInternal.getDepth()/2, colStart + rootInternal.getDepth()/2);
// 			populateBoard(rootInternal.getSW(), rowStart + rootInternal.getDepth()/2, colStart);
// 		}
		
// 		else{ //if leaf node:
// 			System.out.println("Confirmed that we are at a leaf node");
// 			LeafTile rootLeaf = (LeafTile) root;
// 			System.out.println("casted from tile to a leaf");
		
// 			System.out.print("rowStart is: " + rowStart + "\ncolStart is: " + colStart);
// 			System.out.print("rootleaf depth is: " + rootLeaf.getDepth());
			
			
// 			for (int i = rowStart; i < rowStart + rootLeaf.getDepth(); i++){
// 				for (int j = colStart; j<colStart + rootLeaf.getDepth(); j++){
// 					System.out.println("\ni = " + i + "\nj = " + j);
// 					ordToPrint[i][j] = rootLeaf.getDepth();
// 				}
// 			}
			
// 		}
// 	}
	
	///this will take in a leaf Tile and get is depth and starting points from there 
	public void updateDisplay(LeafTile leaf) {
		int startRow = leaf.getStartRow();
		int startCol = leaf.getStartCol();
		int endRow = startRow + leaf.getDepth();
		int endCol = startCol + leaf.getDepth();
		
		String cell = "   ";
		for(int i = startRow; i < endRow; i++) {
			for(int j = startCol; j < endCol; j++) {
				if((i == endRow -1) && (j == startCol)) {
					if(leaf.getDepth() == 1) {
						if(leaf == dog) cell = "|" + "D\u0332" + "|";
						else if(leaf == walker) cell = "|" + "W\\u0332" + "|";
						else cell = "|_|";
					} 
					else cell = "|__";
				}
				else if((i == endRow -1) && (j == endCol -1)) {
					cell = "__|";
				}
				else if(j == startCol) {
					if(i == startRow && leaf == dog) {
						cell = "|D ";
					}
					else if(i == startRow && leaf == walker) {
						cell = "|W ";
					}
					else cell = "|  ";
				}
				else if(j == endCol -1) {
					cell = "  |";
				}
				else if(i == endRow - 1) {
					cell = "___";
				}
				dis[i][j] = cell;
				cell = "   ";
			}	
		}
	}
	
	public void populateDis() {
		for(int i = 0; i < maxDepth; i++) {
			dis[0][i] = "___";
		}
		
		for(int i = 1; i < maxDepth + 1; i++) {
			for(int j = 0; j < maxDepth; j++) {
				dis[i][j] = "  ";
			}
		}
	}
	
	@Override
	public void printBoard() {
		int index = 0;
		String [] strDis = new String [maxDepth + 1]; //this is harcoded but could take in MaxDepth 
		for(String [] rw : dis) {
			String res = "";
			for(String e : rw) {
				res = res + e;
			}
			strDis[index] = res;
			index++;
		}
		
		for(int i = 0; i < strDis.length; i++) {
			System.out.println(strDis[i]);
		}
	}
	
	@Override
	public Tile placeDog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile placeWalker() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
@Override //need to update interface w/ this new signature
	public InternalTile findParent(Tile toFind, Tile startingTile) {
		InternalTile current = (InternalTile) startingTile;
		InternalTile neParent = null;
		InternalTile nwParent = null;
		InternalTile seParent = null;
		InternalTile swParent = null;
		//If any of the child leaves==caller leaf, return this
		if((current.getNE()!= null && current.getNE().equals(toFind))||
			(current.getNW()!= null && current.getNW().equals(toFind))||
			(current.getSE()!= null && current.getSE().equals(toFind))||
			(current.getSW() != null && current.getSW().equals(toFind))){
					/*System.out.println("getNE: " + current.getNE());
					System.out.println("getNW: " + current.getNW());
					System.out.println("getSE: " + current.getSE());
					System.out.println("getSW: " + current.getSW());
					System.out.println("this is: " + this);
					*/
				return current;
			}
				//recursively store the search result (possibly null) findParent within each child	
		else {
			System.out.println("here");
			if(current.getNE() !=null && current.getNE() instanceof InternalTile){
				neParent = this.findParent(toFind, (InternalTile)current.getNE());
			}
			if(current.getNW() != null && current.getNW() instanceof InternalTile){
				nwParent = this.findParent(toFind, (InternalTile)current.getNW());
			}			
			if(current.getSE() != null && current.getSE() instanceof InternalTile){
				seParent = this.findParent(toFind, (InternalTile)current.getSE());
			}
			if(current.getSW() != null && current.getSW() instanceof InternalTile){
				swParent = this.findParent(toFind, (InternalTile)current.getSW());
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

// 	@Override
// 	public void printBoard(int[][] board) {
// 		// TODO Auto-generated method stub
		
// 	}
	
	//for debugging populateBoard only:
// 	public void printIntArray(int[][] board) {
// 		for(int[] row: board) {
// 			System.out.print("\n");
// 			for(int value: row) {
// 				System.out.print(value);
// 			}
// 		}
// 	}

}
