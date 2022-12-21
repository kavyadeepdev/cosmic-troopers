package entity;

import java.awt.image.BufferedImage;

import game.GamePanel;
import game.KeyHandler;

public class Entity {
	// declaring common variables
	public int x, y;
	public int speed;
	public GamePanel panel;
	public KeyHandler key;
	public BufferedImage sprite;
	
	// setting required values for common variables
	public Entity (GamePanel panel, KeyHandler key) {
		this.panel = panel;
		this.key = key;
	}
}
