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
		return TimeUtils.cleanUp24HrClockTime_2(tHours + aHours, tMins + aMins);
	}

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
	public static long[] addClockTime(final long[] tArr, final long[] aArr) {
		return TimeUtils.cleanUp24HrClockTime_2(tArr[0] + aArr[0], tArr[1] + aArr[1]);
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
		return TimeUtils.longArrTo24HrTimeStr(addClockTime(TimeUtils.timeStr24HrToLongArr(time), TimeUtils.timeStr24HrToLongArr(addedTime)), true);
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
		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(TimeUtils.timeStr24HrToLongArr(end), TimeUtils.timeStr24HrToLongArr(start)), false);
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
		final long[] subtractedTime = subtractClockTime(TimeUtils.timeStr24HrToLongArr(end), TimeUtils.timeStr24HrToLongArr(start));

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
		return TimeUtils.cleanUp24HrClockTime_2(tHours - sHours, tMins - sMins);
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
	public static long[] subtractClockTime(final long[] tArr, final long[] sArr) {
		return TimeUtils.cleanUp24HrClockTime_2(tArr[0] - sArr[0], tArr[1] - sArr[1]);
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
		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(TimeUtils.timeStr24HrToLongArr(time), TimeUtils.timeStr24HrToLongArr(subtractedTime)), true);
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
		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(TimeUtils.get24HrTimeArr(offset), TimeUtils.timeStr24HrToLongArr(subtractedTime)), true);
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
		return TimeUtils.longArrTo24HrTimeStr(addClockTime(TimeUtils.get24HrTimeArr(offset), TimeUtils.timeStr24HrToLongArr(addedTime)), true);
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
		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(TimeUtils.get24HrTimeArr(offset), TimeUtils.timeStr24HrToLongArr(start)), false);
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
		return TimeUtils.longArrTo24HrTimeStr(subtractClockTime(TimeUtils.timeStr24HrToLongArr(end), TimeUtils.get24HrTimeArr(offset)), false);
	}
}