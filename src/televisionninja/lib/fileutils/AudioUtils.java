package televisionninja.lib.fileutils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author TelevisionNinja
 *
 */
public class AudioUtils {
	/**
	 * plays until the end of the clip
	 * 
	 * @param file
	 * @author TelevisionNinja
	 */
	public static void play(final File file) {
		try {
			final Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		}
		catch (final UnsupportedAudioFileException e) {
			System.out.println("play() UnsupportedAudioFileException");
		}
		catch (final IOException e) {
			System.out.println("play() IOException");
		}
		catch (final LineUnavailableException e) {
			System.out.println("play() LineUnavailableException");
		}
	}
}