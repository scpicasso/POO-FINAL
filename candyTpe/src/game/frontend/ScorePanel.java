package game.frontend;

import game.backend.level.Level1;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ScorePanel extends BorderPane {

	private Label scoreLabel;
	private Label movesLabel;

	public ScorePanel() {
		setStyle("-fx-background-color: #5490ff");
		scoreLabel = new Label("Points: 0");
		scoreLabel.setAlignment(Pos.CENTER);
		scoreLabel.setStyle("-fx-font-size: 24");
		setCenter(scoreLabel);
	
		movesLabel = new Label("Moves: " + Level1.getMaxMoves());
		movesLabel.setAlignment(Pos.CENTER_LEFT);
		movesLabel.setStyle("-fx-font-size: 24");
		setLeft(movesLabel);
		
	}
	
	public void updateScore(String text) {
		scoreLabel.setText("Points: " + text);
	}
	
	public void updateRemainingMoves(String text) {
		movesLabel.setText("Moves: " + text);
	}

}