package Game_Session;

/*Ryan Medenwaldt
 CSCD349, Tom Capaul
 01/31/2015*/

import java.util.Scanner;
import Maze_Setup.Maze;
import Maze_Setup.MazeBuilder;

public class DDTester {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		GameSession game = new GameSession(input);
		
		SFXLibrary sfxLibrary = new SFXLibrary();
		BGMLibrary bgmLibrary = new BGMLibrary(); 
		
		game.titleScreen();
		game.newGame();

		// build maze...
		MazeBuilder builder = new MazeBuilder(5);
		Maze maze = builder.build();

		//add BGM files to library...
		bgmLibrary.add("cave.wav");
		bgmLibrary.add("battle.wav");
		
		//add SFX files to library...
		sfxLibrary.add("footsteps.wav");
		sfxLibrary.add("attack.wav");
		sfxLibrary.add("hurt.wav");
		sfxLibrary.add("arrow.wav");
		sfxLibrary.add("magic.wav");
		
		//start with cave as bgm...
		bgmLibrary.playBGM("cave.wav");
		
		while (!game.isGameOver(maze)) {
			String cmd = game.getCommand(input);
			if (cmd.equals("map")) {
				System.out.println("\nMAP:\n");
				System.out.println(maze.toString());
				game.navigate(maze);
				sfxLibrary.playTrack("footsteps.wav");
				game.initiateBattle(maze, bgmLibrary, sfxLibrary);
			}// end if

			else if (cmd.equals("status")) {
				game.getPartyStats();
			}// end else if

			else {
				System.out.println("There was an error executing the command...");
			}// end
		}// end while
	}// end main
}// end class

