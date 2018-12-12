package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;

import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.Fruit;
import game.backend.element.FruitType;

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
				int i = (int)(Math.random() * FruitType.values().length);
				return new Fruit(FruitType.values()[i]);	
			}
		}
		int i = (int)(Math.random() * CandyColor.values().length);
		return new Candy(CandyColor.values()[i]);
	}

}