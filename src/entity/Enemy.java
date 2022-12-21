package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.KeyHandler;

public class Enemy extends Entity {
	// declaring variable for default ordinate
	int ord;
	
	// initializing required variables
	public Enemy(GamePanel panel, KeyHandler key, int ord) {
		super(panel, key);
		this.ord = ord;
		setDefaultValues();
		getEnemyImage();
	}

	// setting default values for variables
	void setDefaultValues() {
		x = randomSpawn();
		y = ord;
		speed = 0;
	}
	
	public void setStartValues() {
		setDefaultValues();
		speed = 2;
	}
	
	public void setStopValues() {
		speed = 0;
	}
	
	// obtaining sprite for enemy objects
	void getEnemyImage() {
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/enemy/battleship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// function to set up random spawning
	public int randomSpawn() {
		double spawnPoint = Math.random();
		
		if (spawnPoint <= 0.0625)
			return 0;
		if (spawnPoint <= 0.125)
			return 1 * panel.tileSize;
		if (spawnPoint <= 0.1875)
			return 2 * panel.tileSize;
		if (spawnPoint <= 0.250)
			return 3 * panel.tileSize;
		if (spawnPoint <= 0.3125)
			return 4 * panel.tileSize;
		if (spawnPoint <= 0.375)
			return 5 * panel.tileSize;
		if (spawnPoint <= 0.4375)
			return 6 * panel.tileSize;
		if (spawnPoint <= 0.5625)
			return 7 * panel.tileSize;
		if (spawnPoint <= 0.625)
			return 8 * panel.tileSize;
		if (spawnPoint <= 0.6875)
			return 9 * panel.tileSize;
		if (spawnPoint <= 0.75)
			return 10 * panel.tileSize;
		if (spawnPoint <= 0.8125)
			return 11 * panel.tileSize;
		if (spawnPoint <= 0.875)
			return 12 * panel.tileSize;
		if (spawnPoint <= 0.9375)
			return (13 * panel.tileSize);
		if (spawnPoint <= 0.0625)
			return (14 * panel.tileSize);
		return (15 * panel.tileSize);
	}
	
	// updates every frame
	public void update() {
		if (panel.isLive == false) {
			if (key.enterPressed == true) {
				setStartValues();
			}
		}
		
		// movement
		y += speed;
		
		// changing speed with change in score to increase difficulty
		if (panel.stats.score > 20) {
			speed = 3;
		}
		else if (panel.stats.score > 40) {
			speed = 4;
		}
		else if (panel.stats.score > 80) {
			speed = 5;
		}
		else if (panel.stats.score > 120) {
			speed = 6;
		}
		else if (panel.stats.score > 160) {
			speed = 7;
		}
		else if (panel.stats.score > 200) {
			speed = 8;
		}
		
		//  checking for collision between an enemy object and the player object
		if (y + 48 >= panel.player.y && y <= panel.player.y + 48 && x + 48 >= panel.player.x && x <= panel.player.x + 48) {
			panel.sound.damage();
			--panel.stats.score;
			--panel.stats.lives;
			x = randomSpawn();
			y = ord;
		}
		
		// checking if the enemy object is out of bounds
		if (y > panel.screenHeight) {
			if (panel.stats.score > 0)
				--panel.stats.score;
			++panel.stats.decrement;
			x = randomSpawn();
			y = ord;
		}
	}
	
	// display the sprite for the enemy objects
	public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
		g2.drawImage(image, x, y, panel.tileSize, panel.tileSize, null);
	}
}
