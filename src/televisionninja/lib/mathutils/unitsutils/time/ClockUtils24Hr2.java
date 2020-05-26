/**
 * 
 */
package televisionninja.lib.mathutils.unitsutils.time;

/**
 * @author TelevisionNinja
 *
 */
public class ClockUtils24Hr2 {
	/**
	 * works with any time addition
	 * 
	 * @param tHours
	 * @param tMins
	 * @param aHours
	 * @param aMins
	 * @return
	 * @author TelevisionNinja
	 */
	public static long[] addClockTime(final long tHours, final long tMins, final long aHours, final long aMins) {
		return TimeUtils.cleanUpClockTime(tHours + aHours, tMins + aMins);
	}

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
		final long[] tl = TimeUtils.timeStr24HrToLongArr(time),
				al = TimeUtils.timeStr24HrToLongArr(addedTime);

		return TimeUtils.longArrTo24HrTimeStr(addClockTime(tl[0], tl[1], al[0], al[1]), true);
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
	public static String elapsedTime(final String start, final String end) {
		final long[] sl = TimeUtils.timeStr24HrToLongArr(start),
				el = TimeUtils.timeStr24HrToLongArr(end);

		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(el[0], el[1], sl[0], sl[1]), false);
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
	public static long elapsedTimeMins(final String start, final String end) {
		final long[] sl = TimeUtils.timeStr24HrToLongArr(start),
				el = TimeUtils.timeStr24HrToLongArr(end);

		final long[] subtractedTime = subtractClockTime(el[0], el[1], sl[0], sl[1]);

		return subtractedTime[0] * 60 + subtractedTime[1];
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
	public static long[] subtractClockTime(final long tHours, final long tMins, final long sHours, final long sMins) {
		return TimeUtils.cleanUpClockTime(tHours - sHours, tMins - sMins);
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
		final long[] tl = TimeUtils.timeStr24HrToLongArr(time),
				sl = TimeUtils.timeStr24HrToLongArr(subtractedTime);

		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(tl[0], tl[1], sl[0], sl[1]), true);
	}

	/**
	 * works with any time subtraction
	 * 
	 * @param subtractedTime
	 * 		hh:mm
	 * @param offset
	 * 		hh:mm
	 * 		both the hours and minutes must be positive or negative
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		hh:mm
	 * 		what time it was from the given time
	 * @author TelevisionNinja
	 */
	public static String timeAgo(final String subtractedTime, final String offset) {
		final long[] sl = TimeUtils.timeStr24HrToLongArr(subtractedTime),
				currentTime = TimeUtils.get24HrTimeArr(offset);

		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(currentTime[0], currentTime[1], sl[0], sl[1]), true);
	}

	/**
	 * works with any time addition
	 * 
	 * @param addedTime
	 * 		hh:mm
	 * @param offset
	 * 		hh:mm
	 * 		both the hours and minutes must be positive or negative
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		hh:mm
	 * 		what time it'll be from now using the given time
	 * @author TelevisionNinja
	 */
	public static String timeFromNow(final String addedTime, final String offset) {
		final long[] al = TimeUtils.timeStr24HrToLongArr(addedTime),
				currentTime = TimeUtils.get24HrTimeArr(offset);

		return TimeUtils.longArrTo24HrTimeStr(addClockTime(currentTime[0], currentTime[1], al[0], al[1]), true);
	}

	/**
	 * no negative answers
	 * 
	 * @param start
	 * 		hh:mm
	 * @param offset
	 * 		hh:mm
	 * 		both the hours and minutes must be positive or negative
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		hh:mm
	 * 		how much time since the given time
	 * @author TelevisionNinja
	 */
	public static String timeSince(final String start, final String offset) {
		final long[] sl = TimeUtils.timeStr24HrToLongArr(start),
				currentTime = TimeUtils.get24HrTimeArr(offset);

		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(currentTime[0], currentTime[1], sl[0], sl[1]), false);
	}

	/**
	 * no negative answers
	 * 
	 * @param end
	 * 		hh:mm
	 * @param offset
	 * 		hh:mm
	 * 		both the hours and minutes must be positive or negative
	 * 		negative if your time zone is behind UTC
	 * 		positive if your time zone is ahead of UTC
	 * @return
	 * 		hh:mm
	 * 		how much time until the given time
	 * @author TelevisionNinja
	 */
	public static String timeUntil(final String end, final String offset) {
		final long[] el = TimeUtils.timeStr24HrToLongArr(end),
				currentTime = TimeUtils.get24HrTimeArr(offset);

		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(el[0], el[1], currentTime[0], currentTime[1]), false);
	}
}