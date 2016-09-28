
public class Ship implements Comparable<Ship> {
	
	private int size;
	private Position position;
	
	public Ship(int s) {
		size = s;
	}
	
	public int getSize() {
		return size;
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
	
	@Override
	public int compareTo(Ship s) {
		if(getSize() < s.getSize())
			return -1;
		if(getSize() == s.getSize())
			return 0;
		return 1;
	}
}
