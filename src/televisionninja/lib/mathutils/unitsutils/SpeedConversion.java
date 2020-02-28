/**
 * 
 */
package televisionninja.lib.mathutils.unitsutils;

/**
 * @author TelevisionNinja
 *
 */
public class SpeedConversion {
	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double kmhToKnot(final double k) {
		return LengthConversion.kmToNauticalMile(k);
	}

	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double knotTokm(final double k) {
		return LengthConversion.nauticalMileTokm(k);
	}

	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double knotTomi(final double k) {
		return LengthConversion.nauticalMileTomi(k);
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double miToKnot(final double m) {
		return LengthConversion.miToNauticalMile(m);
	}
}