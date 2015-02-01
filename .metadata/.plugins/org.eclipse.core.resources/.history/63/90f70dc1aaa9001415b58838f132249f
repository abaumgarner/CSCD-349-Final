package Maze_Setup;

import java.awt.Point;

public class Maze {
	Room[][] rooms;
	int dimension;
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	int playerRow, playerCol;
	public Maze() {
		this.playerRow = 0;
		this.playerCol = 0;
	}

	public Room[][] getRooms() {
		return rooms;
	}

	public void setRooms(Room[][] rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		String maze = "";
		int i, j;
		for (i = 0; i < this.rooms.length; i++) {
			// build north doors
			for (j = 0; j < this.rooms[i].length; j++) {
				maze += "*";
				if(this.rooms[i][j].getNorth().isLocked())
					maze+= "x";
				else
					maze+= "-";
			}
			
				maze += "*\n";
				for (j = 0; j < this.rooms[i].length; j++) {
					if(this.rooms[i][j].getWest().isLocked())
						maze+= "x";
					else
						maze+= "|";
					if(this.rooms[i][j].isExit())
						maze+= "E";
					else if(i == this.playerRow && j == this.playerCol)
						maze+= "P";
					else
						maze+= " ";
					maze += "";
				}
				if(this.rooms[i][j-1].getEast().isLocked())
					maze+= "x";
				else
					maze+= "|";
				maze += "\n";			
		}
		for (j = 0; j < this.rooms[0].length; j++){
			maze += "*";
			if(this.rooms[this.rooms.length-1][j].getSouth().isLocked())
				maze+= "x";
			else
				maze+= "-";
		}
		maze+="*\n";
		return maze;
	}
	
	public void moveNorth() {
		Room curRoom = this.rooms[this.playerRow][this.playerCol];
		if(!curRoom.getNorth().canPass()) 
			System.out.println("Door is Locked, find another way around.\n");
		else {
			this.playerRow--;
			if(this.playerRow < 0)
				this.playerRow = this.rooms.length -1;
		}
		
	}
	public void moveSouth() {
		Room curRoom = this.rooms[this.playerRow][this.playerCol];
		if(!curRoom.getSouth().canPass()) 
			System.out.println("Door is Locked, find another way around.\n");
		else {
			this.playerRow++;
			if(this.playerRow >= this.rooms.length)
				this.playerRow = 0;
		}
	}
	public void moveEast() {
		Room curRoom = this.rooms[this.playerRow][this.playerCol];
		if(!curRoom.getEast().canPass()) 
			System.out.println("Door is Locked, find another way around.\n");
		else {
			this.playerCol++;
			if(this.playerCol >= this.rooms.length)
				this.playerCol = 0;
		}
	}
	public void moveWest() {
		Room curRoom = this.rooms[this.playerRow][this.playerCol];
		if(!curRoom.getWest().canPass()) 
			System.out.println("Door is Locked, find another way around.\n");
		else {
			this.playerCol--;
			if(this.playerCol < 0)
				this.playerCol= this.rooms.length -1;
		}
	}
	public boolean playerInExit() {
		return this.rooms[this.playerRow][this.playerCol].isExit();
	}

	public Point getPosition() { 
		return new Point(this.playerRow,this.playerRow);
	}
	
	public Room getCurrentRoom() {
		return this.rooms[this.playerRow][this.playerCol];
	}

	public boolean mazeTraversal() {
		boolean grid[][] = new boolean[this.dimension][this.dimension];
		for(int i = 0; i < this.dimension; i++)
			for(int j = 0; j < this.dimension; j++)
				grid[i][j] = false;
		
		mazeTraversalHelper(this.playerRow, this.playerCol, grid);
		
		return grid[this.dimension-1][this.dimension-1];
	}
	public void mazeTraversalHelper(int row, int col, boolean grid[][]) {
		Room curRoom = this.rooms[row][col];
		grid[row][col] = true;
		if(curRoom.isExit())
			return;
		
		int northRow = row -1, northCol = col, 
				southRow = row +1, southCol = col, 
				eastRow = row, eastCol = col +1, 
				westRow = row, westCol = col -1;
		
		if(northRow < 0)
			northRow = this.dimension -1;
		if(southRow >= this.dimension)
			southRow = 0;
		if(eastCol >= this.dimension)
			eastCol = 0;
		if(westCol < 0)
			westCol = this.dimension -1;
		
		if(!curRoom.getNorth().isLocked() && !grid[northRow][northCol])
			mazeTraversalHelper(northRow,northCol,grid);
		if(!curRoom.getSouth().isLocked() && !grid[southRow][northCol])
			mazeTraversalHelper(southRow, southCol, grid);
		if(!curRoom.getEast().isLocked() && !grid[eastRow][eastCol])
			mazeTraversalHelper(eastRow,eastCol,grid);
		if(!curRoom.getWest().isLocked() && !grid[westRow][westCol])
			mazeTraversalHelper(westRow,westCol, grid);
	}
}

