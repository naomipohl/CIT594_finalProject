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
	
	private void split() {
		// TODO Auto-generated method stub
		return;
	}
	
	private void swap() {
		// TODO Auto-generated method stub
		return;
	}

}
