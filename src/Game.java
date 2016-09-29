import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Bot bot = new Bot();
		
		do {
			if(in.next().equals("INIT"))
				new PositionGenerator(new Board(10)).printAllCoordinates();
			else {
				String buffer = "";
				for(int x = 0; x < 10; x++)
					buffer += in.next();
				bot.play(buffer);
			}
		} while(true);
	}
}