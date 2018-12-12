package game.backend.element;

public class Fruit extends Element{
	
	private FruitType fruit;
	
	public Fruit(FruitType fruit) {
		this.fruit = fruit;
	}
	
	public FruitType getFruit() {
		return fruit;
	}

	@Override
	public boolean isMovable() {
		return true;
	}
	
	@Override
	public String getFullKey() {
		return fruit.toString() + "-FRUIT";
	}
	
	@Override
	public String getKey() {
		return "FRUIT";
	}
	
	
	@Override
	public long getScore() {
		return 50;
	}
	
	@Override
	public int hashCode() {
		return ((fruit == null) ? 0 : fruit.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Fruit))
			return false;
		Fruit other = (Fruit) obj;
		if (fruit != other.fruit)
			return false;
		return true;
	}

}
