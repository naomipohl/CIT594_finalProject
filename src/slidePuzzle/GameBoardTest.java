package slidePuzzle;

import static org.junit.Assert.*;
import org.junit.Test;
 
public class GameBoardTest {
	

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
	public void testsPopulateDis(){
		GameBoard gboard = new GameBoard(16, 1);
		gboard.populateDis();

		String[][] dis = gboard.getDis();
		
		assertEquals(dis[0][12], "___");
		assertEquals(dis[1][1], "  ");
	}
	
	@Test
	public void testWipeDisplay() {
		GameBoard myGB3 = new GameBoard(16,1);	
		myGB3.populateDis();
		
		Tile a = new LeafTile(2, 1, 0, 4);
		LeafTile b = new LeafTile(2, 2, 0, 4);
		LeafTile c = new LeafTile(2, 3, 0, 4);
		LeafTile d = new LeafTile(2, 4, 0, 4);
		
		
		Tile NE = new InternalTile(a, b, d, c);
		((InternalTile)NE).setDepth(4);
		((InternalTile)NE).setLocation(1);
		
		LeafTile NW = new LeafTile(4, 2, 0, 0);
		LeafTile SW = new LeafTile(4, 3, 0, 0);
		LeafTile SE = new LeafTile(4, 4, 0, 0);
		
		InternalTile root = new InternalTile(NE, NW, SE, SW);
		
		myGB3.root = root;
		myGB3.traverseTree(root); //updates the display for each leaf
		String[][] dis = myGB3.getDis();
		
		
		//verify that board is populated (without visual inspection)
		for (int i = 1; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				assertFalse(dis[i][j].equals(""));
			}
		}
		
		myGB3.wipeDisplay();
		
		//verify that board has been wiped except for top border
		for (int i = 1; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				assertEquals(dis[i][j],"");
			}
		}
		
	}




}
