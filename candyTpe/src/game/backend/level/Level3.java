package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyAndDropsGeneratorCell;
import game.backend.cell.CandyGeneratorCell;


import game.backend.cell.Cell;
import game.backend.element.Fruit;
import game.backend.element.Wall;

public class Level3 extends Grid{
	
	private static int REQUIRED_DROPS = 3; 
	private static int MAX_MOVES = 40; 
	
	
	private Cell wallCell;
	private Cell candyDropGenCell;
	private Cell candyGenCell;
	
	public static int getMaxMoves() {
		return MAX_MOVES;
	}
	
	public static int getRequiredDrops() {
		return REQUIRED_DROPS;
	}
	
	@Override
	protected GameState newState() {
		return new Level3State(REQUIRED_DROPS, MAX_MOVES);
	}

	@Override
	protected void fillCells() {
		
		wallCell = new Cell(this);
		wallCell.setContent(new Wall());
		candyDropGenCell = new CandyAndDropsGeneratorCell(this,REQUIRED_DROPS);
		candyGenCell = new CandyGeneratorCell(this);

		
		//corners
		g()[0][0].setAround(candyGenCell, g()[1][0], wallCell, g()[0][1]);
		g()[0][SIZE-1].setAround(candyGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
		g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
		g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);


		
		//upper line cells
		for (int j = 1; j < SIZE-1; j++) {
			g()[0][j].setAround(candyDropGenCell,g()[0][j],g()[0][j-1],g()[0][j+1]);
		}
			
		//bottom line cells
		for (int j = 1; j < SIZE-1; j++) {
			g()[SIZE-1][j].setAround(g()[SIZE-2][j], wallCell, g()[SIZE-1][j-1],g()[SIZE-1][j+1]);
		}
		//left line cells
		for (int i = 1; i < SIZE-1; i++) {
			g()[i][0].setAround(g()[i-1][0],g()[i+1][0], wallCell ,g()[i][1]);
		}
		//right line cells
		for (int i = 1; i < SIZE-1; i++) {
			g()[i][SIZE-1].setAround(g()[i-1][SIZE-1],g()[i+1][SIZE-1], g()[i][SIZE-2], wallCell);
		}		
		//central cells
		for (int i = 1; i < SIZE-1; i++) {
			for (int j = 1; j < SIZE-1; j++) {
				g()[i][j].setAround(g()[i-1][j],g()[i+1][j],g()[i][j-1],g()[i][j+1]);
			}
		}
	}
	
	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if (ret = super.tryMove(i1, j1, i2, j2)) {
			while (checkForFruit()) {
				fallElements();
			}
			state().addMove();
		}
		return ret;
	}
	
	public boolean checkForFruit() {
		boolean flag = false;
		for(int j=0;j < SIZE; j++) {
			if((g()[SIZE-1][j].getContent()) instanceof Fruit) {
				clearContentFruit(SIZE-1,j);
				state().addDrops();
				flag = true;
			}
		}
		return flag;
	}
	
	
	public void clearContentFruit(int i, int j){
		g()[i][j].clearContentFruit();
	}
	

	private class Level3State extends GameState {
		private int requiredDrops;
		private int maxMoves;
		
		public Level3State(int requiredDrops, int maxMoves) {
			this.requiredDrops = requiredDrops;
			this.maxMoves = maxMoves;
		}
		
		public boolean gameOver() {
			return playerWon() || getMoves() >= maxMoves;
		}
		
		public boolean playerWon() {
			return getDrops() >= requiredDrops;
		}

		@Override
		public int getRemainingMoves() {
			return maxMoves - getMoves();
		}
		
	}
}
