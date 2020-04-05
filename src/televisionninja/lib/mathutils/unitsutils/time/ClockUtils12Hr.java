package televisionninja.lib.mathutils.unitsutils.time;

public class ClockUtils12Hr {
	/**
	 * works with any time addition
	 * 
	 * @param time
	 * 		hh:mm am/pm
	 * @param addedTime
	 * 		hh:mm
	 * @return
	 * 		hh:mm am/pm
	 * @author TelevisionNinja
	 */
	public static String addClockTime(final String time, final String addedTime) {
		final String separator = ":",
				space = " ";
		final String[] tBoolean = time.split(space),
				t = tBoolean[0].split(separator),
				a = addedTime.split(separator);
		final int length = t.length;
		final long[] tl = new long[length],
				al = new long[length];
		for (int x = 0; x < length; x++) {
			tl[x] = Long.parseLong(t[x]);
			al[x] = Long.parseLong(a[x]);
		}

		if (tl[0] == 12) {
			tl[0] = 0;
		}

		if (al[1] >= 60) {
			al[0] += al[1] / 60;
			al[1] %= 60;
		}

		al[0] %= 24;

		final long add = 60 * (tl[0] + al[0]) + tl[1] + al[1],
				mins = add % 60;
		long hours = add / 60;

		String half = tBoolean[1].toLowerCase();

		if (hours >= 12) {
			if ((hours / 12) % 2 != 0) {
				half = TimeUtils.amPmSwitcher(half);
			}
			hours %= 12;
		}

		if (hours == 0) {
			hours = 12;
		}
		if (mins < 10) {
			return hours + separator + "0" + mins + space + half;
		}
		else {
			return hours + separator + mins + space + half;
		}
	}

	/**
	 * negative answers
	 * 
	 * @param start
	 * 		hh:mm am/pm
	 * @param end
	 * 		hh:mm am/pm
	 * @return
	 * 		hh:mm elapsed
	 * @author TelevisionNinja
	 */
	public static String elapsedTime_1(final String start, final String end) {
		final String separator = ":",
				space = " ";
		final String[] sBoolean = start.split(space),
				eBoolean = end.split(space),
				s = sBoolean[0].split(separator),
				e = eBoolean[0].split(separator);
		final int length = s.length;
		final long[] sl = new long[length],
				el = new long[length];
		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		long eHrs = el[0],
				eMins = el[1],
				sHrs = sl[0],
				hours = 0;
		final long sMins = sl[1];

		if (sHrs == 12) {
			sHrs = 0;
		}
		if (eHrs == 12) {
			eHrs = 0;
		}

		hours = eHrs - sHrs;

		if (!(sBoolean[1].equalsIgnoreCase(eBoolean[1]))) {
			hours += 12;
		}

		if (sMins > eMins) {
			final long multiply = sMins / 60 + 1;
			hours -= multiply;
			eMins += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}
		final long mins = eMins - sMins;

		if (mins < 10) {
			return hours + separator + "0" + mins;
		}
		else {
			return hours + separator + mins;
		}
	}

