package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private Label scoreLabel;
	private Label movesLabel;
	private Label fruitLabel;

	public ScorePanel() {
		setStyle("-fx-background-color: #5490ff");
		scoreLabel = new Label("0");
		scoreLabel.setAlignment(Pos.CENTER);
		scoreLabel.setStyle("-fx-font-size: 24");
		setCenter(scoreLabel);

		int maxMoves;
		if(CandyGame.getLevelClass() == Level1.class) {
			maxMoves = Level1.getMaxMoves();
		}
		else if(CandyGame.getLevelClass() == Level2.class) {
			maxMoves = Level2.getMaxMoves();
		}
		else{
			maxMoves = Level3.getMaxMoves();
			fruitLabel = new Label("Fruits: " + Level3.getRequiredDrops());
			fruitLabel.setAlignment(Pos.CENTER_RIGHT);
			fruitLabel.setStyle("-fx-font-size: 24");
			setRight(fruitLabel);
			
		}

		movesLabel = new Label("Moves: " + maxMoves);
		movesLabel.setAlignment(Pos.CENTER_LEFT);
		movesLabel.setStyle("-fx-font-size: 24");
		setLeft(movesLabel);

	}

	public void updateScore(String text) {
		scoreLabel.setText(text);
	}

	public void updateRemainingMoves(String text) {
		movesLabel.setText("Moves: " + text);
	}
	
	public void updateRemainingFruits(String text) {
		fruitLabel.setText("Fruits: " + text);
	}

}