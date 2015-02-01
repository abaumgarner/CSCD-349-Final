package Maze_Setup;

public class Door {
	private boolean locked = false, open = false;
	
	
	/*
	 * Truth Table
	 * Locked - Open - Result
	 * T - T - THIS STATE SHOULD BE UNREACHABLE
	 * T - F - THE DOOR CANNOT BE PASSED THROUGH (QUESTION HAS BEEN ANSWERED WRONG)
	 * F - T - THE DOOR CAN BE PASSED THROUGH (QUESTION HAS BE ANSWERED CORRECTLY)
	 * F - F - THE DOOR CANNOT BE PASSED THROUGH (QUESTION HAS YET TO BE ANSWERED) 
	 */
	
	public boolean isLocked() {
		return this.locked;
	}
	public boolean isOpen() {
		return this.open;
	}
	public void lock() {
		this.locked = true;
	}
	public void unlock() {
		this.locked = false;
	}
	public void open() {
		this.open = true;
	}
	public void close() {
		this.open = false;
	}
	
	public boolean canPass(){
		if(this.open)
			return true;
		if(!this.locked && !this.open)
			return true;
		else
			return false;
	}
}
