package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.cell.GapCell;
import game.backend.element.Wall;

public class Level2 extends Grid{
	
	private static int REQUIRED_SCORE = 3000; 
	private static int MAX_MOVES = 20; 
	
	/* Row where the gap starts and ends */
	private static int startGap = 3;
	private static int endGap = 5;
	
	private Cell wallCell;
	private Cell candyGenCell;
	private Cell gapCell;
	
	
	public static int getMaxMoves() {
		return MAX_MOVES;
	}
	
	
	public static int getStartGap() {
		return startGap;
	}
	
	
	public static int getEndGap() {
		return endGap;
	}
	
	
	@Override
	protected GameState newState() {
		return new Level2State(REQUIRED_SCORE, MAX_MOVES);
	}

	
	@Override
	protected void fillCells() {
		
		wallCell = new Cell(this);
		wallCell.setContent(new Wall());
		candyGenCell = new CandyGeneratorCell(this);
		gapCell = new GapCell(this);

		
		//corners
		g()[0][0].setAround(candyGenCell, g()[1][0], wallCell, g()[0][1]);
		g()[0][SIZE-1].setAround(candyGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
		g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
		g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);

		//upper line cells
		for (int j = 1; j < SIZE-1; j++) {
			g()[0][j].setAround(candyGenCell,g()[1][j],g()[0][j-1],g()[0][j+1]);
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
		
		//create the gap cells
				for(int i = startGap; i <= endGap; i++) {
					for (int j = 0; j < SIZE; j++) {
						g()[i][j]= gapCell;
					}
				}
				
		//to bridge the cells above and below the gap
		//bridge center cells
		for (int j = 1; j < SIZE-1; j++) {
				g()[startGap-1][j].setAround(g()[startGap-2][j],g()[endGap+1][j],g()[startGap-1][j-1],g()[startGap-1][j+1]);
		}
		for (int j = 1; j < SIZE-1; j++) {
			g()[endGap+1][j].setAround(g()[startGap-1][j],g()[endGap+2][j],g()[endGap+1][j-1],g()[endGap+1][j+1]);
		}
		
		//bridge left corner cells
		g()[startGap-1][0].setAround(g()[startGap-2][0],g()[endGap+1][0],wallCell,g()[startGap-1][1]);
		g()[endGap+1][0].setAround(g()[startGap-1][0],g()[endGap+2][0],wallCell,g()[endGap+1][1]);
		
		//bridge right corner cells
		g()[startGap-1][SIZE-1].setAround(g()[startGap-2][SIZE-1],g()[endGap+1][SIZE-1],g()[startGap-1][SIZE-2],wallCell);
		g()[endGap+1][SIZE-1].setAround(g()[startGap-1][SIZE-1],g()[endGap+2][SIZE-1],g()[endGap+1][SIZE-2],wallCell);
	}
	
	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if (ret = super.tryMove(i1, j1, i2, j2)) {
			state().addMove();
		}
		return ret;
	}
	
	private class Level2State extends GameState {
		private long requiredScore;
		private int maxMoves;
		
		public Level2State(long requiredScore, int maxMoves) {
			this.requiredScore = requiredScore;
			this.maxMoves = maxMoves;
		}
		
		public boolean gameOver() {
			return playerWon() || getMoves() >= maxMoves;
		}
		
		public boolean playerWon() {
			return getScore() > requiredScore;
		}

		@Override
		public int getRemainingMoves() {
			return maxMoves - getMoves();
		}

	}
}
