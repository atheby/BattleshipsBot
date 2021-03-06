import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PositionGenerator {
	
	private List<MyShip> ships;
	private Board board;
	
	public PositionGenerator() {
		ships = new ArrayList<MyShip>();
		board = new Board();
		addShips();
		generatePosition();
	}
	
	private void addShips() {
		ships.add(new MyShip(1));
		ships.add(new MyShip(1));
		ships.add(new MyShip(2));
		ships.add(new MyShip(2));
		ships.add(new MyShip(3));
		ships.add(new MyShip(4));
		ships.add(new MyShip(5));
		Collections.sort(getShips(), Collections.reverseOrder());
	}
	
	private void generatePosition() {
		Position pos;
		int max;
		for(MyShip ship : getShips()) {
			max = board.getSize() - ship.getSize();
			do {
				pos = new Position();
				pos.setOrientation(Orientation.values()[getRandom(0, 1)]);
				if(pos.getOrientation().equals(Orientation.Horizontally)) {
					pos.setRow(getRandom(0, board.getSize() - 1));
					pos.setColumn(getRandom(0, max));
				}
				else {
					pos.setRow(getRandom(0, max));
					pos.setColumn(getRandom(0, board.getSize() - 1));
				}
			}
			while(!board.isSpaceFree(pos, ship));
			ship.setPosition(pos);
			putOnBoard(ship);
		}
	}
	
	private void putOnBoard(MyShip s) {
		if(s.getPosition().getOrientation().equals(Orientation.Horizontally))
			for(int c = 0; c < s.getSize(); c++)
				board.getBoard()[s.getPosition().getRow()][s.getPosition().getColumn() + c] = Symbols.SHIP;
		else
			for(int r = 0; r < s.getSize(); r++)
				board.getBoard()[s.getPosition().getRow() + r][s.getPosition().getColumn()] = Symbols.SHIP;
	}
	
	private int getRandom(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public void printAllCoordinates() {
		Collections.sort(getShips());
		for(MyShip ship : getShips()) {
			ship.printCoordinates();
		}
	}
	
	public List<MyShip> getShips() {
		return ships;
	}
}
