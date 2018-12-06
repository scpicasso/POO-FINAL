package game.backend;

public abstract class GameState {
	
	private long score = 0;
	private int moves = 0;
	private int drops = 0;
	
	public void addScore(long value) {
		this.score = this.score + value;
	}
	
	public long getScore(){
		return score;
	}
	
	public void addMove() {
		moves++;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public int getDrops() {
		return drops;
	}
	
	public void addDrops() {
		drops++;
	}
		
	
	public abstract boolean gameOver();
	
	public abstract boolean playerWon();
	
	public abstract int getRemainingMoves();


}
