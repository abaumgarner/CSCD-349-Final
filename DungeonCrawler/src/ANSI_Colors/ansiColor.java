package ANSI_Colors;

public class ansiColor {
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_BLACK = "\u001B[30m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_WHITE = "\u001B[37m";

	public ansiColor() {
	}
	
	private String black(){
		return ANSI_BLACK;
	}
	
	private String red(){
		return ANSI_RED;
	}
	
	private String green(){
		return ANSI_GREEN;
	}
	
	private String yellow() {
		return ANSI_YELLOW;
	}
	
	private String blue() {
		return ANSI_BLUE;
	}
	
	private String purple() {
		return ANSI_PURPLE;
	}
	
	private String cyan() {
		
	}
	
}
