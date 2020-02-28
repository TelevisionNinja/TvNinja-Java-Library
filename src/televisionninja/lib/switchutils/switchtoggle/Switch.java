package televisionninja.lib.switchutils.switchtoggle;

/**
 * @author TelevisionNinja
 *
 */
public class Switch {
	private boolean state = false;

	private final SwitchActions actions;

	/**
	 * 
	 * @param initialState
	 * @author TelevisionNinja
	 */
	public Switch(final boolean initialState, final SwitchActions actions) {
		this.state = initialState;
		this.actions = actions;
	}

	/**
	 * @author TelevisionNinja
	 */
	public Switch(final SwitchActions actions) {
		this.actions = actions;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public boolean getState() {
		return this.state;
	}

	/**
	 * override this
	 */
	public void offAction() {
		this.actions.offAction();
	}

	/**
	 * override this
	 */
	public void onAction() {
		this.actions.onAction();
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	public void resetState() {
		this.state = false;
	}

	/**
	 * 
	 * @param state
	 * @author TelevisionNinja
	 */
	public void setState(final boolean state) {
		this.state = state;
	}

	/**
	 * @author TelevisionNinja
	 */
	public void toggle() {
		if (this.state) {
			offAction();
		}
		else {
			onAction();
		}
		this.state = !this.state;
	}
}