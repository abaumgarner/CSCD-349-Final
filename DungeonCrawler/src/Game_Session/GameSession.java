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
import Maze_Setup.MazeBuilder;
import Character.*;

public class GameSession {
	final int PARTYSIZE = 3;
	private CharacterFactory factory = new CharacterFactory();
	private MazeBuilder mazeBuilder;
	private Maze maze;
	private Party party = new Party();
	private boolean gameOver;
	private Scanner input;
	private BGMLibrary bgmLib;
	private SFXLibrary sfxLib;
	private GameShop shop;

	public GameSession(Scanner scan) {
		this.input = scan;
		this.gameOver = false;

		// add BGM files to library...
		bgmLib = new BGMLibrary();
		bgmLib.add("cave.wav");
		bgmLib.add("battle.wav");

		// add SFX files to library...
		sfxLib = new SFXLibrary();
		sfxLib.add("arrow.wav");
		sfxLib.add("attack.wav");
		sfxLib.add("die.wav");
		sfxLib.add("fire.wav");
		sfxLib.add("footsteps.wav");
		sfxLib.add("freeze.wav");
		sfxLib.add("die.wav");
		sfxLib.add("heal.wav");
		sfxLib.add("hurt.wav");
		sfxLib.add("magic.wav");
		sfxLib.add("multiply.wav");
		sfxLib.add("rumble.wav");
		this.newGame();
	}// end constructor

	protected void credits() {
		System.out.println("------------------------");
		System.out.println("\n SPECIAL THANKS TO:\n");
		System.out.println("------------------------");
		System.out.println(" www.bensoundmusic.com");
		System.out.println(" www.freesound.org");
		System.out.println(" www.bfxr.net");

		System.out.println("\n THE END\n");
	}// end credits

	private boolean foundExit() {
		if (maze.playerInExit())
			return true;
		return false;
	}// end foundExit

