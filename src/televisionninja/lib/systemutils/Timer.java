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

	private boolean isRunning = false;

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
	public Timer(final long startingValue) {
		this.startValue = startingValue;
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
	public long getTime() {
		if (this.isRunning) {
			return System.nanoTime() - this.initialTime + this.startValue;
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
		this.isRunning = false;
	}

	/**
	 * set timer by nanoseconds
	 * 
	 * @param value
	 * 		nanoseconds
	 * @author TelevisionNinja
	 */
	public void setStartValue(final long value) {
		this.startValue = value;
	}

	/**
	 * System.nanoTime() method
	 * @author TelevisionNinja
	 */
	public void start() {
		this.initialTime = System.nanoTime();
		this.isRunning = true;
		this.elapsedTime = this.startValue;
	}

	/**
	 * System.nanoTime() method
	 * @author TelevisionNinja
	 */
	public void stop() {
		if (this.isRunning) {
			this.elapsedTime = System.nanoTime() - this.initialTime + this.startValue;
			this.isRunning = false;
		}
	}
}