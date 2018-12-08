package game.backend.cell;

import game.backend.Grid;
import game.backend.move.Direction;
import game.backend.element.Cherry;
import game.backend.element.Element;
import game.backend.element.GapElement;
import game.backend.element.Nothing;

public class GapCell extends Cell{

	
	public GapCell(Grid grid) {
		super(grid);
		setContent(new GapElement());
	}
	
	
	
	public Element getTrueContent() {
		Cell[] around = getSurroundings();
		Cell up = around[Direction.UP.ordinal()];
		Element e;
		if(up instanceof GapCell) {
			e = ((GapCell) up).getTrueContent();
		}
		else {
			e = up.getAndClearContent();
		}
		return e;

	}
	
	
}


