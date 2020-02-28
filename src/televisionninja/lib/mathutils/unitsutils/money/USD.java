package televisionninja.lib.mathutils.unitsutils.money;

/**
 * @author TelevisionNinja
 *
 */
public class USD {
	/**
	 * textbook method
	 * 
	 * @param amount
	 * @return
	 * 		-long[] {hundreds, fifties, twenties, tens, fives, ones, quarters, dimes, nickels, pennies}
	 * 		-null if amount <= 0
	 * @author TelevisionNinja
	 */
	public static long[] USDToChange(final double amount) {
		if (amount <= 0) {
			return null;
		}
		long money = (long) (amount * 100);
		final long hundreds = money / 10000;
		money %= 10000;
		final long fifties = money / 5000;
		money %= 5000;
		final long twenties = money / 2000;
		money %= 2000;
		final long tens = money / 1000;
		money %= 1000;
		final long fives = money / 500;
		money %= 500;
		final long ones = money / 100;
		money %= 100;
		final long quarters = money / 25;
		money %= 25;
		final long dimes = money / 10;
		money %= 10;
		final long nickels = money / 5;
		money %= 5;
		final long pennies = money;

		return new long[] {hundreds, fifties, twenties, tens, fives, ones, quarters, dimes, nickels, pennies};
	}
}