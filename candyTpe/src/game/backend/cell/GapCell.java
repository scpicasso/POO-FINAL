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
	
	public Cell bridgeContent() {
		Cell[] around = getSurroundings();
		Cell up = around[Direction.UP.ordinal()];
		if(up.getContent() instanceof GapElement) {
			return ((GapCell) up).bridgeContent();
		}
		boolean b = up.fallUpperContent();
		return up;
	}
	
	@Override
	public boolean fallUpperContent() {
		return true;
	}
	
	
}


