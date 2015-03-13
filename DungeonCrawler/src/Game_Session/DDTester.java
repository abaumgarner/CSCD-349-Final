package Game_Session;

/*Ryan Medenwaldt
 CSCD349, Tom Capaul
 01/31/2015*/

import java.util.Scanner;
import Maze_Setup.Maze;
import Maze_Setup.MazeBuilder;
import game_Shop.GameShop;

public class DDTester {
	public static void main(String[] args) {
		String exit = "";
		Scanner input = new Scanner(System.in);
		GameSession game = new GameSession(input);

		while (!game.isGameOver() && !exit.equalsIgnoreCase("q")) {
			String cmd = game.getCommand();
			boolean moved = false;
			if (cmd.equalsIgnoreCase("map")) {
				moved = game.navigate();
				if (moved)
					game.checkForBattle();
			}// end if

			else if (cmd.equalsIgnoreCase("status")) {
				game.getPartyStats();
			}// end else if

			else if (cmd.equalsIgnoreCase("shop")) {
				game.openShop();
			}// end else if

			else if (cmd.equalsIgnoreCase("q"))
				exit = cmd;
			else {
				System.out
						.println("There was an error executing the command...");
			}// end
		}// end while

		System.out
				.println("Thank you for playing our game. Please don't look at the smells of our code (it kind of smells).");
	}// end main
}// end class

