package televisionninja.lib.switchutils.confirmationbutton;

/**
 * @author TelevisionNinja
 *
 */
public class ConfirmationButton {
	private boolean toggleConfirm = true;

	private final ConfirmActions actions;

	/**
	 * @author TelevisionNinja
	 */
	public ConfirmationButton(final ConfirmActions actions) {
		this.actions = actions;
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	public void action() {
		this.actions.action();
	}

	/**
	 * override this
	 */
	public void confirm() {
		this.actions.confirm();
	}

	/**
	 * @author TelevisionNinja
	 */
	public void press() {
		if (this.toggleConfirm) {
			confirm();
		}
		else {
			action();
		}
		this.toggleConfirm = !this.toggleConfirm;
	}

	/**
	 * @author TelevisionNinja
	 */
	public void reset() {
		this.toggleConfirm = true;
		resetAction();
	}

	/**
	 * override this
	 */
	public void resetAction() {
		this.actions.resetAction();
	}
}