package slidePuzzle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LeafTileTest {

	@Test
	public void testLeafTileConstructor() {

		LeafTile leaf = new LeafTile(1,1,14,15);
		assertEquals(leaf.getDepth(), 1);
		assertEquals(leaf.getLocation(), 1);
		assertEquals(leaf.getStartRow(), 14);
		assertEquals(leaf.getStartCol(), 15);
	}

	
/*	THIS BLOCK OF TESTS FOR GETTERS AND SETTERS IS UNNECESSARY:
	@Test
	public void testGetDepth() {
		InternalTile rootDepth2 = new InternalTile(null, null, null, null);
		rootDepth2.setDepth(2);
		rootDepth2.setNE(new LeafTile(1,1,14,15));
		rootDepth2.setNW(new LeafTile(1,2,14,14));
		rootDepth2.setSW(new LeafTile(1,3,15,14));
		rootDepth2.setSE(new LeafTile(1,4,15,15));
		
		InternalTile rootDepth4 = new InternalTile(null, null, null, null);
		rootDepth2.setDepth(4);
		rootDepth4.setNE(new LeafTile(2,1,12,14));
		rootDepth4.setNW(new LeafTile(2,2,12,12));
		rootDepth4.setSW(new LeafTile(2,3,14,12));
		rootDepth4.setSE(rootDepth2);
		rootDepth2.setLocation(4);

		InternalTile rootDepth8 = new InternalTile(null, null, null, null);
		rootDepth8.setDepth(8);
		rootDepth8.setNE(new LeafTile(4,1,8,12));
		rootDepth8.setNW(new LeafTile(4,2,8,8));
		rootDepth8.setSW(new LeafTile(4,3,12,8));
		rootDepth8.setSE(rootDepth4);
		rootDepth4.setLocation(4);
		
		InternalTile rootDepth16 = new InternalTile(null, null, null, null);
		rootDepth16.setDepth(16);
		rootDepth16.setNE(new LeafTile(8,1,0,8));
		rootDepth16.setNW(new LeafTile(8,2,0,0));
		rootDepth16.setSW(new LeafTile(8,3,8,0));	
		rootDepth16.setSE(rootDepth8);
		rootDepth8.setLocation(4);

		assertEquals(rootDepth16.getDepth(), 16);
	}

	@Test
	public void testGetLocation() {
		LeafTile leaf = new LeafTile(1,2,14,15);
		assertEquals(leaf.getLocation(), 2);
	}

	@Test
	public void testGetStartRow() {
		LeafTile leaf = new LeafTile(1,1,14,15);
		assertEquals(leaf.getStartRow(), 14);
	}
	
	@Test
	public void testGetStartCol() {
		LeafTile leaf = new LeafTile(1,1,14,15);
		assertEquals(leaf.getStartCol(), 15);	
	}
*/
		
	@Test
	public void testSplitLeafTile() {
		//Splits a leaf tile into four new leaf tiles and
		//returns the "parent" internal tile.
		//The resulting leaf tiles will each have depth equal 
		//to one half that of their parent. 

		Tile a = new LeafTile(4,1,0,4);
		Tile b = new LeafTile(4,2,0,0);
		Tile c = new LeafTile(4,3,4,0);
		Tile d = new LeafTile(4,4,4,4);

		InternalTile root8 = new InternalTile(null, null, null, null);
		root8.setNE(a);
		root8.setNW(b);
		root8.setSW(c);
		root8.setSE(d);
		
		InternalTile splitA = a.split();
		assertEquals(splitA.getDepth(),2);
		assertEquals(splitA.getLocation(),1);
		
		Tile upperRight = splitA.getNE();
		assertEquals(((LeafTile) upperRight).getLocation(),1);
		assertEquals(((LeafTile) upperRight).getDepth(), 2);
		assertEquals(((LeafTile) upperRight).getStartRow(), 0);
		assertEquals(((LeafTile) upperRight).getStartCol(), 6);
		
		Tile upperLeft = splitA.getNW();
		assertEquals(((LeafTile) upperLeft).getStartRow(), 0);
		assertEquals(((LeafTile) upperLeft).getStartCol(), 4);
		
		Tile lowerLeft = splitA.getSW();
		assertEquals(((LeafTile) lowerLeft).getStartRow(), 2);
		assertEquals(((LeafTile) lowerLeft).getStartCol(), 4);
		
		Tile lowerRight = splitA.getSE();
		assertEquals(((LeafTile) lowerRight).getStartRow(), 2);
		assertEquals(((LeafTile) lowerRight).getStartCol(), 6);
	}
	
	@Test
	public void testSplitInternalTile() {
		
		Tile root16 = new InternalTile(null, null, null, null);
		root16.setDepth(16);
		root16.setLocation(1);
		assertEquals(root16.split(), root16);
	}

	
	@Test
	public void testMerge() {
		//Creates a new leaf node from 4 sibling leaf tiles
		//test that depth, location, startRow and startCol are correct for new leaf node
		LeafTile root8NE = new LeafTile(8, 1, 0, 8);
		LeafTile root8SW = new LeafTile(8, 3, 8, 0);
		LeafTile root8SE = new LeafTile(8, 4, 8, 8);
		
		LeafTile a = new LeafTile(4,1,0,4);
		LeafTile b = new LeafTile(4,2,0,0);
		LeafTile c = new LeafTile(4,3,4,0);
		LeafTile d = new LeafTile(4,4,4,4);

		InternalTile root8NW = new InternalTile(null, null, null, null);
		root8NW.setNE(a);
		root8NW.setNW(b);
		root8NW.setSW(c);
		root8NW.setSE(d);
		root8NW.setLocation(2);
		root8NW.setDepth(8);
				
		InternalTile root16 = new InternalTile(null, null, null, null);
		root16.setNE(root8NE);
		root16.setNW(root8NW);
		root16.setSW(root8SW);
		root16.setSE(root8SE);
		
		Tile actual = a.merge();
		assertEquals(((LeafTile)actual).getDepth(), 8);
		assertEquals(((LeafTile)actual).getLocation(), 2);
		assertEquals(((LeafTile)actual).getStartRow(), 0);
		assertEquals(((LeafTile)actual).getStartCol(), 0);
		assertEquals(root16.getNW(), (LeafTile)actual);
	}
	
	@Test
	public void testRotate() {
		//takes four sibling leaf tile and rotates position clockwise 180 degrees

		InternalTile rootDepth2 = new InternalTile(null, null, null, null);

		LeafTile northeast = new LeafTile(1,1,14,15);
		LeafTile northwest = new LeafTile(1,2,14,14);
		LeafTile southwest = new LeafTile(1,3,15,14);
		LeafTile southeast = new LeafTile(1,4,15,15);
		
		rootDepth2.setNE(northeast);
		rootDepth2.setNW(northwest);
		rootDepth2.setSW(southwest);
		rootDepth2.setSE(southeast);

		//From Location 3: 
		southwest.rotate();
		assertEquals(rootDepth2.getSW(), northeast); 
		
		//From Location 2:
		northwest.rotate();
		assertEquals(rootDepth2.getNW(), southeast);
		
		//From Location 1:
		northeast.rotate();
		assertEquals(rootDepth2.getSW(), southwest);
		
		//From Location 4:
		southeast.rotate();
		assertEquals(rootDepth2.getSE(), southeast);
		
	}
	
	@Test
	public void testSwapLeftSameParent() {
		//Construct a grid of 4 leaf nodes:
		LeafTile a = new LeafTile(4,1,8,12);
		LeafTile b = new LeafTile(4,2,8,8);
		LeafTile c = new LeafTile(4,3,12,8);
		LeafTile d = new LeafTile(4,4,12,12);
		
		InternalTile root = new InternalTile(null, null, null, null);
		root.setNE(a);
		root.setNW(b);
		root.setSW(c);
		root.setSE(d);
		
		//SWAP LEFT: 
		//from Location 1
		a.swap("sl");
		assertEquals(root.getNE(), b);
		assertEquals(root.getNW(), a);
		
		//from Location 4
		d.swap("sl");
		assertEquals(root.getSE(), c);
		assertEquals(root.getSW(), d);
	}
	
	@Test
	public void testSwapRightSameParent() {
		//Construct a grid of 4 leaf nodes:
		LeafTile a = new LeafTile(4,1,8,12);
		LeafTile b = new LeafTile(4,2,8,8);
		LeafTile c = new LeafTile(4,3,12,8);
		LeafTile d = new LeafTile(4,4,12,12);
		
		InternalTile root = new InternalTile(null, null, null, null);
		root.setNE(a);
		root.setNW(b);
		root.setSW(c);
		root.setSE(d);
		
		//SWAP RIGHT:
		//from Location 2
		b.swap("sr");
		assertEquals(root.getNW(), a);
		assertEquals(root.getNE(), b);
		
		//from Location 3
		c.swap("sr");
		assertEquals(root.getSW(), d);
		assertEquals(root.getSE(), c);
	}
	
	@Test
	public void testSwapUpSameParent() {
		//Construct a grid of 4 leaf nodes:
		LeafTile a = new LeafTile(4,1,8,12);
		LeafTile b = new LeafTile(4,2,8,8);
		LeafTile c = new LeafTile(4,3,12,8);
		LeafTile d = new LeafTile(4,4,12,12);
		
		InternalTile root = new InternalTile(null, null, null, null);
		root.setNE(a);
		root.setNW(b);
		root.setSW(c);
		root.setSE(d);
		
		//SWAP UP:
		//from Location 3
		c.swap("su");
		assertEquals(root.getSW(), b);
		assertEquals(root.getNW(), c);
		
		//from Location 4
		d.swap("su");
		assertEquals(root.getSE(), a);
		assertEquals(root.getNE(), d);
	}

	@Test
	public void testSwapDownSameParent() {
		//Construct a grid of 4 leaf nodes:
		LeafTile a = new LeafTile(4,1,8,12);
		LeafTile b = new LeafTile(4,2,8,8);
		LeafTile c = new LeafTile(4,3,12,8);
		LeafTile d = new LeafTile(4,4,12,12);
		
		InternalTile root = new InternalTile(null, null, null, null);
		root.setNE(a);
		root.setNW(b);
		root.setSW(c);
		root.setSE(d);
			
		//SWAP DOWN:
		//from Location 1
		a.swap("sd");
		assertEquals(root.getNE(), d);
		assertEquals(root.getSE(), a);
		
		//from Location 2
		b.swap("sd");
		assertEquals(root.getNW(), c);
		assertEquals(root.getSW(), b);
	}
		
		@Test
		public void testSwapUpDifferentParent() {
		//Construct a grid of 16 leaf nodes:
		InternalTile root8NE = new InternalTile(null, null, null, null);
		root8NE.setNE(new LeafTile(4,1,0,12));
		root8NE.setNW(new LeafTile(4,2,0,8));
		LeafTile southwest1 = new LeafTile(4,3,4,8);
		LeafTile southeast1 = new LeafTile(4,4,4,12);		
		root8NE.setSW(southwest1);
		root8NE.setSE(southeast1);

		InternalTile root8NW = new InternalTile(null, null, null, null);
		root8NW.setNE(new LeafTile(4,1,0,4));
		root8NW.setNW(new LeafTile(4,2,0,0));
		root8NW.setSW(new LeafTile(4,3,4,0));
		root8NW.setSE(new LeafTile(4,4,4,4));

		InternalTile root8SW = new InternalTile(null, null, null, null);
		LeafTile northeast3 = new LeafTile(4,1,8,4);
		LeafTile southeast3 = new LeafTile(4,4,12,4);
		root8SW.setNE(northeast3);
		root8SW.setNW(new LeafTile(4,2,8,0));	
		root8SW.setSW(new LeafTile(4,3,12,0));
		root8SW.setSE(southeast3);

		LeafTile northeast4 = new LeafTile(4,1,8,12);
		LeafTile northwest4 = new LeafTile(4,2,8,8);
		LeafTile southwest4 = new LeafTile(4,3,12,8);
		LeafTile southeast4 = new LeafTile(4,4,12,12);
		
		InternalTile root8SE = new InternalTile(null, null, null, null);
		root8SE.setNE(northeast4);
		root8SE.setNW(northwest4);
		root8SE.setSW(southwest4);
		root8SE.setSE(southeast4);
		
		InternalTile root16 = new InternalTile(null, null, null, null);
		root16.setNE(root8NE);
		root16.setNW(root8NW);
		root16.setSW(root8SW);	
		root16.setSE(root8SE);
			
		//Swap leaf tile from Parent 1 with Leaf Tile from Parent 2
		//From Location 1
		northeast4.swap("su");
		assertEquals(root8SE.getNE(), southeast1);
		assertEquals(root8NE.getSE(), northwest4);
		
		//From Location 2
		northwest4.swap("su");
		assertEquals(root8SE.getNW(), southwest1);
		assertEquals(root8NE.getSW(), northwest4);
	}
		
		@Test
		public void testSwapDownDifferentParent() {
		//Construct a grid of 16 leaf nodes:
		InternalTile root8NE = new InternalTile(null, null, null, null);
		root8NE.setNE(new LeafTile(4,1,0,12));
		root8NE.setNW(new LeafTile(4,2,0,8));
		LeafTile southwest1 = new LeafTile(4,3,4,8);
		LeafTile southeast1 = new LeafTile(4,4,4,12);		
		root8NE.setSW(southwest1);
		root8NE.setSE(southeast1);

		InternalTile root8NW = new InternalTile(null, null, null, null);
		root8NW.setNE(new LeafTile(4,1,0,4));
		root8NW.setNW(new LeafTile(4,2,0,0));
		root8NW.setSW(new LeafTile(4,3,4,0));
		root8NW.setSE(new LeafTile(4,4,4,4));

		InternalTile root8SW = new InternalTile(null, null, null, null);
		LeafTile northeast3 = new LeafTile(4,1,8,4);
		LeafTile southeast3 = new LeafTile(4,4,12,4);
		root8SW.setNE(northeast3);
		root8SW.setNW(new LeafTile(4,2,8,0));	
		root8SW.setSW(new LeafTile(4,3,12,0));
		root8SW.setSE(southeast3);

		LeafTile northeast4 = new LeafTile(4,1,8,12);
		LeafTile northwest4 = new LeafTile(4,2,8,8);
		LeafTile southwest4 = new LeafTile(4,3,12,8);
		LeafTile southeast4 = new LeafTile(4,4,12,12);
		
		InternalTile root8SE = new InternalTile(null, null, null, null);
		root8SE.setNE(northeast4);
		root8SE.setNW(northwest4);
		root8SE.setSW(southwest4);
		root8SE.setSE(southeast4);
		
		InternalTile root16 = new InternalTile(null, null, null, null);
		root16.setNE(root8NE);
		root16.setNW(root8NW);
		root16.setSW(root8SW);	
		root16.setSE(root8SE);
	
		//SWAP DOWN
		//From Location 3
		southwest1.swap("sd");
		assertEquals(root8NE.getSW(), northwest4);
		assertEquals(root8SE.getNW(), southwest1);
	
	
		//From Location 4
		southeast1.swap("sd");
		assertEquals(root8SE.getNE(), southeast1);
		assertEquals(root8NE.getSE(), northwest4);
	}

		@Test
		public void testSwapLeftDifferentParent() {
		//Construct a grid of 16 leaf nodes:
		InternalTile root8NE = new InternalTile(null, null, null, null);
		root8NE.setNE(new LeafTile(4,1,0,12));
		root8NE.setNW(new LeafTile(4,2,0,8));
		LeafTile southwest1 = new LeafTile(4,3,4,8);
		LeafTile southeast1 = new LeafTile(4,4,4,12);		
		root8NE.setSW(southwest1);
		root8NE.setSE(southeast1);

		InternalTile root8NW = new InternalTile(null, null, null, null);
		root8NW.setNE(new LeafTile(4,1,0,4));
		root8NW.setNW(new LeafTile(4,2,0,0));
		root8NW.setSW(new LeafTile(4,3,4,0));
		root8NW.setSE(new LeafTile(4,4,4,4));

		InternalTile root8SW = new InternalTile(null, null, null, null);
		LeafTile northeast3 = new LeafTile(4,1,8,4);
		LeafTile southeast3 = new LeafTile(4,4,12,4);
		root8SW.setNE(northeast3);
		root8SW.setNW(new LeafTile(4,2,8,0));	
		root8SW.setSW(new LeafTile(4,3,12,0));
		root8SW.setSE(southeast3);

		LeafTile northeast4 = new LeafTile(4,1,8,12);
		LeafTile northwest4 = new LeafTile(4,2,8,8);
		LeafTile southwest4 = new LeafTile(4,3,12,8);
		LeafTile southeast4 = new LeafTile(4,4,12,12);
		
		InternalTile root8SE = new InternalTile(null, null, null, null);
		root8SE.setNE(northeast4);
		root8SE.setNW(northwest4);
		root8SE.setSW(southwest4);
		root8SE.setSE(southeast4);
		
		InternalTile root16 = new InternalTile(null, null, null, null);
		root16.setNE(root8NE);
		root16.setNW(root8NW);
		root16.setSW(root8SW);	
		root16.setSE(root8SE);
	
		//SWAP LEFT
		//From Location 2
		northwest4.swap("sl");
		assertEquals(root8SE.getNW(), northeast3);
		assertEquals(root8SW.getNE(), northwest4);
		
		//From Location 3
		southwest4.swap("sl");
		assertEquals(root8SE.getSW(), southeast3);
		assertEquals(root8SW.getSE(), southwest4);
	}
		
		@Test
		public void testSwapRightDifferentParent() {
		//Construct a grid of 16 leaf nodes:
		InternalTile root8NE = new InternalTile(null, null, null, null);
		root8NE.setNE(new LeafTile(4,1,0,12));
		root8NE.setNW(new LeafTile(4,2,0,8));
		LeafTile southwest1 = new LeafTile(4,3,4,8);
		LeafTile southeast1 = new LeafTile(4,4,4,12);		
		root8NE.setSW(southwest1);
		root8NE.setSE(southeast1);

		InternalTile root8NW = new InternalTile(null, null, null, null);
		root8NW.setNE(new LeafTile(4,1,0,4));
		root8NW.setNW(new LeafTile(4,2,0,0));
		root8NW.setSW(new LeafTile(4,3,4,0));
		root8NW.setSE(new LeafTile(4,4,4,4));

		InternalTile root8SW = new InternalTile(null, null, null, null);
		LeafTile northeast3 = new LeafTile(4,1,8,4);
		LeafTile southeast3 = new LeafTile(4,4,12,4);
		root8SW.setNE(northeast3);
		root8SW.setNW(new LeafTile(4,2,8,0));	
		root8SW.setSW(new LeafTile(4,3,12,0));
		root8SW.setSE(southeast3);

		LeafTile northeast4 = new LeafTile(4,1,8,12);
		LeafTile northwest4 = new LeafTile(4,2,8,8);
		LeafTile southwest4 = new LeafTile(4,3,12,8);
		LeafTile southeast4 = new LeafTile(4,4,12,12);
		
		InternalTile root8SE = new InternalTile(null, null, null, null);
		root8SE.setNE(northeast4);
		root8SE.setNW(northwest4);
		root8SE.setSW(southwest4);
		root8SE.setSE(southeast4);
		
		InternalTile root16 = new InternalTile(null, null, null, null);
		root16.setNE(root8NE);
		root16.setNW(root8NW);
		root16.setSW(root8SW);	
		root16.setSE(root8SE);
	
		//SWAP RIGHT
		//From Location 2
		northeast3.swap("sr");
		assertEquals(root8SE.getNW(), northeast3);
		assertEquals(root8SW.getNE(), northwest4);
		
		//From Location 3
		southeast3.swap("sr");
		assertEquals(root8SE.getSW(), southeast3);
		assertEquals(root8SW.getSE(), southwest4);
	}
		
		
}
