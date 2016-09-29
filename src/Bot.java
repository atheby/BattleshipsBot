import java.util.List;

public class Bot {
	
	private BotMode mode;
	private Board board;
	private List<Ship> enemyShips;
	
	public Bot() {
		addEnemyShips();
		mode = BotMode.Hunt;
	}
	
	public void play(String s) {
		if(mode.equals(BotMode.Hunt))
			hunt();
		else
			destroy();
	}
	
	private void addEnemyShips() {
		enemyShips.add(new Ship(1));
		enemyShips.add(new Ship(1));
		enemyShips.add(new Ship(2));
		enemyShips.add(new Ship(2));
		enemyShips.add(new Ship(3));
		enemyShips.add(new Ship(4));
		enemyShips.add(new Ship(5));
	}
	
	private void hunt() {
		
	}
	
	private void destroy() {
		
	}
}
