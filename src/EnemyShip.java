import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyShip extends Ship {

	private List<Position> possiblePositions;
	
	public EnemyShip(int s) {
		super(s);
		possiblePositions = new ArrayList<Position>();
	}
	
	public void addPosition(Position pos) {
		possiblePositions.add(pos);
	}
	
	public Position getFirstPosition() {
		return possiblePositions.remove(0);
	}
	
	public Position getRandomPosition() {
		return possiblePositions.remove(ThreadLocalRandom.current().nextInt(0, possiblePositions.size()));
	}
	
	public List<Position> getPositions() {
		return possiblePositions;
	}
	
	public void clearList() {
		possiblePositions.clear();
	}
}
