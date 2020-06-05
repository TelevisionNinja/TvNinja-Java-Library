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
		final String[] s = time.split(":"),
				e = add.split(":");
		final long[] sl = new long[s.length],
				el = new long[s.length];
		for (int x = 0; x < s.length; x++) {
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

		return years +
				":" +
				days +
				":" +
				hours +
				":" +
				StringUtils.addLeadingToString_1(Long.toString(mins), '0', 2) +
				":" +
				StringUtils.addLeadingToString_1(Long.toString(sec), '0', 2);
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
	 * @param hours
	 * @param mins
	 * @return
	 * @author TelevisionNinja
	 */
	public static String[] cleanUp12HrClockTimeOverflow(long hours, final long mins, String half) {
		if (half.toLowerCase().contains("p")) {
			hours += 12;
		}

		final long[] values = cleanUp24HrClockTimeOverflow(hours, mins);

		if (values[0] >= 12) {
			values[0] %= 12;
			half = "pm";
		}
		else {
			half = "am";
		}

		if (values[0] == 0) {
			values[0] = 12;
		}

		return new String[] {String.valueOf(values[0]), String.valueOf(values[1]), half};
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] cleanUp24HrClockTime_1(long hours, long mins) {
		if (mins >= 60) {
			hours += mins / 60;

			mins %= 60;

			hours %= 24;
		}
		else if (mins < 0) {
			final long multiply = Math.abs(mins) / 60 + 1;

			hours -= multiply;

			mins += multiply * 60;
		}

		if (hours >= 24) {
			hours %= 24;
		}
		else if (hours < 0) {
			hours += (Math.abs(hours) / 24 + 1) * 24;
		}

		return new long[]{hours, mins};
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] cleanUp24HrClockTime_1(final long[] arr) {
		if (arr[1] >= 60) {
			arr[0] += arr[1] / 60;

			arr[1] %= 60;

			arr[0] %= 24;
		}
		else if (arr[1] < 0) {
			final long multiply = Math.abs(arr[1]) / 60 + 1;

			arr[0] -= multiply;

			arr[1] += multiply * 60;
		}

		if (arr[0] >= 24) {
			arr[0] %= 24;
		}
		else if (arr[0] < 0) {
			arr[0] += (Math.abs(arr[0]) / 24 + 1) * 24;
		}

		return arr;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] cleanUp24HrClockTime_2(final long hours, final long mins) {
		long[] result = new long[]{hours, mins};

		if (mins >= 60 || hours >= 24) {
			result = cleanUp24HrClockTimeOverflow(result);
		}

		if (mins < 0 || hours < 0) {
			result = cleanUp24HrClockTimeUnderflow(result);
		}

		return result;
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] cleanUp24HrClockTime_2(long[] arr) {
		if (arr[1] >= 60 || arr[0] >= 24) {
			arr = cleanUp24HrClockTimeOverflow(arr);
		}

		if (arr[1] < 0 || arr[0] < 0) {
			arr = cleanUp24HrClockTimeUnderflow(arr);
		}

		return arr;
	}

	/**
	 * 
	 * @param hours
	 * @param mins
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] cleanUp24HrClockTimeOverflow(long hours, long mins) {
		if (mins >= 60) {
			hours += mins / 60;
			mins %= 60;
		}

		if (hours >= 24) {
			hours %= 24;
		}

		return new long[] {hours, mins};
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] cleanUp24HrClockTimeOverflow(final long[] arr) {
		if (arr[1] >= 60) {
			arr[0] += arr[1] / 60;
			arr[1] %= 60;
		}

		if (arr[0] >= 24) {
			arr[0] %= 24;
		}

		return arr;
	}

	/**
	 * 
	 * @param hours
	 * @param mins
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] cleanUp24HrClockTimeUnderflow(long hours, long mins) {
		if (mins < 0) {
			final long multiply = Math.abs(mins) / 60 + 1;
			hours -= multiply;
			mins += multiply * 60;
		}

		if (hours < 0) {
			hours += (Math.abs(hours) / 24 + 1) * 24;
		}

		return new long[] {hours, mins};
	}

	/**
	 * 
	 * @param arr
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] cleanUp24HrClockTimeUnderflow(final long[] arr) {
		if (arr[1] < 0) {
			final long multiply = Math.abs(arr[1]) / 60 + 1;
			arr[0] -= multiply;
			arr[1] += multiply * 60;
		}

		if (arr[0] < 0) {
			arr[0] += (Math.abs(arr[0]) / 24 + 1) * 24;
		}

		return arr;
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
	 * 
	 * @param offset
	 * 		hh:mm
	 * 
	 * 		both the hours and minutes must be positive or negative
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		long[]
	 * 
	 * 		long[0] = 24 hours
	 * 		long[1] = mins
	 * @author TelevisionNinja
	 */
	public static long[] get24HrTimeArr(final String offset) {
		final long[] offsetArr = timeStr24HrToLongArr(offset);

		final long totalMinutes = System.currentTimeMillis() / 1000 / 60 + offsetArr[1],
				totalHours = totalMinutes / 60 + offsetArr[0];

		return new long[] {totalHours % 24, totalMinutes % 60};
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
	 * does not work for negative values in the array
	 * 
	 * @param arr
	 * @param timeOrValue
	 * 		set to true if the array contains time
	 * 		set to false if the array contains values
	 * @return
	 * @author TelevisionNinja
	 */
	public static String longArrTo24HrTimeStr(final long[] arr, final boolean timeOrValue) {
		String time = arr[0] + ":";

		if (arr[1] < 10) {
			time += "0" + arr[1];
		}
		else {
			time += arr[1];
		}

		if (timeOrValue && arr[0] < 10) {
			time = "0" + time;
		}

		return time;
	}

	/**
	 * does not work for negative values
	 * 
	 * @param hours
	 * @param mins
	 * @return
	 * @author TelevisionNinja
	 */
	public static String longsTo24HrTimeStr(final long hours, final long mins, final boolean timeOrValue) {
		String time = hours + ":";

		if (mins < 10) {
			time += "0" + mins;
		}
		else {
			time += mins;
		}

		if (timeOrValue && hours < 10) {
			time = "0" + time;
		}

		return time;
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
	 * does not work for negative values in the array
	 * 
	 * @param arr
	 * @param timeOrValue
	 * 		set to true if the array contains time
	 * 		set to false if the array contains values
	 * @return
	 * @author TelevisionNinja
	 */
	public static String strArrToTimeStr_1(final String[] arr, final boolean timeOrValue) {
		String time = ":" + StringUtils.addLeadingToString_2(arr[1], '0', 2);

		if (timeOrValue) {
			time = StringUtils.addLeadingToString_2(arr[0], '0', 2) + time;
		}
		else {
			time = arr[0] + time;
		}

		if (arr.length < 3) {
			return time;
		}

		return time + " " + arr[2];
	}

	/**
	 * does not work for negative values in the array
	 * 
	 * @param arr
	 * @param timeOrValue
	 * 		set to true if the array contains time
	 * 		set to false if the array contains values
	 * @return
	 * @author TelevisionNinja
	 */
	public static String strArrToTimeStr_2(final String[] arr, final boolean timeOrValue) {
		String time = arr[0] + ":" + StringUtils.addLeadingToString_2(arr[1], '0', 2);

		if (timeOrValue && arr[0].length() < 2) {
			time = "0" + time;
		}

		if (arr.length < 3) {
			return time;
		}

		return time + " " + arr[2];
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
		String sign = "";
		final String[] s = sub.split(":"),
				e = time.split(":");
		long[] sl = new long[s.length],
				el = new long[s.length];
		for (int x = 0; x < s.length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		/*
		 * indices
		 * 0 1 2 3 4
		 * y d h m s
		 */

		if (el[0] < sl[0]) {
			final long[] temp = el;
			el = sl;
			sl = temp;
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

		return sign +
				years +
				":" +
				days +
				":" +
				StringUtils.addLeadingToString_1(Long.toString(hours), '0', 2) +
				":" +
				StringUtils.addLeadingToString_1(Long.toString(mins), '0', 2) +
				":" +
				StringUtils.addLeadingToString_1(Long.toString(sec), '0', 2);
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
		String sign = "";
		final String[] s = sub.split(":"),
				e = time.split(":");
		long[] sl = new long[s.length],
				el = new long[s.length];
		for (int x = 0; x < s.length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		/*
		 * indices
		 * 0 1 2 3 4
		 * y d h m s
		 */

		if (el[0] < sl[0]) {
			final long[] temp = el;
			el = sl;
			sl = temp;
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

		return sign +
				years +
				":" +
				days +
				":" +
				StringUtils.addLeadingToString_1(Long.toString(hours), '0', 2) +
				":" +
				StringUtils.addLeadingToString_1(Long.toString(mins), '0', 2) +
				":" +
				StringUtils.addLeadingToString_1(Long.toString(sec), '0', 2);
	}

	/**
	 * this will throw an exception if the time string is not correctly formatted
	 * 
	 * @param time
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] timeStr24HrToLongArr(final String time) {
		final String[] str = time.split(":");

		return new long[]{Long.parseLong(str[0]), Long.parseLong(str[1])};
	}

	/**
	 * accepts 12 and 24 hr time
	 * 
	 * @param time
	 * @return
	 * @author TelevisionNinja
	 */
	public static String[] timeStrToStrArr(final String time) {
		final String[] valuesAndHalf = time.split(" "),
				valuesString = valuesAndHalf[0].split(":"),
				finalArr;

		if (valuesAndHalf.length > 1) {
			finalArr = new String[]{valuesString[0], valuesString[1], valuesAndHalf[1]};
		}
		else {
			finalArr = new String[]{valuesString[0], valuesString[1]};
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
	 * @param t12
	 * 		hh:mm am/pm
	 * @param colon
	 * @return
	 * @author TelevisionNinja
	 */
	public static String twelveHrToTwentyFourHr_5(final String t12, final boolean colon) {
		final String[] valuesAndHalf = t12.split(" "),
				values = valuesAndHalf[0].split(":");

		long longHours = Long.parseLong(values[0]);

		longHours %= 12;

		if (valuesAndHalf[1].toLowerCase().contains("p")) {
			longHours += 12;
		}

		values[0] = String.valueOf(longHours);

		if (longHours < 10) {
			values[0] = "0" + values[0];
		}

		if (colon) {
			return values[0] + ":" + values[1];
		}
		else {
			return values[0] + values[1];
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
			return hr + t24.substring(2) + " " + end;
		}
		else {
			return hr + ":" + t24.substring(2) + " " + end;
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
		long hr = Long.parseLong(t24.substring(0, 2));

		if (hr >= 12) {
			hr %= 12;
			end = "pm";
		}

		if (hr == 0) {
			hr = 12;
		}

		if (colon) {
			return hr + t24.substring(2) + " " + end;
		}
		else {
			return hr + ":" + t24.substring(2) + " " + end;
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
	public static String twentyFourHrToTwelveHr_5(final String t24) {
		String end = "am";

		int index = 2;

		if (t24.contains(":")) {
			index = 3;
		}

		long hr = Long.parseLong(t24.substring(0, 2));

		if (hr >= 12) {
			hr %= 12;
			end = "pm";
		}

		if (hr == 0) {
			hr = 12;
		}

		if (index == 3) {
			return hr + t24.substring(index) + " " + end;
		}
		else {
			return hr + ":" + t24.substring(index) + " " + end;
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