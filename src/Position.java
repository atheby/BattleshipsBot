
public class Position {
	private int row, column;
	private Orientation orientation;
	
	public void setRow(int r) {
		row = r;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setColumn(int c) {
		column = c;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setOrientation(Orientation o) {
		orientation = o;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
}
