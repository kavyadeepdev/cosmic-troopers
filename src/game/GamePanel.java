package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Player;
import entity.Bullet;
import entity.Enemy;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	// setting screen settings
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	
	public final int screenWidth = tileSize * maxScreenCol; // 768px
	public final int screenHeight = tileSize * maxScreenRow; // 576px
	
	final int FPS = 30;
	
	// declaring required objects and variables
	public String title = "Cosmic Troopers";
	public String introText = "Press ENTER to play!";
	public String controlsText = "Use: A or <- to move left, D or -> to move right, SPACE or W or ^ to shoot";
	public String deathText = "";
	public String retryText = "";

	Thread gameThread;
	KeyHandler key = new KeyHandler();
	
	public Boolean isLive = false;
	
	BufferedImage background;
	public Stats stats = new Stats();
	public Sound sound = new Sound();
	public Player player = new Player(this, key);
	public Enemy enemy1 = new Enemy(this, key, -48);
	public Enemy enemy2 = new Enemy(this, key, -96);
	public Enemy enemy3 = new Enemy(this, key, -144);
	public Bullet bullet = new Bullet(this, key);
	Status status = new Status(this, key);
	
	// setting default panel options
	public GamePanel() {
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/background/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(key);
	}
	
	// starting game thread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS; // Draw every 0.016 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (gameThread != null) {
			// update components
			update();
			
			// draw components
			repaint();
			
			// sleep (wait for the next frame)
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				
				// in case update() and repaint() take too long
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// updates every frame
	public void update() {
		// updates individual components
		player.update();
		enemy1.update();
		enemy2.update();
		enemy3.update();
		bullet.update();
		status.update();
	}
	
	// display components
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g.drawImage(background, 0, 0, null);
		player.draw(g2);
		enemy1.draw(g2);
		enemy2.draw(g2);
		enemy3.draw(g2);
		bullet.draw(g2);

		// setting font settings
		g2.setColor(Color.white);

		g2.setFont(new Font("Arial", Font.BOLD, 48));
		// display game title
		g2.drawString(title, 150, 192);
		
		
		g2.setFont(new Font("Arial", Font.BOLD, 20));
		// display "Press ENTER to play!"
		g2.drawString(introText, 250, 240);
		g2.drawString(deathText, 314, 240);
		

		g2.setFont(new Font("Arial", Font.BOLD, 16));
		// display instructions for playing the game
		g2.drawString(controlsText, 50, 288);
		g2.drawString(retryText, 274, 288);
		
		// display stats
		String scoreText = "Score: " + stats.score;
		g2.drawString(scoreText, 24, screenHeight - 48);
		String livesText = "Lives: " + stats.lives;
		g2.drawString(livesText, 24, screenHeight - 96);
		String decText = "Life Drain: " + stats.decrement;
		g2.drawString(decText, 24, screenHeight - 144);
		String incText = "Life Gain: " + stats.increment;
		g2.drawString(incText, 24, screenHeight - 192);
		g2.dispose();
	}
}
