package game.backend.cell;

import game.backend.Grid;
import game.backend.move.Direction;
import game.backend.element.Element;
import game.backend.element.GapElement;
import game.backend.element.Nothing;

public class GapCell extends Cell{

	public GapCell(Grid grid) {
		super(grid);
		setContent(new GapElement());
	}
	
	public boolean continueGap(Element content) {
		Cell[] around = getSurroundings();
		Grid grid = getGrid();
		Cell down = around[Direction.DOWN.ordinal()];
		if(down instanceof GapCell) {
			return ((GapCell) down).continueGap(content);
		}
		else {
			setContent(content);
			grid.wasUpdated();
			boolean ret = down.fallUpperContent();
			setContent(new GapElement());
			grid.wasUpdated();
			return ret;
		}
	}
	
	public boolean doesItFall() {
		Cell[] around = getSurroundings();
		Cell down = around[Direction.DOWN.ordinal()];
		if(down instanceof GapCell) {
			return ((GapCell) down).doesItFall();
		}
		else {
			if(down.isEmpty()) 
				return true;
			else
				return false;
		}
	}
	
	@Override
	public boolean isMovable(){
		return true;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public boolean fallUpperContent() {
		Cell[] around = getSurroundings();
		Grid grid = getGrid();
		Cell up = around[Direction.UP.ordinal()];
		boolean keepGoing = doesItFall();
		if(!keepGoing) {
			return false;
		}
		if (!up.isEmpty() && up.isMovable()) {
			Element content = up.getAndClearContent();
			grid.wasUpdated();
			Cell down = around[Direction.DOWN.ordinal()];
			if(down instanceof GapCell) {
				return ((GapCell) down).continueGap(content);
			}
			else {
				setContent(content);
				grid.wasUpdated();
				boolean ret = down.fallUpperContent();
				setContent(new Nothing());
				grid.wasUpdated();
				return ret;
			}
		} 
		return false;
	}

}
