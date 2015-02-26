/*
 * Test class for the maze. Commented out sections were implemented in another class but tested here locally first.
 * 
 */
package Maze_Setup;

import java.util.Scanner;

public class MazeTester {
	/*private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_BLACK = "\u001B[30m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_WHITE = "\u001B[37m";
	*/	
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		MazeBuilder builder = new MazeBuilder(5);
		Maze maze = builder.build();

		while (!maze.playerInExit()) {
			System.out.println(maze.toString());
			System.out.println("w/a/s/d --> n/w/s/e\n");

			char dir = kb.next().charAt(0);
			switch (dir) {
			case ('w'):
				maze.getCurrentRoom().getNorth().open();
				maze.moveNorth();
				break;
			case ('s'):
				maze.getCurrentRoom().getSouth().open();
				maze.moveSouth();
				break;
			case ('a'):
				maze.getCurrentRoom().getWest().open();
				maze.moveWest();
				break;
			case ('d'):
				maze.getCurrentRoom().getEast().open();
				maze.moveEast();
				break;
			case ('l'):
				lockOut(maze);
				break;
			default:
				System.out.println("Wrong input, moron");
			}
			if (!maze.mazeTraversal()) {
				System.out.println(maze.toString());
				System.out.println("MAZE CAN NO LONGER BE TRAVERSED");

				System.exit(0);
			}
		}
	}
/*
	private static void printMaze(Maze maze) {
		int i, j;
		Room [][] rooms = maze.getRooms();
		
		for (i = 0; i < rooms.length; i++) {
			for (j = 0; j < rooms[i].length; j++) {
				System.out.print(lockedColor + "*" + ANSI_RESET);
				if (rooms[i][j].getNorth().isLocked())
					System.out.print(lockedColor + "x" + ANSI_RESET);
				else if(rooms[i][j].getNorth().isOpen())
					System.out.print(openColor + "-" + ANSI_RESET);
				else
					System.out.print(closedColor + "-" + ANSI_RESET);
			}

			System.out.print(lockedColor + "*\n" + ANSI_RESET);
			for (j = 0; j < rooms[i].length; j++) {
				if (rooms[i][j].getWest().isLocked())
					System.out.print(lockedColor + "x" + ANSI_RESET);
				else if(rooms[i][j].getWest().isOpen())
					System.out.print(openColor + "|" + ANSI_RESET);
				else
					System.out.print(closedColor + "|" + ANSI_RESET);
				
				if (rooms[i][j].isExit())
					System.out.print(" ");
				else if (i == maze.playerRow && j == maze.playerCol)
					System.out.print(playerColor + "P" + ANSI_RESET);
				else
					System.out.print(" ");
				System.out.print("");
			}
			
			if (rooms[i][j - 1].getEast().isLocked())
				System.out.print(lockedColor + "x" + ANSI_RESET);
			else if(rooms[i][j - 1].getEast().isOpen())
				System.out.print(openColor + "|" + ANSI_RESET);
			else
				System.out.print(closedColor + "|" + ANSI_RESET);
			
			System.out.print("\n");
		}
		for (j = 0; j < rooms[0].length; j++) {
			System.out.print(lockedColor + "*" + ANSI_RESET);
			if (rooms[rooms.length - 1][j].getSouth().isLocked())
				System.out.print(lockedColor + "x" + ANSI_RESET);
			else if(rooms[rooms.length - 1][j].getSouth().isOpen())
				System.out.print(openColor + "-" + ANSI_RESET);
			else
				System.out.print(closedColor + "-" + ANSI_RESET);
		}
		System.out.print(lockedColor + "*\n" + ANSI_RESET);
				
	}
	*/

	private static void lockOut(Maze maze) {
		Room[][] rooms = maze.getRooms();

		Room init = rooms[maze.dimension - 1][maze.dimension - 1];
		init.getNorth().lock();
		init.getSouth().lock();
		init.getWest().lock();
		init.getEast().lock();
	}
}
