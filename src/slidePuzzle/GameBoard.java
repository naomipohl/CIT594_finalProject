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
		// TODO Auto-generated method stub
		
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
