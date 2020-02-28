/**
 * 
 */
package televisionninja.lib.systemutils;

/**
 * @author TelevisionNinja
 *
 */
public class Time {
	private String timeZone = "UTC";
	private final String separator = ":",
			zero = "0";
	private final long sixty = 60,
			ten = 10,
			thousand = 1000;

	/**
	 * 
	 */
	public Time() {

	}

	/**
	 * 
	 * @param timeZone
	 */
	public Time(final String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public String getCurrentTime_1() {
		final String separator = ":",
				zero = "0";
		final long sixty = 60,
				ten = 10,
				thousand = 1000;
		final long totalMilliseconds = System.currentTimeMillis(),
				currentMillisecond = totalMilliseconds % thousand,
				totalSeconds = totalMilliseconds / thousand,
				currentSecond = totalSeconds % sixty,
				totalMinutes = totalSeconds / sixty,
				currentMinute = totalMinutes % sixty,
				totalHours = totalMinutes / sixty,
				currentHour = totalHours % 24;

		String min = Long.toString(currentMinute),
				sec = Long.toString(currentSecond),
				mill = Long.toString(currentMillisecond);

		if (currentMinute < ten) {
			min = zero + min;
		}

		if (currentSecond < ten) {
			sec = zero + sec;
		}

		if (currentMillisecond < 100) {
			if (currentMillisecond < ten) {
				mill = zero + zero + mill;
			}
			else {
				mill = zero + mill;
			}
		}
		return currentHour + separator + min + separator + sec + "." + mill + " " + this.timeZone;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public String getCurrentTime_2() {
		final long totalMilliseconds = System.currentTimeMillis(),
				currentMillisecond = totalMilliseconds % this.thousand,
				totalSeconds = totalMilliseconds / this.thousand,
				currentSecond = totalSeconds % this.sixty,
				totalMinutes = totalSeconds / this.sixty,
				currentMinute = totalMinutes % this.sixty,
				totalHours = totalMinutes / this.sixty,
				currentHour = totalHours % 24;

		String min = Long.toString(currentMinute),
				sec = Long.toString(currentSecond),
				mill = Long.toString(currentMillisecond);

		if (currentMinute < this.ten) {
			min = this.zero + currentMinute;
		}

		if (currentSecond < this.ten) {
			sec = this.zero + currentSecond;
		}

		if (currentMillisecond < 100) {
			if (currentMillisecond < this.ten) {
				mill = this.zero + this.zero + currentMillisecond;
			}
			else {
				mill = this.zero + currentMillisecond;
			}
		}
		return currentHour + this.separator + min + this.separator + sec + "." + mill + " " + this.timeZone;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public String getCurrentTimeHrsAndMins() {
		final long totalMilliseconds = System.currentTimeMillis(),
				totalSeconds = totalMilliseconds / this.thousand,
				totalMinutes = totalSeconds / this.sixty,
				currentMinute = totalMinutes % this.sixty,
				totalHours = totalMinutes / this.sixty,
				currentHour = totalHours % 24;

		String min = Long.toString(currentMinute);

		if (currentMinute < this.ten) {
			min = this.zero + currentMinute;
		}
		return currentHour + this.separator + min + " " + this.timeZone;
	}
}