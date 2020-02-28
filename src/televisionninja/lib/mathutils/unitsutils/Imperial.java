package televisionninja.lib.mathutils.unitsutils;

/**
 * @author TelevisionNinja
 *
 */
public class Imperial {
	/**
	 * if awg is one of the zero names, then put -(# of zeros - 1)
	 * 
	 * @param awg
	 * @return diameter
	 * @author TelevisionNinja
	 */
	public static double awgToin(final double awg) {
		return Math.pow(92d, (36d - awg) / 39d) / 200d;
	}
	
	/**
	 * returns awg name of a given radius in inches
	 * if negative returned, then add 1 to the absolute value of the number to get the number of zeros
	 * unless if the returned number is near 0 then the name is 0
	 * 
	 * @param i
	 * @return awg
	 * @author TelevisionNinja
	 */
	public static double inToAwg(final double i) {
		return 36d - 39d * (Math.log(200d * i) / Math.log(92d));
	}

	/**
	 * 
	 * @param f
	 * @return
	 * @author TelevisionNinja
	 */
	public static double footToInch(final double f) {
		return f * 12d;
	}

	/**
	 * 
	 * @param f
	 * @return
	 * @author TelevisionNinja
	 */
	public static double footToYard(final double f) {
		return f / 3d;
	}

	/**
	 * 
	 * @param i
	 * @return
	 * @author TelevisionNinja
	 */
	public static double inchToFoot(final double i) {
		return i / 12d;
	}

	/**
	 * 
	 * @param y
	 * @return
	 * @author TelevisionNinja
	 */
	public static double yardToFoot(final double y) {
		return y * 3d;
	}
}