package Game_Session;

/*Ryan Medenwaldt
 CSCD349, Tom Capaul
 01/31/2015*/

import java.util.Scanner;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.IOException;

import Maze_Setup.Maze;
import Character.*;
import Character.GameCharacter;

public class GameSession {
	final int PARTYSIZE = 3;
	private String classes[] = { "", "Warrior", "Mage", "Ranger", "Cleric" };
	private Party party = new Party();
	private boolean gameOver;
	private boolean gameWon;
	Scanner input;
	BGMLibrary bgmLib;
	SFXLibrary sfxLib;

	public GameSession(Scanner scan) {
		this.input = scan;
		this.gameOver = false;
	}// end constructor

	private String characterClassName(String str) {
		int num = Integer.parseInt(str);
		return classes[num];
	}// end characterClassName

	private void credits() {
		System.out.println("\nSPECIAL THANKS TO:\n");
		System.out.println("www.bensoundmusic.com");
		System.out.println("www.freesound.org");
		System.out.println("www.bfxr.net");

		System.out.println("\nTHE END\n");
	}// end credits

	private void displayClasses() {
		for (int i = 1; i < classes.length; i++)
			System.out.println(" " + i + " - " + classes[i]);
	}// endDisplayClasses

	private boolean foundExit(Maze maze) {
		if (maze.playerInExit())
			return true;
		return false;
	}// end foundExit

	private void generateParty() {
		String name = "";
		String classTitle = "";
		int members = 0;
		System.out.println("\nCHOOSE A PARTY OF " + PARTYSIZE + "\n");
		while (members != 3) {
			name = createCharacterName(input);
			classTitle = createCharacterClass(name);
			GameCharacter partyMember;
			if (classTitle.compareTo("Warrior") == 0) {
				partyMember = new Warrior();
				partyMember.setName(name);
				party.addMember(partyMember);
				System.out
						.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("\n " + partyMember.getName() + " the "
						+ partyMember.getProfession() + " joins the party.\n");
				System.out
						.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
				members++;
			}// end if

			else {
				System.out
						.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out
						.println("\n That class is currently unavailable...\n");
				System.out
						.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			}// end else
		}// end while
	}// end generateParty

	private String createCharacterClass(String heroName) {
		String str = "";

		System.out.println("\nHERO CLASSES:");
		displayClasses();

		System.out.print("\nEnter " + heroName + "'s class: ");
		str = input.nextLine();

		while (!regexCheck("[1-" + classes.length + "]", str)
				|| str.length() == 0) {
			if (!regexCheck("[1-" + classes.length + "]", str)
					|| str.length() == 0) {
				System.out.println("\n'" + str + "' is not a valid entry.");
			}// end if

			System.out.print("\nEnter a character class:");
			str = input.nextLine();
		}// end while
		return characterClassName(str);
	}// end getCharacterClass

	private String createCharacterName(Scanner kb) {
		String name = "";
		System.out.print("\nEnter a character's name: ");
		while (name.length() == 0 || !regexCheck("([a-zA-Z])+", name)) {
			name = kb.nextLine();
			if (!regexCheck("([a-zA-Z])+", name) || name.length() == 0) {
				System.out.println("\n'" + name + "' is not a valid entry.");
				System.out.print("\nPlease enter character name: ");
			}// end if
		}// end while
		return name;
	}// end getCharacterName

	protected String getCommand(Scanner kb) {
		System.out.println("\n(Type 'help' for a list of commands)\n");
		System.out.print("PROMPT: ");
		String cmd = kb.nextLine();
		if (cmd.equals("help"))
			printHelpOptions();

		while (!cmd.equals("map") && !cmd.equals("status")) {
			System.out.println("\n(Type 'help' for a list of commands)\n");
			System.out.print("PROMPT: ");
			cmd = kb.nextLine();

			if (cmd.equals("help"))
				printHelpOptions();

		}// end while
		return cmd;
	}// end getCommand

