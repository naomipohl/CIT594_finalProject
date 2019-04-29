package slidePuzzle;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard implements Game {

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

	public String[][] getDis() {
		return dis;
	}
	@Override
	public Tile createTree() {

		// initialize an internal tile of size 16 w/ four children size 8
		LeafTile ne8 = new LeafTile(8, 1, 1, 0);
		LeafTile nw8 = new LeafTile(8, 2, 1, 0);
		LeafTile sw8 = new LeafTile(8, 3, 1, 0);
		LeafTile se8 = new LeafTile(8, 4, 1, 0);

		InternalTile internal16 = new InternalTile(ne8, nw8, sw8, se8);
		internal16.setDepth(16);
		internal16.setLocation(0);

		internal16.setNE(randomSplits(ne8, internal16));
		internal16.setNW(randomSplits(nw8, internal16));
		internal16.setSW(randomSplits(sw8, internal16));
		internal16.setSE(randomSplits(se8, internal16));

		return internal16;
	}

	private Tile randomSplits(LeafTile origLeaf, Tile root) {

		Tile toReturn = origLeaf;

		// flip a coin to decide whether or not to split
		Random rand = new Random();
		int rand_binary_int = rand.nextInt() % 2;
		if (rand_binary_int == 0) {
			toReturn = origLeaf;
		} else if (rand_binary_int == 1 && ((LeafTile) toReturn).getDepth() > 2) {
			InternalTile parent = findParent(toReturn, root);
			int location = ((LeafTile) toReturn).getLocation();
			parent = toReturn.split(parent);

			if (location == 1) {
				toReturn = parent.getNE();
			} else if (location == 2) {
				toReturn = parent.getNW();
			} else if (location == 3) {
				toReturn = parent.getSW();
			} else {
				toReturn = parent.getSE();
			}
		}

		if (toReturn instanceof InternalTile && ((InternalTile) toReturn).getDepth() > 2) {
			if (((InternalTile) toReturn).getNE() instanceof LeafTile) {
				((InternalTile) toReturn).setNE(randomSplits(((LeafTile) ((InternalTile) toReturn).getNE()), root));
			}

			if (((InternalTile) toReturn).getNW() instanceof LeafTile) {
				((InternalTile) toReturn).setNW(randomSplits(((LeafTile) ((InternalTile) toReturn).getNW()), root));
			}
			if (((InternalTile) toReturn).getSE() instanceof LeafTile) {
				((InternalTile) toReturn).setSE(randomSplits(((LeafTile) ((InternalTile) toReturn).getSE()), root));
			}
			if (((InternalTile) toReturn).getSW() instanceof LeafTile) {
				((InternalTile) toReturn).setSW(randomSplits(((LeafTile) ((InternalTile) toReturn).getSW()), root));
			}
		}

		// may be either the leaf passed in OR the internal resulting from split
		return toReturn;
	}

	/// this will take in a leaf Tile and get is depth and starting points from
	/// there
	public void updateDisplay(LeafTile leaf) {
		int startRow = leaf.getStartRow();
		int startCol = leaf.getStartCol();
		int endRow = startRow + leaf.getDepth();
		int endCol = startCol + leaf.getDepth();

		String cell = "   ";
		for (int i = startRow; i < endRow; i++) {
			for (int j = startCol; j < endCol; j++) {
				if ((i == endRow - 1) && (j == startCol)) {
					if (leaf.getDepth() == 1) {
						if (leaf == dog)
							cell = "|" + "D\u0332" + "|";
						else if (leaf == walker)
							cell = "|" + "W\\u0332" + "|";
						else
							cell = "|_|";
					} else
						cell = "|__";
				} else if ((i == endRow - 1) && (j == endCol - 1)) {
					cell = "__|";
				} else if (j == startCol) {
					if (i == startRow && leaf == dog) {
						cell = "|D ";
					} else if (i == startRow && leaf == walker) {
						cell = "|W ";
					} else
						cell = "|  ";
				} else if (j == endCol - 1) {
					cell = "  |";
				} else if (i == endRow - 1) {
					cell = "___";
				}
				dis[i][j] = cell;
				cell = "   ";
			}
		}
	}

	public void populateDis() {
		for (int i = 0; i < maxDepth; i++) {
			dis[0][i] = "___";
		}

		for (int i = 1; i < maxDepth + 1; i++) {
			for (int j = 0; j < maxDepth; j++) {
				dis[i][j] = "  ";
			}
		}
	}

	@Override
	public void printBoard() {
		int index = 0;
		String[] strDis = new String[maxDepth + 1];
		for (String[] rw : dis) {
			String res = "";
			for (String e : rw) {
				res = res + e;
			}
			strDis[index] = res;
			index++;
		}

		for (int i = 0; i < strDis.length; i++) {
			System.out.println(strDis[i]);
		}
	}

	@Override // need to update interface w/ this new signature
	public InternalTile findParent(Tile toFind, Tile startingTile) {
		InternalTile current = (InternalTile) startingTile;
		InternalTile neParent = null;
		InternalTile nwParent = null;
		InternalTile seParent = null;
		InternalTile swParent = null;
		// If any of the child leaves==caller leaf, return this
		if ((current.getNE() != null && current.getNE().equals(toFind))
				|| (current.getNW() != null && current.getNW().equals(toFind))
				|| (current.getSE() != null && current.getSE().equals(toFind))
				|| (current.getSW() != null && current.getSW().equals(toFind))) {
			return current;
		}
		// recursively store the search result (possibly null) findParent within each
		// child
		else {
			if (current.getNE() != null && current.getNE() instanceof InternalTile) {
				neParent = this.findParent(toFind, (InternalTile) current.getNE());
			}
			if (current.getNW() != null && current.getNW() instanceof InternalTile) {
				nwParent = this.findParent(toFind, (InternalTile) current.getNW());
			}
			if (current.getSW() != null && current.getSW() instanceof InternalTile) {
				swParent = this.findParent(toFind, (InternalTile) current.getSW());
			}
			if (current.getSE() != null && current.getSE() instanceof InternalTile) {
				seParent = this.findParent(toFind, (InternalTile) current.getSE());
			}

			// if found a non-null matching parent:
			if (neParent != null)
				return neParent;
			if (nwParent != null)
				return nwParent;
			if (seParent != null)
				return seParent;
			if (swParent != null)
				return swParent;

			// if no child and no recursive search resulted in a matching parent:
			return null;
		}

	}

	@Override
	public Tile placeDog(Tile root) {
		ArrayList<Tile> leafTiles = new ArrayList<Tile>();
		createLeafList(root, leafTiles);
		dog = (LeafTile) leafTiles.get(leafTiles.size() - 2);
		return dog;
	}

	@Override
	public Tile placeWalker(InternalTile root) {
		ArrayList<Tile> leafTiles = new ArrayList<Tile>();
		createLeafList(root, leafTiles);
		walker = (LeafTile) leafTiles.get(0);
		return walker;
	}

	private void createLeafList(Tile tile, ArrayList<Tile> leafTiles) {
		if (tile.isLeaf())
			leafTiles.add(tile);
		else {
			createLeafList(((InternalTile) tile).getNW(), leafTiles);
			createLeafList(((InternalTile) tile).getNE(), leafTiles);
			createLeafList(((InternalTile) tile).getSW(), leafTiles);
			createLeafList(((InternalTile) tile).getSE(), leafTiles);
		}
	}

	public void wipeDisplay() {
		for (int i = 1; i < dis.length; i++) {
			for (int j = 0; j < dis[0].length; j++) {
				dis[i][j] = "";
			}
		}
	}

	public void traverseTree(Tile tile) {
		if (tile.isLeaf())
			updateDisplay((LeafTile) tile);
		else {
			traverseTree(((InternalTile) tile).getNW());
			traverseTree(((InternalTile) tile).getNE());
			traverseTree(((InternalTile) tile).getSW());
			traverseTree(((InternalTile) tile).getSE());
		}
	}
}
