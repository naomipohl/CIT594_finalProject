package slidePuzzle;

import static org.junit.Assert.*;

import org.junit.Test;

public class InternalTileTest {

	@Test
	public void testMerge() {
		fail("Not yet implemented");
		
		// edge case: if current node is root
	}
	
	@Test
	public void testRotate() {
		LeafTile ne = new LeafTile(8, 1, 0, 0);
		LeafTile nw = new LeafTile(8, 2, 0, 0);
		LeafTile se = new LeafTile(8, 3, 0, 0);
		LeafTile sw = new LeafTile(8, 4, 0, 0);
		InternalTile ogTile = new InternalTile(ne, nw, se, sw);
		InternalTile newTile = new InternalTile(ne, nw, se, sw);
		
		newTile.rotate();
		
		// check that the top-right & bottom-left entries are swapped
		assertTrue(newTile.getNE().equals(ogTile.getSW()));
		assertTrue(newTile.getSW().equals(ogTile.getNE()));
		
		// check that the top-left & bottom-right entries are swapped
		assertTrue(newTile.getNW().equals(ogTile.getSE()));
		assertTrue(newTile.getSE().equals(ogTile.getNW()));
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
		// do this at the leaf tile level?
		fail("Not yet implemented");
	}
	
	@Test
	public void testIsLeaf() {
		LeafTile ne = new LeafTile(8, 1, 0, 0);
		LeafTile nw = new LeafTile(8, 2, 0, 0);
		LeafTile se = new LeafTile(8, 3, 0, 0);
		LeafTile sw = new LeafTile(8, 4, 0, 0);
		InternalTile i = new InternalTile(ne, nw, se, sw);
		
		assertTrue(ne.isLeaf() == true);
		assertTrue(i.isLeaf() == false);
	}
	
}
