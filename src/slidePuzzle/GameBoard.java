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
	
	Private void populateBoard(Tile root, int rowStart, int colStart){

		if(root instanceOf InternalTile){ //if internal node just call fcn recursively on children
			generateIntArray(root.NW, rowStart, colStart);
			generateIntArray(root.NE, rowStart, colStart + root.size()/2);
			generateIntArray(root.SE, rowStart + root.size()/2, colStart + row.size()/2);
			generateIntArray(root.SW, rowStart + root.size()/2, coStart);
		}
		
		else{ //if leaf node:
			for (int i = rowStart; i < rowStart + root.size(); i++){
				for (int j = colStart; ij< colStart + root.size(); j++){
					ordToPrint[i][j] = root.size();
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
