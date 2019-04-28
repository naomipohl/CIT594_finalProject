package slidePuzzle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InternalTileTest {
	
	static GameBoard myGB;
	static LeafTile hardCodeRoot;
	
	@Before
	public void beforeEachTestMethod() {
		myGB = new GameBoard(16,1);
		hardCodeRoot = new LeafTile(16,2,0,0); //inputs are parent location = 0,0 and position = 2 (top left)
	}

	@Test
	public void testMerge() {
		//have already initialized 16x16 board without splits
		myGB.populateBoard(hardCodeRoot);
		
		/*****************************************
		 * Now let's split into four tiles of width 8:
		 ****************************************/
		LeafTile Leaf8_1 = new LeafTile(8,1,0,0);
		LeafTile Leaf8_2 = new LeafTile(8,2,0,0);
		LeafTile Leaf8_3 = new LeafTile(8,3,0,0);
		LeafTile Leaf8_4 = new LeafTile(8,4,0,0);
		
		InternalTile internal16 = new InternalTile(Leaf8_1,Leaf8_2,Leaf8_3,Leaf8_4);
		internal16.setDepth(16);
		myGB.populateBoard(internal16);

		/*****************************************
		 * Now let's split SE corner into four tiles of width 4:
		 ****************************************/
		LeafTile Leaf4_1 = new LeafTile(4,1,0,0);
		LeafTile Leaf4_2 = new LeafTile(4,2,0,0);
		LeafTile Leaf4_3 = new LeafTile(4,3,0,0);
		LeafTile Leaf4_4 = new LeafTile(4,4,0,0);
		
		InternalTile internal8 = new InternalTile(Leaf4_1,Leaf4_2,Leaf4_3,Leaf4_4);
		internal8.setDepth(8);
		internal16.setSE(internal8);
		
		myGB.populateBoard(internal16);
		
		/*****************************************
		 * Now let's split NE corner of SE tile into four tiles of width 2:
		 ****************************************/
		LeafTile Leaf2_1 = new LeafTile(2,1,0,0);
		LeafTile Leaf2_2 = new LeafTile(2,2,0,0);
		LeafTile Leaf2_3 = new LeafTile(2,3,0,0);
		LeafTile Leaf2_4 = new LeafTile(2,4,0,0);
		
		InternalTile internal4 = new InternalTile(Leaf2_1,Leaf2_2,Leaf2_3,Leaf2_4);
		internal4.setDepth(4);
		internal8.setNE(internal4);
		
		myGB.populateBoard(internal16);
		
		
		/*****************************************
		 * Finally, split SW corner of NE corner of bottom right tile into four tiles of width 1:
		 ****************************************/
		LeafTile Leaf1_1 = new LeafTile(1,1,0,0);
		LeafTile Leaf1_2 = new LeafTile(1,2,0,0);
		LeafTile Leaf1_3 = new LeafTile(1,3,0,0);
		LeafTile Leaf1_4 = new LeafTile(1,4,0,0);
		
		InternalTile internal2 = new InternalTile(Leaf1_1,Leaf1_2,Leaf1_3,Leaf1_4);
		internal2.setDepth(2);
		internal4.setSW(internal2);
		
		myGB.populateBoard(internal16);
		
		/*****************************************
		 * Merge the width 1 tiles
		 ****************************************/
		// Find the parent of the width 1 tiles
		InternalTile parent = myGB.findParent(Leaf1_1);
		// Find the grandparent of the width 1 tiles
		InternalTile grandparent = myGB.findParent(parent);
		
		// Merge the tiles
		LeafTile l = parent.merge(grandparent);
		
		assertTrue(l.getDepth() == 2);
		assertTrue(((LeafTile) internal4.getSE()).getDepth() == 2);
	}
	
	@Test
	public void testRotate() {
		//have already initialized 16x16 board without splits
		myGB.populateBoard(hardCodeRoot);
		
		/*****************************************
		 * Now let's split into four tiles of width 8:
		 ****************************************/
		LeafTile Leaf8_1 = new LeafTile(8,1,0,0);
		LeafTile Leaf8_2 = new LeafTile(8,2,0,0);
		LeafTile Leaf8_3 = new LeafTile(8,3,0,0);
		LeafTile Leaf8_4 = new LeafTile(8,4,0,0);
		
		InternalTile internal16 = new InternalTile(Leaf8_1,Leaf8_2,Leaf8_3,Leaf8_4);
		internal16.setDepth(16);
		myGB.populateBoard(internal16);

		/*****************************************
		 * Now let's split SE corner into four tiles of width 4:
		 ****************************************/
		LeafTile Leaf4_1 = new LeafTile(4,1,0,0);
		LeafTile Leaf4_2 = new LeafTile(4,2,0,0);
		LeafTile Leaf4_3 = new LeafTile(4,3,0,0);
		LeafTile Leaf4_4 = new LeafTile(4,4,0,0);
		
		InternalTile internal8 = new InternalTile(Leaf4_1,Leaf4_2,Leaf4_3,Leaf4_4);
		internal8.setDepth(8);
		internal16.setSE(internal8);
		
		myGB.populateBoard(internal16);
		
		/*****************************************
		 * Now let's split NE corner of SE tile into four tiles of width 2:
		 ****************************************/
		LeafTile Leaf2_1 = new LeafTile(2,1,0,0);
		LeafTile Leaf2_2 = new LeafTile(2,2,0,0);
		LeafTile Leaf2_3 = new LeafTile(2,3,0,0);
		LeafTile Leaf2_4 = new LeafTile(2,4,0,0);
		
		InternalTile internal4 = new InternalTile(Leaf2_1,Leaf2_2,Leaf2_3,Leaf2_4);
		internal4.setDepth(4);
		internal8.setNE(internal4);
		
		myGB.populateBoard(internal16);
		
		
		/*****************************************
		 * Finally, split SW corner of NE corner of bottom right tile into four tiles of width 1:
		 ****************************************/
		LeafTile Leaf1_1 = new LeafTile(1,1,0,0);
		LeafTile Leaf1_2 = new LeafTile(1,2,0,0);
		LeafTile Leaf1_3 = new LeafTile(1,3,0,0);
		LeafTile Leaf1_4 = new LeafTile(1,4,0,0);
		
		InternalTile internal2 = new InternalTile(Leaf1_1,Leaf1_2,Leaf1_3,Leaf1_4);
		internal2.setDepth(2);
		internal4.setSW(internal2);
		
		myGB.populateBoard(internal16);
		
		/*****************************************
		 * Rotate the width 2 tiles
		 ****************************************/
		
		Tile t = Leaf2_3.rotate();
		
		assertTrue(((LeafTile) ((InternalTile) t).getNE()).getDepth() == 1);
		assertTrue(((LeafTile) ((InternalTile) t).getNW()).getDepth() == 2);
		assertTrue(((LeafTile) ((InternalTile) t).getSW()).getDepth() == 2);
		assertTrue(((LeafTile) ((InternalTile) t).getSE()).getDepth() == 2);
	}
	
	@Test
	public void testSplit() {
		LeafTile ne = new LeafTile(8, 1, 0, 0);
		LeafTile nw = new LeafTile(8, 2, 0, 0);
		LeafTile se = new LeafTile(8, 3, 0, 0);
		LeafTile sw = new LeafTile(8, 4, 0, 0);
		InternalTile newTile = new InternalTile(ne, nw, se, sw);
		
		InternalTile res = newTile.split();
		
		// assert that the method returns a reference to itself
		assertTrue(res.equals(newTile));
	}
	
	@Test
	public void testSwap() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testIsLeaf() {
		LeafTile ne = new LeafTile(8, 1, 0, 0);
		LeafTile nw = new LeafTile(8, 2, 0, 0);
		LeafTile se = new LeafTile(8, 3, 0, 0);
		LeafTile sw = new LeafTile(8, 4, 0, 0);
		InternalTile i = new InternalTile(ne, nw, se, sw);
		
		assertTrue(ne.isLeaf());
		assertFalse(i.isLeaf());
	}
	
}