	public void printHelpOptions() {
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" COMMANDS:\n");
		System.out.println(" * 'map' - Open map to move character.");
		System.out.println(" * 'status' - View current party Stats.");
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}// end help

	public void getPartyStats() {
		party.partyStats();
	}// end getParty

	public boolean isGameOver(Maze maze) {
		if (party.isDefeated()) {
			gameOver = true;
			return gameOver;
		}// end if
		else if (foundExit(maze)) {
			gameOver = true;
			System.out
					.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\n CONGRATULATIONS!\n");
			System.out.println(" you found the exit and won the game!\n");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			return gameOver;
		}// end else if

		return !maze.mazeTraversal();
	}// end isGameOver

	public String navigate(Maze maze) {
		String command = "";
		while (!regexCheck("[wasdWASD]", command)) {
			System.out.println("Which direction will you go?\n");
			System.out.println("North - 'W'");
			System.out.println("West  - 'A'");
			System.out.println("South - 'S'");
			System.out.println("East  - 'D'");
			System.out.print("\nCOMMAND: ");
			command = input.nextLine();
		}// end while

		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (command.equals("w") || command.equals("W")) {
			System.out.println("\n You move North...\n");
			maze.moveNorth();
		}// end if

		else if (command.equals("a") || command.equals("A")) {
			System.out.println("\n You move West...\n");
			maze.moveWest();
		}// end else if

		else if (command.equals("d") || command.equals("D")) {
			System.out.println("\n You move East...\n");
			maze.moveEast();
		}// end else if

		else {
			System.out.println("\n You move South...\n");
			maze.moveSouth();
		}// end else
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return command;
	}// end navigate

	public void newGame() {
		synopsis();
		generateParty();
		intro();
	}// end newGame

	private void printSplash() {

		System.out
				.println("  ___                                 ___                 _         ");
		System.out
				.println(" |   \\ _  _ _ _  __ _ ___ ___ _ _    / __|_ _ __ ___ __ _| |___ _ _ ");
		System.out
				.println(" | |) | || | ' \\/ _` / -_) _ \\ ' \\  | (__| '_/ _` \\ V  V / / -_) '_|");
		System.out
				.println(" |___/ \\_,_|_||_\\__, \\___\\___/_||_|  \\___|_| \\__,_|\\_/\\_/|_\\___|_|");
		System.out
				.println("                |___/                                               ");
		System.out.println();
	}// end printSplash

	private boolean regexCheck(String pattern, String str) {
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher;
		boolean matches = false;

		matcher = regex.matcher(str);
		matches = matcher.matches();

		return matches;
	}// end regexCheck

	protected void initiateBattle(Maze maze) {
		Random randVal = new Random();
		int result = randVal.nextInt(50);
		// System.out.println("result = " + result);

		if (result >= 25 || maze.playerInExit())
		{
			System.out.println("BATTLE COMMENCED!");
			Combat combat = new Combat(party, );
			
		}//end if
	}// end initiateBattle

	public void intro() {
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\nYOUR JOURNEY BEGINS:\n");
		System.out
				.println("The room is engulfed in darkness. Thick cobwebs fill the corners of the room,");
		System.out
				.println("and wisps of webbing hang from the ceiling and waver in a wind you can barely");
		System.out
				.println("feel. One corner of the ceiling has a particularly large clot of webbing within");
		System.out
				.println("which a goblin's bones are tangled. There are four large wooden doors that");
		System.out
				.println("surround you splintered, rotted and riddled with markings from ages long since");
		System.out
				.println("past. It is clear that finding an exit will not be easy...\n");
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}// end intro

	public void synopsis() {
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out
				.println("\nWelcome to Dungeon Crawler. A short D&D inspired game.");
		System.out
				.println("\nOBJECTIVE: Brave the labyrinth as you navigate a party of heroes through");
		System.out
				.println("its darkest corners seeking treasures beyond measure. Collect keys to unlock");
		System.out
				.println("doors, items to aid your party, and battle monsters as you make your escape.");
		System.out
				.println("Few have entered and survived, will you live long enough to tell the tale?\n");
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}// end synopsis

	public void titleScreen() {
		System.out
				.println("\n===========================================================================");
		printSplash();
		System.out
				.println("                                Ver. 0.5                                  ");
		System.out
				.println("===========================================================================");
		System.out
				.println("*                                                                         *");
		System.out
				.println("*     Created by:                                                         *");
		System.out
				.println("*     Aaron Baumgarner, Ryan Medenwaldt, Josh Ohm                         *");
		System.out
				.println("*                                                                         *");
		System.out
				.println("***************************************************************************");
		System.out.println();
	}// end titleScreen
}// end GameSession