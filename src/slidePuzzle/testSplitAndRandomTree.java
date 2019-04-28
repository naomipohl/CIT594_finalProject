package slidePuzzle;

import static org.junit.Assert.*;

import org.junit.Test;

public class testSplitAndRandomTree {

	

	static GameBoard myGB;
	static LeafTile hardCodeRoot;
	
	
	
	
	
	
	@Test
	public void test() {
		
		/*
		myGB = new GameBoard(16,1);
		hardCodeRoot = new LeafTile(16,2,0,0); //inputs are parent location = 0,0 and position = 2 (top left)
		InternalTile parent = hard
		InternalTile newRoot = hardCodeRoot.split(hardCodeRoot);
		myGB.populateBoard(newRoot);
		myGB.printIntArray(myGB.ordToPrint);
		

		newRoot.setNW(((LeafTile) newRoot.getNW()).split((LeafTile) newRoot.getNW()));
		myGB.populateBoard(newRoot);
		myGB.printIntArray(myGB.ordToPrint);
		*/
		
		
		
		
		GameBoard myGB2 = new GameBoard(16,1);
		
		//Tile randomRoot = myGB2.createTree();
		
		//Tile randomRoot = new LeafTile(16,1,0,0);
		//randomRoot = ((LeafTile) randomRoot).split(randomRoot);

		
		
		
		myGB2.populateBoard(myGB2.root); 
		

		myGB2.printIntArray(myGB2.ordToPrint);
		
		
		
	}

}
