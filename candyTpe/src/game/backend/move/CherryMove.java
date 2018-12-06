package game.backend.move;

import game.backend.Grid;
import game.backend.element.Cherry;
import game.backend.element.Candy;

public class CherryMove extends Move{

	public CherryMove(Grid grid) {
		super(grid);
	}

	@Override
	public void removeElements() {
		return;
	}

}
