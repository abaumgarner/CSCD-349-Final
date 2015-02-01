package Maze_Setup;

import java.util.Random;

public class MazeBuilder {
	private int dimension;

	public MazeBuilder() {
		this(2);
	}

	public MazeBuilder(int dimension) {
		this.dimension = dimension;
	}

	public Maze build() {
		Maze newMaze = new Maze();

		newMaze.setRooms(this.roomSetup());
		newMaze.setDimension(this.dimension);
		//this.randomLocks(newMaze);
		return newMaze;
	}

	private Room[][] roomSetup() {
		Room[][] rooms = new Room[this.dimension][this.dimension];

		// Initializing Rooms
		int i, j;
		for (i = 0; i < this.dimension; i++) {
			for (j = 0; j < this.dimension; j++) {
				rooms[i][j] = new Room();
			}
		}
		this.doorSetup(rooms);
		rooms[this.dimension -1][this.dimension -1].setExit();
		return rooms;
	}

	private void doorSetup(Room[][] rooms) {
		int i, j;

		// Setting up West and East Shared Doors
		for (i = 0; i < this.dimension; i++) {
			rooms[i][0].setWest(new Door());
			rooms[i][0].setEast(new Door());
		}
		for (i = 0; i < this.dimension; i++)
			for (j = 1; j < this.dimension; j++) {
				rooms[i][j].setWest(rooms[i][j -1].getEast());

				if (j == this.dimension - 1)
					rooms[i][j].setEast(rooms[i][0].getWest());
				else
					rooms[i][j].setEast(new Door());
			}

		// Setting up North and South Shared Doors
		for (i = 0; i < this.dimension; i++) {
			rooms[0][i].setNorth(new Door());
			rooms[0][i].setSouth(new Door());
		}
		for (i = 1; i < this.dimension; i++)
			for (j = 0; j < this.dimension; j++) {
				rooms[i][j].setNorth(rooms[i - 1][j].getSouth());

				if (i == this.dimension - 1)
					rooms[i][j].setSouth(rooms[0][j].getNorth());
				else
					rooms[i][j].setSouth(new Door());
			}
	}
	
	private void randomLocks(Maze maze) {
		Room[][] rooms = maze.getRooms();
		int i,j;
		Random gen = new Random();
		for(i = 0; i < maze.getDimension();i++) 
			for(j = 0; j < maze.getDimension(); j++) {
				int rand = gen.nextInt(2);
				int rand2 = gen.nextInt(4);
				if(rand == 0)
					break;
				if(rand2 ==0)
					rooms[i][j].getNorth().lock();
				if(rand2 ==1)
					rooms[i][j].getSouth().lock();
				if(rand2 ==2)
					rooms[i][j].getWest().lock();
				if(rand2 ==3)
					rooms[i][j].getEast().lock();
			}
	}
}
