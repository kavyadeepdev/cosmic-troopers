package game;

public class Stats {
	// declaring stat variables
	public int score;
	public int lives;
	public int increment;
	public int decrement;
	
	Stats() {
		setDefaultValues();
	}
	
	// setting default values for stats
	void setDefaultValues() {
		score = 0;
		lives = 5;
		increment = 0;
		decrement = 0;
	}
}
