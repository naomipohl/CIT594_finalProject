package slidePuzzle;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
 
public class GameBoardTest {
	
	static GameBoard myGB;
	static LeafTile hardCodeRoot;
	
	@Before
	public void beforeEachTestMethod() {
		myGB = new GameBoard(16,1);
		hardCodeRoot = new LeafTile(16,2,0,0); //inputs are parent location = 0,0 and position = 2 (top left)
	}
	
	/*
	 * Several methods in GameBoard.java are the result of calculations 
	 * with random numbers. These methods have been tested with the Eclipse
	 * debugger and they include:
	 * - GameBoard()
	 * - createTree()
	 * - placeDog()
	 * - placeWalker()
	 */
	
	
	
	
	@Test
	public void testFindParent() {
		
		GameBoard myGB2 = new GameBoard(16,1);
		
		Tile a = new LeafTile(2, 1, 0, 4);
		LeafTile b = new LeafTile(2, 2, 0, 4);
		LeafTile c = new LeafTile(2, 3, 0, 4);
		LeafTile d = new LeafTile(2, 4, 0, 4);
		LeafTile e = new LeafTile(2, 1, 0, 0);
		
		Tile NE = new InternalTile(a, b, d, c);
		((InternalTile)NE).setDepth(4);
		((InternalTile)NE).setLocation(1);
		
		LeafTile NW = new LeafTile(4, 2, 0, 0);
		LeafTile SW = new LeafTile(4, 3, 0, 0);
		LeafTile SE = new LeafTile(4, 4, 0, 0);
		
		InternalTile root = new InternalTile(NE, NW, SE, SW);
		InternalTile fakeRoot = new InternalTile(NW, SW, NE, SE);
		
		assertEquals(myGB2.findParent(a, (Tile)root), NE);
		assertEquals(myGB2.findParent(b, (Tile)root), NE);
		assertEquals(myGB2.findParent(c, (Tile)root), NE);
		assertEquals(myGB2.findParent(d, (Tile)root), NE);
		
		assertNotEquals(myGB2.findParent(SE, (Tile)root), NE);
		assertNotEquals(myGB2.findParent(d, (Tile)root), NW);
		assertNotEquals(myGB2.findParent(a, (Tile)root), root);
		assertNotEquals(myGB2.findParent(a, (Tile)root), fakeRoot);
		
		assertNull(myGB2.findParent(e, (Tile)root));
	}
	
	@Test
	public void testUpdateDisplay() {
		GameBoard gboard = new GameBoard(16, 1);
		gboard.populateDis();
		LeafTile tr = new LeafTile(8, 1, 1, 0);
		LeafTile tl = new LeafTile(8, 2, 1, 0);
		LeafTile bl = new LeafTile(8, 3, 1, 0);
		LeafTile br = new LeafTile(8, 4, 1, 0);
		LeafTile tr1 = new LeafTile(4, 1, 1, 8);
		LeafTile tr2 = new LeafTile(4, 2, 1, 8);
		LeafTile tr3 = new LeafTile(4, 3, 1, 8);
		LeafTile tr4 = new LeafTile(4, 4, 1, 8);
		
		LeafTile tr11 = new LeafTile(2, 1, 1, 12);
		LeafTile tr12 = new LeafTile(2, 2, 1, 12);
		gboard.walker = tr12;
		LeafTile tr13 = new LeafTile(2, 3, 1, 12);
		LeafTile tr14 = new LeafTile(2, 4, 1, 12);
		
		LeafTile tr141 = new LeafTile(1, 1, 1, 14);
		gboard.dog = tr141;
		LeafTile tr142 = new LeafTile(1, 2, 1, 14);
		LeafTile tr143 = new LeafTile(1, 3, 1, 14);
		LeafTile tr144 = new LeafTile(1, 4, 1, 14);
		
		
		gboard.populateDis();
		gboard.updateDisplay(tr);
		gboard.updateDisplay(tl);
		gboard.updateDisplay(bl);
		gboard.updateDisplay(br);
		
		gboard.updateDisplay(tr1);
		gboard.updateDisplay(tr2);
		gboard.updateDisplay(tr3);
		gboard.updateDisplay(tr4);
		
		gboard.updateDisplay(tr11);
		gboard.updateDisplay(tr12);
		gboard.updateDisplay(tr13);
		gboard.updateDisplay(tr14);
		
		gboard.updateDisplay(tr141);
		gboard.updateDisplay(tr142);
		gboard.updateDisplay(tr143);
		gboard.updateDisplay(tr144);
		gboard.printBoard();
		assertEquals(gboard.dis[0][0], "___");
		assertEquals(gboard.dis[1][7], "  |");
		assertEquals(gboard.dis[1][8], "|  ");
		assertEquals(gboard.dis[16][15], "__|");
		assertEquals(gboard.dis[1][12], "|W ");
		assertEquals(gboard.dis[1][15], "|" + "D\u0332" + "|");
		assertEquals(gboard.dis[9][7], "  |");
		assertEquals(gboard.dis[8][5], "___");
		assertEquals(gboard.dis[4][4], "   ");
		assertEquals(gboard.dis[16][0], "|__");	
	}
	

