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
}

