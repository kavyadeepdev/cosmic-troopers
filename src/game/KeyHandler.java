package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	// declaring variables
	public boolean enterPressed, leftPressed, rightPressed, spacePressed;
	
	// overrides default value - obsolete (required for proper functioning without any warnings or errors)
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	// check if key is pressed
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			spacePressed = true;
		}
	}

	// check if key is released
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			spacePressed = false;
		}
	}

}
