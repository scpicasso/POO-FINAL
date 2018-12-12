package game.backend.element;

public class Cherry extends Element{

	@Override
	public boolean isMovable() {
		return true;
	}
	
	@Override
	public String getKey() {
		return "CHERRY";
	}
	
	
	@Override
	public long getScore() {
		return 50;
	}

}
