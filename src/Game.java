import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Bot bot = new Bot();
		String buffer = "";
		String s = in.nextLine();
        if(s.equals("INIT"))
            new PositionGenerator().printAllCoordinates();
        if(s.equals("10"))
        {
			buffer = "";
			for(int x = 0; x < 10; x++)
				buffer += in.nextLine();
			bot.play(buffer);
        }
        in.close();
	}
}