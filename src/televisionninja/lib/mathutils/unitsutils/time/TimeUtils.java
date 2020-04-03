package televisionninja.lib.mathutils.unitsutils.time;

/**
 * @author TelevisionNinja
 *
 */
public class TimeUtils {
	/**
	 * 
	 * @param ampm
	 * @return
	 * @author TelevisionNinja
	 */
	public static String amPmSwitcher(final String ampm) {
		if (ampm.toLowerCase().contains("a")) {
			return "pm";
		}
		else {
			return "am";
		}
	}

	/**
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double centuryToDecade(final double d) {
		return d * 10d;
	}

	/**
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double dayToHr(final double d) {
		return d * 24d;
	}

	/**
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double dayToWeek(final double d) {
		return d / 7d;
	}

	/**
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double dayToYr(final double d) {
		return d / 365d;
	}

	/**
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double decadeToCentury(final double d) {
		return d / 10d;
	}

	/**
	 * 
	 * @param d
	 * @return
	 * @author TelevisionNinja
	 */
	public static double decadeToYr(final double d) {
		return d * 10d;
	}

	/**
	 * System.currentTimeMillis()
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public static double getTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double hrToDay(final double h) {
		return h / 24d;
	}

	/**
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double hrToMin(final double h) {
		return h * 60d;
	}

	/**
	 * 
	 * @param h
	 * @return
	 * @author TelevisionNinja
	 */
	public static double hrToSec(final double h) {
		return h * 3600d;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double microsecToMillisec(final double m) {
		return m / 1000d;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double microsecToNanosec(final double m) {
		return m * 1000d;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double millisecToMicrosec(final double m) {
		return m * 1000d;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double millisecToSec(final double m) {
		return m / 1000d;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double minToHr(final double m) {
		return m / 60d;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double minToSec(final double m) {
		return m * 60d;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double monthToWeek(final double m) {
		return m * (365d / 84d);
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double monthToYr(final double m) {
		return m / 12d;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @author TelevisionNinja
	 */
	public static double nanosecToMicrosec(final double m) {
		return m / 1000d;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double secToHr(final double s) {
		return s / 3600d;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double secToMillisec(final double s) {
		return s * 1000d;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @author TelevisionNinja
	 */
	public static double secToMin(final double s) {
		return s / 60d;
	}

	/**
	 * 
	 * @param t12
	 * 		hh:mm am/pm
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twelveHrToTwentyFourHr_1(final String t12) {
		String hours = "0";
		final String[] t = t12.split(":"),
				m = t[1].split(" ");
		long hr = Long.parseLong(t[0]);
		hr %= 12;
		if (m[1].toLowerCase().contains("p")) {
			hr += 12;
		}
		else if (m[1].toLowerCase().contains("a") && hr == 12) {
			hr = 0;
		}
		if (hr < 10) {
			hours += hr;
		}
		else {
			hours = Long.toString(hr);
		}
		return hours + m[0];
	}

	/**
	 * 
	 * @param t12
	 * 		hh:mm am/pm
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twelveHrToTwentyFourHr_2(final String t12) {
		String hours = "0";
		final int zero = 0,
				one = 1,
				twelve = 12;
		final String[] t = t12.split(":"),
				m = t[one].split(" ");
		long hr = Long.parseLong(t[zero]);
		hr %= twelve;
		if (m[one].toLowerCase().contains("p")) {
			hr += twelve;
		}
		else if (m[one].toLowerCase().contains("a") && hr == twelve) {
			hr = zero;
		}
		if (hr < 10) {
			hours += hr;
		}
		else {
			hours = Long.toString(hr);
		}
		return hours + m[zero];
	}

	/**
	 * 
	 * @param t12
	 * 		hh:mm am/pm
	 * @param colon
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twelveHrToTwentyFourHr_3(final String t12, final boolean colon) {
		String hours = "0";
		final int zero = 0,
				one = 1,
				twelve = 12;
		final String[] t = t12.split(":"),
				m = t[one].split(" ");
		long hr = Long.parseLong(t[zero]);
		hr %= twelve;
		if (m[one].toLowerCase().contains("p")) {
			hr += twelve;
		}
		else if (m[one].toLowerCase().contains("a") && hr == twelve) {
			hr = zero;
		}
		if (hr < 10) {
			hours += hr;
		}
		else {
			hours = Long.toString(hr);
		}

		if (colon) {
			return hours + ":" + m[zero];
		}
		else {
			return hours + m[zero];
		}
	}

	/**
	 * 
	 * @param t24
	 * 		hhmm
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twentyFourHrToTwelveHr_1(final String t24) {
		String end = "am";
		long hr = Long.parseLong(t24.substring(0, 2));
		hr %= 24;
		if (hr >= 12) {
			hr %= 12;
			end = "pm";
		}
		if (hr == 0) {
			hr = 12;
		}
		return hr + ":" + t24.substring(2) + " " + end;
	}

	/**
	 * 
	 * @param t24
	 * 		hhmm
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twentyFourHrToTwelveHr_2(final String t24) {
		String end = "am";
		final int twelve= 12,
				two = 2,
				zero = 0;
		long hr = Long.parseLong(t24.substring(zero, two));
		hr %= 24;
		if (hr >= twelve) {
			hr %= twelve;
			end = "pm";
		}
		if (hr == zero) {
			hr = twelve;
		}
		return hr + ":" + t24.substring(two) + " " + end;
	}

	/**
	 * 
	 * @param t24
	 * 		hhmm or hh:mm
	 * @param colon
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twentyFourHrToTwelveHr_3(final String t24, final boolean colon) {
		String end = "am";
		final String space = " ";
		final int twelve= 12,
				two = 2,
				zero = 0;
		long hr = Long.parseLong(t24.substring(zero, two));
		hr %= 24;
		if (hr >= twelve) {
			hr %= twelve;
			end = "pm";
		}
		if (hr == zero) {
			hr = twelve;
		}

		if (colon) {
			return hr + t24.substring(two) + space + end;
		}
		else {
			return hr + ":" + t24.substring(two) + space + end;
		}
	}

	/**
	 * 
	 * @param w
	 * @return
	 * @author TelevisionNinja
	 */
	public static double weekToDay(final double w) {
		return w * 7d;
	}

	/**
	 * 
	 * @param w
	 * @return
	 * @author TelevisionNinja
	 */
	public static double weekToMonth(final double w) {
		return w * (84d / 365d);
	}

	/**
	 * 
	 * @param y
	 * @return
	 * @author TelevisionNinja
	 */
	public static double yrToDay(final double y) {
		return y * 365d;
	}

	/**
	 * 
	 * @param y
	 * @return
	 * @author TelevisionNinja
	 */
	public static double yrToDecade(final double y) {
		return y / 10d;
	}

	/**
	 * 
	 * @param y
	 * @return
	 * @author TelevisionNinja
	 */
	public static double yrToMonth(final double y) {
		return y * 12d;
	}
}