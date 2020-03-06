package televisionninja.lib.systemutils;

/**
 * @author TelevisionNinja
 *
 */
public class Timer {
	/**
	 * @author TelevisionNinja
	 */
	private long initialTime = 0,
			elapsedTime = 0,
			startValue = 0;

	/**
	 * @author TelevisionNinja
	 */
	public Timer() {

	}

	/**
	 * 
	 * @param initialTime
	 * @author TelevisionNinja
	 */
	public Timer(final long initialTime) {
		this.startValue = initialTime;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public long getStartValue() {
		return this.startValue;
	}

	/**
	 * 
	 * @return
	 * 		nanoseconds
	 * @author TelevisionNinja
	 */
	public long getTime(final boolean check) {
		if (check) {
			this.elapsedTime = System.nanoTime() - this.initialTime + this.startValue;
		}
		return this.elapsedTime;
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	public void reset() {
		this.elapsedTime = this.startValue;
	}

	/**
	 * set timer by nanoseconds
	 * 
	 * @param amount
	 * 		nanoseconds
	 * @author TelevisionNinja
	 */
	public void setStartValue(final long amount) {
		this.startValue = amount;
	}

	/**
	 * System.nanoTime() method
	 * @author TelevisionNinja
	 */
	public void start() {
		this.initialTime = System.nanoTime();
	}

	/**
	 * System.nanoTime() method
	 * @author TelevisionNinja
	 */
	public void stop() {
		this.elapsedTime = System.nanoTime() - this.initialTime + this.startValue;
	}
}