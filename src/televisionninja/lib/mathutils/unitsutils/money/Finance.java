package televisionninja.lib.mathutils.unitsutils.money;

import televisionninja.lib.mathutils.MathUtils;

public class Finance {
	/**
	 * 
	 * @param total
	 * @param percent
	 * @return
	 * @author TelevisionNinja
	 */
	public static double tip(double total, double percent) {
		return MathUtils.roundToDecimalPlace(percent / 100d * total, 2);
	}
	
	/**
	 * 
	 * @param total
	 * @param people
	 * @return
	 * @author TelevisionNinja
	 */
	public static double splitBill(double total, int people) {
		return MathUtils.roundToDecimalPlace(total / people, 2);
	}
}