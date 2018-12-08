package game.backend.element;

public class GapElement extends Element{
	
	public GapElement() {
	}
	
	@Override
	public boolean isMovable() {
		return false;
	}
	

	
	@Override
	public String getKey() {
		return "GAP";
	}

}
