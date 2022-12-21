package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.KeyHandler;

public class Player extends Entity {
	// setting values for required variables
	public Player(GamePanel panel, KeyHandler key) {
		super(panel, key);
		setDefaultValues();
		getPlayerImage();
	}
	
	// setting default values for variables
	void setDefaultValues() {
		x = 360;
		y = 512;
		speed = 0;
	}

	public void setStartValues() {
		speed = 16;
	}
	
	public void setStopValues() {
		speed = 0;
	}
	
	// obtaining sprite for player object
	void getPlayerImage() {
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/player/spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// updates every second
	public void update() {
		if (panel.isLive == false) {
			if (key.enterPressed == true) {
				setStartValues();
			}
		}
		// facilitating movement
		if (key.leftPressed == true && x - speed >= 0) {
			x -= speed;
		}
		if (key.rightPressed == true && x + speed <= (panel.screenWidth - panel.tileSize)) { // -48 because player width on scale is 48
			x += speed;
		}
	}
	
	// display player sprite
	public void draw(Graphics2D g2) {
		BufferedImage image = sprite;
		g2.drawImage(image, x, y, panel.tileSize, panel.tileSize, null);
	}
}
