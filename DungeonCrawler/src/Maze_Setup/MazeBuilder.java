package Maze_Setup;

import java.util.Random;

public class MazeBuilder {
	private int dimension;

	@SuppressWarnings("unused")
	private MazeBuilder() {
		this(2);
	}

	public MazeBuilder(int dimension) {
		this.dimension = dimension;
	}

	public Maze build() {
		Maze newMaze = new Maze();

		newMaze.setRooms(this.roomSetup());
		newMaze.setDimension(this.dimension);
		lockRandomDoors(newMaze);
		this.lockBorder(newMaze);

		return newMaze;
	}

	private Room[][] roomSetup() {
		Room[][] rooms = new Room[this.dimension][this.dimension];
		int i, j;

		for (i = 0; i < this.dimension; i++) {
			for (j = 0; j < this.dimension; j++) {
				rooms[i][j] = new Room();
			}
		}

		this.doorSetup(rooms);

		rooms = randomlyPlaceExit(rooms);
		return rooms;
	}

	private Room[][] randomlyPlaceExit(Room[][] rooms) {
		Random rand = new Random();
		int randomX, randomY;

		do {
			randomX = rand.nextInt((this.dimension - (this.dimension - 5)) - 1);
			randomY = rand.nextInt((this.dimension - (this.dimension - 5)) - 1);
		} while (randomX < 2);

		rooms[randomX][randomY].setExit();

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
				rooms[i][j].setWest(rooms[i][j - 1].getEast());

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

	private void lockRandomDoors(Maze maze) {
		Room[][] rooms = maze.getRooms();
		Random rand = new Random();
		int randomX, randomY;

		for (int i = 0; i < this.dimension; i++) {
			randomX = rand.nextInt(this.dimension);
			randomY = rand.nextInt(this.dimension);

			int randomDoor = rand.nextInt(4) + 1;

			if (randomDoor == 1) {
				rooms[randomX][randomY].getNorth().lock();
				if (!maze.mazeTraversal())
					rooms[randomX][randomY].getNorth().unlock();
			} else if (randomDoor == 2) {
				rooms[randomX][randomY].getEast().lock();
				if (!maze.mazeTraversal())
					rooms[randomX][randomY].getEast().unlock();
			} else if (randomDoor == 3) {
				rooms[randomX][randomY].getSouth().lock();
				if (!maze.mazeTraversal())
					rooms[randomX][randomY].getSouth().unlock();
			} else {
				rooms[randomX][randomY].getWest().lock();
				if (!maze.mazeTraversal())
					rooms[randomX][randomY].getWest().unlock();
			}
		}
	}

	private void lockBorder(Maze maze) {
		Room[][] rooms = maze.getRooms();

		int i, j;

		for (i = 0; i < maze.getDimension(); i++)
			for (j = 0; j < maze.getDimension(); j++) {
				if (i == 0)
					rooms[i][j].lockNorth();
				if (j == 0)
					rooms[i][j].lockWest();
			}
	}
}
