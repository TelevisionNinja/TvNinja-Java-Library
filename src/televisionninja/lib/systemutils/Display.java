package televisionninja.lib.systemutils;

import java.awt.GraphicsEnvironment;

/**
 * @author TelevisionNinja
 *
 */
public class Display {
	/**
	 * GraphicsEnvironment method
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public static long getPrimaryDisplayLength() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
	}

	/**
	 * GraphicsEnvironment method
	 * 
	 * @return
	 * 		hertz
	 * @author TelevisionNinja
	 */
	public static long getPrimaryDisplayRefreshRate() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
	}

	/**
	 * GraphicsEnvironment method
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public static long getPrimaryDisplayWidth() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
	}
}