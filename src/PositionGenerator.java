import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PositionGenerator {
	
	private List<Ship> ships;
	private Board board;
	
	public PositionGenerator(Board b) {
		ships = new ArrayList<Ship>();
		board = b;
		addShips();
		generatePosition();
	}
	
	private void addShips() {
		ships.add(new Ship(1));
		ships.add(new Ship(1));
		ships.add(new Ship(2));
		ships.add(new Ship(2));
		ships.add(new Ship(3));
		ships.add(new Ship(4));
		ships.add(new Ship(5));
		Collections.reverse(ships);
	}
	
	private void generatePosition() {
		Position pos;
		int max;
		for(Ship ship : ships) {
			max = board.getSize() - ship.getSize();
			do {
				pos = new Position();
				pos.setOrientation(Orientation.values()[getRandom(0, 1)]);
				if(pos.getOrientation().equals(Orientation.Horizontally)) {
					pos.setRow(getRandom(0, 9));
					pos.setColumn(getRandom(0, max));
				}
				else {
					pos.setRow(getRandom(0, max));
					pos.setColumn(getRandom(0, 9));
				}
			}
			while(!isSpaceFree(pos, ship));
			ship.setPosition(pos);
			putOnBoard(ship);
		}
	}
	
	private boolean isSpaceFree(Position pos, Ship ship) {
		if(pos.getOrientation().equals(Orientation.Horizontally))
			for(int c = 0; c < ship.getSize(); c++)
				if(!board.getBoard()[pos.getRow()][pos.getColumn() + c].equals(Symbols.EMPTY))
					return false;
		if(pos.getOrientation().equals(Orientation.Vertically))
			for(int r = 0; r < ship.getSize(); r++)
				if(!board.getBoard()[pos.getRow() + r][pos.getColumn()].equals(Symbols.EMPTY))
					return false;
		return true;
	}
	
	private void putOnBoard(Ship s) {
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
		Collections.sort(ships);
		for(Ship ship : getShips()) {
			ship.printCoordinates();
		}
	}
	
	public List<Ship> getShips() {
		return ships;
	}
}
