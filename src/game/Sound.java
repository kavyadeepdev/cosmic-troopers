package game;

import javax.sound.sampled.*;

public class Sound {
	// declaring variables
	AudioInputStream ais;
	Clip shoot, kill, damage, death;
	
	// setting default values
	public Sound() {
		ais = null;
		death = null;
		setDefaultValues();
	}
	
	// setting intended values
	void setDefaultValues() {
		try {
			// shoot sound
			ais = AudioSystem.getAudioInputStream(Sound.class.getResource("/sound/shoot.wav"));
			shoot = AudioSystem.getClip();
	        shoot.open(ais);
	        
	        // kill sound
			ais = AudioSystem.getAudioInputStream(Sound.class.getResource("/sound/kill.wav"));
			kill = AudioSystem.getClip();
	        kill.open(ais);
	        
	        // damage sound
			ais = AudioSystem.getAudioInputStream(Sound.class.getResource("/sound/damage.wav"));
			damage = AudioSystem.getClip();
	        damage.open(ais);
	        
	        // death sound
			ais = AudioSystem.getAudioInputStream(Sound.class.getResource("/sound/death.wav"));
			death = AudioSystem.getClip();
	        death.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// function for shoot sound
	public void shoot()
	{
		shoot.setFramePosition(0);
		shoot.start();
	}

	// function for kill sound
	public void kill()
	{
		kill.setFramePosition(0);
		kill.start();
	}

	// function for damage sound
	public void damage()
	{
		damage.setFramePosition(0);
		damage.start();
	}

	// function for death sound
	public void death()
	{
		death.setFramePosition(0);
		death.start();
	}
}
