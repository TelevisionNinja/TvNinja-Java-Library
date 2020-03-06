package televisionninja.lib.switchutils.switchtoggle;

/**
 * @author TelevisionNinja
 *
 */
public class Switch {
	private boolean state = false,
			initialState = false;

	private final SwitchActions actions;

	/**
	 * 
	 * @param initialState
	 * @author TelevisionNinja
	 */
	public Switch(final boolean initialState, final SwitchActions actions) {
		this.initialState = initialState;
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
	public boolean getInitialState() {
		return this.initialState;
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
	 * resets the state back to the initial state
	 * 
	 * @author TelevisionNinja
	 */
	public void resetState() {
		this.state = this.initialState;
	}

	/**
	 * 
	 * @param state
	 * @author TelevisionNinja
	 */
	public void setInitialState(final boolean state) {
		this.initialState = state;
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