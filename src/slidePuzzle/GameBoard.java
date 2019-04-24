package slidePuzzle;

public class GameBoard implements Game{
	
	public Tile root;
	public int maxDepth;
	public int minDepth;
	public LeafTile walker;
	public LeafTile dog;
	public int[][] dis;
	
	public GameBoard(int maxDepth, int minDepth) {
		this.maxDepth = maxDepth;
		this.minDepth = minDepth;
		this.root = createTree();
		this.dis = new int[maxDepth + 1][maxDepth];
	}

	@Override
	public Tile createTree() {
		// TODO Auto-generated method stub
		return null;
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
	
	///this will actually take in a leaf Tile and get is depth and starting points from there 
	void updateDisplay(LeafTile leaf) {
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
	
	public void disToString() {
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

	@Override
	public void printBoard(int[][] board) {
		// TODO Auto-generated method stub
		
	}
	
	//for debugging populateBoard only:
	public void printIntArray(int[][] board) {
		for(int[] row: board) {
			System.out.print("\n");
			for(int value: row) {
				System.out.print(value);
			}
		}
	}

}
