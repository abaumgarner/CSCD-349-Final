package Game_Session;

/*Ryan Medenwaldt
 CSCD349, Tom Capaul
 01/31/2015*/

import game_Shop.GameShop;
import game_Items.HealPotion;

import java.util.Scanner;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import Maze_Setup.Maze;
import Character.*;

public class GameSession {
	final int PARTYSIZE = 3;
	private CharacterFactory factory = new CharacterFactory();
	private Party party = new Party();
	private boolean gameOver;
	private Scanner input;
	protected BGMLibrary bgmLib;
	protected SFXLibrary sfxLib;
	protected GameShop shop;

	public GameSession(Scanner scan) {
		this.input = scan;
		this.gameOver = false;
	}// end constructor

	protected void credits() {
		System.out.println("\nSPECIAL THANKS TO:\n");
		System.out.println("www.bensoundmusic.com");
		System.out.println("www.freesound.org");
		System.out.println("www.bfxr.net");

		System.out.println("\nTHE END\n");
	}// end credits

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
			GameCharacter partyMember;
			name = createCharacterName(input);
			System.out.println("CHOOSE A CLASS FOR YOUR HERO:");
			factory.displayHeroTypes();

			System.out.print("COMMAND: ");
			int index = input.nextInt();
			input.nextLine();

			classTitle = factory.getHeroClassification(index);
			partyMember = factory.spawnCharacter(classTitle);

			if (partyMember != null) {
				partyMember.setName(name);
				party.addMember(partyMember);
				members++;
				System.out
						.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("\n " + partyMember.getName() + " the "
						+ partyMember.getProfession() + " joins the party.\n");
				System.out
						.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			}// end if
		}// end while
	}// end generateParty

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

		while (!cmd.equalsIgnoreCase("map") && !cmd.equalsIgnoreCase("status")
				&& !cmd.equalsIgnoreCase("shop") && !cmd.equalsIgnoreCase("q")) {
			System.out.println("\n(Type 'help' for a list of commands)\n");
			System.out.print("PROMPT: ");
			cmd = kb.nextLine();

			if (cmd.equals("help"))
				printHelpOptions();
		}// end while
		return cmd;
	}// end getCommand

	protected void printHelpOptions() {
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" COMMANDS:\n");
		System.out.println(" * 'map' - Open map to move your party.");
		System.out.println(" * 'status' - View your party memebers' stats.");
		System.out
				.println(" * 'shop' - purchase useful items to aid your party.");
		System.out.println(" * 'q' - ends the game.");
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}// end help

	protected void getPartyStats() {
		party.partyStats();
	}// end getParty

	protected void openShop(String cmd) {
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" You enter the shop...");
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		shop.displayShop();
		System.out.println("\nWelcome!");
		while (!cmd.equals("q")) {
			System.out
					.println("(Enter item number to make a purchase, or 'q' to quit.");
			System.out.print("\nItem Number: ");
			cmd = input.nextLine();

			if (regexCheck("[1-" + shop.shopSize() + "]{1}", cmd)) {
				int itemNumber = Integer.parseInt(cmd);
				HealPotion item = shop.getShopItem(itemNumber);
				useItemOnCharacter(item);
			}
		}// end while
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" You leave the shop...");
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}// end openShop

	private void useItemOnCharacter(HealPotion item) {
		this.getPartyStats();
		String characterToHeal;

		do {
			System.out.print("Enter the party member to add item to: ");
			characterToHeal = input.nextLine();
		} while (!party.findMember(characterToHeal));

		for (GameCharacter member : party.getPartyMembers()) {
			if (member.getName().equalsIgnoreCase(characterToHeal))
				System.out
						.println(member.getName() + " recovered some health.");
		}

	}

	protected boolean isGameOver(Maze maze) {
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
			credits();
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			return gameOver;
		}// end else if

		return !maze.mazeTraversal();
	}// end isGameOver

	protected String navigate(Maze maze) {
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

	protected void newGame() {
		synopsis();
		generateParty();
		intro();
	}// end newGame

	protected void printSplash() {

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

	protected void initiateBattle(Maze maze, BGMLibrary bgmLibrary,
			SFXLibrary sfxLibrary) {
		Random randVal = new Random();
		int result = randVal.nextInt(50);
		// System.out.println("result = " + result);

		if (result >= 25 || maze.playerInExit()) {
			System.out.println("BATTLE COMMENCED!");
			ArrayList<GameCharacter> monsters = spawnMonsters();
			Combat combat = new Combat(party.getPartyMembers(), monsters);
			bgmLibrary.playBGM("battle.wav");
			combat.run();
			bgmLibrary.playBGM("cave.wav");
		}// end if
	}// end initiateBattle

	private ArrayList<GameCharacter> spawnMonsters() {
		ArrayList<GameCharacter> monsters = new ArrayList<GameCharacter>();
		for (int i = 0; i < 3; i++)
			monsters.add(factory.randomMonster());
		return monsters;
	}// end spawnMonsters

	protected void intro() {
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

	protected void synopsis() {
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

	protected void titleScreen() {
		System.out
				.println("\n===========================================================================");
		printSplash();
		System.out
				.println("                                Ver. 1.0                                  ");
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