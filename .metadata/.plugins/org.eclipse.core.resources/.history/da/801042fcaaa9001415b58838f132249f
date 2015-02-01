package Maze_Setup;

public class Room {
	private Door north, south, east, west;
	boolean exit = false;
	
	public Door getNorth() {
		return north;
	}

	public void setNorth(Door north) {
		this.north = north;
	}

	public Door getSouth() {
		return south;
	}

	public void setSouth(Door south) {
		this.south = south;
	}

	public Door getEast() {
		return east;
	}

	public void setEast(Door east) {
		this.east = east;
	}

	public Door getWest() {
		return west;
	}

	public void setWest(Door west) {
		this.west = west;
	}
	public boolean isExit() {
		return exit;
	}
	public void setExit() {
		this.exit = true;
	}	
	
	public void lockNorth() {
		this.north.lock();
	}
	public void lockSouth() {
		this.south.lock();
	}
	public void lockWest() {
		this.west.lock();
	}
	public void lockEast() {
		this.east.lock();
	}
}
