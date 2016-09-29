
public class MyShip extends Ship {
	
	private Position position;
	
	public MyShip(int s) {
		super(s);
	}
	
	public void setPosition(Position p) {
		position = p;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void printCoordinates() {
		if(getSize() == 1)
			System.out.println(getPosition().getRow() + " " + getPosition().getColumn());
		else {
			if(getPosition().getOrientation().equals(Orientation.Horizontally))
				System.out.println(getPosition().getRow() + " " + getPosition().getColumn() + ":" + getPosition().getRow() + " " + (getPosition().getColumn() + getSize() - 1));
			if(getPosition().getOrientation().equals(Orientation.Vertically))
				System.out.println(getPosition().getRow() + " " + getPosition().getColumn() + ":" + (getPosition().getRow() + getSize() - 1) + " " + getPosition().getColumn());
		}
	}
}
