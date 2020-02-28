/**
 * 
 */
package televisionninja.lib.mathutils.unitsutils;

/**
 * @author TelevisionNinja
 *
 */
public class LengthConversion {
	/**
	 * 
	 * @param in
	 * @return
	 * @author TelevisionNinja
	 */
	public static double inTomm(final double in) {
		return 127d / 5d * in;
	}

	/**
	 * 
	 * @param km
	 * @return
	 * @author TelevisionNinja
	 */
	public static double kmTomi(final double km) {
		return km * (15625d / 25146d);
	}

	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double kmToNauticalMile(final double k) {
		return k * (250d / 463d);
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double meterToNauticalMile(final double m) {
		return m / 1852d;
	}

	/**
	 * 
	 * @param mi
	 * @return
	 * @author TelevisionNinja
	 */
	public static double miTokm(final double mi) {
		return mi * (25146d / 15625d);
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double miToNauticalMile(final double m) {
		return m * (50292d / 57875d);
	}

	/**
	 * 
	 * @param mm
	 * @return
	 * @author TelevisionNinja
	 */
	public static double mmToin(final double mm) {
		return mm / 127d * 5d;
	}

	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double nauticalMileTokm(final double k) {
		return k * (463d / 250d);
	}

	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double nauticalMileToMeter(final double k) {
		return k * 1852d;
	}

	/**
	 * 
	 * @param k
	 * @return
	 * @author TelevisionNinja
	 */
	public static double nauticalMileTomi(final double k) {
		return k * (57875d / 50292d);
	}
}