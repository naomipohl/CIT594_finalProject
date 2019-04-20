package slidePuzzle;

public class GameBoard implements Game{
	
	public Tile root;
	public int maxDepth;
	public int minDepth;
	public LeafTile walker;
	public LeafTile dog;
	public int[][] ordToPrint;
	
	public GameBoard(int maxDepth, int minDepth) {
		this.maxDepth = maxDepth;
		this.minDepth = minDepth;
		this.root = createTree();
		this.ordToPrint = new int[maxDepth][maxDepth];
	}

	@Override
	public Tile createTree() {
		// TODO Auto-generated method stub
		return null;
	}

		@Override
	public void populateBoard(Tile t) {
		populateBoard(t, 0, 0);
		
	}
	
	private void populateBoard(Tile root, int rowStart, int colStart){

		if(root instanceof InternalTile){ //if internal node just call fcn recursively on children
			InternalTile rootInternal = (InternalTile) root;
			populateBoard(rootInternal.getNW(), rowStart, colStart);
			populateBoard(rootInternal.getNE(), rowStart, colStart + rootInternal.size()/2);
			populateBoard(rootInternal.getSE(), rowStart + rootInternal.size()/2, colStart + rootInternal.size()/2);
			populateBoard(rootInternal.getSW(), rowStart + rootInternal.size()/2, colStart);
		}
		
		else{ //if leaf node:
			LeafTile rootLeaf = (LeafTile) root;
			for (int i = rowStart; i < rowStart + rootLeaf.size(); i++){
				for (int j = colStart; j< colStart + rootLeaf.size(); j++){
					ordToPrint[i][j] = rootLeaf.size();
				}
			}
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

}
