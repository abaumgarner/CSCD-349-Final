package Maze_Setup;

public class Door {
	private boolean locked = false, open = false;

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

	public boolean canPass() {
		if (this.open)
			return true;
		if (!this.locked && !this.open)
			return true;
		else
			return false;
	}
}
