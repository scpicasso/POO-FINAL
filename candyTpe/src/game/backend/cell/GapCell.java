package game.backend.cell;

import game.backend.Grid;
import game.backend.element.GapElement;

public class GapCell extends Cell{

	public GapCell(Grid grid) {
		super(grid);
		setContent(new GapElement());
	}
	
	
}


