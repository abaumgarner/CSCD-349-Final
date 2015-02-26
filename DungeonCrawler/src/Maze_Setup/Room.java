package Maze_Setup;

public class Room {
	private Door north, south, east, west;
	boolean exit = false;

	Door getNorth() {
		return north;
	}

	void setNorth(Door north) {
		this.north = north;
	}

	Door getSouth() {
		return south;
	}

	void setSouth(Door south) {
		this.south = south;
	}

	Door getEast() {
		return east;
	}

	void setEast(Door east) {
		this.east = east;
	}

	Door getWest() {
		return west;
	}

	void setWest(Door west) {
		this.west = west;
	}

	boolean isExit() {
		return exit;
	}

	void setExit() {
		this.exit = true;
	}

	void lockNorth() {
		this.north.lock();
	}

	void lockSouth() {
		this.south.lock();
	}

	void lockWest() {
		this.west.lock();
	}

	void lockEast() {
		this.east.lock();
	}
}