	/**
	 * no negative answers
	 * 
	 * @param start
	 * 		hh:mm am/pm
	 * @param end
	 * 		hh:mm am/pm
	 * @return
	 * 		hh:mm elapsed
	 * @author TelevisionNinja
	 */
	public static String elapsedTime_2(final String start, final String end) {
		final String separator = ":",
				space = " ";

		final String[] sBoolean = start.split(space),
				eBoolean = end.split(space),
				s = sBoolean[0].split(separator),
				e = eBoolean[0].split(separator);

		final int length = s.length;

		final long[] sl = new long[length],
				el = new long[length];

		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		if (sl[0] == 12) {
			sl[0] = 0;
		}

		if (el[0] == 12) {
			el[0] = 0;
		}

		long hours = el[0] - sl[0];

		if (!(sBoolean[1].equalsIgnoreCase(eBoolean[1]))) {
			hours += 12;
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

		if (mins < 10) {
			return hours + separator + "0" + mins;
		}
		else {
			return hours + separator + mins;
		}
	}

	/**
	 * uses elapsedTime_2()
	 * 
	 * @param start
	 * 		hh:mm am/pm
	 * @param end
	 * 		hh:mm am/pm
	 * @return
	 * 		mins elapsed
	 * @author TelevisionNinja
	 */
	public static long elapsedTimeMins_1(final String start, final String end) {
		final String time = elapsedTime_2(start, end);
		final String[] timeArr = time.split(":");
		return Long.parseLong(timeArr[0]) * 60 + Long.parseLong(timeArr[1]);
	}

	/**
	 * no negative answers
	 * 
	 * @param start
	 * 		hh:mm am/pm
	 * @param end
	 * 		hh:mm am/pm
	 * @return
	 * 		mins elapsed
	 * @author TelevisionNinja
	 */
	public static long elapsedTimeMins_2(final String start, final String end) {
		final String separator = ":",
				space = " ";

		final String[] sBoolean = start.split(space),
				eBoolean = end.split(space),
				s = sBoolean[0].split(separator),
				e = eBoolean[0].split(separator);

		final int length = s.length;

		final long[] sl = new long[length],
				el = new long[length];

		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
			el[x] = Long.parseLong(e[x]);
		}

		if (sl[0] == 12) {
			sl[0] = 0;
		}

		if (el[0] == 12) {
			el[0] = 0;
		}

		long hours = el[0] - sl[0];

		if (!(sBoolean[1].equalsIgnoreCase(eBoolean[1]))) {
			hours += 12;
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
	 * 		hh:mm am/pm
	 * @param subtractedTime
	 * 		hh:mm
	 * @return
	 * 		hh:mm am/pm
	 * @author TelevisionNinja
	 */
	public static String subtractClockTime(final String time, final String subtractedTime) {
		final String separator = ":",
				space = " ";
		final String[] tBoolean = time.split(space),
				t = tBoolean[0].split(separator),
				s = subtractedTime.split(separator);
		final int length = t.length;
		final long[] tl = new long[length],
				sl = new long[length];
		for (int x = 0; x < length; x++) {
			tl[x] = Long.parseLong(t[x]);
			sl[x] = Long.parseLong(s[x]);
		}


		if (tl[0] == 12) {
			tl[0] = 0;
		}

		if (sl[1] >= 60) {
			sl[0] += sl[1] / 60;
			sl[1] %= 60;
		}

		sl[0] %= 24;

		final long sMins = sl[0] * 60 + sl[1];

		long tMins = tl[0] * 60 + tl[1];

		String half = tBoolean[1].toLowerCase();

		if (sl[0] >= 12) {
			half = TimeUtils.amPmSwitcher(half);
		}

		if (sMins > tMins) {
			tMins += sMins - (sMins % 720) + 720; //12 hours in mins
			half = TimeUtils.amPmSwitcher(half);
		}

		final long sub = tMins - sMins,
				mins = sub % 60;

		long hours = sub / 60;

		if (hours >= 12) {
			half = TimeUtils.amPmSwitcher(half);
			hours %= 12;
		}

		if (hours == 0) {
			hours = 12;
		}

		if (mins < 10) {
			return hours + separator + "0" + mins + space + half;
		}
		else {
			return hours + separator + mins + space + half;
		}
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
	 * 		hh:mm am/pm
	 * 		what time it was from the given time
	 * @author TelevisionNinja
	 */
	public static String timeAgo(final String subtractedTime, final String offset) {
		final String separator = ":",
				space = " ";
		final String[] s = subtractedTime.split(separator),
				offsetArr = offset.split(separator);

		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / 60 + Long.parseLong(offsetArr[1]),
				m = totalMinutes % 60,
				totalHours = totalMinutes / 60 + Long.parseLong(offsetArr[0]);

		final int length = s.length;

		final long[] sl = new long[length];

		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
		}

		long t = totalHours % 24;

		String half = "";
		if (t >= 12) {
			half = "pm";
		}
		else {
			half = "am";
		}

		t %= 12;

		if (t == 12) {//-----------------------------------------------------------------------------------------------
			t = 0;
		}

		if (sl[1] >= 60) {
			sl[0] += sl[1] / 60;
			sl[1] %= 60;
		}

		sl[0] %= 24;

		final long sMins = sl[0] * 60 + sl[1];

		long tMins = t * 60 + m;

		if (sl[0] >= 12) {
			half = TimeUtils.amPmSwitcher(half);
		}

		if (sMins > tMins) {
			tMins += sMins - (sMins % 720) + 720; //12 hours in mins
			half = TimeUtils.amPmSwitcher(half);
		}

		final long sub = tMins - sMins,
				mins = sub % 60;

		long hours = sub / 60;

		if (hours >= 12) {
			half = TimeUtils.amPmSwitcher(half);
			hours %= 12;
		}

		if (hours == 0) {
			hours = 12;
		}

		if (mins < 10) {
			return hours + separator + "0" + mins + space + half;
		}
		else {
			return hours + separator + mins + space + half;
		}
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
	 * 		hh:mm am/pm
	 * 		what time it'll be from the given time
	 * @author TelevisionNinja
	 */
	public static String timeFromNow(final String addedTime, final String offset) {
		final String separator = ":",
				space = " ";
		final String[] a = addedTime.split(separator),
				offsetArr = offset.split(separator);

		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / 60 + Long.parseLong(offsetArr[1]),
				m = totalMinutes % 60,
				totalHours = totalMinutes / 60 + Long.parseLong(offsetArr[0]);

		final int length = a.length;

		final long[] al = new long[length];

		for (int x = 0; x < length; x++) {
			al[x] = Long.parseLong(a[x]);
		}

		long t = totalHours % 24;

		String half = "";
		if (t >= 12) {
			half = "pm";
		}
		else {
			half = "am";
		}

		t %= 12;

		if (t == 12) {//-----------------------------------------------------------------------------------------------
			t = 0;
		}

		if (al[1] >= 60) {
			al[0] += al[1] / 60;
			al[1] %= 60;
		}

		al[0] %= 24;

		final long add = 60 * (t + al[0]) + m + al[1],
				mins = add % 60;
		long hours = add / 60;

		if (hours >= 12) {
			if ((hours / 12) % 2 != 0) {
				half = TimeUtils.amPmSwitcher(half);
			}
			hours %= 12;
		}

		if (hours == 0) {
			hours = 12;
		}
		if (mins < 10) {
			return hours + separator + "0" + mins + space + half;
		}
		else {
			return hours + separator + mins + space + half;
		}
	}

	/**
	 * no negative answers
	 * 
	 * @param start
	 * 		hh:mm am/pm
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

		final String[] sBoolean = start.split(" "),
				s = sBoolean[0].split(separator),
				offsetArr = offset.split(separator);

		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / 60 + Long.parseLong(offsetArr[1]),
				totalHours = totalMinutes / 60 + Long.parseLong(offsetArr[0]);

		final int length = s.length;

		final long[] sl = new long[length];

		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
		}

		long m = totalMinutes % 60,
				hours = 0,
				t = totalHours % 24;

		String half = "";
		if (t >= 12) {
			half = "pm";
		}
		else {
			half = "am";
		}

		t %= 12;

		if (sl[0] == 12) {
			sl[0] = 0;
		}

		if (t == 12) {//-----------------------------------------------------------------------------------------------
			t = 0;
		}

		hours = t - sl[0];

		if (!(sBoolean[1].equalsIgnoreCase(half))) {
			hours += 12;
		}

		if (sl[1] > m) {
			final long multiply = sl[1] / 60 + 1;
			hours -= multiply;
			m += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += 24;
		}

		final long mins = m - sl[1];

		if (mins < 10) {
			return hours + separator + "0" + mins;
		}
		else {
			return hours + separator + mins;
		}
	}

	/**
	 * no negative answers
	 * 
	 * @param end
	 * 		hh:mm am/pm
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

		final String[] eBoolean = end.split(" "),
				e = eBoolean[0].split(separator),
				offsetArr = offset.split(separator);

		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / 60 + Long.parseLong(offsetArr[1]),
				m = totalMinutes % 60,
				totalHours = totalMinutes / 60 + Long.parseLong(offsetArr[0]);

		final int length = e.length;

		final long[] el = new long[length];

		for (int x = 0; x < length; x++) {
			el[x] = Long.parseLong(e[x]);
		}

		long hours = 0,
				t = totalHours % 24;

		String half = "";
		if (t >= 12) {
			half = "pm";
		}
		else {
			half = "am";
		}

		t %= 12;

		if (t == 12) {//-----------------------------------------------------------------------------------------------
			t = 0;
		}

		if (el[0] == 12) {
			el[0] = 0;
		}

		hours = el[0] - t;

		if (!(half.equalsIgnoreCase(eBoolean[1]))) {
			hours += 12;
		}

		if (m > el[1]) {
			final long multiply = m / 60 + 1;
			hours -= multiply;
			el[1] += 60 * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += 24;
		}

		final long mins = el[1] - m;

		if (mins < 10) {
			return hours + separator + "0" + mins;
		}
		else {
			return hours + separator + mins;
		}
	}
}