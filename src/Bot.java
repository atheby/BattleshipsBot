import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bot {
	
	private Board board;
	private List<EnemyShip> shipsInPlay;
	private GameState gameState;
	
	public Bot() {
		board = new Board();
		gameState = new GameState();
		addShipsInPlay();
	}
	
	public void play(String buffer) {
		board.updateFromBuffer(buffer);
		checkResult(gameState.getLastShoot());
        updatePossiblePositions();
		if(gameState.getMode().equals(BotMode.Hunt))
			hunt();
        else
            destroy();
	}
	
	private void addShipsInPlay() {
		int[] ships = gameState.getShipsInPlay();
		shipsInPlay = new ArrayList<EnemyShip>();
		for(int shipSize : ships)
			shipsInPlay.add(new EnemyShip(shipSize));
	}
	
	private void updatePossiblePositions() {
		Position posH, posV;
		for(EnemyShip ship : shipsInPlay)
			ship.clearList();
		for(int r = 0; r < board.getSize(); r++)
			for(int c = 0; c < board.getSize(); c++) {
				posH = new Position();
				posV = new Position();
				posH.setRow(r);
				posH.setColumn(c);
				posH.setOrientation(Orientation.Horizontally);
				posV.setRow(r);
				posV.setColumn(c);
				posV.setOrientation(Orientation.Vertically);
				for(EnemyShip ship : shipsInPlay) {
					if(r + ship.getSize() <= board.getSize())
						if(board.isSpaceFree(posV, ship))
							ship.addPosition(posV);
					if(c + ship.getSize() <= board.getSize())
						if(board.isSpaceFree(posH, ship))
							ship.addPosition(posH);
				}
			}
	}
	
	private void checkResult(Position pos) {
		if(pos != null) {
			if(board.getBoard()[pos.getRow()][pos.getColumn()].equals(Symbols.MISS) && gameState.getMode().equals(BotMode.Destroy))
				gameState.setMode(BotMode.Destroy);
			if(board.getBoard()[pos.getRow()][pos.getColumn()].equals(Symbols.HIT)) {
				gameState.setHitCounter(board.getHitsOnBoard());
				gameState.setMode(BotMode.Destroy);
			}
			if(board.getBoard()[pos.getRow()][pos.getColumn()].equals(Symbols.DESTROYED))
				shipDestroyed();
		}
	}
	
	private void shipDestroyed() {
		int beforeHits = gameState.getHitCounter();
		int afterHits = board.getHitsOnBoard();
		int shipSize = beforeHits - afterHits + 1;
		gameState.removeShipFromPlay(shipSize);
		if(board.getHitsOnBoard() == 0)
			gameState.setMode(BotMode.Hunt);
		else
			gameState.setMode(BotMode.Destroy);
	}
	
	private void shoot(Position pos) {
		gameState.saveShoot(pos);
		System.out.println(pos.getRow() + " " + pos.getColumn());
	}
	
	private void hunt() {
		Collections.sort(shipsInPlay, Collections.reverseOrder());
		shoot(shipsInPlay.get(0).getRandomPosition());
	}
	
	private void destroy() {
		Position pos = null;
		
		for(int r = 0; r < board.getSize(); r++)
			for(int c = 0; c < board.getSize(); c++) {
				if(board.getBoard()[r][c].equals(Symbols.HIT)) {
					if(r - 1 >= 0) {
						if(board.getBoard()[r - 1][c].equals(Symbols.EMPTY)) {
							pos = new Position();
							pos.setRow(r - 1);
							pos.setColumn(c);
						}
					}
					if(r + 1 < board.getSize() && pos == null) {
						if(board.getBoard()[r + 1][c].equals(Symbols.EMPTY)) {
							pos = new Position();
							pos.setRow(r + 1);
							pos.setColumn(c);
						}
					}
					if(c - 1 >= 0 && pos == null) {
						if(board.getBoard()[r][c - 1].equals(Symbols.EMPTY)) {
							pos = new Position();
							pos.setRow(r);
							pos.setColumn(c - 1);
						}
					}
					if(c + 1 < board.getSize() && pos == null) {
						if(board.getBoard()[r][c + 1].equals(Symbols.EMPTY)) {
							pos = new Position();
							pos.setRow(r);
							pos.setColumn(c + 1);
						}
					}
				}
				if(pos == null)
					continue;
			}
		shoot(pos);
	}
}
