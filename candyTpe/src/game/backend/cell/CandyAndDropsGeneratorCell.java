package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;

import game.backend.element.CandyColor;
import game.backend.element.Cherry;
import game.backend.element.Element;

public class CandyAndDropsGeneratorCell extends CandyGeneratorCell {
	
	private int drops;
	
	public CandyAndDropsGeneratorCell(Grid grid, int drops) {
		super(grid);
		this.drops = drops;
		
	}
	
	@Override
	public Element getContent() {
		if(drops>0) {
			if((Math.random() * 100)< 3) {
				drops--;
				return new Cherry();	
			}
		}
		int i = (int)(Math.random() * CandyColor.values().length);
		return new Candy(CandyColor.values()[i]);
	}

}