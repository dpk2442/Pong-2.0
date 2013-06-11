package helpers;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound.
 */
public enum SoundEffect {
	CLICK("sound/click.wav"),
	BOUNCE("sound/bounce.wav"),
	SCORE("sound/score.wav");


	public static double volume = 1.0;

	// Each sound effect has its own clip, loaded with its own sound file.
	private Clip clip;

	// Constructor to construct each element of the enum with its own sound file.
	SoundEffect(String soundFileName) {
		try {
			// Use URL (instead of File) to read from disk and JAR.
			URL url = this.getClass().getClassLoader().getResource(soundFileName);
			// Set up an audio input stream piped from the sound file.
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			// Get a clip resource.
			clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	// Play or Re-play the sound effect from the beginning, by rewinding.
	public void play(double gain) {
		gain = (SoundEffect.volume == 0.0) ? 0.0 : gain;
		if (clip.isRunning())
			clip.stop();   // Stop the player if it is still running
		clip.setFramePosition(0); // rewind to the beginning

		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		float dB = (float)(Math.log(gain)/Math.log(10.0)*20.0);
		gainControl.setValue(dB);

		clip.start();     // Start playing
	}

	public void play() {
		play(volume);
	}

	// Optional static method to pre-load all the sound files.
	static void init() {
		values(); // calls the constructor for all the elements
	}
}
