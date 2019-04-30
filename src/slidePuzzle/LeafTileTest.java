package slidePuzzle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LeafTileTest {

	/**
	 * Tests constructor
	 */
	@Test
	public void testLeafTileConstructor() {

		LeafTile leaf = new LeafTile(1,1,14,15);
		assertEquals(leaf.getDepth(), 1);
		assertEquals(leaf.getLocation(), 1);
		assertEquals(leaf.getStartRow(), 14);
		assertEquals(leaf.getStartCol(), 16);
	}

	/**
	 * Tests getDepth method on LeafTile objects
	 */
	@Test
	public void testGetDepth() {
		InternalTile rootDepth2 = new InternalTile(null, null, null, null);
		rootDepth2.setDepth(2);
		rootDepth2.setLocation(2);
		assertEquals(rootDepth2.getDepth(), 2);
		
		LeafTile leaf = new LeafTile(2, 1, 0, 0);
		assertEquals(leaf.getDepth(), 2);
	}

	/**
	 * Tests getLocation on LeafTile objects
	 */
	@Test
	public void testGetLocation() {
		InternalTile internal = new InternalTile(null, null, null, null);
		internal.setLocation(2);
		assertEquals(internal.getLocation(), 2);
		
		LeafTile leaf = new LeafTile(1,2,14,15);
		assertEquals(leaf.getLocation(), 2);
	}

	/**
	 * Tests getStartRow on LeafTile objects
	 */
	@Test
	public void testGetStartRow() {
		LeafTile leaf = new LeafTile(1,1,14,15);
		assertEquals(leaf.getStartRow(), 14);
		
		LeafTile leaf2 = new LeafTile(1, 4, 0, 0);
		assertEquals(leaf2.getStartRow(), 1);
	}
	
	/**
	 * Tests getStartCol on LeafTile objects
	 */
	@Test
	public void testGetStartCol() {
		LeafTile leaf = new LeafTile(1,1,14,15);
		assertEquals(leaf.getStartCol(), 16);	
		
		LeafTile leaf2 = new LeafTile(1, 4, 0, 0);
		assertEquals(leaf2.getStartCol(), 1);
	}

	
	/**
	 * Tests split on LeafTile objects
	 */
	@Test
	public void testSplitLeafTile() {
		//Splits a leaf tile into four new leaf tiles and
		//returns the grandparent" internal tile of the four
		//new leaf tiles.
		//The resulting leaf tiles will each have depth equal 
		//to one half that of their parent. 

		Tile a = new LeafTile(4,1,0,0);
		Tile b = new LeafTile(4,2,0,0);
		Tile c = new LeafTile(4,3,0,0);
		Tile d = new LeafTile(4,4,0,0);

		InternalTile parent = new InternalTile(null, null, null, null);
		parent.setDepth(8);
		parent.setLocation(1);
		parent.setNE(a);
		parent.setNW(b);
		parent.setSW(c);
		parent.setSE(d);
		
		InternalTile splitA = a.split(parent);
		assertEquals(splitA.getDepth(),8);  //depth of parent should stay the same
		
		InternalTile aInternalTile = (InternalTile)splitA.getNE();  //this is original leaf tile that method was called on
		LeafTile upperRight = (LeafTile) aInternalTile.getNE();	
		assertEquals(((LeafTile) upperRight).getLocation(),1);
		assertEquals(((LeafTile) upperRight).getDepth(), 2);
		assertEquals(((LeafTile) upperRight).getStartRow(), 0);
		assertEquals(((LeafTile) upperRight).getStartCol(), 6);
		
		Tile upperLeft = (LeafTile) aInternalTile.getNW();
		assertEquals(((LeafTile) upperLeft).getStartRow(), 0);
		assertEquals(((LeafTile) upperLeft).getStartCol(), 4);
		
		Tile lowerLeft = (LeafTile) aInternalTile.getSW();
		assertEquals(((LeafTile) lowerLeft).getStartRow(), 2);
		assertEquals(((LeafTile) lowerLeft).getStartCol(), 4);
		
		Tile lowerRight = (LeafTile) aInternalTile.getSE();
		assertEquals(((LeafTile) lowerRight).getStartRow(), 2);
		assertEquals(((LeafTile) lowerRight).getStartCol(), 6);
	}
	
	/**
	 * Tests merge on LeafTile objects
	 */
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
		InternalTile SE = new InternalTile(a, b, d, c);
		SE.setLocation(4);
		SE.setDepth(4);
				
		InternalTile root = new InternalTile(NE, NW, SE, SW);
		root.setDepth(8);
		root.setLocation(1);
		
		LeafTile actual = a.merge(SE, root, a);
		assertEquals(actual.getDepth(), 4);
		assertEquals(actual.getLocation(), 4);
		assertEquals(actual.getStartRow(), 4);
		assertEquals(actual.getStartCol(), 4);
		assertEquals(root.getSE(), actual);  
		
	}
	
	/**
	 * Tests rotate on LeafTile objects
	 */
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
		assertEquals(SE.getNE().rotate(SE, 1), c);
		
		//From Location 2:
		assertEquals(SE.getNW().rotate(SE, 2), d);
		
		//From Location 3: 
		assertEquals(root.getSW().rotate(root, 3), NE);
		
		LeafTile mergedLoc4 = a.merge(SE, root, a);
		
		//From Location 4:
		assertEquals(mergedLoc4.rotate(root, 4), NW);
	}

	@Test
	public void testIsLeaf() {
		LeafTile NE = new LeafTile(4, 1, 0, 0);
		InternalTile parent = new InternalTile(null, null, null, null);

		assertTrue(NE.isLeaf());
		assertFalse(parent.isLeaf());
	}

	@Test
	public void swapLeftRight() {
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
		
		assertEquals(a.swap("sl", root), b);
		assertEquals(c.swap("sr", root), d);
	}

	@Test
	public void swapUpDown() {
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
		
		assertEquals(a.swap("sd", root), d);
		assertEquals(c.swap("su", root), b);
	}
	
	@Test
	public void swapDifferentParents() {
		
		LeafTile a4 = new LeafTile(2,1,4,4);
		LeafTile b4 = new LeafTile(2,2,4,4);
		LeafTile c4 = new LeafTile(2,3,4,4);
		LeafTile d4 = new LeafTile(2,4,4,4);
		InternalTile SE = new InternalTile(a4, b4, d4, c4);
		
		LeafTile a3 = new LeafTile(2, 1, 4, 0);
		LeafTile b3 = new LeafTile(2, 2, 4, 0);
		LeafTile c3 = new LeafTile(2, 3, 4, 0);
		LeafTile d3 = new LeafTile(2, 4, 4, 0);
		InternalTile SW = new InternalTile(a3, b3, d3, c3);
		
		LeafTile a1 = new LeafTile(2, 1, 0, 4);
		LeafTile b1 = new LeafTile(2, 2, 0, 4);
		LeafTile c1 = new LeafTile(2, 3, 0, 4);
		LeafTile d1 = new LeafTile(2, 4, 0, 4);
		InternalTile NE = new InternalTile(a1, b1, d1, c1);
		
		LeafTile a2 = new LeafTile(2, 1, 0, 0);
		LeafTile b2 = new LeafTile(2, 2, 0, 0);
		LeafTile c2 = new LeafTile(2, 3, 0, 0);
		LeafTile d2 = new LeafTile(2, 4, 0, 0);
		InternalTile NW = new InternalTile(a2, b2, d2, c2);
		
		InternalTile root = new InternalTile(NE, NW, SE, SW);
		root.setDepth(8);
		root.setLocation(1);

		assertEquals(a4.swap("su", root), d1);
		assertEquals(c1.swap("sd", root), b4);
		assertEquals(d3.swap("sr", root), c4);
		assertEquals(b1.swap("sl", root), a2);
	}
	@Test
	public void testSetLocation() {
		LeafTile leaf = new LeafTile(2, 1, 0, 0);
		leaf.setLocation(2);
		assertEquals(leaf.getLocation(), 2);
	}	
}
