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
		
		//Creates a new leaf node from 4 sibling leaf tiles
		//test that depth, location, startRow and startCol are correct for new leaf node
		LeafTile NE2 = new LeafTile(4, 1, 0, 0);
		LeafTile NW2 = new LeafTile(4, 2, 0, 0);
		LeafTile SW2 = new LeafTile(4, 3, 0, 0);
		
		LeafTile a2 = new LeafTile(2,1,4,4);
		LeafTile b2 = new LeafTile(2,2,4,4);
		LeafTile c2 = new LeafTile(2,3,4,4);
		LeafTile d2 = new LeafTile(2,4,4,4);
		InternalTile SE2 = new InternalTile(a2, b2, c2, d2);
		SE2.setLocation(4);
		SE2.setDepth(4);
				
		InternalTile root2 = new InternalTile(NE2, NW2, SW2, SE2);
		root2.setDepth(8);
		root2.setLocation(1);
		
		LeafTile actual3 = SE2.merge(SE2, root2, b2);
		assertEquals(actual3.getDepth(), 4);
		assertEquals(actual3.getLocation(), 4);
		assertEquals(actual3.getStartRow(), 4);
		assertEquals(actual3.getStartCol(), 4);
		assertEquals(root2.getSE(), actual3);
		
		LeafTile actual4 = NE2.merge(root2, root2, NE2);
		assertEquals(actual4.getDepth(), 8);
		assertEquals(actual4.getLocation(), 1);
		assertEquals(actual4.getStartRow(), 0);
		assertEquals(actual4.getStartCol(), 0);
		
		//Creates a new leaf node from 4 sibling leaf tiles
		//test that depth, location, startRow and startCol are correct for new leaf node
		LeafTile NE3 = new LeafTile(4, 1, 0, 0);
		LeafTile NW3 = new LeafTile(4, 2, 0, 0);
		LeafTile SW3 = new LeafTile(4, 3, 0, 0);
		
		LeafTile a3 = new LeafTile(2,1,4,4);
		LeafTile b3 = new LeafTile(2,2,4,4);
		LeafTile c3 = new LeafTile(2,3,4,4);
		LeafTile d3 = new LeafTile(2,4,4,4);
		InternalTile SE3 = new InternalTile(a3, b3, c3, d3);
		SE3.setLocation(4);
		SE3.setDepth(4);
				
		InternalTile root3 = new InternalTile(NE3, NW3, SW3, SE3);
		root3.setDepth(8);
		root3.setLocation(1);
		
		LeafTile actual5 = SE3.merge(SE3, root3, c3);
		assertEquals(actual5.getDepth(), 4);
		assertEquals(actual5.getLocation(), 4);
		assertEquals(actual5.getStartRow(), 4);
		assertEquals(actual5.getStartCol(), 4);
		assertEquals(root3.getSE(), actual5);
		
		LeafTile actual6 = NW3.merge(root3, root3, NW3);
		assertEquals(actual6.getDepth(), 8);
		assertEquals(actual6.getLocation(), 1);
		assertEquals(actual6.getStartRow(), 0);
		assertEquals(actual6.getStartCol(), 0);
		
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
