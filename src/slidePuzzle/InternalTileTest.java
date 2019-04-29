package slidePuzzle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InternalTileTest {

	@Test
	public void testMerge() {
		//Creates a new leaf node from 4 sibling leaf tiles
		//test that depth, location, startRow and startCol are correct for new leaf node
		LeafTile NE = new LeafTile(4, 1, 0, 0);
		LeafTile NW = new LeafTile(4, 2, 0, 0);
		LeafTile SW = new LeafTile(4, 3, 0, 0);
		
		LeafTile a = new LeafTile(2,1,4,4);
		LeafTile b = new LeafTile(2,2,4,4);
		LeafTile c = new LeafTile(2,3,4,4);
		LeafTile d = new LeafTile(2,4,4,4);
		InternalTile SE = new InternalTile(a, b, c, d);
		SE.setLocation(4);
		SE.setDepth(4);
				
		InternalTile root = new InternalTile(NE, NW, SW, SE);
		root.setDepth(8);
		root.setLocation(1);
		
		LeafTile actual = SE.merge(SE, root, a);
		assertEquals(actual.getDepth(), 4);
		assertEquals(actual.getLocation(), 4);
		assertEquals(actual.getStartRow(), 4);
		assertEquals(actual.getStartCol(), 4);
		assertEquals(root.getSE(), actual);
		
		LeafTile actual2 = SW.merge(root, root, SW);
		assertEquals(actual2.getDepth(), 8);
		assertEquals(actual2.getLocation(), 1);
		assertEquals(actual2.getStartRow(), 0);
		assertEquals(actual2.getStartCol(), 0);
		
		
		
	}
	
	@Test
	public void testRotate() {
		//takes four sibling leaf tile and rotates position clockwise 180 degrees

		LeafTile NE = new LeafTile(4, 1, 0, 0);
		LeafTile NW = new LeafTile(4, 2, 0, 0);
		LeafTile SW = new LeafTile(4, 3, 0, 0);
		
		LeafTile a = new LeafTile(2,1,4,4);
		LeafTile b = new LeafTile(2,2,4,4);
		LeafTile c = new LeafTile(2,3,4,4);
		LeafTile d = new LeafTile(2,4,4,4);
		InternalTile SE = new InternalTile(a, b, c, d);
		SE.setLocation(4);
		SE.setDepth(4);
				
		InternalTile root = new InternalTile(NE, NW, SW, SE);
		root.setDepth(8);
		root.setLocation(1);

		//From Location 1:
		assertEquals(SE.getNE().rotate(SE, 1), SE.getSW());
		
		//From Location 2:
		assertEquals(SE.getNW().rotate(SE, 2), SE.getSE());
		
		//From Location 3: 
		assertEquals(SE.getSW().rotate(SE, 3), SE.getNE());
		
		LeafTile mergedLoc4 = a.merge(SE, root, a);
		
		//From Location 4:
		assertEquals(mergedLoc4.rotate(root, 4), NW);
	}
	
	@Test
	public void testSplit() {
		LeafTile NE = new LeafTile(4, 1, 0, 0);
		LeafTile NW = new LeafTile(4, 2, 0, 0);
		LeafTile SW = new LeafTile(4, 3, 0, 0);
		
		LeafTile a = new LeafTile(2,1,4,4);
		LeafTile b = new LeafTile(2,2,4,4);
		LeafTile c = new LeafTile(2,3,4,4);
		LeafTile d = new LeafTile(2,4,4,4);
		InternalTile SE = new InternalTile(a, b, d, c);
		SE.setLocation(4);
		SE.setDepth(4);
				
		InternalTile root = new InternalTile(NE, NW, SE, SW);
		root.setDepth(8);
		root.setLocation(1);
		
		InternalTile t = SE.split(root);

		assertTrue(t.equals(SE));
	}
	
	@Test
	public void testSwap() {
		LeafTile NE = new LeafTile(4, 1, 0, 0);
		LeafTile NW = new LeafTile(4, 2, 0, 0);
		LeafTile SW = new LeafTile(4, 3, 0, 0);
		
		LeafTile a = new LeafTile(2,1,4,4);
		LeafTile b = new LeafTile(2,2,4,4);
		LeafTile c = new LeafTile(2,3,4,4);
		LeafTile d = new LeafTile(2,4,4,4);
		InternalTile SE = new InternalTile(a, b, d, c);
		SE.setLocation(4);
		SE.setDepth(4);
				
		InternalTile root = new InternalTile(NE, NW, SE, SW);
		root.setDepth(8);
		root.setLocation(1);
		
		assertTrue(SE.swap("su", root) == null);
		
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
