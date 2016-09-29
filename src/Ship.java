
public class Ship implements Comparable<Ship> {
	
	private int size;
	
	public Ship(int s) {
		size = s;
	}
	
	public int getSize() {
		return size;
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