	private void generateParty() {
		String name = "";
		String classTitle = "";
		int members = 0;
		System.out.println("CHOOSE A PARTY OF " + PARTYSIZE + "\n");

		while (members != 3) {
			GameCharacter partyMember;
			name = createCharacterName();
			System.out.println("------------------------------");
			System.out.println("CHOOSE A CLASS FOR YOUR HERO:");
			System.out.println("------------------------------");
			factory.displayHeroTypes();

			System.out.print("\nCOMMAND: ");
			int index = input.nextInt();
			input.nextLine();

			classTitle = factory.getHeroClassification(index);
			partyMember = factory.spawnCharacter(classTitle);
			partyMember.setSFXLib(sfxLib);
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

	private String createCharacterName() {
		String name = "";
		System.out.print("Enter a character's name: ");
		while (name.length() == 0 || !regexCheck("([a-zA-Z])+", name)) {
			name = input.nextLine();
			if (!regexCheck("([a-zA-Z])+", name) || name.length() == 0) {
				System.out.println("\n'" + name + "' is not a valid entry.");
				System.out.print("Please enter character name: ");
			}// end if
		}// end while

		return name;
	}// end getCharacterName

	protected String getCommand() {
		System.out.println("\n(Type 'help' for a list of commands)\n");
		System.out.print("PROMPT: ");
		String cmd = input.nextLine();
		if (cmd.equals("help"))
			printHelpOptions();

		while (!cmd.equalsIgnoreCase("map") && !cmd.equalsIgnoreCase("status")
				&& !cmd.equalsIgnoreCase("shop") && !cmd.equalsIgnoreCase("q")) {
			System.out.println("\n(Type 'help' for a list of commands)\n");
			System.out.print("PROMPT: ");
			cmd = input.nextLine();

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

	protected void openShop() {
		shop = new GameShop();
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" You enter the shop...");
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		String cmd = "";
		while (!cmd.equals("q")) {
			shop.displayShop();
			System.out
					.println("(Enter item number to make a purchase, or 'q' to quit.)");
			System.out.print("ITEM NUMBER: ");
			cmd = input.nextLine();

			if (regexCheck("[1-" + shop.shopSize() + "]{1}", cmd)) {
				int itemNumber = Integer.parseInt(cmd);
				HealPotion item = shop.getShopItem(itemNumber);
				useItemOnCharacter(item);
			}// end if
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
			if (member.getName().equalsIgnoreCase(characterToHeal)) {
				int recoverAmount = item.getHealthAmount();
				member.heal(recoverAmount);
				System.out
						.println(member.getName() + " recovered some health.");
			}// end if
		}// end for

	}

	protected boolean isGameOver() {
		if (party.isDefeated()) {
			gameOver = true;
			loseDialog();
			return gameOver;
		}// end if
		else if (foundExit()) {
			gameOver = true;
			winDialog();
			return gameOver;
		}// end else if

		return !maze.mazeTraversal();
	}// end isGameOver

	protected boolean navigate() {
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" Opening map...");
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		System.out.println(maze.toString());
		String command = "";
		while (!regexCheck("[wasdqWASDQ]", command)) {
			System.out.println("Which direction will you go?\n");
			System.out.println("North - 'W'");
			System.out.println("West  - 'A'");
			System.out.println("South - 'S'");
			System.out.println("East  - 'D'");
			System.out.println("Quit  - 'Q'");
			System.out.print("\nCOMMAND: ");
			command = input.nextLine();
		}// end while

		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (command.equalsIgnoreCase("w")) {
			System.out.println(" You move North...");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			maze.moveNorth();
			sfxLib.playTrack("footsteps.wav");
			return true;
		}// end if

		else if (command.equalsIgnoreCase("a")) {
			System.out.println(" You move West...");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			maze.moveWest();
			sfxLib.playTrack("footsteps.wav");
			return true;
		}// end else if

		else if (command.equalsIgnoreCase("s")) {
			System.out.println(" You move South...");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			maze.moveSouth();
			sfxLib.playTrack("footsteps.wav");
			return true;
		}// end else if

		else if (command.equalsIgnoreCase("d")) {
			System.out.println(" You move East...");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			maze.moveEast();
			sfxLib.playTrack("footsteps.wav");
			return true;
		}// end else if

		else {
			System.out.println(" Exiting map...");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}// end else
		return false;
	}// end navigate

	private void newGame() {
		String str = "";
		titleScreen();
		synopsis();
		System.out.println("\n(Enter any key to continue)");
		while (str.equals("")) {
			System.out.print("COMMAND: ");
			str = input.nextLine();
		}// end while
		int mapSize = setDifficulty();
		mazeBuilder = new MazeBuilder(mapSize);
		maze = mazeBuilder.build();
		generateParty();
		intro();
		bgmLib.playBGM("cave.wav");
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

	public int setDifficulty() {

		final int DIFFICULTY_MODIFIER = 5;

		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" DIFFICULTY");
		System.out
				.println(" 1 - Easy: you won't break a sweat finding the exit.");
		System.out
				.println(" 2 - Normal: finding the exit could be a bit of a challenge.");
		System.out.println(" 3 - Hard: You will most likely die.");
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.print("\nCOMMAND: ");
		String prompt = input.nextLine();
		while (!regexCheck("[1-3]", prompt)) {
			System.out.println("Invalid entry.");
			System.out.print("\nCOMMAND: ");
			prompt = input.nextLine();
		}// end while
		int val = Integer.parseInt(prompt);
		return val * DIFFICULTY_MODIFIER;

	}// end setDifficulty

	protected void checkForBattle() {
		Random randVal = new Random();
		int result = randVal.nextInt(50);
		// System.out.println("result = " + result);

		if (result >= 25 || maze.playerInExit()) {
			System.out
					.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(" BATTLE COMMENCED!");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			ArrayList<GameCharacter> monsters = spawnMonsters();
			Combat combat = new Combat(party.getPartyMembers(), monsters);
			bgmLib.playBGM("battle.wav");
			combat.run();
			bgmLib.playBGM("cave.wav");
			System.out
					.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println(" BATTLE ENDED");
			System.out
					.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}// end if
	}// end initiateBattle

	private ArrayList<GameCharacter> spawnMonsters() {
		ArrayList<GameCharacter> monsters = new ArrayList<GameCharacter>();
		for (int i = 0; i < 3; i++) {
			Monster monster = factory.randomMonster();
			monster.setSFXLib(sfxLib);
			monsters.add(monster);
		}// end for
		return monsters;
	}// end spawnMonsters

	protected void intro() {
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n YOUR JOURNEY BEGINS:\n");
		System.out
				.println(" The room is engulfed in darkness. Thick cobwebs fill the corners of the room,");
		System.out
				.println(" and wisps of webbing hang from the ceiling and waver in a wind you can barely");
		System.out
				.println(" feel. One corner of the ceiling has a particularly large clot of webbing within");
		System.out
				.println(" which a goblin's bones are tangled. There are four large wooden doors that");
		System.out
				.println(" surround you splintered, rotted and riddled with markings from ages long since");
		System.out
				.println(" past. It is clear that finding an exit will not be easy...\n");
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}// end intro

	protected void synopsis() {
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out
				.println(" Welcome to Dungeon Crawler. A short D&D inspired game.");
		System.out
				.println("\n OBJECTIVE: Brave the labyrinth as you navigate a party of heroes through");
		System.out
				.println(" its darkest corners. Battle monsters as you try to make your escape and");
		System.out
				.println(" purchase items to aid your party. Few have entered and survived, will you");
		System.out.println(" live long enough to tell the tale?\n");
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
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
				.println("***************************************************************************\n");
		System.out.println();
	}// end titleScreen

	private void winDialog() {
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n CONGRATULATIONS!\n");
		System.out.println(" you found the exit and won the game!\n");
		credits();
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}// end

	private void loseDialog() {
		System.out
				.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n GAME OVER!\n");
		System.out.println(" your party was defeated...\n");
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}// end
}// end GameSession