package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.Nothing;

public class GapCell extends Cell{

	public GapCell(Grid grid) {
		super(grid);
	}
	
	@Override
	public boolean isMovable(){
		return false;
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Element getContent() {
		return new Nothing();
	}
	
	@Override
	public Element getAndClearContent() {
		return getContent();
	}

	@Override
	public boolean fallUpperContent() {
		throw new IllegalStateException();
	}
	
	@Override
	public void setContent(Element content) {
		throw new IllegalStateException();
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}

}
