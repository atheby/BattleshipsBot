public class Game {

	public static void main(String[] args) {
		Board board = new Board(10);
		PositionGenerator pg = new PositionGenerator(board);
		pg.printAllCoordinates();
	}
}
