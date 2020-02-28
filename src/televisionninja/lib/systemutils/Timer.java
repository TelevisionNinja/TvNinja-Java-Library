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
			timeStartValue = 0;

	/**
	 * @author TelevisionNinja
	 */
	public Timer() {

	}

	/**
	 * 
	 * @return
	 * 		nanoseconds
	 * @author TelevisionNinja
	 */
	public long getTime(boolean check) {
		if (check) {
			this.elapsedTime = System.nanoTime() - this.initialTime + this.timeStartValue;
		}
		return this.elapsedTime;
	}

	public void reset() {
		this.initialTime = this.timeStartValue;
	}

	/**
	 * set timer by nanoseconds
	 * 
	 * @param amount
	 * 		nanoseconds
	 * @author TelevisionNinja
	 */
	public void setTime(final long amount) {
		this.timeStartValue = amount;
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
		this.elapsedTime = System.nanoTime() - this.initialTime + this.timeStartValue;
	}
}