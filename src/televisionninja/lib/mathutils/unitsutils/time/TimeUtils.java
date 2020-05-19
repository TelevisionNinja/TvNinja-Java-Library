package televisionninja.lib.mathutils.unitsutils.time;

import televisionninja.lib.stringutils.StringUtils;

/**
 * @author TelevisionNinja
 *
 */
public class TimeUtils {
	/**
	 * 
	 * @param time
	 * 		y:ddd:hh:mm:ss
	 * @param add
	 * 		y:ddd:hh:mm:ss
	 * @return
	 * 		y:ddd:hh:mm:ss
	 * @author TelevisionNinja
	 */
	public static String addTime(final String time, final String add) {
		final String separator = ":";
		final String[] s = time.split(separator),
				e = add.split(separator);
		final int length = s.length;
		final long[] sl = new long[length],
				el = new long[length];
		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		/*
		 * indices
		 * 0 1 2 3 4
		 * y d h m s
		 */

		long sec = el[4] + sl[4],
				mins = el[3] + sl[3],
				hours = el[2] + sl[2],
				days = el[1] + sl[1],
				years = el[0] + sl[0];

		if (sec >= 60) {
			final long carry = sec / 60;
			sec %= 60;
			mins += carry;
		}

		if (mins >= 60) {
			final long carry = mins / 60;
			mins %= 60;
			hours += carry;
		}

		if (hours >= 24) {
			final long carry = hours / 24;
			hours %= 24;
			days += carry;
		}

		if (days >= 365) {
			final long carry = days / 365;
			days %= 365;
			years += carry;
		}

		return years + separator + days + separator + hours + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2) + separator + StringUtils.addLeadingToString_2(Long.toString(sec), '0', 2);
	}

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
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static String intArrToTimeStr(final int[] arr) {
		return arr[0] + ":" + arr[1];
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
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static String strArrToTimeStr(final String[] arr) {
		if (arr[2] == null) {
			return arr[0] + ':' + arr[1];
		}

		return arr[0] + ':' + arr[1] + ' ' + arr[2];
	}

	/**
	 * safest version
	 * 
	 * @param sub
	 * 		y:ddd:hh:mm:ss
	 * @param time
	 * 		y:ddd:hh:mm:ss
	 * @return
	 * 		y:ddd:hh:mm:ss
	 * @author TelevisionNinja
	 */
	public static String subtractTime_1(final String time, final String sub) {
		final String separator = ":";
		String sign = "";
		final String[] s = sub.split(separator),
				e = time.split(separator);
		final int length = s.length;
		final long[] sl = new long[length],
				el = new long[length];
		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		/*
		 * indices
		 * 0 1 2 3 4
		 * y d h m s
		 */

		if (el[0] < sl[0]) {
			for (int x = 0; x < length; x++) {
				final long temp = el[x];
				el[x] = sl[x];
				sl[x] = temp;
			}
			sign = "-";
		}

		if (sl[4] > el[4]) {
			final long multiply = sl[4] / 60 + 1;
			el[3] -= multiply;
			el[4] += 60 * multiply;
		}

		final long sec = el[4] - sl[4];

		if (sl[3] > el[3]) {
			final long multiply = sl[3] / 60 + 1;
			el[2] -= multiply;
			el[3] += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}

		final long mins = el[3] - sl[3];

		if (sl[2] > el[2]) {
			final long multiply = sl[2] / 24 + 1;
			el[1] -= multiply;
			el[2] += 24 * multiply;
		}

		final long hours = el[2] - sl[2];

		if (sl[1] > el[1]) {
			final long multiply = sl[1] / 365 + 1;
			el[0] -= multiply;
			el[1] += 365 * multiply;
		}

		final long days = el[1] - sl[1],
				years = el[0] - sl[0];

		return sign + years + separator + days + separator + hours + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2) + separator + StringUtils.addLeadingToString_2(Long.toString(sec), '0', 2);
	}

	/**
	 * efficient version
	 * 
	 * @param sub
	 * 		y:ddd:hh:mm:ss
	 * @param time
	 * 		y:ddd:hh:mm:ss
	 * @return
	 * 		y:ddd:hh:mm:ss
	 * @author TelevisionNinja
	 */
	public static String subtractTime_2(final String time, final String sub) {
		final String separator = ":";
		String sign = "";
		final String[] s = sub.split(separator),
				e = time.split(separator);
		final int length = s.length;
		final long[] sl = new long[length],
				el = new long[length];
		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		/*
		 * indices
		 * 0 1 2 3 4
		 * y d h m s
		 */

		if (el[0] < sl[0]) {
			for (int x = 0; x < length; x++) {
				final long temp = el[x];
				el[x] = sl[x];
				sl[x] = temp;
			}

			sign = "-";
		}

		if (sl[4] > el[4]) {
			el[3] -= 1;
			el[4] += 60;
		}

		final long sec = el[4] - sl[4];

		if (sl[3] > el[3]) {
			el[2] -= 1;
			el[3] += 60;
		}

		final long mins = el[3] - sl[3];

		if (sl[2] > el[2]) {
			el[1] -= 1;
			el[2] += 24;
		}

		final long hours = el[2] - sl[2];

		if (sl[1] > el[1]) {
			el[0] -= 1;
			el[1] += 365;
		}

		final long days = el[1] - sl[1],
				years = el[0] - sl[0];

		return sign + years + separator + days + separator + hours + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2) + separator + StringUtils.addLeadingToString_2(Long.toString(sec), '0', 2);
	}

	/**
	 * 
	 * @param time
	 * @return
	 * @author TelevisionNinja
	 */
	public static String[] timeStrToStrArr(final String time) {
		final String[] valuesAndHalf = time.split(" "),
				valuesString = valuesAndHalf[0].split(":"),
				finalArr = {valuesString[0], valuesString[1], null};

		if (valuesAndHalf.length > 1) {
			finalArr[2] = valuesAndHalf[1];
		}

		return finalArr;
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
	 * @param colon
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twelveHrToTwentyFourHr_3(final String t12, final boolean colon) {
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

		if (colon) {
			return hours + ":" + m[0];
		}
		else {
			return hours + m[0];
		}
	}

	/**
	 * 
	 * @param t12
	 * 		hh:mm am/pm
	 * @param colon
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twelveHrToTwentyFourHr_4(final String t12, final boolean colon) {
		String hours = "0";
		final String[] t = t12.split(":"),
				m = t[1].split(" ");
		long hr = Long.parseLong(t[0]);

		hr %= 12;

		if (m[1].toLowerCase().contains("p")) {
			hr += 12;
		}

		if (hr < 10) {
			hours += hr;
		}
		else {
			hours = Long.toString(hr);
		}

		if (colon) {
			return hours + ":" + m[0];
		}
		else {
			return hours + m[0];
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
	 * any value to 12 hr
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
		long hr = Long.parseLong(t24.substring(0, 2));
		hr %= 24;
		if (hr >= 12) {
			hr %= 12;
			end = "pm";
		}
		if (hr == 0) {
			hr = 12;
		}

		if (colon) {
			return hr + t24.substring(2) + space + end;
		}
		else {
			return hr + ":" + t24.substring(2) + space + end;
		}
	}

	/**
	 * 
	 * @param t24
	 * 		hhmm or hh:mm
	 * @param colon
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twentyFourHrToTwelveHr_4(final String t24, final boolean colon) {
		String end = "am";
		final String space = " ";
		long hr = Long.parseLong(t24.substring(0, 2));

		if (hr >= 12) {
			hr %= 12;
			end = "pm";
		}

		if (hr == 0) {
			hr = 12;
		}

		if (colon) {
			return hr + t24.substring(2) + space + end;
		}
		else {
			return hr + ":" + t24.substring(2) + space + end;
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