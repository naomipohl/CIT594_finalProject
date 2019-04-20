package slidePuzzle;

public class LeafTile implements Tile {
	private int size;
	private int location;
	private int parentLocation;
	
	public LeafTile(int size, int location, int parentLocation) {
		this.size = size;
		this.location = location;
		this.parentLocation = parentLocation;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*SIZE GETTER*/
	public int size() {
		return this.size();
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
