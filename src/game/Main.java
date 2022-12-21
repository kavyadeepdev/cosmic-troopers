package game;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		// create window object
		JFrame window = new JFrame();

		// basic window interaction options
		window.setTitle("CosmicTroopers");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// adding game panel to the window
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); // fitting the window to the size of the contents

		// making window visible
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
		// starting the game thread
		gamePanel.startGameThread();
	}
}
