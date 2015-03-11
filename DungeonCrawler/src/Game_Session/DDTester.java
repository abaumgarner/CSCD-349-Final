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

		SFXLibrary sfxLibrary = new SFXLibrary();
		BGMLibrary bgmLibrary = new BGMLibrary();

		game.titleScreen();
		game.newGame();

		// build maze...
		MazeBuilder builder = new MazeBuilder(5);
		Maze maze = builder.build();

		// add BGM files to library...
		bgmLibrary.add("cave.wav");
		bgmLibrary.add("battle.wav");

		// add SFX files to library...
		sfxLibrary.add("footsteps.wav");
		sfxLibrary.add("attack.wav");
		sfxLibrary.add("hurt.wav");
		sfxLibrary.add("arrow.wav");
		sfxLibrary.add("magic.wav");

		// add shop...
		game.shop = new GameShop();

		// start with cave as bgm...
		bgmLibrary.playBGM("cave.wav");

		while (!game.isGameOver(maze) && !exit.equalsIgnoreCase("q")) {
			String cmd = game.getCommand(input);
			if (cmd.equalsIgnoreCase("map")) {
				System.out.println("\nMAP:\n");
				System.out.println(maze.toString());
				game.navigate(maze);
				sfxLibrary.playTrack("footsteps.wav");
				game.initiateBattle(maze, bgmLibrary, sfxLibrary);
			}// end if

			else if (cmd.equalsIgnoreCase("status")) {
				game.getPartyStats();
			}// end else if

			else if (cmd.equalsIgnoreCase("shop")) {
				game.openShop(cmd);
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

