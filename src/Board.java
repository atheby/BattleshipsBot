
public class Board {
	
	private String[][] board;
	private int size;
	
	public Board(int s) {
		size = s;
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
}
