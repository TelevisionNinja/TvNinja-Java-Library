package televisionninja.lib.switchutils.pushbutton;

/**
 * @author TelevisionNinja
 *
 */
public class PushButton {
	private final ButtonAction action;

	/**
	 * @author TelevisionNinja
	 */
	public PushButton(final ButtonAction action) {
		this.action = action;
	}

	/**
	 * override this
	 */
	public void action() {
		this.action.action();
	}

	/**
	 * @author TelevisionNinja
	 */
	public void press() {
		action();
	}
}