package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.KeyHandler;

public class Bullet extends Entity {
	// declaring isShooting variable to check if shot is out of bounds
	Boolean isShooting;
	
	// initializing required variables
	public Bullet(GamePanel panel, KeyHandler key) {
		super(panel, key);
		setDefaultValues();
		getBulletImage();
	}
	
	// setting values for variables
	void setDefaultValues() {
		x = -48;
		y = panel.player.y;
		speed = 0;
		isShooting = false;
	}
	
	public void setStartValues() {
		speed = 32;
	}
	
	public void setStopValues() {
		speed = 0;
	}
	
	// obtaining sprite for bullet
	void getBulletImage() {
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/bullet/bullet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// updates every frame
	public void update() {
		if (panel.isLive == false) {
			if (key.enterPressed == true) {
				setStartValues();
			}
		}
		
		// shooting mechanism
		if (key.spacePressed == true && isShooting == false && panel.isLive == true) {
			panel.sound.shoot();
			x = panel.player.x;
			isShooting = true;
		}
		if (isShooting == true) {
			y -= speed;
		}
		if (y < -48) {
			y = 672;
			isShooting = false;
		}
		
		// collision detection between the bullet and an enemy
		if (panel.enemy1.y + 48 - 4 >= y && x + 9 >= panel.enemy1.x && x + 7 <= panel.enemy1.x + 24) {
			// operations on collision
			panel.sound.kill();
			++panel.stats.increment;
			++panel.stats.score;
			
			// reset enemy
			panel.enemy1.y = -64;
			panel.enemy1.x = panel.enemy1.randomSpawn();
			
			// reset bullet
			y = 672;
			isShooting = false;
		}
		
		if (panel.enemy2.y + 44 >= y && x + 9 >= panel.enemy2.x && x + 7 <= panel.enemy2.x + 24) {
			// operations on collision
			panel.sound.kill();
			++panel.stats.increment;
			++panel.stats.score;
			
			// reset enemy
			panel.enemy2.y = -64;
			panel.enemy2.x = panel.enemy2.randomSpawn();
			
			// reset bullet
			y = 672;
			isShooting = false;
		}
		
		if (panel.enemy3.y + 48 - 4 >= y && x + 9 >= panel.enemy3.x && x + 7 <= panel.enemy3.x + 24) {
			// operations on collision
			panel.sound.kill();
			++panel.stats.increment;
			++panel.stats.score;
			
			// reset enemy
			panel.enemy3.y = -64;
			panel.enemy3.x = panel.enemy3.randomSpawn();
			
			// reset bullet
			y = 672;
			isShooting = false;
		}
	}
	
	// display the bullet
	public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
		g2.drawImage(image, x, y, panel.tileSize, panel.tileSize, null);
	}
}
