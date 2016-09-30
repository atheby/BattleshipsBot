
public class Board {
	
	private String[][] board;
	private int size = 10;
	
	public Board() {
		board = new String[getSize()][getSize()];
		clearBoard();
	}
	
	private void clearBoard() {
		for(int r = 0; r < getSize(); r++) {
			for(int c = 0; c < getSize(); c++)
				board[r][c] = Symbols.EMPTY;
		}
	}
	
	public void setSymbol(int row, int column, String symbol) {
		board[row][column] = symbol;
	}
	
	public int getSize() {
		return size;
	}
	
	public String[][] getBoard() {
		return board;
	}
	
	public void printBoard() {
		for(int r = 0; r < getSize(); r++) {
			System.out.print("|");
			for(int c = 0; c < getSize(); c++)
				System.out.print(" " + board[r][c] + " ");
			System.out.println("|");
		}
	}
	
	public boolean isSpaceFree(Position pos, Ship ship) {
		if(pos.getOrientation().equals(Orientation.Horizontally))
			for(int c = 0; c < ship.getSize(); c++)
				if(!getBoard()[pos.getRow()][pos.getColumn() + c].equals(Symbols.EMPTY))
					return false;
		if(pos.getOrientation().equals(Orientation.Vertically))
			for(int r = 0; r < ship.getSize(); r++)
				if(!getBoard()[pos.getRow() + r][pos.getColumn()].equals(Symbols.EMPTY))
					return false;
		return true;
	}
	
	public void updateFromBuffer(String buffer) {
		for(int r = 0; r < getSize(); r++)
			for(int c = 0; c < getSize(); c++) {
				setSymbol(r, c, buffer.substring(0, 1));
				buffer = buffer.substring(1);
			}
	}
	
	public int getHitsOnBoard() {
		int hits = 0;
		for(int r = 0; r < getSize(); r++) {
			for(int c = 0; c < getSize(); c++)
				if(board[r][c].equals(Symbols.HIT))
					hits++;
		}
		return hits;
	}
}
