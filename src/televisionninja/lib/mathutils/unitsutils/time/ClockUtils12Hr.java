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

		final long sixtyMins = 60,
				twelveHrs = 12;

		if (tl[0] == twelveHrs) {
			tl[0] = 0;
		}

		if (al[1] >= sixtyMins) {
			al[0] += al[1] / sixtyMins;
			al[1] %= sixtyMins;
		}

		al[0] %= 24;

		final long add = sixtyMins * (tl[0] + al[0]) + tl[1] + al[1],
				mins = add % sixtyMins;
		long hours = add / sixtyMins;

		String half = tBoolean[1].toLowerCase();

		if (hours >= twelveHrs) {
			if ((hours / twelveHrs) % 2 != 0) {
				half = TimeUtils.amPmSwitcher(half);
			}
			hours %= twelveHrs;
		}

		if (hours == 0) {
			hours = twelveHrs;
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
		final long sMins = sl[1],
				sixty = 60,
				twelve = 12;

		if (sHrs == twelve) {
			sHrs = 0;
		}
		if (eHrs == twelve) {
			eHrs = 0;
		}

		hours = eHrs - sHrs;

		if (!(sBoolean[1].equalsIgnoreCase(eBoolean[1]))) {
			hours += twelve;
		}

		if (sMins > eMins) {
			final long multiply = sMins / sixty + 1;
			hours -= multiply;
			eMins += sixty * multiply;//eMins += sMins / 60 * 60 + 60
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

		final long sixty = 60,
				twelve = 12;

		if (sl[0] == twelve) {
			sl[0] = 0;
		}

		if (el[0] == twelve) {
			el[0] = 0;
		}

		long hours = el[0] - sl[0];

		if (!(sBoolean[1].equalsIgnoreCase(eBoolean[1]))) {
			hours += twelve;
		}

		if (sl[1] > el[1]) {
			final long multiply = sl[1] / sixty + 1;
			hours -= multiply;
			el[1] += sixty * multiply;//eMins += sMins / 60 * 60 + 60
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

		final long sixty = 60,
				twelve = 12;

		if (sl[0] == twelve) {
			sl[0] = 0;
		}

		if (el[0] == twelve) {
			el[0] = 0;
		}

		long hours = el[0] - sl[0];

		if (!(sBoolean[1].equalsIgnoreCase(eBoolean[1]))) {
			hours += twelve;
		}

		if (sl[1] > el[1]) {
			final long multiply = sl[1] / sixty + 1;
			hours -= multiply;
			el[1] += sixty * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += 24;
		}

		return el[1] - sl[1] + hours * sixty;
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

		final long sixtyMins = 60,
				twelveHrs = 12,
				hrsInMins = 720;

		if (tl[0] == twelveHrs) {
			tl[0] = 0;
		}

		if (sl[1] >= sixtyMins) {
			sl[0] += sl[1] / sixtyMins;
			sl[1] %= sixtyMins;
		}

		sl[0] %= 24;

		final long sMins = sl[0] * sixtyMins + sl[1];

		long tMins = tl[0] * sixtyMins + tl[1];

		String half = tBoolean[1].toLowerCase();

		if (sl[0] >= twelveHrs) {
			half = TimeUtils.amPmSwitcher(half);
		}

		if (sMins > tMins) {
			tMins += sMins - (sMins % hrsInMins) + hrsInMins;
			half = TimeUtils.amPmSwitcher(half);
		}

		final long sub = tMins - sMins,
				mins = sub % sixtyMins;

		long hours = sub / sixtyMins;

		if (hours >= twelveHrs) {
			half = TimeUtils.amPmSwitcher(half);
			hours %= twelveHrs;
		}

		if (hours == 0) {
			hours = twelveHrs;
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
				sixtyMins = 60,
				twelveHrs = 12,
				twentyFour = 24,
				hrsInMins = 720,
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / sixtyMins + Long.parseLong(offsetArr[1]),
				m = totalMinutes % sixtyMins,
				totalHours = totalMinutes / sixtyMins + Long.parseLong(offsetArr[0]);

		final int length = s.length;

		final long[] sl = new long[length];

		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
		}

		long t = totalHours % twentyFour;

		String half = "";
		if (t >= twelveHrs) {
			half = "pm";
		}
		else {
			half = "am";
		}

		t %= twelveHrs;

		if (t == twelveHrs) {
			t = 0;
		}

		if (sl[1] >= sixtyMins) {
			sl[0] += sl[1] / sixtyMins;
			sl[1] %= sixtyMins;
		}

		sl[0] %= twentyFour;

		final long sMins = sl[0] * sixtyMins + sl[1];

		long tMins = t * sixtyMins + m;

		if (sl[0] >= twelveHrs) {
			half = TimeUtils.amPmSwitcher(half);
		}

		if (sMins > tMins) {
			tMins += sMins - (sMins % hrsInMins) + hrsInMins;
			half = TimeUtils.amPmSwitcher(half);
		}

		final long sub = tMins - sMins,
				mins = sub % sixtyMins;

		long hours = sub / sixtyMins;

		if (hours >= twelveHrs) {
			half = TimeUtils.amPmSwitcher(half);
			hours %= twelveHrs;
		}

		if (hours == 0) {
			hours = twelveHrs;
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
				sixtyMins = 60,
				twelveHrs = 12,
				twentyFour = 24,
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / sixtyMins + Long.parseLong(offsetArr[1]),
				m = totalMinutes % sixtyMins,
				totalHours = totalMinutes / sixtyMins + Long.parseLong(offsetArr[0]);

		final int length = a.length;

		final long[] al = new long[length];

		for (int x = 0; x < length; x++) {
			al[x] = Long.parseLong(a[x]);
		}

		long t = totalHours % twentyFour;

		String half = "";
		if (t >= twelveHrs) {
			half = "pm";
		}
		else {
			half = "am";
		}

		t %= twelveHrs;

		if (t == twelveHrs) {
			t = 0;
		}

		if (al[1] >= sixtyMins) {
			al[0] += al[1] / sixtyMins;
			al[1] %= sixtyMins;
		}

		al[0] %= twentyFour;

		final long add = sixtyMins * (t + al[0]) + m + al[1],
				mins = add % sixtyMins;
		long hours = add / sixtyMins;

		if (hours >= twelveHrs) {
			if ((hours / twelveHrs) % 2 != 0) {
				half = TimeUtils.amPmSwitcher(half);
			}
			hours %= twelveHrs;
		}

		if (hours == 0) {
			hours = twelveHrs;
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
				sixtyMins = 60,
				twelveHrs = 12,
				twentyFour = 24,
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / sixtyMins + Long.parseLong(offsetArr[1]),
				totalHours = totalMinutes / sixtyMins + Long.parseLong(offsetArr[0]);

		final int length = s.length;

		final long[] sl = new long[length];

		for (int x = 0; x < length; x++) {
			sl[x] = Long.parseLong(s[x]);
		}

		long m = totalMinutes % sixtyMins,
				hours = 0,
				t = totalHours % twentyFour;

		String half = "";
		if (t >= twelveHrs) {
			half = "pm";
		}
		else {
			half = "am";
		}

		t %= twelveHrs;

		if (sl[0] == twelveHrs) {
			sl[0] = 0;
		}

		if (t == twelveHrs) {
			t = 0;
		}

		hours = t - sl[0];

		if (!(sBoolean[1].equalsIgnoreCase(half))) {
			hours += twelveHrs;
		}

		if (sl[1] > m) {
			final long multiply = sl[1] / sixtyMins + 1;
			hours -= multiply;
			m += sixtyMins * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += twentyFour;
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
				sixtyMins = 60,
				twelveHrs = 12,
				twentyFour = 24,
				totalSeconds = totalMilliseconds / 1000,
				totalMinutes = totalSeconds / sixtyMins + Long.parseLong(offsetArr[1]),
				m = totalMinutes % sixtyMins,
				totalHours = totalMinutes / sixtyMins + Long.parseLong(offsetArr[0]);

		final int length = e.length;

		final long[] el = new long[length];

		for (int x = 0; x < length; x++) {
			el[x] = Long.parseLong(e[x]);
		}

		long hours = 0,
				t = totalHours % twentyFour;

		String half = "";
		if (t >= twelveHrs) {
			half = "pm";
		}
		else {
			half = "am";
		}

		t %= twelveHrs;

		if (t == twelveHrs) {
			t = 0;
		}

		if (el[0] == twelveHrs) {
			el[0] = 0;
		}

		hours = el[0] - t;

		if (!(half.equalsIgnoreCase(eBoolean[1]))) {
			hours += twelveHrs;
		}

		if (m > el[1]) {
			final long multiply = m / sixtyMins + 1;
			hours -= multiply;
			el[1] += sixtyMins * multiply;//eMins += sMins / 60 * 60 + 60
		}

		if (hours < 0) {
			hours += twentyFour;
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