package slidePuzzle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InternalTileTest {
	
	 GameBoard myGB;
	 LeafTile hardCodeRoot;
	
	@Before
	public void beforeEachTestMethod() {
		myGB = new GameBoard(16,1);
		hardCodeRoot = new LeafTile(16,2,0,0); //inputs are parent location = 0,0 and position = 2 (top left)
	}

	@Test
	public void testMerge() {
		myGB = new GameBoard(16,1);
		// Populate the board
		myGB.populateDis();
				
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

		// Perform the merge on the internal tile
		LeafTile l = internal2.merge(internal2, internal4);
		
		assertTrue(l.getDepth() == 2);
		assertTrue(((LeafTile) internal4.getSE()).getDepth() == 2);
		
		
		/*****************************************
		 * Merge the NE tile of the SE box
		 ****************************************/
		
		LeafTile l2 = internal4.merge(internal4, internal8);
		
		assertTrue(l2.getDepth() == 4);
		assertTrue(((LeafTile) internal8.getSE()).getDepth() == 4);
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
		
		Tile t = internal2.rotate(internal2, 3);
		
		Tile t2 = internal2.rotate(internal2, 2);
		
		Tile t3 = internal2.rotate(internal2, 1);
		
		Tile t4 = internal2.rotate(internal2, 4);
		
		assertTrue(t.equals(internal2.getNE()));
		assertTrue(t2.equals(internal2.getSE()));
		assertTrue(t3.equals(internal2.getSW()));
		assertTrue(t4.equals(internal2.getNW()));
	}
	
	@Test
	public void testSplit() {
		LeafTile ne = new LeafTile(8, 1, 0, 0);
		LeafTile nw = new LeafTile(8, 2, 0, 0);
		LeafTile se = new LeafTile(8, 3, 0, 0);
		LeafTile sw = new LeafTile(8, 4, 0, 0);
		InternalTile newTile = new InternalTile(ne, nw, se, sw);
		
		LeafTile ne2 = new LeafTile(8, 1, 0, 0);
		LeafTile nw2 = new LeafTile(8, 2, 0, 0);
		LeafTile se2 = new LeafTile(8, 3, 0, 0);
		LeafTile sw2 = new LeafTile(8, 4, 0, 0);
		InternalTile newTile2 = new InternalTile(ne2, nw2, se2, sw2);
		
		InternalTile t = newTile2.split(newTile);

		assertTrue(t.equals(newTile2));
	}
	
	@Test
	public void testSwap() {
		LeafTile ne = new LeafTile(8, 1, 0, 0);
		LeafTile nw = new LeafTile(8, 2, 0, 0);
		LeafTile se = new LeafTile(8, 3, 0, 0);
		LeafTile sw = new LeafTile(8, 4, 0, 0);
		InternalTile newTile = new InternalTile(ne, nw, se, sw);
		
		assertTrue(newTile.swap("su", myGB.root) == null);
		
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

