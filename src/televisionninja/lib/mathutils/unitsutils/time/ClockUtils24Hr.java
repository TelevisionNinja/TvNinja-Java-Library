/**
 * 
 */
package televisionninja.lib.mathutils.unitsutils.time;

import televisionninja.lib.stringutils.StringUtils;

/**
 * @author TelevisionNinja
 *
 */
public class ClockUtils24Hr {
	/**
	 * works with any time addition
	 * 
	 * @param time
	 * 		hh:mm
	 * @param addedTime
	 * 		hh:mm
	 * @return
	 * 		hh:mm
	 * @author TelevisionNinja
	 */
	public static String addClockTime(final String time, final String addedTime) {
		final String separator = ":";
		final String[] t = time.split(separator),
				a = addedTime.split(separator);
		final int length = t.length;
		final long[] tl = new long[length],
				al = new long[length];
		for (int x = 0; x < length; x++) {
			tl[x] = Long.parseLong(t[x]);
			al[x] = Long.parseLong(a[x]);
		}

		if (al[1] >= 60) {
			al[0] += al[1] / 60;
			al[1] %= 60;
		}

		al[0] %= 24;

		final long add = 60 * (tl[0] + al[0]) + tl[1] + al[1],
				mins = add % 60;
		long hours = add / 60;

		if (hours >= 24) {
			hours %= 24;
		}

		return StringUtils.addLeadingToString_2(Long.toString(hours), '0', 2) + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2);
	}

	/**
	 * negative answers
	 * 
	 * @param start
	 * 		hh:mm
	 * @param end
	 * 		hh:mm
	 * @return
	 * 		hh:mm elapsed
	 * @author TelevisionNinja
	 */
	public static String elapsedTime_1(final String start, final String end) {
		final String separator = ":";
		final String[] s = start.split(separator),
				e = end.split(separator);
		final int length = s.length;
		final long[] sl = new long[length],
				el = new long[length];
		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		final long eHrs = el[0],
				sHrs = sl[0],
				sMins = sl[1];

		long eMins = el[1],
				hours = eHrs - sHrs;

		if (sHrs > eHrs) {
			hours += 24;
		}

		if (sMins > eMins) {
			final long multiply = sMins / 60 + 1;
			hours -= multiply;
			eMins += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}
		final long mins = eMins - sMins;

		return hours + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2);
	}

	/**
	 * no negative answers
	 * 
	 * @param start
	 * 		hh:mm
	 * @param end
	 * 		hh:mm
	 * @return
	 * 		hh:mm elapsed
	 * @author TelevisionNinja
	 */
	public static String elapsedTime_2(final String start, final String end) {
		final String separator = ":";
		final String[] s = start.split(separator),
				e = end.split(separator);
		final int length = s.length;
		final long[] sl = new long[length],
				el = new long[length];
		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		long hours = el[0] - sl[0];

		if (sl[0] > el[0]) {
			hours += 24;
		}

		if (sl[1] > el[1]) {
			final long multiply = sl[1] / 60 + 1;
			hours -= multiply;
			el[1] += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += 24;
		}

		final long mins = el[1] - sl[1];

		return hours + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2);
	}

	/**
	 * no negative answers
	 * 
	 * @param start
	 * 		hh:mm
	 * @param end
	 * 		hh:mm
	 * @return
	 * 		mins elapsed
	 * @author TelevisionNinja
	 */
	public static long elapsedTimeMins(final String start, final String end) {
		final String separator = ":";
		final String[] s = start.split(separator),
				e = end.split(separator);
		final int length = s.length;
		final long[] sl = new long[length],
				el = new long[length];
		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		long hours = el[0] - sl[0];

		if (sl[0] > el[0]) {
			hours += 24;
		}

		if (sl[1] > el[1]) {
			final long multiply = sl[1] / 60 + 1;
			hours -= multiply;
			el[1] += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += 24;
		}

		return el[1] - sl[1] + hours * 60;
	}

	/**
	 * works with any time subtraction
	 * 
	 * @param time
	 * 		hh:mm
	 * @param subtractedTime
	 * 		hh:mm
	 * @return
	 * 		hh:mm
	 * @author TelevisionNinja
	 */
	public static String subtractClockTime(final String time, final String subtractedTime) {
		final String separator = ":";
		final String[] t = time.split(separator),
				s = subtractedTime.split(separator);
		final int length = t.length;
		final long[] tl = new long[length],
				sl = new long[length];
		for (int x = 0; x < length; x++) {
			tl[x] = Long.parseLong(t[x]);
			sl[x] = Long.parseLong(s[x]);
		}

		if (sl[1] >= 60) {
			sl[0] += sl[1] / 60;
			sl[1] %= 60;
		}

		sl[0] %= 24;

		final long sMins = sl[0] * 60 + sl[1];

		long tMins = tl[0] * 60 + tl[1];

		if (sMins > tMins) {
			tMins += sMins - (sMins % 1440) + 1440; //24 hrs in mins
		}

		final long sub = tMins - sMins,
				mins = sub % 60,
				hours = sub / 60;

		return StringUtils.addLeadingToString_2(Long.toString(hours), '0', 2) + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2);
	}

	/**
	 * works with any time subtraction
	 * 
	 * @param subtractedTime
	 * 		hh:mm
	 * @param offset
	 * 		hh:mm
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		hh:mm
	 * 		what time it was from the given time
	 * @author TelevisionNinja
	 */
	public static String timeAgo(final String subtractedTime, final String offset) {
		final String separator = ":";
		final String[] s = subtractedTime.split(separator),
				offsetArr = offset.split(separator);

		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / 60 + Long.parseLong(offsetArr[1]),
				m = totalMinutes % 60,
				totalHours = totalMinutes / 60 + Long.parseLong(offsetArr[0]),
				t = totalHours % 24;

		final int length = s.length;

		final long[] sl = new long[length];

		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
		}

		if (sl[1] >= 60) {
			sl[0] += sl[1] / 60;
			sl[1] %= 60;
		}

		sl[0] %= 24;

		final long sMins = sl[0] * 60 + sl[1];

		long tMins = t * 60 + m;

		if (sMins > tMins) {
			tMins += sMins - (sMins % 1440) + 1440; //24 hrs in mins
		}

		final long sub = tMins - sMins,
				mins = sub % 60,
				hours = sub / 60;

		return StringUtils.addLeadingToString_2(Long.toString(hours), '0', 2) + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2);
	}

	/**
	 * works with any time addition
	 * 
	 * @param addedTime
	 * 		hh:mm
	 * @param offset
	 * 		hh:mm
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		hh:mm
	 * 		what time it'll be from the given time
	 * @author TelevisionNinja
	 */
	public static String timeFromNow(final String addedTime, final String offset) {
		final String separator = ":";
		final String[] a = addedTime.split(separator),
				offsetArr = offset.split(separator);

		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / 60 + Long.parseLong(offsetArr[1]),
				m = totalMinutes % 60,
				totalHours = totalMinutes / 60 + Long.parseLong(offsetArr[0]),
				t = totalHours % 24;

		final int length = a.length;

		final long[] al = new long[length];

		for (int x = 0; x < length; x++) {
			al[x] = Long.parseLong(a[x]);
		}

		if (al[1] >= 60) {
			al[0] += al[1] / 60;
			al[1] %= 60;
		}

		al[0] %= 24;

		final long add = 60 * (t + al[0]) + m + al[1],
				mins = add % 60;
		long hours = add / 60;

		if (hours >= 24) {
			hours %= 24;
		}

		return StringUtils.addLeadingToString_2(Long.toString(hours), '0', 2) + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2);
	}

	/**
	 * no negative answers
	 * 
	 * @param start
	 * 		hh:mm
	 * @param offset
	 * 		hh:mm
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		hh:mm
	 * 		how much time since the given time
	 * @author TelevisionNinja
	 */
	public static String timeSince(final String start, final String offset) {
		final String separator = ":";

		final String[] s = start.split(separator),
				offsetArr = offset.split(separator);

		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / 60 + Long.parseLong(offsetArr[1]),
				totalHours = totalMinutes / 60 + Long.parseLong(offsetArr[0]),
				t = totalHours % 24;

		final int length = s.length;

		final long[] sl = new long[length];

		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
		}

		long m = totalMinutes % 60,
				hours = t - sl[0];

		if (sl[1] > m) {
			final long multiply = sl[1] / 60 + 1;
			hours -= multiply;
			m += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += 24;
		}

		final long mins = m - sl[1];

		return hours + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2);
	}

	/**
	 * no negative answers
	 * 
	 * @param end
	 * 		hh:mm
	 * @param offset
	 * 		hh:mm
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		hh:mm
	 * 		how much time until the given time
	 * @author TelevisionNinja
	 */
	public static String timeUntil(final String end, final String offset) {
		final String separator = ":";

		final String[] e = end.split(separator),
				offsetArr = offset.split(separator);

		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / 60 + Long.parseLong(offsetArr[1]),
				m = totalMinutes % 60,
				totalHours = totalMinutes / 60 + Long.parseLong(offsetArr[0]),
				t = totalHours % 24;

		final int length = e.length;

		final long[] el = new long[length];

		for (int x = 0; x < length; x++) {
			el[x] = Long.parseLong(e[x]);
		}

		long hours = el[0] - t;

		if (m > el[1]) {
			final long multiply = m / 60 + 1;
			hours -= multiply;
			el[1] += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += 24;
		}

		final long mins = el[1] - m;

		return hours + separator + StringUtils.addLeadingToString_2(Long.toString(mins), '0', 2);
	}
}