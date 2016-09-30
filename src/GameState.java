import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SuppressWarnings("unchecked")
public class GameState {

	private File fileName;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private JSONParser parser;
	private JSONObject obj;
	
	public GameState() {
		fileName = new File("game.json");
		parser = new JSONParser();
	}
	
	public JSONObject getObjectFromFile() {
		if(!fileName.exists())
        {
			obj = new JSONObject();
			obj.put("lastShoot", "");
			obj.put("shipsInPlay", "5:4:3:2:2:1:1");
			obj.put("hitCounter", "0");
			obj.put("mode", "Hunt");
			try {
				fileName.createNewFile();
				fileWriter = new FileWriter(fileName);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(obj.toString());
                bufferedWriter.close();
			} catch (IOException e) {}
        }
		else {
			try {
				obj = (JSONObject) parser.parse(new FileReader(fileName));
			} catch (Exception e) {}
		}
		return obj;
	}
	
	public Position getLastShoot() {
		Position pos = new Position();;
		obj = getObjectFromFile();
		String[] arrStr = obj.get("lastShoot").toString().split(":");
		try {
			pos.setRow(Integer.parseInt(arrStr[0]));
			pos.setColumn(Integer.parseInt(arrStr[1]));
		} catch(NumberFormatException e) {
				pos = null;
			}
		return pos;
	}
	
	public boolean removeShipFromPlay(int shipSize) {
		try {
			obj = getObjectFromFile();
			String ships = obj.get("shipsInPlay").toString();
			ships = ships.replaceFirst(Integer.toString(shipSize), " ");
			ships = ships.replaceAll(": :", ":");
			ships = ships.replaceAll(" :", "");
			ships = ships.replaceAll(": ", "");
			ships = ships.trim();
			obj.put("shipsInPlay", ships);
			if(saveState(obj))
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public int[] getShipsInPlay() {
		obj = getObjectFromFile();
		String[] arrStr = obj.get("shipsInPlay").toString().split(":");
		int[] arrInt = new int[arrStr.length];
		for(int x = 0; x < arrInt.length; x++)
			arrInt[x] = Integer.parseInt(arrStr[x]);
		return arrInt;
	}
	
	public boolean setMode(BotMode mode) {
		try {
			obj = getObjectFromFile();
			obj.put("mode", mode.toString());
			if(saveState(obj))
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public BotMode getMode() {
		obj = getObjectFromFile();
		return BotMode.valueOf(obj.get("mode").toString());
	}
	
	public boolean setHitCounter(int counter) {
		try {
			obj = getObjectFromFile();
			obj.put("hitCounter", counter);
			if(saveState(obj))
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public int getHitCounter() {
		obj = getObjectFromFile();
		return Integer.parseInt(obj.get("hitCounter").toString());
	}
	
	public boolean saveShoot(Position pos) {
		try {
			obj = getObjectFromFile();
			obj.put("lastShoot", pos.getRow() + ":" + pos.getColumn());
			if(saveState(obj))
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	private boolean saveState(JSONObject obj) {
		try {
			fileWriter = new FileWriter(fileName);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(obj.toString());
	        bufferedWriter.close();
	        return true;
		} catch (IOException e) {
			return false;
		}
	}
}
