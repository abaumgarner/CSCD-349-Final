package Maze_Setup;

import java.util.Random;
import java.util.Scanner;

public class MazeTester {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		MazeBuilder builder = new MazeBuilder(5);
		Maze maze = builder.build();
		//lockOut(maze);
		
		while(!maze.playerInExit()) {
			System.out.println(maze.toString());
			System.out.println("w/a/s/d --> n/w/s/e\n");
			
			char dir = kb.next().charAt(0);
			switch(dir) {
			case('w'):
				maze.moveNorth();
				break;
			case('s'):
				maze.moveSouth();
				break;
			case('a'):
				maze.moveWest();
				break;
			case('d'):
				maze.moveEast();
				break;
			default:
				System.out.println("Wrong input, moron");
			}
			if(!maze.mazeTraversal())
				System.out.println("MAZE CAN NO LONGER BE TRAVERSED");
		}
	}
	
	private static void lockOut(Maze maze) {
		Room[][] rooms = maze.getRooms();
		
		Room init = rooms[maze.dimension -1][maze.dimension -1];
		init.getNorth().lock();
		init.getSouth().lock();
		init.getWest().lock();
		init.getEast().lock();
	}
}
