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

		
		//everything in int array should be 8 after initial split:
		for(int i = 0; i < myGB.maxDepth; i++) {
			for(int j = 0; j < myGB.maxDepth; j++) {
				assertEquals(myGB.ordToPrint[i][j],8);
			}
		}
		
		
		
		
		
		
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
		
		//make sure the appropriate elements are all 2s:
		for(int i = 8; i < 12; i++) {
			for(int j = 12; j < 16; j++) {
				assertEquals(myGB.ordToPrint[i][j],2);
			}
		}
		
		
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
		
		
		//check for updated values
		assertEquals(myGB.ordToPrint[10][12],1);
		assertEquals(myGB.ordToPrint[11][12],1);
		assertEquals(myGB.ordToPrint[10][13],1);
		assertEquals(myGB.ordToPrint[11][13],1);
	}

}