	@Test
	public void testPopulateBoard() {
		//have already initialized 16x16 board without splits
		myGB.populateBoard(hardCodeRoot);
		
		//everything in int array should be 16 before splits occur:
		for(int i = 0; i < myGB.maxDepth; i++) {
			for(int j = 0; j < myGB.maxDepth; j++) {
				assertEquals(myGB.ordToPrint[i][j],16);
			}
		}
		
		 // Now let's split into four tiles of width 8:
		 
		LeafTile Leaf8_1 = new LeafTile(8,1,0,0);
		LeafTile Leaf8_2 = new LeafTile(8,2,0,0);
		LeafTile Leaf8_3 = new LeafTile(8,3,0,0);
		LeafTile Leaf8_4 = new LeafTile(8,4,0,0);
		
		InternalTile internal16 = new InternalTile(Leaf8_1,Leaf8_2,Leaf8_3,Leaf8_4);
		internal16.setDepth(16);
		myGB.populateBoard(internal16);

		
		//everything in int array should be 8 after initial split:
		for(int i = 0; i < myGB.maxDepth; i++) {
			for(int j = 0; j < myGB.maxDepth; j++) {
				assertEquals(myGB.ordToPrint[i][j],8);
			}
		}
		
		//Now let's split SE corner into four tiles of width 4:
		
		LeafTile Leaf4_1 = new LeafTile(4,1,0,0);
		LeafTile Leaf4_2 = new LeafTile(4,2,0,0);
		LeafTile Leaf4_3 = new LeafTile(4,3,0,0);
		LeafTile Leaf4_4 = new LeafTile(4,4,0,0);
		
		InternalTile internal8 = new InternalTile(Leaf4_1,Leaf4_2,Leaf4_3,Leaf4_4);
		internal8.setDepth(8);
		internal16.setSE(internal8);
		
		myGB.populateBoard(internal16);
		
		//verify that everything in largest NE,NW,SW tiles still 8 after initial split:
		for(int i = 0; i < myGB.maxDepth; i++) {
			for(int j = 0; j < 8; j++) {
				assertEquals(myGB.ordToPrint[i][j],8);
			}
		}
		for(int i = 0; i < 8; i++) {
			for(int j = 8; j < myGB.maxDepth; j++) {
				assertEquals(myGB.ordToPrint[i][j],8);
			}
		}
		
		//and the bottom right should be all 4s:
		for(int i = 8; i < myGB.maxDepth; i++) {
			for(int j = 8; j < myGB.maxDepth; j++) {
				assertEquals(myGB.ordToPrint[i][j],4);
			}
		}
		
		//Now let's split NE corner of SE tile into four tiles of width 2:
		
		LeafTile Leaf2_1 = new LeafTile(2,1,0,0);
		LeafTile Leaf2_2 = new LeafTile(2,2,0,0);
		LeafTile Leaf2_3 = new LeafTile(2,3,0,0);
		LeafTile Leaf2_4 = new LeafTile(2,4,0,0);
		
		InternalTile internal4 = new InternalTile(Leaf2_1,Leaf2_2,Leaf2_3,Leaf2_4);
		internal4.setDepth(4);
		internal8.setNE(internal4);
		
		myGB.populateBoard(internal16);
		
		//make sure the appropriate elements are all 2s:
		for(int i = 8; i < 12; i++) {
			for(int j = 12; j < 16; j++) {
				assertEquals(myGB.ordToPrint[i][j],2);
			}
		}
				
		
		//Finally, split SW corner of NE corner of bottom right tile into four tiles of width 1:
		
		LeafTile Leaf1_1 = new LeafTile(1,1,0,0);
		LeafTile Leaf1_2 = new LeafTile(1,2,0,0);
		LeafTile Leaf1_3 = new LeafTile(1,3,0,0);
		LeafTile Leaf1_4 = new LeafTile(1,4,0,0);
		
		InternalTile internal2 = new InternalTile(Leaf1_1,Leaf1_2,Leaf1_3,Leaf1_4);
		internal2.setDepth(2);
		internal4.setSW(internal2);
		
		myGB.populateBoard(internal16);
		
		
		//check for updated values
		assertEquals(myGB.ordToPrint[10][12],1);
		assertEquals(myGB.ordToPrint[11][12],1);
		assertEquals(myGB.ordToPrint[10][13],1);
		assertEquals(myGB.ordToPrint[11][13],1);
	}


}
