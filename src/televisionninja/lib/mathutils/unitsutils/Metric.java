package televisionninja.lib.mathutils.unitsutils;

/**
 * @author TelevisionNinja
 *
 */
public class Metric {
	/**
	 * if awg is one of the zero names, then put -(# of zeros - 1)
	 * 
	 * @param awg
	 * @return diameter
	 * @author TelevisionNinja
	 */
	public static double awgTomm(final double awg) {
		return Math.pow(92d, (36d - awg) / 39d) / 1000d * 127d;
	}

	/**
	 * 
	 * @param c
	 * @return
	 * @author TelevisionNinja
	 */
	public static double baseToCenti(final double b) {
		return b * 100d;
	}

	/**
	 *
	 * @param c
	 * @return
	 * @author TelevisionNinja
	 */
	public static double baseToDeci(final double b) {
		return b * 10d;
	}

	/**
	 * 
	 * @param c
	 * @return
	 * @author TelevisionNinja
	 */
	public static double centiToBase(final double c) {
		return c / 100d;
	}

	/**
	 * SI unit prefixes
	 * 
	 * @param prefix
	 * @return
	 * @author TelevisionNinja
	 */
	public static long prefixToPower(final String prefix) {
		switch (prefix) {
		case "yotta":
			return 24;
		case "zetta":
			return 21;
		case "exa":
			return 18;
		case "peta":
			return 15;
		case "tera":
			return 12;
		case "giga":
			return 9;
		case "mega":
			return 6;
		case "kilo":
			return 3;
		case "hecto":
			return 2;
		case "deca":
			return 1;
		case "deci":
			return -1;
		case "centi":
			return -2;
		case "milli":
			return -3;
		case "micro":
			return -6;
		case "nano":
			return -9;
		case "pico":
			return -12;
		case "femto":
			return -15;
		case "atto":
			return -18;
		case "zepto":
			return -21;
		case "yocto":
			return -24;
		default:
			return 0;
		}
	}

	/**
	 * SI unit prefixes
	 * 
	 * @param n
	 * @param prefix1
	 * @param prefix2
	 * @return
	 * @author TelevisionNinja
	 */
	public static double prefixToPrefix_1(final double n, final String prefix1, final String prefix2) {
		long power1 = 0,
				power2 = 0,
				power = 0;

		switch (prefix1) {
		case "yotta":
			power1 = 24;
			break;
		case "zetta":
			power1 = 21;
			break;
		case "exa":
			power1 = 18;
			break;
		case "peta":
			power1 = 15;
			break;
		case "tera":
			power1 = 12;
			break;
		case "giga":
			power1 = 9;
			break;
		case "mega":
			power1 = 6;
			break;
		case "kilo":
			power1 = 3;
			break;
		case "hecto":
			power1 = 2;
			break;
		case "deca":
			power1 = 1;
			break;
		case "deci":
			power1 = -1;
			break;
		case "centi":
			power1 = -2;
			break;
		case "milli":
			power1 = -3;
			break;
		case "micro":
			power1 = -6;
			break;
		case "nano":
			power1 = -9;
			break;
		case "pico":
			power1 = -12;
			break;
		case "femto":
			power1 = -15;
			break;
		case "atto":
			power1 = -18;
			break;
		case "zepto":
			power1 = -21;
			break;
		case "yocto":
			power1 = -24;
			break;
		}

		switch (prefix2) {
		case "yotta":
			power2 = 24;
			break;
		case "zetta":
			power2 = 21;
			break;
		case "exa":
			power2 = 18;
			break;
		case "peta":
			power2 = 15;
			break;
		case "tera":
			power2 = 12;
			break;
		case "giga":
			power2 = 9;
			break;
		case "mega":
			power2 = 6;
			break;
		case "kilo":
			power2 = 3;
			break;
		case "hecto":
			power2 = 2;
			break;
		case "deca":
			power2 = 1;
			break;
		case "deci":
			power2 = -1;
			break;
		case "centi":
			power2 = -2;
			break;
		case "milli":
			power2 = -3;
			break;
		case "micro":
			power2 = -6;
			break;
		case "nano":
			power2 = -9;
			break;
		case "pico":
			power2 = -12;
			break;
		case "femto":
			power2 = -15;
			break;
		case "atto":
			power2 = -18;
			break;
		case "zepto":
			power2 = -21;
			break;
		case "yocto":
			power2 = -24;
			break;
		}

		if ((power1 > 0 && power2 > 0) || (power1 < 0 && power2 < 0)) {
			if (power1 > power2) {
				power = power1 - power2;
			}
			else {
				power = power2 - power1;
			}
		}
		else {
			if (power1 < 0) {
				power = power2 - power1;
			}
			else {
				power = power1 - power2;
			}
		}
		if (power1 > power2) {
			return n * Math.pow(10, power);
		}
		return n / Math.pow(10, power);
	}

	/**
	 * SI unit prefixes
	 * 
	 * @param n
	 * @param prefix1
	 * @param prefix2
	 * @return
	 * @author TelevisionNinja
	 */
	public static double prefixToPrefix_2(final double n, final String prefix1, final String prefix2) {
		final long power1 = prefixToPower(prefix1),
				power2 = prefixToPower(prefix2);
		long p = 0;

		if ((power1 > 0 && power2 > 0) || (power1 < 0 && power2 < 0)) {
			if (power1 > power2) {
				p = power1 - power2;
			}
			else {
				p = power2 - power1;
			}
		}
		else {
			if (power1 < 0) {
				p = power2 - power1;
			}
			else {
				p = power1 - power2;
			}
		}
		if (power1 > power2) {
			return n * Math.pow(10, p);
		}
		return n / Math.pow(10, p);
	}

	/**
	 * SI unit prefixes
	 * 
	 * @param n
	 * @param prefix1
	 * @param prefix2
	 * @return
	 * @author TelevisionNinja
	 */
	public static double prefixToPrefix_3(final double n, final String prefix1, final String prefix2) {
		final long power1 = prefixToPower(prefix1),
				power2 = prefixToPower(prefix2);
		long p = 0;

		if (power1 > power2) {
			p = power1 - power2;
		}
		else {
			p = power2 - power1;
		}

		if (power1 > power2) {
			return n * Math.pow(10, p);
		}
		return n / Math.pow(10, p);
	}
}