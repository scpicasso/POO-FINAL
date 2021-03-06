package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;
import game.backend.level.Level2;
import game.backend.level.Level3;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CandyFrame extends VBox {

	private static final int CELL_SIZE = 65;

	private BoardPanel boardPanel;
	private ScorePanel scorePanel;
	private ImageManager images;
	private Point2D lastPoint;
	private CandyGame game;
	private boolean gameOver = false;

	
	public CandyFrame(CandyGame game) {
		this.game = game;
		getChildren().add(new AppMenu());
		images = new ImageManager();
		boardPanel = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		getChildren().add(boardPanel);
		scorePanel = new ScorePanel();
		getChildren().add(scorePanel);
		game.initGame();
		GameListener listener;
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				Timeline timeLine = new Timeline();
				Duration frameGap = Duration.millis(100);
				Duration frameTime = Duration.ZERO;
				for (int i = game().getSize() - 1; i >= 0; i--) {
					for (int j = game().getSize() - 1; j >= 0; j--) {
						int finalI = i;
						int finalJ = j;
						Cell cell = CandyFrame.this.game.get(i, j);
						Element element = cell.getContent();
						Image image = images.getImage(element);
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, null)));
						timeLine.getKeyFrames().add(new KeyFrame(frameTime, e -> boardPanel.setImage(finalI, finalJ, image)));
					}
					frameTime = frameTime.add(frameGap);
				}
				timeLine.play();
			}
			@Override
			public void cellExplosion(Element e) {
				//
			}
		});

		listener.gridUpdated();
		
		Class<?> levelClass = CandyGame.getLevelClass();
		
		
		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			if (lastPoint == null) {
				lastPoint = translateCoords(event.getX(), event.getY());
				if(levelClass == Level2.class) {
					if((int)lastPoint.getX() >= Level2.getStartGap() && (int)lastPoint.getX() <= Level2.getEndGap()) {
						lastPoint = null;
					}
				}
				System.out.println("Get first = " +  lastPoint);
			} 
			else {
				Point2D newPoint = translateCoords(event.getX(), event.getY());
				boolean isValid = true;
				if (newPoint != null) {
					if(levelClass == Level2.class) {
						if((int)newPoint.getX() >= Level2.getStartGap() && (int)newPoint.getX() <=  Level2.getEndGap()) {
							newPoint = null;
							isValid = false;
						}
					}
					
				System.out.println("Get second = " +  newPoint);
				
				if(isValid && !gameOver) {
					game().tryMove((int)lastPoint.getX(), (int)lastPoint.getY(), (int)newPoint.getX(), (int)newPoint.getY());
					String score = ((Long)game().getScore()).toString();
					String remainingMoves = ((Integer)game().getRemainingMoves()).toString();
						
					//Level3 has fruits
					String fruits;
					if( levelClass == Level3.class) {
						fruits = ((Integer)(Level3.getRequiredDrops() - game().getDrops())).toString();
						scorePanel.updateRemainingFruits(fruits);
					}
					
					if (game().isFinished()) {
						gameOver = true;
						if (game().playerWon()) {
							score = score + " Finished - Player Won!";
						} else {
							score = score + " Finished - Loser !";
						}
					}
					scorePanel.updateScore(score);
					scorePanel.updateRemainingMoves(remainingMoves);
				}
				lastPoint = null;
				}
			}
		});
		
	}

	private CandyGame game() {
		return game;
	}

	private Point2D translateCoords(double x, double y) {
		double i = x / CELL_SIZE;
		double j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point2D(j, i) : null;
	}

}
