package game;

public class Status {
	// declaring variables
	GamePanel panel;
	KeyHandler key;
	int prevLives;
	
	// setting values for required variables
	public Status(GamePanel panel, KeyHandler key) {
		this.panel = panel;
		this.key = key;
		prevLives = panel.stats.lives;
	}
	
	void checkIsLive() {
		if (panel.isLive == false) {
			if (key.enterPressed == true) {
				panel.title = "";
				panel.introText = "";
				panel.controlsText = "";
				panel.isLive = true;
			}
		}
	}
	
	void checkLives() {
		// checking if increment counter is full
		if (panel.stats.increment == 5) {
			if (panel.stats.lives < 5)
				++panel.stats.lives; // increments lives
			panel.stats.increment = 0;
		}
		
		// checking if decrement counter is full
		if (panel.stats.decrement == 5) {
			if (panel.stats.lives > 0)
				--panel.stats.lives; // decrements lives
			panel.stats.decrement = 0;
		}
		
		// checking if lives has just turned 0
		if (panel.stats.lives == 0 && prevLives == 1) {
			panel.sound.death(); // death sound is played
		}
		
		// setting parameters for loss screen
		if (panel.stats.lives == 0) {
			panel.isLive = false;
			panel.deathText = "YOU DIED!";
			panel.retryText = "Press ENTER to retry!";
			panel.player.speed = 0;
			panel.bullet.setStopValues();
			panel.enemy1.setStopValues();
			panel.enemy2.setStopValues();
			panel.enemy3.setStopValues();
			if (key.enterPressed == true) {
				panel.isLive = true;
				panel.deathText = "";
				panel.retryText = "";
				panel.stats.setDefaultValues();
				panel.player.setStartValues();
				panel.bullet.setStartValues();
				panel.enemy1.setStartValues();
				panel.enemy2.setStartValues();
				panel.enemy3.setStartValues();
			}
		}
		prevLives = panel.stats.lives;
	}
	
	// execute the operations defined
	public void update() {
		checkIsLive();
		checkLives();
	}
}
